package domain.propositional;

import java.util.Objects;

/**
 * @author Jiupeng Zhang
 * @since 10/12/2018
 */
public class AtomicSentence extends Sentence {
    public static final AtomicSentence TRUE = new AtomicSentence("true");
    public static final AtomicSentence FALSE = new AtomicSentence("false");

    protected String value;

    public AtomicSentence(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AtomicSentence that = (AtomicSentence) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
