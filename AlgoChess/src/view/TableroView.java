package view;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import model.AlgoChessSubScene;
import model.Celda;

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
	
}
