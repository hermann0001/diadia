package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comando.ComandoAiuto;
import it.uniroma3.diadia.comando.ComandoFine;
import it.uniroma3.diadia.comando.ComandoNonValido;
import it.uniroma3.diadia.comando.ComandoPrendi;
import it.uniroma3.diadia.fixture.Fixture;
import it.uniroma3.diadia.giocatore.Borsa;

public class IOSimulatorTest {
	
	private Fixture simulazione;
	private DiaDia session;
	
	@Before
	public void setUp() {
		this.simulazione = new Fixture();
	}
//
//	@Test
//	public void testSimulazioneComandoFine() {
//		String righeDaLeggere[] = { "fine" };
//		IOSimulator io = this.simulazione.creaSimulazionePartitaEGioca(righeDaLeggere);
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
//		assertFalse(io.hasNextMessaggio());
//	}
//
//	@Test
//	public void testSimulazioneComandoVai() {
//		String comandi[] = { "vai sud", "fine" };
//		IOSimulator io = this.simulazione.creaSimulazionePartitaEGioca(comandi);
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
//		assertTrue(io.hasNextMessaggio());
//		assertContains("Aula N10", io.nextMessaggio());
//		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
//		assertFalse(io.hasNextMessaggio());
//	}
//
//	public void assertContains(String expected, String interaRiga) {
//		assertTrue(interaRiga.contains(expected));
//	}
//
//	@Test
//	public void testSimulazioneComandoAiuto() {
//		String righeDaLeggere[] = { "aiuto", "fine" };
//		IOSimulator io = this.simulazione.creaSimulazionePartitaEGioca(righeDaLeggere);
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
//		for (int i = 0; i < ComandoAiuto.ELENCO_COMANDI.length; i++) {
//			assertTrue(io.hasNextMessaggio());
//			assertEquals(ComandoAiuto.ELENCO_COMANDI[i]+" ", io.nextMessaggio());
//		}
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
//		assertFalse(io.hasNextMessaggio());
//	}
//	
//	@Test
//	public void testSimulazioneComandoNonValido() {
//		String righeDaLeggere[] = {"sbagliato", "fine"};
//		IOSimulator io = this.simulazione.creaSimulazionePartitaEGioca(righeDaLeggere);
//		
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(ComandoNonValido.COMANDO_NON_VALIDO, io.nextMessaggio());
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
//		assertFalse(io.hasNextMessaggio());
//	}
//	
//	@Test
//	public void testSimulazioneComandoGuarda() {
//		String righeDaLeggere[] = {"guarda", "fine"};
//		IOSimulator io = this.simulazione.creaSimulazionePartitaEGioca(righeDaLeggere);
//		this.session = this.simulazione.getSession();
//		Partita partita = this.session.getPartita();
//		String outputStanza = partita.getStanzaCorrente().getDescrizione();
//		String nomeMappa = partita.getLabirinto().getNome();
//		String nomeGiocatore = partita.getGiocatore().getNome();
//		int cfu = partita.getGiocatore().getCfu();
//		
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(outputStanza, io.nextMessaggio());
//		assertTrue(io.hasNextMessaggio());
//		assertEquals("Mappa:" + nomeMappa, io.nextMessaggio());
//		assertTrue(io.hasNextMessaggio());
//		assertEquals("Giocatore:" + nomeGiocatore, io.nextMessaggio());
//		assertTrue(io.hasNextMessaggio());
//		assertEquals("CFU:" + cfu, io.nextMessaggio());
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
//		assertFalse(io.hasNextMessaggio());
//	}
//	
//	@Test
//	public void testSimulazioneComandoPrendiConAttrezzoNellaStanza() {
//		String righeDaLeggere[] = {"prendi osso", "fine"};
//		IOSimulator io = this.simulazione.creaSimulazionePartitaEGioca(righeDaLeggere);
//		
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(ComandoPrendi.OGGETTO_PRESO, io.nextMessaggio());
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
//		assertFalse(io.hasNextMessaggio());
//	}
//	
//	@Test
//	public void testSimulazioneComandoPrendiConAttrezzoNonPresente() {
//		String righeDaLeggere[] = {"prendi chiave", "fine"};
//		IOSimulator io = this.simulazione.creaSimulazionePartitaEGioca(righeDaLeggere);
//			
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(ComandoPrendi.ATTREZZO_NON_IN_STANZA, io.nextMessaggio());
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
//		assertFalse(io.hasNextMessaggio());
//	}
//	
//	@Test
//	public void testSimulazioneComandoPrendiSenzaParametro() {
//		String righeDaLeggere[] = {"prendi", "fine"};
//		IOSimulator io = this.simulazione.creaSimulazionePartitaEGioca(righeDaLeggere);
//			
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(ComandoPrendi.ATTREZZO_NULL, io.nextMessaggio());
//		assertTrue(io.hasNextMessaggio());
//		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
//		assertFalse(io.hasNextMessaggio());
//	}
//	
	@Test
	public void testSimulazioneComandoPosaConAttrezzoNellaStanza() {
		IOSimulator io = this.simulazione.creaSimulazionePartitaEGioca();
		this.session = this.simulazione.getSession();
		Borsa borsa = this.session.getPartita().getGiocatore().getBorsa();
		Attrezzo attrezzo = this.simulazione.creaAttrezzoEAggiungiBorsa(borsa, "osso", 1);
		io.setInput("prendi osso", "fine");
		
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoPrendi.OGGETTO_PRESO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}
}
