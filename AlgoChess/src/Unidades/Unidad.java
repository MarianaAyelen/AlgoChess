package Unidades;



import Jugadores.*;
import Excepciones.*;
import Tablero.*;
import java.lang.Math;

public class Unidad {
	
	protected Celda celdaActual;
	protected Jugador propietario;
	protected int costo;
	protected int vida;
	protected int vidaMax;
	protected Movilidad movilidad;
	
	public int calcularDistancia(Unidad otraUnidad) {
		int deltaX = this.obtenerPosicionX() - otraUnidad.obtenerPosicionX();
		int deltaY = this.obtenerPosicionY() - otraUnidad.obtenerPosicionY();
		return ( deltaX*deltaX + deltaY*deltaY ) ;
	}
	
	public void asignarCelda(Celda unaCelda) {
		celdaActual = unaCelda;
	}
	
	public int obtenerPosicionX() {
		return celdaActual.obtenerPosicionX();
	}
	
	public int obtenerPosicionY() {
		return celdaActual.obtenerPosicionY();
	}
	
	public void asignarPropietario(Jugador unJugador) {
		propietario = unJugador;
	}
	
	public void restarVida(int danioSufrido) {
	
		vida = vida - danioSufrido; 
	}	
	
	public void sumarVida(int curacionGanada) {
		vida = vida + curacionGanada;
		if(vida > vidaMax) {
			vida = vidaMax; 
		}
			
	}
	
	public int obtenerCosto() {
		return costo;
	}

	public void realizarComportamiento(Unidad unaUnidad) {
	}
	
	public Jugador obtenerPropietario() {
		return propietario;
	}
	// Metodos utilizados para las pruebas 
	
	public int vidaDeLaUnidad() {
		return vida;
	}
	
	public Celda obtenerCelda() {
		return celdaActual; 
	}
	
	
}