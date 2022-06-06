package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Direzione;
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

	private static final String STANZA_INESISTENTE = "Non ci sono uscite per quella direzione...";
	public static final String DIREZIONE_NULL = "Direzione non valida...\nDove vuoi andare ?";
	private Direzione direzione;
	
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
		try {
			this.direzione = Direzione.valueOf(super.getParametro().toUpperCase());
		}catch(NullPointerException |IllegalArgumentException e) {
			partita.getIoconsole().mostraMessaggio(DIREZIONE_NULL);
			return;
		}
		this.io = partita.getIoconsole();
		
		Stanza prossimaStanza = null;

		Stanza stanzaCorrente = partita.getStanzaCorrente();
		prossimaStanza = stanzaCorrente.getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			this.io.mostraMessaggio(STANZA_INESISTENTE);
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		this.io.mostraMessaggio(partita.getStanzaCorrente().getNome());

		int cfu = partita.getGiocatore().getCfu();
		cfu--;
		partita.getGiocatore().setCfu(cfu);
	}

//	public boolean direzioneIsCorretta() {
//		return this.direzione.equals("sud") || this.direzione.equals("nord") || this.direzione.equals("est")
//				|| this.direzione.equals("ovest");
//	}
}
