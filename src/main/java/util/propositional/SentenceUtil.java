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

    @SuppressWarnings("ConstantConditions")
    private static Set<Sentence> convertToCNF(Set<Sentence> beforeConvert, Set<Sentence> afterConvert) {
        if (beforeConvert.isEmpty()) {
            return afterConvert;
        }
        Set<Sentence> tmpBefore = new HashSet<>(beforeConvert);
        for (Sentence s : tmpBefore) {
            boolean isNegateSentence = true;
            if (s instanceof ComplexSentence) {
                ComplexSentence complexSentence = (ComplexSentence) s;
                if (!complexSentence.getConnective().equals(Connective.NOT)) {
                    isNegateSentence = false;
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
                            int andMark2 = -1;
                            int isNegateClauses = 0;
                            List<Integer> orMarkIndex = new ArrayList<>();
                            Map<Integer, Set<Sentence>> orSentences = new HashMap<>();
                            Set<Sentence> andSentence = new HashSet<>();
                            for (int i = 0; i < clauses.length; i++) {
                                if (clauses[i] instanceof ComplexSentence) {
                                    ComplexSentence subSentence = (ComplexSentence) clauses[i];
                                    Set<Sentence> subClauses;
                                    if (subSentence.getConnective().equals(Connective.OR)) {
                                        subClauses = subSentence.getClauses();
                                        orSentences.put(i, subClauses);
                                        orMarkIndex.add(i);
                                    } else if (subSentence.getConnective().equals(Connective.AND) && allAtomic) {
                                        allAtomic = false;
                                        andMark = i;
                                        subClauses = subSentence.getClauses();
                                        for (Sentence subClause : subClauses) {
                                            if (i < clauses.length - 1) {
                                                andMark2 = i + 1;
                                                if (!subClause.equals(clauses[i + 1])) {
                                                    andSentence.add(OR(subClause, clauses[i + 1]));
                                                } else {
                                                    andSentence.add(clauses[i + 1]);
                                                }
                                            } else {
                                                andMark2 = i - 1;
                                                if (!subClause.equals(clauses[i - 1])) {
                                                    andSentence.add(OR(subClause, clauses[i - 1]));
                                                } else {
                                                    andSentence.add(clauses[i - 1]);
                                                }
                                            }

                                        }
                                    } else {
                                        isNegateClauses += 1;
                                    }
                                } else {
                                    isNegateClauses += 1;
                                }
                            }

                            Set<Sentence> toAdd = new HashSet<>();
                            if (!orMarkIndex.isEmpty()) {
                                for (int i = 0; i < clauses.length; i++) {
                                    if (orMarkIndex.contains(i) && !(i == andMark2)) {
                                        toAdd.addAll(orSentences.get(i));
                                    }
                                }
                            }

                            for (int i = 0; i < clauses.length; i++) {
                                if (!((orMarkIndex.contains(i)) || (i == andMark) || (i == andMark2))) {
                                    toAdd.add(clauses[i]);
                                }
                            }

                            if (!allAtomic) {
                                Sentence andOrSentence;
                                if (andSentence.size() > 1) {
                                    andOrSentence = AND(andSentence.toArray(new Sentence[0]));
                                } else {
                                    andOrSentence = andSentence.iterator().next();
                                }
                                Set<Sentence> orAndSentence = new HashSet<>();
                                orAndSentence.add(andOrSentence);
                                toAdd.addAll(orAndSentence);
                            }
                            if (isNegateClauses == clauses.length) {
                                if (toAdd.size() > 1) {
                                    Sentence newSentence = OR(toAdd.toArray(new Sentence[0]));
                                    afterConvert.add(newSentence);
                                } else {
                                    afterConvert.add(toAdd.iterator().next());
                                }
                            } else {
                                if (toAdd.size() > 1) {
                                    Sentence newSentence = OR(toAdd.toArray(new Sentence[0]));
                                    beforeConvert.add(newSentence);
                                } else {
                                    beforeConvert.add(toAdd.iterator().next());
                                }
                            }
                            beforeConvert.remove(s);
                    }
                }
            }
            if (isNegateSentence) {
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
