package it.uniroma3.diadia.comando;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FabbricaDiComandiRiflessivaTest {	
	
	private FabbricaDiComandiRiflessiva factory;
	
	@Before
	public void setUp() {
		this.factory = new FabbricaDiComandiRiflessiva();
	}

	@Test
	public void testCostruisciComandoAiuto() {
		assertSame(ComandoAiuto.class, this.factory.costruisciComando("aiuto").getClass());
	}

	@Test
	public void testCostruisciComandoFine() {
		assertSame(ComandoFine.class, this.factory.costruisciComando("fine").getClass());
	}

	@Test
	public void testCostruisciComandoVai() {
		assertSame(ComandoVai.class, this.factory.costruisciComando("vai sud").getClass());

	}

	@Test
	public void testCostruisciComandoGuarda() {
		assertSame(ComandoGuarda.class, this.factory.costruisciComando("guarda").getClass());

	}

	@Test
	public void testCostruisciComandoPosa() {
		assertSame(ComandoPosa.class, this.factory.costruisciComando("posa").getClass());

	}

	@Test
	public void testCostruisciComandoPrendi() {
		assertSame(ComandoPrendi.class, this.factory.costruisciComando("prendi").getClass());
	}
	
	@Test
	public void testCostruisciComandoVuoto() {
		assertSame(ComandoNonValido.class, this.factory.costruisciComando("Inesistente").getClass());
	}
	
	@Test
	public void testCostruisciComando_classNotFound() {
		assertSame(ComandoNonValido.class, this.factory.costruisciComando("Inesistente").getClass());
		assertSame(ClassNotFoundException.class, ((ComandoNonValido) this.factory.costruisciComando("Inesistente")).getException().getClass());
	}
	
	@Test
	public void testCostruisciComando_IllegalInstatiation() {
		assertSame(ComandoNonValido.class, this.factory.costruisciComando("noCostruttoreNoArgs").getClass());
		assertSame(InstantiationException.class, ((ComandoNonValido) this.factory.costruisciComando("noCostruttoreNoArgs")).getException().getClass());
	}
	
	@Test
	public void testCostruisciComando_IllegalAccess() {
		assertSame(ComandoNonValido.class, this.factory.costruisciComando("noAccess").getClass());
		assertSame(IllegalAccessException.class, ((ComandoNonValido) this.factory.costruisciComando("noAccess")).getException().getClass());
	}
	
	@Test(expected = RuntimeException.class)
	public void testCostruisciComando_null() {
		this.factory.costruisciComando(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCostruisciComando_vuoto() {
		this.factory.costruisciComando("");
	}
	

}
