package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Strega;

public class ComandoSalutaTest {
	
	private static final String PRES_STREGA = "Ciao sono la strega Anna";
	private static final String NOME_STREGA = "Anna";
	private AbstractComando comandoSaluta;
	private AbstractPersonaggio personaggio;
	private Partita partita;
	private IO io;

	@Before
	public void setUp() throws Exception {
		Labirinto labirinto = Labirinto.newBuilder().addStanzaIniziale("partenza").getLabirinto();
		this.comandoSaluta = new ComandoSaluta();
		this.io = new IOConsole();
		this.partita = new Partita(this.io, labirinto);
	}

	@Test
	public void testHaSalutatoStrega() {
		this.personaggio = new Strega(NOME_STREGA, PRES_STREGA);
		this.partita.getStanzaCorrente().setPersonaggio(personaggio);
		this.comandoSaluta.esegui(this.partita);
		assertTrue(this.personaggio.haSalutato());
	}
	
	@Test
	public void testoNonHaSalutatoStrega() {
		this.personaggio = new Strega(NOME_STREGA, PRES_STREGA);
		this.partita.getStanzaCorrente().setPersonaggio(personaggio);
		assertFalse(this.personaggio.haSalutato());
	}
}
