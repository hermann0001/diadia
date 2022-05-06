package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author Hermann Tamilia
 * @see Stanza
 * @see Labrinto
 * @see Giocatore
 * @version hw2
 */

public class Partita {
	private Labirinto labirinto;
	private Stanza stanzaCorrente;
	private boolean finita;
	private Giocatore giocatore;
	private IO io;

	public Partita(IO io, Labirinto labirinto) {
		this.giocatore = new Giocatore("Player 1"); 
		this.labirinto = labirinto; 
		this.finita = false;
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.io = io;
	}

	public Partita(IO io) {
		this.giocatore = new Giocatore("Player 1"); 
		this.labirinto = DiaDia.creaMappaPredefinita(); 
		this.finita = false;
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.io = io;	}

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
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}

	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * 
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		if (this.labirinto.getStanzaVincente() == null)
			return false;

		return this.getStanzaCorrente() == this.labirinto.getStanzaVincente();
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

	public IO getIoconsole() {
		return this.io;
	}

	public boolean giocatoreIsVivo() {
		return (this.getGiocatore().getCfu() > 0);
	}
}
