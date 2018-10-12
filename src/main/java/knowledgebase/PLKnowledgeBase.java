package knowledgebase;

import domain.propositional.Sentence;

/**
 * @author Jiupeng Zhang
 * @since 10/11/2018
 */
public class PLKnowledgeBase extends knowledgebase.KnowledgeBase<Sentence> {
    @Override
    public boolean insert(Sentence sentence) {
        return false;
    }

    @Override
    public boolean remove(Sentence sentence) {
        return false;
    }

    @Override
    public boolean ask(Sentence querySentence) {
        return false;
    }

    public boolean ttEntails(Sentence sentence) {
        return false;
    }
}
