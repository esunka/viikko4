
package ohtu.intjoukkosovellus;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.ArrayList;
import java.lang.*;
import org.apache.commons.lang3.ArrayUtils;
public class IntJoukko {

    private ArrayList<Integer> ljono = new ArrayList<Integer>();      // Joukon luvut säilytetään taulukon alkupäässä. 
    
    public IntJoukko(ArrayList<Integer> jono){
        this.ljono = jono;
    }
    public IntJoukko(){
        
    }
    
    public ArrayList<Integer> getJoukko() {
        return ljono;
    }
    public boolean lisaa(Integer luku) {
        return this.kuuluu(luku) ? false : ljono.add(luku);
        //return ljono.add(luku);
    }

    public boolean kuuluu(Integer luku) {
        return ljono.contains(luku);
        //return IntStream.of(ljono).anyMatch(x -> x == luku);
    }

    public boolean poista(Integer luku) {
       return ljono.remove(luku);
    }

    public int mahtavuus() {
        return ljono.size();
    }


    @Override
    public String toString() {
        String withBrackets = Arrays.toString(ljono.toArray());
        return withBrackets.replace("[","{").replace("]","}");
    }

    public Integer[] toIntArray() {
        return ljono.toArray(new Integer[ljono.size()]);
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        // Because we don't want to modify the original
        ArrayList<Integer> retValue = new ArrayList<Integer>();
        retValue.addAll(a.ljono);
        retValue.addAll(b.ljono);
        return new IntJoukko(retValue);

    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        ArrayList<Integer> retValue = new ArrayList<Integer>();
        retValue.addAll(a.ljono);
        retValue.retainAll(b.ljono);
        return new IntJoukko(retValue);
    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        ArrayList<Integer> retValue = new ArrayList<Integer>();
        retValue.addAll(a.ljono);
        retValue.removeAll(b.ljono);
        return new IntJoukko(retValue);
    }
        
}
