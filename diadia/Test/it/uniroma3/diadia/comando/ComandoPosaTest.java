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
	private AbstractComando posa;
	private Partita partita;
	private IO io;
	private Borsa borsa;

	@Before
	public void setUp() {
		this.io = new IOConsole();
		this.posa = new ComandoPosa();
		this.stanza = new Stanza("Test");
		this.partita = new Partita(this.io);
		this.partita.setStanzaCorrente(this.stanza);
		this.borsa = this.partita.getGiocatore().getBorsa();
	}

	@Test
	public void testPosaUnSoloAttrezzo() {
		this.attrezzo = new Attrezzo("spada", 2);
		this.borsa.addAttrezzo(this.attrezzo);
		this.posa.setParametro("spada");
		this.posa.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}

	@Test
	public void testPosaAttrezzoNullo() {
		this.attrezzo = null;
		this.posa.setParametro("spada");
		this.posa.esegui(this.partita);
		this.borsa.addAttrezzo(this.attrezzo);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}

	@Test
	public void testPrendiAttrezzoBorsaPiena() {
		this.attrezzo = new Attrezzo("martello", MAX_PESO + 1);
		this.posa.setParametro("martello");
		this.borsa.addAttrezzo(attrezzo);
		this.posa.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("martello"));
	}
}
