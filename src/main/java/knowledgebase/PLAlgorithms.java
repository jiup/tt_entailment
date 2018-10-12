package knowledgebase;

import domain.propositional.Sentence;

/**
 * @author Jiupeng Zhang
 * @since 10/12/2018
 */
public class PLAlgorithms {
    public enum Entailment implements EntailCheckStrategies {
        TruthTableChecking {
            @Override
            public boolean entails(PLKnowledgeBase kb, Sentence... sentence) {
                return false;
            }
        }
    }

    interface EntailCheckStrategies {
        boolean entails(PLKnowledgeBase kb, Sentence... sentence);
    }
}
