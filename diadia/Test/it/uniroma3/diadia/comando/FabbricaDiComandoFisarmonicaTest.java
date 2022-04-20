package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandoFisarmonicaTest {
	static final private String ISTRUZIONI[] = { "aiuto", "fine", "vai", "guarda", "posa", "prendi" };
	static final private String DIREZIONI[] = { "nord", "sud", "est", "ovest" };

	FabbricaDiComandoFisarmonica factory;
	Comando comandoDaEseguire;

	@Before
	public void setUp() {
		this.factory = new FabbricaDiComandoFisarmonica();
	}

	@Test
	public void testCostruisciComandoAiuto() {
		this.comandoDaEseguire = this.factory.costruisciComando(this.ISTRUZIONI[0]);
		assertEquals(this.ISTRUZIONI[0], this.factory.getNome());
	}

	@Test
	public void testCostruisciComandoFine() {
		this.comandoDaEseguire = this.factory.costruisciComando(this.ISTRUZIONI[1]);
		assertEquals(this.ISTRUZIONI[1], this.factory.getNome());
	}

	@Test
	public void testCostruisciComandoVai() {
		for (String direzione : this.DIREZIONI) {
			this.comandoDaEseguire = this.factory.costruisciComando(this.ISTRUZIONI[2] + " " + direzione);
			assertEquals(this.ISTRUZIONI[2], this.factory.getNome());
			assertEquals(direzione, this.factory.getParametro());
		}
	}

	@Test
	public void testCostruisciComandoGuarda() {
		this.comandoDaEseguire = this.factory.costruisciComando(this.ISTRUZIONI[3]);
		assertEquals(this.ISTRUZIONI[3], this.factory.getNome());
	}

	@Test
	public void testCostruisciComandoPosa() {
		this.comandoDaEseguire = this.factory.costruisciComando(this.ISTRUZIONI[4] + " osso");
		assertEquals(this.ISTRUZIONI[4], this.factory.getNome());
		assertEquals("osso", this.factory.getParametro());
	}

	@Test
	public void testCostruisciComandoPrendi() {
		this.comandoDaEseguire = this.factory.costruisciComando(this.ISTRUZIONI[5] + " osso");
		assertEquals(this.ISTRUZIONI[5], this.factory.getNome());
		assertEquals("osso", this.factory.getParametro());
	}

}
