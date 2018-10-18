package knowledgebase;

import domain.propositional.AtomicSentence;
import domain.propositional.ComplexSentence;
import domain.propositional.Sentence;

import java.util.*;

/**
 * @author Jiupeng Zhang
 * @since 10/12/2018
 */
public class PLAlgorithms {
    public enum Entailment implements EntailCheckStrategies {
        ModelChecking {
            private boolean[][] truthTable;
            private Map<AtomicSentence, Integer> symbols;
            private Sentence[] alphaSentences;
            private Sentence[] betaSentences;

            @Override
            public boolean entails(PLKnowledgeBase kb, Sentence... sentences) {
                alphaSentences = kb.list().toArray(new Sentence[0]);
                betaSentences = sentences;
                symbols = new LinkedHashMap<>();
                int[] index = {0};
                getSymbols(alphaSentences).forEach(s -> symbols.putIfAbsent(s, index[0]++));
                getSymbols(betaSentences).forEach(s -> symbols.putIfAbsent(s, index[0]++));
                int symbolSize = symbols.size();
                int sentenceSize = alphaSentences.length + betaSentences.length;
                int tableSize = symbolSize + sentenceSize;
                initTable(symbolSize, sentenceSize);
                loadTruthTable(symbolSize, tableSize);
                return checkEntailment(reduce(alphaSentences, symbolSize), tableSize);
            }

            @Override
            public int entailsCount(PLKnowledgeBase kb, Sentence... sentences) {
                alphaSentences = kb.list().toArray(new Sentence[0]);
                betaSentences = sentences;
                symbols = new LinkedHashMap<>();
                int[] index = {0};
                getSymbols(alphaSentences).forEach(s -> symbols.putIfAbsent(s, index[0]++));
                getSymbols(betaSentences).forEach(s -> symbols.putIfAbsent(s, index[0]++));
                int symbolSize = symbols.size();
                int sentenceSize = alphaSentences.length + betaSentences.length;
                int tableSize = symbolSize + sentenceSize;
                initTable(symbolSize, sentenceSize);
                loadTruthTable(symbolSize, tableSize);
                return checkEntailmentCount(reduce(alphaSentences, symbolSize), tableSize);
            }

            private boolean checkEntailment(Set<Integer> range, int tableSize) {
                for (int i : range) {
                    boolean model = true;
                    for (int j = 0; j < betaSentences.length; j++) {
                        if (!truthTable[i][tableSize - betaSentences.length + j]) {
                            model = false;
                            break;
                        }
                    }
                    if (model) return true;
                }
                return false;
            }

            private int checkEntailmentCount(Set<Integer> range, int tableSize) {
                int count = 0;
                for (int i : range) {
                    boolean model = true;
                    for (int j = 0; j < betaSentences.length; j++) {
                        if (!truthTable[i][tableSize - betaSentences.length + j]) {
                            model = false;
                            break;
                        }
                    }
                    if (model) count++;

                    // debug
//                    if (model) {
//                        ArrayList<Sentence> modelSentences = new ArrayList<>();
//                        List<Sentence> columns = new ArrayList<Sentence>() {{
//                            addAll(symbols.keySet());
//                            addAll(Arrays.asList(alphaSentences));
//                            addAll(Arrays.asList(betaSentences));
//                        }};
//                        for (int col = 0; col < tableSize; col++) {
//                            modelSentences.add(truthTable[i][col] ? columns.remove(0) : ComplexSentence.NOT(columns.remove(0)));
//                        }
//                        System.out.println("Model #" + count + ": " + modelSentences.toString().replaceAll(", ", " ∧ "));
//                    }
                }
                return count;
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

            private Set<AtomicSentence> getSymbols(Sentence... sentences) {
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
        }
    }

    interface EntailCheckStrategies {
        boolean entails(PLKnowledgeBase kb, Sentence... sentences);

        int entailsCount(PLKnowledgeBase kb, Sentence... sentences);
    }
}
