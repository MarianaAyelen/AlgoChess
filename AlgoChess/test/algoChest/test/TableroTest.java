package algoChest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

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
	void test003VerificaMovimientoDeBatallonSinObstaculo() throws Exception {
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador unJugador = new Jugador("Juan1");
		Jugador otroJugador = new Jugador("Juan2");
		jugadores[0] = unJugador;
		jugadores[1] = otroJugador;

		unJugador.agregarTablero(unTablero);
		otroJugador.agregarTablero(unTablero);
		
		unJugador.agregarSoldadoInfanteria();
		unJugador.agregarSoldadoInfanteria();
		unJugador.agregarSoldadoInfanteria();
	
		unTablero.generarTerritorios(jugadores);
		int pos_2 = 2;
		int pos_3 = 3;
		int pos_4 = 4;
				
		unJugador.agregarUnidadAlTablero(unTablero, pos_2, pos_2, 0);
		unJugador.agregarUnidadAlTablero(unTablero, pos_2, pos_3, 1);
		unJugador.agregarUnidadAlTablero(unTablero, pos_2, pos_4, 2);
			
		unTablero.moverBatallon(pos_2, pos_2, pos_3, pos_2);
		
		assertEquals(unTablero.obtenerUnidad(pos_3, pos_2), unJugador.obtenerUnidadEnPosicion(0));
		assertEquals(unTablero.obtenerUnidad(pos_3, pos_3), unJugador.obtenerUnidadEnPosicion(1));
		assertEquals(unTablero.obtenerUnidad(pos_3, pos_4), unJugador.obtenerUnidadEnPosicion(2));

		assertEquals(unTablero.obtenerUnidad(pos_2, pos_2), null);
		assertEquals(unTablero.obtenerUnidad(pos_2, pos_3), null);
		assertEquals(unTablero.obtenerUnidad(pos_2, pos_4), null);
		
	}

	
	@Test
	void test004VerificaMovimientoDeBatallonConObstaculo() throws Exception {
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador unJugador = new Jugador("Juan1");
		Jugador otroJugador = new Jugador("Juan2");
		jugadores[0] = unJugador;
		jugadores[1] = otroJugador;

		unJugador.agregarTablero(unTablero);
		otroJugador.agregarTablero(unTablero);
		
		unJugador.agregarSoldadoInfanteria();
		unJugador.agregarSoldadoInfanteria();
		unJugador.agregarSoldadoInfanteria();
		unJugador.agregarCurandero();
	
		unTablero.generarTerritorios(jugadores);
		int pos_2 = 2;
		int pos_3 = 3;
		int pos_4 = 4;
				
		unJugador.agregarUnidadAlTablero(unTablero, pos_2, pos_2, 0);// soldado en posicion (2,2)
		unJugador.agregarUnidadAlTablero(unTablero, pos_2, pos_3, 1);// soldado en posicion (2,3)
		unJugador.agregarUnidadAlTablero(unTablero, pos_2, pos_4, 2);// soldado en posicion (2,4)
		unJugador.agregarUnidadAlTablero(unTablero, pos_3, pos_4, 3);// curandero en posicion (3,4)
			
		unTablero.moverBatallon(pos_2, pos_2, pos_3, pos_2);
		
		assertEquals(unTablero.obtenerUnidad(pos_3, pos_2), unJugador.obtenerUnidadEnPosicion(0));// soldado en posicion (3,2)
		assertEquals(unTablero.obtenerUnidad(pos_3, pos_3), unJugador.obtenerUnidadEnPosicion(1));// soldado en posicion (3,3)
		assertEquals(unTablero.obtenerUnidad(pos_2, pos_4), unJugador.obtenerUnidadEnPosicion(2));// soldado que no se puede mover
		assertEquals(unTablero.obtenerUnidad(pos_3, pos_4), unJugador.obtenerUnidadEnPosicion(3));// curandero que es el obstaculo
			
	}
	
	@Test
	void test005VerificaMovimientoDeBatallon_4SoldadosContiguosSoloSeMueven3() throws Exception {
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador unJugador = new Jugador("Juan1");
		Jugador otroJugador = new Jugador("Juan2");
		jugadores[0] = unJugador;
		jugadores[1] = otroJugador;

		unJugador.agregarTablero(unTablero);
		otroJugador.agregarTablero(unTablero);
		
		unJugador.agregarSoldadoInfanteria();
		unJugador.agregarSoldadoInfanteria();
		unJugador.agregarSoldadoInfanteria();
		unJugador.agregarSoldadoInfanteria();
	
		unTablero.generarTerritorios(jugadores);
		int pos_2 = 2;
		int pos_3 = 3;
		int pos_4 = 4;
		int pos_5 = 5;
				
		unJugador.agregarUnidadAlTablero(unTablero, pos_2, pos_2, 0);// soldado en posicion (2,2)
		unJugador.agregarUnidadAlTablero(unTablero, pos_2, pos_3, 1);// soldado en posicion (2,3)
		unJugador.agregarUnidadAlTablero(unTablero, pos_2, pos_4, 2);// soldado en posicion (2,4)
		unJugador.agregarUnidadAlTablero(unTablero, pos_2, pos_5, 3);// soldado en posicion (2,5)
			
		unTablero.moverBatallon(pos_2, pos_3, pos_3, pos_3);
		
		assertEquals(unTablero.obtenerUnidad(pos_3, pos_2), unJugador.obtenerUnidadEnPosicion(0));// soldado que se mueve con el batallon
		assertEquals(unTablero.obtenerUnidad(pos_3, pos_3), unJugador.obtenerUnidadEnPosicion(1));// soldado que se mueve con el batallon
		assertEquals(unTablero.obtenerUnidad(pos_3, pos_4), unJugador.obtenerUnidadEnPosicion(2));// soldado que se mueve con el batallon
		assertEquals(unTablero.obtenerUnidad(pos_2, pos_5), unJugador.obtenerUnidadEnPosicion(3));// soldado que no se mueve
			
	}
	
	
	@Test
	void test006JineteAliadoAtacaAPiezaEnDistanciaMediaYVerificaQueSeRestaVida() {
		
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador jugadorAliado = new Jugador("pepe");
		Jugador jugadorEnemigo = new Jugador("maria");
		jugadores[0] = jugadorAliado;
		jugadores[1] = jugadorEnemigo;
		jugadorAliado.agregarTablero(unTablero);
		jugadorEnemigo.agregarTablero(unTablero);
		
		// jugador aliado
		Jinete unJinete = new Jinete();
		unJinete.asignarPropietario(jugadorAliado);
		
		// jugador enemigo
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria.asignarPropietario(jugadorEnemigo);
		
		//Posicionamiento
		unTablero.generarTerritorios(jugadores);
		int pos_1 = 5;
		int pos_2 = 9;
		int pos_3 = 13;
			
		unTablero.agregarUnidad(unJinete, pos_2, pos_1);
		unTablero.agregarUnidad(unSoldadoDeInfanteria, pos_3, pos_1);
		unTablero.realizarComportamiento(pos_2, pos_1, pos_3, pos_1);
		
		assertEquals(unSoldadoDeInfanteria.vidaDeLaUnidad(), 85);
	
	}
	
	@Test
	void test007JineteAliadoAtacaAPiezaEnDistanciaCortaSinAliadosCernanosYVerificaQueSeRestaVida() {
		
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador jugadorAliado = new Jugador("pepe");
		Jugador jugadorEnemigo = new Jugador("maria");
		jugadores[0] = jugadorAliado;
		jugadores[1] = jugadorEnemigo;
		jugadorAliado.agregarTablero(unTablero);
		jugadorEnemigo.agregarTablero(unTablero);
		
		unTablero.generarTerritorios(jugadores);
		// jugador aliado
		Jinete unJinete = new Jinete();
		unJinete.asignarPropietario(jugadorAliado);
		
		// jugador enemigo
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria.asignarPropietario(jugadorEnemigo);
		
		//Posicionamiento
		
		int pos_1 = 5;
		int pos_2 = 9;
		int pos_3 = 11;
			
		unTablero.agregarUnidad(unJinete, pos_2, pos_1);
		unTablero.agregarUnidad(unSoldadoDeInfanteria, pos_3, pos_1); 
		unTablero.realizarComportamiento(pos_2, pos_1, pos_3, pos_1);
		
		assertEquals(unSoldadoDeInfanteria.vidaDeLaUnidad(), 95);
	
	}
	
	@Test
	void test008JineteAliadoAtacaAPiezaEnDistanciaMediaSinAliadosCernanosYVerificaQueSeRestaVida() {
		
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador jugadorAliado = new Jugador("pepe");
		Jugador jugadorEnemigo = new Jugador("maria");
		jugadores[0] = jugadorAliado;
		jugadores[1] = jugadorEnemigo;
		jugadorAliado.agregarTablero(unTablero);
		jugadorEnemigo.agregarTablero(unTablero);
		
		unTablero.generarTerritorios(jugadores);
		// jugador aliado
		Jinete unJinete = new Jinete();
		unJinete.asignarPropietario(jugadorAliado);
		
		// jugador enemigo
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria.asignarPropietario(jugadorEnemigo);
		
		//Posicionamiento
		
		int pos_1 = 5;
		int pos_2 = 9;
		int pos_3 = 14;
			
		unTablero.agregarUnidad(unJinete, pos_2, pos_1);
		unTablero.agregarUnidad(unSoldadoDeInfanteria, pos_3, pos_1); 
		unTablero.realizarComportamiento(pos_2, pos_1, pos_3, pos_1);
		
		assertEquals(unSoldadoDeInfanteria.vidaDeLaUnidad(), 85);
	
	}
	
	@Test
	void test009JineteAliadoNoPuedeAtacarAPiezaEnDistanciaLejana() {
		
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador jugadorAliado = new Jugador("mariana");
		Jugador jugadorEnemigo = new Jugador("alejandro");
		jugadores[0] = jugadorAliado;
		jugadores[1] = jugadorEnemigo;
		jugadorAliado.agregarTablero(unTablero);
		jugadorEnemigo.agregarTablero(unTablero);
		
		unTablero.generarTerritorios(jugadores);
		// jugador aliado
		Jinete unJinete = new Jinete();
		unJinete.asignarPropietario(jugadorAliado);
		
		// jugador enemigo
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria.asignarPropietario(jugadorEnemigo);
		
		//Posicionamiento
		
		int pos_1 = 5;
		int pos_2 = 9;
		int pos_3 = 18;
			
		unTablero.agregarUnidad(unJinete, pos_2, pos_1);
		unTablero.agregarUnidad(unSoldadoDeInfanteria, pos_3, pos_1); 
		unTablero.realizarComportamiento(pos_2, pos_1, pos_3, pos_1);
		
		assertEquals(unSoldadoDeInfanteria.vidaDeLaUnidad(), 100);
	
	}
	
	@Test
	void test010VerificarCeldasDistanciaCercana() {
		
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		unTablero.generarTerritorios(jugadores);
		
		Celda celda1 = new Celda(5,9,true);

		assertEquals(unTablero.devolverCeldasDistanciaCercana(celda1).size(), 24);
	}
	
	@Test
	void test011JugadorPierdaUnidadYSeVaciaCelda() {
		
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
		otroJugador.agregarCatapulta();
		otroJugador.agregarUnidadAlTablero(unTablero, jugadorEnemigoPosX, jugadorEnemigoPosY, 0);
		
		Unidad unaUnidad = unTablero.obtenerUnidad(jugadorEnemigoPosX, jugadorEnemigoPosY);
		
		
		for (int i = 0; i < 10; i++) {
				unTablero.realizarComportamiento(jugadorAliadoPosX, jugadorAliadoPosY, jugadorEnemigoPosX, jugadorEnemigoPosY);
		}
			
			
		Celda unaCelda = unaUnidad.obtenerCelda();	
		
		assertEquals(unaCelda.obtenerEntidad(), null);
	}
	
	@Test
	void test012MoverUnidad() {
		
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador unJugador = new Jugador("pepe");
		Jugador otroJugador = new Jugador("roberto");
		jugadores[0] = unJugador;
		jugadores[1] = otroJugador;
		
		unTablero.generarTerritorios(jugadores);
		Jinete unJinete = new Jinete();	
		unJinete.asignarPropietario(unJugador);
		int posInicialX = 9;
		int posInicialY = 1;
		
		int destinoX = 10;
		int destinoY = 2;
		
		unTablero.agregarUnidad(unJinete, posInicialX, posInicialY);
		try {
			unTablero.moverUnidad(posInicialX, posInicialY, destinoX, destinoY);
			assertEquals(unTablero.obtenerUnidad(posInicialX, posInicialY), null);
			assertEquals(unTablero.obtenerUnidad(destinoX, destinoY), unJinete);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	void test013HayUnidadesEnemigasEnDistanciaCercana() {
		
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador jugadorAliado = new Jugador("juan1");
		Jugador jugadorEnemigo = new Jugador("juan2");
		jugadores[0] = jugadorAliado;
		jugadores[1] = jugadorEnemigo;
		jugadorAliado.agregarTablero(unTablero);
		jugadorEnemigo.agregarTablero(unTablero);
		
		// jugador aliado
		Jinete unJinete = new Jinete();
		unJinete.asignarPropietario(jugadorAliado);
		
		// jugador enemigo
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria.asignarPropietario(jugadorEnemigo);
		
		//Posicionamiento
		unTablero.generarTerritorios(jugadores);
		int pos_2 = 2;
		int pos_3 = 3;
		int pos_15 = 19;
			
		unTablero.agregarUnidad(unJinete, pos_2, pos_2);
		unTablero.agregarUnidad(unSoldadoDeInfanteria, pos_15, pos_2);
		
		assertEquals(unTablero.hayUnidadesEnemigasEnDistanciaCercana(unJinete), false);
	
	}
	

}
