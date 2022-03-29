package it.uniroma3.diadia.giocatore;

/**
 * Questa classe modella un giocatore
 *
 * @author Hermann Tamilia
 * @see Borsa
 * @version base
 */

public class Giocatore {
	private String nome;
	private int cfu;
	private Borsa borsa;
	static final private int CFU_INIZIALI = 20;

	public Giocatore(String nome) {
		this.nome = nome;
		this.borsa = new Borsa();
		this.cfu = CFU_INIZIALI;
	}

	/**
	 * Ritorna l'attuale borsa
	 * 
	 * @return Borsa
	 */
	public Borsa getBorsa() {
		return this.borsa;
	}

	/**
	 * Ritorna il numero di cfu del giocatore
	 * 
	 * @return int
	 */
	public int getCfu() {
		return this.cfu;
	}

	/**
	 * Imposta il numero di cfu del giocatore
	 * 
	 * @param cfu
	 */
	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
}
