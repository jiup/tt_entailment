package knowledgebase;

import domain.propositional.AtomicSentence;
import domain.propositional.ComplexSentence;
import domain.propositional.Connective;
import domain.propositional.Sentence;
import util.propositional.SentenceUtil;

import java.util.*;

/**
 * @author (Jiupeng ∨ Lu ∨ Yu) ∧ Zhang
 * @since 10/12/2018
 */
public class PLAlgorithms {
    private static final boolean DEBUG = true;

    @SuppressWarnings("Duplicates")
    public enum Entailment implements EntailCheckStrategies {
        ModelChecking {
            private boolean[][] truthTable;
            private Map<AtomicSentence, Integer> symbols;
            private Sentence[] alphaSentences;
            private Sentence[] betaSentences;

            @Override
            public boolean entails(PLKnowledgeBase kb, Sentence... sentences) {
                if (DEBUG) {
                    int res = entailsCount(kb, sentences);
                    System.out.println();
                    printTruthTable(true);
                    System.out.println();
                    // System.out.println(res);
                    return res > 0;
                }

                if (kb.size() == 0) return true;

                alphaSentences = kb.list().toArray(new Sentence[0]);
                betaSentences = sentences;
                symbols = new LinkedHashMap<>();
                int[] index = {0};
                SentenceUtil.getSymbols(alphaSentences).forEach(s -> symbols.putIfAbsent(s, index[0]++));
                SentenceUtil.getSymbols(betaSentences).forEach(s -> symbols.putIfAbsent(s, index[0]++));
                int symbolSize = symbols.size();
                int sentenceSize = alphaSentences.length + betaSentences.length;
                int tableSize = symbolSize + sentenceSize;
                initTable(symbolSize, sentenceSize);
                loadTruthTable(symbolSize, tableSize);
                return checkEntailment(reduce(alphaSentences, symbolSize), tableSize);
            }

            public int entailsCount(PLKnowledgeBase kb, Sentence... sentences) {
                if (kb.size() == 0) return 0;

                alphaSentences = kb.list().toArray(new Sentence[0]);
                betaSentences = sentences;
                symbols = new LinkedHashMap<>();
                int[] index = {0};
                SentenceUtil.getSymbols(alphaSentences).forEach(s -> symbols.putIfAbsent(s, index[0]++));
                SentenceUtil.getSymbols(betaSentences).forEach(s -> symbols.putIfAbsent(s, index[0]++));
                int symbolSize = symbols.size();
                int sentenceSize = alphaSentences.length + betaSentences.length;
                int tableSize = symbolSize + sentenceSize;
                initTable(symbolSize, sentenceSize);
                loadTruthTable(symbolSize, tableSize);
                return checkEntailmentCount(reduce(alphaSentences, symbolSize), tableSize);
            }

            private boolean checkEntailment(Set<Integer> range, int tableSize) {
                for (int i : range) {
                    for (int j = 0; j < betaSentences.length; j++) {
                        if (!truthTable[i][tableSize - betaSentences.length + j]) {
                            return false;
                        }
                    }
                }
                return true;
            }

            @SuppressWarnings("UnusedAssignment")
            private int checkEntailmentCount(Set<Integer> range, int tableSize) {
                List<Sentence> columns;
                int count = 0;
                boolean satisfy = true;
                for (int i : range) {
                    boolean model = true;
                    for (int j = 0; j < betaSentences.length; j++) {
                        if (!truthTable[i][tableSize - betaSentences.length + j]) {
                            model = false;
                            satisfy = false;
                            break;
                        }
                    }
                    if (model) count++;

                    if (DEBUG) {
                        ArrayList<Sentence> modelSentences = new ArrayList<>();
                        columns = new ArrayList<Sentence>() {{
                            addAll(symbols.keySet());
                            addAll(Arrays.asList(alphaSentences));
                            addAll(Arrays.asList(betaSentences));
                        }};
                        for (int col = 0; col < tableSize; col++) {
                            modelSentences.add(truthTable[i][col] ? columns.remove(0) : ComplexSentence.NOT(columns.remove(0)));
                        }
                        if (model) {
                            System.out.println("Model #" + count + ":\t" + modelSentences.toString().replaceAll(", ", " ∧ "));
                        } else {
                            System.out.println("False L" + i + ":\t" + modelSentences.toString().replaceAll(", ", " ∧ "));
                        }
                    }
                }
                return satisfy ? count : -count;
            }

            private Set<Integer> reduce(Sentence[] alpha, int offset) {
                Set<Integer> range = new HashSet<>();
                for (int i = 0; i < truthTable.length; i++) range.add(i);

                for (int k = offset; k < offset + alpha.length; k++) {
                    Iterator<Integer> iterator = range.iterator();
                    while (iterator.hasNext()) {
                        if (!truthTable[iterator.next()][k]) {
                            iterator.remove();
                        }
                    }
                }
                return range;
            }

            private boolean check(int row, Sentence s) {
                if (s instanceof AtomicSentence) {
                    return truthTable[row][symbols.get(s)];
                }

                ComplexSentence sentence = (ComplexSentence) s;
                Sentence[] clauses = sentence.getClauses().toArray(new Sentence[0]);

                switch (sentence.getConnective()) {
                    case NOT:
                        return !check(row, clauses[0]);
                    case AND:
                        boolean result = true;
                        for (Sentence s0 : clauses) {
                            result = result && check(row, s0);
                        }
                        return result;
                    case OR:
                        result = false;
                        for (Sentence s0 : clauses) {
                            result = result || check(row, s0);
                        }
                        return result;
                    case IMPLICATION:
                        if (!check(row, clauses[0])) {
                            return true;
                        } else {
                            return check(row, clauses[1]);
                        }
                    case BI_IMPLICATION:
                        return check(row, clauses[0]) == check(row, clauses[1]);
                }

                return false;
            }

            private void initTable(int symbolSize, int sentenceSize) {
                truthTable = new boolean[(int) Math.pow(2, symbolSize)][symbolSize + sentenceSize];
                for (int j = 0; j < symbolSize; j++) {
                    boolean flip = false;
                    int period = (int) (truthTable.length / Math.pow(2, (j + 1)));
                    for (int i = 0; i < truthTable.length; i++) {
                        if (flip) {
                            truthTable[i][j] = true;
                        }
                        if ((i + 1) % period == 0) {
                            flip = !flip;
                        }
                    }
                }
            }

            private void loadTruthTable(int symbolSize, int tableSize) {
                int p = symbolSize - 1;
                while (++p < tableSize) {
                    Sentence s;
                    if (p < symbolSize + alphaSentences.length) {
                        s = alphaSentences[p - symbolSize];
                    } else {
                        s = betaSentences[p - symbolSize - alphaSentences.length];
                    }
                    for (int i = 0; i < truthTable.length; i++) {
                        truthTable[i][p] = check(i, s);
                    }
                }
            }

            // debug
            private void printTruthTable(boolean withHead) {
                if (withHead) {
                    System.out.println(new ArrayList<Sentence>() {{
                        addAll(symbols.keySet());
                        addAll(Arrays.asList(alphaSentences));
                        addAll(Arrays.asList(betaSentences));
                    }});
                }
                System.out.println(Arrays.deepToString(truthTable).replaceAll("\\[+", "\n")
                        .replaceAll("]+,? ?", ";").replaceAll("true", "T").replaceAll("false", "F"));
            }
        },

