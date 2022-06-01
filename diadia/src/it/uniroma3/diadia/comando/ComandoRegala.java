
package it.uniroma3.diadia.comando;

import java.util.List;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

	private static final String ATTREZZO_NON_TROVATO = "Attrezzo non esistente in borsa...";
	private static final String ATTREZZO_REGALATO = "Attrezzo regalato a ";

	public ComandoRegala() {
		super("reala");
	}

	@Override
	public void esegui(Partita partita) {
		final IO io = partita.getIoconsole();
		final AbstractPersonaggio personaggio= partita.getStanzaCorrente().getPersonaggio();
		io.mostraMessaggio("Quale attrezzo vuoi regalare a " + personaggio.getNome());
		List<Attrezzo> listAttrezziBorsa = partita.getGiocatore().getBorsa().getAttrezzi();
		stampaListaAttrezzi(listAttrezziBorsa, io);
		boolean regalato = false;
		do {
			String attrezzoScelto = io.leggiRiga();
			for(Attrezzo a : listAttrezziBorsa) {
				if(attrezzoScelto.equals(a.getNome())) {
					regalato = true;
					personaggio.riceviRegalo(a, partita);
				}
			}
			
			io.mostraMessaggio(ATTREZZO_NON_TROVATO);
		}while(regalato);
		io.mostraMessaggio(ATTREZZO_REGALATO+ personaggio.getNome());
	}

	private void stampaListaAttrezzi(List<Attrezzo> listAttrezziBorsa, final IO io) {
		io.mostraMessaggio("Attrezzi nella borsa: ");
		for(Attrezzo a: listAttrezziBorsa)
			io.mostraMessaggio(a.getNome());
	}

}
