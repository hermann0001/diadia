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

	private List<String> righeDaLeggere;		//la lista di righe da leggere
	private List<String> messaggiProdotti;		//la lista di messagi prodotti dal gioco
	private int indiceRigheLette;			//un indice per contare quanti comandi sono stati letti
	private int indiceMessaggiProdotti;		//un indice per contare quanti messaggi sono stati prodotti dal gioco
	private int indiceMessaggiStampati;		//un indice per contare quanti messaggi sono stati "visti" / "visitati"

	/**
	 * istanzia due linkedList e imposta gli indici a 0
	 */
	public IOSimulator() {
		this.righeDaLeggere = new LinkedList<>();
		this.messaggiProdotti = new LinkedList<>();
		this.indiceRigheLette = 0;
		this.indiceMessaggiProdotti = 0;
		this.indiceMessaggiStampati = 0;
	}
	
	/**
	 * Salva i messaggi prodotti nella lista e incrementa il contatore
	 * 
	 * @param messaggio messaggio prodotto
	 */
	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiProdotti.add(messaggio);
		this.indiceMessaggiProdotti++;
	}
	
	/**
	 * Prende i comandi dalla lista delle righe da leggere, incrementa il contatore
	 * e lo salva nella variabile riga letta
	 * 
	 * @return il comando appena letto
	 */
	@Override
	public String leggiRiga() {
		String rigaLetta = this.righeDaLeggere.get(this.indiceRigheLette);
		this.indiceRigheLette++;
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
		String next = this.messaggiProdotti.get(this.indiceMessaggiStampati);
		this.indiceMessaggiStampati++;
		return next;
	}
	
	/**
	 * @return true se ci sono ancora messaggi prodotti, false altrimenti
	 */
	public boolean hasNextMessaggio() {
		return this.indiceMessaggiStampati < this.indiceMessaggiProdotti;
	}
}
