
package ohtu.intjoukkosovellus;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
public class JoukkoOperaatiotTest {
    @Test
    public void testYhdiste() {
        IntJoukko eka = teeJoukko(1,2);
        IntJoukko toka = teeJoukko(3,4);
        
        IntJoukko tulos = IntJoukko.yhdiste(eka, toka);
        Integer[] vastauksenLuvut = tulos.toIntArray();
        Arrays.sort(vastauksenLuvut);
        
        Integer[] odotettu = {1,2,3,4};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    } 

    @Test
    public void testLeikkaus() {
        IntJoukko eka = teeJoukko(1,2);
        IntJoukko toka = teeJoukko(2,3,4);
        
        IntJoukko tulos = IntJoukko.leikkaus(eka, toka);
        Integer[] vastauksenLuvut = tulos.toIntArray();
        Arrays.sort(vastauksenLuvut);
        
        Integer[] odotettu = {2};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    }     
    
    @Test
    public void testErotus() {
        IntJoukko eka = teeJoukko(1,2,5,6);
        IntJoukko toka = teeJoukko(2,3,4);
        
        IntJoukko tulos = IntJoukko.erotus(eka, toka);
        Integer[] vastauksenLuvut = tulos.toIntArray();
        Arrays.sort(vastauksenLuvut);
        
        Integer[] odotettu = {1, 5, 6};
        
        assertArrayEquals(odotettu, vastauksenLuvut);        
    }  

    private IntJoukko teeJoukko(Integer... luvut) {
        IntJoukko joukko = new IntJoukko();
        
        for (Integer luku : luvut) {
            joukko.lisaa(luku);
        }
        
        return joukko;
    }
}
