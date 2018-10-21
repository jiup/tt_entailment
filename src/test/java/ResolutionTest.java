import domain.propositional.AtomicSentence;
import knowledgebase.PLKnowledgeBase;
import org.junit.Assert;
import org.junit.Test;

import static domain.propositional.ComplexSentence.NOT;
import static domain.propositional.ComplexSentence.OR;
import static knowledgebase.PLAlgorithms.Entailment.Resolution;

/**
 * @author Jiupeng Zhang
 * @since 10/18/2018
 */
public class ResolutionTest {
    @Test
    public void testBasicFunction() {
        AtomicSentence a = new AtomicSentence("A");
        AtomicSentence b = new AtomicSentence("B");
        AtomicSentence c = new AtomicSentence("C");
        PLKnowledgeBase kb = new PLKnowledgeBase();
        Assert.assertTrue(Resolution.entails(kb, a));
        Assert.assertTrue(kb.insert(NOT(b)));
        Assert.assertFalse(kb.insert(NOT(b)));
        Assert.assertTrue(Resolution.entails(kb, NOT(b)));
        Assert.assertTrue(kb.insert(OR(a, b)));
        Assert.assertTrue(Resolution.entails(kb, a));
    }

    @Test
    public void testExample() {
        AtomicSentence w12 = new AtomicSentence("W12");
        AtomicSentence w21 = new AtomicSentence("W21");
        Assert.assertTrue(Resolution.entails(KnowledgeBases.exampleKnowledgeBase(true), NOT(w12), NOT(w21))); // TODO
    }

    @Test
    public void testModusPonens() {
        AtomicSentence q = new AtomicSentence("Q");
        Assert.assertTrue(Resolution.entails(KnowledgeBases.modusPonensKnowledgeBase(true), q));
    }

    @Test
    public void testSimpleWumpusWorld() {
        AtomicSentence p12 = new AtomicSentence("P12");
        Assert.assertTrue(Resolution.entails(KnowledgeBases.wumpusWorldKnowledgeBase(true), NOT(p12)));
    }

    @Test
    public void testHornClauses() {
        AtomicSentence mythical = new AtomicSentence("mythical");
        AtomicSentence magical = new AtomicSentence("magical");
        AtomicSentence horned = new AtomicSentence("horned");
        Assert.assertFalse(Resolution.entails(KnowledgeBases.hornClausesKnowledgeBase(true), mythical));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.hornClausesKnowledgeBase(true), magical));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.hornClausesKnowledgeBase(true), horned));
    }

    @Test
    public void testLiarsAndTruthTellers() {
        AtomicSentence amy = new AtomicSentence("Amy");
        AtomicSentence bob = new AtomicSentence("Bob");
        AtomicSentence cal = new AtomicSentence("Cal");

        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers1KnowledgeBase(true), NOT(amy)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers1KnowledgeBase(true), NOT(bob)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers1KnowledgeBase(true), cal));

        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers2KnowledgeBase(true), amy));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers2KnowledgeBase(true), NOT(bob)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers2KnowledgeBase(true), NOT(cal)));
    }

    @Test
    public void testMoreLiarsAndTruthTellers() {
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
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(true), NOT(Amy)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(true), NOT(Bob)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(true), NOT(Cal)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(true), NOT(Dee)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(true), NOT(Eli)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(true), NOT(Fay)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(true), NOT(Gil)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(true), NOT(Hal)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(true), NOT(Ida)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(true), Jay));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(true), Kay));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(true), NOT(Lee)));
    }

    @Test
    public void testTheDoorsOfEnlightenment() {
        AtomicSentence x = new AtomicSentence("X");
        AtomicSentence y = new AtomicSentence("Y");
        AtomicSentence z = new AtomicSentence("Z");
        AtomicSentence w = new AtomicSentence("W");

        Assert.assertTrue(Resolution.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(true), x));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(true), y));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(true), z));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(true), w));

        Assert.assertTrue(Resolution.entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(true), x));
    }
}
