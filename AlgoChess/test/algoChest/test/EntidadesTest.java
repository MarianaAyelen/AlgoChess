package algoChest.test;

import static org.junit.jupiter.api.Assertions.*;

import org.hamcrest.SelfDescribing;
import org.junit.jupiter.api.Test;

import Excepciones.CatapultaSoloAtacaADistancia;
import Excepciones.CuranderoSoloCuraADistanciaMedia;
import Excepciones.JugadorNoPuedeAtacarFichaAliada;
import Excepciones.JugadorNoPuedeCurarAunidadEnemiga;
import Excepciones.SoldadoDeInfanteriaSoloAtacaACortaDistancia;
import Jugadores.Jugador;
import Tablero.Celda;
import Tablero.Tablero;
import Unidades.*;

class EntidadesTest {

	
	@Test
	void test001SoldadoDeInfanteriaAliadoAtacaAPiezaYVerificaQueSeRestaVida() {
	
		Celda celdaAliado = new Celda();
		Celda celdaEnemigo = new Celda();
		
		celdaAliado.asignarPosicion(1, 1);
		celdaEnemigo.asignarPosicion(1, 2);
		Jugador jugadorAliado = new Jugador("mariana");
		Jugador jugadorEnemimigo = new Jugador("alejandro");
		
		// jugador enemigo 
		Jinete unJinete = new Jinete();
		unJinete.asignarPropietario(jugadorEnemimigo);
		
		// jugador aliado
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria.asignarPropietario(jugadorAliado);
		
		unJinete.asignarCelda(celdaEnemigo);
		unSoldadoDeInfanteria.asignarCelda(celdaAliado);
		
		
		unSoldadoDeInfanteria.realizarComportamiento(unJinete);
		
		assertEquals(unJinete.vidaDeLaUnidad(), 90);
	
	}		
	
	@Test
	void test002JineteAliadoAtacaAPiezaYVerificaQueSeRestaVida() {
		Celda celdaAliado = new Celda();
		Celda celdaEnemigo = new Celda();
		
		celdaAliado.asignarPosicion(1, 1);
		celdaEnemigo.asignarPosicion(1, 2);
		Jugador jugadorAliado = new Jugador("mariana");
		Jugador jugadorEnemimigo = new Jugador("alejandro");
		
		// jugador aliado
		Jinete unJinete = new Jinete();
		unJinete.asignarPropietario(jugadorAliado);
		
		// jugador enemigo
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria.asignarPropietario(jugadorEnemimigo);
		
		unJinete.asignarCelda(celdaAliado);
		unSoldadoDeInfanteria.asignarCelda(celdaEnemigo);
		
		
		unJinete.realizarComportamiento(unSoldadoDeInfanteria);
		
		assertEquals(unSoldadoDeInfanteria.vidaDeLaUnidad(), 85);
	
	}		
	
	@Test
	void test003CatapultaAliadoAtacaAPiezaYVerificaQueSeRestaVida() {
		Celda celdaAliado = new Celda();
		Celda celdaEnemigo = new Celda();
		
		celdaAliado.asignarPosicion(1, 1);
		celdaEnemigo.asignarPosicion(1, 12);
		Jugador jugadorAliado = new Jugador("mariana");
		Jugador jugadorEnemimigo = new Jugador("alejandro");
		
		// jugador aliado
		Catapulta unaCatapulta = new Catapulta();
		unaCatapulta.asignarPropietario(jugadorAliado);
		
		// jugador enemigo
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria.asignarPropietario(jugadorEnemimigo);
		
		unaCatapulta.asignarCelda(celdaAliado);
		unSoldadoDeInfanteria.asignarCelda(celdaEnemigo);
		
		
		unaCatapulta.realizarComportamiento(unSoldadoDeInfanteria);
		
		assertEquals(unSoldadoDeInfanteria.vidaDeLaUnidad(), 80);
	
	
	}		
	
