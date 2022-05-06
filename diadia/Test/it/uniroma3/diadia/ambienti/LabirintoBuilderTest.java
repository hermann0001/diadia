package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LabirintoBuilderTest {
	private LabirintoBuilder costruttore;
	private Labirinto labCostruito;

	@Before
	public void setUp() {
		this.costruttore = new LabirintoBuilder();
	}

	/*
	 * test di addStanzaIniziale() passando come parametro il nome di una stanza non presente nel
	 * labirinto
	 */
	@Test
	public void testAddStanzaInizialeConStanzaNonEsistente() {
		String nomeStanzaIniziale = "Atrio";
		this.costruttore.addStanzaIniziale(nomeStanzaIniziale);
		this.labCostruito = this.costruttore.getLabirinto();
	
		assertEquals(nomeStanzaIniziale, this.labCostruito.getStanzaIniziale().getNome());
	}
	
	/*
	 * test di addStanzaIniziale() passando come parametro il nome di una stanza già presente nel
	 * labirinto
	 */
	@Test
	public void testAddStanzaInizialeConStanzaGiàEsistente() {
		String nomeStanzaIniziale = "Atrio";
		this.costruttore.addStanza(nomeStanzaIniziale);
		this.costruttore.addStanzaIniziale(nomeStanzaIniziale);
		this.labCostruito = this.costruttore.getLabirinto();
	
		assertEquals(nomeStanzaIniziale, this.labCostruito.getStanzaIniziale().getNome());
	}
	
	/*
	 * test di addStanzaIniziale() passando come parametro il nome non valido
	 */
	@Test
	public void testAddStanzaInizialeConNomeNonValido() {
		String nomeStanzaIniziale = "";
		this.costruttore.addStanza(nomeStanzaIniziale);
		this.costruttore.addStanzaIniziale(nomeStanzaIniziale);
		this.labCostruito = this.costruttore.getLabirinto();
	
		assertEquals(LabirintoBuilder.DEFAULT_NOME_NON_VALIDO, this.labCostruito.getStanzaIniziale().getNome());
	}

	/*
	 * test di addStanzaVincente() passando come parametro il nome di una stanza non esistente nel
	 * labirinto
	 */
	@Test
	public void testAddStanzaVincenteConStanzaNonEsistente() {
		String nomeStanzaVincente = "Atrio";
		this.costruttore.addStanzaVincente(nomeStanzaVincente);
		this.labCostruito = this.costruttore.getLabirinto();
	
		assertEquals(nomeStanzaVincente, this.labCostruito.getStanzaVincente().getNome());
	}
	
	/*
	 * test di addStanzaVincente() passando come parametro il nome di una stanza già esistente nel
	 * labirinto
	 */
	@Test
	public void testAddStanzaVincenteConStanzaEsistente() {
		String nomeStanzaVincente = "Atrio";
		this.costruttore.addStanza(nomeStanzaVincente);
		this.costruttore.addStanzaVincente(nomeStanzaVincente);
		this.labCostruito = this.costruttore.getLabirinto();
	
		assertEquals(nomeStanzaVincente, this.labCostruito.getStanzaVincente().getNome());
	}
	
	/*
	 * test di addStanzaIniziale() passando come parametro il nome non valido
	 */
	@Test
	public void testAddStanzaVincenteConNomeNonValido() {
		String nomeStanzaVincente = "";
		this.costruttore.addStanza(nomeStanzaVincente);
		this.costruttore.addStanzaVincente(nomeStanzaVincente);
		this.labCostruito = this.costruttore.getLabirinto();
	
		assertEquals(LabirintoBuilder.DEFAULT_NOME_NON_VALIDO, this.labCostruito.getStanzaVincente().getNome());
	}
	
	
	/*
	 * Testo il metodo addAdiacenze() usando i metodi offerte dalla classe stanza per verificarne
	 * il corretto funzionamento
	 */
	@Test
	public void testAddAdiacenze() {
		String nomeStanza1 = "s1";
		String nomeStanzaAdiacente = "adiacente";
		
		this.costruttore.addAdiacenze(nomeStanza1, "nord", nomeStanzaAdiacente);
		this.labCostruito = this.costruttore.getLabirinto();
		
		assertEquals(nomeStanzaAdiacente, this.costruttore.getStanza(nomeStanza1).getStanzaAdiacente("nord").getNome());
	}

	/*
	 * Testo se addAttrezzo non inserisca da nessuna parte l'attrezzo poiché non esiste 
	 * un'ultima stanza inserita. Mi aspetto una NullPointerException poiché chiamo getAttrezzo()
	 * su un null
	 */
	@Test(expected=NullPointerException.class)
	public void testAddAttrezzoNoStanze() {
		String nomeAttrezzo = "clava";
		this.costruttore.addAttrezzo(nomeAttrezzo, 1);
		
		assertNull(this.costruttore.getUltimaStanza());
		assertNull(this.costruttore.getUltimaStanza().getAttrezzo(nomeAttrezzo).getNome());
	}
	
	/*
	 * Testo se addAttrezzo() inserisca correttamente l'attrezzo nell'ultima stanza inserita
	 * testando cosi anche il metodo getUltimaStanza()
	 */
	@Test
	public void testAddAttrezzo() {
		String nomeAttrezzo = "clava";
		String nomeStanza = "Atrio";
		this.costruttore.addStanza(nomeStanza);
		this.costruttore.addAttrezzo(nomeAttrezzo, 1);
		
		assertEquals(nomeStanza, this.costruttore.getUltimaStanza().getNome());
		assertEquals(nomeAttrezzo, this.costruttore.getUltimaStanza().getAttrezzo(nomeAttrezzo).getNome());
	}
	
	/*
	 * Testo se addAttrezzo() inserisca correttamente l'attrezzo nell'ultima stanza inserita
	 * nel caso in cui inserisca più stanze
	 */
	@Test
	public void testAddAttrezzoConDueStanzeInserite() {
		String nomeAttrezzo = "clava";
		String nomeStanza1 = "Atrio";
		String nomeStanza2 = "Salotto";
		this.costruttore.addStanza(nomeStanza1);
		this.costruttore.addStanza(nomeStanza2);
		this.costruttore.addAttrezzo(nomeAttrezzo, 1);
		
		assertEquals(nomeStanza2, this.costruttore.getUltimaStanza().getNome());
		assertEquals(nomeAttrezzo, this.costruttore.getUltimaStanza().getAttrezzo(nomeAttrezzo).getNome());
	}
}
