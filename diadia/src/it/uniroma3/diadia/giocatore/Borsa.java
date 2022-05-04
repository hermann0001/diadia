package it.uniroma3.diadia.giocatore;

import java.util.*;

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
	private List<Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this.pesoMax = DEFAULT_PESO_MAX_BORSA;
		this.attrezzi = new ArrayList<Attrezzo>();
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>();
	}

	/**
	 * Aggiunge un attrezzo alla borsa
	 * 
	 * @param attrezzo
	 * @return boolean
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (attrezzo == null)
			return false;
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;

		this.attrezzi.add(attrezzo);
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
		for (Attrezzo attrezzo : this.attrezzi)
			if (attrezzo != null && attrezzo.getNome().equals(nomeAttrezzo))
				return attrezzo;

		return null;
	}

	/**
	 * Restituisce il peso attuale della borsa
	 * 
	 * @return int
	 */
	public int getPeso() {
		int peso = 0;
		for(Attrezzo a : this.attrezzi)
			peso += a.getPeso();
		return peso;
	}

	/**
	 * Restituisce true se la borsa ha 0 attrezzi cioè è vuota, altrimenti false
	 * 
	 * @return boolean
	 */
	public boolean isEmpty() {
		return this.attrezzi.size() == 0;
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
		if (this.attrezzi.size() == 0)
			return null;

		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo.getNome().equals(nomeAttrezzo)) {
				this.attrezzi.remove(attrezzo);
				return attrezzo;
			}
		}
		return null;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg):");
			for (Attrezzo a : this.attrezzi)
				s.append(a.toString() + " ");
		} else
			s.append("Borsa vuota");
		return s.toString();
	}
}
