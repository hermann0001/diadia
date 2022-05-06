package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoTest {

	private Labirinto labirintoTest;
	private Stanza stanzaFinale;
	private Stanza stanzaIniziale;

	/* Testo solo i metodi getter, testando cosi implicitamente anche i setter */

	/*
	 * Testo se la stanza finale ritornata da getStanzaFinale() sia quella appena
	 * settata tramite il metodo setStanzaFinale()
	 */
	@Test
	public void testGetStanzaVincente() {
		this.labirintoTest = new LabirintoBuilder().addStanzaVincente("Test1").getLabirinto();
		assertEquals("Test1", this.labirintoTest.getStanzaVincente().getNome());
	}

	/*
	 * Testo se la stanza iniziale ritornata da getStanzaIniziale() sia quella
	 * appena settata tramite il metodo setStanzaIniziale()
	 */
	@Test
	public void testGetStanzaIniziale() {
		this.labirintoTest = new LabirintoBuilder().addStanzaIniziale("Test1").getLabirinto();
		assertEquals("Test1", this.labirintoTest.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testMonolocale() {
		LabirintoBuilder costruttore = new LabirintoBuilder();
		this.labirintoTest = costruttore
				.addStanzaIniziale("ingresso")
				.addStanzaVincente("ingresso")
				.getLabirinto();
		
		assertEquals("ingresso", this.labirintoTest.getStanzaIniziale().getNome());
		assertEquals("ingresso", this.labirintoTest.getStanzaVincente().getNome());
	}
	
	@Test
	public void testBilocale() {
		LabirintoBuilder costruttore = new LabirintoBuilder();
		this.labirintoTest = costruttore
				.addStanzaIniziale("salotto")
				.addStanzaVincente("camera")
				.addAttrezzo("letto", 10)
				.addAdiacenze("salotto", "nord", "camera")
				.getLabirinto();
		
		assertEquals("salotto", this.labirintoTest.getStanzaIniziale().getNome());
		assertEquals("camera", this.labirintoTest.getStanzaVincente().getNome());
		assertEquals("letto", costruttore.getStanza("camera").getAttrezzo("letto").getNome());
		assertEquals("camera", costruttore.getStanza("salotto").getStanzaAdiacente("nord").getNome());
	}
	
	@Test
	public void testTrilocale() {
		LabirintoBuilder costruttore = new LabirintoBuilder();
		this.labirintoTest = costruttore
				.addStanzaIniziale("salotto")
				.addStanza("cucina")
				.addAttrezzo("pentola", 1)
				.addStanzaVincente("camera")
				.addAdiacenze("salotto", "nord", "cucina")
				.addAdiacenze("cucina", "est", "camera")
				.getLabirinto();
		
		assertEquals("salotto", this.labirintoTest.getStanzaIniziale().getNome());
		assertEquals("cucina", costruttore.getStanza("cucina").getNome());
		assertEquals("pentola", costruttore.getStanza("cucina").getAttrezzo("pentola").getNome());
		assertEquals("camera", this.labirintoTest.getStanzaVincente().getNome());
		assertEquals("cucina", costruttore.getStanza("salotto").getStanzaAdiacente("nord").getNome());
		assertEquals("camera", costruttore.getStanza("cucina").getStanzaAdiacente("est").getNome());
	}
}
