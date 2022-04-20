package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	private String direzioneBloccata;
	private String attrezzoSbloccaDirezione;

	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccaDirezione) {
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

	public Stanza getStanzaAdiacente(String direzione) {
		if (!this.hasAttrezzo(this.attrezzoSbloccaDirezione))
			return this;

		return super.getStanzaAdiacente(direzione);
	}

}
