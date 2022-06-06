package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import static it.uniroma3.diadia.Direzione.*;

import org.junit.Test;

import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

public class LabirintoTest {

	private Labirinto labirintoTest;

	/* Testo solo i metodi getter, testando cosi implicitamente anche i setter */

	/*
	 * Testo se la stanza finale ritornata da getStanzaFinale() sia quella appena
	 * settata tramite il metodo setStanzaFinale()
	 */
	@Test
	public void testGetStanzaVincente() {
		this.labirintoTest = Labirinto.newBuilder()
				.addStanzaVincente("Test1")
				.getLabirinto();
		assertEquals("Test1", this.labirintoTest.getStanzaVincente().getNome());
	}

	/*
	 * Testo se la stanza iniziale ritornata da getStanzaIniziale() sia quella
	 * appena settata tramite il metodo setStanzaIniziale()
	 */
	@Test
	public void testGetStanzaIniziale() {
		this.labirintoTest = Labirinto.newBuilder()
				.addStanzaIniziale("Test1")
				.getLabirinto();
		assertEquals("Test1", this.labirintoTest.getStanzaIniziale().getNome());
	}

	@Test
	public void testMonolocale() {
		LabirintoBuilder costruttore = Labirinto.newBuilder();
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
				.addAdiacenze("salotto", NORD, "camera")
				.getLabirinto();

		assertEquals("salotto", this.labirintoTest.getStanzaIniziale().getNome());
		assertEquals("camera", this.labirintoTest.getStanzaVincente().getNome());
		assertEquals("letto", costruttore.getStanza("camera").getAttrezzo("letto").getNome());
		assertEquals("camera", costruttore.getStanza("salotto").getStanzaAdiacente(NORD).getNome());
	}

	@Test
	public void testTrilocale() {
		LabirintoBuilder costruttore = new LabirintoBuilder();
		this.labirintoTest = costruttore.addStanzaIniziale("salotto").addStanza("cucina").addAttrezzo("pentola", 1)
				.addStanzaVincente("camera").addAdiacenze("salotto", NORD, "cucina")
				.addAdiacenze("cucina", EST, "camera").getLabirinto();

		assertEquals("salotto", this.labirintoTest.getStanzaIniziale().getNome());
		assertEquals("cucina", costruttore.getStanza("cucina").getNome());
		assertEquals("pentola", costruttore.getStanza("cucina").getAttrezzo("pentola").getNome());
		assertEquals("camera", this.labirintoTest.getStanzaVincente().getNome());
		assertEquals("cucina", costruttore.getStanza("salotto").getStanzaAdiacente(NORD).getNome());
		assertEquals("camera", costruttore.getStanza("cucina").getStanzaAdiacente(EST).getNome());
	}
}
