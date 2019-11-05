package algoChest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Excepciones.JugadorNoPuedeAgregarMasEntidades;
import Jugadores.Jugador;
import Tablero.Tablero;
import Unidades.*;



class JugadorTest {

	@Test
	void test001JugadorNoPuedeTomarMasEntidadesDeLasQuePuntos() {
		
		Jugador jugador1 = new Jugador("Jugador1");
		
		try {
			for (int i = 0; i < 7; i++) {
				jugador1.agregarCatapulta();
			}
		} catch (JugadorNoPuedeAgregarMasEntidades e) {
			System.out.println("No tiene puntos para agregar esa unidad");
		}
		
		assertEquals(jugador1.cantidadDeUnidades(), 4);
		
		
	}

	@Test
	void test002JugadorQueSeQuedaSinEntidadesEsElPerdedor() {
	
	}

}
