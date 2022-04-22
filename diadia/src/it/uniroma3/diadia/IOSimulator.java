package it.uniroma3.diadia;

public class IOSimulator implements IO {
	
	private String righeDaLeggere[];
	private String messaggiProdotti[];
	private int indiceRigheDaLeggere;
	private int indiceMessaggiProdotti;
	private int indiceMessaggiStampati;

	public IOSimulator(String... righeDaLeggere) {
		this.indiceRigheDaLeggere = 0;
		this.righeDaLeggere = righeDaLeggere;
		this.messaggiProdotti = new String[100];
		this.indiceMessaggiProdotti = 0;
		this.indiceMessaggiStampati = 0;
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiProdotti[this.indiceMessaggiProdotti] = messaggio;
		this.indiceMessaggiProdotti++;
	}

	@Override
	public String leggiRiga() {
		String rigaLetta = this.righeDaLeggere[this.indiceRigheDaLeggere];
		this.indiceRigheDaLeggere++;
		return rigaLetta;
	}
	
	public String nextMessaggio() {
		String next = this.messaggiProdotti[this.indiceMessaggiStampati];
		this.indiceMessaggiStampati++;
		return next;
	}
	
	public boolean hasNextMessaggio() {
		return this.indiceMessaggiStampati < this.indiceMessaggiProdotti;
	}
}
