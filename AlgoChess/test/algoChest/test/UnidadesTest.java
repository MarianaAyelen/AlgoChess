package algoChest.test;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import Unidades.*;

class UnidadesTest {

	@Test
	void test001SoldadoDeInfanteriaAtacaCuerpoACuerpoYRestaVida() {
		
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		SoldadoDeInfanteria otroSoldadoDeInfanteria = new SoldadoDeInfanteria();
		Jinete unJinete = new Jinete();
		Catapulta unaCatapulta = new Catapulta();
		Curandero unCurandero = new Curandero();
		
		
		unSoldadoDeInfanteria.realizarComportamiento(otroSoldadoDeInfanteria);
		unSoldadoDeInfanteria.realizarComportamiento(unaCatapulta);
		unSoldadoDeInfanteria.realizarComportamiento(unCurandero);
		unSoldadoDeInfanteria.realizarComportamiento(unJinete);
		
		assertEquals(otroSoldadoDeInfanteria.vidaDeLaUnidad(), 90);
		assertEquals(unJinete.vidaDeLaUnidad(), 90);
		assertEquals(unaCatapulta.vidaDeLaUnidad(), 40);
		assertEquals(unCurandero.vidaDeLaUnidad(), 65);

	}
	
	@Test
	void test002JineteAtacaCuerpoACuerpoYRestaVida() {
		Jinete unJinete = new Jinete();
		Jinete otroJinete = new Jinete();
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		Catapulta unaCatapulta = new Catapulta();
		Curandero unCurandero = new Curandero();
	
		unJinete.realizarComportamiento(otroJinete);
		unJinete.realizarComportamiento(unSoldadoDeInfanteria);
		unJinete.realizarComportamiento(unaCatapulta);
		unJinete.realizarComportamiento(unCurandero);
		
		assertEquals(otroJinete.vidaDeLaUnidad(), 85);
		assertEquals(unSoldadoDeInfanteria.vidaDeLaUnidad(), 85);
		assertEquals(unaCatapulta.vidaDeLaUnidad(), 35);
		assertEquals(unCurandero.vidaDeLaUnidad(), 60);
	}
	
/*	@Test
	void test003CatapultaAtacaCuerpoACuerpoYNoRestaVida() {
		Catapulta unaCatapulta = new Catapulta();
		Catapulta otraCatapulta = new Catapulta();
		Jinete unJinete = new Jinete();
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		Curandero unCurandero = new Curandero();
	
		unaCatapulta.realizarComportamiento(otraCatapulta);
		unaCatapulta.realizarComportamiento(unJinete);
		unaCatapulta.realizarComportamiento(unSoldadoDeInfanteria);
		unaCatapulta.realizarComportamiento(unCurandero);
		
		assertEquals(unJinete.vidaDeLaUnidad(), 100);
		assertEquals(unSoldadoDeInfanteria.vidaDeLaUnidad(), 100);
		assertEquals(otraCatapulta.vidaDeLaUnidad(), 50);
		assertEquals(unCurandero.vidaDeLaUnidad(), 75);
	}
	*/
	
	/*@Test
	void test004SoldadoDeInfanteriaAtacaADistanciaYNoRestaVida() {
		
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		SoldadoDeInfanteria otroSoldadoDeInfanteria = new SoldadoDeInfanteria();
		Jinete unJinete = new Jinete();
		Catapulta unaCatapulta = new Catapulta();
		Curandero unCurandero = new Curandero();
		
		unSoldadoDeInfanteria.realizarComportamiento(otroSoldadoDeInfanteria);
		unSoldadoDeInfanteria.realizarComportamiento(unJinete);
		unSoldadoDeInfanteria.realizarComportamiento(unaCatapulta);
		unSoldadoDeInfanteria.realizarComportamiento(unCurandero);
		
		assertEquals(otroSoldadoDeInfanteria.vidaDeLaUnidad(), 100);
		assertEquals(unJinete.vidaDeLaUnidad(), 100);
		assertEquals(unaCatapulta.vidaDeLaUnidad(), 50);
		assertEquals(unCurandero.vidaDeLaUnidad(), 75);
	}*/
	
