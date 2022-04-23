package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Comando: fine
 * Termina la partita
 * 
 * @author Hermann Tamilia
 * @see Comando
 * @version hw2
 */

public class ComandoFine implements Comando {

	public static final String MESSAGGIO_FINE = "Grazie di aver giocato!";

	@Override
	public void esegui(Partita partita) {
		final IO ioconsole = partita.getIoconsole();
		ioconsole.mostraMessaggio(MESSAGGIO_FINE); // si desidera smettere
		partita.setFinita();
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
	}

}
