import domain.Connective;
import domain.PropositionalSentence;
import kb.impl.PropositionalKnowledgeBase;

import static domain.Connective.*;

/**
 * @author Jiupeng Zhang
 * @since 10/11/2018
 */
public class Main {
    public boolean ttEntails(PropositionalKnowledgeBase kb,
                              PropositionalSentence sentence) {
        return false;
    }
    public static void main(String[] args) {
        System.out.println(AND);
        System.out.println(Connective.OR);
        System.out.println(Connective.NOT);
        System.out.println(Connective.IMPLICATION);
        System.out.println(Connective.BI_IMPLICATION);
        System.out.println(Connective.NULL);
        System.out.println(new PropositionalSentence("P1"));
        System.out.println(new PropositionalSentence(OR, "P2", "P3", "P4"));
        System.out.println(new PropositionalSentence(NOT, "P2"));
        System.out.println(new PropositionalSentence(OR, "P2"));
    }
}
