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
		unidad.asignarCelda(this);
		this.ocuparCelda();
	}
	
	public void agregarUnidadEnCasilleroVacio( Unidad nuevaUnidad ) {
		if ( this.estaVacia() ) {
			this.agregarUnidad(nuevaUnidad);
		}else {
			// throw new movimientoImposibleCeldaInhabilitada();
		}
	}
	
	public void agregarUnidadEnTerritorioAliado(Unidad nuevaUnidad) {
		if ( this.obtenerPropietario() == nuevaUnidad.obtenerPropietario() ) {
				this.agregarUnidadEnCasilleroVacio(nuevaUnidad);
		}else {
			// throw new JugadorNoPuedeColocarEntidadesEnTerritorioEnemigo();
			System.out.println("No se puede colocar una unidad en territorio enemigo.");
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
}

