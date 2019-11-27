package model;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Celda extends StackPane {

	public Celda(int x, int y) {
		Rectangle rect = new Rectangle(45, 45, Color.CORAL);
		rect.setStroke(Color.BLACK);
		rect.setStrokeWidth(5);
		setAlignment(Pos.CENTER);
		
		setTranslateX(50 * x);
		setTranslateY(50 * y);
		getChildren().addAll(rect);
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				rect.setStroke(Color.GREENYELLOW);	
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				rect.setStroke(Color.BLACK);	
			}
		});
	}
	
}
