package it.uniroma3.diadia;

import it.uniroma3.diadia.comando.Comando;
import it.uniroma3.diadia.comando.FabbricaDiComandoFisarmonica;

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

	public DiaDia(IO io) {
		this.io = io;
		this.partita = new Partita(io);
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
		Comando comandoDaEseguire;
		FabbricaDiComandoFisarmonica factory = new FabbricaDiComandoFisarmonica();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta())
			this.io.mostraMessaggio(VITTORIA);
		if (!this.partita.giocatoreIsVivo())
			this.io.mostraMessaggio(SCONFITTA);
		return this.partita.isFinita();
	}

	public static void main(String[] argc) {
		IO console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
	
	/**
	 * Ritorna un riferimento alla partita in corso
	 * @return Partita
	 */
	public Partita getPartita() {
		return this.partita;
	}
}
