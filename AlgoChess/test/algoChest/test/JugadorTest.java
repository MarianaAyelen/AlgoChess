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
		Tablero tablero = new Tablero(20, 20);
		Unidad unaUnidad;
		
		try {
		for (int i = 0; i <5; i++) {
			unaUnidad = new Catapulta();
			unaUnidad.asignarPropietario(jugador1);
			jugador1.agregarCatapulta(tablero, i, i);
		}
		}catch (JugadorNoPuedeAgregarMasEntidades e) {
			System.out.println("No tiene puntos para agregar esa undiad");
		}
		
		assertEquals(jugador1.cantidadDeUnidades(), 4);
		
		
	}

	@Test
	void test002JugadorQueSeQuedaSinEntidadesEsElPerdedor() {
	
	}

}
