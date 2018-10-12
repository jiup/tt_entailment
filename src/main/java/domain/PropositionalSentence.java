package domain;

/**
 * @author Jiupeng Zhang
 * @since 10/11/2018
 */
public class PropositionalSentence extends Sentence {
    public static final PropositionalSentence TRUE = new PropositionalSentence("true");
    public static final PropositionalSentence FALSE = new PropositionalSentence("false");

    private boolean atomic;
    private boolean complex;
    private Connective connective;
    private String[] variables;
    private PropositionalSentence[] sentences;

    public PropositionalSentence(String variable) {
        this.atomic = true;
        this.connective = Connective.NULL;
        this.variables = new String[]{variable};
    }

    public PropositionalSentence(Connective connective, String... variables) {
        if (variables.length == 0)
            throw new IllegalArgumentException("variable(s) required");

        if (Connective.NULL.equals(connective))
            throw new IllegalArgumentException("invalid connective for sentence (NULL)");

        switch (connective) {
            case AND:
            case OR:
                if (variables.length < 2)
                    throw new IllegalArgumentException("variables not enough for connective '" + connective.name() + "'");
                break;
            case NOT:
                if (variables.length > 1)
                    throw new IllegalArgumentException("too many variables for connective 'NOT'");
                break;
            case IMPLICATION:
            case BI_IMPLICATION:
                if (variables.length != 2)
                    throw new IllegalArgumentException("invalid variable size for connective '" + connective.name() + "', expected 2");
                break;
        }

        this.atomic = false;
        this.connective = connective;
        this.variables = variables;
    }

    public PropositionalSentence(Connective connective, PropositionalSentence... sentences) {
//        if (connective)
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("(");
        if (Connective.NULL.equals(connective)) {
            builder.append(variables[0]);
        } else if (connective == Connective.NOT) {
            builder.append(connective).append(variables[0]);
        } else {
            for (int i = 0; i < variables.length; i++) {
                builder.append(variables[i]);
                if (i < variables.length - 1) {
                    builder.append(" ").append(connective).append(" ");
                }
            }
        }
        return builder.append(")").toString();
    }
}
