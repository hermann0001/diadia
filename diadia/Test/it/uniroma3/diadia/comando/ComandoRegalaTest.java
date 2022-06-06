package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
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

	@Before
	public void setUp() throws Exception {
		this.comandoRegala = new ComandoRegala();
	}

	@Test
	public void testRegalaStrega() {
		Partita partita = creaPartitaMonolocale();
		this.personaggio = new Strega(NOME_STREGA, PRES_STREGA);
		partita.getStanzaCorrente().setPersonaggio(this.personaggio);
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("test", 2));
		this.comandoRegala.setParametro("test");
		this.comandoRegala.esegui(partita);
		
		assertEquals("test", this.personaggio.getAttrezzo().getNome());
	}
	
	@Test
	public void testRegalaStregaAttrezzoNonEsistente() {
		Partita partita = creaPartitaMonolocale();
		this.personaggio = new Strega(NOME_STREGA, PRES_STREGA);
		partita.getStanzaCorrente().setPersonaggio(this.personaggio);
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("test", 2));
		this.comandoRegala.setParametro("nonEsistente");
		this.comandoRegala.esegui(partita);
		
		assertNull(this.personaggio.getAttrezzo());
	}
	
	@Test
	public void testRegalaMago() {
		Partita partita = creaPartitaMonolocale();
		this.personaggio = new Mago(NOME_MAGO, PRES_MAGO, null);
		partita.getStanzaCorrente().setPersonaggio(this.personaggio);
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("test", 2));
		this.comandoRegala.setParametro("test");
		this.comandoRegala.esegui(partita);
		
		assertEquals("test", partita.getStanzaCorrente().getAttrezzo("test").getNome());
		assertEquals(1, partita.getStanzaCorrente().getAttrezzo("test").getPeso());
	}
	
	@Test
	public void testRegalaMagoNonValido() {
		Partita partita = creaPartitaMonolocale();
		this.personaggio = new Mago(NOME_MAGO, PRES_MAGO, null);
		partita.getStanzaCorrente().setPersonaggio(this.personaggio);
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("test", 2));
		this.comandoRegala.setParametro("nonValido");
		this.comandoRegala.esegui(partita);
		
		assertNull(partita.getStanzaCorrente().getAttrezzo("test"));
	}
	
	@Test
	public void testRegalaCane() {
		Partita partita = creaPartitaMonolocale();
		this.personaggio = new Cane(NOME_CANE, PRES_CANE, "test");
		partita.getStanzaCorrente().setPersonaggio(this.personaggio);
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("test", 2));
		this.comandoRegala.setParametro("test");
		this.comandoRegala.esegui(partita);
		
		assertFalse(partita.getStanzaCorrente().getAttrezzi().isEmpty());
	}
	
	@Test
	public void testRegalaCaneDiversoDaCiboPref() {
		Partita partita = creaPartitaMonolocale();
		this.personaggio = new Cane(NOME_CANE, PRES_CANE, "test");
		partita.getStanzaCorrente().setPersonaggio(this.personaggio);
		partita.getGiocatore().getBorsa().addAttrezzo(new Attrezzo("test2", 2));
		this.comandoRegala.setParametro("test2");
		this.comandoRegala.esegui(partita);
		
		assertEquals(18, partita.getGiocatore().getCfu());
	}
	
	
	private Partita creaPartitaMonolocale() {
		Labirinto monolocale = Labirinto.newBuilder().addStanzaIniziale("atrio").getLabirinto();
		return new Partita(new IOConsole(), monolocale); 
	}

}
