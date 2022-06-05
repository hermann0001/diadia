package it.uniroma3.diadia.giocatore;

import java.util.*;

import it.uniroma3.diadia.ConfigurazioniIniziali;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatorePerNome;
import it.uniroma3.diadia.attrezzi.ComparatorePerPesoPerNome;

/**
 * Questa classe modella una borsa contente gli attrezzi del gioco
 *
 * @author docente di POO
 * @author Hermann Tamilia
 * @see Attrezzo
 * @version base
 */

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = ConfigurazioniIniziali.getPesoMax();
	private List<Attrezzo> attrezzi;						
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
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
		int indice = this.attrezzi.indexOf(new Attrezzo(nomeAttrezzo, 0));
		if(indice != -1)
			return this.attrezzi.get(indice);

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
		return (this.getAttrezzo(nomeAttrezzo) != null);
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
		
		int indice = this.attrezzi.indexOf(new Attrezzo(nomeAttrezzo,0));
		if(indice != -1)
			return this.attrezzi.remove(indice);
		return null;
	}
	
	public List<Attrezzo> getAttrezzi() {
		return this.attrezzi;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			List<Attrezzo> attrezziOrdinati = this.getContenutoOrdinatoPerPeso();
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for(Attrezzo a : attrezziOrdinati)
				s.append(a.toString());
		} else
			s.append("Borsa vuota");
		return s.toString();
	}
	
	
	List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List <Attrezzo> list = new ArrayList<>(this.attrezzi);
		list.sort(new ComparatorePerPesoPerNome());
		return list;
	}
	
	SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> set = new TreeSet<>(new ComparatorePerNome());
		set.addAll(this.attrezzi);
		return set;
	}
	
	Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> mappa = new HashMap<>();
		
		for(Attrezzo a : this.attrezzi) {
			int peso = a.getPeso();
			if(a != null) {
				if(mappa.containsKey(peso)) {
					Set<Attrezzo> setAttrezziDelloStessoPeso = mappa.get(peso);
					setAttrezziDelloStessoPeso.add(a);
				} else {
					//Non ho mai visto questo peso
					Set<Attrezzo> attrezziSet = new HashSet<>();
					attrezziSet.add(a);
					mappa.put(peso, attrezziSet);
				}
			}
		}
		return mappa;
	}
	
	SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> setAttrezzi = new TreeSet<>(new ComparatorePerPesoPerNome());
		setAttrezzi.addAll(this.attrezzi);
		return setAttrezzi;
	}
}
