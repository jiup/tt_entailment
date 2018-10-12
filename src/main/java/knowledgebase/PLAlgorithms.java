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
        TruthTableChecking {
            private boolean[][] truthTable;
            private Map<AtomicSentence, Integer> symbols;
            private Sentence[] alphaSentences;
            private Sentence[] betaSentences;

            @Override
            public boolean entails(PLKnowledgeBase kb, Sentence... sentence) {
                alphaSentences = kb.list().toArray(new Sentence[0]);
                betaSentences = sentence;
                symbols = new LinkedHashMap<>();
                int[] index = {0};
                getSymbols(alphaSentences).forEach(s -> symbols.putIfAbsent(s, index[0]++));
                getSymbols(betaSentences).forEach(s -> symbols.putIfAbsent(s, index[0]++));
                int symbolSize = symbols.size();
                int sentenceSize = alphaSentences.length + betaSentences.length;
                int tableSize = symbolSize + sentenceSize;
                this.initTable(symbolSize, sentenceSize);

                // calculate alpha sentences
                for (int j = 0; j < alphaSentences.length; j++) {
                    Sentence s = alphaSentences[j];
                    for (int i = 0; i < truthTable.length; i++) {
                        truthTable[i][symbolSize + j] = check(i, s);
                    }
                }

                // calculate beta sentences
                for (int j = 0; j < betaSentences.length; j++) {
                    Sentence s = betaSentences[j];
                    for (int i = 0; i < truthTable.length; i++) {
                        truthTable[i][tableSize - betaSentences.length + j] = check(i, s);
                    }
                }

                // printTruthTable();
                Set<Integer> range = reduce(alphaSentences, symbolSize);
                if (range.isEmpty()) {
                    return false;
                }
                for (int i : range) {
                    for (int j = 0; j < betaSentences.length; j++) {
                        if (!truthTable[i][tableSize - betaSentences.length + j]) {
                            return false;
                        }
                    }
                }
                return true;
            }

            private void printTruthTable() {
                List<Sentence> columns = new ArrayList<>();
                columns.addAll(symbols.keySet());
                columns.addAll(Arrays.asList(alphaSentences));
                columns.addAll(Arrays.asList(betaSentences));
                System.out.println(columns + Arrays.deepToString(truthTable).replaceAll("\\[+", "\n")
                        .replaceAll("]+,? ?", ";").replaceAll("true", "T").replaceAll("false", "F"));
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
        }
    }

    interface EntailCheckStrategies {
        boolean entails(PLKnowledgeBase kb, Sentence... sentence);
    }
}
