import domain.propositional.AtomicSentence;
import domain.propositional.ComplexSentence;
import domain.propositional.Sentence;
import knowledgebase.PLAlgorithms;
import knowledgebase.PLKnowledgeBase;

/**
 * @author Jiupeng Zhang
 * @since 10/11/2018
 */
public class Main {
    public static boolean entails(PLKnowledgeBase kb, PLAlgorithms.Entailment strategy, Sentence... sentences) {
//        System.out.println("KB:     " + kb.list());
//        System.out.println("ALPHA:  " + Arrays.toString(sentences));
        return strategy.entails(kb, sentences);
    }

    public static void main(String[] args) {
        PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
        knowledgeBase.insert(new AtomicSentence("At11"));
        knowledgeBase.insert(ComplexSentence.NOT(new AtomicSentence("S11")));
        knowledgeBase.insert(ComplexSentence.BI_IMPLIES(new AtomicSentence("S11"),
                ComplexSentence.OR(new AtomicSentence("W21"), new AtomicSentence("W12"))));

        System.out.println(entails(knowledgeBase,
                PLAlgorithms.Entailment.TruthTableChecking,
                ComplexSentence.NOT(new AtomicSentence("W12")),
                ComplexSentence.NOT(new AtomicSentence("W21"))
        ));
    }
}
