package Tablero;

import Unidades.Unidad;

public class Celda {
	protected Posicion posicion;
	protected boolean libre;
	protected Unidad unidad;
	
	public Celda(Posicion nuevaPosicion) {
		posicion = nuevaPosicion;
		libre = true;
		unidad = null;
	}
	
	private void setPosicion(Posicion nuevaPosicion) {
		posicion = nuevaPosicion;
	}
	
	private void vaciarCelda() {
		libre = true;
	}
	
	private void ocuparCelda() {
		libre = false;
	}
	
	public boolean estaVacia() {
		return libre;
	}
	
	public void agregarUnidad(Unidad nuevaUnidad) {
		unidad = nuevaUnidad;
		this.ocuparCelda();
	}
	
	public void sacarUnidad() {
		unidad = null;
		this.vaciarCelda();
	}
	
}

