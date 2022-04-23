package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

/**
 * Questa interface modella un comando. Un comando consiste al piu' di due parole:
 * il nome del comando ed un parametro su cui si applica il comando. (Ad es.
 * alla riga digitata dall'utente "vai nord" corrisponde un comando di nome
 * "vai" e parametro "nord").
 *
 * @author docente di POO
 * @version hw2
 */

public interface Comando {
	
	public void esegui(Partita partita);
	public void setParametro(String parametro);

}
