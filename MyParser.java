package xml_parser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class MyParser {
    public static void main(String[] args) {

        Document document = createDoc("https://www.cbr-xml-daily.ru/daily.xml");

        Root root = createRoot(document);

        getOnlineCurse(root, Designation.AMD);

    }

    private static Document createDoc(String path) {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(path);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Root createRoot(Document document) {
//главный нод, его названиие, и все его атрибуты
        Root root = new Root();
        Node rootNode = document.getFirstChild();
        NodeList nodeList = rootNode.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }
            Node node = nodeList.item(i);
            NodeList nodeList1 = node.getChildNodes();
            Money money = new Money();
            for (int j = 0; j < nodeList1.getLength(); j++) {
                String str = nodeList1.item(j).getNodeName();
                switch (str) {
                    case "CharCode" -> money.setDesignation(Designation.valueOf(str));
                    case "Nominal" -> money.setNominal(Integer.parseInt(str));
                    case "Name" -> money.setName(str);
                    case "Value" -> money.setValue(Double.parseDouble(
                            str.replace(',', '.')));
                    default -> {
                    }
                }
            }
            root.getMoneyList().add(money);
        }
        return root;
    }

    private static void getOnlineCurse(Root root, Designation designation) {
        for (Money money : root.getMoneyList()) {
            if (money.getDesignation() == designation) {
                System.out.println("1 " + designation.name() + " = " +
                        money.getValue() / money.getNominal() +
                        " RUB");
                break;
            }
        }
    }
}
