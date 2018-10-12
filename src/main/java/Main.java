import domain.propositional.AtomicSentence;
import domain.propositional.ComplexSentence;
import domain.propositional.Connective;
import domain.propositional.Sentence;
import kb.PropositionalKnowledgeBase;

import static domain.propositional.Connective.AND;
import static domain.propositional.Connective.NOT;

/**
 * @author Jiupeng Zhang
 * @since 10/11/2018
 */
public class Main {
    public boolean ttEntails(PropositionalKnowledgeBase kb, Sentence sentence) {
        return false;
    }

    public static void main(String[] args) {
        System.out.println(AND);
        System.out.println(Connective.OR);
        System.out.println(Connective.NOT);
        System.out.println(Connective.IMPLICATION);
        System.out.println(Connective.BI_IMPLICATION);
        System.out.println(Connective.NULL);
        System.out.println(new ComplexSentence(AND, new ComplexSentence(NOT, AtomicSentence.TRUE), AtomicSentence.FALSE));
        System.out.println(ComplexSentence.AND(ComplexSentence.NOT(AtomicSentence.TRUE), AtomicSentence.FALSE));
        System.out.println(new ComplexSentence(AND, AtomicSentence.FALSE, AtomicSentence.TRUE));
    }
}
