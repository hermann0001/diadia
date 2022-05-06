package it.uniroma3.diadia.ambienti;

/**
 * Questa classe modella una mappa della partita
 *
 * @author Hermann Tamilia
 * @see Partita
 * @see Stanza
 * @version hw2
 */

public class Labirinto {
	private Stanza stanzaIniziale; // stanza di entrata
	private Stanza stanzaVincente; // stanza di uscita
	String nome; // nome del labirinto
	
	/**
	 * Imposta la stanza finale/vincente
	 * 
	 * @param stanzaVincente
	 */
	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	/**
	 * Restituisce la stanza finale/vincente
	 *
	 * @return stanzaFinale
	 */
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	/**
	 * Imposta la stanza iniziale/entrata
	 * 
	 * @param stanzaIniziale
	 */
	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	/**
	 * Restituisce la stanza iniziale/entrata
	 *
	 * @return stanzaIniziale
	 */
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	/**
	 * Ritorna il nome del giocatore
	 * 
	 * @return String
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Imposta il nome del giocatore
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
}
