package Unidades;

import Excepciones.JugadorNoPuedeAtacarFichaAliada;

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
	/*
	public void atacarCuerpoACuerpo(Unidad unaUnidad) {
		unaUnidad.restarVida(danioCuerpoAcuerpo);
	}
	
	public void atacarADistancia(Unidad unaUnidad) {
		unaUnidad.restarVida(danioADistancia);
	}
	*/
	
}
