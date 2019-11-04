package Unidades;

import Excepciones.JugadorNoPuedeCurarAunidadEnemiga;

public class UnidadCuradora extends Unidad {

	protected int curacion; 
	
	public void realizarComportamiento(Unidad unaUnidad) {
		chequearCuracionAUnidadAliada(unaUnidad);
		unaUnidad.sumarVida(curacion);
	}
	
	protected void chequearCuracionAUnidadAliada(Unidad unaUnidad) {
		if (this.obtenerPropietario() != unaUnidad.obtenerPropietario()) {
			throw new JugadorNoPuedeCurarAunidadEnemiga();
		}
	}
}
