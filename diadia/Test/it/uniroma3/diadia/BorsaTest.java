package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BorsaTest {

	private Borsa borsa1;
	private Borsa borsaPiena;
	private Borsa borsaVuota;
	private Borsa borsaPesante;
	private Attrezzo attrezzo1;
	private Attrezzo attrezzo2;
	private Attrezzo attrezzo3;
	private Attrezzo attrezzo4;
	private Attrezzo attrezzo5;
	private Attrezzo attrezzo6;
	private Attrezzo attrezzo7;
	private Attrezzo attrezzo8;
	private Attrezzo attrezzo9;
	private Attrezzo attrezzo10;
	private Attrezzo attrezzo11;
	private Attrezzo attrezzoPesante;

	@Before
	public void setUp() {
		borsa1 = new Borsa();
		borsaPiena = new Borsa(); //borsa con 10 attrezzi
		borsaVuota = new Borsa(); // borsa senza attrezzi
		borsaPesante = new Borsa(); //borsa con un peso totale di attrezzi pari a 10

		attrezzo1 = new Attrezzo("attrezzo1", 1);
		attrezzo2 = new Attrezzo("attrezzo2", 0);
		attrezzo3 = new Attrezzo("attrezzo3", 1);
		attrezzo4 = new Attrezzo("attrezzo4", 1);
		attrezzo5 = new Attrezzo("attrezzo5", 1);
		attrezzo6 = new Attrezzo("attrezzo6", 1);
		attrezzo7 = new Attrezzo("attrezzo7", 1);
		attrezzo8 = new Attrezzo("attrezzo8", 1);
		attrezzo9 = new Attrezzo("attrezzo9", 1);
		attrezzo10 = new Attrezzo("attrezzo10", 1);
		attrezzo11 = new Attrezzo("attrezzo11", 1);
		
		attrezzoPesante = new Attrezzo("attrezzoPesante", 11);
		
		borsa1.addAttrezzo(attrezzo1);
		
		borsaPiena.addAttrezzo(attrezzo1);
		borsaPiena.addAttrezzo(attrezzo2);
		borsaPiena.addAttrezzo(attrezzo3);
		borsaPiena.addAttrezzo(attrezzo4);
		borsaPiena.addAttrezzo(attrezzo5);
		borsaPiena.addAttrezzo(attrezzo6);
		borsaPiena.addAttrezzo(attrezzo7);
		borsaPiena.addAttrezzo(attrezzo8);
		borsaPiena.addAttrezzo(attrezzo9);
		borsaPiena.addAttrezzo(attrezzo10);
	
	}

	/*
	 * Testo se il metodo removeAttrezzo() ritorni correttamente l'attrezzo che si
	 * voleva rimuovere
	 */
	@Test
	public void testOutputRemoveAttrezzo() {
		assertEquals(attrezzo1, borsa1.removeAttrezzo("attrezzo1"));
	}
	/*
	 * Testo se l'attrezzo appena rimosso sia correttamente cancellato anche
	 * dall'array di attrezzi
	 */

	@Test
	public void testRemoveAttrezzoDaArrayAttrezzi() {
		borsa1.removeAttrezzo("attrezzo1");
		assertEquals(false, borsa1.hasAttrezzo("attrezzo1"));
	}

	/*
	 * Test di removeAttrezzo sulla borsaVuota, il valore aspettato è null
	 */
	@Test
	public void testRemoveAttrezzoBorsaVuota() {
		assertNull(borsaVuota.removeAttrezzo("attrezzo1"));
	}
	
	/*
	 * Testo se addAttrezzo non mi faccia aggiungere un attrezzo in più 
	 * rispetto al limite totale di 10 attrezzi
	 */
	@Test
	public void testAddAttrezzoSuNumeroMaxAttrezzi() {
		assertFalse(borsaPiena.addAttrezzo(attrezzo11));
	}
	
	/*
	 * Testo se all'aggiunta di un oggetto con peso maggiore del peso max = 10
	 * ricevo false
	 */
	@Test
	public void testAddAttrezzoSuMaxPeso() {
		assertFalse(borsaPesante.addAttrezzo(attrezzoPesante));
	}
	
	@Test
	public void testGetPesoBorsaVuota() {
		assertEquals(0, borsaVuota.getPeso());
	}
	
	@Test
	public void testGetPesoBorsaPiena() {
		assertEquals(9, borsaPiena.getPeso());
	}
}
