package algoChest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.Test;

import Excepciones.JugadorNoPuedeAgregarMasEntidades;
import Excepciones.jugadorPierde;
import Excepciones.jugadorSeQuedaSinUnidades;
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
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador unJugador = new Jugador("Alejandro");
		Jugador otroJugador = new Jugador("Mariana");
		jugadores[0] = unJugador;
		jugadores[1] = otroJugador;
		
		unJugador.agregarSoldadoInfanteria();
		otroJugador.agregarSoldadoInfanteria();
		Unidad unaUnidad ;
		Jugador jugadorPerdedor = null; 
		unTablero.generarTerritorios(jugadores);
		
		int jugadorAliadoPosX = 9;
		int jugadorAliadoPosY = 1;
		int jugadorEnemigoPosX = 11;
		int jugadorEnemigoPosY = 1;
		
		unJugador.agregarUnidadAlTablero(unTablero, jugadorAliadoPosX, jugadorAliadoPosY, 0);
		otroJugador.agregarUnidadAlTablero(unTablero, jugadorEnemigoPosX, jugadorEnemigoPosY, 0);
		
		try {
			for (int i = 0; i < 10; i++) {
				unTablero.realizarComportamiento(jugadorAliadoPosX, jugadorAliadoPosY, jugadorEnemigoPosX, jugadorEnemigoPosY);
			}
				
		} catch (jugadorSeQuedaSinUnidades e) {
			unaUnidad = unTablero.obtenerUnidad(jugadorEnemigoPosX, jugadorEnemigoPosY);
			jugadorPerdedor = unaUnidad.obtenerPropietario();
		}
	
		assertEquals(jugadorPerdedor.obtenerPropietario(), "Mariana");
	}
	
		
	@Test
	void test003JugadorPierdeUnaUnidad() {
	
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador unJugador = new Jugador("Alejandro");
		Jugador otroJugador = new Jugador("Mariana");
		jugadores[0] = unJugador;
		jugadores[1] = otroJugador;
		
		unJugador.agregarSoldadoInfanteria();
		otroJugador.agregarSoldadoInfanteria();
	
		unTablero.generarTerritorios(jugadores);
		
		int jugadorAliadoPosX = 9;
		int jugadorAliadoPosY = 1;
		int jugadorEnemigoPosX = 11;
		int jugadorEnemigoPosY = 1;
		
		unJugador.agregarUnidadAlTablero(unTablero, jugadorAliadoPosX, jugadorAliadoPosY, 0);
		otroJugador.agregarUnidadAlTablero(unTablero, jugadorEnemigoPosX, jugadorEnemigoPosY, 0);
		
		try {
			for (int i = 0; i < 10; i++) {
				unTablero.realizarComportamiento(jugadorAliadoPosX, jugadorAliadoPosY, jugadorEnemigoPosX, jugadorEnemigoPosY);
			}
				
		} catch (jugadorSeQuedaSinUnidades e) {
			
		}
		
		assertEquals(otroJugador.cantidadDeUnidades(), 0);
	}
	
	

}