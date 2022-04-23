package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosaTest {
	static final private int MAX_PESO = 10;
	private Stanza stanza;
	private Attrezzo attrezzo;
	private ComandoPosa comando;
	private Partita partita;
	private IO io;
	private Borsa borsa;

	@Before
	public void setUp() {
		this.io = new IOConsole();
		this.comando = new ComandoPosa();
		this.stanza = new Stanza("Test");
		this.partita = new Partita(this.io);
		this.partita.setStanzaCorrente(this.stanza);
		this.borsa = this.partita.getGiocatore().getBorsa();
	}

	@Test
	public void testPosaUnSoloAttrezzo() {
		this.attrezzo = new Attrezzo("spada", 2);
		this.borsa.addAttrezzo(this.attrezzo);
		this.comando.setParametro("spada");
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}

	@Test
	public void testPosaAttrezzoNullo() {
		this.attrezzo = null;
		this.comando.setParametro("spada");
		this.comando.esegui(this.partita);
		this.borsa.addAttrezzo(this.attrezzo);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}

	@Test
	public void testPrendiAttrezzoBorsaPiena() {
		this.attrezzo = new Attrezzo("martello", MAX_PESO + 1);
		this.comando.setParametro("martello");
		this.borsa.addAttrezzo(attrezzo);
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("martello"));
	}
}
