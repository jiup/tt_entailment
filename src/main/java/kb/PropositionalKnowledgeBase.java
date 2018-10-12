package kb;

import domain.Sentence;

/**
 * @author Jiupeng Zhang
 * @since 10/11/2018
 */
public class PropositionalKnowledgeBase extends kb.KnowledgeBase<Sentence> {
    @Override
    protected boolean insert(Sentence sentence) {
        return false;
    }

    @Override
    protected boolean remove(Sentence sentence) {
        return false;
    }

    @Override
    protected boolean ask(Sentence querySentence) {
        return false;
    }
}
