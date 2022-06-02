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

//			Stanze: biblioteca, N10, N11
//			Inizio: N10
//			Vincente: N11
//			Attrezzi: martello 10 biblioteca, pinza 2 N10
//			Uscite: biblioteca nord N10, biblioteca sud N11, N10 sud biblioteca
	
	@Before
	public void setUp() throws Exception{
		this.caricatoreLabirinto = new CaricatoreLabirinto("testLabirinto.txt");
		this.caricatoreLabirinto.carica();
		this.labirinto = this.caricatoreLabirinto.getLabirinto();
	}
	
	
	@Test
	public void testCarica() {
		assertEquals("N10", this.labirinto.getStanzaIniziale().getNome());
		assertEquals("N11", this.labirinto.getStanzaVincente().getNome());
		assertEquals("pinza", this.labirinto.getStanzaIniziale().getAttrezzo("pinza").getNome());
		assertEquals("biblioteca", this.labirinto.getStanzaIniziale().getStanzaAdiacente("sud").getNome());
	}

}
