import domain.propositional.AtomicSentence;
import knowledgebase.PLKnowledgeBase;

import static domain.propositional.ComplexSentence.*;

/**
 * @author Jiupeng Zhang
 * @since 10/18/2018
 */
public class KnowledgeBases {
    public static PLKnowledgeBase exampleKnowledgeBase() {
        return exampleKnowledgeBase(false);
    }

    public static PLKnowledgeBase modusPonensKnowledgeBase() {
        return modusPonensKnowledgeBase(false);
    }

    public static PLKnowledgeBase wumpusWorldKnowledgeBase() {
        return wumpusWorldKnowledgeBase(false);
    }

    public static PLKnowledgeBase hornClausesKnowledgeBase() {
        return hornClausesKnowledgeBase(false);
    }

    public static PLKnowledgeBase liarsAndTruthTellers1KnowledgeBase() {
        return liarsAndTruthTellers1KnowledgeBase(false);
    }

    public static PLKnowledgeBase liarsAndTruthTellers2KnowledgeBase() {
        return liarsAndTruthTellers2KnowledgeBase(false);
    }

    public static PLKnowledgeBase liarsAndTruthTellers3KnowledgeBase() {
        return liarsAndTruthTellers3KnowledgeBase(false);
    }

    public static PLKnowledgeBase doorsOfEnlightenmentKnowledgeBase() {
        return doorsOfEnlightenmentKnowledgeBase(false);
    }

    public static PLKnowledgeBase exampleKnowledgeBase(boolean CNF) {
        PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
        AtomicSentence at11 = new AtomicSentence("At11");
        AtomicSentence s11 = new AtomicSentence("S11");
        AtomicSentence w21 = new AtomicSentence("W21");
        AtomicSentence w12 = new AtomicSentence("W12");
        if (CNF) {
            knowledgeBase.insert(at11);
            knowledgeBase.insert(NOT(s11));
            knowledgeBase.insert(OR(NOT(w21), s11));
            knowledgeBase.insert(OR(NOT(w12), s11));
            knowledgeBase.insert(OR(NOT(s11), w21, w12));
        } else {
            knowledgeBase.insert(at11);
            knowledgeBase.insert(NOT(s11));
            knowledgeBase.insert(BI_IMPLIES(s11, OR(w21, w12)));
        }
        return knowledgeBase;
    }

    public static PLKnowledgeBase modusPonensKnowledgeBase(boolean CNF) {
        PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
        AtomicSentence p = new AtomicSentence("P");
        AtomicSentence q = new AtomicSentence("Q");
        if (CNF) {
            knowledgeBase.insert(p);
            knowledgeBase.insert(OR(NOT(p), q));
        } else {
            knowledgeBase.insert(p);
            knowledgeBase.insert(IMPLIES(p, q));
        }
        return knowledgeBase;
    }

    public static PLKnowledgeBase wumpusWorldKnowledgeBase(boolean CNF) {
        PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
        AtomicSentence p11 = new AtomicSentence("P11");
        AtomicSentence p21 = new AtomicSentence("P21");
        AtomicSentence p22 = new AtomicSentence("P22");
        AtomicSentence p31 = new AtomicSentence("P31");
        AtomicSentence b11 = new AtomicSentence("B11");
        AtomicSentence b21 = new AtomicSentence("B21");
        AtomicSentence p12 = new AtomicSentence("P12");
        if (CNF) {
            knowledgeBase.insert(NOT(p11));
            knowledgeBase.insert(OR(NOT(p12), b11));
            knowledgeBase.insert(OR(NOT(p21), b11));
            knowledgeBase.insert(OR(NOT(b11), p12, p21));
            knowledgeBase.insert(OR(NOT(p11), b21));
            knowledgeBase.insert(OR(NOT(p22), b21));
            knowledgeBase.insert(OR(NOT(p31), b21));
            knowledgeBase.insert(OR(NOT(b21), p11, p22, p31));
            knowledgeBase.insert(NOT(b11));
            knowledgeBase.insert(b21);
        } else {
            knowledgeBase.insert(NOT(p11));
            knowledgeBase.insert(BI_IMPLIES(b11, OR(p12, p21)));
            knowledgeBase.insert(BI_IMPLIES(b21, OR(p11, p22, p31)));
            knowledgeBase.insert(NOT(b11));
            knowledgeBase.insert(b21);
        }
        return knowledgeBase;
    }

