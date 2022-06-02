package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.eccezioni.FormatoFileNonValidoException;

public class CaricatoreLabirintoTest {
	
	private CaricatoreLabirinto caricatoreLabirinto;
	private Labirinto labirinto;

	private static final String DESCRIZIONE_LABIRINTO =
			"Stanze: biblioteca, N10, N11\n"+
			"Inizio: N10\n"+
			"Vincente: N11\n"+
			"Attrezzi: martello 10 biblioteca, pinza 2 N10\n"+
			"Uscite: biblioteca nord N10, biblioteca sud N11";
	@Before
	public void setUp() throws Exception{
//		this.caricatoreLabirinto = new CaricatoreLabirinto("testLabirinto");
		this.labirinto = new Labirinto("testLabirinto.txt");
	}
	
	
	@Test
	public void testCarica() {
		assertEquals("N10", this.labirinto.getStanzaIniziale().getNome());
		
	}

}
