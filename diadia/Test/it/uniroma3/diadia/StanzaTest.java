package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StanzaTest {
	
	/*stanze per il test dei metodi riguardanti l'adiacenza*/
	private Stanza noAdiacenti;
	private Stanza tutteAdiacenti;
	private Stanza stanzaNord;
	private Stanza stanzaSud;
	private Stanza stanzaEst;
	private Stanza stanzaOvest;
	private Stanza stanza5;
	
	/*Stanze per il test dei metodi riguardanti gli attrezzi*/
	private Stanza noAttrezzi;
	private Stanza tuttiAttrezzi;
	
	private Attrezzo Attrezzo1;
	private Attrezzo Attrezzo2;
	private Attrezzo Attrezzo3;
	private Attrezzo Attrezzo4;
	private Attrezzo Attrezzo5;
	private Attrezzo Attrezzo6;
	private Attrezzo Attrezzo7;
	private Attrezzo Attrezzo8;
	private Attrezzo Attrezzo9;
	private Attrezzo Attrezzo10;
	private Attrezzo Attrezzo11;

	@Before
	public void setUpAdiacenti() throws Exception {
		this.noAdiacenti = new Stanza("noAdiacenti"); //stanza senza stanze adiacenti
		this.tutteAdiacenti = new Stanza("tutteAdiacenti"); //stanza con 4 stanze adiacenti
		
		//Imposto le 4 stanze adiacenti
		tutteAdiacenti.impostaStanzaAdiacente("nord", stanzaNord);
		tutteAdiacenti.impostaStanzaAdiacente("sud", stanzaSud);
		tutteAdiacenti.impostaStanzaAdiacente("est", stanzaEst);
		tutteAdiacenti.impostaStanzaAdiacente("ovest", stanzaOvest);
	}
	/* Test su una stanza senza stanze adiacenti*/
	@Test
	public void testGetStanzaNoAdiacenti() {
		assertEquals("La stanza ha delle stanze adiacenti", null, noAdiacenti.getStanzaAdiacente("nord"));
	}
	
	/* Test di aggiunta di una nuova stanza adiacente su una stanza con già 4 stanze
	 * adiacenti esistenti
	 */
	@Test
	public void testGetStanzaTutteAdiacenti() {
		tutteAdiacenti.impostaStanzaAdiacente("nord", stanza5);
		assertEquals(stanza5, tutteAdiacenti.getStanzaAdiacente("nord"));
	}
	
	@Before
	public void setUpAttrezzi(){
		noAttrezzi = new Stanza("noAttrezzi");
		tuttiAttrezzi = new Stanza("tuttiAttrezzi");
		
		Attrezzo1 = new Attrezzo("Attrezzo1", 1);
		Attrezzo2 = new Attrezzo("Attrezzo2", 2);
		Attrezzo3 = new Attrezzo("Attrezzo3", 3);
		Attrezzo4 = new Attrezzo("Attrezzo4", 4);
		Attrezzo5 = new Attrezzo("Attrezzo5", 5);
		Attrezzo6 = new Attrezzo("Attrezzo6", 6);
		Attrezzo7 = new Attrezzo("Attrezzo7", 7);
		Attrezzo8 = new Attrezzo("Attrezzo8", 8);
		Attrezzo9 = new Attrezzo("Attrezzo9", 9);
		Attrezzo10 = new Attrezzo("Attrezzo10", 10);
		Attrezzo11 = new Attrezzo("Attrezzo11", 11);
		
		/*Nella stanza "tutteAdiacenti" sono presenti 10 attrezzi*/
		tuttiAttrezzi.addAttrezzo(Attrezzo1);
		tuttiAttrezzi.addAttrezzo(Attrezzo2);
		tuttiAttrezzi.addAttrezzo(Attrezzo3);
		tuttiAttrezzi.addAttrezzo(Attrezzo4);
		tuttiAttrezzi.addAttrezzo(Attrezzo5);
		tuttiAttrezzi.addAttrezzo(Attrezzo6);
		tuttiAttrezzi.addAttrezzo(Attrezzo7);
		tuttiAttrezzi.addAttrezzo(Attrezzo8);
		tuttiAttrezzi.addAttrezzo(Attrezzo9);
		tuttiAttrezzi.addAttrezzo(Attrezzo10);
		
	}
	
	/*Testo se nella stanza "noAttrezzi" che attualmente ha 0 attrezzi il metodo 
	 * hasAttrezzo("Attrezzo1") ritorna false
	 */
	@Test
	public void testHasAttrezzoNellaStanzaNoAdiacenti() {
		assertEquals("La stanza contiene un attrezzo",false, noAttrezzi.hasAttrezzo("Attrezzo1"));
	}
	
	/*Testo se nella stanza "tutteAdiacenti" che attualmente ha 10 attrezzi
	 * il metodo hasAttrezzo("Attrezzo11") ritorna true anche se il limite di 
	 * attrezzi presenti in una stanza è 10
	 */

	@Test
	public void testHasAttrezzoNellaStanzaTutteAdiacenti() {
		tuttiAttrezzi.addAttrezzo(Attrezzo11);
		assertEquals(false, tuttiAttrezzi.hasAttrezzo("Attrezzo11"));
	}
	
	/*Testo se nella stanza "noAttrezzi" il valore ritornato di getAttrezzo()
	 * sia null
	 */
	@Test
	public void testGetAttrezzo() {
		assertEquals("C'è un attrezzo nella stanza",null, noAttrezzi.getAttrezzo("Attrezzo1"));
	}
	
	
	@Test
	public void testGetDirezioni() {
		fail("Not yet implemented");
	}
	

}
