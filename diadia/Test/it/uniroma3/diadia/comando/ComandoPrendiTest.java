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
	
	private Stanza stanza;
	private Borsa borsa;
	private AbstractComando comando;
	private Partita partita;
	private IO io;

	@Before
	public void setUp() {
		this.io = new IOConsole();
		this.comando = new ComandoPrendi();
		this.stanza = new Stanza("Test");
		this.partita = new Partita(this.io);
		this.partita.setStanzaCorrente(this.stanza);
		this.borsa = this.partita.getGiocatore().getBorsa();
	}

	@Test
	public void testPrendiUnSoloAttrezzo() {
		Attrezzo spada = new Attrezzo("spada", 2);
		this.stanza.addAttrezzo(spada);
		this.comando.setParametro("spada");
		this.comando.esegui(this.partita);
		assertTrue(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}

	@Test
	public void testPrendiAttrezzoNullo() {
		Attrezzo a = null;
		this.partita.setStanzaCorrente(this.stanza);
		this.comando.setParametro("spada");
		this.comando.esegui(this.partita);
		this.stanza.addAttrezzo(a);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("spada"));
		assertFalse(this.partita.getStanzaCorrente().hasAttrezzo("spada"));
	}

	@Test
	public void testPrendiAttrezzoBorsaPesante() {
		Attrezzo pesante = new Attrezzo("pesante", this.borsa.getPesoMax());
		assertEquals(0, this.borsa.getPeso());
		
		this.borsa.addAttrezzo(pesante);
		assertEquals(10, this.borsa.getPeso());
		
		Attrezzo overflow = new Attrezzo("overflow", 1);
		this.partita.setStanzaCorrente(this.stanza);
		this.stanza.addAttrezzo(overflow);
		
		this.comando.setParametro("overflow");
		this.comando.esegui(this.partita);
		assertFalse(this.partita.getGiocatore().getBorsa().hasAttrezzo("overflow"));
		assertTrue(this.partita.getStanzaCorrente().hasAttrezzo("overflow"));
	}
}
