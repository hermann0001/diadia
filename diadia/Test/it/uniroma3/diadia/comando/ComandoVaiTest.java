package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVaiTest {

	private Stanza partenza;
	private ComandoVai vai;
	private Partita partita;
	private IO io;

	@Before
	public void setUp() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("partenza")
				.addAdiacenze("partenza", "nord", "destinazione")
				.getLabirinto();
		this.vai = new ComandoVai();
		this.io = new IOConsole();
		this.partita = new Partita(this.io, labirinto);
	}

	@Test
	public void testVaiDirezioneInesistente() {
		this.vai.setParametro("sud");
		this.vai.esegui(this.partita);
		assertEquals("partenza", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testVaiDirezioneEsistente() {		
		this.vai.setParametro("nord");
		this.vai.esegui(this.partita);
		assertEquals("destinazione", this.partita.getStanzaCorrente().getNome());
	}

	@Test
	public void testDirezioneSbagliata() {
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
