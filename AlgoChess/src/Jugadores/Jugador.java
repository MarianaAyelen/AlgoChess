package Jugadores;



//import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import Excepciones.JugadorNoPuedeAgregarMasEntidades;
import Excepciones.JugadorNoTieneMasPuntos;
import Unidades.Unidad;
import Unidades.SoldadoDeInfanteria;
import Unidades.*;
import Tablero.Celda;
import Tablero.Tablero;

public class Jugador {

		private String nombre;
		private int puntosIniciales;
		private List<Unidad> unidadesJugador = new ArrayList<Unidad>();
		private Tablero tablero;
		private int cantidadDeJinetesColocados;
		private int cantidadDeSoldadosColocados;
		private int cantidadDeCuranderosColocados;
		private int cantidadDeCatapultasColocadas;
		
		public Jugador(String nombreJugador) {
			nombre = nombreJugador;
			puntosIniciales = 20;
			cantidadDeJinetesColocados = 0;
			cantidadDeSoldadosColocados = 0;
			cantidadDeCuranderosColocados = 0;
			cantidadDeCatapultasColocadas =0 ;

		}
		
		public void turnoJugador() {
			

			Celda celdaOrigen, celdaDestino;
			int posOrigenX, posOrigenY, posFinalX, posFinaly;
			
			
			System.out.println("Elegir pieza para jugar este turno");			
			//elegir celda por tablero
			celdaOrigen = new Celda();
			posOrigenX = celdaOrigen.obtenerPosicionX();
			posOrigenY = celdaOrigen.obtenerPosicionY();

			System.out.println("Elegir pieza para realizar comportamiento");			
			//elegir celda por tablero
			celdaDestino = new Celda();
			posFinalX = celdaDestino.obtenerPosicionX();
			posFinaly = celdaDestino.obtenerPosicionY();
			
			tablero.realizarComportamiento(posOrigenX,posOrigenY,posFinalX,posFinaly);
			
		}
		
