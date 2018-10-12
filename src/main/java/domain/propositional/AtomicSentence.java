package domain.propositional;

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
    public String toString() {
        return value;
    }
}
