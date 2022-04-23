package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella una mappa della partita
 *
 * @author Hermann Tamilia
 * @see Partita
 * @see Stanza
 * @version base
 */

public class Labirinto {
	private Stanza stanzaIniziale; // stanza di entrata
	private Stanza stanzaFinale; // stanza di uscita
	String nome; // nome del labirinto

	public Labirinto(String nome) {
		this.nome = nome;
		this.creaStanze();
	}

	/**
	 * Crea tutte le stanze e le porte di collegamento
	 */
	private void creaStanze() {

		/* crea gli attrezzi */
		Attrezzo lanterna = new Attrezzo("lanterna", 3);
		Attrezzo osso = new Attrezzo("osso", 1);

		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");

		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

		/* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
		this.stanzaIniziale = atrio;
		this.stanzaFinale = biblioteca;
	}

	/**
	 * Imposta la stanza finale/vincente
	 * 
	 * @param stanzaFinale
	 */
	public void setStanzaFinale(Stanza stanzaFinale) {
		this.stanzaFinale = stanzaFinale;
	}

	/**
	 * Restituisce la stanza finale/vincente
	 *
	 * @return stanzaFinale
	 */
	public Stanza getStanzaFinale() {
		return stanzaFinale;
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
