package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

/**
 * Comando: aiuto
 * Stampa informazioni di aiuto.
 * 
 * @author Hermann Tamilia
 * @see Comando
 * @version hw2
 */
public class ComandoAiuto extends AbstractComando {
	protected ComandoAiuto() {
		super("aiuto");
	}

	final public static String[] ELENCO_COMANDI = {"aiuto, vai, guarda, posa, prendi, fine"};
	
	@Override
	public void esegui(Partita partita) {
		this.io = partita.getIoconsole();
		for (int i = 0; i < ELENCO_COMANDI.length; i++)
			this.io.mostraMessaggio(ELENCO_COMANDI[i] + " ");
	}
}
