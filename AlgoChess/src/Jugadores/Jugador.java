package Jugadores;


import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xpath.internal.operations.Bool;

import Excepciones.JugadorNoPuedeAgregarMasEntidades;
import Unidades.Unidad;
import sun.font.TrueTypeFont;
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
			this.sePuedeColocarUnidad(1);
			unidadesJugador.add(unSoldadoDeInfanteria);
			
		}
		
		public void agregarJinete() {
			Jinete unJinete = new Jinete();
			this.sePuedeColocarUnidad(3);
			unidadesJugador.add(unJinete);
		}
		
		public void agregarCatapulta() {
			Catapulta unaCatapulta = new Catapulta();
			this.sePuedeColocarUnidad(5);
			unidadesJugador.add(unaCatapulta);
		}
		public void agregarCurandero() {
			Curandero unCurandero = new Curandero();
			this.sePuedeColocarUnidad(2);
			unidadesJugador.add(unCurandero);
		}
		
		public Unidad devolverUnidad(int posicion) {
			Unidad unaUnidad;
			unaUnidad = this.unidadesJugador.get(posicion);
			return unaUnidad;
		}
		
		private void sePuedeColocarUnidad(int puntosARestar) {
	
			if (puntosIniciales < puntosARestar) {
				throw new JugadorNoPuedeAgregarMasEntidades();
			}
		}
}
