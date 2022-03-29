package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

	final private String direzioniVuoto[] = new String[0];
	final private String direzioni[] = { "nord", "sud", "est", "ovest" };

	/*
	 * stanze per il test dei metodi riguardanti l'adiacenza
	 */
	private Stanza noAdiacenti;
	private Stanza tutteAdiacenti;
	private Stanza stanzaNord;
	private Stanza stanzaSud;
	private Stanza stanzaEst;
	private Stanza stanzaOvest;
	private Stanza stanzaOverflow;

	/*
	 * Stanze per il test dei metodi riguardanti gli attrezzi
	 */
	private Stanza noAttrezzi;
	private Stanza tuttiAttrezzi;
	private Stanza unSoloAttrezzo;

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
		this.noAdiacenti = new Stanza("noAdiacenti"); // stanza senza stanze adiacenti
		this.tutteAdiacenti = new Stanza("tutteAdiacenti"); // stanza con 4 stanze adiacenti

		// Imposto le 4 stanze adiacenti
		tutteAdiacenti.impostaStanzaAdiacente("nord", stanzaNord);
		tutteAdiacenti.impostaStanzaAdiacente("sud", stanzaSud);
		tutteAdiacenti.impostaStanzaAdiacente("est", stanzaEst);
		tutteAdiacenti.impostaStanzaAdiacente("ovest", stanzaOvest);
	}

	/*
	 * Test su una stanza senza stanze adiacenti
	 */
	@Test
	public void testGetStanzaNoAdiacentiNord() {
		assertEquals("La stanza ha delle stanze adiacenti", null, noAdiacenti.getStanzaAdiacente("nord"));
		assertEquals("La stanza ha delle stanze adiacenti", null, noAdiacenti.getStanzaAdiacente("sud"));
		assertEquals("La stanza ha delle stanze adiacenti", null, noAdiacenti.getStanzaAdiacente("ovest"));
		assertEquals("La stanza ha delle stanze adiacenti", null, noAdiacenti.getStanzaAdiacente("est"));
	}

	/*
	 * Test di aggiunta di una nuova stanza adiacente su una stanza con già 4 stanze
	 * adiacenti esistenti
	 */
	@Test
	public void testGetStanzaTutteAdiacenti() {
		tutteAdiacenti.impostaStanzaAdiacente("nord", stanzaOverflow);
		assertEquals(stanzaOverflow, tutteAdiacenti.getStanzaAdiacente("nord"));
	}

	@Before
	public void setUpAttrezzi() {
		noAttrezzi = new Stanza("noAttrezzi");
		tuttiAttrezzi = new Stanza("tuttiAttrezzi");
		unSoloAttrezzo = new Stanza("unSoloAttrezzo");

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

		/* Nella stanza "tutteAdiacenti" sono presenti 10 attrezzi */
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

		/* aggiungo un attrezzo alla stanza con un solo attrezzo */
		unSoloAttrezzo.addAttrezzo(Attrezzo1);

	}

	/*
	 * Testo se nella stanza "noAttrezzi" che attualmente ha 0 attrezzi il metodo
	 * hasAttrezzo("Attrezzo1") ritorna false
	 */
	@Test
	public void testHasAttrezzoNellaStanzaNoAttrezzi() {
		assertEquals("La stanza contiene un attrezzo", false, noAttrezzi.hasAttrezzo("Attrezzo1"));
	}

	/*
	 * Testo se nella stanza "tuttiAttrezzi" che attualmente ha 10 attrezzi il
	 * metodo hasAttrezzo("Attrezzo11") ritorna false, poiché il limite è 10
	 */

	@Test
	public void testHasAttrezzoNellaStanzaTuttiAttrezzi() {
		tuttiAttrezzi.addAttrezzo(Attrezzo11);
		assertEquals(false, tuttiAttrezzi.hasAttrezzo("Attrezzo11"));
	}

	/*
	 * Testo se nella stanza "tuttiAttrezzi" il metodo getAttrezzo ritorna
	 * l'attrezzo cercato
	 */
	public void testGetAttrezzo1() {
		assertEquals(Attrezzo1, tuttiAttrezzi.getAttrezzo("Attrezzo1"));
	}

	/*
	 * Testo se nella stanza "noAttrezzi" il metodo getAttrezzo ritorna null
	 */
	public void testGetAttrezzoNellaStanzaVuota() {
		assertEquals(null, noAttrezzi.getAttrezzo("Attrezzo1"));
	}

	/*
	 * Testo se nella stanza "noAdiacenti" il metodo getDirezioni() ritorna un array
	 * vuoto
	 */
	@Test
	public void testGetDirezioniSenzaStanzeAdiacenti() {
		assertArrayEquals(direzioniVuoto, noAdiacenti.getDirezioni());
	}

	/*
	 * Testo se nella stanza "tutteAdiacenti" il metodo getDirezioni() ritorna
	 * l'array con le 4 direzioni corrispondenti alle 4 stanze adiacenti
	 */
	@Test
	public void testGetDirezioniCon4StanzeAdiacenti() {
		assertArrayEquals(direzioni, tutteAdiacenti.getDirezioni());
	}

	/*
	 * Testo se nella stanza "noAttrezzi" il metodo removeAttrezzo() ritorni false
	 */
	@Test
	public void testRemoveAttrezzoInStanzaSenzaAttrezzi() {
		assertFalse(noAttrezzi.removeAttrezzo("Attrezzo1"));
	}

	/*
	 * Testo se nella stanza con un solo attrezzo avvenga correttamente la rimozione
	 * dell'attrezzo 1
	 */
	@Test
	public void testRemoveAttrezzoInStanzaConUnSoloAttrezzo() {
		assertTrue(unSoloAttrezzo.removeAttrezzo("Attrezzo1"));
	}

	/*
	 * Testo se nella stanza con un solo attrezzo avvenga correttamente la rimozione
	 * dell'attrezzo 1
	 */
	@Test
	public void testRemoveAttrezzoControlloEffettivaRimozione() {
		unSoloAttrezzo.removeAttrezzo("Attrezzo1");
		assertFalse(unSoloAttrezzo.hasAttrezzo("Attrezzo1"));
	}

}
