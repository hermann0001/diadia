package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando{
	
	private static final String MESSAGGIO_CON_CHI = "Con chi dovrei interagire?...";
	private String messaggio;

	protected ComandoInteragisci(String nome) {
		super(nome);
	}

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio != null) {
			this.messaggio = personaggio.agisci(partita);
			partita.getIoconsole().mostraMessaggio(this.messaggio);
		} 	else
			partita.getIoconsole().mostraMessaggio(MESSAGGIO_CON_CHI);	
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}
}
