package algoChest.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Tablero.Posicion;

class PosicionTest {

	@Test
	void test00CalculaDistancia() {
		Posicion posicion1 = new Posicion(1,1);
		Posicion posicion2 = new Posicion(4,3);
		
		assertEquals(posicion1.distancia(posicion2), 5);
	}
	
	
}
