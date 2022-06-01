package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandoFisarmonicaTest {
	private final static String ISTRUZIONI[] = { "aiuto", "fine", "vai", "guarda", "posa", "prendi" };
	private final static String DIREZIONI[] = { "nord", "sud", "est", "ovest" };

	FabbricaDiComandoFisarmonica factory;
	Comando comandoDaEseguire;

	@Before
	public void setUp() {
		this.factory = new FabbricaDiComandoFisarmonica();
	}

	@Test
	public void testCostruisciComandoAiuto() {
		this.comandoDaEseguire = this.factory.costruisciComando(ISTRUZIONI[0]);
		assertEquals(ISTRUZIONI[0], this.factory.getNome());
	}

	@Test
	public void testCostruisciComandoFine() {
		this.comandoDaEseguire = this.factory.costruisciComando(ISTRUZIONI[1]);
		assertEquals(ISTRUZIONI[1], this.factory.getNome());
	}

	@Test
	public void testCostruisciComandoVai() {
		for (String direzione : DIREZIONI) {
			this.comandoDaEseguire = this.factory.costruisciComando(ISTRUZIONI[2] + " " + direzione);
			assertEquals(ISTRUZIONI[2], this.factory.getNome());
			assertEquals(direzione, this.factory.getParametro());
		}
	}

	@Test
	public void testCostruisciComandoGuarda() {
		this.comandoDaEseguire = this.factory.costruisciComando(ISTRUZIONI[3]);
		assertEquals(ISTRUZIONI[3], this.factory.getNome());
	}

	@Test
	public void testCostruisciComandoPosa() {
		this.comandoDaEseguire = this.factory.costruisciComando(ISTRUZIONI[4] + " osso");
		assertEquals(ISTRUZIONI[4], this.factory.getNome());
		assertEquals("osso", this.factory.getParametro());
	}

	@Test
	public void testCostruisciComandoPrendi() {
		this.comandoDaEseguire = this.factory.costruisciComando(ISTRUZIONI[5] + " osso");
		assertEquals(ISTRUZIONI[5], this.factory.getNome());
		assertEquals("osso", this.factory.getParametro());
	}

}
