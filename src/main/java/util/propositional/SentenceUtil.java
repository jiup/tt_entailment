package util.propositional;

import domain.propositional.AtomicSentence;
import domain.propositional.ComplexSentence;
import domain.propositional.Connective;
import domain.propositional.Sentence;

import java.util.*;

import static domain.propositional.ComplexSentence.*;

/**
 * @author Yu Zhang, Jiupeng Zhang
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

    private static Set<Sentence> convertToCNF(Set<Sentence> beforeConvert, Set<Sentence> afterConvert) {
        if (beforeConvert.isEmpty()) {
            return afterConvert;
        }
        Set<Sentence> tmpBefore = new HashSet<>(beforeConvert);
        for (Sentence s : tmpBefore) {
            if (s instanceof ComplexSentence) {
                ComplexSentence complexSentence = (ComplexSentence) s;
                Sentence[] clauses = complexSentence.getClauses().toArray(new Sentence[0]);
                switch (complexSentence.getConnective()) {
                    case AND:
                        for (Sentence clause : clauses) {
                            boolean flag = true;
                            if (clause instanceof ComplexSentence) {
                                ComplexSentence compClause = (ComplexSentence) clause;
                                if (!compClause.getConnective().equals(Connective.NOT)) {
                                    beforeConvert.add(clause);
                                    flag = false;
                                }
                            }
                            if (flag) afterConvert.add(clause);
                        }
                        beforeConvert.remove(s);
                        break;
                    case OR:
                        boolean allAtomic = true;
                        int andMark = -1;
                        List<Integer> orMarkIndex = new ArrayList<>();
                        Set<Sentence> orSentences = new HashSet<>();
                        Set<Sentence> andSentence = new HashSet<>();
                        for (int i = 0; i < clauses.length; i++) {
                            if (clauses[i] instanceof ComplexSentence) {
                                ComplexSentence subSentence = (ComplexSentence) clauses[i];
                                Sentence[] subClauses;
                                if (subSentence.getConnective().equals(Connective.OR)) {
                                    subClauses = subSentence.getClauses().toArray(new Sentence[0]);
                                    orSentences.addAll(Arrays.asList(subClauses));
                                    orMarkIndex.add(i);
                                } else if (subSentence.getConnective().equals(Connective.AND)) {
                                    allAtomic = false;
                                    andMark = i;
                                    subClauses = subSentence.getClauses().toArray(new Sentence[0]);
                                    for (Sentence subClause : subClauses) {
                                        if (!subClause.equals(clauses[clauses.length - 1 - i])) {
                                            andSentence.add(OR(subClause, clauses[clauses.length - 1 - i]));
                                        } else {
                                            andSentence.add(subClause);
                                        }
                                    }
                                }
                            }
                        }
                        if (!allAtomic) {
                            Sentence andOrSentence = AND(andSentence.toArray(new Sentence[0]));
                            Set<Sentence> orAndSentence = new HashSet<>();
                            beforeConvert.remove(s);
                            if (clauses.length == 2) {
                                beforeConvert.add(andOrSentence);
                            } else {
                                for (int i = 0; i < clauses.length; i++) {
                                    if (!(i == andMark) && !(i == clauses.length - 1 - andMark)) {
                                        orAndSentence.add(clauses[i]);
                                    }
                                }
                                orAndSentence.add(andOrSentence);
                                Sentence newSentence = OR(orAndSentence.toArray(new Sentence[0]));
                                beforeConvert.add(newSentence);
                            }
                        } else {
                            if (orMarkIndex.isEmpty()) {
                                afterConvert.add(s);
                            } else {
                                for (int i = 0; i < clauses.length; i++) {
                                    if (!orMarkIndex.contains(i)) {
                                        orSentences.add(clauses[i]);
                                    }
                                }
                                beforeConvert.add(OR(orSentences.toArray(new Sentence[0])));
                            }
                            beforeConvert.remove(s);
                        }
                }
            } else {
                beforeConvert.remove(s);
                afterConvert.add(s);
            }
        }
        return convertToCNF(beforeConvert, afterConvert);
    }

    private static Sentence eliminateBIImplies(Sentence s) {
        if (s instanceof AtomicSentence) {
            return s;
        } else {
            ComplexSentence complexSentence = (ComplexSentence) s;
            Sentence[] clauses = complexSentence.getClauses().toArray(new Sentence[0]);
            for (int i = 0; i < clauses.length; i++) {
                clauses[i] = eliminateBIImplies(clauses[i]);
            }
            if (complexSentence.getConnective().equals(Connective.BI_IMPLICATION)) {
                return ComplexSentence.AND(IMPLIES(clauses[0], clauses[1]), IMPLIES(clauses[1], clauses[0]));
            }
            return new ComplexSentence(((ComplexSentence) s).getConnective(), clauses);
        }
    }

    private static Sentence eliminateImplies(Sentence s) {
        if (s instanceof AtomicSentence) {
            return s;
        } else {
            ComplexSentence complexSentence = (ComplexSentence) s;
            Sentence[] clauses = complexSentence.getClauses().toArray(new Sentence[0]);
            for (int i = 0; i < clauses.length; i++) {
                clauses[i] = eliminateImplies(clauses[i]);
            }
            if (complexSentence.getConnective().equals(Connective.IMPLICATION)) {
                return ComplexSentence.OR(NOT(clauses[0]), clauses[1]);
            }
            return new ComplexSentence(((ComplexSentence) s).getConnective(), clauses);
        }
    }

    private static Sentence eliminateNot(Sentence s) {
        if (s instanceof AtomicSentence) {
            return s;
        } else {
            ComplexSentence complexSentence = (ComplexSentence) s;
            Sentence[] clauses = complexSentence.getClauses().toArray(new Sentence[0]);
            for (int i = 0; i < clauses.length; i++) {
                clauses[i] = eliminateNot(clauses[i]);
            }
            if (complexSentence.getConnective().equals(Connective.NOT)) {
                if (clauses[0] instanceof ComplexSentence) {
                    ComplexSentence subSentence = (ComplexSentence) clauses[0];
                    Sentence[] subClauses = subSentence.getClauses().toArray(new Sentence[0]);
                    ArrayList<Sentence> orClauses = new ArrayList<>();
                    for (Sentence tmp : subClauses) {
                        orClauses.add(NOT(tmp));
                    }
                    if (subSentence.getConnective().equals(Connective.AND)) {
                        return eliminateNot(OR(orClauses.toArray(new Sentence[0])));
                    } else if (subSentence.getConnective().equals(Connective.OR)) {
                        return eliminateNot(AND(orClauses.toArray(new Sentence[0])));
                    }
                }
            }
            return new ComplexSentence(((ComplexSentence) s).getConnective(), clauses);
        }
    }

    public static Set<Sentence> convertToCNF(Sentence sentence) {
        Sentence result = eliminateNot(eliminateImplies(eliminateBIImplies(sentence)));
        Set<Sentence> beforeConvert = new HashSet<>();
        beforeConvert.add(result);
        Set<Sentence> afterConvert = new HashSet<>();
        return convertToCNF(beforeConvert, afterConvert);
    }
}