		public void movimientoJugador() {
			Celda celdaOrigen, celdaDestino;
			int posOrigenX, posOrigenY, posFinalX, posFinaly;
			
			
			System.out.println("Elegir pieza para mover este turno");			
			//elegir celda por tablero
			celdaOrigen = new Celda();
			posOrigenX = celdaOrigen.obtenerPosicionX();
			posOrigenY = celdaOrigen.obtenerPosicionY();

			System.out.println("Elegir celda destino");			
			//elegir celda por tablero
			celdaDestino = new Celda();
			posFinalX = celdaDestino.obtenerPosicionX();
			posFinaly = celdaDestino.obtenerPosicionY();
			try {
				tablero.moverUnidad(posOrigenX, posOrigenY, posFinalX, posFinaly);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}
		
		public void agregarTablero(Tablero tableroDelJuego) {
			tablero = tableroDelJuego;
		}
		
		public boolean hayUnidadesEnemigasCercanas(Unidad unaUnidad) {
			return tablero.hayUnidadesEnemigasEnDistanciaCercana(unaUnidad);
		}
		
		// +++++++ Soldado +++++++
		public void agregarSoldadoInfanteria(int posicionX, int posicionY) {
			SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
			restarPuntos(unSoldadoDeInfanteria);
			unSoldadoDeInfanteria.asignarPropietario(this);
			unidadesJugador.add(unSoldadoDeInfanteria);
			agregarUnidadAlTablero(unSoldadoDeInfanteria,posicionX, posicionY);
			this.cantidadDeSoldadosColocados = this.cantidadDeSoldadosColocados + 1;
		}
		
		public void restarCantidadDeSoldadosColocados() {
			this.cantidadDeSoldadosColocados = this.cantidadDeSoldadosColocados - 1;
		}
		
		public int obtenerCantidadDeSoldadosColocados(){
			return cantidadDeSoldadosColocados;
		}
		
		public void agregarSoldadoInfanteria() {
			SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
			restarPuntos(unSoldadoDeInfanteria);
			unSoldadoDeInfanteria.asignarPropietario(this);
			unidadesJugador.add(unSoldadoDeInfanteria);
		}
		
		// +++++++ Jinete +++++++
		public void agregarJinete(int posicionX, int posicionY) {
			Jinete unJinete = new Jinete();
			restarPuntos(unJinete);
			unJinete.asignarPropietario(this);
			unidadesJugador.add(unJinete);
			agregarUnidadAlTablero(unJinete,posicionX, posicionY);
			System.out.println("Metodo de jugador");
			System.out.println(posicionX);
			System.out.println(posicionY);
			this.cantidadDeJinetesColocados = this.cantidadDeJinetesColocados + 1;
			
		}
		
		public void restarCantidadDeJinetesColocados() {
			this.cantidadDeJinetesColocados = this.cantidadDeJinetesColocados - 1;
		}
		
		public int obtenerCantidadDeJinetesColocados() {
			return cantidadDeJinetesColocados;
		}
		
		public void agregarjinete() {
			Jinete unJinete = new Jinete();
			restarPuntos(unJinete);
			unJinete.asignarPropietario(this);
			unidadesJugador.add(unJinete);
		}
		
		// +++++++ Catapulta +++++++
		public void agregarCatapulta() {
			Catapulta unaCatapulta = new Catapulta();
			restarPuntos(unaCatapulta);
			unaCatapulta.asignarPropietario(this);
			unidadesJugador.add(unaCatapulta);
		}
		public void agregarCatapulta(int posicionX, int posicionY) {
			Catapulta unaCatapulta = new Catapulta();
			restarPuntos(unaCatapulta);
			unaCatapulta.asignarPropietario(this);
			unidadesJugador.add(unaCatapulta);
			agregarUnidadAlTablero(unaCatapulta,posicionX, posicionY);
			this.cantidadDeCatapultasColocadas = this.cantidadDeCatapultasColocadas + 1;
		}
		
		public void restarCantidadDeCatapultasColocadas() {
			this.cantidadDeCatapultasColocadas = this.cantidadDeCatapultasColocadas - 1;
		}
		
		public int obtenerCantidadDeCatapultasColocadas(){
			return cantidadDeCatapultasColocadas;
		}
		// +++++++ Curandero +++++++
		public void agregarCurandero() {
			Curandero unCurandero = new Curandero();
			restarPuntos(unCurandero);
			unCurandero.asignarPropietario(this);
			unidadesJugador.add(unCurandero);
		}
		
		public void agregarCurandero(int posicionX, int posicionY) {
			Curandero unCurandero = new Curandero();
			restarPuntos(unCurandero);
			unCurandero.asignarPropietario(this);
			unidadesJugador.add(unCurandero);
			agregarUnidadAlTablero(unCurandero,posicionX, posicionY);
			this.cantidadDeCuranderosColocados = this.cantidadDeCuranderosColocados + 1;
		}
		
		public void restarCantidadDeCuranderosColocados() {
			this.cantidadDeCuranderosColocados = this.cantidadDeCuranderosColocados - 1;
		}
		
		public int obtenerCantidadDeCuranderosColocados(){
			return cantidadDeCuranderosColocados;
		}
		
		 public void eliminarUnidadSinVida(Unidad unaUnidad) {
			 for (int i = 0; i < unidadesJugador.size(); i++) {
				if (unidadesJugador.get(i) == unaUnidad) {
					unidadesJugador.remove(i);
				}
			}
		}
		
		public void agregarUnidadAlTablero( Tablero tablero, int posX, int posY, int posicionLista) {
						
			tablero.agregarUnidad(unidadesJugador.get(posicionLista), posX, posY);
		}
		
		public void agregarUnidadAlTablero( Unidad unidad, int posX, int posY) {
			
			this.tablero.agregarUnidad(unidad, posX, posY);
		}
			
		private void restarPuntos(Unidad unaUnidad) {
			
			int costoUnidad = unaUnidad.obtenerCosto();
			if (puntosIniciales < costoUnidad) {
				throw new JugadorNoPuedeAgregarMasEntidades();
			}else {
				puntosIniciales = puntosIniciales - costoUnidad;
			}
			
		}
		
		public int obtenerPuntos() {
			return this.puntosIniciales;
		}
		
		public List<SoldadoDeInfanteria> soldadosAliadosEnDistanciaCercana(SoldadoDeInfanteria unSoldado) {
			List<SoldadoDeInfanteria> soldadosCercanos = new ArrayList<SoldadoDeInfanteria>();
			List<Unidad> unidadesCercanas = new ArrayList<Unidad>();
			unidadesCercanas = unidadesAliadasEnDistanciaCercana(unSoldado);
			Iterator<Unidad> iteradorUnidades = unidadesCercanas.iterator();
			while (iteradorUnidades.hasNext()) {
				Unidad unidad = iteradorUnidades.next();
				if(unidad instanceof SoldadoDeInfanteria ) {
					soldadosCercanos.add((SoldadoDeInfanteria) unidad);
				}
			}
			return soldadosCercanos;
		}
		
		
		public List<Unidad> unidadesAliadasEnDistanciaCercana(Unidad unaUnidad) {
			List<Unidad> unidadesCercanas = new ArrayList<Unidad>();
			Iterator<Unidad> iteradorUnidades = unidadesJugador.iterator();
			Unidad otraUnidad;
			while (iteradorUnidades.hasNext()) {
				otraUnidad = iteradorUnidades.next();
				if(unaUnidad != otraUnidad) {
					if(unaUnidad.estaADistanciaCercana(otraUnidad)) {
						unidadesCercanas.add(otraUnidad);
					}
				}
			}
			return unidadesCercanas;
		}
		
		public void comparUnidades() {
			// union de la interfaz grafica con 
			 /*
			 this.agregarCatapulta();			  
			 this.agregarCurandero();
			 this.agregarjinete();
			 this.agregarSoldadoInfanteria();
			 */
		}
		
		public void ubicarUnidades() {
		/*	Celda celdaOrigen ;
			int posX, posY;
			Unidad unaUnidad;
			
			for (int i = 0; i < unidadesJugador.size(); i++) {
				System.out.println("Elegir celda para ubicar unidad");			
				//elegir celda por tablero
				celdaOrigen = new Celda();
				posX = celdaOrigen.obtenerPosicionX();
				posY = celdaOrigen.obtenerPosicionY();
				unaUnidad =  unidadesJugador.get(i);
				tablero.agregarUnidad(unaUnidad, posX, posY);
			}*/ 
		}
		
		public void agregarUnidadesContiguasAliadas(Unidad unaUnidad, List<Unidad> unidadesContiguas) {//incluye sucesion de unidades contiguas
			Iterator<Unidad> iteradorUnidades = unidadesJugador.iterator();
			while (iteradorUnidades.hasNext()) {
				Unidad otraUnidad = iteradorUnidades.next();
				if(otraUnidad.calcularDistancia(unaUnidad)==1
					&& !unidadesContiguas.contains(otraUnidad)) {
					unidadesContiguas.add(otraUnidad);
					agregarUnidadesContiguasAliadas(otraUnidad, unidadesContiguas);//recursividad en la busqueda
				}
			}
		}
		
		public List<Unidad> getUnidadesJugador() {
			return this.unidadesJugador;
		}
		
		public String jugadorPerdedor() {
			return this.nombre;
		}
		
		public Celda obtenerCeldaConDireccion(int posX, int posY, int[] direccion) {
			return this.tablero.obtenerCeldaConDireccion(posX, posY, direccion);
		}
		// metodos para pruebas
		public int cantidadDeUnidades() {
			return unidadesJugador.size();
		}
		public String obtenerPropietario() {
			return this.nombre;
		}
		public Unidad obtenerUnidadEnPosicion(int posicion) {
			return this.unidadesJugador.get(posicion);
		}
		
		public void agregarUnidad(Unidad unaUnidad) {
			unidadesJugador.add(unaUnidad);
		}
		

}
