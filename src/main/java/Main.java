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

        // 1. Modus Ponens
        System.out.println(entails(modusPonensKnowledgeBase(), PLAlgorithms.Entailment.TruthTableChecking,
                new AtomicSentence("Q")
        ));

        // 2. Wumpus World (Simple)
        System.out.println(entails(wumpusWorldKnowledgeBase(), PLAlgorithms.Entailment.TruthTableChecking,
                NOT(new AtomicSentence("P12"))
        ));

//        // 3. Horn Clauses (a) TODO IN PART II
//        System.out.println(entails(hornClausesKnowledgeBase(), PLAlgorithms.Entailment.TruthTableChecking,
//                new AtomicSentence("mythical")
//        ));
//
//        // 3. Horn Clauses (b)
//        System.out.println(entails(hornClausesKnowledgeBase(), PLAlgorithms.Entailment.TruthTableChecking,
//                new AtomicSentence("magical")
//        ));
//
//        // 3. Horn Clauses (c)
//        System.out.println(entails(hornClausesKnowledgeBase(), PLAlgorithms.Entailment.TruthTableChecking,
//                new AtomicSentence("horned")
//        ));

        // 4. Liars and Truth-tellers (a)
        System.out.println(entails(liarsAndTruthTellersKnowledgeBase(), PLAlgorithms.Entailment.TruthTableChecking,
                AND(NOT(new AtomicSentence("Amy")), NOT(new AtomicSentence("Bob")), new AtomicSentence("Cal"))
        ));

        // 4. Liars and Truth-tellers (b)
        System.out.println(entails(liarsAndTruthTellers2KnowledgeBase(), PLAlgorithms.Entailment.TruthTableChecking,
                AND(new AtomicSentence("Amy"), NOT(new AtomicSentence("Bob")), NOT(new AtomicSentence("Cal")))
        ));

        // 5. More Liars and Truth-tellers
        System.out.println(entails(liarsAndTruthTellers3KnowledgeBase(), PLAlgorithms.Entailment.TruthTableChecking,
                AND(NOT(new AtomicSentence("Amy")), NOT(new AtomicSentence("Bob")), NOT(new AtomicSentence("Cal")),
                        NOT(new AtomicSentence("Dee")), NOT(new AtomicSentence("Eli")), NOT(new AtomicSentence("Fay")),
                        NOT(new AtomicSentence("Gil")), NOT(new AtomicSentence("Hal")), NOT(new AtomicSentence("Ida")),
                        NOT(new AtomicSentence("Gil")), NOT(new AtomicSentence("Hal")), NOT(new AtomicSentence("Ida")),
                        new AtomicSentence("Jay"), new AtomicSentence("Kay"), NOT(new AtomicSentence("Lee")))
        ));

        // 6. The Doors of Enlightenment
        System.out.println(entails(doorsOfEnlightenmentKnowledgeBase(), PLAlgorithms.Entailment.TruthTableChecking,
                AND(new AtomicSentence("X"), NOT(new AtomicSentence("Y")), NOT(new AtomicSentence("Z")), NOT(new AtomicSentence("W")))
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
        AtomicSentence mammal = new AtomicSentence("mammal");
        AtomicSentence horned = new AtomicSentence("horned");
        AtomicSentence magical = new AtomicSentence("magical");
        knowledgeBase.insert(IMPLIES(mythical, immortal));
        knowledgeBase.insert(IMPLIES(NOT(mythical), AND(NOT(immortal), mammal)));
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

    private static PLKnowledgeBase liarsAndTruthTellers3KnowledgeBase() {
        PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
        AtomicSentence Amy = new AtomicSentence("Amy");
        AtomicSentence Bob = new AtomicSentence("Bob");
        AtomicSentence Cal = new AtomicSentence("Cal");
        AtomicSentence Dee = new AtomicSentence("Dee");
        AtomicSentence Eli = new AtomicSentence("Eli");
        AtomicSentence Fay = new AtomicSentence("Fay");
        AtomicSentence Gil = new AtomicSentence("Gil");
        AtomicSentence Hal = new AtomicSentence("Hal");
        AtomicSentence Ida = new AtomicSentence("Ida");
        AtomicSentence Jay = new AtomicSentence("Jay");
        AtomicSentence Kay = new AtomicSentence("Kay");
        AtomicSentence Lee = new AtomicSentence("Lee");
        knowledgeBase.insert(BI_IMPLIES(Amy, AND(Hal, Ida)));
        knowledgeBase.insert(BI_IMPLIES(Bob, AND(Amy, Lee)));
        knowledgeBase.insert(BI_IMPLIES(Cal, AND(Bob, Gil)));
        knowledgeBase.insert(BI_IMPLIES(Dee, AND(Eli, Lee)));
        knowledgeBase.insert(BI_IMPLIES(Eli, AND(Cal, Hal)));
        knowledgeBase.insert(BI_IMPLIES(Fay, AND(Dee, Ida)));
        knowledgeBase.insert(BI_IMPLIES(Gil, AND(NOT(Eli), NOT(Jay))));
        knowledgeBase.insert(BI_IMPLIES(Hal, AND(NOT(Fay), NOT(Kay))));
        knowledgeBase.insert(BI_IMPLIES(Ida, AND(NOT(Gil), NOT(Kay))));
        knowledgeBase.insert(BI_IMPLIES(Jay, AND(NOT(Amy), NOT(Cal))));
        knowledgeBase.insert(BI_IMPLIES(Kay, AND(NOT(Dee), NOT(Fay))));
        knowledgeBase.insert(BI_IMPLIES(Lee, AND(NOT(Bob), NOT(Jay))));
        return knowledgeBase;
    }

    private static PLKnowledgeBase doorsOfEnlightenmentKnowledgeBase() {
        PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
        AtomicSentence x = new AtomicSentence("X");
        AtomicSentence y = new AtomicSentence("Y");
        AtomicSentence z = new AtomicSentence("Z");
        AtomicSentence w = new AtomicSentence("W");
        AtomicSentence a = new AtomicSentence("A");
        AtomicSentence b = new AtomicSentence("B");
        AtomicSentence c = new AtomicSentence("C");
        AtomicSentence d = new AtomicSentence("D");
        AtomicSentence e = new AtomicSentence("E");
        AtomicSentence f = new AtomicSentence("F");
        AtomicSentence g = new AtomicSentence("G");
        AtomicSentence h = new AtomicSentence("H");
        knowledgeBase.insert(BI_IMPLIES(a, x));
        knowledgeBase.insert(BI_IMPLIES(b, OR(y, z)));
        knowledgeBase.insert(BI_IMPLIES(c, AND(a, b)));
        knowledgeBase.insert(BI_IMPLIES(d, AND(x, y)));
        knowledgeBase.insert(BI_IMPLIES(e, AND(x, z)));
        knowledgeBase.insert(BI_IMPLIES(f, OR(AND(d, NOT(e)), AND(NOT(d), e))));
        knowledgeBase.insert(BI_IMPLIES(g, IMPLIES(c, f)));
        knowledgeBase.insert(BI_IMPLIES(h, IMPLIES(AND(g, h), a)));
        knowledgeBase.insert(IMPLIES(AND(NOT(x), NOT(y), NOT(z)), w));
        return knowledgeBase;
    }
}
