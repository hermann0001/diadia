package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

	final private String direzioni[] = { "nord", "sud", "est", "ovest" };
	static final private int NUMERO_MAX_DIREZIONI = 4;
	static final private int NUMERO_MAX_ATTREZZI = 10;
	final private String direzioniVuoto[] = new String[0];

	/*
	 * stanze per il test dei metodi riguardanti l'adiacenza
	 */
	private Stanza noAdiacenti;
	private Stanza tutteAdiacenti;
	private Stanza stanzeDirezioni[];
	private Stanza stanzaOverflow;

	/*
	 * Stanze per il test dei metodi riguardanti gli attrezzi
	 */
	private Stanza noAttrezzi;
	private Stanza tuttiAttrezzi;
	private Stanza unSoloAttrezzo;

	private Attrezzo attrezzi[];
	private Attrezzo attrezzoOverflow;

	@Before
	public void setUpAdiacenti() throws Exception {
		this.noAdiacenti = new Stanza("noAdiacenti"); // stanza senza stanze adiacenti
		this.tutteAdiacenti = new Stanza("tutteAdiacenti"); // stanza con 4 stanze adiacenti
		this.stanzeDirezioni = new Stanza[NUMERO_MAX_DIREZIONI];

		// Imposto le 4 stanze adiacenti
		for (int i = 0; i < NUMERO_MAX_DIREZIONI; i++)
			this.tutteAdiacenti.impostaStanzaAdiacente(direzioni[i], this.stanzeDirezioni[i]);
	}

	/*
	 * Test su una stanza senza stanze adiacenti
	 */
	@Test
	public void testGetStanzaNoAdiacentiNord() {
		for (String direzione : direzioni)
			assertEquals("La stanza ha delle stanze adiacenti", null, this.noAdiacenti.getStanzaAdiacente(direzione));
	}

	/*
	 * Test di aggiunta di una nuova stanza adiacente su una stanza con già 4 stanze
	 * adiacenti esistenti
	 */
	@Test
	public void testGetStanzaTutteAdiacenti() {
		this.tutteAdiacenti.impostaStanzaAdiacente(direzioni[0], stanzaOverflow);
		assertEquals(this.stanzaOverflow, this.tutteAdiacenti.getStanzaAdiacente(direzioni[0]));
	}

	@Before
	public void setUpAttrezzi() {
		this.noAttrezzi = new Stanza("noAttrezzi");
		this.tuttiAttrezzi = new Stanza("tuttiAttrezzi");
		this.unSoloAttrezzo = new Stanza("unSoloAttrezzo");
		this.attrezzi = new Attrezzo[NUMERO_MAX_ATTREZZI];
		this.attrezzoOverflow = new Attrezzo("Attrezzo10", 10);

		for (int i = 0; i < NUMERO_MAX_ATTREZZI; i++)
			this.attrezzi[i] = new Attrezzo("Attrezzo" + i, i);

		/* Nella stanza "tutteAdiacenti" sono presenti 10 attrezzi */
		for (Attrezzo attrezzo : this.attrezzi)
			this.tuttiAttrezzi.addAttrezzo(attrezzo);

		/* aggiungo un attrezzo alla stanza con un solo attrezzo */
		this.unSoloAttrezzo.addAttrezzo(this.attrezzi[0]);

	}

	/*
	 * Testo se nella stanza "noAttrezzi" che attualmente ha 0 attrezzi il metodo
	 * hasAttrezzo("Attrezzo1") ritorna false
	 */
	@Test
	public void testHasAttrezzoNellaStanzaNoAttrezzi() {
		assertEquals("La stanza contiene un attrezzo", false, this.noAttrezzi.hasAttrezzo("Attrezzo0"));
	}

	/*
	 * Testo se nella stanza "tuttiAttrezzi" che attualmente ha 10 attrezzi il
	 * metodo hasAttrezzo("Attrezzo11") ritorna false, poiché il limite è 10
	 */

	@Test
	public void testHasAttrezzoNellaStanzaTuttiAttrezzi() {
		this.tuttiAttrezzi.addAttrezzo(this.attrezzoOverflow);
		assertEquals(false, this.tuttiAttrezzi.hasAttrezzo("Attrezzo10"));
	}

	/*
	 * Testo se nella stanza "tuttiAttrezzi" il metodo getAttrezzo ritorna
	 * l'attrezzo cercato
	 */
	public void testGetAttrezzo1() {
		assertEquals(this.attrezzi[0], this.tuttiAttrezzi.getAttrezzo("Attrezzo0"));
	}

	/*
	 * Testo se nella stanza "noAttrezzi" il metodo getAttrezzo ritorna null
	 */
	public void testGetAttrezzoNellaStanzaVuota() {
		assertEquals(null, this.noAttrezzi.getAttrezzo("Attrezzo0"));
	}

	/*
	 * Testo se nella stanza "noAdiacenti" il metodo getDirezioni() ritorna un array
	 * vuoto
	 */
	@Test
	public void testGetDirezioniSenzaStanzeAdiacenti() {
		assertArrayEquals(direzioniVuoto, this.noAdiacenti.getDirezioni());
	}

	/*
	 * Testo se nella stanza "tutteAdiacenti" il metodo getDirezioni() ritorna
	 * l'array con le 4 direzioni corrispondenti alle 4 stanze adiacenti
	 */
	@Test
	public void testGetDirezioniCon4StanzeAdiacenti() {
		assertArrayEquals(direzioni, this.tutteAdiacenti.getDirezioni());
	}

	/*
	 * Testo se nella stanza "noAttrezzi" il metodo removeAttrezzo() ritorni false
	 */
	@Test
	public void testRemoveAttrezzoInStanzaSenzaAttrezzi() {
		assertFalse(this.noAttrezzi.removeAttrezzo("Attrezzo0"));
	}

	/*
	 * Testo se nella stanza con un solo attrezzo avvenga correttamente la rimozione
	 * dell'attrezzo 1
	 */
	@Test
	public void testRemoveAttrezzoInStanzaConUnSoloAttrezzo() {
		assertTrue(this.unSoloAttrezzo.removeAttrezzo("Attrezzo0"));
	}

	/*
	 * Testo se nella stanza con un solo attrezzo avvenga correttamente la rimozione
	 * dell'attrezzo 1
	 */
	@Test
	public void testRemoveAttrezzoControlloEffettivaRimozione() {
		this.unSoloAttrezzo.removeAttrezzo("Attrezzo0");
		assertFalse(this.unSoloAttrezzo.hasAttrezzo("Attrezzo0"));
	}

}
