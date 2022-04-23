package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */
public class ComandoAiuto implements Comando {
	public final static String[] ELENCO_COMANDI = { "vai <direzione>", "aiuto", "fine", "prendi <nome attrezzo>", "posa <nome attrezzo>", "guarda" };

	@Override
	public void esegui(Partita partita) {
		final IO ioconsole= partita.getIoconsole();
		for (int i = 0; i < ELENCO_COMANDI.length; i++)
			ioconsole.mostraMessaggio(ELENCO_COMANDI[i] + " ");
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}
}
