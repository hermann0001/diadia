package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai implements Comando {

	public static final String DIREZIONE_NULL = "Dove vuoi andare ?";
	public static final String DIREZIONE_INESISTENTE = "Direzione inesistente";
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
		IO ioconsole = partita.getIoconsole();
		if (this.direzione == null) {
			ioconsole.mostraMessaggio(DIREZIONE_NULL);
			return;
		}
		
		if(!direzioneIsCorretta()){
			ioconsole.mostraMessaggio(DIREZIONE_INESISTENTE);
			return;
		}
		
		
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			ioconsole.mostraMessaggio(DIREZIONE_INESISTENTE);
			return;
		} 
		partita.setStanzaCorrente(prossimaStanza);
		ioconsole.mostraMessaggio(partita.getStanzaCorrente().getNome());
		
		int cfu = partita.getGiocatore().getCfu();
		cfu--;
		partita.getGiocatore().setCfu(cfu);
	}

	public boolean direzioneIsCorretta() {
		return this.direzione.equals("sud") || this.direzione.equals("nord") ||
				this.direzione.equals("est") || this.direzione.equals("ovest");
	}

	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
}
