package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoGuarda implements Comando {

	@Override
	public void esegui(Partita partita) {
		IOConsole ioconsole = partita.getIoconsole();
		Stanza corrente = partita.getStanzaCorrente();

		ioconsole.mostraMessaggio(corrente.getNome());
		ioconsole.mostraMessaggio(corrente.getDescrizione());

		ioconsole.mostraMessaggio("Mappa:" + partita.getLabirinto().getNome());
		ioconsole.mostraMessaggio("Giocatore:" + partita.getGiocatore().getNome());
		ioconsole.mostraMessaggio("CFU:" + partita.getGiocatore().getCfu());

		if (partita.isFinita())
			ioconsole.mostraMessaggio("Partita finita");
		else
			ioconsole.mostraMessaggio("Partita in corso");

	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}

}
