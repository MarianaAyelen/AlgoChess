package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.AlgoChessButton;
import model.AlgoChessSubScene;
import model.Celda;
import java.util.List;

import Unidades.Batallon;
import Unidades.SoldadoDeInfanteria;
import Unidades.Unidad;
import controller.ControladorJugador;
import controller.TableroControlador;
import Excepciones.*;

public class TableroView {
	
	private static final int WIDTH = 1860;
	private static final int HEIGHT = 1000;
	protected int anchoBarraLateral = 215;
	protected int largoBarraLateral = 750;
	protected int posicionXBarraLateralIzquierda = 10;
	protected int posicionYBarraLateralIzquierda = 10;
	protected int posicionXBarraLateralDerecha = 1050;
	protected int posicionYBarraLateralDerecha = 10;
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private AlgoChessSubScene barraLateralParaColocarPiezasDerecha;
	private AlgoChessSubScene barraLateralParaColocarPiezasIzquierda;
	private AlgoChessSubScene barraLateralParaJugarPiezasDerecha;
	private AlgoChessSubScene barraLateralParaJugarPiezasIzquierda;
	int posicionCeldaActualX;
	int posicionCeldaActualY;
	int posicionCeldaAnteriorX;
	int posicionCeldaAnteriorY;
	private String nombreJugador1;
	private String nombreJugador2;
	ControladorJugador jugador1;
	ControladorJugador jugador2;
	private TableroControlador tablero;
	Node[][] celdasView = new Node[NUM_ROWS][NUM_COLUMNS];
	//private Label informacionDePieza;
	private Label informacionDePiezaJugador1;
	private Label informacionDePiezaJugador2;
	private ProgressBar pb1 = new ProgressBar();
	private ProgressBar pb2 = new ProgressBar();
	private Label turnoJugador;
	
	private static final int NUM_ROWS = 20;
	private static final int NUM_COLUMNS = 20;

	private static final String PUNTOS_INSUFICIENTES = "No posee los puntos necesarios para colocar la unidad";
	private static final String TERRITORIO_ENEMIGO = "No puede colocar unidades en territorio enemigo";
	private static final String CELDA_OCUPADA = "La celda esa ocupada";
	public static final String IMG_MADERA = "/view/resources/madera.jpg";
	private Label etiquetaJinetesColocados;
	
	/*
	public TableroView() {
		initializeStage();
	}
	
	public TableroView(String nombreJugador1, String nombreJugador2) {
		jugador1 = nombreJugador1;
		jugador2 = nombreJugador2;
		initializeStage();
				
	}*/
	
	public TableroView(ControladorJugador controladorJugador1, ControladorJugador controladorJugador2, TableroControlador controladorTablero) {
		//nombreJugador1 = jugador1.obtenerNombre();
		//nombreJugador2 = jugador2.obtenerNombre();
		jugador1 = controladorJugador1;
		jugador2 = controladorJugador2;
		tablero = controladorTablero;
		initializeStage();
				
	}
	
	
	private void initializeStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, WIDTH, HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		
		
		AlgoChessSubScene tablero = crearCeldasDeTablero();
		gamePane.getChildren().add(tablero);  
		
		barraLateralParaColocarPiezasIzquierda = new AlgoChessSubScene(posicionXBarraLateralIzquierda,posicionYBarraLateralIzquierda,largoBarraLateral,anchoBarraLateral, AlgoChessSubScene.IMG_TEXTURA);
		AnchorPane rootBarraLateralParaColocarPiezasIzquierda = barraLateralParaColocarPiezasIzquierda.getPane();
		crearBarraJugadorPosicionarPiezas(jugador1, rootBarraLateralParaColocarPiezasIzquierda);
		gamePane.getChildren().add(barraLateralParaColocarPiezasIzquierda);
		
		barraLateralParaColocarPiezasDerecha = new AlgoChessSubScene(posicionXBarraLateralDerecha,posicionYBarraLateralDerecha,largoBarraLateral,anchoBarraLateral, AlgoChessSubScene.IMG_TEXTURA);
		AnchorPane rootBarraLateralParaColocarPiezasDerecha = barraLateralParaColocarPiezasDerecha.getPane();
		crearBarraJugadorPosicionarPiezas(jugador2, rootBarraLateralParaColocarPiezasDerecha);
		gamePane.getChildren().add(barraLateralParaColocarPiezasDerecha);
		
