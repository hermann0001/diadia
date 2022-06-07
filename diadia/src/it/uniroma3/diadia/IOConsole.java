package it.uniroma3.diadia;

import java.util.Scanner;

/**
 * Implementazione dell'interfaccia IO. IOConsole è una classe che contiene
 * tutti i metodi per l'interazione con l'utente.
 * 
 * @author Hermann Tamilia
 * @version hw2
 */

public class IOConsole implements IO {
	private Scanner scannerDiLinee;

	public IOConsole(Scanner scannerDiLinee) {
		this.scannerDiLinee = scannerDiLinee;
	}

	public IOConsole() {
		this.scannerDiLinee = new Scanner(System.in);
	}

	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}

	@Override
	public String leggiRiga() {
		String riga = scannerDiLinee.nextLine();
		return riga;
	}

}