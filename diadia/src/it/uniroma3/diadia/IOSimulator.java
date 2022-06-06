package it.uniroma3.diadia;

import java.util.*;

/**
 * Implementazione dell'interfaccia IO affinché i comandi eseguiti e i messaggi 
 * stampati non siano più legati al system.in (tastiera) o system.out(schermo)
 * ma a semplici strutture dati (Array). In questo modo risuciamo a simulare 
 * intere partite senza l'interazione "umana".
 * @author Hermann Tamilia
 * @version hw2
 */

public class IOSimulator implements IO {

	private List<String> righeDaLeggere;						//la lista di righe da leggere
	private int indiceRigheDaLeggere;							//un indice per contare quanti comandi sono stati letti
	private Map<Integer, List<String>> indice2messaggiProdotti;
	private int ultimoIndiceMappaMostrato;
	private int ultimoIndiceListaMostrato;

	/**
	 * istanzia due linkedList e imposta gli indici a 0
	 */
	public IOSimulator() {
		this.righeDaLeggere = new LinkedList<>();
		this.indice2messaggiProdotti = new HashMap<Integer, List<String>>();
		this.indiceRigheDaLeggere = 0;
		this.ultimoIndiceMappaMostrato = 0;
		this.ultimoIndiceListaMostrato = 0;
	}
	
	/**
	 * Salva i messaggi prodotti nella lista e la aggiunge alla mappa
	 * 
	 * @param messaggio messaggio prodotto
	 */
	@Override
	public void mostraMessaggio(String messaggio) {
		if(!this.indice2messaggiProdotti.containsKey(this.indiceRigheDaLeggere))
			this.indice2messaggiProdotti.put(this.indiceRigheDaLeggere, new LinkedList<String>());
		List<String> l = this.indice2messaggiProdotti.get(this.indiceRigheDaLeggere);
		l.add(messaggio);
	}
	
	/**
	 * Prende i comandi dalla lista delle righe da leggere, incrementa il contatore
	 * e lo salva nella variabile riga letta
	 * 
	 * @return il comando appena letto
	 */
	@Override
	public String leggiRiga() {
		String rigaLetta = this.righeDaLeggere.get(this.indiceRigheDaLeggere);
		this.indiceRigheDaLeggere++;
		return rigaLetta;
	}

	/**
	 * riempie la lista righeDaLeggere(this) con la lista di comandi da eseguire
	 * 
	 * @param righeDaLeggere un array di dimensioni indefinite di comandi da eseguire
	 */
	public void setInput(String... righeDaLeggere) {
		this.righeDaLeggere.addAll(Arrays.asList(righeDaLeggere));
	}
	
	/**
	 * accede alle stringhe prodotte 
	 * 
	 * @return messaggio i-esimo
	 */
	public String nextMessaggio() {
		List<String> messaggiDaMostrare = this.indice2messaggiProdotti.get(this.ultimoIndiceMappaMostrato);
		if(this.ultimoIndiceListaMostrato < messaggiDaMostrare.size()) {
			String messaggio = messaggiDaMostrare.get(this.ultimoIndiceListaMostrato);
			this.ultimoIndiceListaMostrato++;
			return messaggio;
		}
		this.ultimoIndiceListaMostrato = 0;
		this.ultimoIndiceMappaMostrato++;
		return this.nextMessaggio();
	}
	
	/**
	 * @return true se ci sono ancora messaggi prodotti, false altrimenti
	 */
	public boolean hasNextMessaggio() {
		List<String> messaggiDaMostrare = this.indice2messaggiProdotti.get(this.ultimoIndiceMappaMostrato);
		if(this.ultimoIndiceListaMostrato < messaggiDaMostrare.size()) 
			return true;
		else
			return this.indice2messaggiProdotti.containsKey(this.ultimoIndiceMappaMostrato +1);
	}
}
