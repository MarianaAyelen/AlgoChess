package Unidades;

import Excepciones.JugadorNoPuedeAtacarFichaAliada;
import Jugadores.*;


public class UnidadAtacante extends Unidad {


	protected int danioADistancia;
	protected int danioCuerpoAcuerpo;
	
	
	public void realizarComportamiento(Unidad unaUnidad) {	
	}
	
	protected void chequearAtaqueAUnidadEnemiga(Unidad unaUnidad) {
		if (this.obtenerPropietario() == unaUnidad.obtenerPropietario()) {
			throw new JugadorNoPuedeAtacarFichaAliada();			
		}
	}
	
}
