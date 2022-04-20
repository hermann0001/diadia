package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella una borsa contente gli attrezzi del gioco
 *
 * @author docente di POO
 * @author Hermann Tamilia
 * @see Attrezzo
 * @version base
 */

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this.pesoMax = DEFAULT_PESO_MAX_BORSA;
		this.attrezzi = new Attrezzo[10]; // speriamo che bastino...
		this.numeroAttrezzi = 0;
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo che bastino...
		this.numeroAttrezzi = 0;
	}

	/**
	 * Aggiunge un attrezzo alla borsa
	 * 
	 * @param attrezzo
	 * @return boolean
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(attrezzo == null) return false;
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi == 10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}

	/**
	 * Restituisce il peso massimo della borsa
	 * 
	 * @return int
	 */
	public int getPesoMax() {
		return this.pesoMax;
	}

	/**
	 * Restituisce un attrezzo nella borsa
	 * 
	 * @param nomeAttrezzo
	 * @return attrezzo
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i = 0; i < this.numeroAttrezzi; i++) {
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = this.attrezzi[i];
		}
		return a;
	}

	/**
	 * Restituisce il peso attuale della borsa
	 * 
	 * @return int
	 */
	public int getPeso() {
		int peso = 0;
		for (int i = 0; i < this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();
		return peso;
	}

	/**
	 * Restituisce true se la borsa ha 0 attrezzi cioè è vuota, altrimenti false
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}

	/**
	 * Ritorna true se nomeAttrezzo è contenuto nella borsa, false altrimenti
	 * 
	 * @param nomeAttrezzo
	 * @return boolean
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}

	/**
	 * Rimuove un attrezzo dalla borsa
	 * 
	 * @param nomeAttrezzo
	 * @return attrezzo
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = this.getAttrezzo(nomeAttrezzo);
		if (a == null || this.numeroAttrezzi == 0)
			return null;

		for (int i = 0; i < this.numeroAttrezzi; i++) {
			if (this.attrezzi[i] == a) {
				this.attrezzi[i] = this.attrezzi[this.numeroAttrezzi - 1];
				this.attrezzi[this.numeroAttrezzi - 1] = null;
				this.numeroAttrezzi--;
			}
		}
		return a;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg):");
			for (int i = 0; i < this.numeroAttrezzi; i++)
				s.append(this.attrezzi[i].toString() + " ");
		} else
			s.append("Borsa vuota");
		return s.toString();
	}
}
