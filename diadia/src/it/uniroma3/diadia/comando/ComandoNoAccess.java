package it.uniroma3.diadia.comando;

import it.uniroma3.diadia.Partita;

public class ComandoNoAccess extends AbstractComando{
	
	private ComandoNoAccess() {
		//questo per scatenare un illegal access exception
		super("noAccess");
	}

	@Override
	public void esegui(Partita partita) {
		// TODO Auto-generated method stub
		
	}
}
