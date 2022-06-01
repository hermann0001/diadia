package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

/**
 * Comando: fine
 * Termina la partita
 * 
 * @author Hermann Tamilia
 * @see Comando
 * @version hw2
 */

public class ComandoFine extends AbstractComando {
	protected ComandoFine() {
		super("fine");
	}

	public static final String MESSAGGIO_FINE = "Grazie di aver giocato!";
	
	@Override
	public void esegui(Partita partita) {
		this.io = partita.getIoconsole();
		
		this.io.mostraMessaggio(MESSAGGIO_FINE); // si desidera smettere
		partita.setFinita();
	}
}
