package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando{
	
	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";

	public ComandoInteragisci() {
		super("interagisci");
	}

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio != null) {
			super.setMessaggio(personaggio.agisci(partita)); 
			partita.getIoconsole().mostraMessaggio(super.getMessaggio());
		} 	else
			partita.getIoconsole().mostraMessaggio(MESSAGGIO_CON_CHI);	
	}
}
