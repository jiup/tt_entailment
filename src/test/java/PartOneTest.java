import domain.propositional.AtomicSentence;
import knowledgebase.PLKnowledgeBase;
import org.junit.Assert;
import org.junit.Test;

import static domain.propositional.ComplexSentence.*;
import static knowledgebase.PLAlgorithms.Entailment.ModelChecking;

/**
 * @author Jiupeng Zhang
 * @since 10/11/2018
 */
public class PartOneTest {
    @Test
    public void testExample() {
        AtomicSentence w12 = new AtomicSentence("W12");
        AtomicSentence w21 = new AtomicSentence("W21");
        Assert.assertTrue(ModelChecking.entails(exampleKnowledgeBase(), NOT(w12), NOT(w21)));
    }

    @Test
    public void testModusPonens() {
        AtomicSentence q = new AtomicSentence("Q");
        Assert.assertTrue(ModelChecking.entails(modusPonensKnowledgeBase(), q));
    }

    @Test
    public void testSimpleWumpusWorld() {
        AtomicSentence p12 = new AtomicSentence("P12");
        Assert.assertTrue(ModelChecking.entails(wumpusWorldKnowledgeBase(), NOT(p12)));
    }

    @Test
    public void testHornClauses() {
        AtomicSentence mythical = new AtomicSentence("mythical");
        AtomicSentence magical = new AtomicSentence("magical");
        AtomicSentence horned = new AtomicSentence("horned");
        Assert.assertFalse(ModelChecking.entails(hornClausesKnowledgeBase(), mythical));
        Assert.assertTrue(ModelChecking.entails(hornClausesKnowledgeBase(), magical));
        Assert.assertTrue(ModelChecking.entails(hornClausesKnowledgeBase(), horned));
    }

    @Test
    public void testLiarsAndTruthTellers() {
        AtomicSentence amy = new AtomicSentence("Amy");
        AtomicSentence bob = new AtomicSentence("Bob");
        AtomicSentence cal = new AtomicSentence("Cal");
        Assert.assertTrue(ModelChecking.entails(liarsAndTruthTellers1KnowledgeBase(), AND(NOT(amy), NOT(bob), cal)));
        Assert.assertTrue(ModelChecking.entails(liarsAndTruthTellers2KnowledgeBase(), AND(amy, NOT(bob), NOT(cal))));
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
        Assert.assertTrue(ModelChecking.entails(liarsAndTruthTellers3KnowledgeBase(), AND(
                NOT(Amy), NOT(Bob), NOT(Cal), NOT(Dee), NOT(Eli), NOT(Fay), NOT(Gil),
                NOT(Hal), NOT(Ida), NOT(Gil), NOT(Hal), NOT(Ida), Jay, Kay, NOT(Lee)
        )));
    }

    @Test
    public void testTheDoorsOfEnlightenment() {
        AtomicSentence x = new AtomicSentence("X");
        AtomicSentence y = new AtomicSentence("Y");
        AtomicSentence z = new AtomicSentence("Z");
        AtomicSentence w = new AtomicSentence("W");
        Assert.assertTrue(ModelChecking.entails(doorsOfEnlightenmentKnowledgeBase(), x));
        Assert.assertFalse(ModelChecking.entails(doorsOfEnlightenmentKnowledgeBase(), y));
        Assert.assertFalse(ModelChecking.entails(doorsOfEnlightenmentKnowledgeBase(), z));
        Assert.assertFalse(ModelChecking.entails(doorsOfEnlightenmentKnowledgeBase(), w));
    }

    private PLKnowledgeBase exampleKnowledgeBase() {
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

    private static PLKnowledgeBase liarsAndTruthTellers1KnowledgeBase() {
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
