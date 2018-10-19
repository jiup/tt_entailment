package domain.propositional;

/**
 * Created by zhanglu on 10/18/18.
 */
import java.util.*;

public class NegDNF implements Negatable {
//    ComplexSentence DNF;
//    NegDNF(ComplexSentence dnf){
//        DNF = dnf;
//    }
//    public Set<Sentence> negate(){
//        Connective conn = DNF.getConnective();
//
//        Set<Sentence> sentences = DNF.getClauses();
//        if (conn.equals(Connective.NOT)) return sentences;
//                //if (conn.equals(Connective.OR)) conn = Connective.AND;
//        Set<Sentence> negate = new LinkedHashSet<>();
//        Set<Sentence> negated = new LinkedHashSet<>();
//        for (Sentence s:sentences){
//            if (s instanceof AtomicSentence){
//                s = new ComplexSentence(Connective.NOT, s);
//                negated.add(s);
//            }
//            else{
//                s = ((ComplexSentence)s).getClauses().iterator().next();
//                negated.add(s);
//            }
//        }
//        if (conn.equals(Connective.AND)){
//            negate.add(new ComplexSentence(Connective.OR, negated.toArray(new Sentence[0])));
//            return negate;
//        }
//        else{
//            return negated;
//        }
//    }

    public static void main(String[] args) {
//        ComplexSentence tester = new ComplexSentence(Connective.OR, new ComplexSentence(Connective.NOT, new AtomicSentence("B")), new AtomicSentence("A"));
//        ComplexSentence tester2 = new ComplexSentence(Connective.NOT, new AtomicSentence("A"));
//        NegDNF test = new NegDNF(tester);
//        NegDNF test2 = new NegDNF(tester2);
//        Set<Sentence> negated = test.negate();
//        for (Sentence s: negated){
//            System.out.println(s);
//        }
        NegDNF negDNF = new NegDNF();
        System.out.println(negDNF.negate(new ComplexSentence(Connective.NOT, new AtomicSentence("A"))));
    }

    @Override
    public Set<Sentence> negate(ComplexSentence DNF) {
        Connective conn = DNF.getConnective();
        Set<Sentence> sentences = DNF.getClauses();
        if (conn.equals(Connective.NOT)) return sentences;
        //if (conn.equals(Connective.OR)) conn = Connective.AND;
        Set<Sentence> negate = new LinkedHashSet<>();
        Set<Sentence> negated = new LinkedHashSet<>();
        for (Sentence s:sentences){
            if (s instanceof AtomicSentence){
                s = new ComplexSentence(Connective.NOT, s);
                negated.add(s);
            }
            else{
                s = ((ComplexSentence)s).getClauses().iterator().next();
                negated.add(s);
            }
        }
        if (conn.equals(Connective.AND)){
            negate.add(new ComplexSentence(Connective.OR, negated.toArray(new Sentence[0])));
            return negate;
        }
        else{
            return negated;
        }
    }
}
