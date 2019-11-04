package algoChest.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Jugadores.Jugador;
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
	
		Jugador jugador1 =  new Jugador("jugador1");
		Jugador jugador2 = new Jugador("jugador2");
		Unidad unidadAliada ;
		Unidad unidadEnemiga;
		
		jugador1.agregarSoldadoDeInfanteria();
		unidadAliada = jugador1.devolverUnidad(0);
		
		jugador2.agregarSoldadoDeInfanteria();
		unidadEnemiga = jugador2.devolverUnidad(0);
		unidadAliada.realizarComportamiento(unidadEnemiga);
			
		assertEquals(unidadEnemiga.vidaDeLaUnidad(), 90);
		
	}


}
