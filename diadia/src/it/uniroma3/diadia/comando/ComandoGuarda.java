package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

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

		
		final Stanza stanzaCorrente = partita.getStanzaCorrente();
		final Borsa borsaCorrente = partita.getGiocatore().getBorsa();
		final AbstractPersonaggio personaggio = stanzaCorrente.getPersonaggio();


		this.io.mostraMessaggio(stanzaCorrente.getDescrizione());
		this.io.mostraMessaggio(borsaCorrente.toString());
		
		if(personaggio != null) {
			this.io.mostraMessaggio(personaggio.getClass().getSimpleName() + " " + personaggio.getNome());
		}
		
		this.io.mostraMessaggio("Mappa:" + partita.getLabirinto().getNome());
		this.io.mostraMessaggio("Giocatore:" + partita.getGiocatore().getNome());
		this.io.mostraMessaggio("CFU:" + partita.getGiocatore().getCfu());
	}
}
