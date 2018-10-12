import domain.propositional.AtomicSentence;
import domain.propositional.Sentence;
import knowledgebase.PLAlgorithms;
import knowledgebase.PLKnowledgeBase;

/**
 * @author Jiupeng Zhang
 * @since 10/11/2018
 */
public class Main {
    public static boolean entails(PLKnowledgeBase kb, PLAlgorithms.Entailment strategy, Sentence... sentences) {
        return strategy.entails(kb, sentences);
    }

    public static void main(String[] args) {
        PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
        knowledgeBase.insert(new AtomicSentence("P")); // TODO
        boolean result = entails(knowledgeBase, PLAlgorithms.Entailment.TruthTableChecking, AtomicSentence.TRUE);
        System.out.println(result);
        System.out.println(knowledgeBase.ask(AtomicSentence.TRUE));
    }
}
