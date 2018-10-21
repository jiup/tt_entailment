import domain.propositional.ComplexSentence;
import domain.propositional.Sentence;
import knowledgebase.PLKnowledgeBase;
import org.junit.Assert;
import org.junit.Test;
import util.propositional.SentenceUtil;

/**
 * @author Yu Zhang
 * @since 10/20/2018
 */
public class CNFConversionTest {
    @Test
    public void testExampleKnowledgeBase() {
        PLKnowledgeBase kb = KnowledgeBases.exampleKnowledgeBase(true);
        PLKnowledgeBase kb0 = KnowledgeBases.exampleKnowledgeBase(false);
        Sentence sentence = ComplexSentence.AND(kb0.list().toArray(new Sentence[0]));
        Assert.assertEquals(kb.list(), SentenceUtil.convertToCNF(sentence));
    }

    @Test
    public void testModusPonensKnowledgeBase() {
        PLKnowledgeBase kb = KnowledgeBases.modusPonensKnowledgeBase(true);
        PLKnowledgeBase kb0 = KnowledgeBases.modusPonensKnowledgeBase(false);
        Sentence sentence = ComplexSentence.AND(kb0.list().toArray(new Sentence[0]));
        Assert.assertEquals(kb.list(), SentenceUtil.convertToCNF(sentence));
    }

    @Test
    public void testWumpusWorldKnowledgeBase() {
        PLKnowledgeBase kb = KnowledgeBases.wumpusWorldKnowledgeBase(true);
        PLKnowledgeBase kb0 = KnowledgeBases.wumpusWorldKnowledgeBase(false);
        Sentence sentence = ComplexSentence.AND(kb0.list().toArray(new Sentence[0]));
        Assert.assertEquals(kb.list(), SentenceUtil.convertToCNF(sentence));
    }

    @Test
    public void testHornClausesKnowledgeBase() {
        PLKnowledgeBase kb = KnowledgeBases.hornClausesKnowledgeBase(true);
        PLKnowledgeBase kb0 = KnowledgeBases.hornClausesKnowledgeBase(false);
        Sentence sentence = ComplexSentence.AND(kb0.list().toArray(new Sentence[0]));
        Assert.assertEquals(kb.list(), SentenceUtil.convertToCNF(sentence));
    }

    @Test
    public void testLiarsAndTruthTellers1KnowledgeBase() {
        PLKnowledgeBase kb = KnowledgeBases.liarsAndTruthTellers1KnowledgeBase(true);
        PLKnowledgeBase kb0 = KnowledgeBases.liarsAndTruthTellers1KnowledgeBase(false);
        Sentence sentence = ComplexSentence.AND(kb0.list().toArray(new Sentence[0]));
        Assert.assertEquals(kb.list(), SentenceUtil.convertToCNF(sentence));
    }

    @Test
    public void testLiarsAndTruthTellers2KnowledgeBase() {
        PLKnowledgeBase kb = KnowledgeBases.liarsAndTruthTellers2KnowledgeBase(true);
        PLKnowledgeBase kb0 = KnowledgeBases.liarsAndTruthTellers2KnowledgeBase(false);
        Sentence sentence = ComplexSentence.AND(kb0.list().toArray(new Sentence[0]));
        Assert.assertEquals(kb.list(), SentenceUtil.convertToCNF(sentence));
    }

    @Test
    public void testLiarsAndTruthTellers3KnowledgeBase() {
        PLKnowledgeBase kb = KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(true);
        PLKnowledgeBase kb0 = KnowledgeBases.liarsAndTruthTellers3KnowledgeBase(false);
        Sentence sentence = ComplexSentence.AND(kb0.list().toArray(new Sentence[0]));
        Assert.assertEquals(kb.list(), SentenceUtil.convertToCNF(sentence));
    }

    @Test
    public void testDoorsOfEnlightenmentKnowledgeBase() { // TODO
        PLKnowledgeBase kb = KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(true);
        PLKnowledgeBase kb0 = KnowledgeBases.doorsOfEnlightenment1KnowledgeBase(false);
        Sentence sentence = ComplexSentence.AND(kb0.list().toArray(new Sentence[0]));
        Assert.assertEquals(kb.list(), SentenceUtil.convertToCNF(sentence));
    }
}