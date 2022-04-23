package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Comando: posa
 * 
 * @param nomeAttrezzo
 */

public class ComandoPosa implements Comando {

	public static final String ATTREZZO_NULL = "Quale attrezzo vuoi raccogliere?";
	public static final String ATTREZZO_NON_PRESENTE = "L'attrezzo non esiste nella borsa";
	public static final String ATTREZZO_POSATO = "Oggetto posato!";
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {

		final IO ioconsole = partita.getIoconsole();
		Borsa borsa = partita.getGiocatore().getBorsa();

		if (this.nomeAttrezzo == null) {
			ioconsole.mostraMessaggio(ATTREZZO_NULL);
			return;
		}
		if (borsa.hasAttrezzo(this.nomeAttrezzo) == false) {
			ioconsole.mostraMessaggio(ATTREZZO_NON_PRESENTE);
			return;
		}
		Attrezzo a = borsa.removeAttrezzo(this.nomeAttrezzo);
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if (stanzaCorrente.addAttrezzo(a) == false) {
			ioconsole.mostraMessaggio("L'attrezzo non esiste nella stanza");
			return;
		}

		ioconsole.mostraMessaggio(ATTREZZO_POSATO);
		return;
	}

	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
}