	@Test
	void test004CuranderoAliadoCuraAPiezaYVerificaQueSeSumaVida() {
		Celda celdaCurandero = new Celda();
		Celda celdaSoldado = new Celda();
		Celda celdaCatapulta = new Celda();
		
		celdaCurandero.asignarPosicion(1, 4);
		celdaSoldado.asignarPosicion(1, 1);
		
		celdaCatapulta.asignarPosicion(12, 4);
		
		Jugador jugadorAliado = new Jugador("mariana");
		Jugador jugadorEnemimigo = new Jugador("alejandro");
		
		// jugador enemigo
		Catapulta unaCatapulta = new Catapulta();
		unaCatapulta.asignarPropietario(jugadorEnemimigo);
		
		// jugador aliado
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria.asignarPropietario(jugadorAliado);
		
		Curandero unCurandero = new Curandero();
		unCurandero.asignarPropietario(jugadorAliado);
		
		unaCatapulta.asignarCelda(celdaCatapulta);
		unSoldadoDeInfanteria.asignarCelda(celdaSoldado);
		unCurandero.asignarCelda(celdaCurandero);
		
		unaCatapulta.realizarComportamiento(unSoldadoDeInfanteria);
		
		try {
			unCurandero.realizarComportamiento(unSoldadoDeInfanteria);

		} catch (CuranderoSoloCuraADistanciaMedia e) {
			System.out.println("Curandero solo cura a distancia media");
		}

		assertEquals(unSoldadoDeInfanteria.vidaDeLaUnidad(), 95);
		
	}		

	@Test
	void test005UnidadAliadaNoPuedeAtacarUnidadAliada() {

		Jugador unJugador = new Jugador("mariana");
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		Jinete unJinete = new Jinete();
		unSoldadoDeInfanteria.asignarPropietario(unJugador);
		unJinete.asignarPropietario(unJugador);
		
		try {
			unSoldadoDeInfanteria.realizarComportamiento(unJinete);
		}catch (JugadorNoPuedeAtacarFichaAliada e) {
			System.out.println("No se puede atacar unidad aliada");
		}
		assertEquals(unJinete.vidaDeLaUnidad(), 100);
	}		

	@Test
	void test006UnidadAliadaNoPuedeCurarUndidadEnemiga() {
		Jugador jugadorAliado = new Jugador("mariana");
		Jugador jugadorEnemigo = new Jugador("Alejandro");
		Celda celdaAliada = new Celda();
		Celda celdaEnemiga = new Celda();
		
		celdaAliada.asignarPosicion(1, 1);
		celdaEnemiga.asignarPosicion(1, 2);

		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		SoldadoDeInfanteria otroSoldadoDeInfanteria = new SoldadoDeInfanteria();
		Curandero unCurandero = new Curandero();
		
		// unidades jugador enemigo
		unSoldadoDeInfanteria.asignarPropietario(jugadorEnemigo);
		unSoldadoDeInfanteria.asignarCelda(celdaEnemiga);
		
		// unidades jugador aliado
		otroSoldadoDeInfanteria.asignarPropietario(jugadorAliado);
		otroSoldadoDeInfanteria.asignarCelda(celdaAliada);
		
		unCurandero.asignarPropietario(jugadorAliado);
		unCurandero.asignarCelda(celdaAliada);
		
		try {
			otroSoldadoDeInfanteria.realizarComportamiento(unSoldadoDeInfanteria);
		} catch (SoldadoDeInfanteriaSoloAtacaACortaDistancia e) {
			System.out.println("Soldado de infanteria solo ataca a corta distancia");
		}
			
		try {
			unCurandero.realizarComportamiento(unSoldadoDeInfanteria);
		}catch (JugadorNoPuedeCurarAunidadEnemiga e) {
			System.out.println("No se puede curar unidad enemiga");
		}
		
		assertEquals(unSoldadoDeInfanteria.vidaDeLaUnidad(), 90);
	}		

