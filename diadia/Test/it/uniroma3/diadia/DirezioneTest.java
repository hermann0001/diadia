package it.uniroma3.diadia;

import static org.junit.Assert.*;

import org.junit.Test;
import static it.uniroma3.diadia.Direzione.*;

public class DirezioneTest {

	@Test
	public void testOrdinal() {
		assertEquals(0, NORD.ordinal());
		assertEquals(1, SUD.ordinal());
		assertEquals(2, EST.ordinal());
		assertEquals(3, OVEST.ordinal());
	}

	@Test
	public void testGetDeclaringClass() {
		assertSame(Direzione.class, NORD.getDeclaringClass());
	}
	
	@Test
	public void testTuttiSingleton() {
		assertSame(NORD, Direzione.valueOf("NORD"));
		final Direzione singleton = Direzione.valueOf("NORD");
		assertSame(singleton, NORD);
	}

}
