package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Comando: guarda
 * Ottiene informazioni sullo stato della partita
 * 
 * @author Hermann Tamilia
 * @see Comando
 * @version hw2
 */
public class ComandoGuarda extends AbstractComando {
	
	
	protected ComandoGuarda() {
		super("guarda");
	}

	@Override
	public void esegui(Partita partita) {
		this.io = partita.getIoconsole();

		
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Borsa borsaCorrente = partita.getGiocatore().getBorsa();

		this.io.mostraMessaggio(stanzaCorrente.getDescrizione());
		this.io.mostraMessaggio(borsaCorrente.toString());
		this.io.mostraMessaggio("Mappa:" + partita.getLabirinto().getNome());
		this.io.mostraMessaggio("Giocatore:" + partita.getGiocatore().getNome());
		this.io.mostraMessaggio("CFU:" + partita.getGiocatore().getCfu());
	}
}
