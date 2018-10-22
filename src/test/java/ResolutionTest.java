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
        Assert.assertTrue(Resolution.entails(KnowledgeBases.exampleKnowledgeBase(), NOT(w12), NOT(w21)));
    }

    @Test
    public void testModusPonens() {
        AtomicSentence q = new AtomicSentence("Q");
        Assert.assertTrue(Resolution.entails(KnowledgeBases.modusPonensKnowledgeBase(), q));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.modusPonensKnowledgeBase(), NOT(q)));
    }

    @Test
    public void testSimpleWumpusWorld() {
        AtomicSentence p12 = new AtomicSentence("P12");
        Assert.assertTrue(Resolution.entails(KnowledgeBases.wumpusWorldKnowledgeBase(), NOT(p12)));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.wumpusWorldKnowledgeBase(), p12));
    }

    @Test
    public void testHornClauses() {
        AtomicSentence mythical = new AtomicSentence("mythical");
        AtomicSentence magical = new AtomicSentence("magical");
        AtomicSentence horned = new AtomicSentence("horned");
        Assert.assertFalse(Resolution.entails(KnowledgeBases.hornClausesKnowledgeBase(), mythical));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.hornClausesKnowledgeBase(), NOT(mythical)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.hornClausesKnowledgeBase(), magical));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.hornClausesKnowledgeBase(), NOT(magical)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.hornClausesKnowledgeBase(), horned));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.hornClausesKnowledgeBase(), NOT(horned)));
    }

    @Test
    public void testLiarsAndTruthTellers() {
        AtomicSentence amy = new AtomicSentence("Amy");
        AtomicSentence bob = new AtomicSentence("Bob");
        AtomicSentence cal = new AtomicSentence("Cal");

        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers1KnowledgeBase(), NOT(amy)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers1KnowledgeBase(), NOT(bob)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers1KnowledgeBase(), cal));

        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers2KnowledgeBase(), amy));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers2KnowledgeBase(), NOT(bob)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers2KnowledgeBase(), NOT(cal)));
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

        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), NOT(Amy)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), NOT(Bob)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), NOT(Cal)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), NOT(Dee)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), NOT(Eli)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), NOT(Fay)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), NOT(Gil)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), NOT(Hal)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), NOT(Ida)));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), Jay));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), Kay));
        Assert.assertTrue(Resolution.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), NOT(Lee)));
    }

    @Test
    public void testTheDoorsOfEnlightenment() {
        AtomicSentence x = new AtomicSentence("X");
        AtomicSentence y = new AtomicSentence("Y");
        AtomicSentence z = new AtomicSentence("Z");
        AtomicSentence w = new AtomicSentence("W");

        Assert.assertTrue(Resolution.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), x));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), NOT(x)));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), y));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), NOT(y)));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), z));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), NOT(z)));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), w));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), NOT(w)));

        Assert.assertTrue(Resolution.entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), x));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), NOT(x)));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), y));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), NOT(y)));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), z));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), NOT(z)));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), w));
        Assert.assertFalse(Resolution.entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), NOT(w)));
    }
}
