package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandoFisarmonicaTest {
	final private String istruzioni[] = { "aiuto", "fine", "vai", "guarda", "posa", "prendi" };
	final private String direzioni[] = { "nord", "sud", "est", "ovest" };

	FabbricaDiComandoFisarmonica factory;
	Comando comandoDaEseguire;

	@Before
	public void setUp() {
		this.factory = new FabbricaDiComandoFisarmonica();
	}

	@Test
	public void testCostruisciComandoAiuto() {
		this.comandoDaEseguire = this.factory.costruisciComando(this.istruzioni[0]);
		assertEquals(this.istruzioni[0], this.factory.getNome());
	}

	@Test
	public void testCostruisciComandoFine() {
		this.comandoDaEseguire = this.factory.costruisciComando(this.istruzioni[1]);
		assertEquals(this.istruzioni[1], this.factory.getNome());
	}

	@Test
	public void testCostruisciComandoVai() {
		for (String direzione : this.direzioni) {
			this.comandoDaEseguire = this.factory.costruisciComando(this.istruzioni[2] + " " + direzione);
			assertEquals(this.istruzioni[2], this.factory.getNome());
			assertEquals(direzione, this.factory.getParametro());
		}
	}

	@Test
	public void testCostruisciComandoGuarda() {
		this.comandoDaEseguire = this.factory.costruisciComando(this.istruzioni[3]);
		assertEquals(this.istruzioni[3], this.factory.getNome());
	}

	@Test
	public void testCostruisciComandoPosa() {
		this.comandoDaEseguire = this.factory.costruisciComando(this.istruzioni[4] + " osso");
		assertEquals(this.istruzioni[4], this.factory.getNome());
		assertEquals("osso", this.factory.getParametro());
	}

	@Test
	public void testCostruisciComandoPrendi() {
		this.comandoDaEseguire = this.factory.costruisciComando(this.istruzioni[5] + " osso");
		assertEquals(this.istruzioni[5], this.factory.getNome());
		assertEquals("osso", this.factory.getParametro());
	}

}
