package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoVaiTest {
	
	private Stanza partenza;
	private ComandoVai comando;
	private Partita partita;
	private IO io;

	@Before
	public void setUp() {
		this.comando = new ComandoVai();
		this.io = new IOConsole();
		this.partita = new Partita(this.io);
		this.partenza = new Stanza("partenza");
		this.partita.setStanzaCorrente(this.partenza);
	}

	@Test
	public void testVaiDirezioneInesistente() {
		this.comando.setParametro("nord");
		this.comando.esegui(this.partita);
		assertEquals(this.partenza, this.partita.getStanzaCorrente());
	}
	
	@Test
	public void testVaiDirezioneEsistente() {
		Stanza destinazione = new Stanza("destinazione");
		this.partenza.impostaStanzaAdiacente("nord", destinazione);
		this.comando.setParametro("nord");
		this.comando.esegui(this.partita);
		assertEquals(destinazione, this.partita.getStanzaCorrente());
	}
	
	@Test
	public void testVaiDirezioneSbagliata() {
		Stanza destinazione = new Stanza("destinazione");
		this.partenza.impostaStanzaAdiacente("nord", destinazione);
		this.comando.setParametro("est");
		this.comando.esegui(this.partita);
		assertEquals(this.partenza, this.partita.getStanzaCorrente());
	}

}
