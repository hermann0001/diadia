package it.uniroma3.diadia.ambienti;

import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo. Una stanza e' un luogo
 * fisico nel gioco. E' collegata ad altre stanze attraverso delle uscite. Ogni
 * uscita e' associata ad una direzione.
 * 
 * @author docente di POO
 * @see Attrezzo
 * @version protected
 */

public class StanzaProtected {

	static final public int NUMERO_MASSIMO_ATTREZZI = 10;

	private String nome;
	protected Map<String, Attrezzo> attrezzi;
	private Map<String, StanzaProtected> stanzeAdiacenti;

	/**
	 * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * 
	 * @param nome il nome della stanza
	 */
	public StanzaProtected(String nome) {
		this.nome = nome;
		this.stanzeAdiacenti = new HashMap<>();
		this.attrezzi = new HashMap<>();
	}

	/**
	 * Imposta una stanza adiacente.
	 *
	 * @param direzione direzione in cui sara' posta la stanza adiacente.
	 * @param stanza    stanza adiacente nella direzione indicata dal primo
	 *                  parametro.
	 */
	public void impostaStanzaAdiacente(String direzione, StanzaProtected stanza) {
		if((stanza != null) && (!this.nome.equals(stanza.getNome())))
			this.stanzeAdiacenti.put(direzione, stanza);
	}

	/**
	 * Restituisce la stanza adiacente nella direzione specificata
	 * 
	 * @param direzione
	 */
	public StanzaProtected getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	/**
	 * Restituisce la nome della stanza.
	 * 
	 * @return il nome della stanza
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Restituisce la descrizione della stanza.
	 * 
	 * @return la descrizione della stanza
	 */
	public String getDescrizione() {
		return this.toString();
	}

	/**
	 * Restituisce la collezione di attrezzi presenti nella stanza.
	 * 
	 * @return la collezione di attrezzi nella stanza.
	 */
	public Map<String, Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	/**
	 * Mette un attrezzo nella stanza.
	 * 
	 * @param attrezzo l'attrezzo da mettere nella stanza.
	 * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		return false;
	}

	/**
	 * Restituisce una rappresentazione stringa di questa stanza, stampadone la
	 * descrizione, le uscite e gli eventuali attrezzi contenuti
	 * 
	 * @return la rappresentazione stringa
	 */
	public String toString() {
		StringBuilder risultato = new StringBuilder();
		risultato.append(this.nome);
		risultato.append("\nUscite: ");
		risultato.append(this.stanzeAdiacenti.keySet());
		risultato.append("\nAttrezzi nella stanza: ");
		risultato.append(this.attrezzi.values().toString() + " ");

		return risultato.toString();
	}

	/**
	 * Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	 * 
	 * @return true se l'attrezzo esiste nella stanza, false altrimenti.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		if (this.attrezzi.get(nomeAttrezzo) == null)
			return false;
		return true;
	}

	/**
	 * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * 
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza. null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * 
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(String nomeAttrezzo) {
		if (this.attrezzi.remove(nomeAttrezzo) == null)
			return false;
		return true;
	}
	/**
	 * Ottiene le adiacenze della stanza
	 * 
	 * @return i valori dell'hashMap<> delle stanze adiacenti
	 */
	public Collection<StanzaProtected> getAdiacenze() {
		return this.stanzeAdiacenti.values();
	}

	@Override
	public boolean equals(Object o) {
		StanzaProtected that = (StanzaProtected) o;
		if (this.getNome() == that.getNome())
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}

}
