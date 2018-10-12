package kb;

import domain.Sentence;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jiupeng Zhang
 * @since 10/11/2018
 */
public abstract class KnowledgeBase<T extends Sentence> {
    protected Set<T> sentences = new HashSet<>();

    protected abstract boolean insert(T sentence);

    protected abstract boolean remove(T sentence);

    protected abstract boolean ask(T querySentence);

    public int size() {
        return sentences.size();
    }
}
