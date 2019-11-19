package Unidades;

import Excepciones.CuranderoSoloCuraADistanciaMedia;
import Excepciones.JugadorNoPuedeCurarAunidadEnemiga;

public class Curandero extends UnidadCuradora {

		public Curandero() {
			vidaMax = 75;
			vida = 75;
			curacion = 15;	
			movilidad = new unidadMovil();
		}
		
		public void realizarComportamiento(Unidad unaUnidad) {
			chequearCuracionAUnidadAliada(unaUnidad);
			int distancia = this.calcularDistancia(unaUnidad);
			if(distancia < 3 || distancia > 5) {
				throw new CuranderoSoloCuraADistanciaMedia();
			}
			unaUnidad.sumarVida(curacion);
		}
		
		protected void chequearCuracionAUnidadAliada(Unidad unaUnidad) {
			if (this.obtenerPropietario() != unaUnidad.obtenerPropietario()) {
				throw new JugadorNoPuedeCurarAunidadEnemiga();
			}
		}
}
