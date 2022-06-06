package it.uniroma3.diadia.comando;

import java.util.Scanner;


public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	private String nomeComando;
	private String parametro;
	
	@SuppressWarnings("deprecation")
	@Override
	public AbstractComando costruisciComando(String istruzione)
											throws IllegalArgumentException, RuntimeException{
		if(istruzione.equals(""))
			throw new IllegalArgumentException();
		try {
			Scanner scannerDiParole = new Scanner(istruzione);
			String nomeComando = null;
			String parametro = null;
			AbstractComando comando = null;
			if (scannerDiParole.hasNext())
				nomeComando = scannerDiParole.next(); // prima parola: nome del comando
			if (scannerDiParole.hasNext())
				parametro = scannerDiParole.next(); // seconda parola: eventuale parametro
			
			StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comando.Comando");
			nomeClasse.append(Character.toUpperCase(nomeComando.charAt(0)));
			nomeClasse.append(nomeComando.substring(1));
			
			try {
				Class<?> classeComando = Class.forName(nomeClasse.toString());
				comando = (AbstractComando) classeComando.newInstance();
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				comando = new ComandoNonValido(e);
			}
				
			comando.setParametro(parametro);
			scannerDiParole.close();
			return comando;
		}catch(Exception unknown) {
			throw new RuntimeException("Quando hai scritto questo codice ti è sfuggita questa eccezione",unknown);
		}
	}

	public String getNome() {
		return this.nomeComando;
	}

	public String getParametro() {
		return this.parametro;
	}
}
