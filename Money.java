package xml_parser;

public class Money {
        private Designation designation;
        private int nominal;
        private String name;
        private double value;

        public Money(){}
        public Money(Designation designation, int nominal, String name, double value) {
                this.designation = designation;
                this.nominal = nominal;
                this.name = name;
                this.value = value;
        }

        public Designation getDesignation() {
                return designation;
        }

        public void setDesignation(Designation designation) {
                this.designation = designation;
        }

        public int getNominal() {
                return nominal;
        }

        public void setNominal(int nominal) {
                this.nominal = nominal;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public double getValue() {
                return value;
        }

        public void setValue(double value) {
                this.value = value;
        }

        @Override
        public String toString() {
                return "Money{" +
                        "designation=" + designation +
                        ", nominal=" + nominal +
                        ", name='" + name + '\'' +
                        ", value=" + value +
                        '}';
        }
}
