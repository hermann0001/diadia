package it.uniroma3.diadia.ambienti;

import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class LabirintoBuilder {

	public static final String DEFAULT_NOME_NON_VALIDO = "Default";
	private Labirinto labirintoCreato;
	private Map<String, Stanza> stanzeDelLabirinto;
	private Stanza ultimaStanzaInserita;

	public LabirintoBuilder() {
		this.labirintoCreato = new Labirinto();
		this.stanzeDelLabirinto = new HashMap<>();
	}

	public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
		if (stanzaIniziale.equals(""))
			stanzaIniziale = DEFAULT_NOME_NON_VALIDO;
		if (!this.stanzeDelLabirinto.containsKey(stanzaIniziale))
			this.addStanza(stanzaIniziale);

		Stanza s = this.stanzeDelLabirinto.get(stanzaIniziale);
		this.labirintoCreato.setStanzaIniziale(s);
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
		if (stanzaVincente.equals(""))
			stanzaVincente = DEFAULT_NOME_NON_VALIDO;
		if (!this.stanzeDelLabirinto.containsKey(stanzaVincente))
			this.addStanza(stanzaVincente);

		Stanza s = this.stanzeDelLabirinto.get(stanzaVincente);
		this.labirintoCreato.setStanzaVincente(s);
		return this;
	}

	public LabirintoBuilder addStanza(String stanza) {
		/*
		 * se volessi creare una stanza con un nome non valido es: "", imposta
		 * automaticamente il suo nome a "Default" così da non creare possibili errori
		 * futuri
		 */
		if (stanza.equals(""))
			stanza = DEFAULT_NOME_NON_VALIDO;
		Stanza nuova = new Stanza(stanza);
		this.stanzeDelLabirinto.put(stanza, nuova);
		this.ultimaStanzaInserita = nuova;
		return this;
	}

	public LabirintoBuilder addAdiacenze(String stanzaEsistente, String direzione, String stanzaDaAggiungere) {
		if (stanzaEsistente.equals(""))
			stanzaEsistente = DEFAULT_NOME_NON_VALIDO;
		if (!this.stanzeDelLabirinto.containsKey(stanzaEsistente))
			this.addStanza(stanzaEsistente);
		if (stanzaDaAggiungere.equals(""))
			stanzaDaAggiungere = DEFAULT_NOME_NON_VALIDO;
		if (!this.stanzeDelLabirinto.containsKey(stanzaDaAggiungere))
			this.addStanza(stanzaDaAggiungere);

		Stanza s1 = this.stanzeDelLabirinto.get(stanzaEsistente);
		Stanza s2 = this.stanzeDelLabirinto.get(stanzaDaAggiungere);

		s1.impostaStanzaAdiacente(direzione, s2);
		return this;
	}

	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
		if (nomeAttrezzo.equals(""))
			nomeAttrezzo = DEFAULT_NOME_NON_VALIDO;
		Attrezzo attrezzoDaAggiungere = new Attrezzo(nomeAttrezzo, peso);
		this.ultimaStanzaInserita.addAttrezzo(attrezzoDaAggiungere);
		return this;
	}

	/**
	 * versione sovraccarica di addAttrezzo che specifica anche la stanza
	 * 
	 * @version hw4
	 */
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso, String nomeStanza) {
		if (nomeAttrezzo.equals(""))
			nomeAttrezzo = DEFAULT_NOME_NON_VALIDO;
		Attrezzo attrezzoDaAggiungere = new Attrezzo(nomeAttrezzo, peso);
		if (nomeStanza.equals(""))
			nomeStanza = DEFAULT_NOME_NON_VALIDO;
		if (!this.stanzeDelLabirinto.containsKey(nomeStanza))
			this.addStanza(nomeStanza);

		this.getStanza(nomeStanza).addAttrezzo(attrezzoDaAggiungere);
		return this;
	}

	public Labirinto getLabirinto() {
		return this.labirintoCreato;
	}

	public LabirintoBuilder addStanzaMagica(String stanza) {
		if (stanza.equals(""))
			stanza = DEFAULT_NOME_NON_VALIDO;
		
		if (this.stanzeDelLabirinto.containsKey(stanza))
			this.stanzeDelLabirinto.remove(stanza);
		
		StanzaMagica nuova = new StanzaMagica(stanza);
		this.stanzeDelLabirinto.put(stanza, nuova);
		this.ultimaStanzaInserita = nuova;
		return this;
	}

	// overload
	public LabirintoBuilder addStanzaMagica(String stanza, int sogliaMagica) {
		if (stanza.equals(""))
			stanza = DEFAULT_NOME_NON_VALIDO;
		
		Stanza nuova = new StanzaMagica(stanza, sogliaMagica);
		if (this.stanzeDelLabirinto.containsKey(stanza)) {
			if(this.labirintoCreato.getStanzaIniziale().getNome().equals(stanza))
				this.labirintoCreato.setStanzaIniziale(nuova);
			else if(this.labirintoCreato.getStanzaVincente().getNome().equals(stanza))
				this.labirintoCreato.setStanzaVincente(nuova);
			
			this.stanzeDelLabirinto.remove(stanza);
		}
		this.stanzeDelLabirinto.put(stanza, nuova);
		this.ultimaStanzaInserita = nuova;
		return this;
	}

	public LabirintoBuilder addStanzaBuia(String stanza, String nomeAttrezzoLuce) {
		if (stanza.equals(""))
			stanza = DEFAULT_NOME_NON_VALIDO;
	
		Stanza nuova = new StanzaBuia(stanza, nomeAttrezzoLuce);
		if (this.stanzeDelLabirinto.containsKey(stanza)) {
			if(this.labirintoCreato.getStanzaIniziale().getNome().equals(stanza))
				this.labirintoCreato.setStanzaIniziale(nuova);
			else if(this.labirintoCreato.getStanzaVincente().getNome().equals(stanza))
				this.labirintoCreato.setStanzaVincente(nuova);
			
			this.stanzeDelLabirinto.remove(stanza);
		}
		
		this.stanzeDelLabirinto.put(stanza, nuova);
		this.ultimaStanzaInserita = nuova;
		return this;
	}

	public LabirintoBuilder addStanzaBloccata(String stanza, String direzioneBloccata, String nomeAttrezzoSbloccante) {
		if (stanza.equals(""))
			stanza = DEFAULT_NOME_NON_VALIDO;
		
		Stanza nuova = new StanzaBloccata(stanza, direzioneBloccata, nomeAttrezzoSbloccante);
		if (this.stanzeDelLabirinto.containsKey(stanza)) {
			if(this.labirintoCreato.getStanzaIniziale().getNome().equals(stanza))
				this.labirintoCreato.setStanzaIniziale(nuova);
			else if(this.labirintoCreato.getStanzaVincente().getNome().equals(stanza))
				this.labirintoCreato.setStanzaVincente(nuova);
			
			this.stanzeDelLabirinto.remove(stanza);
		}
		
		this.stanzeDelLabirinto.put(stanza, nuova);
		this.ultimaStanzaInserita = nuova;
		return this;
	}

	public LabirintoBuilder addMago(String nomeMago, String presentazione, String nomeAttrezzo, int peso, String stanza) {
		if(stanza.equals(""))								
			stanza = DEFAULT_NOME_NON_VALIDO;
		
		Attrezzo attrezzoMago = new Attrezzo(nomeAttrezzo, peso);
		AbstractPersonaggio mago = new Mago(nomeMago, presentazione, attrezzoMago);

		this.getStanza(stanza).setPersonaggio(mago);
		
		return this;
	}
	
	public LabirintoBuilder addStrega(String nomeStrega, String presentazione, String stanza) {
		if(stanza.equals(""))								
			stanza = DEFAULT_NOME_NON_VALIDO;
		
		AbstractPersonaggio strega = new Strega(nomeStrega, presentazione);
		this.getStanza(stanza).setPersonaggio(strega);
		
		return this;
	}
	
	public LabirintoBuilder addCane(String nomeStrega, String presentazione, String ciboPreferito,  String stanza) {
		if(stanza.equals(""))								
			stanza = DEFAULT_NOME_NON_VALIDO;
		
		AbstractPersonaggio cane = new Cane(nomeStrega, presentazione, ciboPreferito);
		this.getStanza(stanza).setPersonaggio(cane);
		
		return this;
	}

	// metodi che servono principalmente per i test

	public Stanza getStanza(String nomeStanza) {
		return this.stanzeDelLabirinto.get(nomeStanza);
	}

	public Stanza getUltimaStanza() {
		return this.ultimaStanzaInserita;
	}
}
