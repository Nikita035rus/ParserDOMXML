package xml_parser;

import java.util.ArrayList;
import java.util.List;

public class ListMoney {
    private List<Valute> moneyList = new ArrayList<>();

    public void setMoneyList(List<Valute> moneyList) {
        this.moneyList = moneyList;
    }

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