    public static PLKnowledgeBase hornClausesKnowledgeBase(boolean CNF) {
        PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
        AtomicSentence mythical = new AtomicSentence("mythical");
        AtomicSentence immortal = new AtomicSentence("immortal");
        AtomicSentence mammal = new AtomicSentence("mammal");
        AtomicSentence horned = new AtomicSentence("horned");
        AtomicSentence magical = new AtomicSentence("magical");
        if (CNF) {
            knowledgeBase.insert(OR(NOT(mythical), immortal));
            knowledgeBase.insert(OR(mythical, NOT(immortal)));
            knowledgeBase.insert(OR(mythical, mammal));
            knowledgeBase.insert(OR(NOT(immortal), horned));
            knowledgeBase.insert(OR(NOT(mammal), horned));
            knowledgeBase.insert(OR(NOT(horned), magical));
        } else {
            knowledgeBase.insert(IMPLIES(mythical, immortal));
            knowledgeBase.insert(IMPLIES(NOT(mythical), AND(NOT(immortal), mammal)));
            knowledgeBase.insert(IMPLIES(OR(immortal, mammal), horned));
            knowledgeBase.insert(IMPLIES(horned, magical));
        }
        return knowledgeBase;
    }

    public static PLKnowledgeBase liarsAndTruthTellers1KnowledgeBase(boolean CNF) {
        PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
        AtomicSentence Amy = new AtomicSentence("Amy");
        AtomicSentence Bob = new AtomicSentence("Bob");
        AtomicSentence Cal = new AtomicSentence("Cal");
        if (CNF) {
            knowledgeBase.insert(OR(Cal, Bob));
            knowledgeBase.insert(OR(NOT(Cal), Amy, NOT(Amy)));
            knowledgeBase.insert(OR(NOT(Bob), NOT(Cal)));
            knowledgeBase.insert(OR(Cal, NOT(Amy)));
            knowledgeBase.insert(OR(Amy, NOT(Amy)));
            knowledgeBase.insert(OR(Bob, NOT(Cal), NOT(Amy)));
            knowledgeBase.insert(OR(Cal, Amy));
            knowledgeBase.insert(OR(NOT(Bob), Cal));
        } else {
            knowledgeBase.insert(BI_IMPLIES(Amy, AND(Amy, Cal)));
            knowledgeBase.insert(BI_IMPLIES(Bob, NOT(Cal)));
            knowledgeBase.insert(BI_IMPLIES(Cal, OR(Bob, NOT(Amy))));
        }
        return knowledgeBase;
    }

    public static PLKnowledgeBase liarsAndTruthTellers2KnowledgeBase(boolean CNF) {
        PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
        AtomicSentence Amy = new AtomicSentence("Amy");
        AtomicSentence Bob = new AtomicSentence("Bob");
        AtomicSentence Cal = new AtomicSentence("Cal");
        if (CNF) {
            knowledgeBase.insert(OR(NOT(Amy), NOT(Cal)));
            knowledgeBase.insert(OR(Cal, Amy));
            knowledgeBase.insert(OR(NOT(Bob), Amy));
            knowledgeBase.insert(OR(NOT(Bob), Cal));
            knowledgeBase.insert(OR(NOT(Amy), NOT(Cal), Bob));
            knowledgeBase.insert(OR(NOT(Cal), Bob));
            knowledgeBase.insert(OR(Cal, NOT(Bob)));
        } else {
            knowledgeBase.insert(BI_IMPLIES(Amy, NOT(Cal)));
            knowledgeBase.insert(BI_IMPLIES(Bob, AND(Amy, Cal)));
            knowledgeBase.insert(BI_IMPLIES(Cal, Bob));
        }
        return knowledgeBase;
    }

