package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
/**
 * Questa classe astrattta modella un comando. Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro su cui si applica il comando. (Ad es.
 * alla riga digitata dall'utente "vai nord" corrisponde un comando di nome
 * "vai" e parametro "nord").
 *
 * @author docente di POO
 * @version hw4
 */

public abstract class AbstractComando {
	
	private String nome;
	private String parametro;
	protected IO io;

	protected AbstractComando(String nome, String parametro) {
		this.nome = nome;
		this.parametro = parametro;
		
	}
	
	protected AbstractComando(String nome) {
		this.nome = nome;
	}
	
	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	
	public abstract void esegui(Partita partita);
}
