package domain.propositional;

import java.util.Set;

/**
 * Created by zhanglu on 10/18/18.
 */
public interface Negatable {
    Set<Sentence> negate(ComplexSentence DNF);
}
