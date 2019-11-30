package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import javafx.stage.StageStyle;
import model.AlgoChessButton;
import model.AlgoChessSubScene;
import model.Celda;
import model.EntityPicker;

import java.awt.BorderLayout;
import java.util.List;

import Jugadores.Jugador;
import Tablero.Tablero;
import Excepciones.*;

public class TableroView {
	
	private static final int WIDTH = 1860;
	private static final int HEIGHT = 1000;
	protected int anchoBarraLateral = 300;
	protected int largoBarraLateral = 750;
	protected int posicionXBarraLateralIzquierda = 10;
	protected int posicionYBarraLateralIzquierda = 10;
	protected int posicionXBarraLateralDerecha = 1000;
	protected int posicionYBarraLateralDerecha = 10;
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	int posicionCeldaActualX;
	int posicionCeldaActualY;
	private String jugador1;
	private String jugador2;
	
	private static final int NUM_ROWS = 20;
	private static final int NUM_COLUMNS = 20;

	private static final String PUNTOS_INSUFICIENTES = "No posee los puntos necesarios para colocar la unidad";
	private static final String TERRITORIO_ENEMIGO = "No puede colocar unidades en territorio enemigo";
	private static final String CELDA_OCUPADA = "La celda esa ocupada";
	
	private Label etiquetaJinetesColocados;
	
	public TableroView() {
		initializeStage();
	}
	
	public TableroView(String nombreJugador1, String nombreJugador2) {
		jugador1 = nombreJugador1;
		jugador2 = nombreJugador2;
		initializeStage();
				
	}
	private void initializeStage() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, WIDTH, HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		
		Tablero unTablero = new Tablero(20,20);
		Jugador[] jugadores = new Jugador[2];
		Jugador jugadorAliado = new Jugador(jugador1);
		Jugador jugadorEnemigo = new Jugador(jugador2);
		jugadores[0] = jugadorAliado;
		jugadores[1] = jugadorEnemigo;
		jugadorAliado.agregarTablero(unTablero);
		jugadorEnemigo.agregarTablero(unTablero);
		
		AlgoChessSubScene tablero = crearCeldasDeTablero();
		gamePane.getChildren().add(tablero);  
		
		AlgoChessSubScene barraLateralParaColocarPiezasIzquierda = new AlgoChessSubScene(posicionXBarraLateralIzquierda,posicionYBarraLateralIzquierda,largoBarraLateral,anchoBarraLateral);
		AnchorPane rootBarraLateralParaColocarPiezasIzquierda = barraLateralParaColocarPiezasIzquierda.getPane();
		crearBarraJugadorPosicionarPiezas(jugadorAliado, rootBarraLateralParaColocarPiezasIzquierda);
		gamePane.getChildren().add(barraLateralParaColocarPiezasIzquierda);
		
		AlgoChessSubScene barraLateralParaColocarPiezasDerecha = new AlgoChessSubScene(posicionXBarraLateralDerecha,posicionYBarraLateralDerecha,largoBarraLateral,anchoBarraLateral);
		AnchorPane rootBarraLateralParaColocarPiezasDerecha = barraLateralParaColocarPiezasDerecha.getPane();
		crearBarraJugadorPosicionarPiezas(jugadorEnemigo, rootBarraLateralParaColocarPiezasDerecha);
		gamePane.getChildren().add(barraLateralParaColocarPiezasDerecha);
		
		gamePane.setTopAnchor(tablero, 70.0); // 
		gamePane.setLeftAnchor(tablero, 325.0);
		createBackground();
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
	
