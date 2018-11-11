import domain.propositional.AtomicSentence;
import domain.propositional.Sentence;
import knowledgebase.PLAlgorithms;
import knowledgebase.PLKnowledgeBase;

import java.util.Arrays;

import static domain.propositional.ComplexSentence.AND;
import static domain.propositional.ComplexSentence.NOT;

/**
 * @author Jiupeng Zhang
 * @since 10/22/2018
 */
public class SampleTest {
    private PLAlgorithms.Entailment strategy;

    public SampleTest(PLAlgorithms.Entailment strategy) {
        this.strategy = strategy;
        PLAlgorithms.DEBUG = true;
    }

    public void test(String[] args) {
        if (args.length == 0) {
            args = new String[]{"1", "2", "3a", "3b", "3c", "4a", "4b", "5", "6a", "6b"};
        }

        for (String sample : args) {
            System.out.println("Sample #" + sample + ": ");
            switch (sample) {
                case "1":
                    AtomicSentence q = new AtomicSentence("Q");
                    System.out.println("Is Q true?\n" + entails(KnowledgeBases.modusPonensKnowledgeBase(), strategy, q));
                    break;

                case "2":
                    AtomicSentence p12 = new AtomicSentence("P12");
                    System.out.println("Is P12 true?\n" + entails(KnowledgeBases.wumpusWorldKnowledgeBase(), strategy, p12));
                    break;

                case "3a":
                    System.out.println("Can we prove that unicorn is mythical? ");
                    if (provable(KnowledgeBases.hornClausesKnowledgeBase(), strategy, new AtomicSentence("mythical")))
                        System.out.println("true\n\nIs unicorn mythical?\n" + entails(KnowledgeBases.hornClausesKnowledgeBase(), strategy, new AtomicSentence("mythical")));
                    else
                        System.out.println(false);
                    break;

                case "3b":
                    System.out.println("Can we prove that unicorn is magical? ");
                    if (provable(KnowledgeBases.hornClausesKnowledgeBase(), strategy, new AtomicSentence("magical")))
                        System.out.println("true\n\nIs unicorn magical?\n" + entails(KnowledgeBases.hornClausesKnowledgeBase(), strategy, new AtomicSentence("magical")));
                    else
                        System.out.println(false);
                    break;

                case "3c":
                    System.out.println("Can we prove that unicorn is horned? ");
                    if (provable(KnowledgeBases.hornClausesKnowledgeBase(), strategy, new AtomicSentence("horned")))
                        System.out.println("true\n\nIs unicorn horned?\n" + entails(KnowledgeBases.hornClausesKnowledgeBase(), strategy, new AtomicSentence("horned")));
                    else
                        System.out.println(false);
                    break;

                case "4a":
                    System.out.println("Is Amy a truth-teller?\n" + entails(KnowledgeBases.liarsAndTruthTellers1KnowledgeBase(), strategy, new AtomicSentence("Amy")) + "\n");
                    System.out.println("Is Bob a truth-teller?\n" + entails(KnowledgeBases.liarsAndTruthTellers1KnowledgeBase(), strategy, new AtomicSentence("Bob")) + "\n");
                    System.out.println("Is Cal a truth-teller?\n" + entails(KnowledgeBases.liarsAndTruthTellers1KnowledgeBase(), strategy, new AtomicSentence("Cal")) + "\n");
                    break;

                case "4b":
                    System.out.println("Is Amy a truth-teller?\n" + entails(KnowledgeBases.liarsAndTruthTellers2KnowledgeBase(), strategy, new AtomicSentence("Amy")) + "\n");
                    System.out.println("Is Bob a truth-teller?\n" + entails(KnowledgeBases.liarsAndTruthTellers2KnowledgeBase(), strategy, new AtomicSentence("Bob")) + "\n");
                    System.out.println("Is Cal a truth-teller?\n" + entails(KnowledgeBases.liarsAndTruthTellers2KnowledgeBase(), strategy, new AtomicSentence("Cal")) + "\n");
                    break;

                case "5":
                    if (strategy == PLAlgorithms.Entailment.Resolution) PLAlgorithms.DEBUG = false;
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
                    System.out.println("Is Amy a truth-teller?\n" + strategy.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), new AtomicSentence("Amy")) + "\n");
                    System.out.println("Is Bob a truth-teller?\n" + strategy.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), new AtomicSentence("Bob")) + "\n");
                    System.out.println("Is Cal a truth-teller?\n" + strategy.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), new AtomicSentence("Cal")) + "\n");
                    System.out.println("Is Dee a truth-teller?\n" + strategy.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), new AtomicSentence("Dee")) + "\n");
                    System.out.println("Is Eli a truth-teller?\n" + strategy.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), new AtomicSentence("Eli")) + "\n");
                    System.out.println("Is Fay a truth-teller?\n" + strategy.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), new AtomicSentence("Fay")) + "\n");
                    System.out.println("Is Gil a truth-teller?\n" + strategy.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), new AtomicSentence("Gil")) + "\n");
                    System.out.println("Is Hal a truth-teller?\n" + strategy.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), new AtomicSentence("Hal")) + "\n");
                    System.out.println("Is Ida a truth-teller?\n" + strategy.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), new AtomicSentence("Ida")) + "\n");
                    System.out.println("Is Jay a truth-teller?\n" + strategy.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), new AtomicSentence("Jay")) + "\n");
                    System.out.println("Is Kay a truth-teller?\n" + strategy.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), new AtomicSentence("Kay")) + "\n");
                    System.out.println("Is Lee a truth-teller?\n" + strategy.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), new AtomicSentence("Lee")) + "\n");
                    if (strategy == PLAlgorithms.Entailment.Resolution) PLAlgorithms.DEBUG = true;
                    break;

                case "6a":
                    if (strategy == PLAlgorithms.Entailment.Resolution) PLAlgorithms.DEBUG = false;
                    System.out.println("Can we prove if X is a good door? ");
                    if (provable(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), strategy, new AtomicSentence("X")))
                        System.out.println("true\n\nIs X a good door?\n" + entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), strategy, new AtomicSentence("X")) + "\n");
                    else System.out.println(false);

                    System.out.println("Can we prove if Y is a good door? ");
                    if (provable(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), strategy, new AtomicSentence("Y")))
                        System.out.println("true\n\nIs Y a good door?\n" + entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), strategy, new AtomicSentence("Y")) + "\n");
                    else System.out.println(false);

                    System.out.println("Can we prove if Z is a good door? ");
                    if (provable(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), strategy, new AtomicSentence("Z")))
                        System.out.println("true\n\nIs Z a good door?\n" + entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), strategy, new AtomicSentence("Z")) + "\n");
                    else System.out.println(false);

                    System.out.println("Can we prove if W is a good door? ");
                    if (provable(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), strategy, new AtomicSentence("W")))
                        System.out.println("true\n\nIs W a good door?\n" + entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), strategy, new AtomicSentence("W")) + "\n");
                    else System.out.println(false);
                    if (strategy == PLAlgorithms.Entailment.Resolution) PLAlgorithms.DEBUG = true;
                    break;

                case "6b":
                    System.out.println("Can we prove if X is a good door? ");
                    if (provable(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), strategy, new AtomicSentence("X")))
                        System.out.println("true\n\nIs X a good door?\n" + entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), strategy, new AtomicSentence("X")) + "\n");
                    else System.out.println(false);

                    System.out.println("Can we prove if Y is a good door? ");
                    if (provable(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), strategy, new AtomicSentence("Y")))
                        System.out.println("true\n\nIs Y a good door?\n" + entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), strategy, new AtomicSentence("Y")) + "\n");
                    else System.out.println(false);

                    System.out.println("Can we prove if Z is a good door? ");
                    if (provable(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), strategy, new AtomicSentence("Z")))
                        System.out.println("true\n\nIs Z a good door?\n" + entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), strategy, new AtomicSentence("Z")) + "\n");
                    else System.out.println(false);

                    System.out.println("Can we prove if W is a good door? ");
                    if (provable(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), strategy, new AtomicSentence("W")))
                        System.out.println("true\n\nIs W a good door?\n" + entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), strategy, new AtomicSentence("W")) + "\n");
                    else System.out.println(false);
                    break;
                default:
                    System.err.println("unknown sample: '" + sample + "'");
            }
            System.out.println();
        }
    }

    public static boolean provable(PLKnowledgeBase kb, PLAlgorithms.Entailment strategy, Sentence... sentences) {
        boolean tmp = PLAlgorithms.DEBUG;
        PLAlgorithms.DEBUG = false;
        boolean result = strategy.entails(kb, sentences) != strategy.entails(kb, NOT(sentences.length > 1 ? AND(sentences) : sentences[0]));
        PLAlgorithms.DEBUG = tmp;
        return result;
    }

    public static boolean entails(PLKnowledgeBase kb, PLAlgorithms.Entailment strategy, Sentence... sentences) {
        System.out.println("KB: " + kb.list());
        System.out.println("α:  " + Arrays.toString(sentences));
        return strategy.entails(kb, sentences);
    }
}
