package knowledgebase;

import domain.propositional.Sentence;

/**
 * @author Jiupeng Zhang
 * @since 10/11/2018
 */
public class PLKnowledgeBase extends knowledgebase.KnowledgeBase<Sentence> {
    @Override
    public boolean insert(Sentence sentence) {
        return sentences.add(sentence);
    }

    @Override
    public boolean remove(Sentence sentence) {
        return sentences.remove(sentence);
    }

    @Override
    public boolean ask(Sentence querySentence) {
        // TODO
        return false;
    }
}
