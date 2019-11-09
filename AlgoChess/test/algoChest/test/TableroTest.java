package algoChest.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Tablero.*;
import Unidades.*;
import Jugadores.*;
import Excepciones.*;


class TableroTest {

	@Test
	void test000ColocarUnaEntidadEnTerritorioAliadoDesocupado() {
		
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador unJugador = new Jugador("Alejandro");
		Jugador otroJugador = new Jugador("Mariana");
		jugadores[0] = unJugador;
		jugadores[1] = otroJugador;
		
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria.asignarPropietario(unJugador);
		
		unTablero.generarTerritorios(jugadores);
		
		int pos_x = 2;
		int pos_y = 2;
		unTablero.agregarUnidad(unSoldadoDeInfanteria, pos_x, pos_y);
		
		assertEquals(unTablero.obtenerUnidad(pos_x, pos_y), unSoldadoDeInfanteria);
	}
	
	@Test
	void test001ColocarUnaEntidadEnTerritorioAliadoOcupado() {
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador unJugador = new Jugador("Alejandro");
		Jugador otroJugador = new Jugador("Mariana");
		jugadores[0] = unJugador;
		jugadores[1] = otroJugador;
		
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		SoldadoDeInfanteria otroSoldadoDeInfanteria = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria.asignarPropietario(unJugador);
		otroSoldadoDeInfanteria.asignarPropietario(unJugador);
		
		unTablero.generarTerritorios(jugadores);
		
		int pos_x = 2;
		int pos_y = 2;
		unTablero.agregarUnidad(unSoldadoDeInfanteria, pos_x, pos_y);
		unTablero.agregarUnidad(otroSoldadoDeInfanteria, pos_x, pos_y);
		
		assertEquals(unTablero.obtenerUnidad(pos_x, pos_y), unSoldadoDeInfanteria);
	}	
	
	@Test
	void test002ColocarUnaEntidadEnTerritorioEnemigo() {
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador unJugador = new Jugador("Alejandro");
		Jugador otroJugador = new Jugador("Mariana");
		jugadores[0] = unJugador;
		jugadores[1] = otroJugador;
		
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria.asignarPropietario(otroJugador);
		
		unTablero.generarTerritorios(jugadores);
		
		int pos_x = 2;
		int pos_y = 2;
		unTablero.agregarUnidad(unSoldadoDeInfanteria, pos_x, pos_y);
		
		assertEquals(unTablero.obtenerUnidad(pos_x, pos_y), null);
	}
	
	@Test
	void test003VerificaMovimientoEnTablero() throws Exception {
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador unJugador = new Jugador("Juan1");
		Jugador otroJugador = new Jugador("Juan2");
		jugadores[0] = unJugador;
		jugadores[1] = otroJugador;
		
		SoldadoDeInfanteria unSoldadoDeInfanteria1 = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria1.asignarPropietario(unJugador);
		SoldadoDeInfanteria unSoldadoDeInfanteria2 = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria2.asignarPropietario(unJugador);
		SoldadoDeInfanteria unSoldadoDeInfanteria3 = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria3.asignarPropietario(unJugador);
		Jinete unJinete = new Jinete();
		unJinete.asignarPropietario(unJugador);
		
		unTablero.generarTerritorios(jugadores);
		
		int pos_2 = 2;
		int pos_3 = 3;
		int pos_4 = 4;
			
		unTablero.agregarUnidad(unSoldadoDeInfanteria1, pos_2, pos_2);
		
		unTablero.agregarUnidad(unSoldadoDeInfanteria2, pos_2, pos_3);
		unTablero.agregarUnidad(unSoldadoDeInfanteria3, pos_2, pos_4);
		unTablero.agregarUnidad(unJinete, pos_3, pos_4);
		Celda celdaDestino1 = new Celda();
		celdaDestino1.asignarPosicion(pos_3, pos_2);
		Celda celdaDestino2 = new Celda();
		celdaDestino2.asignarPosicion(pos_3, pos_3);
		Celda celdaDestino3 = new Celda();
		celdaDestino3.asignarPosicion(pos_3, pos_4);
		
		assertEquals(unTablero.obtenerUnidad(pos_2, pos_2), unSoldadoDeInfanteria1);
		assertEquals(unTablero.obtenerUnidad(pos_2, pos_3), unSoldadoDeInfanteria2);
		assertEquals(unTablero.obtenerUnidad(pos_2, pos_4), unSoldadoDeInfanteria3);
		//unSoldadoDeInfanteria1.moverBatallon(unSoldadoDeInfanteria2, unSoldadoDeInfanteria3, celdaDestino1, celdaDestino2, celdaDestino3);
		
		//assertEquals(unTablero.obtenerUnidad(pos_2,pos_2), null);
		//assertEquals(unTablero.obtenerUnidad(pos_2, pos_3), null);
		
		unTablero.moverUnidad(pos_2, pos_2, pos_3, pos_2);
		assertEquals(unTablero.obtenerUnidad(pos_3, pos_2), unSoldadoDeInfanteria1);
		assertEquals(unTablero.obtenerUnidad(pos_2, pos_2), null);
		
		//assertEquals(unTablero.obtenerUnidad(pos_2, pos_4), unSoldadoDeInfanteria3);
		//assertEquals(unTablero.obtenerUnidad(pos_3, pos_2), unSoldadoDeInfanteria1);
		//assertEquals(unTablero.obtenerUnidad(3, 3), unSoldadoDeInfanteria2);
		//assertEquals(unTablero.obtenerUnidad(3, 4), unJinete);
		
	}
	
}
