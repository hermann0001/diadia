package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;
import static it.uniroma3.diadia.Direzione.*;

import org.junit.Before;
import org.junit.Test;

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
	private static final String CIBO_PREF_CANE = "osso";
	private static final String PRES_STREGA = "Ciao sono la strega Elisa";
	private static final String NOME_STREGA = "Elisa";
	private static final String PRES_MAGO = "Ciao sono il mago Peppe";
	private static final String NOME_MAGO = "Peppe";
	private static final String PRES_CANE = "Wof sono il cane Bobby";
	private static final String NOME_CANE = "Bobby";
	private AbstractComando comandoInteragisci;
	private AbstractPersonaggio personaggio;

	@Before
	public void setUp() throws Exception {
		this.comandoInteragisci = new ComandoInteragisci();
	}

	@Test
	public void testInteragisciStregaMonolocale() {
		Partita partita = creaPartitaMonolocale();
		this.personaggio = new Strega(NOME_STREGA, PRES_STREGA);
		partita.getStanzaCorrente().setPersonaggio(personaggio);
		this.comandoInteragisci.esegui(partita);
		
		assertEquals(Strega.MESSAGGIO_MONOLOCALE, this.comandoInteragisci.getMessaggio());
	}
	
	@Test
	public void testInteragisciStregaTrilocaleNoSaluto() {
		Partita partita = creaPartitaTrilocale();
		this.personaggio = new Strega(NOME_STREGA, PRES_STREGA);
		partita.getStanzaCorrente().setPersonaggio(personaggio);
		this.comandoInteragisci.esegui(partita);
		
		assertFalse(this.personaggio.haSalutato());
		assertEquals(Strega.MESSAGGIO_INTERAGISCI + "N18", this.comandoInteragisci.getMessaggio());
	}
	
	@Test
	public void testInteragisciStregaTrilocaleSaluto() {
		Partita partita = creaPartitaTrilocale();
		this.personaggio = new Strega(NOME_STREGA, PRES_STREGA);
		partita.getStanzaCorrente().setPersonaggio(personaggio);
		this.personaggio.saluta();
		this.comandoInteragisci.esegui(partita);
		
		assertTrue(this.personaggio.haSalutato());
		assertEquals(Strega.MESSAGGIO_INTERAGISCI + "campus", this.comandoInteragisci.getMessaggio());
	}
	
	@Test
	public void testInteragisciMago() {
		Attrezzo attrezzoMago = new Attrezzo("bastone", 1);
		Partita partita = creaPartitaMonolocale();
		this.personaggio = new Mago(NOME_MAGO, PRES_MAGO, attrezzoMago);
		partita.getStanzaCorrente().setPersonaggio(personaggio);
		this.comandoInteragisci.esegui(partita);
		
		assertEquals(Mago.MESSAGGIO_DONO, this.comandoInteragisci.getMessaggio());
		assertEquals(attrezzoMago, partita.getStanzaCorrente().getAttrezzo(attrezzoMago.getNome()));
	}
	
	@Test
	public void testInteragisciMagoNoAttrezzo() {
		Partita partita = creaPartitaMonolocale();
		this.personaggio = new Mago(NOME_MAGO, PRES_MAGO, null);
		partita.getStanzaCorrente().setPersonaggio(personaggio);
		this.comandoInteragisci.esegui(partita);
		
		assertEquals(Mago.MESSAGGIO_SCUSE, this.comandoInteragisci.getMessaggio());
	}
	
	@Test
	public void testInteragisciCane() {
		this.personaggio = new Cane(NOME_CANE, PRES_CANE, CIBO_PREF_CANE);
		Partita partita = creaPartitaMonolocale();
		partita.getStanzaCorrente().setPersonaggio(personaggio);
		int cfuPrimaMorso = partita.getGiocatore().getCfu();
		this.comandoInteragisci.esegui(partita);
		
		int cfuDopoMorso = partita.getGiocatore().getCfu();
		assertEquals(Cane.MESSAGGIO + cfuDopoMorso, this.comandoInteragisci.getMessaggio());
		assertEquals(cfuPrimaMorso - 2, cfuDopoMorso);
	}
	
	@Test
	public void testInteragisciCaneFineCFU() {
		this.personaggio = new Cane(NOME_CANE, PRES_CANE, CIBO_PREF_CANE);
		Partita partita = creaPartitaMonolocale();
		partita.getStanzaCorrente().setPersonaggio(personaggio);
		partita.getGiocatore().setCfu(2);
		int cfuPrimaMorso = partita.getGiocatore().getCfu();
		this.comandoInteragisci.esegui(partita);
		
		int cfuDopoMorso = partita.getGiocatore().getCfu();
		assertEquals(Cane.MESSAGGIO + cfuDopoMorso, this.comandoInteragisci.getMessaggio());
		assertEquals(cfuPrimaMorso - 2, cfuDopoMorso);
	}
	
	private Partita creaPartitaTrilocale() {
		Labirinto trilocale = new LabirintoBuilder()
				.addStanzaIniziale("atrio")
				.addStanza("N18")
				.addAttrezzo("bastone", 3)
				.addStanza("campus")
				.addAttrezzo("spada", 4)
				.addAttrezzo("pistola", 1)
				.addAdiacenze("atrio", NORD, "N18")
				.addAdiacenze("N18", SUD, "atrio")
				.addAdiacenze("atrio", EST, "campus")
				.addAdiacenze("campus", OVEST, "atrio")
				.getLabirinto();
		return new Partita(new IOConsole(), trilocale); 
	}
	
	private Partita creaPartitaMonolocale() {
		Labirinto monolocale = new LabirintoBuilder().addStanzaIniziale("atrio").getLabirinto();
		return new Partita(new IOConsole(), monolocale); 
	}

}
