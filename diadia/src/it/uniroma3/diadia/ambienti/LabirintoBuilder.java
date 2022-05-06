package it.uniroma3.diadia.ambienti;

import java.util.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder{
	
	public static final String DEFAULT_NOME_NON_VALIDO = "Default";
	private Labirinto labirintoCreato;
	private Map<String, Stanza> stanzeDelLabirinto;
	private Stanza ultimaStanzaInserita;

	public LabirintoBuilder() {
		this.labirintoCreato = new Labirinto();
		this.stanzeDelLabirinto = new HashMap<>();
	}

	public LabirintoBuilder addStanzaIniziale(String stanzaIniziale) {
		if(stanzaIniziale.equals(""))
			stanzaIniziale = DEFAULT_NOME_NON_VALIDO;
		if(!this.stanzeDelLabirinto.containsKey(stanzaIniziale))
			this.addStanza(stanzaIniziale);
		
		Stanza s = this.stanzeDelLabirinto.get(stanzaIniziale);
		this.labirintoCreato.setStanzaIniziale(s);
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String stanzaVincente) {
		if(stanzaVincente.equals(""))
			stanzaVincente = DEFAULT_NOME_NON_VALIDO;
		if(!this.stanzeDelLabirinto.containsKey(stanzaVincente))
			this.addStanza(stanzaVincente);
		
		Stanza s = this.stanzeDelLabirinto.get(stanzaVincente);
		this.labirintoCreato.setStanzaVincente(s);
		return this;
	}
	
	public LabirintoBuilder addStanza(String stanza) {
		/*se volessi creare una stanza con un nome non valido es: "", imposta automaticamente il 
		 * suo nome a "Default" così da non creare possibili errori futuri*/
		if(stanza.equals(""))								
			stanza = DEFAULT_NOME_NON_VALIDO;
		Stanza nuova = new Stanza(stanza);
		this.stanzeDelLabirinto.put(stanza, nuova);
		this.ultimaStanzaInserita = nuova;
		return this;
	}
	
	public LabirintoBuilder addAdiacenze(String stanzaEsistente, String direzione, String stanzaDaAggiungere) {
		if(stanzaEsistente.equals(""))								
			stanzaEsistente = DEFAULT_NOME_NON_VALIDO;
		if(!this.stanzeDelLabirinto.containsKey(stanzaEsistente))
			this.addStanza(stanzaEsistente);
		if(stanzaDaAggiungere.equals(""))								
			stanzaDaAggiungere = DEFAULT_NOME_NON_VALIDO;
		if(!this.stanzeDelLabirinto.containsKey(stanzaDaAggiungere))
			this.addStanza(stanzaDaAggiungere);
		
		Stanza s1 = this.stanzeDelLabirinto.get(stanzaEsistente);
		Stanza s2 = this.stanzeDelLabirinto.get(stanzaDaAggiungere);
		
		s1.impostaStanzaAdiacente(direzione, s2);
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
		if(nomeAttrezzo.equals(""))
			nomeAttrezzo = DEFAULT_NOME_NON_VALIDO;
		Attrezzo attrezzoDaAggiungere = new Attrezzo(nomeAttrezzo, peso);
		this.ultimaStanzaInserita.addAttrezzo(attrezzoDaAggiungere);
		return this;
	}

	public Labirinto getLabirinto() {
		return this.labirintoCreato;
	}
	

	public LabirintoBuilder addStanzaMagica(String stanza) {
		if(stanza.equals(""))								
			stanza = DEFAULT_NOME_NON_VALIDO;
		StanzaMagica nuova = new StanzaMagica(stanza);
		this.stanzeDelLabirinto.put(stanza, nuova);
		this.ultimaStanzaInserita = nuova;
		return this;
	}
	
	//overload
	public LabirintoBuilder addStanzaMagica(String stanza, int sogliaMagica) {
		if(stanza.equals(""))								
			stanza = DEFAULT_NOME_NON_VALIDO;
		Stanza nuova = new StanzaMagica(stanza, sogliaMagica);
		this.stanzeDelLabirinto.put(stanza, nuova);
		this.ultimaStanzaInserita = nuova;
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia(String stanza, String nomeAttrezzoLuce) {
		if(stanza.equals(""))								
			stanza = DEFAULT_NOME_NON_VALIDO;
		Stanza nuova = new StanzaBuia(stanza, nomeAttrezzoLuce);
		this.stanzeDelLabirinto.put(stanza, nuova);
		this.ultimaStanzaInserita = nuova;
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String stanza, String direzioneBloccata, String nomeAttrezzoSbloccante) {
		if(stanza.equals(""))								
			stanza = DEFAULT_NOME_NON_VALIDO;
		Stanza nuova = new StanzaBloccata(stanza, direzioneBloccata, nomeAttrezzoSbloccante);
		this.stanzeDelLabirinto.put(stanza, nuova);
		this.ultimaStanzaInserita = nuova;
		return this;
	}
	
	//metodi che servono principalmente per i test
	
	public Stanza getStanza(String nomeStanza) {
		return this.stanzeDelLabirinto.get(nomeStanza);
	}
	
	public Stanza getUltimaStanza() {
		return this.ultimaStanzaInserita;
	}
}
