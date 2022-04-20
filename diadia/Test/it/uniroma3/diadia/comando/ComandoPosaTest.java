package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosaTest {
	private static final int MAX_NUM_ATTREZZI = 10;
	private Stanza stanza;
	private Attrezzo attrezzi[];
	private ComandoPosa comando;
	private Partita partita;
	private IOConsole io;
	private Borsa borsa;

	@Before
	public void setUp() {
		this.io = new IOConsole();
		this.comando = new ComandoPosa();
		this.stanza = new Stanza("Test");
		this.attrezzi = new Attrezzo[MAX_NUM_ATTREZZI];
		this.partita = new Partita(this.io);
		this.partita.setStanzaCorrente(this.stanza);
		this.borsa = this.partita.getGiocatore().getBorsa();
	}

	@Test
	public void testPosaUnSoloAttrezzo() {
		this.attrezzi[0] = new Attrezzo("spada", 2);
		this.borsa.addAttrezzo(this.attrezzi[0]);
		this.comando.setParametro("spada");
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}
	
	@Test
	public void testPosaAttrezzoNullo() {
		this.attrezzi[0] = null;
		this.partita.setStanzaCorrente(this.stanza);
		this.comando.setParametro("spada");
		this.comando.esegui(this.partita);
		this.borsa.addAttrezzo(this.attrezzi[0]);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}
	
	@Test
	public void testPrendiAttrezzoBorsaPiena() {
		for(int i = 0; i < MAX_NUM_ATTREZZI; i++){
			this.attrezzi[i] = new Attrezzo("Attrezzo"+(i+1),0);
			this.stanza.addAttrezzo(this.attrezzi[i]);
			}
		Attrezzo overflow = new Attrezzo("overflow", 0);
		this.partita.setStanzaCorrente(this.stanza);
		this.comando.setParametro("overflow");
		this.stanza.addAttrezzo(overflow);
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("overflow"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("overflow"));
	}
}
