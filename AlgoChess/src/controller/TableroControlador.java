package controller;

import Jugadores.Jugador;
import Tablero.Celda;
import Tablero.Tablero;
import Unidades.Unidad;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.TableroView;

public class TableroControlador {
	private boolean colocacionDePiezasOk;
	private int cantidadDeJugadoresTotales = 2;
	private int cantidadDeJugadoresQueColocaronPiezas;
	private TableroView tableroVista; 
	private Tablero tablero;
	ControladorJugador jugador1;
	ControladorJugador jugador2;
	
	public void TableroControlador() {
		cantidadDeJugadoresQueColocaronPiezas = 0;
		colocacionDePiezasOk = false;
	}
	
	public void jugar(String nombreJugador1, String nombreJugador2) {
		tablero = new Tablero(20,20);
		jugador1 = new ControladorJugador(nombreJugador1);
		jugador2 = new ControladorJugador(nombreJugador2);
		jugador1.agregarTablero(tablero);
		jugador2.agregarTablero(tablero);
		jugador1.setColor(Color.FUCHSIA);
		jugador2.setColor(Color.BLUE);
		tablero.generarTerritorios(devolverJugadores());
		colocarPiezas(jugador1, jugador2);
	}
	
	private Jugador[] devolverJugadores() {
		Jugador[] jugadores = new Jugador[2];
		jugadores[0] = jugador1.devolverJugador();
		jugadores[1] = jugador2.devolverJugador();
		return jugadores;
	}
	
	public void colocarPiezas(ControladorJugador jugador1, ControladorJugador jugador2) {
		TableroView tableroScene = new TableroView(jugador1, jugador2 , this);
		Stage sceneTablero = tableroScene.getMainStage();
		sceneTablero.show();
		tableroVista = tableroScene;
	}
	
	public void verificarColocacionDePiezas(){
		if(cantidadDeJugadoresQueColocaronPiezas == cantidadDeJugadoresTotales-1) {
			colocacionDePiezasOk= true;
			comenzarLaPartida();
		}
		else {
			cantidadDeJugadoresQueColocaronPiezas = cantidadDeJugadoresQueColocaronPiezas +1;
		}
	}

	public void comenzarLaPartida() {
		tableroVista.eliminarBarraParaDeColocacionDePiezas();
		ControladorJugador[] jugadores = new ControladorJugador[2];

		jugadores[0] = jugador1;
		jugadores[1] = jugador2;
		tableroVista.crearJugadorEnPartidaSubEscenaJugador(jugadores);
	
		jugador1.setTurno(true);
		jugador1.setComportamientoPosible(true);
	}
	
	
	
	public boolean yaComenzoLaPartida() {
		return colocacionDePiezasOk;
	}
	
	public boolean perteneceAlJugador(int x, int y, ControladorJugador controladorJugador) {
		boolean  perteneceAlJugador= false;
		if (tablero.obtenerPropietario(x, y) == controladorJugador.devolverJugador()) {
			perteneceAlJugador = true;
		 }
		return perteneceAlJugador;
	}
	
	public String mostrarTipoDePieza(int x, int y) {
		
		String tipoDePieza = tablero.devolverElTipoDePieza(x, y);
		return tipoDePieza;
	}
	
	public int mostrarVida(int x, int y) {
		
		return  tablero.devolverVidaDePieza(x, y);
	}
	
	public boolean celdaVacia(int x, int y) {
		return tablero.celdaVacia(x, y);
	}
	
	public void realizarComportamiento(int posOrigenX, int posOrigenY, int posFinalX, int posFinalY) {
		
		tablero.realizarComportamiento(posOrigenX, posOrigenY, posFinalX, posFinalY);
	}

	public void mover(int posOrigenX, int posOrigenY, int posFinalX, int posFinalY) throws Exception {
			tablero.moverUnidad(posOrigenX, posOrigenY, posFinalX, posFinalY);
	}
	
	public Unidad devolverUnidad(int posX, int posY) {
		return tablero.obtenerUnidad(posX, posY);
	}
	public boolean celdaPerteneceAJugador(int x, int y, ControladorJugador controladorJugador) {
		boolean celdaPerteneceAJugador = false;
		if(tablero.obtenerPropietario(x, y) == controladorJugador.devolverJugador()) {
			celdaPerteneceAJugador = true;
		}
		
		return celdaPerteneceAJugador;
	}
	
	
}

