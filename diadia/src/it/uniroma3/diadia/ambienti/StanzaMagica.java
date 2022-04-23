package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * La stanza magica è un particolare sottotipo di stanza. Dopo N volte che 
 * in tale stanza viene posato (aggiunto) un qualsiasi attrezzo da parte del giocatore, 
 * la stanza inizierà a comportarsi «magicamente».
 * 
 * @author Hermann Tamilia
 * @see Stanza
 * @see StanzaMagica#modificaAttrezzo(Attrezzo)
 * @version hw2
 *
 */
public class StanzaMagica extends Stanza {

	final static private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati > this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);
		return super.addAttrezzo(attrezzo);
	}
	
	/**
	 * Il comportamento magico della stanza è questo. Ogni volta che posiamo un attrezzo
	 * la stanza inverte il suo nome e ne raddoppia il peso
	 * 
	 * @param attrezzo attrezzo da modificare
	 * @return attrezzo modificato
	 */
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(), pesoX2);
		return attrezzo;
	}

}
