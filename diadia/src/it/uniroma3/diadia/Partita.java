package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {
	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	private boolean finita;
	private Giocatore giocatore;
	private IOConsole io;

	public Partita(IOConsole io) {
		this.giocatore = new Giocatore("Player 1"); // TODO inserire nome da tastiera
		this.labirinto = new Labirinto("Mappa 1"); // TODO inserire nome da tastiera
		this.finita = false;
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.io = io;
	}

	/**
	 * Imposta stanzaCorrente come stanza corrente
	 * 
	 * @param stanzaCorrente
	 */
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	/**
	 * Ritorna la stanzaCorrente
	 * 
	 * @return Stanza
	 */
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

	/**
	 * Ritorna il labirinto attuale della partita
	 * 
	 * @return Labirinto
	 */
	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * 
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		if (this.labirinto.getStanzaFinale() == null)
			return false;

		return this.getStanzaCorrente() == this.labirinto.getStanzaFinale();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * 
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return this.finita || this.vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	/**
	 * Ritorna l'attuale giocatore
	 * 
	 * @return Giocatore
	 */
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	public IOConsole getIoconsole() {
		return this.io;
	}

	public boolean giocatoreIsVivo() {
		if(this.getGiocatore().getCfu() > 0)
			return true;
		return false;
	}
}
