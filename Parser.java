package xml_parser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Parser {

    private static final String TAG_CHAR_CODE = "CharCode";
    private static final String TAG_NOMINAL = "Nominal";
    private static final String TAG_NAME = "Name";
    private static final String TAG_VALUE = "Value";

    public String curse(CodeValute codeValute) {

        Document document = buildDocument("https://www.cbr-xml-daily.ru/daily.xml");

        ValuteList root = parseValuteList(document);

        return getOnlineCurse(root, codeValute);
    }

    private Document buildDocument(String path) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(path);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            return null;
        }
    }

    private ValuteList parseValuteList(Document document) {
//главный нод, его названиие, и все его атрибуты
        ValuteList root = new ValuteList();
        Node rootNode = document.getFirstChild();
        NodeList nodeList = rootNode.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Node node = nodeList.item(i);
            Valute valute = parseValute(node);
            root.getMoneyList().add(valute);
        }
        return root;
    }

    private Valute parseValute(Node node) {
        NodeList nodeList = node.getChildNodes();
        Valute valute = new Valute();
        for (int j = 0; j < nodeList.getLength(); j++) {
            String str1 = nodeList.item(j).getNodeName();
            String str = nodeList.item(j).getTextContent();
            switch (str1) {
                case TAG_CHAR_CODE -> valute.setDesignation(CodeValute.valueOf(str));
                case TAG_NOMINAL -> valute.setNominal(Integer.parseInt(str));
                case TAG_NAME -> valute.setName(str);
                case TAG_VALUE -> valute.setValue(Double.parseDouble(
                        str.replace(',', '.')));
            }
        }
        return valute;
    }

    private String getOnlineCurse(ValuteList root, CodeValute codeValute) {
        for (Valute money : root.getMoneyList()) {
            if (money.getDesignation() == codeValute) {
                return "1 " + codeValute.name() + " = " +
                        (money.getValue() / money.getNominal()) +
                        " RUB";
            }
        }
        return null;
    }
}
