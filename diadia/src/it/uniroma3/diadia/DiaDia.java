package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.CaricatoreLabirinto;
import it.uniroma3.diadia.ambienti.Labirinto;
import static it.uniroma3.diadia.Direzione.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

import it.uniroma3.diadia.comando.AbstractComando;
import it.uniroma3.diadia.comando.ComandoNonValido;
import it.uniroma3.diadia.comando.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.eccezioni.FormatoFileNonValidoException;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version hw1
 */

public class DiaDia {

	public static final String SCONFITTA = "Hai esaurito i CFU...";

	public static final String VITTORIA = "Hai vinto!";

	public final static String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	private IO io;
	private Partita partita;

	public DiaDia(IO io, Labirinto labirinto) {
		this.io = io;
		this.partita = new Partita(io, labirinto);
	}

	public void gioca() {
		String istruzione;

		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		AbstractComando comandoDaEseguire;
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva();
		try {
			comandoDaEseguire = factory.costruisciComando(istruzione);	
		}catch(IllegalArgumentException e) {
			comandoDaEseguire = new ComandoNonValido(e);
		}catch(RuntimeException e) {
			throw new RuntimeException();
		}
		
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			this.io.mostraMessaggio(VITTORIA);
		if (!this.partita.giocatoreIsVivo())
			this.io.mostraMessaggio(SCONFITTA);
		return this.partita.isFinita();
	}

	public static void main(String[] argc) {
		Scanner scannerDiLinee = new Scanner(System.in);
		IO console = new IOConsole(scannerDiLinee);

		try{
			Labirinto labirinto = creaMappaDaFile("testLabirinto.txt");
			DiaDia gioco = new DiaDia(console, labirinto);
			gioco.gioca();
			
		} catch(FileNotFoundException e) {
			console.mostraMessaggio("Errore nel caricamento del file del labirinto... non esiste!");
		} catch(FormatoFileNonValidoException e) {
			console.mostraMessaggio("Errore nel caricamento del file, il formato è errato!");
		} catch(Exception e) {
			console.mostraMessaggio("qualcosa è andato storto...");
		} finally {
			scannerDiLinee.close();
		}
	}

	/**
	 * Ritorna un riferimento alla partita in corso
	 * 
	 * @return Partita
	 */
	public Partita getPartita() {
		return this.partita;
	}

	static public Labirinto creaMappaPredefinita() {
		Labirinto lab =  Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 1)
				.addStanzaVincente("Biblioteca")
				.addAdiacenze("Atrio", NORD, "Biblioteca")
				.addAdiacenze("Atrio", EST, "Aula N11")
				.addAdiacenze("Atrio", SUD, "Aula N10")
				.addAdiacenze("Atrio", OVEST, "Laboratorio")
				.addAdiacenze("Aula N11", EST, "Laboratorio")
				.addAdiacenze("Aula N11", OVEST, "Atrio")
				.addAdiacenze("Aula N10", NORD, "Atrio")
				.addAdiacenze("Aula N10", EST, "Aula N11")
				.addAdiacenze("Aula N10", OVEST, "Laboratorio")
				.addAdiacenze("Laboratorio", EST, "Atrio")
				.addAdiacenze("Laboratorio", OVEST, "Aula N11")
				.addAdiacenze("Biblioteca", SUD, "Atrio")
				.getLabirinto();
		
		lab.setNome("Predefinito");
		return lab;
	}
	
	static public Labirinto creaMappaDaFile(String nomeFile) throws FormatoFileNonValidoException, FileNotFoundException {
		final CaricatoreLabirinto caricatoreLabirinto = new CaricatoreLabirinto(nomeFile);
		caricatoreLabirinto.carica();
		final Labirinto lab = caricatoreLabirinto.getLabirinto();
		lab.setNome(nomeFile);
		return lab;
	}
}
