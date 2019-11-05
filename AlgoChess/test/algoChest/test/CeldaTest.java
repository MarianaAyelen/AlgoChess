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
		
		unaCelda.agregarUnidadEnTerritorioAliado(unSoldadoDeInfanteria);

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
		unaCelda.agregarUnidadEnTerritorioAliado(otroSoldadoDeInfanteria);

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
}
