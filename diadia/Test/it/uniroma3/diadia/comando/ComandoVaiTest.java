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
	public void testVaiDirezioneSbagliata() {
		Stanza destinazione = new Stanza("destinazione");
		this.partenza.impostaStanzaAdiacente("nord", destinazione);
		this.vai.setParametro("est");
		this.vai.esegui(this.partita);
		assertEquals(this.partenza, this.partita.getStanzaCorrente());
	}
	
	@Test
	public void testPartitaAutomaticaComandoVai() {
		String comandi[] = {"vai sud", "fine"};
		Fixture fixture = new Fixture();
		IOSimulator io = fixture.creaSimulazionePartitaEGioca(comandi);
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertContains("Aula N10", io.nextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
	}
	
	public void assertContains(String expected, String interaRiga) {
		assertTrue(interaRiga.contains(expected));
	}
}
