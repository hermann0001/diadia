package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Comando: vai
 * permette di muoversi nella mappa
 * 
 * @author Hermann Tamilia
 * @see Comando
 * @version hw2
 * @param direzione
 */
public class ComandoVai extends AbstractComando {

	public static final String DIREZIONE_NULL = "Dove vuoi andare ?";
	public static final String DIREZIONE_INESISTENTE = "Direzione inesistente";
	private String direzione;
	
	protected ComandoVai() {
		super("vai");
	}
	
	public ComandoVai(String direzione) {
		super("vai", direzione);
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	@Override
	public void esegui(Partita partita) {
		this.direzione = super.getParametro();
		this.io = partita.getIoconsole();
		
		// qui il codice per cambiare stanza ...}}
		Stanza prossimaStanza = null;
		if (this.direzione == null) {
			this.io.mostraMessaggio(DIREZIONE_NULL);
			return;
		}

		if (!direzioneIsCorretta()) {
			this.io.mostraMessaggio(DIREZIONE_INESISTENTE);
			return;
		}

		Stanza stanzaCorrente = partita.getStanzaCorrente();
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			this.io.mostraMessaggio(DIREZIONE_INESISTENTE);
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		this.io.mostraMessaggio(partita.getStanzaCorrente().getNome());

		int cfu = partita.getGiocatore().getCfu();
		cfu--;
		partita.getGiocatore().setCfu(cfu);
	}

	public boolean direzioneIsCorretta() {
		return this.direzione.equals("sud") || this.direzione.equals("nord") || this.direzione.equals("est")
				|| this.direzione.equals("ovest");
	}
}
