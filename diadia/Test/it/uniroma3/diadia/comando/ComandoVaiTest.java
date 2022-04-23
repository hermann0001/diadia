package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.fixture.Fixture;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoVaiTest {

	private Stanza partenza;
	private ComandoVai vai;
	private Partita partita;
	private IO io;

	@Before
	public void setUp() {
		this.vai = new ComandoVai();
		this.io = new IOConsole();
		this.partita = new Partita(this.io);
		this.partenza = new Stanza("partenza");
		this.partita.setStanzaCorrente(this.partenza);
	}

	@Test
	public void testVaiDirezioneInesistente() {
		this.vai.setParametro("nord");
		this.vai.esegui(this.partita);
		assertEquals(this.partenza, this.partita.getStanzaCorrente());
	}

	@Test
	public void testVaiDirezioneEsistente() {
		Stanza destinazione = new Stanza("destinazione");
		this.partenza.impostaStanzaAdiacente("nord", destinazione);
		this.vai.setParametro("nord");
		this.vai.esegui(this.partita);
		assertEquals(destinazione, this.partita.getStanzaCorrente());
	}

	@Test
	public void testVaiStanzaInesistente() {
		Stanza destinazione = new Stanza("destinazione");
		this.partenza.impostaStanzaAdiacente("nord", destinazione);
		this.vai.setParametro("est");
		this.vai.esegui(this.partita);
		assertEquals(this.partenza, this.partita.getStanzaCorrente());
	}

	@Test
	public void testDirezioneIsSbagliata() {
		this.vai.setParametro("sinistra");
		this.vai.esegui(this.partita);
		assertFalse(this.vai.direzioneIsCorretta());
	}

	@Test
	public void testDirezioneIsCorretta() {
		this.vai.setParametro("est");
		this.vai.esegui(this.partita);
		assertTrue(this.vai.direzioneIsCorretta());
	}

}
