package it.uniroma3.diadia.personaggi;

import java.util.*;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	public static final String MESSAGGIO_MONOLOCALE = "A quanto pare siamo in un monolocale...";
	public static final String MESSAGGIO_INTERAGISCI = "Sei stato magicamente spostato alla stanza ";
	public static final String MESSAGGIO_REGALO = "Grazie per il regalo... AHAHAHAH";
	public static final String ATTREZZO_NON_VALIDO = "attrezzo non valido";

	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}
	
	/**
	 * siccome la strega è permalosa se l'abbiamo salutata ci trasferisce nella stanza adiacente con più
	 * attrezzi. In caso contrario ci trasferisce nella stanza con meno attrezzi
	 */
	
	@Override
	public String agisci(Partita partita) {
		Stanza corrente = partita.getStanzaCorrente();
		LinkedList<Stanza> adiacenze = new LinkedList<>(corrente.getAdiacenze());
		if(adiacenze.isEmpty())
			return MESSAGGIO_MONOLOCALE;
		
		Collections.sort(adiacenze, new Comparator<Stanza>() {

			@Override
			public int compare(Stanza s1, Stanza s2) {
				return s2.getAttrezzi().size() - s1.getAttrezzi().size();
			}
		});
				
		if(super.haSalutato())
			partita.setStanzaCorrente(adiacenze.getFirst());
		else
			partita.setStanzaCorrente(adiacenze.getLast());
		
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
}
