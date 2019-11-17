package Tablero;

import Jugadores.*;
import Unidades.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

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
		for (int j = 1; j < cantidadColumnas+1; j++ ) {
			tablero[0][j] = new Celda(0, j, false);;
		}
	}
	
	private void generarBordeInferior() {
		for (int j = 1; j < cantidadColumnas+1; j++ ) {
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
		try {
			tablero[pos_x][pos_y].agregarUnidadEnTerritorioAliado(nuevaUnidad);			
		}catch(Exception e) {
			//TODO
		}
	}
	
	public void moverUnidad(int posInicialx, int posInicialy,int posFinalx, int posFinaly ) throws Exception {
		
		Celda celdaFinal = this.obtenerCelda(posFinalx, posFinaly);
		this.obtenerUnidad(posInicialx, posInicialy).mover(celdaFinal);
		
	}

	public void moverBatallon(int[] posInicialx, int[] posInicialy, int[] posFinalx, int[] posFinaly ) throws Exception {
		
		Celda destino1 = this.obtenerCelda(posFinalx[0], posFinaly[0]);
		Celda destino2 = this.obtenerCelda(posFinalx[1], posFinaly[1]);
		Celda destino3 = this.obtenerCelda(posFinalx[2], posFinaly[2]);
		
		SoldadoDeInfanteria soldado1 = (SoldadoDeInfanteria) this.obtenerUnidad(posInicialx[0], posInicialy[0]);
		SoldadoDeInfanteria soldado2 = (SoldadoDeInfanteria) this.obtenerUnidad(posInicialx[1], posInicialy[1]);
		SoldadoDeInfanteria soldado3 = (SoldadoDeInfanteria) this.obtenerUnidad(posInicialx[2], posInicialy[2]);
		soldado1.moverBatallon(soldado2, soldado3, destino1, destino2, destino3);
	}
	
	public List<Celda> devolverCeldasDistanciaCercana(Celda unaCelda) {
		List<Celda> celdasCercanas = new ArrayList<Celda>();
		for(int row=1; row< cantidadFilas-1; row++) { // -1 porque los bordes no cuentan
			for(int col=1; col< cantidadColumnas-1; col++) {
				if(tablero[row][col].esDistanciaCercana(unaCelda)) {
					celdasCercanas.add(tablero[row][col]);
				}
			}
		}
		return celdasCercanas;
	}
	
	public boolean hayUnidadesEnemigasEnDistanciaCercana(Unidad unaUnidad) {
		boolean hayUnidadesEnemigasEnDistanciaCercana = false; 
		List<Celda> celdasCercanas = new ArrayList<Celda>();
		celdasCercanas = this.devolverCeldasDistanciaCercana(unaUnidad.obtenerCelda());
		Iterator<Celda> iteradorCelda = celdasCercanas.iterator();
		Celda celdaAuxiliar = new Celda();
		while (iteradorCelda.hasNext()) {
			celdaAuxiliar = iteradorCelda.next();
			if(!celdaAuxiliar.estaVacia()) {
				if(!(celdaAuxiliar.obtenerEntidad().esUnidadAliada(unaUnidad))) {
			
					hayUnidadesEnemigasEnDistanciaCercana = true;
				}
			
			}
		}
		return hayUnidadesEnemigasEnDistanciaCercana;
	}
	
	public void realizarComportamiento(int posInicialx, int posInicialy,int posFinalx, int posFinaly ) {
		Unidad unaUnidad = this.obtenerUnidad(posInicialx, posInicialy);
		Unidad otraUnidad = this.obtenerUnidad(posFinalx, posFinaly);
		if(unaUnidad != null && otraUnidad != null) {
			unaUnidad.realizarComportamiento(otraUnidad);
		}
		if(unaUnidad == null ) {
			System.out.println("unidad atacante nula");
		}
		if(otraUnidad == null ) {
			System.out.println("unidad defensora nula");		
		}
		
	}
	
	//Metodo para los test
	public Unidad obtenerUnidad(int pos_x, int pos_y) {
		return tablero[pos_x][pos_y].obtenerEntidad();
	}
	
	private Celda obtenerCelda(int pos_x, int pos_y) {
		return tablero[pos_x][pos_y];
	}
	
	
}