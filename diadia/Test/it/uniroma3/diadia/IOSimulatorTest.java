package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comando.ComandoAiuto;
import it.uniroma3.diadia.comando.ComandoFine;
import it.uniroma3.diadia.comando.ComandoNonValido;
import it.uniroma3.diadia.comando.ComandoPosa;
import it.uniroma3.diadia.comando.ComandoPrendi;
import it.uniroma3.diadia.comando.ComandoVai;
import it.uniroma3.diadia.fixture.Fixture;
import it.uniroma3.diadia.giocatore.Borsa;

public class IOSimulatorTest {

	private Fixture simulazione;
	private DiaDia session;
	private IOSimulator io;

	@Before
	public void setUp() {
		this.simulazione = new Fixture();
		this.io = this.simulazione.creaSimulazionePartitaPredefinitaEGioca();
		this.session = this.simulazione.getSession();
	}

	public void assertContains(String expected, String interaRiga) {
		assertTrue(interaRiga.contains(expected));
	}

	@Test
	public void testSimulazioneComandoFine() {
		String righeDaLeggere[] = { "fine" };
		this.io.setInput(righeDaLeggere);
		this.session.gioca();
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	@Test
	public void testSimulazioneComandoAiuto() {
		String righeDaLeggere[] = { "aiuto", "fine" };
		this.io.setInput(righeDaLeggere);
		this.session.gioca();

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		for (int i = 0; i < ComandoAiuto.ELENCO_COMANDI.length; i++) {
			assertTrue(io.hasNextMessaggio());
			assertEquals(ComandoAiuto.ELENCO_COMANDI[i] + " ", io.nextMessaggio());
		}
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	@Test
	public void testSimulazioneComandoNonValido() {
		String righeDaLeggere[] = { "sbagliato", "fine" };
		this.io.setInput(righeDaLeggere);
		this.session.gioca();

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoNonValido.COMANDO_NON_VALIDO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	@Test
	public void testSimulazioneComandoGuarda() {
		String righeDaLeggere[] = { "guarda", "fine" };
		this.io.setInput(righeDaLeggere);
		this.session.gioca();

		Partita partita = this.session.getPartita();
		String outputStanza = partita.getStanzaCorrente().getDescrizione();
		String outputBorsa = partita.getGiocatore().getBorsa().toString();
		String nomeMappa = partita.getLabirinto().getNome();
		String nomeGiocatore = partita.getGiocatore().getNome();
		int cfu = partita.getGiocatore().getCfu();

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(outputStanza, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(outputBorsa, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Mappa:" + nomeMappa, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Giocatore:" + nomeGiocatore, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("CFU:" + cfu, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	@Test
	public void testSimulazioneComandoPrendiConAttrezzoNellaStanza() {
		String righeDaLeggere[] = { "prendi chiave", "fine" };
		Stanza corrente = this.session.getPartita().getStanzaCorrente();
		this.simulazione.creaAttrezzoEAggiungiStanza(corrente, "chiave", 1);
		this.io.setInput(righeDaLeggere);
		this.session.gioca();

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoPrendi.ATTREZZO_PRESO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	@Test
	public void testSimulazioneComandoPrendiConAttrezzoNonPresente() {
		String righeDaLeggere[] = { "prendi chiave", "fine" };
		this.io.setInput(righeDaLeggere);
		this.session.gioca();

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoPrendi.ATTREZZO_NON_PRESENTE, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	@Test
	public void testSimulazioneComandoPrendiSenzaParametro() {
		String righeDaLeggere[] = { "prendi", "fine" };
		this.io.setInput(righeDaLeggere);
		this.session.gioca();

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoPrendi.ATTREZZO_NULL, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	@Test
	public void testSimulazioneComandoPosaConAttrezzoNellaBorsa() {
		Borsa borsa = this.session.getPartita().getGiocatore().getBorsa();
		this.simulazione.creaAttrezzoEAggiungiBorsa(borsa, "osso", 1);
		String righeDaLeggere[] = { "posa osso", "fine" };
		this.io.setInput(righeDaLeggere);
		this.session.gioca();

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoPosa.ATTREZZO_POSATO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	@Test
	public void testSimulazioneComandoPosaConAttrezzoNonPresente() {
		String righeDaLeggere[] = { "posa chiave", "fine" };
		this.io.setInput(righeDaLeggere);
		this.session.gioca();

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoPosa.ATTREZZO_NON_PRESENTE, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	@Test
	public void testSimulazioneComandoPosaSenzaParametro() {
		String righeDaLeggere[] = { "posa", "fine" };
		this.io.setInput(righeDaLeggere);
		this.session.gioca();

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoPosa.ATTREZZO_NULL, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	@Test
	public void testSimulazioneComandoVai() {
		String righeDaLeggere[] = { "vai sud", "fine" };
		this.io.setInput(righeDaLeggere);
		this.session.gioca();

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertContains("Aula N10", io.nextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	@Test
	public void testSimulazioneComandoVaiParametroErrato() {
		String righeDaLeggere[] = { "vai destra", "fine" };
		this.io.setInput(righeDaLeggere);
		this.session.gioca();

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoVai.DIREZIONE_INESISTENTE, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	public void testSimulazioneComandoVaiSenzaParametro() {
		String righeDaLeggere[] = { "vai", "fine" };
		this.io.setInput(righeDaLeggere);
		this.session.gioca();

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoVai.DIREZIONE_NULL, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	public void testSimulazioneDoppioComandoVai() {
		String righeDaLeggere[] = { "vai sud", "vai est", "fine" };
		this.io.setInput(righeDaLeggere);
		this.session.gioca();

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertContains("Aula N10", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertContains("Aula N11", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	@Test
	public void testSimulazioneVittoria() {
		String righeDaLeggere[] = { "vai sud", "vai nord", "vai nord" };
		this.io.setInput(righeDaLeggere);
		this.session.gioca();

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertContains("Aula N10", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertContains("Atrio", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertContains("Biblioteca", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.VITTORIA, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	@Test
	public void testSimulazioneFineCfu() {
		String righeDaLeggere[] = { "vai sud", "vai nord", "vai est" };
		this.session.getPartita().getGiocatore().setCfu(3);
		this.io.setInput(righeDaLeggere);
		this.session.gioca();

		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertContains("Aula N10", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertContains("Atrio", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertContains("Aula N11", io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.SCONFITTA, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}

	@Test
	public void testSimulazioneAttrezzoCambiaStanza() {
		String righeDaLeggere[] = { "prendi osso", "vai est", "posa osso", "guarda", "fine" };
		this.io.setInput(righeDaLeggere);
		this.session.gioca();

		Partita partita = this.session.getPartita();
		String outputStanza = partita.getStanzaCorrente().getDescrizione();
		String outputBorsa = partita.getGiocatore().getBorsa().toString();
		String nomeMappa = partita.getLabirinto().getNome();
		String nomeGiocatore = partita.getGiocatore().getNome();
		int cfu = partita.getGiocatore().getCfu();

		/* prendi osso */
		assertTrue(io.hasNextMessaggio());
		assertEquals(DiaDia.MESSAGGIO_BENVENUTO, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoPrendi.ATTREZZO_PRESO, io.nextMessaggio());

		/* vai est */
		assertTrue(io.hasNextMessaggio());
		assertContains("Aula N11", io.nextMessaggio());

		/* posa osso */
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoPosa.ATTREZZO_POSATO, io.nextMessaggio());

		/* guarda */
		assertTrue(io.hasNextMessaggio());
		assertEquals(outputStanza, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals(outputBorsa, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Mappa:" + nomeMappa, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("Giocatore:" + nomeGiocatore, io.nextMessaggio());
		assertTrue(io.hasNextMessaggio());
		assertEquals("CFU:" + cfu, io.nextMessaggio());

		/* fine */
		assertTrue(io.hasNextMessaggio());
		assertEquals(ComandoFine.MESSAGGIO_FINE, io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
	}
}
