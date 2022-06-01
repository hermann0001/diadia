package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegalaTest {
	
	private AbstractComando comandoRegala;
	private AbstractPersonaggio personaggio;
	private Partita partita;
	private IO io;

	@Before
	public void setUp() throws Exception {
		Labirinto labirinto = new LabirintoBuilder().addStanzaIniziale("partenza").getLabirinto();
		this.comandoRegala = new ComandoRegala();
		this.io = new IOConsole();
		this.partita = new Partita(this.io, labirinto);
	}

	@Test
	public void testRegala() {
		this.comandoRegala.esegui(this.partita);
	}

}
