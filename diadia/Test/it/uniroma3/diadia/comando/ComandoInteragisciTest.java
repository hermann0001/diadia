package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class ComandoInteragisciTest {
	private static final String PRES_STREGA = "Ciao sono la strega Anna";
	private static final String NOME_STREGA = "Anna";
	private static final String PRES_MAGO = "Ciao sono il mago Peppe";
	private static final String NOME_MAGO = "Peppe";
	private static final String PRES_CANE = "Wof sono il cane Bobby";
	private static final String NOME_CANE = "Bobby";
	private AbstractComando comandoInteragisci;
	private AbstractPersonaggio personaggio;
	private Partita partita;
	private IO io;

	@Before
	public void setUp() throws Exception {
		Labirinto labirinto = new LabirintoBuilder().addStanzaIniziale("partenza").getLabirinto();
		this.comandoInteragisci = new ComandoInteragisci();
		this.io = new IOConsole();
		this.partita = new Partita(this.io, labirinto);
	}

	@Test
	public void testInteragisciStrega() {
		this.personaggio = new Strega(NOME_STREGA, PRES_STREGA);
		this.partita.getStanzaCorrente().setPersonaggio(personaggio);
		this.comandoInteragisci.esegui(this.partita);
		
		assertEquals(Strega.MESSAGGIO_INTERAGISCI + this.partita.getStanzaCorrente().getNome(), this.comandoInteragisci.getMessaggio());
	}
	
	@Test
	public void testInteragisciMago() {
		Attrezzo attrezzoMago = new Attrezzo("bastone", 1);
		this.personaggio = new Mago(NOME_MAGO, PRES_MAGO, attrezzoMago);
		this.partita.getStanzaCorrente().setPersonaggio(personaggio);
		this.comandoInteragisci.esegui(this.partita);
		
		assertEquals(Mago.MESSAGGIO_DONO, this.comandoInteragisci.getMessaggio());
		assertEquals(attrezzoMago, this.partita.getStanzaCorrente().getAttrezzo(attrezzoMago.getNome()));
	}
	
	@Test
	public void testInteragisciCane() {
		this.personaggio = new Cane(NOME_CANE, PRES_CANE);
		this.partita.getStanzaCorrente().setPersonaggio(personaggio);
		int cfuPrimaMorso = this.partita.getGiocatore().getCfu();
		this.comandoInteragisci.esegui(this.partita);
		
		int cfuDopoMorso = this.partita.getGiocatore().getCfu();
		assertEquals(Cane.MESSAGGIO + cfuDopoMorso, this.comandoInteragisci.getMessaggio());
		assertEquals(cfuPrimaMorso - 2, cfuDopoMorso);
	}

}
