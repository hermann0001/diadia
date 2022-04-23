package it.uniroma3.diadia;

/**
 * Implementazione dell'interfaccia IO affinché i comandi eseguiti e i messaggi 
 * stampati non siano più legati al system.in (tastiera) o system.out(schermo)
 * ma a semplici strutture dati (Array). In questo modo risuciamo a simulare 
 * intere partite senza l'interazione "umana".
 * @author Hermann Tamilia
 * @version hw2
 */

public class IOSimulator implements IO {

	private String righeDaLeggere[];		//l'array di righe da leggere
	private String messaggiProdotti[];		//l'array di messagi prodotti dal gioco
	private int indiceRigheLette;			//un indice per contare quanti comandi sono stati letti
	private int indiceMessaggiProdotti;		//un indice per contare quanti messaggi sono stati prodotti dal gioco
	private int indiceMessaggiStampati;		//un indice per contare quanti messaggi sono stati "visti" / "visitati"

	/**
	 * istanzia i due array con un valore predefinito
	 * di 100 elementi e imposta gli indici a 0
	 */
	public IOSimulator() {
		this.righeDaLeggere = new String[100];
		this.messaggiProdotti = new String[100];
		this.indiceRigheLette = 0;
		this.indiceMessaggiProdotti = 0;
		this.indiceMessaggiStampati = 0;
	}
	
	/**
	 * Salva i messaggi prodotti nell'array e incrementa il contatore
	 * 
	 * @param messaggio messaggio prodotto
	 */
	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiProdotti[this.indiceMessaggiProdotti] = messaggio;
		this.indiceMessaggiProdotti++;
	}
	
	/**
	 * Prende i comandi dall'array delle righe da leggere, incrementa il contatore
	 * e lo salva nella variabile riga letta
	 * 
	 * @return il comando appena letto
	 */
	@Override
	public String leggiRiga() {
		String rigaLetta = this.righeDaLeggere[this.indiceRigheLette];
		this.indiceRigheLette++;
		return rigaLetta;
	}

	/**
	 * riempie l'array righeDaLeggere(this) con l'array di comandi da eseguire
	 * 
	 * @param righeDaLeggere un array di dimensioni indefinite di comandi da eseguire
	 */
	public void setInput(String... righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
	}
	
	/**
	 * accede alle stringhe prodotte 
	 * 
	 * @return messaggio i-esimo
	 */
	public String nextMessaggio() {
		String next = this.messaggiProdotti[this.indiceMessaggiStampati];
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
