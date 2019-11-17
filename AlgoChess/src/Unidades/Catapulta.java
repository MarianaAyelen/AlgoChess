package Unidades;

import Excepciones.CatapultaSoloAtacaADistancia;
import Excepciones.SoldadoDeInfanteriaSoloAtacaACortaDistancia;

public class Catapulta extends UnidadAtacante {

	public Catapulta() {
		
		
		vidaMax = 50;  
		costo = 5;
		vida = 50;
		danioCuerpoAcuerpo = 0;
		danioADistancia = 20;
		movilidad = new unidadNoMovil();
	}
	
	public void realizarComportamiento(Unidad unaUnidad) {
		chequearAtaqueAUnidadEnemiga(unaUnidad);
		
		int distancia = this.calcularDistancia(unaUnidad);
		
		if (distancia < 6) {
			throw new CatapultaSoloAtacaADistancia();
		}
		try {
		unaUnidad.restarVida(danioADistancia);
		}catch (unidadSeQuedaSinVida e1) {
			unaUnidad.unidadSinVida();
		} 
	
	}
}
