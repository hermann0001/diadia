package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando{
	
	private String messaggio;
	private static final String MESSAGGIO_CHI = "Chi dovrei salutare?...";


	protected ComandoSaluta(String nome) {
		super("saluta");
	}

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio != null) {
			this.messaggio = personaggio.saluta();
			partita.getIoconsole().mostraMessaggio(this.messaggio);
		} else
			partita.getIoconsole().mostraMessaggio(MESSAGGIO_CHI);
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}
}
