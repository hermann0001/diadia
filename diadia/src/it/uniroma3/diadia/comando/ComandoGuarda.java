package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;


/**
 * Comando: guarda
 * Ottiene informazioni sullo stato della partita
 * 
 * @author Hermann Tamilia
 * @see Comando
 * @version hw2
 */
public class ComandoGuarda implements Comando {

	@Override
	public void esegui(Partita partita) {
		final IO ioconsole = partita.getIoconsole();
		Stanza corrente = partita.getStanzaCorrente();

		ioconsole.mostraMessaggio(corrente.getDescrizione());

		ioconsole.mostraMessaggio("Mappa:" + partita.getLabirinto().getNome());
		ioconsole.mostraMessaggio("Giocatore:" + partita.getGiocatore().getNome());
		ioconsole.mostraMessaggio("CFU:" + partita.getGiocatore().getCfu());
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

}
