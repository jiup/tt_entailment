import domain.propositional.AtomicSentence;
import domain.propositional.Sentence;
import knowledgebase.PLAlgorithms;
import knowledgebase.PLKnowledgeBase;
import util.propositional.SentenceUtil;

import java.util.Arrays;

import static domain.propositional.ComplexSentence.*;
import static knowledgebase.PLAlgorithms.Entailment.ModelChecking;
import static knowledgebase.PLAlgorithms.Entailment.RecursiveModelChecking;

/**
 * @author Jiupeng Zhang
 * @since 10/11/2018
 */
public class Main {
    public static void main(String[] args) {
//        PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
//        AtomicSentence at11 = new AtomicSentence("At11");
//        AtomicSentence s11 = new AtomicSentence("S11");
//        AtomicSentence w21 = new AtomicSentence("W21");
//        AtomicSentence w12 = new AtomicSentence("W12");
//        knowledgeBase.insert(at11);
//        knowledgeBase.insert(NOT(s11));
//        knowledgeBase.insert(BI_IMPLIES(s11, OR(w21, w12)));
//        System.out.println(ModelChecking.entails(knowledgeBase, NOT(w12), NOT(w21)));
//        System.out.println(RecursiveModelChecking.entails(knowledgeBase, NOT(w12), NOT(w21)));
//        System.out.println(ModelChecking.entails(KnowledgeBases.exampleKnowledgeBase(), ));
        SentenceUtil
                .convertToCNF(AND(KnowledgeBases.modusPonensKnowledgeBase().list().toArray(new Sentence[0])))
                .forEach(System.out::println);
    }

    public static boolean entails(PLKnowledgeBase kb, PLAlgorithms.Entailment strategy, Sentence... sentences) {
        System.out.println("alpha:  " + kb.list());
        System.out.println("beta:   " + Arrays.toString(sentences));
        return strategy.entails(kb, sentences);
    }
}
