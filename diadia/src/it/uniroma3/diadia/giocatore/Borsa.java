package it.uniroma3.diadia.giocatore;

import java.util.HashSet;
import java.util.Set;

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
	private Set<Attrezzo> attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;

	public Borsa() {
		this.pesoMax = DEFAULT_PESO_MAX_BORSA;
		this.attrezzi = new HashSet<Attrezzo>();
		this.numeroAttrezzi = 0;
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashSet<Attrezzo>(); // speriamo che bastino...
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
		
		this.attrezzi.add(attrezzo);
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
		for (Attrezzo a : this.attrezzi) {
			if (a.getNome().equals(nomeAttrezzo))
				 return a;
		}
		return null;
	}

	/**
	 * Restituisce il peso attuale della borsa
	 * 
	 * @return int
	 */
	public int getPeso() {
		int peso = 0;
		for (Attrezzo a : this.attrezzi)
			peso += a.getPeso();
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
		if(this.numeroAttrezzi == 0) return null;

		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo.getNome().equals(nomeAttrezzo)) {
				this.attrezzi.remove(attrezzo);
				this.numeroAttrezzi--;
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
