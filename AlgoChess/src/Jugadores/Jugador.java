package Jugadores;


import java.util.ArrayList;
import java.util.List;

import Excepciones.JugadorNoPuedeAgregarMasEntidades;
import Unidades.Unidad;
import Unidades.*;


public class Jugador {

		private String nombre;
		private int puntosIniciales;
		private List<Unidad> unidadesJugador = new ArrayList<Unidad>();
		
		public Jugador(String nombreJugador) {
			puntosIniciales = 20;
			nombre = nombreJugador;
		}
		
		public void agregarSoldadoDeInfanteria() {
			SoldadoDeInfanteria unSoldadoDeInfanteria = new SoldadoDeInfanteria();
			unidadesJugador.add(unSoldadoDeInfanteria);
			this.restarPuntos(1);
		}
		
		public void agregarJinete() {
			Jinete unJinete = new Jinete();
			unidadesJugador.add(unJinete);
			this.restarPuntos(3);
		}
		
		public void agregarCatapulta() {
			Catapulta unaCatapulta = new Catapulta();
			unidadesJugador.add(unaCatapulta);
			this.restarPuntos(5);
		}
		public void agregarCurandero() {
			Curandero unCurandero = new Curandero();
			unidadesJugador.add(unCurandero);
			this.restarPuntos(3);
		}
		
		private void restarPuntos(int puntosARestar) {
			
			puntosIniciales = puntosIniciales - puntosARestar;
			if (puntosIniciales == 0) {
				throw new JugadorNoPuedeAgregarMasEntidades();
			}
		}
}
