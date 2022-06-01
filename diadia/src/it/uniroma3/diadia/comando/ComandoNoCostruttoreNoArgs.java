package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public class ComandoNoCostruttoreNoArgs extends AbstractComando{
	
	public ComandoNoCostruttoreNoArgs(int a) {
		//questo serve per evitare che venga creato il costruttore noargs
		super("noCostruttoreNoArgs");
	}

	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		
	}
}
