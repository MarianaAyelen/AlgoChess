package Tablero;

import Unidades.SoldadoDeInfanteria;
import Unidades.Unidad;
import Jugadores.Jugador;

import java.util.Iterator;
import java.util.List;

import Excepciones.*;

public class Celda {
	private Jugador propietario;
	private boolean habilitada;
	private Unidad unidad;
	private int pos_x;
	private int pos_y;
	
	public Celda() {
		unidad = null;
		propietario = null;
		habilitada = true;
	}
	
	public Celda(boolean flag) {
		unidad = null;
		propietario = null;
		habilitada = flag;
	}
	
	public Celda(int x, int y, boolean flag) {
		unidad = null;
		propietario = null;
		habilitada = flag;
		pos_x = x;
		pos_y = y;
	}
	
	public Celda(Posicion pos) {
		pos_x = pos.getFila();
		pos_y = pos.getColumna();
		unidad = null;
		propietario = null;
		habilitada = true;
	}
		
	public void asignarPropietario(Jugador unJugador) {
		propietario = unJugador;
	}
	
	private void vaciarCelda() {
		habilitada = true;
	}
	
	private void ocuparCelda() {
		habilitada = false;
	}
	
	public boolean estaVacia() {
		return habilitada;
	}
	
	public void agregarUnidad (Unidad nuevaUnidad) {
		unidad = nuevaUnidad;
		this.ocuparCelda();
		unidad.asignarCelda(this);
	}
	
	public void agregarUnidadEnCasilleroVacio( Unidad nuevaUnidad ) throws movimientoImposibleCeldaInhabilitada {
		if ( this.estaVacia() ) {
			this.agregarUnidad(nuevaUnidad);
		}else {
			 throw new movimientoImposibleCeldaInhabilitada();
		}
	}
	
	public void agregarUnidadEnTerritorioAliado(Unidad nuevaUnidad) throws Exception {
		if ( this.obtenerPropietario() == nuevaUnidad.obtenerPropietario() ) {
				this.agregarUnidadEnCasilleroVacio(nuevaUnidad);
		}else {
			 throw new JugadorNoPuedeColocarEntidadesEnTerritorioEnemigo();
			//System.out.println("No se puede colocar una unidad en territorio enemigo.");
		}
	}
	
	public void sacarUnidad() {
		unidad = null;
		this.vaciarCelda();
	}
	
	public void asignarPosicion(int x, int y) {
		pos_x = x;
		pos_y = y;
	}
	
	public int obtenerPosicionX() {
		return pos_x;
	}

	public int obtenerPosicionY() {
		return pos_y;
	}
	
	// Metodo para tests
	public Unidad obtenerEntidad() {
		return unidad;
	}
	
	public Jugador obtenerPropietario() {
		return propietario;
	}
	
	public int calcularDistancia(Celda otraCelda) {
		int deltaX = this.pos_x - otraCelda.pos_x;
		int deltaY = this.pos_y - otraCelda.pos_y;
		return (int) (Math.sqrt(deltaX*deltaX + deltaY*deltaY)  ) ;
	}
	
	public boolean mismaFila(Celda otraCelda) {
		return (this.pos_x==otraCelda.pos_x);
	}

	public boolean mismaColumna(Celda otraCelda) {
		return (this.pos_y==otraCelda.pos_y);
	}

	public boolean mismaDiagonal(Celda otraCelda) {
		return true;
	}
	
	public void aumentarFila() {
		pos_x = pos_x + 1;
	}
	
	public void aumentarColumna() {
		pos_y = pos_y + 1;
	}
	
	public boolean tresCeldasUnidas(Celda celda2 , Celda celda3 ) {
		boolean celdasUnidas = false;
		
		if(this.calcularDistancia(celda2)==1) {
			if(this.calcularDistancia(celda3)==1) {
				celdasUnidas = true;
			}
			if(celda2.calcularDistancia(celda3)==1) {
				celdasUnidas = true;	
			}
		}
		if(this.calcularDistancia(celda3)==1) {
			if(celda2.calcularDistancia(celda3)==1) {
				celdasUnidas = true;
			}else {
				celdasUnidas = false;
			}		
		}
		
		return celdasUnidas;
	}
}

