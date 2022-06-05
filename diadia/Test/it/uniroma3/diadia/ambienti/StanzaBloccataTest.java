package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import static it.uniroma3.diadia.Direzione.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	static final private String ATTREZZO_SBLOCCA_DIREZIONE = "chiave";
	static final private Direzione DIREZIONE_BLOCCATA = NORD;
	static final private String DESCRIZIONE_BLOCCATA = "La stanza è bloccata.\nDirezione bloccata: "
			+ DIREZIONE_BLOCCATA + ".\nServe un attrezzo particolare "
			+ "per sbloccarla.\nAttrezzo per sbloccare la stanza: " + ATTREZZO_SBLOCCA_DIREZIONE;

	private StanzaBloccata stanza;
	private Attrezzo chiave;
	private Attrezzo osso;
	private Stanza stanzaAdiacente;

	@Before
	public void setUp() {
		this.stanzaAdiacente = new Stanza("adiacente");
		this.stanza = new StanzaBloccata("test", NORD, "chiave");
		this.chiave = new Attrezzo("chiave", 1);
		this.osso = new Attrezzo("osso", 1);
	}

	@Test
	public void testGetDescrizioneBloccata() {
		this.stanza.addAttrezzo(osso);
		assertEquals(DESCRIZIONE_BLOCCATA, this.stanza.getDescrizione());
	}

	@Test
	public void testGetDescrizioneSbloccata() {
		this.stanza.addAttrezzo(chiave);
		assertNotEquals(DESCRIZIONE_BLOCCATA, this.stanza.getDescrizione());
	}

	@Test
	public void testGetStanzaAdiacenteBloccata() {
		this.stanza.addAttrezzo(osso);
		this.stanza.impostaStanzaAdiacente(DIREZIONE_BLOCCATA, this.stanzaAdiacente);
		assertEquals(this.stanza, this.stanza.getStanzaAdiacente(DIREZIONE_BLOCCATA));
	}

	@Test
	public void testGetAltraStanzaAdiacenteBloccata() {
		this.stanza.addAttrezzo(osso);
		this.stanza.impostaStanzaAdiacente(SUD, this.stanzaAdiacente);
		assertEquals(this.stanza, this.stanza.getStanzaAdiacente(SUD));
	}

	@Test
	public void testGetStanzaAdiacenteSbloccata() {
		this.stanza.addAttrezzo(chiave);
		this.stanza.impostaStanzaAdiacente(DIREZIONE_BLOCCATA, this.stanzaAdiacente);
		assertEquals(this.stanzaAdiacente, this.stanza.getStanzaAdiacente(DIREZIONE_BLOCCATA));
	}
}
