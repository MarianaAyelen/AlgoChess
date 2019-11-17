package Tablero;

import Unidades.Unidad;
import Jugadores.Jugador;


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
	
	public boolean agregarUnidadEnCasilleroVacio( Unidad nuevaUnidad ) throws movimientoImposibleCeldaInhabilitada {
		boolean estaVacia = false;
		if ( this.estaVacia() ) {
			this.agregarUnidad(nuevaUnidad);
			estaVacia = true;
			
		}else {
			System.out.println("La celda no esta vacia");
		}
			//else {
		//	 throw new movimientoImposibleCeldaInhabilitada();
		//}
		return estaVacia;
	}
	
	public void agregarUnidadEnTerritorioAliado(Unidad nuevaUnidad) throws Exception {
		if ( this.obtenerPropietario() == nuevaUnidad.obtenerPropietario() ) {
				this.agregarUnidadEnCasilleroVacio(nuevaUnidad);
		}else {
			System.out.println("No se puede agregar en territorio enemigo");
			 throw new JugadorNoPuedeColocarEntidadesEnTerritorioEnemigo();
			
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
	
	public boolean esDistanciaCercana(Celda otraCelda) {
		boolean esDistanciaCercana = false;
		if (this.calcularDistancia(otraCelda)>0 && this.calcularDistancia(otraCelda)<3) {
			esDistanciaCercana = true;
		}
		return esDistanciaCercana;
	}
	
	public boolean esDistanciaMedia(Celda otraCelda) {
		boolean esMediaDistancia = false;
		//System.out.println(this.calcularDistancia(otraCelda));
		if (this.calcularDistancia(otraCelda)>2 && this.calcularDistancia(otraCelda)<6) {
			

			esMediaDistancia = true;
		}
		return esMediaDistancia;
	}
	
	public boolean esDistanciaLejana(Celda otraCelda) {
		boolean esDistanciaLejana = false;
		if (this.calcularDistancia(otraCelda)>5) {
			esDistanciaLejana = true;
		}
		return esDistanciaLejana;
	}
	
	
	
	// Metodo para tests
	public Unidad obtenerEntidad() {
		return unidad;
	}
	
	public Jugador obtenerPropietario() {
		return propietario;
	}
	
}