		AnchorPane.setTopAnchor(tablero, 10.0); // 
		AnchorPane.setLeftAnchor(tablero, 255.0);
		createBackground();
	}
	
	private void actualizarTurno(ControladorJugador jugador) {
	
			if(jugador == jugador1) {
				turnoJugador.setText(jugador2.obtenerNombre() + " es su turno");
			}else {
				turnoJugador.setText(jugador1.obtenerNombre() + " es su turno");
			}
			
	}
	
	// +++++++++++ Metodos de ViewBarraLateral +++++++++++

	
	private void actualizarPuntos(int puntos, Label etiquetaPuntos) {
		String strPuntos = Integer.toString(puntos);
		etiquetaPuntos.setText("Puntos restantes: " + strPuntos);
	}
	
	private void actualizarEtiquetaDeEntero(String textoAdelante, int puntos, Label etiquetaPuntos) {
		String strPuntos = Integer.toString(puntos);
		etiquetaPuntos.setText(textoAdelante + strPuntos);
	}
	
	private void crearBarraJugadorPosicionarPiezas(ControladorJugador jugador, AnchorPane root) {
		
		Label etiquetaCostoJinetes = etiqueta("Costo de jinetes: 3", 15);
		Label etiquetaCostoSoldados = etiqueta("Costo de soldados: 1", 15);
		Label etiquetaCostoCuranderos = etiqueta("Costo de curanderos: 2", 15);
		Label etiquetaCostoCatapultas = etiqueta("Costo de catapultas: 5", 15);
		
		Label etiquetaJinetesColocados = etiquetaStringMasEntero("Jinetes colocados: ", jugador.obtenerCantidadDeJinetesColocados(), 15);
		Label etiquetaSoldadosColocados = etiquetaStringMasEntero("Soldados colocados: ", jugador.obtenerCantidadDeSoldadosColocados(), 15);
		Label etiquetaCuranderosColocados = etiquetaStringMasEntero("Curanderos colocados: ", jugador.obtenerCantidadDeCuranderosColocados(), 15);
		Label etiquetaCatapultasColocadas = etiquetaStringMasEntero("Catapultas colocados: ", jugador.obtenerCantidadDeSoldadosColocados(), 15);
		Label etiquetaPuntosRestantes = etiquetaStringMasEntero("Puntos restantes: ", jugador.obtenerPuntos(), 15);
		Label etiquetaNombreJugador = etiqueta(jugador.obtenerNombre(), 35);
		
		AlgoChessButton botonJinete = botonJinete(jugador, etiquetaPuntosRestantes, etiquetaJinetesColocados);
		AlgoChessButton botonSoldado = botonSoldado(jugador, etiquetaPuntosRestantes, etiquetaSoldadosColocados);
		AlgoChessButton botonCurandero = botonCurandero(jugador, etiquetaPuntosRestantes, etiquetaCuranderosColocados);
		AlgoChessButton botonCatapulta = botonCatapulta(jugador, etiquetaPuntosRestantes, etiquetaCatapultasColocadas);
		
		//Se agregan etiquetas
		root.getChildren().add(etiquetaNombreJugador);
		root.getChildren().add(etiquetaPuntosRestantes);
		root.getChildren().add(botonJinete);
		root.getChildren().add(etiquetaCostoJinetes);
		root.getChildren().add(etiquetaJinetesColocados);
		root.getChildren().add(botonSoldado);
		root.getChildren().add(etiquetaCostoSoldados);
		root.getChildren().add(etiquetaSoldadosColocados);
		root.getChildren().add(botonCurandero);
		root.getChildren().add(etiquetaCostoCuranderos);
		root.getChildren().add(etiquetaCuranderosColocados);
		root.getChildren().add(botonCatapulta);
		root.getChildren().add(etiquetaCostoCatapultas);
		root.getChildren().add(etiquetaCatapultasColocadas);
	    
		//posiciones
		AnchorPane.setTopAnchor(etiquetaNombreJugador, 100.0);
		AnchorPane.setLeftAnchor(etiquetaNombreJugador, 10.0);
		AnchorPane.setTopAnchor(etiquetaPuntosRestantes, 200.0);
		AnchorPane.setLeftAnchor(etiquetaPuntosRestantes, 10.0);
		AnchorPane.setTopAnchor(botonJinete, 280.0);
		AnchorPane.setLeftAnchor(botonJinete, 10.0);
		AnchorPane.setTopAnchor(etiquetaCostoJinetes, 330.0);
		AnchorPane.setLeftAnchor(etiquetaCostoJinetes, 10.0);
		AnchorPane.setTopAnchor(etiquetaJinetesColocados, 348.0);
		AnchorPane.setLeftAnchor(etiquetaJinetesColocados, 10.0);
		AnchorPane.setTopAnchor(botonSoldado, 380.0);
		AnchorPane.setLeftAnchor(botonSoldado, 10.0);
		AnchorPane.setTopAnchor(etiquetaCostoSoldados, 430.0);
		AnchorPane.setLeftAnchor(etiquetaCostoSoldados, 10.0);
		AnchorPane.setTopAnchor(etiquetaSoldadosColocados, 448.0);
		AnchorPane.setLeftAnchor(etiquetaSoldadosColocados, 10.0);
		AnchorPane.setTopAnchor(botonCurandero, 480.0);
		AnchorPane.setLeftAnchor(botonCurandero, 10.0);
		AnchorPane.setTopAnchor(etiquetaCostoCuranderos, 530.0);
		AnchorPane.setLeftAnchor(etiquetaCostoCuranderos, 10.0);
		AnchorPane.setTopAnchor(etiquetaCuranderosColocados, 548.0);
		AnchorPane.setLeftAnchor(etiquetaCuranderosColocados, 10.0);
		AnchorPane.setTopAnchor(botonCatapulta, 580.0);
		AnchorPane.setLeftAnchor(botonCatapulta, 10.0);
		AnchorPane.setTopAnchor(etiquetaCostoCatapultas, 630.0);
		AnchorPane.setLeftAnchor(etiquetaCostoCatapultas, 10.0);
		AnchorPane.setTopAnchor(etiquetaCatapultasColocadas, 648.0);
		AnchorPane.setLeftAnchor(etiquetaCatapultasColocadas, 10.0);
		
	}
	
	// +++++++++++ Metodos de ViewTablero +++++++++++
	
	private AlgoChessSubScene crearCeldasDeTablero() {

		AlgoChessSubScene nuevoTableroView = new AlgoChessSubScene(0,0,740,740);
		Node[][] celdas = new Node[NUM_ROWS][NUM_COLUMNS];
		for (int i=0; i < NUM_COLUMNS ; i++) {
			for (int j=0; j < NUM_ROWS ; j++) {
				celdas[i][j] = new Celda(i,j);
				nuevoTableroView.getPane().getChildren().add(celdas[i][j]);
				celdas[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, handlerMostrarPosicionCelda);
			}
		}
		celdasView = celdas;
		return nuevoTableroView;
	}
	
	EventHandler<MouseEvent> handlerMostrarPosicionCelda = new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent event) {
	    		Object celda = event.getSource();
	    		int x = ((Celda) celda).devolverPosicion()[0];
	    		int y = ((Celda) celda).devolverPosicion()[1];
	    		posicionCeldaAnteriorX = posicionCeldaActualX;
	    		posicionCeldaAnteriorY = posicionCeldaActualY;
	    		posicionCeldaActualX = x;
	    		posicionCeldaActualY = y;
	    		
	    		if (tablero.yaComenzoLaPartida()) {
	    			int vida = tablero.mostrarVida(x+1, y+1);
	    			int vidaMax = tablero.mostrarVidaMax(x+1, y+1);
	    			String strVida = Integer.toString(vida); 
	    			String strVidaMax = Integer.toString(vidaMax);
	    			//actualizarEtiquetaDeEstring("Informacion de la pieza:\n", "Tipo de pieza: " + tablero.mostrarTipoDePieza(x+1,y+1) + "\n" + "Vida: " + strVida, informacionDePiezaJugador1);
	    			
	    			if(tablero.unidadPerteneceAlJugador(x+1, y+1, jugador1)) {
	    				actualizarEtiquetaDeEstring("Informacion de la pieza:\n", "Tipo de pieza: " + tablero.mostrarTipoDePieza(x+1,y+1) + "\n" + "Vida: " + strVida + "/" + strVidaMax, informacionDePiezaJugador1);
	    				actualizarBarraDeVida(pb1, vida, vidaMax);
	    			}else {
	    				actualizarEtiquetaDeEstring("Informacion de la pieza:\n", "Tipo de pieza: " + tablero.mostrarTipoDePieza(x+1,y+1) + "\n" + "Vida: " + strVida + "/" + strVidaMax, informacionDePiezaJugador2);
	    				actualizarBarraDeVida(pb2, vida, vidaMax);
	    			}
	    		}
	    }
	};
	
	private void actualizarInfoDeCeldas() {
		if (!tablero.yaComenzoLaPartida()) {
			return;
		}
		int x = posicionCeldaAnteriorX;
		int y = posicionCeldaAnteriorY;		
		if(tablero.celdaVacia(x+1, y+1)) {
			desocuparCeldaTablero(x,y);
		}else {
			int vida = tablero.mostrarVida(x+1, y+1);
			int vidaMax = tablero.mostrarVidaMax(x+1, y+1);
			String strVida = Integer.toString(vida); 
			String strVidaMax = Integer.toString(vidaMax);
			//actualizarEtiquetaDeEstring("Informacion de la pieza:\n", "Tipo de pieza: " + tablero.mostrarTipoDePieza(x+1,y+1) + "\n" + "Vida: " + strVida, informacionDePiezaJugador1);
			
			if(tablero.unidadPerteneceAlJugador(x+1, y+1, jugador1)) {
				actualizarEtiquetaDeEstring("Informacion de la pieza:\n", "Tipo de pieza: " + tablero.mostrarTipoDePieza(x+1,y+1) + "\n" + "Vida: " + strVida + "/" + strVidaMax, informacionDePiezaJugador1);
				actualizarBarraDeVida(pb1, vida, vidaMax);
			}else {
				actualizarEtiquetaDeEstring("Informacion de la pieza:\n", "Tipo de pieza: " + tablero.mostrarTipoDePieza(x+1,y+1) + "\n" + "Vida: " + strVida + "/" + strVidaMax, informacionDePiezaJugador2);
				actualizarBarraDeVida(pb2, vida, vidaMax);
			}
		}
		x = posicionCeldaActualX;
		y = posicionCeldaActualY;
		if(tablero.celdaVacia(x+1, y+1)) {
			desocuparCeldaTablero(x,y);	
		}else {
			int vida = tablero.mostrarVida(x+1, y+1);
			int vidaMax = tablero.mostrarVidaMax(x+1, y+1);
			String strVida = Integer.toString(vida); 
			String strVidaMax = Integer.toString(vidaMax);
			//actualizarEtiquetaDeEstring("Informacion de la pieza:\n", "Tipo de pieza: " + tablero.mostrarTipoDePieza(x+1,y+1) + "\n" + "Vida: " + strVida, informacionDePiezaJugador1);
			
			if(tablero.unidadPerteneceAlJugador(x+1, y+1, jugador1)) {
				actualizarEtiquetaDeEstring("Informacion de la pieza:\n", "Tipo de pieza: " + tablero.mostrarTipoDePieza(x+1,y+1) + "\n" + "Vida: " + strVida + "/" + strVidaMax, informacionDePiezaJugador1);
				actualizarBarraDeVida(pb1, vida, vidaMax);
			}else {
				actualizarEtiquetaDeEstring("Informacion de la pieza:\n", "Tipo de pieza: " + tablero.mostrarTipoDePieza(x+1,y+1) + "\n" + "Vida: " + strVida + "/" + strVidaMax, informacionDePiezaJugador2);
				actualizarBarraDeVida(pb2, vida, vidaMax);
			}
		}		
	}
	
	private void chequearCeldasMuertas() {
		for(int x=0; x<tablero.getCantidadFilas();x++) {
			for(int y=0; y<tablero.getCantidadColumnas();y++) {
				if(tablero.celdaVacia(x+1, y+1)) {
					desocuparCeldaTablero(x,y);
				}
			}
		}
	}
	
	
	public Stage getMainStage() {
		return gameStage;
	}
	
	private void createBackground() {
		Image backgroundImage = new Image(IMG_MADERA, 256, 256, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null );
		gamePane.setBackground(new Background(background));
	}
	
	//+++++++++++ Metodos Botones +++++++++++
	
	private AlgoChessButton botonJinete(ControladorJugador jugador, Label etiquetaPuntosRestantes, Label etiquetaJinetesColocados) {
		
		AlgoChessButton botonJinete = new AlgoChessButton("Jinete");
		botonJinete.setOnAction(event-> {
				try {
					if(tablero.celdaVacia(posicionCeldaActualX+1, posicionCeldaActualY+1) && tablero.celdaPerteneceAJugador(posicionCeldaActualX+1, posicionCeldaActualY+1, jugador)) {
						jugador.agregarJinete(posicionCeldaActualX+1, posicionCeldaActualY+1);
						agregarJineteATablero(jugador,posicionCeldaActualX,posicionCeldaActualY);
						actualizarEtiquetaDeEntero("Jinetes colocados: ",jugador.obtenerCantidadDeJinetesColocados(), etiquetaJinetesColocados );
						actualizarPuntos(jugador.obtenerPuntos(),etiquetaPuntosRestantes);
						if(jugador.obtenerPuntos()==0) {
							//crearJugadorEnPartidaSubEscenaJugador();
							tablero.verificarColocacionDePiezas();
						}
					}
					
					
				}catch(JugadorNoPuedeAgregarMasEntidades e) {
					noPuedeColocarPieza(PUNTOS_INSUFICIENTES);
				}
		});
		return botonJinete;
	}
	
	
	private AlgoChessButton botonSoldado(ControladorJugador jugador, Label etiquetaPuntosRestantes, Label etiquetaSoldadosColocados) {
		AlgoChessButton botonSoldado = new AlgoChessButton("Soldado");
		
		botonSoldado.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					if(tablero.celdaVacia(posicionCeldaActualX+1, posicionCeldaActualY+1) && tablero.celdaPerteneceAJugador(posicionCeldaActualX+1, posicionCeldaActualY+1, jugador)) {
						jugador.agregarSoldado(posicionCeldaActualX+1, posicionCeldaActualY+1);
						agregarSoldadoATablero(jugador,posicionCeldaActualX,posicionCeldaActualY);
						
						actualizarEtiquetaDeEntero("Soldados colocados: ",jugador.obtenerCantidadDeSoldadosColocados(), etiquetaSoldadosColocados );
						actualizarPuntos(jugador.obtenerPuntos(),etiquetaPuntosRestantes);
						if(jugador.obtenerPuntos()==0) {
							//crearJugadorEnPartidaSubEscenaJugador();
							tablero.verificarColocacionDePiezas();
						}
					}
					
				
				}catch(JugadorNoPuedeAgregarMasEntidades e) {
					noPuedeColocarPieza(PUNTOS_INSUFICIENTES);
				}
					}			
		});
		return botonSoldado;
	}
	
	private AlgoChessButton botonCurandero(ControladorJugador jugador, Label etiquetaPuntosRestantes, Label etiquetaCuranderosColocados) {
		AlgoChessButton botonCurandero = new AlgoChessButton("Curandero");
		
		botonCurandero.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				try {
					if(tablero.celdaVacia(posicionCeldaActualX+1, posicionCeldaActualY+1) && tablero.celdaPerteneceAJugador(posicionCeldaActualX+1, posicionCeldaActualY+1, jugador)) {
						jugador.agregarCurandero(posicionCeldaActualX+1, posicionCeldaActualY+1);
						agregarCuranderoATablero(jugador,posicionCeldaActualX,posicionCeldaActualY);
						actualizarEtiquetaDeEntero("Curandero colocados: ",jugador.obtenerCantidadDeCuranderosColocados(), etiquetaCuranderosColocados );
						actualizarPuntos(jugador.obtenerPuntos(),etiquetaPuntosRestantes);

						if(jugador.obtenerPuntos()==0) {
							//crearJugadorEnPartidaSubEscenaJugador();
							tablero.verificarColocacionDePiezas();
						}
					}
					
				}catch(JugadorNoPuedeAgregarMasEntidades e) {
					noPuedeColocarPieza(PUNTOS_INSUFICIENTES);
				}	
			}			
			});
		return botonCurandero;
	}
	
	private AlgoChessButton botonCatapulta(ControladorJugador jugador, Label etiquetaPuntosRestantes, Label etiquetaCatapultasColocados) {
		AlgoChessButton botonCatapulta = new AlgoChessButton("Catapulta");
		
		botonCatapulta.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					if(tablero.celdaVacia(posicionCeldaActualX+1, posicionCeldaActualY+1) && tablero.celdaPerteneceAJugador(posicionCeldaActualX+1, posicionCeldaActualY+1, jugador)) {
						jugador.agregarCatapulta(posicionCeldaActualX+1, posicionCeldaActualY+1);
						agregarCatapultaATablero(jugador,posicionCeldaActualX,posicionCeldaActualY);
						actualizarEtiquetaDeEntero("Catapultas colocadas: ",jugador.obtenerCantidadDeCatapultasColocadas(), etiquetaCatapultasColocados );
						actualizarPuntos(jugador.obtenerPuntos(),etiquetaPuntosRestantes);
						if(jugador.obtenerPuntos()==0) {
							//crearJugadorEnPartidaSubEscenaJugador();
							tablero.verificarColocacionDePiezas();
						}
					}
					
				}catch(JugadorNoPuedeAgregarMasEntidades e) {
					noPuedeColocarPieza(PUNTOS_INSUFICIENTES);
				}	
			}		
				
				
		});
		return botonCatapulta;
	}
	
	public void eliminarBarraParaDeColocacionDePiezas() {
		
		gamePane.getChildren().remove(barraLateralParaColocarPiezasDerecha);
		gamePane.getChildren().remove(barraLateralParaColocarPiezasIzquierda);
	}
	
	//
	/*
	public void crearJugadorEnPartidaSubEscenaJugador() {
		
		AlgoChessSubScene jugador1Partida = new AlgoChessSubScene(posicionXBarraLateralDerecha,posicionYBarraLateralDerecha,largoBarraLateral,anchoBarraLateral);
		AlgoChessSubScene jugador2Partida = new AlgoChessSubScene(posicionXBarraLateralIzquierda,posicionYBarraLateralIzquierda,largoBarraLateral,anchoBarraLateral);
		
		gamePane.getChildren().add(jugador1Partida); 
		gamePane.getChildren().add(jugador2Partida);
		
		AlgoChessButton comportamientoBoton1  = ComportamientoButton();
		AlgoChessButton movimientoBoton1 = movimientoButton();
		AnchorPane root = jugador1Partida.getPane();
		
		this.informacionDePieza = etiquetaStringMasString("TipoDePieza","", 15);
		root.getChildren().add(this.informacionDePieza);
	    root.setTopAnchor(this.informacionDePieza, 100.0);
		root.setLeftAnchor(this.informacionDePieza, 10.0);
		
		
		root.getChildren().add(comportamientoBoton1);
		root.getChildren().add(movimientoBoton1);
		
		root.setTopAnchor(comportamientoBoton1, 200.0);
		root.setLeftAnchor(comportamientoBoton1, 10.0);
		
		root.setTopAnchor(movimientoBoton1, 400.0);
		root.setLeftAnchor(movimientoBoton1, 10.0);
		
		AlgoChessButton comportamientoBoton2  = ComportamientoButton();
		AlgoChessButton movimientoBoton2 = movimientoButton();
		AnchorPane root2 = jugador2Partida.getPane();
		
		root2.getChildren().add(comportamientoBoton2);
		root2.getChildren().add(movimientoBoton2);
		root2.setTopAnchor(comportamientoBoton2, 200.0);
		root2.setLeftAnchor(comportamientoBoton2, 0.0);
		
		root2.setTopAnchor(movimientoBoton2, 400.0);
		root2.setLeftAnchor(movimientoBoton2, 10.0);
	}*/
	
	private void actualizarBarraDeVida(ProgressBar pb, int vidaActual, int vidaTotal) {
		double value = Double.valueOf(vidaActual)/Double.valueOf(vidaTotal);
		if(value>0.7) {
			pb.setStyle("-fx-accent: green;");
		}
		if(value<0.7 && value>0.4) {
			pb.setStyle("-fx-accent: yellow;");
		}
		if(value<0.4) {
			pb.setStyle("-fx-accent: red;");
		}
			 
		pb.setProgress(value);
	}

	public void crearJugadorEnPartidaSubEscenaJugador(ControladorJugador jugador[]) {
		
		turnoJugador = etiquetaStringMasString(jugador1.obtenerNombre()," es su turno", 35);
		
		barraLateralParaJugarPiezasIzquierda = new AlgoChessSubScene(posicionXBarraLateralIzquierda,posicionYBarraLateralIzquierda,largoBarraLateral,anchoBarraLateral,AlgoChessSubScene.IMG_TEXTURA);
		AnchorPane rootBarraLateralParaJugarPiezasIzquierda = barraLateralParaJugarPiezasIzquierda.getPane();
		informacionDePiezaJugador1 = new Label("Jugador 1"); 
		crearBarraLateralEnPartida(jugador[0], rootBarraLateralParaJugarPiezasIzquierda, informacionDePiezaJugador1, pb1);
		gamePane.getChildren().add(barraLateralParaJugarPiezasIzquierda);
		gamePane.getChildren().add(turnoJugador);
		turnoJugador.setTextFill(Color.HONEYDEW);
		AnchorPane.setTopAnchor(turnoJugador, 800.0);
		AnchorPane.setLeftAnchor(turnoJugador, 550.0);
		
		barraLateralParaJugarPiezasDerecha = new AlgoChessSubScene(posicionXBarraLateralDerecha,posicionYBarraLateralDerecha,largoBarraLateral,anchoBarraLateral, AlgoChessSubScene.IMG_TEXTURA);
		AnchorPane rootBarraLateralParaJugarPiezasDerecha = barraLateralParaJugarPiezasDerecha.getPane();
		informacionDePiezaJugador2 = new Label("Jugador 2");
		crearBarraLateralEnPartida(jugador[1], rootBarraLateralParaJugarPiezasDerecha, informacionDePiezaJugador2, pb2);
		gamePane.getChildren().add(barraLateralParaJugarPiezasDerecha);
		
	}
	
