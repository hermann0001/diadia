package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IOConsole;
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

	private String nomeAttrezzo;

	@Override
	public void esegui(Partita partita) {
		final IOConsole ioconsole = partita.getIoconsole();
		
		if(this.nomeAttrezzo == null) {
			ioconsole.mostraMessaggio("Quale attrezzo vuoi raccogliere?");
			return;
		}

		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if (stanzaCorrente.hasAttrezzo(this.nomeAttrezzo) == false) {
			ioconsole.mostraMessaggio("L'attrezzo non esiste nella stanza");
			return;
		}

		Borsa borsa = partita.getGiocatore().getBorsa();
		Attrezzo a = stanzaCorrente.getAttrezzo(this.nomeAttrezzo);
		stanzaCorrente.removeAttrezzo(this.nomeAttrezzo);
		if (borsa.addAttrezzo(a) == false) {
			ioconsole.mostraMessaggio("L'attrezzo non esiste nella borsa");
			return;
		}
		ioconsole.mostraMessaggio("Oggetto preso!");
		return;
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

}
