package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	public static final String MESSAGGIO_REGALO = "Che buono... wof";
	public static final String ATTREZZO_NON_VALIDO = "attrezzo non valido";
	public static final String MESSAGGIO = "Sei stato morso! I tuoi cfu sono: ";
	private String ciboPreferito;

	public Cane(String nome, String presentaz, String ciboPreferito) {
		super(nome, presentaz);
		this.ciboPreferito = ciboPreferito;
	}

	@Override
	public String agisci(Partita partita) {
		int cfu = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(cfu - 2);

		return MESSAGGIO + partita.getGiocatore().getCfu();
	}

	@Override
	public String riceviRegalo(Attrezzo regalo, Partita partita) {
		if (regalo == null)
			return ATTREZZO_NON_VALIDO;
		if (regalo.getNome().equals(ciboPreferito)) {
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("spada", 3));
			return MESSAGGIO_REGALO;
		}
		return this.agisci(partita);
	}
}
