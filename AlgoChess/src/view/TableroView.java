package view;

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
		
		Node[][] celdas = new Node[NUM_ROWS][NUM_COLUMNS];
		for (int i=0; i < NUM_COLUMNS ; i++) {
			for (int j=0; j < NUM_ROWS ; j++) {
				celdas[i][j] = new Celda(i,j);
				gamePane.getChildren().add(celdas[i][j]);
				celdas[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, handlerMostrarPosicionCelda);
			}
		}
		
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador jugadorAliado = new Jugador("Pepe");
		Jugador jugadorEnemigo = new Jugador("Roberto");
		jugadores[0] = jugadorAliado;
		jugadores[1] = jugadorEnemigo;
		jugadorAliado.agregarTablero(unTablero);
		jugadorEnemigo.agregarTablero(unTablero);
		
		colocarPiezasJugador(jugadorAliado);
		
		createBackground();
	}	
	
	private void refrescar() {
		// TODO Auto-generated method stub
		
	}

	private void mostrarFilaYColumna() {
		agregarEtiquetaAGamePane("Fila: ",1025.0,180.0,15 );
		agregarEtiquetaAGamePane("Columna: ",1025.0,215.0,15 );
		
	}
	
	EventHandler<MouseEvent> handlerMostrarPosicionCelda = new EventHandler<MouseEvent>() {
	    @Override
	    public void handle(MouseEvent event) {
	    		Object celda = event.getSource();
	    		int x = ((Celda) celda).devolverPosicion()[0];
	    		int y = ((Celda) celda).devolverPosicion()[1];
	    		posicionCeldaActualX = x;
	    		posicionCeldaActualY = y;
	    		mostrarFilaYColumnaDeCeldaEnLaBarraLateral(posicionCeldaActualX, posicionCeldaActualY);
	    		
	    }
	
	};
	
	private void mostrarFilaYColumnaDeCeldaEnLaBarraLateral(int x, int y) {
		String strX = Integer.toString(x+1);
		String strY = Integer.toString(y+1);
		
		TextField campoDeTextoX = new TextField(strX);
		TextField campoDeTextoY = new TextField(strY);
		campoDeTextoX.setMaxWidth(35);
		campoDeTextoY.setMaxWidth(35);
		
		gamePane.getChildren().add(campoDeTextoX);
		gamePane.getChildren().add(campoDeTextoY);
		
		gamePane.setTopAnchor(campoDeTextoX, 180.0);
		gamePane.setLeftAnchor(campoDeTextoX, 1100.0);
		gamePane.setTopAnchor(campoDeTextoY, 215.0);
		gamePane.setLeftAnchor(campoDeTextoY, 1100.0);
		
		
		
	}
	
	public Stage getMainStage() {
		return gameStage;
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("/view/resources/purple.png", 256, 256, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null );
		gamePane.setBackground(new Background(background));
	}
	
	
	
	private void colocarPiezasJugador(Jugador jugador) {
		AlgoChessSubScene barraColocarPiezas = new AlgoChessSubScene(1010,20,700,260);
		gamePane.getChildren().add(barraColocarPiezas);
		mostrarFilaYColumna();
		agregarEtiquetaAGamePane(jugador.obtenerPropietario(), 1025.0, 40.0, 35);
		agregarEtiquetaAGamePane("Coloque sus piezas", 1025.0, 80.0, 25);
		
		agregarPiezasJugador(jugador);
	}
	
	private void agregarPiezasJugador(Jugador jugador) {
		int cantidadDeUnidades = jugador.cantidadDeUnidades();
		
		agregarPuntosJugador(jugador.obtenerPuntos());
		agregarBotonesPiezas(jugador);
		
	}
	
	private void agregarPuntosJugador(int puntos) {
		agregarEtiquetaAGamePane("Puntos restantes: ", puntos,1025.0, 140, 20);
	}
	
	private void agregarBotonesPiezas(Jugador jugador) {
		botonJinete(jugador);
		agregarEtiquetaAGamePane("Jinetes colocados: ", jugador.obtenerCantidadDeJinetesColocados(),1050.0, 350.0, 15);
		botonSoldado(jugador);
		agregarEtiquetaAGamePane("Soldados colocados: ", jugador.obtenerCantidadDeSoldadosColocados(),1050.0, 450.0, 15);
		botonCurandero(jugador);
		agregarEtiquetaAGamePane("Curanderos colocados: ", jugador.obtenerCantidadDeCuranderosColocados(),1050.0, 550.0, 15);
		botonCatapulta(jugador);
		agregarEtiquetaAGamePane("Catapultas colocados: ", jugador.obtenerCantidadDeCatapultasColocadas(),1050.0, 650.0, 15);
	}
	
	
	private void botonJinete(Jugador jugador) {
		
		AlgoChessButton botonJinete = new AlgoChessButton("Jinete");
		
		gamePane.setTopAnchor(botonJinete, 300.0);
		gamePane.setLeftAnchor(botonJinete, 1050.0);
		gamePane.getChildren().add(botonJinete);
		botonJinete.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				jugador.agregarJinete(posicionCeldaActualX, posicionCeldaActualY);
				agregarJineteATablero(posicionCeldaActualX,posicionCeldaActualY);
				colocarPiezasJugador(jugador);
			}			
		});
		
	}
	
	private void botonSoldado(Jugador jugador) {
		AlgoChessButton botonSoldado = new AlgoChessButton("Soldado");
		gamePane.setTopAnchor(botonSoldado, 400.0);
		gamePane.setLeftAnchor(botonSoldado, 1050.0);
		gamePane.getChildren().add(botonSoldado);
		botonSoldado.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				jugador.agregarSoldadoInfanteria(posicionCeldaActualX, posicionCeldaActualY);
				agregarSoldadoATablero(posicionCeldaActualX,posicionCeldaActualY);
				colocarPiezasJugador(jugador);
					}			
		});
	}
	
	private void botonCurandero(Jugador jugador) {
		AlgoChessButton botonCurandero = new AlgoChessButton("Curandero");
		gamePane.setTopAnchor(botonCurandero, 500.0);
		gamePane.setLeftAnchor(botonCurandero, 1050.0);
		gamePane.getChildren().add(botonCurandero);
		
		botonCurandero.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				jugador.agregarCurandero(posicionCeldaActualX, posicionCeldaActualY);
				agregarCuranderoATablero(posicionCeldaActualX,posicionCeldaActualY);
				colocarPiezasJugador(jugador);
					}			
		});
		
	}
	
	private void botonCatapulta(Jugador jugador) {
		AlgoChessButton botonCatapulta = new AlgoChessButton("Catapulta");
		gamePane.setTopAnchor(botonCatapulta, 600.0);
		gamePane.setLeftAnchor(botonCatapulta, 1050.0);
		gamePane.getChildren().add(botonCatapulta);
		botonCatapulta.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				jugador.agregarCatapulta(posicionCeldaActualX, posicionCeldaActualY);
				agregarCatapultaATablero(posicionCeldaActualX,posicionCeldaActualY);
				colocarPiezasJugador(jugador);
					}			
		});
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
}
