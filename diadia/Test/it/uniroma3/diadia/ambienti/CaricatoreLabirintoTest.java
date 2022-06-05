package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;
import static it.uniroma3.diadia.Direzione.*;

import java.io.FileNotFoundException;
import java.io.StringReader;

import org.junit.Test;

import it.uniroma3.diadia.eccezioni.FormatoFileNonValidoException;

public class CaricatoreLabirintoTest {
	
	private CaricatoreLabirinto caricatoreLabirinto;
	private Labirinto labirinto;
	private final static String MONOLOCALE = 
			"Stanze: biblioteca\n"+
			"Inizio: biblioteca\n"+
			"Vincente: biblioteca";

	private final static String BILOCALE = 
			"Stanze: biblioteca, atrio\n"+
			"Inizio: biblioteca\n"+
			"Vincente: atrio\n"+
			"Attrezzi: \n"+
			"Uscite: biblioteca est atrio, atrio ovest biblioteca";
	
	private final static String TRILOCALE_CON_ATTREZZI = 
			"Stanze: biblioteca, atrio, N11\n"+
			"Inizio: biblioteca\n"+
			"Vincente: atrio\n"+
			"Attrezzi: osso 3 atrio\n"+
			"Uscite: biblioteca sud atrio, atrio nord biblioteca";
	
	private final static String MONOLOCALE_MAGICO = 
			  "Stanze: N18\n"
			+ "Inizio: N18\n"
			+ "Vincente: N18\n"
			+ "Attrezzi: \n"
			+ "Uscite: \n"
			+ "Magiche: N18 2";
	
	private final static String MONOLOCALE_BUIO = 
			  "Stanze: N18\n"
			+ "Inizio: N18\n"
			+ "Vincente: N18\n"
			+ "Attrezzi: \n"
			+ "Uscite: \n"
			+ "Magiche: \n"
			+ "Buie: N18 lampada";
	
	private final static String MONOLOCALE_BLOCCATO = 
			  "Stanze: N18\n"
			+ "Inizio: N18\n"
			+ "Vincente: N18\n"
			+ "Attrezzi: \n"
			+ "Uscite: \n"
			+ "Magiche: \n"
			+ "Buie: \n"
			+ "Bloccate: N18 nord chiave";
	
	private final static String MONOLOCALE_MAGO = 
			  "Stanze: N18\n"
			+ "Inizio: N18\n"
			+ "Vincente: N18\n"
			+ "Attrezzi: \n"
			+ "Uscite: \n"
			+ "Magiche: \n"
			+ "Buie: \n"
			+ "Bloccate: \n"
			+ "Maghi: Pancione Ciao-sono-il-mago-pancione bastone 3 N18\n";
	
	private final static String MONOLOCALE_STREGA = 
			  "Stanze: N18\n"
			+ "Inizio: N18\n"
			+ "Vincente: N18\n"
			+ "Attrezzi: \n"
			+ "Uscite: \n"
			+ "Magiche: \n"
			+ "Buie: \n"
			+ "Bloccate: \n"
			+ "Maghi: \n"
			+ "Streghe: Varana Ciao-sono-la-strega-varana N18";
	
