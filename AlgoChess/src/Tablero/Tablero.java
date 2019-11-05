package Tablero;

import Jugadores.*;
import Unidades.*;

public class Tablero {

	private Celda[][] tablero;
	private int cantidadFilas;
	private int cantidadColumnas;
	
	public Tablero(int n, int m) {
		cantidadFilas = n;
		cantidadColumnas = m;
		tablero = new Celda[n+2][m+2]; 	// Creo una matriz de referencias a objetos Celda (aun no estan creadas las intancias de las celdas)
		this.generarTablero();
	}
	
	
	private void generarTablero() { 
		  this.generarBordeSuperior();
		  this.generarBordeInferior(); 
		  this.generarBordeIzquierdo();
		  this.generarBordeDerecho(); 
		  this.generarCentro(); 
	}
	
	private void generarBordeSuperior() {
		for (int j = 1; j < cantidadColumnas-1; j++ ) {
			tablero[0][j] = new Celda(0, j, false);;
		}
	}
	
	private void generarBordeInferior() {
		for (int j = 1; j < cantidadColumnas-1; j++ ) {
			tablero[cantidadFilas-1][j] = new Celda(cantidadFilas-1, j, false);
		}
	}
	
	private void generarBordeIzquierdo() {
		for (int i = 0; i < cantidadFilas; i++ ) {
			tablero[i][0] = new Celda(i, 0, false);
		}
	}
	
	private void generarBordeDerecho() {
		for (int i = 0; i < cantidadFilas; i++ ) {
			tablero[i][cantidadColumnas-1] = new Celda(i, cantidadColumnas-1, false);
		}
	}
	
	private void generarCentro() {
		for (int i=1; i<cantidadFilas-1; i++) {
			for (int j=1; j<cantidadColumnas-1; j++) {
				tablero[i][j] = new Celda(i, j, true);
			}	
		}
	}
	
	public void generarTerritorios(Jugador[] vtrJugadores) {
		for (int i=1; 2*i<cantidadFilas-1; i++) {
			for (int j=1; j<(cantidadColumnas-1); j++) {
				tablero[i][j].asignarPropietario(vtrJugadores[0]);
				tablero[i+(cantidadFilas/2)][j].asignarPropietario(vtrJugadores[1]);
			}	
		}
	}
	
	public void agregarUnidad(Unidad nuevaUnidad, int pos_x, int pos_y) {
		tablero[pos_x][pos_y].agregarUnidadEnTerritorioAliado(nuevaUnidad);
	}
	
	//Metodo para los test
	public Unidad obtenerUnidad(int pos_x, int pos_y) {
		return tablero[pos_x][pos_y].obtenerEntidad();
	}
 	
}
