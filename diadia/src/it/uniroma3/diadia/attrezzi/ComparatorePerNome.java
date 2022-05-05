package it.uniroma3.diadia.attrezzi;

import java.util.*;

public class ComparatorePerNome implements Comparator<Attrezzo>{

	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {
		return a1.getNome().compareTo(a2.getNome());
	}

}
