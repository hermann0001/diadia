package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {
	private Giocatore giocatore;

	@Before
	public void setUp() {
		this.giocatore = new Giocatore("Player Test");
	}

	@Test
	public void testGetCfu() {
		assertEquals(20, this.giocatore.getCfu());
	}

	@Test
	public void testSetCfu() {
		this.giocatore.setCfu(12);
		assertEquals(12, this.giocatore.getCfu());
	}

}
