package algoChest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Excepciones.JugadorNoPuedeAgregarMasEntidades;
import Jugadores.Jugador;
import Unidades.*;


class JugadorTest {

	@Test
	void test001JugadorNoPuedeTomarMasEntidadesDeLasQuePuntos() {
		
		Jugador jugador1 = new Jugador("Jugador1");
		
		jugador1.agregarCatapulta();
		jugador1.agregarCatapulta();

		try {
			jugador1.agregarCatapulta();
		}catch (JugadorNoPuedeAgregarMasEntidades e) {
			assertTrue(false);
		}
		
		
	}

	@Test
	void test002JugadorQueSeQuedaSinEntidadesEsElPerdedor() {
	
	}

}
