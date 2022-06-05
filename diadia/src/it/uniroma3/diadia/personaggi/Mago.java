package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {

	public static final String MESSAGGIO_REGALO = "Ho fatto una magia all'attrezzo... ma mi è caduto!";
	public static final String ATTREZZO_NON_VALIDO = "attrezzo non valido";
	public static final String MESSAGGIO_DONO = "Sei un vero simpaticone, "
			+ "con una mia magica azione, troverai un nuovo oggetto " + "per il tuo borsone!";
	public static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";

	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		super.setAttrezzo(attrezzo);
	}
	
	/**
	 * un mago possiede un attrezzo che può donare
	 * 
	 * @param partita
	 * @return messaggio
	 */
	@Override
	public String agisci(Partita partita) {
		String msg = null;
		Attrezzo attrezzo = super.getAttrezzo();
		if (attrezzo != null) {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			attrezzo = null;
			msg = MESSAGGIO_DONO;
		} else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}
	
	
	/**
	 * un mago riceve un regalo, gli dimezza il peso e lo lascia cadere nella stanza
	 * @param attrezzo, partita
	 * @return string messaggio
	 */
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo == null)
			return ATTREZZO_NON_VALIDO;

		Attrezzo attrezzoTrasformato = new Attrezzo(attrezzo.getNome(), attrezzo.getPeso() / 2);
		partita.getStanzaCorrente().addAttrezzo(attrezzoTrasformato);

		return MESSAGGIO_REGALO;
	}

}