	private void crearBarraJugadorPosicionarPiezas(Jugador jugador, AnchorPane root) {
		
		Label etiquetaJinetesColocados = etiquetaStringMasEntero("Jinetes colocados: ", jugador.obtenerCantidadDeJinetesColocados(), 15);
		Label etiquetaSoldadosColocados = etiquetaStringMasEntero("Soldados colocados: ", jugador.obtenerCantidadDeSoldadosColocados(), 15);
		Label etiquetaCuranderosColocados = etiquetaStringMasEntero("Curanderos colocados: ", jugador.obtenerCantidadDeCuranderosColocados(), 15);
		Label etiquetaCatapultasColocadas = etiquetaStringMasEntero("Catapultas colocados: ", jugador.obtenerCantidadDeSoldadosColocados(), 15);
		Label etiquetaPuntosRestantes = etiquetaStringMasEntero("Puntos restantes: ", jugador.obtenerPuntos(), 15);
		Label etiquetaNombreJugador = etiqueta(jugador.obtenerPropietario(), 35);
		
		AlgoChessButton botonJinete = botonJinete(jugador, etiquetaPuntosRestantes, etiquetaJinetesColocados);
		AlgoChessButton botonSoldado = botonSoldado(jugador, etiquetaPuntosRestantes, etiquetaSoldadosColocados);
		AlgoChessButton botonCurandero = botonCurandero(jugador, etiquetaPuntosRestantes, etiquetaCuranderosColocados);
		AlgoChessButton botonCatapulta = botonCatapulta(jugador, etiquetaPuntosRestantes, etiquetaCatapultasColocadas);
		
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
		
	    
	}
	
	// +++++++++++ Metodos de ViewTablero +++++++++++
	
