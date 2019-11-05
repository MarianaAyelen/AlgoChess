package Unidades;

import Excepciones.SoldadoDeInfanteriaSoloAtacaACortaDistancia;

public class SoldadoDeInfanteria extends UnidadAtacante {

		public SoldadoDeInfanteria() {
			
			vidaMax = 100;
			costo = 1;
			vida = 100;
			danioCuerpoAcuerpo = 10;
			danioADistancia = 0; 
			movilidad = new unidadMovil();
		
		}
		
		public void realizarComportamiento(Unidad unaUnidad) {
			chequearAtaqueAUnidadEnemiga(unaUnidad);
			int distancia = this.calcularDistancia(unaUnidad);
			if (distancia > 2) {
				throw new SoldadoDeInfanteriaSoloAtacaACortaDistancia();
			}
			unaUnidad.restarVida(danioCuerpoAcuerpo);
		}
		
	
		
}
