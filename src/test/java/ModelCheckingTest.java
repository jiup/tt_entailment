import domain.propositional.AtomicSentence;
import org.junit.Assert;
import org.junit.Test;

import static domain.propositional.ComplexSentence.AND;
import static domain.propositional.ComplexSentence.NOT;
import static knowledgebase.PLAlgorithms.Entailment.ModelChecking;

/**
 * @author Jiupeng Zhang
 * @since 10/11/2018
 */
public class ModelCheckingTest {
    @Test
    public void testExample() {
        AtomicSentence w12 = new AtomicSentence("W12");
        AtomicSentence w21 = new AtomicSentence("W21");
        Assert.assertTrue(ModelChecking.entails(KnowledgeBases.exampleKnowledgeBase(), NOT(w12), NOT(w21)));
    }

    @Test
    public void testModusPonens() {
        AtomicSentence q = new AtomicSentence("Q");
        Assert.assertTrue(ModelChecking.entails(KnowledgeBases.modusPonensKnowledgeBase(), q));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.modusPonensKnowledgeBase(), NOT(q)));
    }

    @Test
    public void testSimpleWumpusWorld() {
        AtomicSentence p12 = new AtomicSentence("P12");
        Assert.assertTrue(ModelChecking.entails(KnowledgeBases.wumpusWorldKnowledgeBase(), NOT(p12)));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.wumpusWorldKnowledgeBase(), p12));
    }

    @Test
    public void testHornClauses() {
        AtomicSentence mythical = new AtomicSentence("mythical");
        AtomicSentence magical = new AtomicSentence("magical");
        AtomicSentence horned = new AtomicSentence("horned");
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.hornClausesKnowledgeBase(), mythical));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.hornClausesKnowledgeBase(), NOT(mythical)));
        Assert.assertTrue(ModelChecking.entails(KnowledgeBases.hornClausesKnowledgeBase(), magical));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.hornClausesKnowledgeBase(), NOT(magical)));
        Assert.assertTrue(ModelChecking.entails(KnowledgeBases.hornClausesKnowledgeBase(), horned));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.hornClausesKnowledgeBase(), NOT(horned)));
    }

    @Test
    public void testLiarsAndTruthTellers() {
        AtomicSentence amy = new AtomicSentence("Amy");
        AtomicSentence bob = new AtomicSentence("Bob");
        AtomicSentence cal = new AtomicSentence("Cal");
        Assert.assertTrue(ModelChecking.entails(KnowledgeBases.liarsAndTruthTellers1KnowledgeBase(), AND(NOT(amy), NOT(bob), cal)));
        Assert.assertTrue(ModelChecking.entails(KnowledgeBases.liarsAndTruthTellers2KnowledgeBase(), AND(amy, NOT(bob), NOT(cal))));

        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.liarsAndTruthTellers1KnowledgeBase(), NOT(AND(NOT(amy), NOT(bob), cal))));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.liarsAndTruthTellers2KnowledgeBase(), NOT(AND(amy, NOT(bob), NOT(cal)))));
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
        Assert.assertTrue(ModelChecking.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), AND(
                NOT(Amy), NOT(Bob), NOT(Cal), NOT(Dee), NOT(Eli), NOT(Fay), NOT(Gil), NOT(Hal), NOT(Ida), Jay, Kay, NOT(Lee)
        )));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(), NOT(AND(
                NOT(Amy), NOT(Bob), NOT(Cal), NOT(Dee), NOT(Eli), NOT(Fay), NOT(Gil), NOT(Hal), NOT(Ida), Jay, Kay, NOT(Lee)
        ))));
    }

    @Test
    public void testTheDoorsOfEnlightenment() {
        AtomicSentence x = new AtomicSentence("X");
        AtomicSentence y = new AtomicSentence("Y");
        AtomicSentence z = new AtomicSentence("Z");
        AtomicSentence w = new AtomicSentence("W");

        Assert.assertTrue(ModelChecking.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), x));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), NOT(x)));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), y));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), NOT(y)));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), z));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), NOT(z)));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), w));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(), NOT(w)));

        Assert.assertTrue(ModelChecking.entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), x));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), NOT(x)));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), y));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), NOT(y)));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), z));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), NOT(z)));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), w));
        Assert.assertFalse(ModelChecking.entails(KnowledgeBases.doorsOfEnlightenment2KnowledgeBase(), NOT(w)));
    }
}
