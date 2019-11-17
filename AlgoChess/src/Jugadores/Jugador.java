package Jugadores;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import Excepciones.JugadorNoPuedeAgregarMasEntidades;
import Excepciones.JugadorNoTieneMasPuntos;
import Excepciones.jugadorSeQuedaSinUnidades;
import Unidades.Unidad;
import Unidades.SoldadoDeInfanteria;
import Unidades.*;
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
			if(unidadesJugador.size() == 0) {
				throw new jugadorSeQuedaSinUnidades();
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
		
		
		
		
		public List<Unidad> unidadesAliadasEnDistanciaCercana(Unidad unaUnidad) {
			List<Unidad> unidadesCercanas = new ArrayList<Unidad>();
			Iterator<Unidad> iteradorUnidades = unidadesJugador.iterator();
			while (iteradorUnidades.hasNext()) {
				Unidad otraUnidad = iteradorUnidades.next();
				if(otraUnidad.estaADistanciaCercana(unaUnidad)) {
					unidadesCercanas.add(otraUnidad);
				}
			}
			return unidadesCercanas;
		}
		
		public String jugadorPerdedor() {
			return this.nombre;
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
}
