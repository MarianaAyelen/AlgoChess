package algoChest.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Tablero.Celda;
import Jugadores.*;
import Unidades.*;
import Excepciones.*;


public class MovilidadTest {
	
	@Test
	void moverUnidadNoMovil() {
		Catapulta catap = new Catapulta();
		Celda celdaOrigen = new Celda(0,0,true);
		Celda celdaDestino = new Celda(1,1,true);
		catap.asignarCelda(celdaOrigen);
	    assertThrows(movimientoImposibleUnidadNoMovil.class, () -> {
	        catap.mover(celdaDestino);
	    });
	}

	@Test
	void moverUnidadMovilACeldaOcupada() {
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
	void moverUnidadMovilACeldaVacia() {
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
	void moverUnidadMovilAMismaCelda() {
		Jinete jinete = new Jinete();
		Celda celdaOrigen = new Celda(0,0,true);
		jinete.asignarCelda(celdaOrigen);
		Celda celdaDestino = celdaOrigen;
	    assertThrows(movimientoImposibleDistanciaNula.class, () -> {
	        jinete.mover(celdaDestino);
	    });		
	}
	
	@Test
	void moverUnidadMovilDistanciaMayorAMaxima() {
		Jinete jinete = new Jinete();
		Celda celdaOrigen = new Celda(0,0,true);
		jinete.asignarCelda(celdaOrigen);
		Celda celdaDestino = new Celda(3,3,true);
	    assertThrows(movimientoImposibleDistanciaMayorAMaxima.class, () -> {
	        jinete.mover(celdaDestino);
	    });		
	}


	
}
