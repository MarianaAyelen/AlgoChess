package Unidades;

import Tablero.Tablero;

public class Jinete extends UnidadAtacante {
	
	public Jinete() {
	
		vidaMax = 100;
		costo = 3;
		vida = 100;
		danioCuerpoAcuerpo = 5;
		danioADistancia = 15;
		movilidad = new unidadMovil();
			}
/* Se reemplaza un realizarComportamiento con todos los tipos de ataque
	public void realizarComportamiento(Unidad unaUnidad) {
		chequearAtaqueAUnidadEnemiga(unaUnidad);
		
		unaUnidad.restarVida(danioADistancia);
	}
*/	
	public void realizarComportamiento(Unidad unaUnidad) {
		chequearAtaqueAUnidadEnemiga(unaUnidad);
		if((this.haySoldadoDeInfanteriaAliadoEnDistanciaCercana(unaUnidad) || !this.hayUnidadesEnemigasCercanas()) && this.estaADistanciaMedia(unaUnidad)) {
			try {
				unaUnidad.restarVida(danioADistancia);
			}catch (unidadSeQuedaSinVida e1) {
				unaUnidad.unidadSinVida();
			} 
		}
		if(this.estaADistanciaCercana(unaUnidad)) {
			try {
				unaUnidad.restarVida(danioCuerpoAcuerpo);
			}catch (unidadSeQuedaSinVida e1) {
				unaUnidad.unidadSinVida();
			} 	
		}
	}
	
	
}
