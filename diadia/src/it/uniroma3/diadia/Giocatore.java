package it.uniroma3.diadia;

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
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		return this.borsa.addAttrezzo(attrezzo);
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		return this.borsa.removeAttrezzo(nomeAttrezzo);
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.borsa.getAttrezzo(nomeAttrezzo);
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.borsa.hasAttrezzo(nomeAttrezzo);
	}
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}	
}
