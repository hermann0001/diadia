package it.uniroma3.diadia;

public class IOSimulator implements IO {
	
	private String righeDaLeggere[];
	private String messaggiProdotti[];
	private int indiceRigheLette;
	private int indiceMessaggiProdotti;

	public IOSimulator() {
		this.righeDaLeggere = new String[100];
		this.messaggiProdotti = new String[100];
		this.indiceRigheLette = 0;
		this.indiceMessaggiProdotti = 0;
	}
	
	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiProdotti[this.indiceMessaggiProdotti] = messaggio;
		this.indiceMessaggiProdotti++;
	}

	@Override
	public String leggiRiga() {
		String rigaLetta = this.righeDaLeggere[this.indiceRigheLette];
		this.indiceRigheLette++;
		return rigaLetta;
	}
	
	public void setInput(String... righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
	}
	
	public String nextMessaggio() {
		String next = this.messaggiProdotti[this.indiceMessaggiProdotti];
		this.indiceMessaggiProdotti++;
		return next;
	}
	
	public boolean hasNextMessaggio() {
		if(this.messaggiProdotti[this.indiceMessaggiProdotti + 1] == null)
			return false;
		return true;
	}
}