        RecursiveModelChecking {
            public boolean ttCheckAll(Set<Sentence> kb, Set<AtomicSentence> symbols, HashMap<AtomicSentence, Boolean> model, Sentence... sentences) {
                if (symbols.isEmpty()) {
                    if (isTrueS(model, kb.toArray(new Sentence[0]))) {
                        return isTrueS(model, sentences);
                    } else {
                        return true;
                    }
                }
                AtomicSentence p = symbols.iterator().next();
                symbols.remove(p);
                HashMap<AtomicSentence, Boolean> model1 = new HashMap<>(model);
                model1.put(p, true);
                model.put(p, false);
                return ttCheckAll(kb, new HashSet<>(symbols), model, sentences)
                        && ttCheckAll(kb, new HashSet<>(symbols), model1, sentences);
            }

            private boolean isTrue(HashMap<AtomicSentence, Boolean> model, Sentence sentence) {
                if (sentence instanceof AtomicSentence) {
                    AtomicSentence s = (AtomicSentence) sentence;
                    return model.get(s);
                }
                ComplexSentence cs = (ComplexSentence) sentence;
                Sentence[] clauses = cs.getClauses().toArray(new Sentence[0]);
                switch (cs.getConnective()) {
                    case NOT:
                        return !isTrue(model, clauses[0]);
                    case OR:
                        Boolean result = false;
                        for (Sentence s : clauses) {
                            result = result || isTrue(model, s);
                        }
                        return result;
                    case AND:
                        result = true;
                        for (Sentence s : clauses) {
                            result = result && isTrue(model, s);
                        }
                        return result;
                    case IMPLICATION:
                        result = null;
                        for (Sentence s : clauses) {
                            result = result == null ? isTrue(model, s) : !result || isTrue(model, s);
                        }
                        //noinspection ConstantConditions
                        return result;
                    case BI_IMPLICATION:
                        result = null;
                        for (Sentence s : clauses) {
                            result = result == null ? isTrue(model, s) : result == isTrue(model, s);
                        }
                        //noinspection ConstantConditions
                        return result;
                }
                return false;
            }

            private boolean isTrueS(HashMap<AtomicSentence, Boolean> model, Sentence... sentences) {
                boolean result = true;
                for (Sentence sentence : sentences) {
                    result = result && isTrue(model, sentence);
                }
                return result;
            }

            @Override
            public boolean entails(PLKnowledgeBase kb, Sentence... sentences) {
                Set<Sentence> KB = kb.list();
                Set<AtomicSentence> symbols = new HashSet<>();
                for (Sentence s : KB) {
                    symbols.addAll(SentenceUtil.getSymbols(s));
                }
                HashMap<AtomicSentence, Boolean> model = new HashMap<>();
                return ttCheckAll(KB, symbols, model, sentences);
            }
        },

