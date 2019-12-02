package controller;



import Jugadores.Jugador;
import Tablero.Tablero;
import javafx.scene.paint.Color;

public class ControladorJugador {
	private Jugador jugador;
	private boolean turnoJugador = false;
	private boolean comportamientoPosible = false;
	private Color color ;
	
	
	public ControladorJugador(String nombre) {
		jugador = new Jugador(nombre);
	}
	public void setColor(Color colorJugador) {
		color = colorJugador;
	}
	
	public Color getColor() {
		return color;
	}
	public void setComportamientoPosible(boolean movimientoPosible) {
		comportamientoPosible = movimientoPosible;
	}
	public boolean getcomportamientoPosible() {
		return comportamientoPosible;
	}
	
	public void setTurno(boolean turno) {
		turnoJugador =  turno;
	}
	
	public boolean getTurno() {
		return turnoJugador;
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
	
	public int obtenerCantidadDeUnidades() {
		return jugador.cantidadDeUnidades();
	}
	public Jugador devolverJugador() {
		return jugador;
	}
}
