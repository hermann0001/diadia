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

public class ComandoPrendiTest {
	/*Aggiornamento hw3: non c'è più il vincolo di 10 attrezzi sulla borsa
	 * ma mantengo comunque la costante MAX_NUM_ATTREZZI per comodità
	 */
	static final private int MAX_NUM_ATTREZZI = 10; 
	private Stanza stanza;
	private Attrezzo attrezzi[];
	private Borsa borsa;
	private ComandoPrendi comando;
	private Partita partita;
	private IO io;

	@Before
	public void setUp() {
		this.io = new IOConsole();
		this.comando = new ComandoPrendi();
		this.stanza = new Stanza("Test");
		this.attrezzi = new Attrezzo[MAX_NUM_ATTREZZI];
		this.partita = new Partita(this.io);
		this.partita.setStanzaCorrente(this.stanza);
		this.borsa = this.partita.getGiocatore().getBorsa();
	}

	@Test
	public void testPrendiUnSoloAttrezzo() {
		this.attrezzi[0] = new Attrezzo("spada", 2);
		this.stanza.addAttrezzo(this.attrezzi[0]);
		this.comando.setParametro("spada");
		this.comando.esegui(this.partita);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}

	@Test
	public void testPrendiAttrezzoNullo() {
		this.attrezzi[0] = null;
		this.partita.setStanzaCorrente(this.stanza);
		this.comando.setParametro("spada");
		this.comando.esegui(this.partita);
		this.stanza.addAttrezzo(this.attrezzi[0]);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}

	@Test
	public void testPrendiAttrezzoBorsaPesante() {
		for (int i = 0; i < MAX_NUM_ATTREZZI; i++) {
			this.attrezzi[i] = new Attrezzo("Attrezzo" + (i + 1), 1);
			this.borsa.addAttrezzo(this.attrezzi[i]);
		}
		Attrezzo overflow = new Attrezzo("overflow", 10);
		this.partita.setStanzaCorrente(this.stanza);
		this.comando.setParametro("overflow");
		this.borsa.addAttrezzo(overflow);
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("overflow"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("overflow"));
	}
}
