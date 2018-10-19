package domain.propositional;

/**
 * @author Jiupeng Zhang
 * @since 10/12/2018
 */
public enum Connective {
    NULL, AND("∧"), OR("∨"), NOT("¬"), IMPLICATION("==>"), BI_IMPLICATION("<=>");

    String symbol;

    Connective() {
        //.
    }

    Connective(String symbol) {
        this.symbol = symbol;
    }

    public boolean equals(Connective connective) {
        return symbol == null ? connective.symbol == null : symbol.equals(connective.symbol);
    }

    @Override
    public String toString() {
        return symbol;
    }
}
