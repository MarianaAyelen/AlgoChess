package Jugadores;



import java.util.ArrayList;
import java.util.List;


import Excepciones.JugadorNoPuedeAgregarMasEntidades;
import Excepciones.JugadorNoTieneMasPuntos;
import Unidades.Unidad;
import sun.awt.AWTAccessor.SystemColorAccessor;
import Unidades.*;
import Tablero.Tablero;

public class Jugador {

		private String nombre;
		private int puntosIniciales;
		private List<Unidad> unidadesJugador = new ArrayList<Unidad>();
		
		public Jugador(String nombreJugador) {
			nombre = nombreJugador;
			puntosIniciales = 20;
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
		public void realizarComportamiento(Unidad unaUnidad, Unidad otraUnidad) {
			
			unaUnidad.realizarComportamiento(otraUnidad);
		}
		
		public void eliminarUnidad(Unidad unaUnidad) {
			
		}
		
		
		// metodos para pruebas
		public int cantidadDeUnidades() {
			return unidadesJugador.size();
		}
}
