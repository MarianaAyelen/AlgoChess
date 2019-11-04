package Jugadores;


import java.util.ArrayList;
import java.util.List;


import Excepciones.JugadorNoPuedeAgregarMasEntidades;
import Unidades.Unidad;
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
		
		public void agregarSoldadoInfanteria(Tablero tablero, int posX, int posY) {
			
			SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
			restarPuntos(unSoldadoDeInfanteria);
			unSoldadoDeInfanteria.asignarPropietario(this);
			tablero.agregarUnidad(unSoldadoDeInfanteria, posX, posY);
			unidadesJugador.add(unSoldadoDeInfanteria);
		}
		
		public void agregarJinete(Tablero tablero, int posX, int posY) {
			
			Jinete unJinete = new Jinete();
			restarPuntos(unJinete);
			unJinete.asignarPropietario(this);
			tablero.agregarUnidad(unJinete,posX,posY);
			unidadesJugador.add(unJinete);
		}
		
		public void agregarCatapulta(Tablero tablero, int posX, int posY) {
			Catapulta unaCatapulta = new Catapulta();
			restarPuntos(unaCatapulta);
			unaCatapulta.asignarPropietario(this);
			tablero.agregarUnidad(unaCatapulta, posX, posY);
			unidadesJugador.add(unaCatapulta);
			
		}
		
		public void agregarCurandero(Tablero tablero, int posX, int posY) {
			Curandero unCurandero = new Curandero();
			restarPuntos(unCurandero);
			unCurandero.asignarPropietario(this);
			tablero.agregarUnidad(unCurandero, posX, posY);
			unidadesJugador.add(unCurandero);
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
		
		// metodos para pruebas
		public int cantidadDeUnidades() {
			return unidadesJugador.size();
		}
}
