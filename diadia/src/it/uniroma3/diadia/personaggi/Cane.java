package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{
	
	public static final String MESSAGGIO_REGALO = "Che buono... wof";
	public static final String ATTREZZO_NON_VALIDO = "attrezzo non valido";
	public static final String MESSAGGIO = "Sei stato morso! I tuoi cfu sono: ";
	public static final String CIBO_PREFERITO = "osso";
	private Attrezzo ciboPreferito;

	public Cane(String nome, String presentaz) {
		super(nome, presentaz);
	}
	
	public Attrezzo getAttrezzo() {
		return ciboPreferito;
	}

	public void setAttrezzo(Attrezzo attrezzo) {
		this.ciboPreferito = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		int cfu = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(cfu - 2);
		
		return MESSAGGIO + partita.getGiocatore().getCfu();
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo == null) return ATTREZZO_NON_VALIDO; 
		if(attrezzo.getNome().equals(CIBO_PREFERITO)) {
			partita.getStanzaCorrente().addAttrezzo(new Attrezzo("spada",3));
			return MESSAGGIO_REGALO;
		}
		
		return this.agisci(partita);
	}

}
