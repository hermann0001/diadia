package it.uniroma3.diadia.attrezzi;

import java.util.Comparator;

public class ComparatorePerPesoPerNome implements Comparator<Attrezzo> {

	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {
		int tmp = a1.getPeso() - a2.getPeso();
		if(tmp == 0)
			return a1.getNome().compareTo(a2.getNome());
		return tmp;
	}
}
