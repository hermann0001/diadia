package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.Direzione;

/**
 * La stanza bloccata è un particolare sottotipo di stanza che possiede una direzione
 * bloccata che può essere sbloccata solo da un particolare attrezzo
 * 
 * @author Hermann Tamilia
 * @see Stanza
 * @version hw2
 */

public class StanzaBloccata extends Stanza {
	private Direzione direzioneBloccata;
	private String attrezzoSbloccaDirezione;

	public StanzaBloccata(String nome, Direzione direzioneBloccata, String attrezzoSbloccaDirezione) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccaDirezione = attrezzoSbloccaDirezione;
	}

	@Override
	public String getDescrizione() {
		if (!this.hasAttrezzo(attrezzoSbloccaDirezione)) {
			String messaggio = "La stanza è bloccata.\nDirezione bloccata: " + this.direzioneBloccata
					+ ".\nServe un attrezzo particolare " + "per sbloccarla.\nAttrezzo per sbloccare la stanza: "
					+ this.attrezzoSbloccaDirezione;
			return messaggio;
		}
		return toString();
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if (!this.hasAttrezzo(this.attrezzoSbloccaDirezione))
			return this;

		return super.getStanzaAdiacente(direzione);
	}
}
