
package it.uniroma3.diadia.comando;

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

		final AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		io.mostraMessaggio("Quale attrezzo vuoi regalare a " + personaggio.getNome());
		List<Attrezzo> listAttrezziBorsa = partita.getGiocatore().getBorsa().getAttrezzi();
		stampaListaAttrezzi(listAttrezziBorsa, io);
		boolean regalato = false;

		String attrezzoScelto = getAttrezzo(io);
		for (Attrezzo a : listAttrezziBorsa) {
			if (attrezzoScelto.equals(a.getNome())) {
				regalato = true;
				msg = personaggio.riceviRegalo(a, partita);
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

	/**
	 * prende attrezzo da tastiera
	 * 
	 * @param io
	 * @return
	 */
	private String getAttrezzo(IO io) {
		return io.leggiRiga();
	}

	/**
	 * overload prende il primo attrezzo della borsa
	 * 
	 * @param partita
	 * @return
	 */
	private String getAttrezzo(Partita partita) {
		return partita.getGiocatore().getBorsa().getAttrezzi().get(0).getNome();

	}

}
