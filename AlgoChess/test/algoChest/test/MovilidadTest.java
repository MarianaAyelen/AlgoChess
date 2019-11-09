package algoChest.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Tablero.Celda;
import Jugadores.*;
import Unidades.*;
import Excepciones.*;


public class MovilidadTest {
	
	@Test
	void test00moverUnidadNoMovil() {
		Catapulta catap = new Catapulta();
		Celda celdaOrigen = new Celda(0,0,true);
		Celda celdaDestino = new Celda(1,1,true);
		catap.asignarCelda(celdaOrigen);
	    assertThrows(movimientoImposibleUnidadNoMovil.class, () -> {
	        catap.mover(celdaDestino);
	    });
	}

	@Test
	void test01moverUnidadMovilACeldaOcupada() {
		Jinete jinete = new Jinete();
		Curandero cur = new Curandero();
		Celda celdaOrigen = new Celda(0,0,true);
		Celda celdaDestino = new Celda(1,1,true);
		jinete.asignarCelda(celdaOrigen);
		cur.asignarCelda(celdaDestino);
	    assertThrows(movimientoImposibleCeldaInhabilitada.class, () -> {
	        jinete.mover(celdaDestino);
	    });		
	}

	@Test
	void test02moverUnidadMovilACeldaVacia() {
		Jinete jinete = new Jinete();
		Celda celdaOrigen = new Celda(0,0,true);
		Celda celdaDestino = new Celda(1,1,true);
		jinete.asignarCelda(celdaOrigen);
		try {
			jinete.mover(celdaDestino);			
		}catch(Exception e)
		{
			assertEquals(true,false);//should never get here
		}
		assertEquals(jinete.obtenerCelda(),celdaDestino);
	}

	@Test
	void test03moverUnidadMovilAMismaCelda() {
		Jinete jinete = new Jinete();
		Celda celdaOrigen = new Celda(0,0,true);
		jinete.asignarCelda(celdaOrigen);
		Celda celdaDestino = celdaOrigen;
	    assertThrows(movimientoImposibleDistanciaNula.class, () -> {
	        jinete.mover(celdaDestino);
	    });		
	}
	
	@Test
	void test04moverUnidadMovilDistanciaMayorAMaxima() {
		Jinete jinete = new Jinete();
		Celda celdaOrigen = new Celda(0,0,true);
		jinete.asignarCelda(celdaOrigen);
		Celda celdaDestino = new Celda(3,3,true);
	    assertThrows(movimientoImposibleDistanciaMayorAMaxima.class, () -> {
	        jinete.mover(celdaDestino);
	    });		
	}
	
	@Test
	void test05moverComoBatallon() {
		Celda celdaOrigen1 = new Celda();
		Celda celdaOrigen2 = new Celda();
		Celda celdaOrigen3 = new Celda();

		celdaOrigen1.asignarPosicion(2, 2);
		celdaOrigen2.asignarPosicion(2, 3); 
		celdaOrigen3.asignarPosicion(2, 4);
		
		Celda celdaDestino1 = new Celda();
		Celda celdaDestino2 = new Celda();
		Celda celdaDestino3 = new Celda();

		celdaDestino1.asignarPosicion(2, 3);
		celdaDestino2.asignarPosicion(2, 4); 
		celdaDestino3.asignarPosicion(2, 5);

		
		SoldadoDeInfanteria soldado1 = new SoldadoDeInfanteria();
		SoldadoDeInfanteria soldado2 = new SoldadoDeInfanteria();
		SoldadoDeInfanteria soldado3 = new SoldadoDeInfanteria();
		soldado1.asignarCelda(celdaOrigen1);
		soldado2.asignarCelda(celdaOrigen2);
		soldado3.asignarCelda(celdaOrigen3);
		
		try {
			soldado1.moverBatallon(soldado2, soldado3, celdaDestino1, celdaDestino2, celdaDestino3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(soldado1.obtenerCelda(),celdaDestino1);
		assertEquals(soldado2.obtenerCelda(),celdaDestino2);
		assertEquals(soldado3.obtenerCelda(),celdaDestino3);
	}

	@Test
	void test06moverComoBatallonFalso() {
		Celda celdaOrigen1 = new Celda();
		Celda celdaOrigen2 = new Celda();
		Celda celdaOrigen3 = new Celda();

		celdaOrigen1.asignarPosicion(2, 2);
		celdaOrigen2.asignarPosicion(2, 5); 
		celdaOrigen3.asignarPosicion(2, 4);
		
		Celda celdaDestino1 = new Celda();
		Celda celdaDestino2 = new Celda();
		Celda celdaDestino3 = new Celda();

		celdaDestino1.asignarPosicion(2, 3);
		celdaDestino2.asignarPosicion(2, 4); 
		celdaDestino3.asignarPosicion(2, 5);

		
		SoldadoDeInfanteria soldado1 = new SoldadoDeInfanteria();
		SoldadoDeInfanteria soldado2 = new SoldadoDeInfanteria();
		SoldadoDeInfanteria soldado3 = new SoldadoDeInfanteria();
		soldado1.asignarCelda(celdaOrigen1);
		soldado2.asignarCelda(celdaOrigen2);
		soldado3.asignarCelda(celdaOrigen3);
		
		try {
			soldado1.moverBatallon(soldado2, soldado3, celdaDestino1, celdaDestino2, celdaDestino3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(soldado1.obtenerCelda(),celdaOrigen1);
		assertEquals(soldado2.obtenerCelda(),celdaOrigen2);
		assertEquals(soldado3.obtenerCelda(),celdaOrigen3);
	}

	
}
