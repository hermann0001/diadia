package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;
import static it.uniroma3.diadia.Direzione.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;

public class ComandoVaiTest {

	private AbstractComando vai;
	private Partita partita;
	private IO io;

	@Before
	public void setUp() {
		Labirinto labirinto = Labirinto.newBuilder().addStanzaIniziale("partenza")
				.addAdiacenze("partenza", NORD, "destinazione").getLabirinto();
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
}
