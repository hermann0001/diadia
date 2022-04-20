package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	private String nomeAttrezzoLuce;

	public StanzaBuia(String nome, String nomeAttrezzoLuce) {
		super(nome);
		this.nomeAttrezzoLuce = nomeAttrezzoLuce;
	}

	@Override
	public String getDescrizione() {
		if (!this.hasAttrezzo(this.nomeAttrezzoLuce)) {
			String messaggio = "qui c'è un buio pesto";
			return messaggio;
		}
		return this.toString();
	}

}