	private AlgoChessSubScene crearCeldasDeTablero() {

		AlgoChessSubScene tablero = new AlgoChessSubScene(0,0,600,600);
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
	    		
	    }
	};
	
	
	
	public Stage getMainStage() {
		return gameStage;
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("/view/resources/purple.png", 256, 256, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null );
		gamePane.setBackground(new Background(background));
	}
	
	//+++++++++++ Metodos Botones +++++++++++
	
	private AlgoChessButton botonJinete(Jugador jugador, Label etiquetaPuntosRestantes, Label etiquetaJinetesColocados) {
		
		AlgoChessButton botonJinete = new AlgoChessButton("Jinete");
		botonJinete.setOnAction(event-> {
				try {
					jugador.agregarJinete(posicionCeldaActualX, posicionCeldaActualY);
					agregarJineteATablero(posicionCeldaActualX,posicionCeldaActualY);
					actualizarEtiquetaDeEntero("Jinetes colocados: ",jugador.obtenerCantidadDeJinetesColocados(), etiquetaJinetesColocados );
					actualizarPuntos(jugador.obtenerPuntos(),etiquetaPuntosRestantes);
					if(jugador.obtenerPuntos()==0) {
						crearJugadorEnPartidaSubEscenaJugador();
					}
					
				}catch(JugadorNoPuedeAgregarMasEntidades e) {
					noPuedeColocarPieza(PUNTOS_INSUFICIENTES);
				}
		});
		return botonJinete;
	}
	
	
	private AlgoChessButton botonSoldado(Jugador jugador, Label etiquetaPuntosRestantes, Label etiquetaSoldadosColocados) {
		AlgoChessButton botonSoldado = new AlgoChessButton("Soldado");
		
		botonSoldado.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					jugador.agregarSoldadoInfanteria(posicionCeldaActualX, posicionCeldaActualY);
					agregarSoldadoATablero(posicionCeldaActualX,posicionCeldaActualY);
					actualizarEtiquetaDeEntero("Soldados colocados: ",jugador.obtenerCantidadDeSoldadosColocados(), etiquetaSoldadosColocados );
					actualizarPuntos(jugador.obtenerPuntos(),etiquetaPuntosRestantes);
					if(jugador.obtenerPuntos()==0) {
						crearJugadorEnPartidaSubEscenaJugador();
					}
				
				}catch(JugadorNoPuedeAgregarMasEntidades e) {
					noPuedeColocarPieza(PUNTOS_INSUFICIENTES);
				}
					}			
		});
		return botonSoldado;
	}
	
	private AlgoChessButton botonCurandero(Jugador jugador, Label etiquetaPuntosRestantes, Label etiquetaCuranderosColocados) {
		AlgoChessButton botonCurandero = new AlgoChessButton("Curandero");
		
		botonCurandero.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				try {
					jugador.agregarCurandero(posicionCeldaActualX, posicionCeldaActualY);
					agregarCuranderoATablero(posicionCeldaActualX,posicionCeldaActualY);
					actualizarEtiquetaDeEntero("Curandero colocados: ",jugador.obtenerCantidadDeCuranderosColocados(), etiquetaCuranderosColocados );
					actualizarPuntos(jugador.obtenerPuntos(),etiquetaPuntosRestantes);

					if(jugador.obtenerPuntos()==0) {
						crearJugadorEnPartidaSubEscenaJugador();
					}
				}catch(JugadorNoPuedeAgregarMasEntidades e) {
					noPuedeColocarPieza(PUNTOS_INSUFICIENTES);
				}	
			}			
			});
		return botonCurandero;
	}
	
	private AlgoChessButton botonCatapulta(Jugador jugador, Label etiquetaPuntosRestantes, Label etiquetaCatapultasColocados) {
		AlgoChessButton botonCatapulta = new AlgoChessButton("Catapulta");
		
		botonCatapulta.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					jugador.agregarCatapulta(posicionCeldaActualX, posicionCeldaActualY);
					agregarCatapultaATablero(posicionCeldaActualX,posicionCeldaActualY);
					actualizarEtiquetaDeEntero("Catapultas colocadas: ",jugador.obtenerCantidadDeCatapultasColocadas(), etiquetaCatapultasColocados );
					actualizarPuntos(jugador.obtenerPuntos(),etiquetaPuntosRestantes);
					if(jugador.obtenerPuntos()==0) {
						crearJugadorEnPartidaSubEscenaJugador();
					}
				}catch(JugadorNoPuedeAgregarMasEntidades e) {
					noPuedeColocarPieza(PUNTOS_INSUFICIENTES);
				}	
			}		
				
				
		});
		return botonCatapulta;
	}
	
	//
	private void crearJugadorEnPartidaSubEscenaJugador() {
		
		AlgoChessSubScene jugador1Partida = new AlgoChessSubScene(1000,10,750,250);
		AlgoChessSubScene jugador2Partida = new AlgoChessSubScene(000,10,750,250);
		
		gamePane.getChildren().add(jugador1Partida); 
		gamePane.getChildren().add(jugador2Partida);
		
		AlgoChessButton comportamientoBoton1  = ComportamientorButton();
		AlgoChessButton movimientoBoton1 = movimientoButton();
		AnchorPane root = jugador1Partida.getPane();
		
		
		root.getChildren().add(comportamientoBoton1);
		root.getChildren().add(movimientoBoton1);
		
		root.setTopAnchor(comportamientoBoton1, 200.0);
		root.setLeftAnchor(comportamientoBoton1, 10.0);
		
		root.setTopAnchor(movimientoBoton1, 400.0);
		root.setLeftAnchor(movimientoBoton1, 10.0);
		
		AlgoChessButton comportamientoBoton2  = ComportamientorButton();
		AlgoChessButton movimientoBoton2 = movimientoButton();
		AnchorPane root2 = jugador2Partida.getPane();
		
		root2.getChildren().add(comportamientoBoton2);
		root2.getChildren().add(movimientoBoton2);
		root2.setTopAnchor(comportamientoBoton2, 200.0);
		root2.setLeftAnchor(comportamientoBoton2, 0.0);
		
		root2.setTopAnchor(movimientoBoton2, 400.0);
		root2.setLeftAnchor(movimientoBoton2, 10.0);
	}
	
	private void subSceneComportamiento(AlgoChessSubScene jugadorPartida) {
		
	}
	
	private AlgoChessButton ComportamientorButton() {
		AlgoChessButton botonComportamiento = new AlgoChessButton("Comportamiento");
		botonComportamiento.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
	
			}	
		});
	
		return botonComportamiento;
	}
	
	private AlgoChessButton movimientoButton() {
		AlgoChessButton movimientoButton = new AlgoChessButton("Mover");
		movimientoButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
	
			}	
		});
	
		return movimientoButton;
	}
	
	private void noPuedeColocarPieza(String texto) {
		Alert dialogoAlerta = new Alert(AlertType.INFORMATION);
		dialogoAlerta.setTitle("Pieza invalida");
		dialogoAlerta.setHeaderText(null);
		dialogoAlerta.setContentText(texto);
		dialogoAlerta.initStyle(StageStyle.UTILITY);
		dialogoAlerta.showAndWait();
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
