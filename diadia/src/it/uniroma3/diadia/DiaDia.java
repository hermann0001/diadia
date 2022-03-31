package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa" };
	private IOConsole io;
	private Partita partita;

	public DiaDia(IOConsole io) {
		this.io = io;
		this.partita = new Partita();
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
		Comando comandoDaEseguire = new Comando(istruzione);

		String nome = comandoDaEseguire.getNome();
		if (nome == null) {
			this.io.mostraMessaggio("Devi digitare un comando");
			return false;
		}

		if (nome.equals("fine")) {
			this.fine();
			return true;
		} else if (nome.equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (nome.equals("aiuto"))
			this.aiuto();
		else if (nome.equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if (nome.equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			this.io.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			this.io.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for (int i = 0; i < elencoComandi.length; i++)
			this.io.mostraMessaggio(elencoComandi[i] + " ");
		this.io.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null)
			this.io.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		this.io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.io.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
	}

	private boolean prendi(String nomeAttrezzo) {
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		if (stanzaCorrente.hasAttrezzo(nomeAttrezzo) == false)
			return false;

		Borsa borsa = this.partita.getGiocatore().getBorsa();
		Attrezzo a = stanzaCorrente.getAttrezzo(nomeAttrezzo);
		stanzaCorrente.removeAttrezzo(nomeAttrezzo);
		if (borsa.addAttrezzo(a) == false)
			return false;
		
		this.io.mostraMessaggio("Oggetto preso!");

		return true;
	}

	/**
	 * Comando: posa
	 * 
	 * @param nomeAttrezzo
	 */
	private boolean posa(String nomeAttrezzo) {

		Borsa borsa = this.partita.getGiocatore().getBorsa();
		if (borsa.hasAttrezzo(nomeAttrezzo) == false)
			return false;
		Attrezzo a = borsa.removeAttrezzo(nomeAttrezzo);
		Stanza stanzaCorrente = this.partita.getStanzaCorrente();
		if (stanzaCorrente.addAttrezzo(a) == false)
			return false;
		
		this.io.mostraMessaggio("Oggetto posato!");

		return true;
	}

	public static void main(String[] argc) {
		IOConsole console = new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}
