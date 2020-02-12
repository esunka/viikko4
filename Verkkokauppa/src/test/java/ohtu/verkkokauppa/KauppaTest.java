package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;

import ohtu.verkkokauppa.Viitegeneraattori;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
	Pankki pankki;
	Viitegeneraattori viite;
	Varasto varasto;
	Kauppa k;

	@Before
	public void setUp() {
		pankki = mock(Pankki.class);
		viite = mock(Viitegeneraattori.class);
		varasto = mock(Varasto.class);
		k = new Kauppa(varasto,pankki,viite);
	}

    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
       
        when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
	}
	@Test 
	public void checkAccount() {
		when(viite.uusi()).thenReturn(42);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
		k.tilimaksu("pekka", "12345");
		/* public boolean tilisiirto(
			String nimi,
			 int viitenumero,
			  String tililta,
			   String tilille,
			    int summa)
		*/
		verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"),anyString(),eq(5));

	}
	@Test
	public void checkNameNumberSum(){
		when(viite.uusi()).thenReturn(42);
		when(varasto.saldo(1)).thenReturn(10); 
		when(varasto.saldo(2)).thenReturn(10);
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
		when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "es", 1));
	
		k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
		k.lisaaKoriin(2); 
		k.tilimaksu("pekka", "12345");
		verify(pankki).tilisiirto(eq("pekka"),anyInt(),eq("12345"),anyString(),eq(6));

	}
	@Test
	public void checkNameNumberSum2() {
		when(viite.uusi()).thenReturn(42);
		when(varasto.saldo(1)).thenReturn(10); 
		//when(varasto.saldo(2)).thenReturn(10);
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
		//when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "es", 1));
	
		k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
		k.lisaaKoriin(1); 
		k.tilimaksu("pekka", "12345");
		verify(pankki).tilisiirto(eq("pekka"),anyInt(),eq("12345"),anyString(),eq(10));

	}
	@Test
	public void checkNameNumberSum3() {
		when(viite.uusi()).thenReturn(42);
		when(varasto.saldo(1)).thenReturn(10); 
		when(varasto.saldo(2)).thenReturn(0);
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
		when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "es", 1));
	
		k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
		k.lisaaKoriin(2); 
		k.tilimaksu("pekka", "12345");
		verify(pankki).tilisiirto(eq("pekka"),anyInt(),eq("12345"),anyString(),eq(5));

	}
}

