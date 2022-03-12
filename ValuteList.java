package xml_parser;

import java.util.ArrayList;
import java.util.List;

public class ValuteList {
    private static final List<Valute> moneyList = new ArrayList<>();


    public List<Valute> getMoneyList() {
        return moneyList;
    }

    @Override
    public String toString() {
        return "Root{" +
                "moneyList=" + moneyList +
                '}';
    }
}
