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
	
}
