package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.AlgoChessButton;
import model.AlgoChessSubScene;
import model.Celda;

import java.util.List;

import Jugadores.Jugador;
import Tablero.Tablero;

public class TableroView {
	
	private static final int WIDTH = 1860;
	private static final int HEIGHT = 1000;
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	
	
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
		botonCurandero();
		agregarEtiquetaAGamePane("Curanderos colocados: ", 0,1050.0, 550.0, 15);
		botonCatapulta();
		agregarEtiquetaAGamePane("Catapultas colocados: ", 0,1050.0, 650.0, 15);
	}
	
	
	private void botonJinete(Jugador jugador) {
		
		AlgoChessButton botonJinete = new AlgoChessButton("Jinete");
		
		final TextField posicionX = new TextField();
		System.out.println(posicionX.getText());
		//int intPosicionX = Integer.parseInt(posicionX.getText());
		posicionX.setPrefWidth(40);
		gamePane.getChildren().add(posicionX);
		
		
		final TextField posicionY = new TextField();
		//int intPosicionY = Integer.parseInt(posicionY.getText());
		posicionY.setPrefWidth(40);
		gamePane.getChildren().add(posicionY);
		
		gamePane.setTopAnchor(botonJinete, 180.0);
		gamePane.setLeftAnchor(botonJinete, 1050.0);
		gamePane.setTopAnchor(posicionX, 240.0);
		gamePane.setLeftAnchor(posicionX, 1050.0);
		gamePane.setTopAnchor(posicionY, 240.0);
		gamePane.setLeftAnchor(posicionY, 1100.0);
		
		gamePane.getChildren().add(botonJinete);
		botonJinete.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				jugador.agregarJinete(2, 2);
				
				agregarJineteATablero(2-1,2-1);
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
				jugador.agregarSoldadoInfanteria(4, 4);
				agregarSoldadoATablero(4,4);
				colocarPiezasJugador(jugador);
					}			
		});
	}
	
	private void botonCurandero() {
		AlgoChessButton botonCurandero = new AlgoChessButton("Curandero");
		gamePane.setTopAnchor(botonCurandero, 500.0);
		gamePane.setLeftAnchor(botonCurandero, 1050.0);
		gamePane.getChildren().add(botonCurandero);
		
	}
	
	private void botonCatapulta() {
		AlgoChessButton botonCatapulta = new AlgoChessButton("Catapulta");
		gamePane.setTopAnchor(botonCatapulta, 600.0);
		gamePane.setLeftAnchor(botonCatapulta, 1050.0);
		gamePane.getChildren().add(botonCatapulta);
		
	}
	
	private void agregarJineteATablero(int posicionX, int posicionY) {
		
		Node[][] celdas = new Node[NUM_ROWS][NUM_COLUMNS];
		celdas[posicionX][posicionY] = new Celda(posicionX,posicionY, Color.BEIGE, "J");
		gamePane.getChildren().add(celdas[posicionX][posicionY]);
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
