package model;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class AlgoChessSubScene extends SubScene {

	private final static String FONT_PATH = "src/model/resources/kenvector_future.ttf";
	private final static String BACKGROUND_IMAGE = "model/resources/yellow_panel.png";
	
	private boolean isHidden; 
	
	public AlgoChessSubScene(int posX, int posY, int ancho, int largo, Label label) {
		
		super(new AnchorPane(label), largo, ancho);

		
		prefWidth(largo);
		prefHeight(ancho);
		
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, largo, ancho, false, true), 
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		AnchorPane root2 = (AnchorPane) this.getRoot();
		root2.setTopAnchor(label, 50.0);
		root2.setLeftAnchor(label, 50.0);
		root2.setBackground(new Background(image));
		
		isHidden = true;
		
		setLayoutX(posX);
		setLayoutY(posY);
	}
	
public AlgoChessSubScene(int posX, int posY, int ancho, int largo) {
		
		super(new AnchorPane(), largo, ancho);
		
		prefWidth(largo);
		prefHeight(ancho);
		
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, largo, ancho, false, true), 
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		AnchorPane root2 = (AnchorPane) this.getRoot();
		
		root2.setBackground(new Background(image));
		
		isHidden = true;
		
		setLayoutX(posX);
		setLayoutY(posY);
	}
	
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);
		
		if(isHidden) {
			transition.setToX(-750);
			isHidden = false;
		} else {
			transition.setToX(0);
			isHidden = true;
		}
		
		transition.play();
	}

	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
	
}
