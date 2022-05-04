package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {

	private Borsa borsa1;
	private Borsa borsaPiena;
	private Borsa borsaVuota;
	private Borsa borsaPesante;
	private Attrezzo attrezzi[];
	private Borsa borsaSort;

	private Attrezzo attrezzoOverflow;
	private Attrezzo attrezzoPesante;

	@Before
	public void setUp() {
		this.borsa1 = new Borsa();
		this.borsaPiena = new Borsa(); // borsa con 10 attrezzi
		this.borsaVuota = new Borsa(); // borsa senza attrezzi
		this.borsaPesante = new Borsa(); // borsa con un peso totale di attrezzi pari a 10
		this.attrezzi = new Attrezzo[10];
		this.borsaSort = new Borsa(5000);

		/* creo 10 attrezzi di peso unitario */
		for (int i = 0; i < 10; i++)
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
	
	/*
	 * Test se il metodo getPeso() sulla borsa vuota sia effettivamente 0
	 */
	@Test
	public void testGetPesoBorsaVuota() {
		assertEquals(0, this.borsaVuota.getPeso());
	}
	
	/*
	 * Testo se il metodo getPeso() nella borsa piena (con peso max = 10) ritorni 10
	 */
	@Test
	public void testGetPesoBorsaPiena() {
		assertEquals(10, this.borsaPiena.getPeso());
	}
	
	/*
	 * Testo se il metodo getContenutoOrdinatoPerPeso() ritorni una lista ordinata
	 */
	@Test
	public void testGetContenutoOrdinatoPerPeso() {
		Attrezzo attrezzo1 = new Attrezzo("clava", 2);
		Attrezzo attrezzo2 = new Attrezzo("spada", 3);
		this.borsaSort.addAttrezzo(attrezzo1);
		this.borsaSort.addAttrezzo(attrezzo2);
		Collections.shuffle(this.borsaSort.getAttrezzi());

		assertEquals(Arrays.asList(attrezzo1, attrezzo2), this.borsaSort.getContenutoOrdinatoPerPeso());
	}

	/*
	 * Testo se il metodo getContenutoOrdinatoPerPeso() con attrezzi con lo stesso peso
	 * ma nome diverso, ritorni una lista ordinata 
	 */
	@Test
	public void testGetContenutoOrdinatoPerPesoConStessoPesoNomeDiverso() {
		Attrezzo attrezzo1 = new Attrezzo("clava", 2);
		Attrezzo attrezzoStessoPesoNomeDiverso = new Attrezzo("spada", 2);
		this.borsaSort.addAttrezzo(attrezzo1);
		this.borsaSort.addAttrezzo(attrezzoStessoPesoNomeDiverso);
		Collections.shuffle(this.borsaSort.getAttrezzi());

		assertEquals(Arrays.asList(attrezzo1, attrezzoStessoPesoNomeDiverso),
				this.borsaSort.getContenutoOrdinatoPerPeso());
	}

	/*
	 * Testo se il metodo getContenutoOrdinatoPerPeso() con attrezzi con lo stesso peso
	 * e stesso nome, ritorni una lista ordinata 
	 */
	@Test
	public void testGetContenutoOrdinatoPerPesoConStessoPesoStessoNome() {
		Attrezzo attrezzo1 = new Attrezzo("clava", 2);
		Attrezzo attrezzo2 = new Attrezzo("clava", 2);
		this.borsaSort.addAttrezzo(attrezzo1);
		this.borsaSort.addAttrezzo(attrezzo2);
		Collections.shuffle(this.borsaSort.getAttrezzi());

		assertEquals(Arrays.asList(attrezzo1, attrezzo2), this.borsaSort.getContenutoOrdinatoPerPeso());
	}

	/*
	 * Testo se il metodo getContenutoOrdinatoPerPeso() su un singoletto, ritorni una lista
	 * contenente solo quell'elemento
	 */
	@Test
	public void testGetContenutoOrdinatoSingleton() {
		Attrezzo a1 = new Attrezzo("clava", 2);
		this.borsaSort.addAttrezzo(a1);

		assertEquals(Arrays.asList(a1), this.borsaSort.getContenutoOrdinatoPerPeso());
	}
	
	/*
	 * Testo se il metodo getContenutoOrdinatoPerPeso() su una lista vuota ritorni una
	 * lista vuota
	 */
	@Test
	public void testGetContenutoOrdinatoListaVuota() {
		assertTrue(this.borsaSort.getAttrezzi().isEmpty());
		assertEquals(Arrays.asList(), this.borsaSort.getContenutoOrdinatoPerPeso());
	}
	
	/*
	 * Testo se il metodo getContenutoOrdinatoPerPeso() su un caso
	 * più esteso con più attrezzi differenti ritorni una lista ordinata
	 */
	@Test
	public void testGetContenutoOrdinatoPerPesoCasoEsteso() {
		Attrezzo attrezzo1 = new Attrezzo("clava", 2);	//2
		Attrezzo attrezzo2 = new Attrezzo("spada", 3);	//4
		Attrezzo attrezzo3 = new Attrezzo("spranga", 3);//5
		Attrezzo attrezzo4 = new Attrezzo("osso", 5);	//6
		Attrezzo attrezzo5 = new Attrezzo("spada", 2);	//3
		Attrezzo attrezzo6 = new Attrezzo("chiave", 1);	//1
		Attrezzo attrezzo7 = new Attrezzo("lanterna", 8);//7
		this.borsaSort.addAttrezzo(attrezzo1);
		this.borsaSort.addAttrezzo(attrezzo2);
		this.borsaSort.addAttrezzo(attrezzo3);
		this.borsaSort.addAttrezzo(attrezzo4);
		this.borsaSort.addAttrezzo(attrezzo5);
		this.borsaSort.addAttrezzo(attrezzo6);
		this.borsaSort.addAttrezzo(attrezzo7);
		Collections.shuffle(this.borsaSort.getAttrezzi());

		assertEquals(Arrays.asList(attrezzo6, attrezzo1, attrezzo5, attrezzo2, attrezzo3, 
				attrezzo4, attrezzo7), this.borsaSort.getContenutoOrdinatoPerPeso());
	}
}