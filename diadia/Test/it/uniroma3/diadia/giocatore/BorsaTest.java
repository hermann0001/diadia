package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	static final private int NUMERO_MAX_ATTREZZI = 10;

	private Borsa borsa1;
	private Borsa borsaPiena;
	private Borsa borsaVuota;
	private Borsa borsaPesante;
	private Attrezzo attrezzi[];

	private Attrezzo attrezzoOverflow;
	private Attrezzo attrezzoPesante;

	@Before
	public void setUp() {
		this.borsa1 = new Borsa();
		this.borsaPiena = new Borsa(); // borsa con 10 attrezzi
		this.borsaVuota = new Borsa(); // borsa senza attrezzi
		this.borsaPesante = new Borsa(); // borsa con un peso totale di attrezzi pari a 10
		this.attrezzi = new Attrezzo[NUMERO_MAX_ATTREZZI];

		/* creo 10 attrezzi di peso unitario */
		for (int i = 0; i < NUMERO_MAX_ATTREZZI; i++)
			this.attrezzi[i] = new Attrezzo("attrezzo" + i, 1);

		this.attrezzoOverflow = new Attrezzo("attrezzo10", 1);
		this.attrezzoPesante = new Attrezzo("attrezzoPesante", 11);

		this.borsa1.addAttrezzo(this.attrezzi[0]);
		this.borsa1.addAttrezzo(this.attrezzi[1]);
		this.borsa1.addAttrezzo(this.attrezzi[2]);

		for (Attrezzo attrezzo : this.attrezzi)
			this.borsaPiena.addAttrezzo(attrezzo);
	}

	/*
	 * Testo se il metodo removeAttrezzo() ,sull'oggetto "borsa1" che attualmente ha
	 * un 3 attrezzi (attrezzo 1/2/3), ritorni correttamente l'attrezzo che si
	 * voleva rimuovere
	 */
	@Test
	public void testOutputRemoveAttrezziDaBorsa1() {
		assertEquals(this.attrezzi[0], this.borsa1.removeAttrezzo("attrezzo0"));
		assertEquals(this.attrezzi[1], this.borsa1.removeAttrezzo("attrezzo1"));
		assertEquals(this.attrezzi[2], this.borsa1.removeAttrezzo("attrezzo2"));
	}

	/*
	 * Testo se l'attrezzo appena rimosso sia correttamente cancellato anche
	 * dall'array di attrezzi
	 */

	@Test
	public void testRemoveAttrezzoDaArrayAttrezzi() {
		this.borsa1.removeAttrezzo("attrezzo0");
		assertEquals(false, borsa1.hasAttrezzo("attrezzo0"));
	}

	/*
	 * Test di removeAttrezzo sulla borsaVuota, il valore aspettato è null
	 */
	@Test
	public void testRemoveAttrezzoBorsaVuota() {
		assertNull(this.borsaVuota.removeAttrezzo("attrezzo0"));
	}

	/*
	 * Testo se addAttrezzo mi impedisca di aggiungere un attrezzo in più rispetto
	 * al limite totale di 10 attrezzi
	 */
	@Test
	public void testAddAttrezzoSuNumeroMaxAttrezzi() {
		assertFalse(this.borsaPiena.addAttrezzo(this.attrezzoOverflow));
	}

	/*
	 * Testo se all'aggiunta di un oggetto con peso maggiore del peso max = 10
	 * ricevo false
	 */
	@Test
	public void testAddAttrezzoSuMaxPeso() {
		assertFalse(this.borsaPesante.addAttrezzo(this.attrezzoPesante));
	}

	@Test
	public void testGetPesoBorsaVuota() {
		assertEquals(0, this.borsaVuota.getPeso());
	}

	@Test
	public void testGetPesoBorsaPiena() {
		assertEquals(10, this.borsaPiena.getPeso());
	}
}
