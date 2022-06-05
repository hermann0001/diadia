package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.util.*;

import static it.uniroma3.diadia.Direzione.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {

	/*
	 * stanze per il test dei metodi riguardanti l'adiacenza
	 */
	private Stanza noAdiacenti;
	private Stanza tutteAdiacenti;
	private Map<Direzione, Stanza> direzione2stanza;
	private Stanza stanzaOverflow;
	private Stanza noDoppioni;

	/*
	 * Stanze per il test dei metodi riguardanti gli attrezzi
	 */
	private Stanza noAttrezzi;
	private Stanza tuttiAttrezzi;
	private Stanza unSoloAttrezzo;

	private List<Attrezzo> attrezzi;
	private Attrezzo attrezzoOverflow;

	@Before
	public void setUpAdiacenti() {
		this.noAdiacenti = new Stanza("noAdiacenti"); // stanza senza stanze adiacenti
		this.tutteAdiacenti = new Stanza("tutteAdiacenti"); // stanza con 4 stanze adiacenti
		this.direzione2stanza = new HashMap<>();
		
		this.stanzaOverflow = new Stanza("overflow");

		// Imposto le 4 stanze adiacenti
		for (Direzione d : Direzione.values()) {
			this.direzione2stanza.put(d, new Stanza("Stanza " + d));
			this.tutteAdiacenti.impostaStanzaAdiacente(d, this.direzione2stanza.get(d));
		}
	}

	/*
	 * Test su una stanza senza stanze adiacenti
	 */
	@Test
	public void testGetStanzaNoAdiacentiNord() {
		for (Direzione d : Direzione.values())
			assertEquals("La stanza ha delle stanze adiacenti", null, this.noAdiacenti.getStanzaAdiacente(d));
	}

	/*
	 * Test di aggiunta di una nuova stanza adiacente su una stanza con già 4 stanze
	 * adiacenti esistenti
	 */
	@Test
	public void testGetStanzaTutteAdiacenti() {
		this.tutteAdiacenti.impostaStanzaAdiacente(NORD, stanzaOverflow);
		assertEquals(this.stanzaOverflow, this.tutteAdiacenti.getStanzaAdiacente(NORD));
	}
	
	@Test
	public void testSeStessaComeAdiacente() {
		Stanza seStessa = new Stanza("seStessa");
		seStessa.impostaStanzaAdiacente(SUD, seStessa);
		assertEquals(0, seStessa.getAdiacenze().size());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testImpostaAdiacenteParametroErrato() {
		Stanza centrale = new Stanza("centrale");
		Stanza adiacente = new Stanza("adiacente");
		Direzione direzione = Direzione.valueOf("sud-ovest");
		centrale.impostaStanzaAdiacente(direzione, adiacente);
		
		assertNull(centrale.getStanzaAdiacente(direzione));
	}

	@Before
	public void setUpAttrezzi() {
		this.noAttrezzi = new Stanza("noAttrezzi");
		this.tuttiAttrezzi = new Stanza("tuttiAttrezzi");
		this.unSoloAttrezzo = new Stanza("unSoloAttrezzo");
		this.attrezzi = new ArrayList<>();
		this.attrezzoOverflow = new Attrezzo("Attrezzo10", 10);
		this.noDoppioni = new Stanza("noDoppioni");

		for (int i = 0; i < Stanza.NUMERO_MASSIMO_ATTREZZI; i++)
			this.attrezzi.add(new Attrezzo("Attrezzo"+i, i));

		/* Nella stanza "tutteAdiacenti" sono presenti 10 attrezzi */
		for (Attrezzo a : this.attrezzi)
			this.tuttiAttrezzi.addAttrezzo(a);

		/* aggiungo un attrezzo alla stanza con un solo attrezzo */
		this.unSoloAttrezzo.addAttrezzo(this.attrezzi.get(0));

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
		assertEquals(this.attrezzi.get(0), this.tuttiAttrezzi.getAttrezzo("Attrezzo0"));
	}

	/*
	 * Testo se nella stanza "noAttrezzi" il metodo getAttrezzo ritorna null
	 */
	public void testGetAttrezzoNellaStanzaVuota() {
		assertEquals(null, this.noAttrezzi.getAttrezzo("Attrezzo0"));
	}

	/*
	 * Testo se nella stanza "noAdiacenti" il metodo getAdiacenze() ritorna un array
	 * vuoto
	 */
	@Test
	public void testGetAdiacenzeSenzaStanzeAdiacenti() {
		assertTrue(this.noAdiacenti.getAdiacenze().isEmpty());
	}

	/*
	 * Testo se nella stanza "tutteAdiacenti" il metodo getAdiacenze() ritorna
	 * l'array con le 4 direzioni corrispondenti alle 4 stanze adiacenti
	 */
	@Test
	public void testGetAdiacenzeCon4StanzeAdiacenti() {
		assertEquals(4, this.tutteAdiacenti.getAdiacenze().size());
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
	
	@Test
	public void testNoAttrezziDuplicati() {
		this.noDoppioni.addAttrezzo(this.attrezzi.get(0));
		this.noDoppioni.addAttrezzo(this.attrezzi.get(0));
		
		assertEquals(1, this.noDoppioni.getAttrezzi().size());
	}
}
