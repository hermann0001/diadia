package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando{
	
	private static final String MESSAGGIO_CHI = "Chi dovrei salutare?...";


	public ComandoSaluta() {
		super("saluta");
	}

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		this.io = partita.getIoconsole();
		if(personaggio != null) {
			super.setMessaggio(personaggio.saluta()); 
			this.io.mostraMessaggio(super.getMessaggio());
		} else
			this.io.mostraMessaggio(MESSAGGIO_CHI);
	}
}
