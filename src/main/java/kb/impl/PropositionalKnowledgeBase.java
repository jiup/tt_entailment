package kb.impl;

import domain.PropositionalSentence;
import kb.KnowledgeBase;

/**
 * @author Jiupeng Zhang
 * @since 10/11/2018
 */
public class PropositionalKnowledgeBase extends KnowledgeBase<PropositionalSentence> {
    @Override
    protected boolean insert(PropositionalSentence sentence) {
        return false;
    }

    @Override
    protected boolean remove(PropositionalSentence sentence) {
        return false;
    }

    @Override
    protected boolean ask(PropositionalSentence querySentence) {
        return false;
    }
}
