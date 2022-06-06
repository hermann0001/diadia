
package it.uniroma3.diadia.comando;

import java.util.Iterator;
import java.util.List;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

	public static final String ATTREZZO_NON_TROVATO = "Attrezzo non esistente in borsa...";

	public ComandoRegala() {
		super("reala");
	}

	/**
	 * regala un attrezzo da tastiera
	 */
	@Override
	public void esegui(Partita partita) {
		final IO io = partita.getIoconsole();
		String msg = null;
		String attrezzoScelto = super.getParametro();

		final AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		List<Attrezzo> listAttrezziBorsa = partita.getGiocatore().getBorsa().getAttrezzi();

		if(attrezzoScelto == null || attrezzoScelto.equals("")) {
			io.mostraMessaggio("Quale attrezzo vuoi regalare a " + personaggio.getNome());
			stampaListaAttrezzi(listAttrezziBorsa, io);
			return;
		}
		
		for (Iterator<Attrezzo> iterator = listAttrezziBorsa.iterator(); iterator.hasNext();) {
			Attrezzo a = iterator.next();
			if (attrezzoScelto.equals(a.getNome())) {
				msg = personaggio.riceviRegalo(a, partita);
				iterator.remove();
			} else
				msg = ATTREZZO_NON_TROVATO;
		}

		io.mostraMessaggio(msg);
	}

	private void stampaListaAttrezzi(List<Attrezzo> listAttrezziBorsa, final IO io) {
		int i = 1;
		io.mostraMessaggio("Attrezzi nella borsa: ");
		for (Attrezzo a : listAttrezziBorsa)
			io.mostraMessaggio(i++ + ")" + " " + a.getNome());
	}
}
