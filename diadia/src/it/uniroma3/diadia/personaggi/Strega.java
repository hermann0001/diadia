package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class Strega extends AbstractPersonaggio {

	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		Stanza corrente = partita.getStanzaCorrente();
		if (this.haSalutato()) {
			for(Stanza s : corrente.getAdiacenze()) {
				if(s.getAttrezzi().size() > corrente.getAttrezzi().size())
					corrente = s;
			}
		} else {
			for(Stanza s : corrente.getAdiacenze()) {
				if(s.getAttrezzi().size() < corrente.getAttrezzi().size())
					corrente = s;
			}
		}
		
		partita.setStanzaCorrente(corrente);
		final String messaggio = "Sei stato magicamente spostato alla stanza " + partita.getStanzaCorrente().getNome();
		return messaggio;
	}
}
