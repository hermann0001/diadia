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

	final public static String[] ELENCO_COMANDI = {"aiuto", "vai", "guarda", "posa", "prendi", "saluta", "interagisci", "regala", "fine"};
	
	@Override
	public void esegui(Partita partita) {
		this.io = partita.getIoconsole();
		for (String comando : ELENCO_COMANDI)
			this.io.mostraMessaggio(comando + " ");
	}
}
