package controller;

import Jugadores.Jugador;
import Tablero.Celda;
import Tablero.Tablero;
import Unidades.Batallon;
import Unidades.Unidad;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import view.EntidadesView;
import view.TableroView;

public class TableroControlador {
	private boolean colocacionDePiezasOk;
	private int cantidadDeJugadoresTotales = 2;
	private int cantidadDeJugadoresQueColocaronPiezas;
	private TableroView tableroVista; 
	private Tablero tablero;
	ControladorJugador jugador1;
	ControladorJugador jugador2;
	public static String IMG_SOLDADO_NEGRO = "/view/resources/entityChooser/soldado_negro.png";
	public static String IMG_SOLDADO_BLANCO = "/view/resources/entityChooser/soldado_blanco.png";
	public static String IMG_CATAPULTA_NEGRO = "/view/resources/entityChooser/catapulta_negra.png";
	public static String IMG_CATAPULTA_BLANCO = "/view/resources/entityChooser/catapulta_blanca.png";
	public static String IMG_JINETE_NEGRO = "/view/resources/entityChooser/jinete_negro.png";
	public static String IMG_JINETE_BLANCO = "/view/resources/entityChooser/jinete_blanco.png";
	public static String IMG_CURADOR_NEGRO = "/view/resources/entityChooser/curador_negro.png";
	public static String IMG_CURADOR_BLANCO = "/view/resources/entityChooser/curador_blanco.png";
	private Batallon batallonFormado;
	private int contadorTurnosSinActividad;
	
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
		jugador1.setColor(Color.PERU.darker().darker());
		jugador2.setColor(Color.OLIVE.darker());
		jugador1.seleccionarImagenSoldado(IMG_SOLDADO_BLANCO);
		jugador2.seleccionarImagenSoldado(IMG_SOLDADO_NEGRO);
		jugador1.seleccionarImagenCatapulta(IMG_CATAPULTA_BLANCO);
		jugador2.seleccionarImagenCatapulta(IMG_CATAPULTA_NEGRO);
		jugador1.seleccionarImagenCurador(IMG_CURADOR_BLANCO);
		jugador2.seleccionarImagenCurador(IMG_CURADOR_NEGRO);
		jugador1.seleccionarImagenJinete(IMG_JINETE_BLANCO);
		jugador2.seleccionarImagenJinete(IMG_JINETE_NEGRO);
		
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

	public boolean unidadPerteneceAlJugador(int x, int y, ControladorJugador controladorJugador) {
		boolean  perteneceAlJugador= false;
		if(tablero.obtenerUnidad(x,y)==null) {
			return false;
		}
		if (tablero.obtenerUnidad(x, y).obtenerPropietario() == controladorJugador.devolverJugador()) {
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
	
	public int mostrarVidaMax(int x, int y) {
		return  tablero.devolverVidaMaxDePieza(x, y);		
	}
	
	public boolean celdaVacia(int x, int y) {
		return tablero.celdaVacia(x, y);
	}
	
	public void realizarComportamiento(int posOrigenX, int posOrigenY, int posFinalX, int posFinalY) {
		tablero.realizarComportamiento(posOrigenX, posOrigenY, posFinalX, posFinalY);
	}

	public void mover(int posOrigenX, int posOrigenY, int posFinalX, int posFinalY) throws Exception {
			batallonFormado = tablero.moverUnidad(posOrigenX, posOrigenY, posFinalX, posFinalY);
	}
	
	public Batallon devolverBatallon() {
		return batallonFormado;
	}
	
	public void disolverBatallon() {
		batallonFormado = null;
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

	public int getCantidadFilas() {
		return tablero.getCantidadFilas();
	}
	
	public int getCantidadColumnas() {
		return tablero.getCantidadColumnas();
	}
	
	public void resetearTurnosSinActividad() {
		contadorTurnosSinActividad=0;
	}

	public void aumentarTurnosSinActividad() {
		contadorTurnosSinActividad++;
	}
	
	public int getTurnosSinActividad() {
		return contadorTurnosSinActividad;
	}

}

