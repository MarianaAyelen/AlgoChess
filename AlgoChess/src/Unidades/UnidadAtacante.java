package Unidades;

import Excepciones.JugadorNoPuedeAtacarFichaAliada;
import Jugadores.*;


public class UnidadAtacante extends Unidad {

	//Se cambio a privado porque lo necesita la clase Unidad
	protected int danioADistancia;
	protected int danioCuerpoAcuerpo;
	protected int danioALargaDistancia;
	
		
	
	public void realizarComportamiento(Unidad unaUnidad) {	
	}
	
	protected void chequearAtaqueAUnidadEnemiga(Unidad unaUnidad) {
		if (this.obtenerPropietario() == unaUnidad.obtenerPropietario()) {
			throw new JugadorNoPuedeAtacarFichaAliada();			
		}
	}
	
	public int devolverDanioSegunDistancia(Unidad unaUnidad) {
		int danio = 0;
		if(this.estaADistanciaCercana(unaUnidad)) {
			danio = danioCuerpoAcuerpo;
		}
		if(this.estaADistanciaMedia(unaUnidad)) {
			danio = danioADistancia;
		}
		if(this.estaADistanciaLejana(unaUnidad)) {
			danio = danioALargaDistancia;
		}
		return danio;
	}
	
	
}
