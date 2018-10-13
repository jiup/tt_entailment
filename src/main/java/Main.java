import domain.propositional.AtomicSentence;
import domain.propositional.Sentence;
import knowledgebase.PLAlgorithms;
import knowledgebase.PLKnowledgeBase;

import java.util.Arrays;

import static domain.propositional.ComplexSentence.*;

/**
 * @author Jiupeng Zhang
 * @since 10/11/2018
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(entails(testKnowledgeBase(), PLAlgorithms.Entailment.TruthTableChecking,
                NOT(new AtomicSentence("W12")), NOT(new AtomicSentence("W21"))
        ));

        System.out.println(entails(modusPonensKnowledgeBase(), PLAlgorithms.Entailment.TruthTableChecking,
                new AtomicSentence("Q")
        ));

        System.out.println(entails(wumpusWorldKnowledgeBase(), PLAlgorithms.Entailment.TruthTableChecking,
                NOT(new AtomicSentence("P12"))
        ));

        System.out.println(entails(hornClausesKnowledgeBase(), PLAlgorithms.Entailment.TruthTableChecking,
                new AtomicSentence("mythical")
        ));

        System.out.println(entails(hornClausesKnowledgeBase(), PLAlgorithms.Entailment.TruthTableChecking,
                new AtomicSentence("magical")
        ));

        System.out.println(entails(hornClausesKnowledgeBase(), PLAlgorithms.Entailment.TruthTableChecking,
                new AtomicSentence("horned")
        ));

        System.out.println(entails(liarsAndTruthTellersKnowledgeBase(), PLAlgorithms.Entailment.TruthTableChecking,
                AND(NOT(new AtomicSentence("Amy")), NOT(new AtomicSentence("Bob")), new AtomicSentence("Cal"))
        ));

        System.out.println(entails(liarsAndTruthTellers2KnowledgeBase(), PLAlgorithms.Entailment.TruthTableChecking,
                AND(new AtomicSentence("Amy"), NOT(new AtomicSentence("Bob")), NOT(new AtomicSentence("Cal")))
        ));
    }

    public static boolean entails(PLKnowledgeBase kb, PLAlgorithms.Entailment strategy, Sentence... sentences) {
        System.out.println("alpha:  " + kb.list());
        System.out.println("beta:   " + Arrays.toString(sentences));
        return strategy.entails(kb, sentences);
    }

    private static PLKnowledgeBase testKnowledgeBase() {
        PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
        AtomicSentence at11 = new AtomicSentence("At11");
        AtomicSentence s11 = new AtomicSentence("S11");
        AtomicSentence w21 = new AtomicSentence("W21");
        AtomicSentence w12 = new AtomicSentence("W12");
        knowledgeBase.insert(at11);
        knowledgeBase.insert(NOT(s11));
        knowledgeBase.insert(BI_IMPLIES(s11, OR(w21, w12)));
        return knowledgeBase;
    }

    private static PLKnowledgeBase modusPonensKnowledgeBase() {
        PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
        AtomicSentence p = new AtomicSentence("P");
        AtomicSentence q = new AtomicSentence("Q");
        knowledgeBase.insert(p);
        knowledgeBase.insert(IMPLIES(p, q));
        return knowledgeBase;
    }

    private static PLKnowledgeBase wumpusWorldKnowledgeBase() {
        PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
        AtomicSentence p11 = new AtomicSentence("P11");
        AtomicSentence p21 = new AtomicSentence("P21");
        AtomicSentence p22 = new AtomicSentence("P22");
        AtomicSentence p31 = new AtomicSentence("P31");
        AtomicSentence b11 = new AtomicSentence("B11");
        AtomicSentence b21 = new AtomicSentence("B21");
        AtomicSentence p12 = new AtomicSentence("P12");
        knowledgeBase.insert(NOT(p11));
        knowledgeBase.insert(BI_IMPLIES(b11, OR(p12, p21)));
        knowledgeBase.insert(BI_IMPLIES(b21, OR(p11, p22, p31)));
        knowledgeBase.insert(NOT(b11));
        knowledgeBase.insert(b21);
        return knowledgeBase;
    }

    private static PLKnowledgeBase hornClausesKnowledgeBase() {
        PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
        AtomicSentence mythical = new AtomicSentence("mythical");
        AtomicSentence immortal = new AtomicSentence("immortal");
        AtomicSentence mortal = new AtomicSentence("mortal");
        AtomicSentence mammal = new AtomicSentence("mammal");
        AtomicSentence horned = new AtomicSentence("horned");
        AtomicSentence magical = new AtomicSentence("magical");
        knowledgeBase.insert(IMPLIES(mythical, immortal));
        knowledgeBase.insert(IMPLIES(NOT(mythical), AND(mortal, mammal)));
        knowledgeBase.insert(IMPLIES(OR(immortal, mammal), horned));
        knowledgeBase.insert(IMPLIES(horned, magical));
        return knowledgeBase;
    }

    private static PLKnowledgeBase liarsAndTruthTellersKnowledgeBase() {
        PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
        AtomicSentence Amy = new AtomicSentence("Amy");
        AtomicSentence Bob = new AtomicSentence("Bob");
        AtomicSentence Cal = new AtomicSentence("Cal");
        knowledgeBase.insert(BI_IMPLIES(Amy, AND(Amy, Cal)));
        knowledgeBase.insert(BI_IMPLIES(Bob, NOT(Cal)));
        knowledgeBase.insert(BI_IMPLIES(Cal, OR(Bob, NOT(Amy))));
        return knowledgeBase;
    }

    private static PLKnowledgeBase liarsAndTruthTellers2KnowledgeBase() {
        PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
        AtomicSentence Amy = new AtomicSentence("Amy");
        AtomicSentence Bob = new AtomicSentence("Bob");
        AtomicSentence Cal = new AtomicSentence("Cal");
        knowledgeBase.insert(BI_IMPLIES(Amy, NOT(Cal)));
        knowledgeBase.insert(BI_IMPLIES(Bob, AND(Amy, Cal)));
        knowledgeBase.insert(BI_IMPLIES(Cal, Bob));
        return knowledgeBase;
    }
}
