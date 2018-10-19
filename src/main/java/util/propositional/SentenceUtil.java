package util.propositional;

import domain.propositional.AtomicSentence;
import domain.propositional.ComplexSentence;
import domain.propositional.Sentence;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Jiupeng Zhang
 * @since 10/19/2018
 */
public class SentenceUtil {
    public static Set<AtomicSentence> getSymbols(Sentence... sentences) {
        Set<AtomicSentence> symbols = new LinkedHashSet<>();
        for (Sentence s : sentences) {
            if (s instanceof AtomicSentence) {
                symbols.add((AtomicSentence) s);
            } else if (s instanceof ComplexSentence) {
                symbols.addAll(getSymbols((((ComplexSentence) s).getClauses().toArray(new Sentence[0]))));
            }
        }
        return symbols;
    }

    public static Set<Sentence> convert2DNF(Sentence sentence) {
        return null;
    }
}
