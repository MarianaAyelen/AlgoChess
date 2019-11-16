package algoChest.test;

import Unidades.*;
import Tablero.*;
import Jugadores.*;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Excepciones.*;

class CeldaTest {

	@Test
	void test000ColocarUnaEntidadEnUnaCelda() {
		
		Celda unaCelda = new Celda();
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		
		unaCelda.agregarUnidad(unSoldadoDeInfanteria);
		
		assertEquals(unaCelda.estaVacia(), false);
		assertEquals(unaCelda.obtenerEntidad() , unSoldadoDeInfanteria);
	}
	
	@Test
	void test0001ColocarUnaEntidadEnUnaCeldaYVerificarQueLaEntidadSepaSuPosicion() {
		
		Celda unaCelda = new Celda();
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		
		unaCelda.agregarUnidad(unSoldadoDeInfanteria);
		
		assertEquals(unSoldadoDeInfanteria.obtenerCelda(), unaCelda);
	}
	
	@Test
	void test001ColocarYSacarUnaEntidadDeUnaCelda() {
		
		Celda unaCelda = new Celda();
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		
		unaCelda.agregarUnidad(unSoldadoDeInfanteria);
		unaCelda.sacarUnidad();
		
		assertEquals(unaCelda.estaVacia(), true);
		assertEquals(unaCelda.obtenerEntidad() , null);
	}
	
	@Test
	void test002AsignarPropietarioAUnaCelda() {
		
		Celda unaCelda = new Celda();
		Jugador unJugador = new Jugador("Alejandro");
		
		unaCelda.asignarPropietario(unJugador);
				
		assertEquals(unaCelda.obtenerPropietario() , unJugador);
	}

	@Test
	void test003ColocarUnaEntidadEnTerritorioAliado() throws Exception {
		
		Celda unaCelda = new Celda();
		Jugador unJugador = new Jugador("Alejandro");
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		
		unSoldadoDeInfanteria.asignarPropietario(unJugador);
		unaCelda.asignarPropietario(unJugador);
		unaCelda.agregarUnidadEnTerritorioAliado(unSoldadoDeInfanteria);
		
		assertEquals(unaCelda.estaVacia(), false);
		assertEquals(unaCelda.obtenerEntidad() , unSoldadoDeInfanteria);
	}
	
	@Test
	void test004ColocarUnaEntidadEnTerritorioEnemigo() throws Exception {
		
		Celda unaCelda = new Celda();
		Jugador unJugador = new Jugador("Alejandro");
		Jugador otroJugador = new Jugador("Lucas");
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		
		unSoldadoDeInfanteria.asignarPropietario(unJugador);
		unaCelda.asignarPropietario(otroJugador);
		
		try {
			unaCelda.agregarUnidadEnTerritorioAliado(unSoldadoDeInfanteria);
		}catch (JugadorNoPuedeColocarEntidadesEnTerritorioEnemigo e) {
			// TODO: handle exception
		}

		assertEquals(unaCelda.estaVacia(), true);
		assertEquals(unaCelda.obtenerEntidad() , null);
	}	
	
	@Test
	void test005ColocarUnaEntidadEnCasilleroLibre() throws Exception {
		
		Celda unaCelda = new Celda();
		Jugador unJugador = new Jugador("Alejandro");
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		
		unSoldadoDeInfanteria.asignarPropietario(unJugador);
		unaCelda.asignarPropietario(unJugador);
		
		unaCelda.agregarUnidadEnTerritorioAliado(unSoldadoDeInfanteria);

		assertEquals(unaCelda.estaVacia(), false);
		assertEquals(unaCelda.obtenerEntidad() , unSoldadoDeInfanteria);
	}	
	
	@Test
	void test006ColocarUnaEntidadEnCasilleroOcupado() throws Exception {
		
		Celda unaCelda = new Celda();
		Jugador unJugador = new Jugador("Alejandro");
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		SoldadoDeInfanteria otroSoldadoDeInfanteria = new SoldadoDeInfanteria();
		
		unSoldadoDeInfanteria.asignarPropietario(unJugador);
		otroSoldadoDeInfanteria.asignarPropietario(unJugador);
		unaCelda.asignarPropietario(unJugador);
		
		unaCelda.agregarUnidadEnTerritorioAliado(unSoldadoDeInfanteria);
		try {
			unaCelda.agregarUnidadEnTerritorioAliado(otroSoldadoDeInfanteria);
		}catch (movimientoImposibleCeldaInhabilitada e) {
			// TODO: handle exception
		}
		
		assertEquals(unaCelda.estaVacia(), false);
		assertEquals(unaCelda.obtenerEntidad() , unSoldadoDeInfanteria);
	}	
	
	@Test
	void test007ObtenerPosicionDeUnaCelda() {
		
		Celda unaCelda = new Celda();

		unaCelda.asignarPosicion(3, 4);
		
		assertEquals(unaCelda.obtenerPosicionX() , 3);
		assertEquals(unaCelda.obtenerPosicionY() , 4);
	}
	
	@Test
	void test008TresCeldasUnidas() {
		
		Celda celda1 = new Celda();
		Celda celda2 = new Celda();
		Celda celda3 = new Celda();

		celda1.asignarPosicion(2, 2);
		celda2.asignarPosicion(2, 3); 
		celda3 .asignarPosicion(2, 4);
		
		assertEquals(celda1.tresCeldasUnidas(celda2, celda3) , true);

	}	
	
	@Test
	void test009TresCeldasNoUnidas() {
		
		Celda celda1 = new Celda();
		Celda celda2 = new Celda();
		Celda celda3 = new Celda();

		celda1.asignarPosicion(2, 2);
		celda2.asignarPosicion(2, 3); 
		celda3.asignarPosicion(2, 5);
		
		assertEquals(celda1.tresCeldasUnidas(celda2, celda3) , false);

	}
	
	@Test
	void test010EsDistanciaCercana() {
		
		Celda celda1 = new Celda();
		Celda celda2 = new Celda();
		Celda celda3 = new Celda();

		celda1.asignarPosicion(2, 2);
		celda2.asignarPosicion(2, 4); 
		celda3.asignarPosicion(2, 5);
		
		assertEquals(celda1.esDistanciaCercana(celda2) , true);
		assertEquals(celda1.esDistanciaCercana(celda3) , false);

	}
	
	@Test
	void test010EsDistanciaMedia() {
		
		Celda celda1 = new Celda();
		Celda celda2 = new Celda();
		Celda celda3 = new Celda();
		Celda celda4 = new Celda();

		celda1.asignarPosicion(2, 2);
		celda2.asignarPosicion(2, 4); 
		celda3.asignarPosicion(2, 5);
		celda4.asignarPosicion(10, 5);
		
		assertEquals(celda1.esDistanciaMedia(celda2) , false);
		assertEquals(celda1.esDistanciaMedia(celda3) , true);
		assertEquals(celda1.esDistanciaMedia(celda4) , false);

	}
}
