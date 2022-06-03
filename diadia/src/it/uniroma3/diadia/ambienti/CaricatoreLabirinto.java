package it.uniroma3.diadia.ambienti;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.eccezioni.FormatoFileNonValidoException;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze: ";

	/* prefisso di una singola riga contenente il nome della stanza magica */
	private static final String STANZA_MAGICA_MARKER = "Magiche: ";

	/* prefisso di una singola riga contenente il nome della stanza buia */
	private static final String STANZA_BUIA_MARKER = "Buie: ";

	/* prefisso di una singola riga contenente il nome della stanza bloccata */
	private static final String STANZA_BLOCCATA_MARKER = "Bloccate: ";

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio: ";

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente: ";

	/*
	 * prefisso della riga contenente le specifiche del mago da collocare nel
	 * formato <nomePersonaggio> <presentazionePersonaggio> <nomeAttrezzo>
	 * <pesoAttrezzo> <nomeStanza>
	 */
	private static final String MAGHI_MARKER = "Maghi: ";

	/*
	 * prefisso della riga contenente le specifiche della strega da collocare nel
	 * formato <nomePersonaggio> <presentazionePersonaggio> <nomeStanza>
	 */
	private static final String STREGHE_MARKER = "Streghe: ";
	
	/*
	 * prefisso della riga contenente le specifiche del cane da collocare nel
	 * formato <nomePersonaggio> <presentazionePersonaggio> <ciboPreferito> <nomeStanza>
	 */
	private static final String CANI_MARKER = "Cani: ";

	/*
	 * prefisso della riga contenente le specifiche degli attrezzi da collocare nel
	 * formato <nomeAttrezzo> <peso> <nomeStanza>
	 */
	private static final String ATTREZZI_MARKER = "Attrezzi: ";

	/*
	 * prefisso della riga contenente le specifiche dei collegamenti tra stanza nel
	 * formato <nomeStanzaDa> <direzione> <nomeStanzaA>
	 */
	private static final String USCITE_MARKER = "Uscite: ";

	/*
	 * Esempio di un possibile file di specifica di un labirinto (vedi
	 * POO-26-eccezioni-file.pdf)
	 * 
	 * Stanze: biblioteca, N10, N11 
	 * Inizio: N10 
	 * Vincente: N11 
	 * Attrezzi: martello 10 biblioteca, pinza 2 N10
	 * Uscite: biblioteca nord N10, biblioteca sud N11
	 * Magiche: atrio 3
	 * Buie: N12 lampada
	 * Bloccate: N18 sud chiave
	 * Maghi: Pancione Ciao-sono-il-mago-pancione bastone 5 N11
	 * Streghe: Peppina Ciao-sono-la-strega-peppina N12
	 * Cani: Fido Wof-bark-bork carne N18
	 */
	private LineNumberReader reader;

	private LabirintoBuilder builder;
	// private Map<String, Stanza> nome2stanza;