public void crearBarraLateralEnPartida(ControladorJugador jugador,  AnchorPane root, Label informacionDePieza, ProgressBar pb) {
		
		AlgoChessButton comportamientoBoton1  = ComportamientoButton(jugador);
		AlgoChessButton movimientoBoton1 = movimientoButton(jugador);
		AlgoChessButton botonPausar = pasarButton(jugador); 
		
		Label etiquetaNombreJugador = etiqueta(jugador.obtenerNombre(), 35);
		
		root.getChildren().add(etiquetaNombreJugador);
		root.getChildren().add(informacionDePieza);
		root.getChildren().add(pb);
		root.getChildren().add(comportamientoBoton1);
		root.getChildren().add(movimientoBoton1);
		root.getChildren().add(botonPausar);
		
		
		AnchorPane.setTopAnchor(etiquetaNombreJugador, 10.0);
		AnchorPane.setLeftAnchor(etiquetaNombreJugador, 10.0);
		AnchorPane.setTopAnchor(informacionDePieza, 70.0);
		AnchorPane.setLeftAnchor(informacionDePieza, 20.0);
		AnchorPane.setTopAnchor(pb, 140.0);
		AnchorPane.setLeftAnchor(pb, 20.0);
		AnchorPane.setTopAnchor(comportamientoBoton1, 200.0);
		AnchorPane.setLeftAnchor(comportamientoBoton1, 10.0);
		AnchorPane.setTopAnchor(movimientoBoton1, 400.0);
		AnchorPane.setLeftAnchor(movimientoBoton1, 10.0);
		AnchorPane.setTopAnchor(botonPausar, 600.0);
		AnchorPane.setLeftAnchor(botonPausar, 10.0);
		
	}
	
	private AlgoChessButton pasarButton(ControladorJugador jugador) {
		AlgoChessButton botonPausar = new AlgoChessButton("Pasar turno");

		botonPausar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (jugador.getTurno()) {
					if(jugador.getcomportamientoPosible()) {
						tablero.aumentarTurnosSinActividad();
						if(tablero.getTurnosSinActividad()>=6) {
							finDelJuegoPorEmpate();
						}
					}else {
						tablero.resetearTurnosSinActividad();
					}
					cambiarTurno(jugador);
				}	
			}	
				
		});
	
		return botonPausar;
	}
	
	
	private AlgoChessButton ComportamientoButton(ControladorJugador jugador) {
		AlgoChessButton botonComportamiento = new AlgoChessButton("Comportamiento");

		botonComportamiento.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (jugador.getTurno() && jugador.getcomportamientoPosible()
				&& tablero.unidadPerteneceAlJugador(posicionCeldaAnteriorX+1,posicionCeldaAnteriorY+1,jugador)
				&& !tablero.celdaVacia(posicionCeldaActualX +1, posicionCeldaActualY +1)) {
					try {
						tablero.realizarComportamiento(posicionCeldaAnteriorX +1 , posicionCeldaAnteriorY+1, posicionCeldaActualX +1 , posicionCeldaActualY +1 );
						jugador.setComportamientoPosible(false);
						mostrarNotificacion("Comportamiento realizado");
						actualizarInfoDeCeldas();
						chequearCeldasMuertas();// necesario para el caso del ataque de catapulta (puede matar varias unidades)
						chequearGanador();
					}catch(Exception e) {
						mostrarNotificacion("Ataque invalido: "+e.getMessage());//TODO
					}
				}
			}	
		});
	
		return botonComportamiento;
	}
	private void chequearGanador() {
		if(jugador1.obtenerCantidadDeUnidades()==0) {
			finDelJuego(jugador2);
		}
		if(jugador2.obtenerCantidadDeUnidades()==0) {
			finDelJuego(jugador1);
		}
	}
		
	private void finDelJuego(ControladorJugador jugador) {
		mostrarNotificacion("El ganador es "+ jugador.obtenerNombre());
		gameStage.close();
	}
	
	private void finDelJuegoPorEmpate() {
		mostrarNotificacion("Empate");
		gameStage.close();	
	}

	
	private AlgoChessButton movimientoButton(ControladorJugador jugador) {
		
		AlgoChessButton movimientoButton = new AlgoChessButton("Mover");		
		movimientoButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(jugador.getTurno() && tablero.unidadPerteneceAlJugador(posicionCeldaAnteriorX+1,posicionCeldaAnteriorY+1,jugador)) {
					try {
						tablero.mover(posicionCeldaAnteriorX+1, posicionCeldaAnteriorY+1, posicionCeldaActualX+1, posicionCeldaActualY+1);
						try {
							if ( tablero.devolverBatallon() == null) {
								moverEnTablero(jugador, posicionCeldaAnteriorX, posicionCeldaAnteriorY, posicionCeldaActualX, posicionCeldaActualY);					
							}else {
								moverBatallonEnTablero(jugador, tablero.devolverBatallon());
								tablero.disolverBatallon();
							}
							cambiarTurno(jugador);
						}catch(Exception e) {
							noPuedeMoverPieza("Error de movimiento en tablero grafico");						
						}
					}catch(Exception e) {
						noPuedeMoverPieza("Movimiento invalido: "+e.getMessage());
					}
				}
			}	
		});
		
		return movimientoButton;
	}
	
	private void cambiarTurno(ControladorJugador unJugador) {
	
		if(unJugador == jugador1) {
			jugador1.setTurno(false);
			jugador2.setTurno(true);
			jugador2.setComportamientoPosible(true);
		}else {
			jugador1.setTurno(true);
			jugador1.setComportamientoPosible(true);
			jugador2.setTurno(false);
			
		}
		actualizarTurno(unJugador);
	}
	
	private void noPuedeMoverPieza(String texto) {
		Alert dialogoAlerta = new Alert(AlertType.INFORMATION);
		dialogoAlerta.setTitle("Movimiento invalido");
		dialogoAlerta.setHeaderText(null);
		dialogoAlerta.setContentText(texto);
		dialogoAlerta.initStyle(StageStyle.UTILITY);
		dialogoAlerta.showAndWait();
	}
	
	private void mostrarNotificacion(String texto) {
		Alert dialogoAlerta = new Alert(AlertType.INFORMATION);
		dialogoAlerta.setTitle("Notificacion");
		dialogoAlerta.setHeaderText(null);
		dialogoAlerta.setContentText(texto);
		dialogoAlerta.initStyle(StageStyle.UTILITY);
		dialogoAlerta.showAndWait();		
	}
	
	private void moverEnTablero(ControladorJugador jugador, int posOrigenX,int posOrigenY,int posFinalX,int posFinalY) {
		String tipoUnidad; Unidad unaUnidad; boolean celdaVacia;
		
		unaUnidad = tablero.devolverUnidad(posFinalX+1, posFinalY+1);//la unidad ya se movio si se llego a la instancia grafica
		tipoUnidad = tablero.mostrarTipoDePieza(posFinalX+1, posFinalY+1);
			
		if(tipoUnidad=="Catapulta") {
			agregarCatapultaATablero(jugador, posicionCeldaActualX, posicionCeldaActualY);
		}
		
		if(tipoUnidad=="Curandero") {
			agregarCuranderoATablero(jugador,posicionCeldaActualX, posicionCeldaActualY);
		}
		
		if(tipoUnidad=="Jinete") {
			agregarJineteATablero(jugador,posicionCeldaActualX, posicionCeldaActualY);
		}
		if(tipoUnidad=="Soldado De Infanteria") {
			agregarSoldadoATablero(jugador,posicionCeldaActualX, posicionCeldaActualY);
		}
		try {
			desocuparCeldaTablero(posOrigenX, posOrigenY);			
		}catch(Exception e){
			//siempre lanza excepcion, parece no afectar el juego
		}
	}
	
	private void moverBatallonEnTablero(ControladorJugador jugador, Batallon batallon) {
		List<SoldadoDeInfanteria> integrantesBatallon = batallon.obtenerSoldados();
		for(SoldadoDeInfanteria soldado: integrantesBatallon) {
			try {
				desocuparCeldaTablero(soldado.obtenerUltimaPosicionX()-1, soldado.obtenerUltimaPosicionY()-1);			
			}catch(Exception e){
				//siempre lanza excepcion, parece no afectar el juego
			}
		}
		
		for(SoldadoDeInfanteria soldado: integrantesBatallon) {
			agregarSoldadoATablero(jugador, soldado.obtenerPosicionX()-1, soldado.obtenerPosicionY()-1);
		}
	}
	
	private void desocuparCeldaTablero(int posX, int posY) {
		
		//Node[][] celdas = new Node[NUM_ROWS][NUM_COLUMNS];
		//celdas[posX][posY] = new Celda(posicionCeldaAnteriorX,posicionCeldaAnteriorY, Color.CORAL, "");
		Celda celda = (Celda) celdasView[posX][posY];
		celda.eliminarImagen();
//		celda.despintarCelda();
//		celda.eliminarEtiqueta();
		//gamePane.getChildren().add(celda);
		//gamePane.getChildren().add(celdas[posX][posY]);
		//celdas[posX][posX].addEventHandler(MouseEvent.MOUSE_CLICKED, handlerMostrarPosicionCelda);
	}
	private void noPuedeColocarPieza(String texto) {
		Alert dialogoAlerta = new Alert(AlertType.INFORMATION);
		dialogoAlerta.setTitle("Pieza invalida");
		dialogoAlerta.setHeaderText(null);
		dialogoAlerta.setContentText(texto);
		dialogoAlerta.initStyle(StageStyle.UTILITY);
		dialogoAlerta.showAndWait();
	}
	
	private void agregarJineteATablero(ControladorJugador jugador, int posicionX, int posicionY) {
		Celda celda = (Celda) celdasView[posicionX][posicionY];
		celda.agregarImagenJinete(jugador);
		//celda.agregarEtiqueta("J");
		//celda.pintarCelda(jugador.getColor());
		
		//gamePane.getChildren().add(celda);
		//Node[][] celdas = new Node[NUM_ROWS][NUM_COLUMNS];
		//celdas[posicionX][posicionY] = new Celda(posicionCeldaActualX,posicionCeldaActualY, jugador.getColor(), "J");
		//gamePane.getChildren().add(celdas[posicionCeldaActualX][posicionCeldaActualY]);
		//celdas[posicionCeldaActualX][posicionCeldaActualY].addEventHandler(MouseEvent.MOUSE_CLICKED, handlerMostrarPosicionCelda);
	}
	
	private void agregarSoldadoATablero(ControladorJugador jugador, int posicionX, int posicionY) {
		Celda celda = (Celda) celdasView[posicionX][posicionY];
		//celda.agregarEtiqueta("S");
		celda.agregarImagenSoldado(jugador);
		//celda.pintarCelda(jugador.getColor());
		//Node[][] celdas = new Node[NUM_ROWS][NUM_COLUMNS];
		//celdas[posicionX][posicionY] = new Celda(posicionX,posicionY, jugador.getColor(), "S");
		//gamePane.getChildren().add(celdas[posicionX][posicionY]);
		//celdas[posicionCeldaActualX][posicionCeldaActualY].addEventHandler(MouseEvent.MOUSE_CLICKED, handlerMostrarPosicionCelda);
	}
	
	private void agregarCatapultaATablero(ControladorJugador jugador, int posicionX, int posicionY) {
		Celda celda = (Celda) celdasView[posicionX][posicionY];
		//celda.agregarEtiqueta("Ca");
		//celda.pintarCelda(jugador.getColor());
		celda.agregarImagenCatapulta(jugador);
		//Node[][] celdas = new Node[NUM_ROWS][NUM_COLUMNS];
		//celdas[posicionX][posicionY] = new Celda(posicionX,posicionY, jugador.getColor(), "Ca");
		//gamePane.getChildren().add(celdas[posicionX][posicionY]);
		//celdas[posicionCeldaActualX][posicionCeldaActualY].addEventHandler(MouseEvent.MOUSE_CLICKED, handlerMostrarPosicionCelda);
	}
	
	private void agregarCuranderoATablero(ControladorJugador jugador, int posicionX, int posicionY) {
		Celda celda = (Celda) celdasView[posicionX][posicionY];
		celda.agregarImagenCurandero(jugador);
		//celda.agregarEtiqueta("Cu");
		//celda.pintarCelda(jugador.getColor());
		//Node[][] celdas = new Node[NUM_ROWS][NUM_COLUMNS];
		//celdas[posicionX][posicionY] = new Celda(posicionX,posicionY,jugador.getColor(), "Cu");
		//gamePane.getChildren().add(celdas[posicionX][posicionY]);
		//celdas[posicionCeldaActualX][posicionCeldaActualY].addEventHandler(MouseEvent.MOUSE_CLICKED, handlerMostrarPosicionCelda);
	}
	
	
	private Label etiquetaStringMasEntero(String etiqueta, int cantidad, double tamanioLetra) {
		String strCantidad = Integer.toString(cantidad);
		Label label = new Label(etiqueta + strCantidad);
		label.setFont(new Font("Serif", tamanioLetra));
		return label;
	}
	
	private Label etiquetaStringMasString(String etiqueta, String str, double tamanioLetra) {
		
		Label label = new Label(etiqueta + str);
		label.setFont(new Font("Serif", tamanioLetra));
		return label;
	}
	private Label etiqueta(String etiqueta, double tamanioLetra) {
		
		Label label = new Label(etiqueta);
		label.setFont(new Font("Serif", tamanioLetra));
		return label;
	}
	
	private void actualizarEtiquetaDeEstring(String textoAdelante, String str, Label etiqueta) {
		
		etiqueta.setText(textoAdelante + str);
	}
	
}