	@Test
	void test005JineteAtacaADistanciaYRestaVida() {
		
		Jinete unJinete = new Jinete();
		Jinete otroJinete = new Jinete();
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		Catapulta unaCatapulta = new Catapulta();
		Curandero unCurandero = new Curandero();
		
		unJinete.realizarComportamiento(otroJinete);
		unJinete.realizarComportamiento(unSoldadoDeInfanteria);
		unJinete.realizarComportamiento(unaCatapulta);
		unJinete.realizarComportamiento(unCurandero);
		
		assertEquals(otroJinete.vidaDeLaUnidad(), 85);
		assertEquals(unSoldadoDeInfanteria.vidaDeLaUnidad(), 85);
		assertEquals(unaCatapulta.vidaDeLaUnidad(),35);
		assertEquals(unCurandero.vidaDeLaUnidad(), 60);
		
	}
	
	@Test
	void test006CatapultaAtacaADistanciaYRestaVida() {
		
		Catapulta unaCatapulta = new Catapulta();
		Catapulta otraCatapulta = new Catapulta();
		Jinete unJinete = new Jinete();
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		Curandero unCurandero = new Curandero();
		
		unaCatapulta.realizarComportamiento(otraCatapulta);
		unaCatapulta.realizarComportamiento(unJinete);
		unaCatapulta.realizarComportamiento(unSoldadoDeInfanteria);
		unaCatapulta.realizarComportamiento(unCurandero);
		
		assertEquals(otraCatapulta.vidaDeLaUnidad(), 30);
		assertEquals(unSoldadoDeInfanteria.vidaDeLaUnidad(), 80);
		assertEquals(unJinete.vidaDeLaUnidad(), 80);
		assertEquals(unCurandero.vidaDeLaUnidad(), 55);
	}
	
	@Test
	void test007CuradorCuraUnaUnidadYSumaVida() {
		
		Curandero unCurandero = new Curandero();
		Curandero otroCurandero = new Curandero();
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		Jinete unJinete = new Jinete();
		Catapulta unaCatapulta = new Catapulta();
		Catapulta otraCatapulta = new Catapulta();
		
		// Se realizaran ataques para que la unidad necesite mas de 15 de vida
		
		otraCatapulta.realizarComportamiento(otroCurandero);
		otraCatapulta.realizarComportamiento(unSoldadoDeInfanteria);
		otraCatapulta.realizarComportamiento(unJinete);
		otraCatapulta.realizarComportamiento(unaCatapulta);
		
		
		unCurandero.realizarComportamiento(otroCurandero);
		unCurandero.realizarComportamiento(unSoldadoDeInfanteria);
		unCurandero.realizarComportamiento(unJinete);
		unCurandero.realizarComportamiento(unaCatapulta);
		
		assertEquals(otroCurandero.vidaDeLaUnidad(), 70);
		assertEquals(unSoldadoDeInfanteria.vidaDeLaUnidad(), 95);
		assertEquals(unJinete.vidaDeLaUnidad(), 95);
		assertEquals(unaCatapulta.vidaDeLaUnidad(), 45);
	}
	
	@Test
	void test008CuradorCuraUnaUnidadSanaYNoSumaVida() {
		
		Curandero unCurandero = new Curandero();
		Curandero otroCurandero = new Curandero();
		SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
		Jinete unJinete = new Jinete();
		Catapulta unaCatapulta = new Catapulta();
		
		unCurandero.realizarComportamiento(otroCurandero);
		unCurandero.realizarComportamiento(unSoldadoDeInfanteria);
		unCurandero.realizarComportamiento(unJinete);
		unCurandero.realizarComportamiento(unaCatapulta);
		
		assertEquals(otroCurandero.vidaDeLaUnidad(), 75);
		assertEquals(unSoldadoDeInfanteria.vidaDeLaUnidad(), 100);
		assertEquals(unJinete.vidaDeLaUnidad(), 100);
		assertEquals(unaCatapulta.vidaDeLaUnidad(), 50);
	}
}	