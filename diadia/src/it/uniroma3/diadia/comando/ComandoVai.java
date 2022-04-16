package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {

	private String direzione;

	/*
	 * esecuzione del comando
	 */

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	@Override
	public void esegui(Partita partita) {
		// qui il codice per cambiare stanza ...}}
		Stanza prossimaStanza = null;
		IOConsole ioconsole = partita.getIoconsole();
		if (this.direzione == null) {
			ioconsole.mostraMessaggio("Dove vuoi andare ?");
			return;
		}

		Stanza stanzaCorrente = partita.getStanzaCorrente();
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			ioconsole.mostraMessaggio("Direzione inesistente");
			return;
		} 
		partita.setStanzaCorrente(prossimaStanza);
		ioconsole.mostraMessaggio(partita.getStanzaCorrente().getNome());
		
		int cfu = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(cfu--);
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
}
