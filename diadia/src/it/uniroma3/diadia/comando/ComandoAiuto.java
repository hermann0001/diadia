package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

/**
 * Stampa informazioni di aiuto.
 */
public class ComandoAiuto implements Comando {
	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa", "guarda" };

	@Override
	public void esegui(Partita partita) {
		final IO ioconsole= partita.getIoconsole();
		for (int i = 0; i < elencoComandi.length; i++)
			ioconsole.mostraMessaggio(elencoComandi[i] + " ");
		ioconsole.mostraMessaggio("");
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub

	}
}
