package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

/**
 * gestisce il caso in cui si inserisca un comando non valido
 * 
 * @author Hermann Tamilia
 * @see Comando
 * @version hw2
 */
public class ComandoNonValido extends AbstractComando {
	
	private Exception exception;
	public static final String COMANDO_NON_VALIDO = "Comando non valido";
	
	public ComandoNonValido(ReflectiveOperationException e) {
		super("nonValido");
		this.exception = e;
	}

	
	public ComandoNonValido(IllegalArgumentException e) {
		super("nonValido");
		this.exception = e;
	}


	public Exception getException() {
		return this.exception;
	}
	
	@Override
	public void esegui(Partita partita) {
		partita.getIoconsole().mostraMessaggio(COMANDO_NON_VALIDO);

	}
}
