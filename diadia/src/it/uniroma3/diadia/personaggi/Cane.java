package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;

public class Cane extends AbstractPersonaggio{
	
	private static final String MESSAGGIO = "Sei stato morso! I tuoi cfu sono: ";

	public Cane(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		int cfu = partita.getGiocatore().getCfu();
		partita.getGiocatore().setCfu(cfu - 2);
		
		return MESSAGGIO + partita.getGiocatore().getCfu();
	}

}
