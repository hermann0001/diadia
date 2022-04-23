package it.uniroma3.diadia.fixture;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOSimulator;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class Fixture {

	private DiaDia session;

	public IOSimulator creaSimulazionePartitaEGioca() {
		IOSimulator io = new IOSimulator();
		this.session = new DiaDia(io);
		return io;
	}

	public Attrezzo creaAttrezzoEAggiungiStanza(Stanza corrente, String nomeAttrezzo, int peso) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		corrente.addAttrezzo(attrezzo);
		return attrezzo;
	}

	public Attrezzo creaAttrezzoEAggiungiBorsa(Borsa borsa, String nomeAttrezzo, int peso) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		borsa.addAttrezzo(attrezzo);
		return attrezzo;
	}

	public DiaDia getSession() {
		return this.session;
	}
}
