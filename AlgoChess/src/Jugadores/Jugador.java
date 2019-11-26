package Jugadores;



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
		
		public Jugador(String nombreJugador) {
			nombre = nombreJugador;
			puntosIniciales = 20;
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
		public void agregarTablero(Tablero tableroDelJuego) {
			tablero = tableroDelJuego;
		}
		
		public boolean hayUnidadesEnemigasCercanas(Unidad unaUnidad) {
			return tablero.hayUnidadesEnemigasEnDistanciaCercana(unaUnidad);
		}
		
		public void agregarSoldadoInfanteria() {
			SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
			restarPuntos(unSoldadoDeInfanteria);
			unSoldadoDeInfanteria.asignarPropietario(this);
			unidadesJugador.add(unSoldadoDeInfanteria);
		}
		
		public void agregarjinete() {
			Jinete unJinete = new Jinete();
			restarPuntos(unJinete);
			unJinete.asignarPropietario(this);
			unidadesJugador.add(unJinete);
		}
		
		public void agregarCatapulta() {
			Catapulta unaCatapulta = new Catapulta();
			restarPuntos(unaCatapulta);
			unaCatapulta.asignarPropietario(this);
			unidadesJugador.add(unaCatapulta);
		}
		
		public void agregarCurandero() {
			Curandero unCurandero = new Curandero();
			restarPuntos(unCurandero);
			unCurandero.asignarPropietario(this);
			unidadesJugador.add(unCurandero);
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
			
		private void restarPuntos(Unidad unaUnidad) {
			
			int costoUnidad = unaUnidad.obtenerCosto();
			if (puntosIniciales < costoUnidad) {
				throw new JugadorNoPuedeAgregarMasEntidades();
			}else {
				puntosIniciales = puntosIniciales - costoUnidad;
			}
			
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
			
			
		}
		
		public void ubicarUnidades() {
			
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
