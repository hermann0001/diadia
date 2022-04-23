package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Comando: prendi
 * 
 * @param nomeAttrezzo
 */
public class ComandoPrendi implements Comando {

	public static final String ATTREZZO_NULL = "Quale attrezzo vuoi raccogliere?";
	public static final String ATTREZZO_NON_PRESENTE = "L'attrezzo non esiste nella stanza";
	public static final String ATTREZZO_PRESO = "Oggetto preso!";
	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		final IO ioconsole = partita.getIoconsole();
		
		if(this.nomeAttrezzo == null) {
			ioconsole.mostraMessaggio(ATTREZZO_NULL);
			return;
		}

		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if (stanzaCorrente.hasAttrezzo(this.nomeAttrezzo) == false) {
			ioconsole.mostraMessaggio(ATTREZZO_NON_PRESENTE);
			return;
		}

		Borsa borsa = partita.getGiocatore().getBorsa();
		Attrezzo a = stanzaCorrente.getAttrezzo(this.nomeAttrezzo);
		stanzaCorrente.removeAttrezzo(this.nomeAttrezzo);
		if (borsa.addAttrezzo(a) == false) {
			ioconsole.mostraMessaggio("Qualcosa è andato storto!");
			return;
		}
		ioconsole.mostraMessaggio(ATTREZZO_PRESO);
		return;
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
}
