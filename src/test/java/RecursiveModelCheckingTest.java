import domain.propositional.AtomicSentence;
import org.junit.Assert;
import org.junit.Test;

import static domain.propositional.ComplexSentence.AND;
import static domain.propositional.ComplexSentence.NOT;
import static knowledgebase.PLAlgorithms.Entailment.RecursiveModelChecking;

/**
 * @author Jiupeng Zhang
 * @since 10/19/2018
 */
public class RecursiveModelCheckingTest {
    @Test
    public void testExample() {
        AtomicSentence w12 = new AtomicSentence("W12");
        AtomicSentence w21 = new AtomicSentence("W21");
        Assert.assertTrue(RecursiveModelChecking.entails(KnowledgeBases.exampleKnowledgeBase(), NOT(w12), NOT(w21)));
    }

    @Test
    public void testModusPonens() {
        AtomicSentence q = new AtomicSentence("Q");
        Assert.assertTrue(RecursiveModelChecking.entails(KnowledgeBases.modusPonensKnowledgeBase(), q));
    }

    @Test
    public void testSimpleWumpusWorld() {
        AtomicSentence p12 = new AtomicSentence("P12");
        Assert.assertTrue(RecursiveModelChecking.entails(KnowledgeBases.wumpusWorldKnowledgeBase(), NOT(p12)));
    }

    @Test
    public void testHornClauses() {
        AtomicSentence mythical = new AtomicSentence("mythical");
        AtomicSentence magical = new AtomicSentence("magical");
        AtomicSentence horned = new AtomicSentence("horned");
        Assert.assertFalse(RecursiveModelChecking.entails(KnowledgeBases.hornClausesKnowledgeBase(), mythical));
        Assert.assertTrue(RecursiveModelChecking.entails(KnowledgeBases.hornClausesKnowledgeBase(), magical));
        Assert.assertTrue(RecursiveModelChecking.entails(KnowledgeBases.hornClausesKnowledgeBase(), horned));
    }

    @Test
    public void testLiarsAndTruthTellers() {
        AtomicSentence amy = new AtomicSentence("Amy");
        AtomicSentence bob = new AtomicSentence("Bob");
        AtomicSentence cal = new AtomicSentence("Cal");
        Assert.assertTrue(RecursiveModelChecking.entails(KnowledgeBases.liarsAndTruthTellers1KnowledgeBase(), AND(NOT(amy), NOT(bob), cal)));
        Assert.assertTrue(RecursiveModelChecking.entails(KnowledgeBases.liarsAndTruthTellers2KnowledgeBase(), AND(amy, NOT(bob), NOT(cal))));
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
        Assert.assertTrue(RecursiveModelChecking.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), AND(
                NOT(Amy), NOT(Bob), NOT(Cal), NOT(Dee), NOT(Eli), NOT(Fay), NOT(Gil), NOT(Hal), NOT(Ida), Jay, Kay, NOT(Lee)
        )));
    }

    @Test
    public void testTheDoorsOfEnlightenment() {
        AtomicSentence x = new AtomicSentence("X");
        AtomicSentence y = new AtomicSentence("Y");
        AtomicSentence z = new AtomicSentence("Z");
        AtomicSentence w = new AtomicSentence("W");
        Assert.assertTrue(RecursiveModelChecking.entails(KnowledgeBases.doorsOfEnlightenmentKnowledgeBase(), x));
        Assert.assertFalse(RecursiveModelChecking.entails(KnowledgeBases.doorsOfEnlightenmentKnowledgeBase(), y));
        Assert.assertFalse(RecursiveModelChecking.entails(KnowledgeBases.doorsOfEnlightenmentKnowledgeBase(), z));
        Assert.assertFalse(RecursiveModelChecking.entails(KnowledgeBases.doorsOfEnlightenmentKnowledgeBase(), w));
    }
}
