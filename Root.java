package xml_parser;

import java.util.ArrayList;
import java.util.List;

public class Root {
    private String name;
    private List<Money> moneyList = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setMoneyList(List<Money> moneyList) {
        this.moneyList = moneyList;
    }

    public String getName() {
        return name;
    }

    public List<Money> getMoneyList() {
        return moneyList;
    }

    @Override
    public String toString() {
        return "Root{" +
                "name='" + name + '\'' +
                ", moneyList=" + moneyList +
                '}';
    }
}
