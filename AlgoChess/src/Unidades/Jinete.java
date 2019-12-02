package Unidades;

import Tablero.Tablero;
import Excepciones.*;

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
		if(this.haySoldadoDeInfanteriaAliadoEnDistanciaCercana(unaUnidad) || !this.hayUnidadesEnemigasCercanas()) {
			//ataque solo a media distancia
			if(this.estaADistanciaMedia(unaUnidad)) {
				try {
					unaUnidad.restarVida(danioADistancia);
				}catch (unidadSeQuedaSinVida e1) {
					unaUnidad.unidadSinVida();
				}
			} else {
				//tirar excepcion: soloPuedeAtacarADistanciaMedia
				throw new JineteSoloPuedeAtacarADistanciaMedia();
			}
		}else if(this.hayUnidadesEnemigasCercanas()) {
			if(this.haySoldadoDeInfanteriaAliadoEnDistanciaCercana(unaUnidad)){
				//tirar excepcion: noPuedeAtacarConSoldadosAliadosCerca
				throw new JineteNoPuedeAtacarConSoldadosAliadosCerca();
			}else {
				//ataque solo a corta distancia
				if(this.estaADistanciaCercana(unaUnidad)) {
					try {
						unaUnidad.restarVida(danioCuerpoAcuerpo);
					}catch (unidadSeQuedaSinVida e1) {
						unaUnidad.unidadSinVida();
					}
				}else {
					//tirar excepcion: soloPuedeAtacarADistanciaCorta
					throw new JineteSoloPuedeAtacarADistanciaCorta();
				}
			}
		}
	}
	
	//Tipo de unidad
	public int tipoDeUnidad() {
		return 2;
	}
	
	
}
