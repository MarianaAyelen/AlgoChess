package view;

import java.util.List;

import application.Main;

import java.util.ArrayList;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.*;
import view.*;


public class EntidadesView {

	private static final int WIDTH = 1200;
	private static final int HEIGHT = 800;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;

	private AlgoChessSubScene jugador1SubScene; 
	private AlgoChessSubScene jugador2SubScene; 
	
	private PlayerLabel jugador1Label;
	private List<EntityPicker> entidadesJugador1;
	private ENTITY eleccionEntidades1;
	private PointsLabel puntosJugador1;
	
	private PlayerLabel jugador2Label;
	private List<EntityPicker> entidadesJugador2;
	private ENTITY eleccionEntidades2;
	private PointsLabel puntosJugador2;
	
	public EntidadesView() {
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createSubScenes();
		crearComenzarButtons();
		createBackground();
	}
	
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	public void createSubScenes() {
		jugador1SubScene = new AlgoChessSubScene(100,90,300,600);
		mainPane.getChildren().add(jugador1SubScene);
		
		jugador2SubScene = new AlgoChessSubScene(100,410,300,600);
		mainPane.getChildren().add(jugador2SubScene);
		
		crearEleccionSubEscena();
	}
	
	private void crearComenzarButtons() {
		AlgoChessButton startButton = new AlgoChessButton("PLAY");
		startButton.setLayoutX(900);
		startButton.setLayoutY(350);
		mainPane.getChildren().add(startButton);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
					}			
		});
	
	}

	
	private void crearEleccionSubEscena() {
	
		jugador1Label = new PlayerLabel("JUGADOR 1 ");
		jugador1Label.setLayoutX(20);
		jugador1Label.setLayoutY(10);
		jugador1SubScene.getPane().getChildren().add(jugador1Label);
		
		jugador2Label = new PlayerLabel("JUGADOR 2 ");
		jugador2Label.setLayoutX(20);
		jugador2Label.setLayoutY(10);
		jugador2SubScene.getPane().getChildren().add(jugador2Label);
		

		jugador1SubScene.getPane().getChildren().add(createEntitiesToChoose());
		jugador2SubScene.getPane().getChildren().add(createEntitiesToChoose());

		puntosJugador1 = new PointsLabel("PUNTOS: " + 20);
		puntosJugador1.setLayoutX(20);
		puntosJugador1.setLayoutY(255);
		jugador1SubScene.getPane().getChildren().add(puntosJugador1);
	
		puntosJugador2 = new PointsLabel("PUNTOS: " + 20);
		puntosJugador2.setLayoutX(20);
		puntosJugador2.setLayoutY(255);
		jugador2SubScene.getPane().getChildren().add(puntosJugador2);
		
		
		
	}
	private void createBackground() {
		Image backgroundImage = new Image("/view/resources/purple.png", 256, 256, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null );
		mainPane.setBackground(new Background(background));
	}
	
	private HBox createEntitiesToChoose() {
		HBox box = new HBox();
		box.setSpacing(20);
		entidadesJugador1 = new ArrayList<>();
		for(ENTITY entity : ENTITY.values()) {
			EntityPicker entitiesToPick = new EntityPicker(entity);
			entidadesJugador1.add(entitiesToPick);
			box.getChildren().add(entitiesToPick);
			
			entitiesToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {	
				@Override
				public void handle(MouseEvent event) {
					 if (event.getButton() == MouseButton.PRIMARY)
			            {
							for(EntityPicker entity: entidadesJugador1) {
								entity.setCircleNotChoosen();
							}
							entitiesToPick.setCircleAdd();
							entitiesToPick.setLabel(1);		// Acá, en lugar de estar harcodeado deberia haber una funcion que devuelva la cantidad de entidades
							puntosJugador1.setPoints(17);		// Acá, en lugar de estar harcodeado deberia haber una funcion que devuelva los puntos restantes
							eleccionEntidades1 = entitiesToPick.getEntity();

			            } else if (event.getButton() == MouseButton.SECONDARY)
			            {
							for(EntityPicker entity: entidadesJugador1) {
								entity.setCircleNotChoosen();
							}
							entitiesToPick.setCircleAdd();
							entitiesToPick.setLabel(-1);		// Acá, en lugar de estar harcodeado deberia haber una funcion que devuelva la cantidad de entidades
							puntosJugador1.setPoints(20);		// Acá, en lugar de estar harcodeado deberia haber una funcion que devuelva los puntos restantes
							eleccionEntidades1 = entitiesToPick.getEntity();
			            }
				}
				
			});
		}
		box.setLayoutX(300 - (118 * 2));
		box.setLayoutY(60);
		return box;
	}

}
