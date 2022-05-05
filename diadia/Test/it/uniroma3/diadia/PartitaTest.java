package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;

public class PartitaTest {

	private Partita partita;
	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	private IO io;

	@Before
	public void setUp() {
		this.io = new IOConsole();
		this.partita = new Partita(io);
		this.stanzaCorrente = new Stanza("stanzaCorrente");
		this.stanzaVincente = new Stanza("stanzaVincente");
	}

	/* Testo solo i metodi getter, testando cosi implicitamente anche i setter */

	/*
	 * Testo se la stanza corrente ritornata da getStanzaCorrente() sia quella
	 * appena settata tramite il metodo setStanzaCorrente()
	 */
	@Test
	public void testGetStanzaCorrente() {
		this.partita.setStanzaCorrente(stanzaCorrente);
		assertEquals(stanzaCorrente, this.partita.getStanzaCorrente());
	}

	/*
	 * Testo se il metodo vinta() ritorni true al momento in cui la stanza corrente
	 * coincide con la stanza finale
	 */
	@Test
	public void testVinta() {
		this.partita.setStanzaCorrente(stanzaVincente);
		this.partita.getLabirinto().setStanzaVincente(stanzaVincente);
		assertTrue(this.partita.vinta());
	}

	/*
	 * Testo se il metodo vinta() ritorni false al momento in cui la stanza corrente
	 * non coincide con la stanza finale
	 */
	@Test
	public void testPersa() {
		this.partita.setStanzaCorrente(stanzaCorrente);
		this.partita.getLabirinto().setStanzaVincente(stanzaVincente);
		assertFalse(this.partita.vinta());
	}

	/*
	 * Testo se il metodo vinta() ritorni false al momento in cui la stanza corrente
	 * e la stanza finale sono due valori null
	 */
	@Test
	public void testVintaSenzaStanze() {
		this.partita.setStanzaCorrente(null);
		this.partita.getLabirinto().setStanzaVincente(null);
		assertFalse(this.partita.vinta());
	}

}
