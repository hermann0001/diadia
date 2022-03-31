package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {

	private Labirinto labirintoTest;
	private Stanza stanzaFinale;
	private Stanza stanzaIniziale;

	@Before
	public void setUp() {
		this.stanzaFinale = new Stanza("Test1");
		this.stanzaIniziale = new Stanza("Test2");
		this.labirintoTest = new Labirinto("MappaTest");
	}

	/* Testo solo i metodi getter, testando cosi implicitamente anche i setter */

	/*
	 * Testo se la stanza finale ritornata da getStanzaFinale() sia quella appena
	 * settata tramite il metodo setStanzaFinale()
	 */
	@Test
	public void testGetStanzaFinale() {
		this.labirintoTest.setStanzaFinale(this.stanzaFinale);
		assertEquals(this.stanzaFinale, this.labirintoTest.getStanzaFinale());
	}

	/*
	 * Testo se la stanza iniziale ritornata da getStanzaIniziale() sia quella
	 * appena settata tramite il metodo setStanzaIniziale()
	 */
	@Test
	public void testGetStanzaIniziale() {
		this.labirintoTest.setStanzaIniziale(this.stanzaIniziale);
		assertEquals(this.stanzaIniziale, this.labirintoTest.getStanzaIniziale());
	}

}
