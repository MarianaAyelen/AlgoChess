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
		
		jugador1.agregarSoldadoDeInfanteria();
		
		jugador2.agregarSoldadoDeInfanteria();
		jugador2.agregarCatapulta();
		jugador2.agregarCurandero();
		jugador2.agregarJinete();
		
		
		
	}


}
