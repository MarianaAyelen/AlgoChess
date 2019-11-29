package view;
//import java.awt.TextField;
import java.util.ArrayList;
import java.util.List;

import com.sun.glass.events.KeyEvent;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import model.InfoLabel;
import model.PlayerLabel;
import model.PointsLabel;
import model.ENTITY;
import model.EntityPicker;
import model.AlgoChessButton;
import model.AlgoChessSubScene;
import view.*;

public class ViewManager {
	private static final int WIDTH = 1200;
	private static final int HEIGHT = 800;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private final static int MENU_BUTTONS_START_X = 100;
	private final static int MENU_BUTTONS_START_Y = 250;
	
	private AlgoChessSubScene helpSubScene;
	private AlgoChessSubScene creditsSubScene;
	
	private AlgoChessSubScene jugadoresSubScene;
	
	private TableroView tableroScene;
	
	private AlgoChessSubScene sceneToHide;
	
	private AlgoChessButton playButton;
	
	List<AlgoChessButton> menuButtons;
	
	List<EntityPicker> entityList;
	PointsLabel pointsLabel;
	PlayerLabel playerLabel;
	
	private String nombreJugador1;
	private String nombreJugador2;
	
	public ViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createSubScenes();
		createButtons();
		createBackground();
		createLogo();
	}
	
	private void showSubScene(AlgoChessSubScene subScene) {
		if(sceneToHide != null) {
			sceneToHide.moveSubScene();
		}
		
		subScene.moveSubScene();
		sceneToHide = subScene;  
	}
	
	private void createSubScenes() {
		Label labelCredits = new Label("Créditos: Alejandro, Mariana, Juan, Juan Pablo");
		labelCredits.setFont(new Font("Serif", 20.0));
		creditsSubScene = new AlgoChessSubScene(1200,300,400,600, labelCredits);
		mainPane.getChildren().add(creditsSubScene);
		
		Label labelHelp = new Label("No hay ayuda en este juego");
		labelHelp.setFont(new Font("Serif", 20.0));
		helpSubScene = new AlgoChessSubScene(1200,300,400,600, labelHelp);
		mainPane.getChildren().add(helpSubScene);
		
		
		crearJugadoresSubScene();
			
		
	}
	
	public void crearJugadoresSubScene() {
	
		Label labelJugadores = new Label("Ingresar nombre de los usuarios");
		labelJugadores.setFont(new Font("Serif", 30.0));
		jugadoresSubScene = new AlgoChessSubScene(1200,300,400,600, labelJugadores);
		mainPane.getChildren().add(jugadoresSubScene);
		
		
		PlayerLabel jugador1Label = new PlayerLabel("JUGADOR 1: ");
		jugador1Label.setLayoutX(20);
		jugador1Label.setLayoutY(120);
		jugadoresSubScene.getPane().getChildren().add(jugador1Label);
		
		PlayerLabel jugador2Label = new PlayerLabel("JUGADOR 2: ");
		jugador2Label.setLayoutX(20);
		jugador2Label.setLayoutY(200);
		jugadoresSubScene.getPane().getChildren().add(jugador2Label);
		
		
		createPlayButton();
		playButton.setLayoutX(200);
		playButton.setLayoutY(300);
		jugadoresSubScene.getPane().getChildren().add(playButton);
	
	
	}
		
	public Stage getMainStage() {
		return mainStage;
	}
	
	private void addMenuButton(AlgoChessButton button) {
		button.setLayoutX(MENU_BUTTONS_START_X);
		button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
	private void createButtons() {
		createStartButton();
		createHelpButton();
		createCreditsButton();
		createExitButton();
	}
	
	private void createPlayButton() {
		playButton = new AlgoChessButton("PLAY");
			
		TextField textFieldJugador1 = new TextField();
		HBox hboxJugador1 = new HBox(textFieldJugador1);
		hboxJugador1.setLayoutX(300);
		hboxJugador1.setLayoutY(120);		
		jugadoresSubScene.getPane().getChildren().add(hboxJugador1);		
		
		TextField textFieldJugador2 = new TextField();
		HBox hboxJugador2 = new HBox(textFieldJugador2);
		hboxJugador2.setLayoutX(300);
		hboxJugador2.setLayoutY(200);		
		jugadoresSubScene.getPane().getChildren().add(hboxJugador2);
	
		playButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
				nombreJugador1 = textFieldJugador1.getText();
				nombreJugador2 = textFieldJugador2.getText();
				tableroScene = new TableroView(nombreJugador1, nombreJugador2 );
				Stage sceneTablero = tableroScene.getMainStage();
				mainStage.hide();
				sceneTablero.show();		
				
			}			
		});
	}
	
	private void createStartButton() {
		AlgoChessButton startButton = new AlgoChessButton("PLAY");
		addMenuButton(startButton);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				/*
				Stage sceneEntidades = entidadesScene.getMainStage();
				mainStage.hide();
				sceneEntidades.show();
				*/
				showSubScene(jugadoresSubScene);
			}			
		});
	}

	private void createHelpButton() {
		AlgoChessButton helpButton = new AlgoChessButton("HELP");
		addMenuButton(helpButton); 	
		
		helpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(helpSubScene);
			}			
		});
	}

	private void createCreditsButton() {
		AlgoChessButton creditsButton = new AlgoChessButton("CREDITS");
		addMenuButton(creditsButton); 	
		
		creditsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(creditsSubScene);
			}			
		});
	}
	
	private void createExitButton() {
		AlgoChessButton exitButton = new AlgoChessButton("EXIT");
		addMenuButton(exitButton); 	
		
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
			}			
		});
	}
	
	private void createBackground() {
		Image backgroundImage = new Image("/view/resources/purple.png", 256, 256, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null );
		mainPane.setBackground(new Background(background));
	}
	
	private void createLogo() {
		ImageView logo = new ImageView("view/resources/logo.png");
		logo.setLayoutX(300);
		logo.setLayoutY(25);
		
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override 
			public void handle(MouseEvent event) {
				logo.setEffect(new DropShadow());
			}
		});
		
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override 
			public void handle(MouseEvent event) {
				logo.setEffect(null);
			}
		});
		
		mainPane.getChildren().add(logo);
	}
}
