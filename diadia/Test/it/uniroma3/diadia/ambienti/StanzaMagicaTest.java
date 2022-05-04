package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaTest {

	private static final int SOGLIA_MAGICA = 1;

	/*
	 * Stanze per il test dei metodi riguardanti gli attrezzi
	 */
	private StanzaMagica comportamentoMagico;

	private Attrezzo chiave;
	private Attrezzo attrezzoOverflow;

	@Before
	public void setUpAttrezzi() {
		this.comportamentoMagico = new StanzaMagica("test", SOGLIA_MAGICA);
		this.chiave = new Attrezzo("chiave", 1);
		this.attrezzoOverflow = new Attrezzo("mazza", 5);
	}

	@Test
	public void testComportamentoMagico() {
		this.comportamentoMagico.addAttrezzo(chiave);
		this.comportamentoMagico.addAttrezzo(this.attrezzoOverflow);
		String nomeExpected = "azzam";
		int pesoExpected = this.attrezzoOverflow.getPeso() * 2;
		assertEquals("chiave", this.comportamentoMagico.getAttrezzo("chiave").getNome());
		assertEquals(1, this.comportamentoMagico.getAttrezzo("chiave").getPeso());
		assertEquals(nomeExpected, this.comportamentoMagico.getAttrezzo(nomeExpected).getNome());
		assertEquals(pesoExpected, this.comportamentoMagico.getAttrezzo(nomeExpected).getPeso());

	}

	@Test
	public void testNessunaReazione() {
		this.comportamentoMagico.addAttrezzo(chiave);
		String nomeExpected = "chiave";
		int pesoExpected = this.chiave.getPeso();
		assertEquals(nomeExpected, this.comportamentoMagico.getAttrezzo(nomeExpected).getNome());
		assertEquals(pesoExpected, this.comportamentoMagico.getAttrezzo(nomeExpected).getPeso());
	}

}