//	private Stanza stanzaIniziale;
//	private Stanza stanzaVincente;

	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this(new FileReader(nomeFile));
	}

	public CaricatoreLabirinto(Reader reader) throws FileNotFoundException {
		// this.nome2stanza = new HashMap<String,Stanza>();
		this.reader = new LineNumberReader(reader);
		this.builder = new LabirintoBuilder();
	}

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiECollocaMaghi();
			this.leggiECollocaStreghe();
			this.leggiECollocaCani();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga;
			if ((riga = this.reader.readLine()) == null)
				return "";

			check(riga.startsWith(marker), "era attesa una riga che cominciasse per " + marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		if (nomiStanze == null)
			return;
		for (String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			this.builder.addStanza(nomeStanza);
		}
	}

	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException {
		String specificheStanzeMagiche = this.leggiRigaCheCominciaPer(STANZA_MAGICA_MARKER);
		if (specificheStanzeMagiche == null)
			return;
		for (String specificaStanzaMagica : separaStringheAlleVirgole(specificheStanzeMagiche)) {
			String nomeStanza = null;
			String soglia = null;
			try (Scanner scannerLinea = new Scanner(specificaStanzaMagica)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di una Stanza."));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("La sua soglia magica " + soglia + "."));
				soglia = scannerLinea.next();
			}
			this.builder.addStanzaMagica(nomeStanza, Integer.parseInt(soglia));
		}
	}

	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException {
		String specificheStanzeBuie = this.leggiRigaCheCominciaPer(STANZA_BUIA_MARKER);
		if (specificheStanzeBuie == null)
			return;
		for (String specificaStanzaBuia : separaStringheAlleVirgole(specificheStanzeBuie)) {
			String nomeStanzaBuia = null;
			String nomeAttrezzoLuce = null;
			try (Scanner scannerLinea = new Scanner(specificaStanzaBuia)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di una Stanza."));
				nomeStanzaBuia = scannerLinea.next();
				check(scannerLinea.hasNext(),
						msgTerminazionePrecoce("Il suo attrezzo illuminante " + nomeAttrezzoLuce + "."));
				nomeAttrezzoLuce = scannerLinea.next();
			}
			this.builder.addStanzaBuia(nomeStanzaBuia, nomeAttrezzoLuce);
		}
	}

	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException {
		String specificheStanzeBloccate = this.leggiRigaCheCominciaPer(STANZA_BLOCCATA_MARKER);
		if (specificheStanzeBloccate == null)
			return;
		for (String specificaStanzaBloccata : separaStringheAlleVirgole(specificheStanzeBloccate)) {
			String nomeStanzaBloccata = null;
			String nomeDirezioneBloccata = null;
			String nomeAttrezzoSblocco = null;
			try (Scanner scannerLinea = new Scanner(specificaStanzaBloccata)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di una Stanza."));
				nomeStanzaBloccata = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("La sua direzione bloccata."));
				nomeDirezioneBloccata = scannerLinea.next();
				check(scannerLinea.hasNext(),
						msgTerminazionePrecoce("Il suo attrezzo sbloccante " + nomeAttrezzoSblocco + "."));
				nomeAttrezzoSblocco = scannerLinea.next();
			}
			this.builder.addStanzaBloccata(nomeStanzaBloccata, nomeDirezioneBloccata, nomeAttrezzoSblocco);
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		Scanner scannerDiParole = scanner.useDelimiter(", ");
		while (scannerDiParole.hasNext()) {
			result.add(scannerDiParole.next());
		}
		return result;
	}
	
	private List<String> separaStringheAiTrattini(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		Scanner scannerDiParole = scanner.useDelimiter("-");
		while (scannerDiParole.hasNext()) {
			result.add(scannerDiParole.next());
		}
		return result;
	}

	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale + " non definita");
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.builder.addStanzaIniziale(nomeStanzaIniziale);
		// this.stanzaIniziale = this.builder.getStanza(nomeStanzaVincente);
		this.builder.addStanzaVincente(nomeStanzaVincente);
		// this.stanzaVincente = this.builder.getStanza(nomeStanzaVincente);
	}

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);
		if (specificheAttrezzi == null)
			return;

		for (String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il peso dell'attrezzo " + nomeAttrezzo + "."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce(
						"il nome della stanza in cui collocare l'attrezzo " + nomeAttrezzo + "."));
				nomeStanza = scannerLinea.next();
			}
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza)
			throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			check(isStanzaValida(nomeStanza),
					"Attrezzo " + nomeAttrezzo + " non collocabile: stanza " + nomeStanza + " inesistente");
			this.builder.addAttrezzo(nomeAttrezzo, peso, nomeStanza);
		} catch (NumberFormatException e) {
			check(false, "Peso attrezzo " + pesoAttrezzo + " non valido");
		}
	}

	private void leggiECollocaMaghi() throws FormatoFileNonValidoException {
		String specificheMaghi = this.leggiRigaCheCominciaPer(MAGHI_MARKER);
		if (specificheMaghi == null)
			return;

		for (String specificoMago : separaStringheAlleVirgole(specificheMaghi)) {
			String nomeMago = null;
			String presentazioneMago = null;
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificoMago)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di un mago."));
				nomeMago = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("La sua presentazione " + nomeMago + "."));
				presentazioneMago = leggiPresentazione(presentazioneMago, scannerLinea);
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("Il nome dell'attrezzo " + nomeAttrezzo + "."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("Il peso dell'attrezzo " + pesoAttrezzo + "."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce(
						"il nome della stanza in cui collocare il personaggio " + nomeMago + "."));
				nomeStanza = scannerLinea.next();
			}
			aggiungiMago(nomeMago, presentazioneMago, nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}

	private String leggiPresentazione(String presentazione, Scanner scannerLinea)
			throws FormatoFileNonValidoException {
		presentazione = "";
		for(String parola : separaStringheAiTrattini(scannerLinea.next())) {
			try(Scanner scannerDiParola = new Scanner(parola)){
				check(scannerDiParola.hasNext(), msgTerminazionePrecoce("Lettura della descrizione"));
				presentazione += (parola + " ");
			}
		}
		return presentazione;
	}

	private void aggiungiMago(String nomePersonaggio, String presentazionePersonaggio, String nomeAttrezzo,
			String pesoAttrezzo, String stanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			check(isStanzaValida(stanza),
					"Mago " + nomePersonaggio + " non collocabile: stanza " + stanza + " inesistente");
			this.builder.addMago(nomePersonaggio, presentazionePersonaggio, nomeAttrezzo, peso, stanza);
		} catch (NumberFormatException e) {
			check(false, "Peso attrezzo " + pesoAttrezzo + " non valido");
		}
	}

	private void leggiECollocaStreghe() throws FormatoFileNonValidoException {
		String specificheStreghe = this.leggiRigaCheCominciaPer(STREGHE_MARKER);
		if (specificheStreghe == null)
			return;

		for (String specificaStrega : separaStringheAlleVirgole(specificheStreghe)) {
			String nomeStrega = null;
			String presentazioneStrega = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificaStrega)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di una strega."));
				nomeStrega = scannerLinea.next();
				check(scannerLinea.hasNext(),
						msgTerminazionePrecoce("La sua presentazione " + presentazioneStrega + "."));
				presentazioneStrega = leggiPresentazione(presentazioneStrega, scannerLinea);
				check(scannerLinea.hasNext(), msgTerminazionePrecoce(
						"il nome della stanza in cui collocare il personaggio " + nomeStrega + "."));
				nomeStanza = scannerLinea.next();
			}
			check(isStanzaValida(nomeStanza),
					"Strega " + nomeStrega + " non collocabile: stanza " + nomeStanza + " inesistente");
			this.builder.addStrega(nomeStrega, presentazioneStrega, nomeStanza);
		}
	}

	private void leggiECollocaCani() throws FormatoFileNonValidoException {
		String specificheCani = this.leggiRigaCheCominciaPer(CANI_MARKER);
		if (specificheCani == null)
			return;

		for (String specificoCane : separaStringheAlleVirgole(specificheCani)) {
			String nomeCane = null;
			String presentazioneCane = null;
			String ciboPreferito = null;
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificoCane)) {
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("il nome di un cane."));
				nomeCane = scannerLinea.next();
				check(scannerLinea.hasNext(),
						msgTerminazionePrecoce("La sua presentazione " + presentazioneCane + "."));
				presentazioneCane = leggiPresentazione(presentazioneCane, scannerLinea);
				check(scannerLinea.hasNext(), msgTerminazionePrecoce("Il suo cibo preferito " + ciboPreferito + "."));
				ciboPreferito = scannerLinea.next();
				check(scannerLinea.hasNext(), msgTerminazionePrecoce(
						"il nome della stanza in cui collocare il personaggio " + nomeCane + "."));
				nomeStanza = scannerLinea.next();
			}
			check(isStanzaValida(nomeStanza),
					"Cane " + nomeCane + " non collocabile: stanza " + nomeStanza + " inesistente");
			this.builder.addCane(nomeCane, presentazioneCane, ciboPreferito, nomeStanza);
		}
	}

	private boolean isStanzaValida(String nomeStanza) {
		// return this.nome2stanza.containsKey(nomeStanza);
		return this.builder.getStanza(nomeStanza) != null;
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		if (specificheUscite == null)
			return;

		for (String specificaUscita : separaStringheAlleVirgole(specificheUscite)) {
			String stanzaPartenza = null;
			String dir = null;
			String stanzaDestinazione = null;
			try (Scanner scannerDiLinea = new Scanner(specificaUscita)) {
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce("le uscite di una stanza."));
				stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),
						msgTerminazionePrecoce("la direzione di una uscita della stanza " + stanzaPartenza));
				dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(), msgTerminazionePrecoce(
						"la destinazione di una uscita della stanza " + stanzaPartenza + " nella direzione " + dir));
				stanzaDestinazione = scannerDiLinea.next();

				impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
			}
		}
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere " + msg;
	}

	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa), "Stanza di partenza sconosciuta " + dir);
		check(isStanzaValida(nomeA), "Stanza di destinazione sconosciuta " + dir);
		this.builder.addAdiacenze(stanzaDa, dir, nomeA);
	}

	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore)
			throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException(
					"Formato file non valido [" + this.reader.getLineNumber() + "] " + messaggioErrore);
	}

	public Labirinto getLabirinto() {
		return this.builder.getLabirinto();
	}
}