	@Test
	void test007SoldadoDeInfanteriaSoloPuedeAtacarACortaDistancia() {
	
		Celda celdaAliado = new Celda();
		Celda celdaEnemigo = new Celda();
		
		celdaAliado.asignarPosicion(1, 1);
		celdaEnemigo.asignarPosicion(1, 6);
		Jugador jugadorAliado = new Jugador("mariana");
		Jugador jugadorEnemimigo = new Jugador("alejandro");
		
		// jugador enemigo 
		Jinete unJinete = new Jinete();
		unJinete.asignarPropietario(jugadorEnemimigo);
		unJinete.asignarCelda(celdaEnemigo);
		
		// jugador aliado
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria.asignarPropietario(jugadorAliado);
		unSoldadoDeInfanteria.asignarCelda(celdaAliado);
		
		try {
			unSoldadoDeInfanteria.realizarComportamiento(unJinete);
			
		} catch (SoldadoDeInfanteriaSoloAtacaACortaDistancia e) {
			System.out.println("Soldado de infanteria solo puede atacar a corta distancia");
		}
		
		assertEquals(unJinete.vidaDeLaUnidad(), 100);
	
	}		
	
	@Test
	void test008CatapultaSoloAtacaADistanciaslejanas() {
		Celda celdaAliado = new Celda();
		Celda celdaEnemigo = new Celda();
		
		celdaAliado.asignarPosicion(1, 1);
		celdaEnemigo.asignarPosicion(1, 2);
		Jugador jugadorAliado = new Jugador("mariana");
		Jugador jugadorEnemimigo = new Jugador("alejandro");
		
		// jugador aliado
		Catapulta unaCatapulta = new Catapulta();
		unaCatapulta.asignarPropietario(jugadorAliado);
		
		// jugador enemigo
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria.asignarPropietario(jugadorEnemimigo);
		
		unaCatapulta.asignarCelda(celdaAliado);
		unSoldadoDeInfanteria.asignarCelda(celdaEnemigo);
		
		try {
			unaCatapulta.realizarComportamiento(unSoldadoDeInfanteria);
			
		} catch (CatapultaSoloAtacaADistancia e) {
			System.out.println("Catapulta solo puede atacar a distancias lejanas");
		}
		
		assertEquals(unSoldadoDeInfanteria.vidaDeLaUnidad(), 100);
	
	
	}		

	@Test
	void test009CuranderoAliadoCuraAPiezaYVerificaQueSeSumaVida() {
		Celda celdaCurandero = new Celda();
		Celda celdaSoldado = new Celda();
		Celda celdaCatapulta = new Celda();
		
		celdaCurandero.asignarPosicion(1, 14);
		celdaSoldado.asignarPosicion(1, 1);
		
		celdaCatapulta.asignarPosicion(12, 4);
		
		Jugador jugadorAliado = new Jugador("mariana");
		Jugador jugadorEnemimigo = new Jugador("alejandro");
		
		// jugador enemigo
		Catapulta unaCatapulta = new Catapulta();
		unaCatapulta.asignarPropietario(jugadorEnemimigo);
		
		// jugador aliado
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		unSoldadoDeInfanteria.asignarPropietario(jugadorAliado);
		
		Curandero unCurandero = new Curandero();
		unCurandero.asignarPropietario(jugadorAliado);
		
		unaCatapulta.asignarCelda(celdaCatapulta);
		unSoldadoDeInfanteria.asignarCelda(celdaSoldado);
		unCurandero.asignarCelda(celdaCurandero);
		
		unaCatapulta.realizarComportamiento(unSoldadoDeInfanteria);
		
		try {
			unCurandero.realizarComportamiento(unSoldadoDeInfanteria);

		} catch (CuranderoSoloCuraADistanciaMedia e) {
			System.out.println("Curandero solo cura a distancia media");
		}

		assertEquals(unSoldadoDeInfanteria.vidaDeLaUnidad(), 80);
		
	}		

}