	private final static String MONOLOCALE_CANE = 
			  "Stanze: N18\n"
			+ "Inizio: N18\n"
			+ "Vincente: N18\n"
			+ "Attrezzi: \n"
			+ "Uscite: \n"
			+ "Magiche: \n"
			+ "Buie: \n"
			+ "Bloccate: \n"
			+ "Maghi: \n"
			+ "Streghe: \n"
			+ "Cani: Bobby Wof-wof-bark-bork carne N18";
	
	
//	Stanze: biblioteca, N10, N11
//	Inizio: N10
//	Vincente: N11
//	Attrezzi: martello 10 biblioteca, pinza 2 N10
//	Uscite: biblioteca nord N10, biblioteca sud N11, N10 sud biblioteca
	@Test
	public void testCaricaDaFile() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto("testLabirinto.txt");
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		assertEquals("N10", this.labirinto.getStanzaIniziale().getNome());
		assertEquals("N11", this.labirinto.getStanzaVincente().getNome());
		assertEquals("pinza", this.labirinto.getStanzaIniziale().getAttrezzo("pinza").getNome());
		assertEquals("biblioteca", this.labirinto.getStanzaIniziale().getStanzaAdiacente(SUD).getNome());
	}
	
	@Test
	public void testCaricaMonolocaleStringa() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(MONOLOCALE));
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		
		assertEquals("biblioteca", this.labirinto.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testCaricaBilocaleStringa() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(BILOCALE));
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		
		assertEquals("biblioteca", this.labirinto.getStanzaIniziale().getNome());
		assertEquals("atrio", this.labirinto.getStanzaVincente().getNome());
		assertEquals("atrio", this.labirinto.getStanzaIniziale().getStanzaAdiacente(EST).getNome());

	}
	
	@Test
	public void testCaricaTrilocaleConAttrezziStringa() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(TRILOCALE_CON_ATTREZZI));
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		
		assertEquals("osso", this.labirinto.getStanzaVincente().getAttrezzo("osso").getNome());
		assertEquals(3, this.labirinto.getStanzaVincente().getAttrezzo("osso").getPeso());
		assertEquals("atrio", this.labirinto.getStanzaIniziale().getStanzaAdiacente(SUD).getNome());
	}
	
	@Test
	public void testCaricaMonolocaleMagicoStringa() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(MONOLOCALE_MAGICO));
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		
		Stanza stanzaMagica = this.labirinto.getStanzaIniziale();
		assertEquals("N18", stanzaMagica.getNome());
		assertEquals(StanzaMagica.class, stanzaMagica.getClass());
	}
	
	@Test
	public void testCaricaMonolocaleBuioStringa() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(MONOLOCALE_BUIO));
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		
		Stanza stanzaBuia = this.labirinto.getStanzaIniziale();
		assertEquals("N18", stanzaBuia.getNome());
		assertEquals(StanzaBuia.class, stanzaBuia.getClass());
	}
	
	@Test
	public void testCaricaMonolocaleBloccatoStringa() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(MONOLOCALE_BLOCCATO));
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		
		Stanza stanzaBloccata = this.labirinto.getStanzaIniziale();
		assertEquals("N18", stanzaBloccata.getNome());
		assertEquals(StanzaBloccata.class, stanzaBloccata.getClass());
	}
	
	@Test
	public void testCaricaMagoMonolocaleStringa() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(MONOLOCALE_MAGO));
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		
		assertEquals("Pancione", this.labirinto.getStanzaIniziale().getPersonaggio().getNome());
		assertEquals("Ciao sono il mago pancione ", this.labirinto.getStanzaIniziale().getPersonaggio().getPresentazione());
		assertEquals("bastone", this.labirinto.getStanzaIniziale().getPersonaggio().getAttrezzo().getNome());
		assertEquals(3, this.labirinto.getStanzaIniziale().getPersonaggio().getAttrezzo().getPeso());

	}
	
	@Test
	public void testCaricaStregaMonolocaleStringa() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(MONOLOCALE_STREGA));
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		
		assertEquals("Varana", this.labirinto.getStanzaIniziale().getPersonaggio().getNome());
		assertEquals("Ciao sono la strega varana ", this.labirinto.getStanzaIniziale().getPersonaggio().getPresentazione());
	}
	
	@Test
	public void testCaricaCaneMonolocaleStringa() throws FileNotFoundException, FormatoFileNonValidoException {
		this.caricatoreLabirinto = new CaricatoreLabirinto(new StringReader(MONOLOCALE_CANE));
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
		
		assertEquals("Bobby", this.labirinto.getStanzaIniziale().getPersonaggio().getNome());
		assertEquals("Wof wof bark bork ", this.labirinto.getStanzaIniziale().getPersonaggio().getPresentazione());
	}
}
