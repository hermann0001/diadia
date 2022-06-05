package it.uniroma3.diadia.personaggi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class AbstractPersonaggioTest {
	
	private static final String NOME = "Fake";
	private static final String DESCRIZIONE = "Sono qui solo per il testing...";
	private AbstractPersonaggio fake;
	private Partita partita;
	

	@Before
	public void setUp() throws Exception {
		this.fake = new FakePersonaggio(NOME, DESCRIZIONE);
		this.partita = new Partita(new IOConsole());
	}

	@Test
	public void testHaSalutato() {
		assertFalse(this.fake.haSalutato());
		this.fake.saluta();
		assertTrue(this.fake.haSalutato());
	}
	
	@Test
	public void testAgisci() {
		assertEquals("done", this.fake.agisci(this.partita));
	}
	
	@Test
	public void testRiceviRegalo() {
		assertEquals("ricevuto", this.fake.riceviRegalo(new Attrezzo("test", 1),this.partita));
	}
	
	@Test
	public void testToString() {
		assertEquals(NOME + "\n" + DESCRIZIONE, this.fake.toString());
	}
}
