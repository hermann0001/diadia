package it.uniroma3.diadia.ambienti;

/**
 * La stanza buia è un particolare sottotipo di stanza che non permetterà di
 * leggere la sua descrizione a meno che il giocatore non abbia un attrezzo particolare
 * che gli permetta di illuminare dalla stanza.
 * 
 * @author Hermann Tamilia
 * @see Stanza
 * @version hw2
 *
 */
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
