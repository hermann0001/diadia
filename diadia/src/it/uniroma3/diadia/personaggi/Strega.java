package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	public static final String MESSAGGIO_INTERAGISCI = "Sei stato magicamente spostato alla stanza ";
	public static final String MESSAGGIO_REGALO = "Grazie per il regalo... AHAHAHAH";
	public static final String ATTREZZO_NON_VALIDO = "attrezzo non valido";
	private Attrezzo attrezzo;

	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
		this.attrezzo = null;
	}

	@Override
	public String agisci(Partita partita) {
		Stanza corrente = partita.getStanzaCorrente();
		if (super.haSalutato()) {
			for (Stanza s : corrente.getAdiacenze()) {
				if (s.getAttrezzi().size() > corrente.getAttrezzi().size())
					corrente = s;
			}
		} else {
			for (Stanza s : corrente.getAdiacenze()) {
				if (s.getAttrezzi().size() < corrente.getAttrezzi().size())
					corrente = s;
			}
		}

		partita.setStanzaCorrente(corrente);
		final String messaggio = MESSAGGIO_INTERAGISCI + partita.getStanzaCorrente().getNome();
		return messaggio;
	}

	@Override
	public String riceviRegalo(Attrezzo regalo, Partita partita) {
		if (regalo == null)
			return ATTREZZO_NON_VALIDO;
		this.setAttrezzo(regalo);
		return MESSAGGIO_REGALO;
	}

	public void setAttrezzo(Attrezzo attrezzo) {
		this.attrezzo = attrezzo;
	}

	public Attrezzo getAttrezzo() {
		return this.attrezzo;
	}
}