        Resolution {
            private final Sentence EMPTY_CLAUSE = new AtomicSentence(null);
            private Set<Sentence> resolvents;
            private Sentence[] clauses;

            @Override
            public boolean entails(PLKnowledgeBase kb, Sentence... sentences) {
                Set<Sentence> kbSentences = kb.list();
                try {
                    checkClauses(kbSentences.toArray(new Sentence[0]));
                } catch (IllegalArgumentException e) {
                    if (kbSentences.size() > 1) {
                        kbSentences = SentenceUtil.convertToCNF(ComplexSentence.AND(kbSentences.toArray(new Sentence[0])));
                    } else {
                        kbSentences = SentenceUtil.convertToCNF(kbSentences.iterator().next());
                    }
                    checkClauses(kbSentences.toArray(new Sentence[0]));
                    System.err.println("warn: invalid KnowledgeBase for resolution, auto-converted to CNF KnowledgeBase");
                }

                resolvents = new HashSet<>(kbSentences);
                Set<Sentence> newResolvents = new HashSet<Sentence>(kbSentences) {{
                    if (sentences.length > 1) {
                        addAll(SentenceUtil.convertToCNF(ComplexSentence.NOT(ComplexSentence.AND(sentences))));
                    } else {
                        addAll(SentenceUtil.convertToCNF(ComplexSentence.NOT(sentences[0])));
                    }
                }};

                while (kb.size() > 0) {
                    // if (DEBUG) System.out.println(resolvents);
                    Sentence[] clauses = resolvents.toArray(new Sentence[0]);
                    Sentence[] newClauses = newResolvents.toArray(new Sentence[0]);
                    //  newResolvents.clear();
                    for (Sentence newClause : newClauses) {
                        for (Sentence clause : clauses) {
                            if (DEBUG) System.out.println("resolve " + newClause + ", " + clause);
                            Set<Sentence> tmp = resolve(newClause, clause);
                            if (tmp.contains(EMPTY_CLAUSE)) {
                                return true;
                            }
                            if (DEBUG && !tmp.isEmpty()) System.out.println("add new " + tmp);
                            newResolvents.addAll(tmp);
                        }
                    }
                    if (resolvents.containsAll(newResolvents)) {
                        return false;
                    }
                    resolvents.addAll(newResolvents);
                }
                return true;
            }

            private Set<Sentence> resolve(Sentence clause1, Sentence clause2) {
                Set<Sentence> combination = new HashSet<>();
                Set<Sentence> sentences1;
                Set<Sentence> sentences2 = new HashSet<Sentence>() {{
                    if (clause2 instanceof AtomicSentence) add(clause2);
                    if (clause2 instanceof ComplexSentence) {
                        if (((ComplexSentence) clause2).getConnective().equals(Connective.NOT)) {
                            add(clause2);
                        } else {
                            addAll(((ComplexSentence) clause2).getClauses()); // DNF clauses
                        }
                    }
                }};

                for (Sentence c1 : (sentences1 = new HashSet<Sentence>() {{
                    if (clause1 instanceof AtomicSentence) add(clause1);
                    if (clause1 instanceof ComplexSentence) {
                        if (((ComplexSentence) clause1).getConnective().equals(Connective.NOT)) {
                            add(clause1);
                        } else {
                            addAll(((ComplexSentence) clause1).getClauses()); // DNF clauses
                        }
                    }
                }})) {
                    //noinspection ConstantConditions
                    for (Sentence c2 : sentences2) {
                        if (c1 instanceof ComplexSentence && c2 instanceof AtomicSentence) {
                            ComplexSentence negateSentence = (ComplexSentence) c1;
                            if (((ComplexSentence) c1).getClauses().contains(c2)) {
                                Sentence s = combine(sentences1, sentences2, (AtomicSentence) c2);
                                if (s != null) combination.add(s);
                            }
                        }
                        if (c2 instanceof ComplexSentence && c1 instanceof AtomicSentence) {
                            ComplexSentence negateSentence = (ComplexSentence) c2;
                            if (((ComplexSentence) c2).getClauses().contains(c1)) {
                                Sentence s = combine(sentences1, sentences2, (AtomicSentence) c1);
                                if (s != null) combination.add(s);
                            }
                        }
                    }
                }
                return combination;
            }

            // combine to new DNF
            private Sentence combine(Set<Sentence> clause1, Set<Sentence> clause2, AtomicSentence skip) {
                List<Sentence> sentences = new ArrayList<>();
                sentences.addAll(clause1);
                sentences.addAll(clause2);
                sentences.remove(skip);
                sentences.remove(ComplexSentence.NOT(skip));
                switch (sentences.size()) {
                    case 0:
                        return EMPTY_CLAUSE;
                    case 1:
                        return sentences.iterator().next();
                    default:
                        Set<Sentence> set = new HashSet<>(sentences);
                        if (set.size() == 1) {
                            // single sentence remained after deduplicate
                            return set.iterator().next();
                        } else {
                            if (set.size() >= Math.max(clause1.size(), clause2.size())) {
                                return null;
                            }
                            for (Sentence s : set) {
                                if (set.contains(ComplexSentence.NOT(s))) return null;
                            }
                            return ComplexSentence.OR(set.toArray(new Sentence[0]));
                        }
                }
            }

            // only accept DNF clauses
            private void checkClauses(Sentence... clauses) {
                checkClauses(1, clauses);
            }

            private void checkClauses(int limit, Sentence... clauses) {
                for (Sentence c : clauses) {
                    if (c instanceof AtomicSentence) continue;
                    if (c instanceof ComplexSentence) {
                        ComplexSentence sentence = (ComplexSentence) c;
                        switch (sentence.getConnective()) {
                            case NOT:
                                continue;
                            case OR:
                                if (limit == 0) throw new IllegalArgumentException("nesting out of limit");
                                for (Sentence s : sentence.getClauses()) {
                                    checkClauses(limit - 1, s);
                                }
                                break;
                            default:
                                if (limit == 0) throw new IllegalArgumentException("nesting out of limit");
                                throw new IllegalArgumentException("invalid connective for resolution (DNF expected)");
                        }
                    }
                }
            }
        }
    }

    interface EntailCheckStrategies {
        boolean entails(PLKnowledgeBase kb, Sentence... sentences);
    }
}
