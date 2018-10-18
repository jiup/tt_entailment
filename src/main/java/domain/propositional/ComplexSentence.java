package domain.propositional;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Jiupeng Zhang
 * @since 10/12/2018
 */
public class ComplexSentence extends Sentence {
    private Connective connective;
    private Set<Sentence> clauses;

    public static ComplexSentence AND(Sentence... clauses) {
        return new ComplexSentence(Connective.AND, clauses);
    }

    public static ComplexSentence OR(Sentence... clauses) {
        return new ComplexSentence(Connective.OR, clauses);
    }

    public static ComplexSentence NOT(Sentence sentence) {
        return new ComplexSentence(Connective.NOT, sentence);
    }

    public static ComplexSentence IMPLIES(Sentence clause1, Sentence clause2) {
        return new ComplexSentence(Connective.IMPLICATION, clause1, clause2);
    }

    public static ComplexSentence BI_IMPLIES(Sentence clause1, Sentence clause2) {
        return new ComplexSentence(Connective.BI_IMPLICATION, clause1, clause2);
    }

    public ComplexSentence(Connective connective, Sentence... clauses) {
        validate(connective, clauses.length);
        this.connective = connective;
        this.clauses = new LinkedHashSet<>(Arrays.asList(clauses));
    }

    public Set<Sentence> getClauses() {
        return clauses;
    }

    public Connective getConnective() {
        return connective;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexSentence that = (ComplexSentence) o;
        return connective == that.connective &&
                Objects.equals(clauses, that.clauses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connective, clauses);
    }

    @Override
    public String toString() {
        Sentence[] clauses = new Sentence[this.clauses.size()];
        this.clauses.toArray(clauses);
        StringBuilder builder = new StringBuilder();
        if (Connective.NULL.equals(connective)) {
            builder.append(clauses[0]);
        } else if (connective == Connective.NOT) {
            builder.append(connective).append(clauses[0]);
        } else {
            builder.append("(");
            for (int i = 0; i < clauses.length; i++) {
                builder.append(clauses[i]);
                if (i < clauses.length - 1) {
                    builder.append(" ").append(connective).append(" ");
                }
            }
            builder.append(")");
        }
        return builder.toString();
    }

    private void validate(Connective connective, int clauseCount) {
        if (clauseCount == 0)
            throw new IllegalArgumentException("clauses required");

        if (Connective.NULL.equals(connective))
            throw new IllegalArgumentException("invalid connective for sentence (NULL)");

        switch (connective) {
            case AND:
            case OR:
                if (clauseCount < 2)
                    throw new IllegalArgumentException("clause not enough for '" + connective.name() + "', expected 2+");
                break;
            case NOT:
                if (clauseCount > 1)
                    throw new IllegalArgumentException("too many clauses for '" + connective.name() + "', expected 1");
                break;
            case IMPLICATION:
            case BI_IMPLICATION:
                if (clauseCount != 2)
                    throw new IllegalArgumentException("invalid clause number for '" + connective.name() + "', expected 2");
                break;
        }
    }
}
