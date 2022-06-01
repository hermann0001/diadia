package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Comando: prendi
 * prende un attrezzo da una stanza
 * 
 * @param nomeAttrezzo attrezzo da prendere
 * @author Hermann Tamilia
 * @see Comando
 * @version hw2
 */
public class ComandoPrendi extends AbstractComando {
	
	public static final String ATTREZZO_NULL = "Quale attrezzo vuoi raccogliere?";
	public static final String ATTREZZO_NON_PRESENTE = "L'attrezzo non esiste nella stanza";
	public static final String ATTREZZO_PRESO = "Oggetto preso!";
	private String nomeAttrezzo;

	public ComandoPrendi() {
		super("prendi");
	}
	
	public ComandoPrendi(String nomeAttrezzo) {
		super("prendi", nomeAttrezzo);
	}

	@Override
	public void esegui(Partita partita) {
		this.nomeAttrezzo = super.getParametro();
		this.io = partita.getIoconsole();
		if (this.nomeAttrezzo == null) {
			this.io.mostraMessaggio(ATTREZZO_NULL);
			return;
		}

		Stanza stanzaCorrente = partita.getStanzaCorrente();
		if (stanzaCorrente.hasAttrezzo(this.nomeAttrezzo) == false) {
			this.io.mostraMessaggio(ATTREZZO_NON_PRESENTE);
			return;
		}

		Borsa borsa = partita.getGiocatore().getBorsa();
		Attrezzo a = stanzaCorrente.getAttrezzo(this.nomeAttrezzo);
		stanzaCorrente.removeAttrezzo(this.nomeAttrezzo);
		if (borsa.addAttrezzo(a) == false) {
			this.io.mostraMessaggio("Qualcosa è andato storto!");
			return;
		}
		this.io.mostraMessaggio(ATTREZZO_PRESO);
		return;
	}
}
