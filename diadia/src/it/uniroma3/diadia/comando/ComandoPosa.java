package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Comando: posa
 * 
 * @author Hermann Tamilia
 * @see Comando
 * @version hw2
 * @param nomeAttrezzo attrezzo da posare
 */

public class ComandoPosa extends AbstractComando {
	
	public static final String ATTREZZO_NULL = "Quale attrezzo vuoi raccogliere?";
	public static final String ATTREZZO_NON_PRESENTE = "L'attrezzo non esiste nella borsa";
	public static final String ATTREZZO_POSATO = "Oggetto posato!";
	private String nomeAttrezzo;

	public ComandoPosa() {
		super("posa");
	}
	
	public ComandoPosa(String nomeAttrezzo) {
		super("posa", nomeAttrezzo);
	}

	@Override
	public void esegui(Partita partita) {
		this.io = partita.getIoconsole();
		this.nomeAttrezzo = super.getParametro();

		Borsa borsa = partita.getGiocatore().getBorsa();

		if (this.nomeAttrezzo == null) {
			this.io.mostraMessaggio(ATTREZZO_NULL);
			return;
		}
		if (borsa.hasAttrezzo(this.nomeAttrezzo) == false) {
			this.io.mostraMessaggio(ATTREZZO_NON_PRESENTE);
			return;
		}
		Attrezzo a = borsa.removeAttrezzo(this.nomeAttrezzo);
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if (stanzaCorrente.addAttrezzo(a) == false) {
			this.io.mostraMessaggio("L'attrezzo non esiste nella stanza");
			return;
		}

		this.io.mostraMessaggio(ATTREZZO_POSATO);
		return;
	}
}
