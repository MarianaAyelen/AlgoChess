package Jugadores;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import Excepciones.JugadorNoPuedeAgregarMasEntidades;
import Excepciones.JugadorNoTieneMasPuntos;
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
		
		public void agregarUnidadAlTablero( Tablero tablero, int posX, int posY) {
						
			tablero.agregarUnidad(unidadesJugador.get(1), posX, posY);
		}
			
		private void restarPuntos(Unidad unaUnidad) {
			
			int costoUnidad = unaUnidad.obtenerCosto();
			if (puntosIniciales < costoUnidad) {
				throw new JugadorNoPuedeAgregarMasEntidades();
			}else {
				puntosIniciales = puntosIniciales - costoUnidad;
			}
			
		}
		
		public void eliminarUnidad(Unidad unaUnidad) {
			
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
		
		
		
		// metodos para pruebas
		public int cantidadDeUnidades() {
			return unidadesJugador.size();
		}
}
