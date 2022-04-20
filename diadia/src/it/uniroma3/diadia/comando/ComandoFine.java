package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;


/**
 * Comando "Fine".
 */

public class ComandoFine implements Comando {

	@Override
	public void esegui(Partita partita) {
		final IO ioconsole = partita.getIoconsole();
		ioconsole.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
	}

}