    public static PLKnowledgeBase liarsAndTruthTellers3KnowledgeBase(boolean CNF) {
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
        if (CNF) {
            knowledgeBase.insert(OR(NOT(Amy), Hal));
            knowledgeBase.insert(OR(NOT(Amy), Ida));
            knowledgeBase.insert(OR(NOT(Hal), NOT(Ida), Amy));
            knowledgeBase.insert(OR(NOT(Bob), Amy));
            knowledgeBase.insert(OR(NOT(Bob), Lee));
            knowledgeBase.insert(OR(NOT(Amy), NOT(Lee), Bob));
            knowledgeBase.insert(OR(NOT(Cal), Bob));
            knowledgeBase.insert(OR(NOT(Cal), Gil));
            knowledgeBase.insert(OR(NOT(Bob), NOT(Gil), Cal));
            knowledgeBase.insert(OR(NOT(Dee), Eli));
            knowledgeBase.insert(OR(NOT(Dee), Lee));
            knowledgeBase.insert(OR(NOT(Eli), NOT(Lee), Dee));
            knowledgeBase.insert(OR(NOT(Eli), Cal));
            knowledgeBase.insert(OR(NOT(Eli), Hal));
            knowledgeBase.insert(OR(NOT(Cal), NOT(Hal), Eli));
            knowledgeBase.insert(OR(NOT(Fay), Dee));
            knowledgeBase.insert(OR(NOT(Fay), Ida));
            knowledgeBase.insert(OR(NOT(Dee), NOT(Ida), Fay));
            knowledgeBase.insert(OR(NOT(Gil), NOT(Eli)));
            knowledgeBase.insert(OR(NOT(Gil), NOT(Jay)));
            knowledgeBase.insert(OR(Eli, Jay, Gil));
            knowledgeBase.insert(OR(NOT(Hal), NOT(Fay)));
            knowledgeBase.insert(OR(NOT(Hal), NOT(Kay)));
            knowledgeBase.insert(OR(Fay, Kay, Hal));
            knowledgeBase.insert(OR(NOT(Ida), NOT(Gil)));
            knowledgeBase.insert(OR(NOT(Ida), NOT(Kay)));
            knowledgeBase.insert(OR(Gil, Kay, Ida));
            knowledgeBase.insert(OR(NOT(Jay), NOT(Amy)));
            knowledgeBase.insert(OR(NOT(Jay), NOT(Cal)));
            knowledgeBase.insert(OR(Amy, Cal, Jay));
            knowledgeBase.insert(OR(NOT(Kay), NOT(Dee)));
            knowledgeBase.insert(OR(NOT(Kay), NOT(Fay)));
            knowledgeBase.insert(OR(Dee, Fay, Kay));
            knowledgeBase.insert(OR(NOT(Lee), NOT(Bob)));
            knowledgeBase.insert(OR(NOT(Lee), NOT(Jay)));
            knowledgeBase.insert(OR(Bob, Jay, Lee));
        } else {
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
        }
        return knowledgeBase;
    }

    public static PLKnowledgeBase doorsOfEnlightenmentKnowledgeBase(boolean CNF) {
        if (CNF) {
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
            knowledgeBase.insert(OR(x, y, z, w));
            knowledgeBase.insert(OR(a, NOT(c)));
            knowledgeBase.insert(OR(b, NOT(c)));
            knowledgeBase.insert(OR(NOT(a), h));
            knowledgeBase.insert(OR(d, NOT(e)));
            knowledgeBase.insert(OR(NOT(f), g));
            knowledgeBase.insert(OR(y, z, NOT(b)));
            knowledgeBase.insert(d);
            knowledgeBase.insert(OR(NOT(y), b));
            knowledgeBase.insert(OR(NOT(g), f, NOT(c)));
            knowledgeBase.insert(OR(a, NOT(g), NOT(h)));
            knowledgeBase.insert(OR(NOT(z), b));
            knowledgeBase.insert(OR(x, NOT(d)));
            knowledgeBase.insert(OR(NOT(e), NOT(f), e));
            knowledgeBase.insert(OR(NOT(a), x));
            knowledgeBase.insert(OR(NOT(x), a));
            knowledgeBase.insert(h);
            knowledgeBase.insert(OR(NOT(f), d, NOT(d)));
            knowledgeBase.insert(OR(z, NOT(e)));
            knowledgeBase.insert(OR(y, NOT(d)));
            knowledgeBase.insert(OR(x, NOT(e)));
            knowledgeBase.insert(OR(c, NOT(a), NOT(b)));
            knowledgeBase.insert(NOT(e));
            knowledgeBase.insert(OR(NOT(e), NOT(f), NOT(d)));
            knowledgeBase.insert(OR(NOT(x), d, NOT(y)));
            knowledgeBase.insert(OR(c, g));
            knowledgeBase.insert(OR(e, f, NOT(d)));
            knowledgeBase.insert(OR(NOT(e), d, f));
            knowledgeBase.insert(OR(NOT(f), d, e));
            knowledgeBase.insert(OR(g, h));
            knowledgeBase.insert(OR(NOT(x), e, NOT(z)));
            return knowledgeBase;
        } else {
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
            PLKnowledgeBase knowledgeBase = new PLKnowledgeBase();
            knowledgeBase.insert(BI_IMPLIES(a, x));
            knowledgeBase.insert(BI_IMPLIES(b, OR(y, z)));
            knowledgeBase.insert(BI_IMPLIES(c, AND(a, b)));
            knowledgeBase.insert(BI_IMPLIES(d, AND(x, y)));
            knowledgeBase.insert(BI_IMPLIES(e, AND(x, z)));
            knowledgeBase.insert(BI_IMPLIES(f, OR(AND(d, NOT(e)), AND(NOT(d), e))));
            knowledgeBase.insert(BI_IMPLIES(g, IMPLIES(c, f)));
            knowledgeBase.insert(BI_IMPLIES(h, IMPLIES(AND(g, h), a)));
            knowledgeBase.insert(OR(x, y, z, w));
            return knowledgeBase;
        }
    }
}
