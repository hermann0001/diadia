package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.fixture.Fixture;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class ComandoRegalaTest {
	
	private static final String PRES_STREGA = "Ciao sono la strega Elisa";
	private static final String NOME_STREGA = "Elisa";
	private static final String PRES_MAGO = "Ciao sono il mago Peppe";
	private static final String NOME_MAGO = "Peppe";
	private static final String PRES_CANE = "Wof sono il cane Bobby";
	private static final String NOME_CANE = "Bobby";
	
	
	private AbstractComando comandoRegala;
	private AbstractPersonaggio personaggio;
	private IOSimulator io;

	@Before
	public void setUp() throws Exception {
		this.comandoRegala = new ComandoRegala();
		this.io = new IOSimulator();
	}

	@Test
	public void testRegalaStrega() {
		String[] righeDaLeggere = {"test"};
		Partita partita = creaPartitaMonolocale();
		this.io.setInput(righeDaLeggere);
		this.personaggio = new Strega(NOME_STREGA, PRES_STREGA);
		partita.getStanzaCorrente().setPersonaggio(this.personaggio);
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("test", 2));
		this.comandoRegala.esegui(partita);
		
		assertEquals("test", this.personaggio.getAttrezzo().getNome());
	}
	
	@Test
	public void testRegalaStregaAttrezzoNonEsistente() {
		String[] righeDaLeggere = {"nonEsistente"};
		Partita partita = creaPartitaMonolocale();
		this.io.setInput(righeDaLeggere);
		this.personaggio = new Strega(NOME_STREGA, PRES_STREGA);
		partita.getStanzaCorrente().setPersonaggio(this.personaggio);
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("test", 2));
		this.comandoRegala.esegui(partita);
		
		assertNull(this.personaggio.getAttrezzo());
	}
	
	@Test
	public void testRegalaMago() {
		String[] righeDaLeggere = {"test"};
		Partita partita = creaPartitaMonolocale();
		this.io.setInput(righeDaLeggere);
		this.personaggio = new Mago(NOME_MAGO, PRES_MAGO, null);
		partita.getStanzaCorrente().setPersonaggio(this.personaggio);
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("test", 2));
		this.comandoRegala.esegui(partita);
		
		assertEquals("test", partita.getStanzaCorrente().getAttrezzo("test").getNome());
		assertEquals(1, partita.getStanzaCorrente().getAttrezzo("test").getPeso());
	}
	
	@Test
	public void testRegalaMagoNonValido() {
		String[] righeDaLeggere = {"nonValido"};
		Partita partita = creaPartitaMonolocale();
		this.io.setInput(righeDaLeggere);
		this.personaggio = new Mago(NOME_MAGO, PRES_MAGO, null);
		partita.getStanzaCorrente().setPersonaggio(this.personaggio);
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("test", 2));
		this.comandoRegala.esegui(partita);
		
		assertNull(partita.getStanzaCorrente().getAttrezzo("test"));
	}
	
	@Test
	public void testRegalaCane() {
		String[] righeDaLeggere = {"test"};
		Partita partita = creaPartitaMonolocale();
		this.io.setInput(righeDaLeggere);
		this.personaggio = new Cane(NOME_CANE, PRES_CANE, "test");
		partita.getStanzaCorrente().setPersonaggio(this.personaggio);
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("test", 2));
		this.comandoRegala.esegui(partita);
		
		assertFalse(partita.getStanzaCorrente().getAttrezzi().isEmpty());
	}
	
	@Test
	public void testRegalaCaneDiversoDaCiboPref() {
		String[] righeDaLeggere = {"test2"};
		Partita partita = creaPartitaMonolocale();
		this.io.setInput(righeDaLeggere);
		this.personaggio = new Cane(NOME_CANE, PRES_CANE, "test");
		partita.getStanzaCorrente().setPersonaggio(this.personaggio);
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("test2", 2));
		this.comandoRegala.esegui(partita);
		
		assertEquals(18, partita.getGiocatore().getCfu());
	}
	
	
	private Partita creaPartitaMonolocale() {
		Labirinto monolocale = new LabirintoBuilder().addStanzaIniziale("atrio").getLabirinto();
		return new Partita(this.io, monolocale); 
	}

}
