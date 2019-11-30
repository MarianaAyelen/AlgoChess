package controller;

import Jugadores.Jugador;
import Tablero.Tablero;

public class ControladorJugador {
	private Jugador jugador;
	
	public ControladorJugador(String nombre) {
		jugador = new Jugador(nombre);
	}
	
	public String obtenerNombre() {
		return jugador.obtenerPropietario();
	}
	
	public int obtenerPuntos() {
		return jugador.obtenerPuntos();
	}
	
	public void agregarTablero(Tablero unTablero) {
		jugador.agregarTablero(unTablero);
	}
		
	public int obtenerCantidadDeJinetesColocados() {
		return jugador.obtenerCantidadDeJinetesColocados();
	}
	
	public int obtenerCantidadDeSoldadosColocados() {
		return jugador.obtenerCantidadDeSoldadosColocados();
	}
	
	public int obtenerCantidadDeCuranderosColocados() {
		return jugador.obtenerCantidadDeCuranderosColocados();
	}
	
	public int obtenerCantidadDeCatapultasColocadas() {
		return jugador.obtenerCantidadDeCatapultasColocadas();
	}
	
	public void agregarJinete(int x, int y) {
		jugador.agregarJinete(x,y);
	}
	
	public void agregarSoldado(int x, int y) {
		jugador.agregarSoldadoInfanteria(x,y);
	}
	
	public void agregarCatapulta(int x, int y) {
		jugador.agregarCatapulta(x,y);
	}
	
	public void agregarCurandero(int x, int y) {
		jugador.agregarCurandero(x,y);
	}
	
	public Jugador devolverJugador() {
		return jugador;
	}
}
