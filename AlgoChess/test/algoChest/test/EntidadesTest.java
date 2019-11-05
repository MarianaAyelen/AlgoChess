package algoChest.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Excepciones.JugadorNoPuedeAtacarFichaAliada;
import Excepciones.JugadorNoPuedeCurarAunidadEnemiga;
import Jugadores.Jugador;
import Tablero.Tablero;
import Unidades.*;

class EntidadesTest {

	@Test
	void test001EntidadMovibleSePuedeMoverEnTodasLasDirecciones() {
	
	}
	@Test
	void test002EntidadMovibleNoPuedeMoverseACasilleroOcupado() {
	
	}
	
	@Test
	void test003SoldadoDeInfanteriaAliadoAtacaAPiezaYVerificaQueSeRestaVida() {
	
		Tablero tablero = new Tablero(20,20);
		Jugador jugador1 =  new Jugador("jugador1");
		Jugador jugador2 = new Jugador("jugador2");
		Unidad unidadAliada ;
		Unidad unidadEnemiga;
	/*	
		jugador1.agregarSoldadoInfanteria();
		jugador2.agregarSoldadoInfanteria();
		jugador1.agregarUnidadAlTablero(tablero,1,1);
		jugador2.agregarUnidadAlTablero(tablero,1,2);
		*/
	}		
	
	@Test
	void test004JineteAliadoAtacaAPiezaYVerificaQueSeRestaVida() {
	}		
	
	@Test
	void test005CatapultaAliadoAtacaAPiezaYVerificaQueSeRestaVida() {
	}		
	
	@Test
	void test006CuranderoAliadoCuraAPiezaYVerificaQueSeSumaVida() {
	}		

	@Test
	void test007UnidadAliadaNoPuedeAtacarUnidadAliada() {

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
	void test008UnidadAliadaNoPuedeCurarUndidadEnemiga() {
		Jugador jugadorAliado = new Jugador("mariana");
		Jugador jugadorEnemigo = new Jugador("Alejandro");
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		SoldadoDeInfanteria otroSoldadoDeInfanteria = new SoldadoDeInfanteria();
		Curandero unCurandero = new Curandero();
		unSoldadoDeInfanteria.asignarPropietario(jugadorEnemigo);
		otroSoldadoDeInfanteria.asignarPropietario(jugadorAliado);
		unCurandero.asignarPropietario(jugadorAliado);
		otroSoldadoDeInfanteria.realizarComportamiento(unSoldadoDeInfanteria);
		
		try {
			unCurandero.realizarComportamiento(unSoldadoDeInfanteria);
		}catch (JugadorNoPuedeCurarAunidadEnemiga e) {
			System.out.println("No se puede curar unidad enemiga");
		}
		
		assertEquals(unSoldadoDeInfanteria.vidaDeLaUnidad(), 90);
	}		

}
