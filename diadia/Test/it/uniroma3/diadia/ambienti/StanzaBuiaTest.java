package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	static final private String MESSAGGIO_OSCURATO = "qui c'è un buio pesto";

	private StanzaBuia stanza;
	private StanzaBuia stanzaVuota;
	private Attrezzo lanterna;
	private Attrezzo osso;

	@Before
	public void setUp() {
		this.stanza = new StanzaBuia("test", "lanterna");
		this.stanzaVuota = new StanzaBuia("test", "lanterna");
		this.lanterna = new Attrezzo("lanterna", 2);
		this.osso = new Attrezzo("osso", 1);
	}

	@Test
	public void testGetDescrizioneOscurata() {
		this.stanza.addAttrezzo(osso);
		assertEquals(MESSAGGIO_OSCURATO, this.stanza.getDescrizione());
		assertEquals(MESSAGGIO_OSCURATO, this.stanzaVuota.getDescrizione());
	}

	@Test
	public void testGetDescrizioneChiara() {
		this.stanza.addAttrezzo(lanterna);
		assertNotEquals(MESSAGGIO_OSCURATO, this.stanza.getDescrizione());
	}

}
