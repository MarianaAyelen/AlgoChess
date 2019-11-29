package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.AlgoChessButton;
import model.AlgoChessSubScene;
import model.Celda;
import model.EntityPicker;

import java.awt.BorderLayout;
import java.util.List;

import Jugadores.Jugador;
import Tablero.Tablero;

public class TableroView {
	
	private static final int WIDTH = 1860;
	private static final int HEIGHT = 1000;
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	int posicionCeldaActualX;
	int posicionCeldaActualY;
	
	
	private static final int NUM_ROWS = 20;
	private static final int NUM_COLUMNS = 20;

	public TableroView() {
		initializeStage();
	}

	private void initializeStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, WIDTH, HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		
		
		//crearCeldasDeTablero();
		
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador jugadorAliado = new Jugador("Pepe");
		Jugador jugadorEnemigo = new Jugador("Roberto");
		jugadores[0] = jugadorAliado;
		jugadores[1] = jugadorEnemigo;
		jugadorAliado.agregarTablero(unTablero);
		jugadorEnemigo.agregarTablero(unTablero);
		//crearBarraJugadorPosicionarPiezas(jugadorAliado);
		AlgoChessSubScene tablero = crearCeldasDeTablero();
		gamePane.getChildren().add(tablero);  
		gamePane.getChildren().add(crearBarraJugadorPosicionarPiezas(jugadorAliado,1000,10,750,250));
		gamePane.getChildren().add(crearBarraJugadorPosicionarPiezas(jugadorEnemigo,000,10,750,250));
		gamePane.setTopAnchor(tablero, 10.0);
		gamePane.setLeftAnchor(tablero, 270.0);
		createBackground();
	}
	
	

	// +++++++++++ Metodos de ViewBarraLateral +++++++++++
	private AlgoChessSubScene crearBarraJugadorPosicionarPiezas(Jugador jugador,int posX, int posY,int ancho,int alto) {
		AlgoChessSubScene barraLateralParaColocarPiezas = new AlgoChessSubScene(posX,posY,ancho,alto);
		AnchorPane root = barraLateralParaColocarPiezas.getPane();
		
		AlgoChessButton botonJinete = botonJinete(jugador);
		AlgoChessButton botonSoldado = botonSoldado(jugador);
		AlgoChessButton botonCurandero = botonCurandero(jugador);
		AlgoChessButton botonCatapulta = botonCatapulta(jugador);
		
		Label etiquetaJinetesColocados = etiquetaStringMasEntero("Jinetes colocados: ", jugador.obtenerCantidadDeJinetesColocados(), 15);
		Label etiquetaSoldadosColocados = etiquetaStringMasEntero("Soldados colocados: ", jugador.obtenerCantidadDeSoldadosColocados(), 15);
		Label etiquetaCuranderosColocados = etiquetaStringMasEntero("Curanderos colocados: ", jugador.obtenerCantidadDeCuranderosColocados(), 15);
		Label etiquetaCatapultasColocadas = etiquetaStringMasEntero("Catapultas colocados: ", jugador.obtenerCantidadDeSoldadosColocados(), 15);
		Label etiquetaPuntosRestantes = etiquetaStringMasEntero("Puntos restantes: ", jugador.obtenerPuntos(), 15);
		Label etiquetaNombreJugador = etiqueta(jugador.obtenerPropietario(), 35);
		
		//Se agregan etiquetas
		root.getChildren().add(etiquetaNombreJugador);
		root.getChildren().add(etiquetaPuntosRestantes);
		root.getChildren().add(botonJinete);
		root.getChildren().add(etiquetaJinetesColocados);
		root.getChildren().add(botonSoldado);
		root.getChildren().add(etiquetaSoldadosColocados);
		root.getChildren().add(botonCurandero);
		root.getChildren().add(etiquetaCuranderosColocados);
		root.getChildren().add(botonCatapulta);
		root.getChildren().add(etiquetaCatapultasColocadas);
	    
		//posiciones
		root.setTopAnchor(etiquetaNombreJugador, 100.0);
		root.setLeftAnchor(etiquetaNombreJugador, 10.0);
		root.setTopAnchor(etiquetaPuntosRestantes, 200.0);
		root.setLeftAnchor(etiquetaPuntosRestantes, 10.0);
		root.setTopAnchor(botonJinete, 300.0);
		root.setLeftAnchor(botonJinete, 10.0);
		root.setTopAnchor(etiquetaJinetesColocados, 350.0);
		root.setLeftAnchor(etiquetaJinetesColocados, 10.0);
		root.setTopAnchor(botonSoldado, 400.0);
		root.setLeftAnchor(botonSoldado, 10.0);
		root.setTopAnchor(etiquetaSoldadosColocados, 450.0);
		root.setLeftAnchor(etiquetaSoldadosColocados, 10.0);
		root.setTopAnchor(botonCurandero, 500.0);
		root.setLeftAnchor(botonCurandero, 10.0);
		root.setTopAnchor(etiquetaCuranderosColocados, 550.0);
		root.setLeftAnchor(etiquetaCuranderosColocados, 10.0);
		root.setTopAnchor(botonCatapulta, 600.0);
		root.setLeftAnchor(botonCatapulta, 10.0);
		root.setTopAnchor(etiquetaCatapultasColocadas, 650.0);
		root.setLeftAnchor(etiquetaCatapultasColocadas, 10.0);
		
	    return barraLateralParaColocarPiezas;
	}
	
	// +++++++++++ Metodos de ViewTablero +++++++++++
	
	private AlgoChessSubScene crearCeldasDeTablero() {

		AlgoChessSubScene tablero = new AlgoChessSubScene(0,0,700,700);
		Node[][] celdas = new Node[NUM_ROWS][NUM_COLUMNS];
		for (int i=0; i < NUM_COLUMNS ; i++) {
			for (int j=0; j < NUM_ROWS ; j++) {
				celdas[i][j] = new Celda(i,j);
				tablero.getPane().getChildren().add(celdas[i][j]);
				celdas[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, handlerMostrarPosicionCelda);
			}
		}
		return tablero;
	}
	
	EventHandler<MouseEvent> handlerMostrarPosicionCelda = new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent event) {
	    		Object celda = event.getSource();
	    		int x = ((Celda) celda).devolverPosicion()[0];
	    		int y = ((Celda) celda).devolverPosicion()[1];
	    		posicionCeldaActualX = x;
	    		posicionCeldaActualY = y;
	    		
	    		//mostrarFilaYColumnaDeCeldaEnLaBarraLateral(posicionCeldaActualX, posicionCeldaActualY);
	    }
	};
	
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	// +++++++++++ Metodos de ViewCelda +++++++++++
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	// +++++++++++ Metodos barra lateral de jugador +++++++++++
	/*
	private AnchorPane mostrarFilaYColumnaDeCeldaEnLaBarraLateral(int x, int y) {
		AnchorPane filaYCol = new AnchorPane();
		String strX = Integer.toString(x+1);
		String strY = Integer.toString(y+1);
		
		TextField campoDeTextoX = new TextField(strX);
		TextField campoDeTextoY = new TextField(strY);
		campoDeTextoX.setMaxWidth(35);
		campoDeTextoY.setMaxWidth(35);
		
		filaYCol.getChildren().add(campoDeTextoX);
		filaYCol.getChildren().add(campoDeTextoY);
		
		filaYCol.setTopAnchor(campoDeTextoX, 10.0);
		filaYCol.setLeftAnchor(campoDeTextoX, 10.0);
		filaYCol.setTopAnchor(campoDeTextoY, 15.0);
		filaYCol.setLeftAnchor(campoDeTextoY, 10.0);
		return filaYCol;
	}*/
	
	// +++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public Stage getMainStage() {
		return gameStage;
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("/view/resources/purple.png", 256, 256, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null );
		gamePane.setBackground(new Background(background));
	}
	
	
	//+++++++++++ Metodos Botones +++++++++++
	
	private AlgoChessButton botonJinete(Jugador jugador) {
		
		AlgoChessButton botonJinete = new AlgoChessButton("Jinete");
		botonJinete.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				jugador.agregarJinete(posicionCeldaActualX, posicionCeldaActualY);
				agregarJineteATablero(posicionCeldaActualX,posicionCeldaActualY);
				
				
			}			
		});
		return botonJinete;
	}
	
	private AlgoChessButton botonSoldado(Jugador jugador) {
		AlgoChessButton botonSoldado = new AlgoChessButton("Soldado");
		
		botonSoldado.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				jugador.agregarSoldadoInfanteria(posicionCeldaActualX, posicionCeldaActualY);
				agregarSoldadoATablero(posicionCeldaActualX,posicionCeldaActualY);
				
					}			
		});
		return botonSoldado;
	}
	
	private AlgoChessButton botonCurandero(Jugador jugador) {
		AlgoChessButton botonCurandero = new AlgoChessButton("Curandero");
		
		botonCurandero.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				jugador.agregarCurandero(posicionCeldaActualX, posicionCeldaActualY);
				agregarCuranderoATablero(posicionCeldaActualX,posicionCeldaActualY);
				//crearBarraJugadorPosicionarPiezas(jugador);
				
					}			
		});
		return botonCurandero;
	}
	
	private AlgoChessButton botonCatapulta(Jugador jugador) {
		AlgoChessButton botonCatapulta = new AlgoChessButton("Catapulta");
		
		botonCatapulta.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				jugador.agregarCatapulta(posicionCeldaActualX, posicionCeldaActualY);
				agregarCatapultaATablero(posicionCeldaActualX,posicionCeldaActualY);
				//crearBarraJugadorPosicionarPiezas(jugador);
				
					}			
		});
		return botonCatapulta;
	}
	
	private void agregarJineteATablero(int posicionX, int posicionY) {
		
		Node[][] celdas = new Node[NUM_ROWS][NUM_COLUMNS];
		celdas[posicionX][posicionY] = new Celda(posicionCeldaActualX,posicionCeldaActualY, Color.BEIGE, "J");
		gamePane.getChildren().add(celdas[posicionCeldaActualX][posicionCeldaActualY]);
	}
	
	private void agregarSoldadoATablero(int posicionX, int posicionY) {
		
		Node[][] celdas = new Node[NUM_ROWS][NUM_COLUMNS];
		celdas[posicionX][posicionY] = new Celda(posicionX,posicionY, Color.BEIGE, "S");
		gamePane.getChildren().add(celdas[posicionX][posicionY]);
	}
	
	private void agregarCatapultaATablero(int posicionX, int posicionY) {
		
		Node[][] celdas = new Node[NUM_ROWS][NUM_COLUMNS];
		celdas[posicionX][posicionY] = new Celda(posicionX,posicionY, Color.BEIGE, "Ca");
		gamePane.getChildren().add(celdas[posicionX][posicionY]);
	}
	
	private void agregarCuranderoATablero(int posicionX, int posicionY) {
		
		Node[][] celdas = new Node[NUM_ROWS][NUM_COLUMNS];
		celdas[posicionX][posicionY] = new Celda(posicionX,posicionY, Color.BEIGE, "Cu");
		gamePane.getChildren().add(celdas[posicionX][posicionY]);
	}
	
	private void agregarEtiquetaAGamePane(String etiqueta, double posicionX, double posicionY, double tamanioLetra) {
		Label label = new Label(etiqueta);
		label.setFont(new Font("Serif", tamanioLetra));
		gamePane.setTopAnchor(label, (double) posicionY);
		gamePane.setLeftAnchor(label, (double) posicionX);
		gamePane.getChildren().add(label);
	}
	
	private void agregarEtiquetaAGamePane(String etiqueta, int cantidad, double posicionX, double posicionY, double tamanioLetra) {
		String strCantidad = Integer.toString(cantidad);
		Label label = new Label(etiqueta + strCantidad);
		label.setFont(new Font("Serif", tamanioLetra));
		gamePane.setTopAnchor(label, (double) posicionY);
		gamePane.setLeftAnchor(label, (double) posicionX);
		gamePane.getChildren().add(label);
	}
	
	private Label etiquetaStringMasEntero(String etiqueta, int cantidad, double tamanioLetra) {
		String strCantidad = Integer.toString(cantidad);
		Label label = new Label(etiqueta + strCantidad);
		label.setFont(new Font("Serif", tamanioLetra));
		return label;
	}
	private Label etiqueta(String etiqueta, double tamanioLetra) {
		
		Label label = new Label(etiqueta);
		label.setFont(new Font("Serif", tamanioLetra));
		return label;
	}
	
}
