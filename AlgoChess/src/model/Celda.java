package model;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Celda extends StackPane {
	protected int posX;
	protected int posY;
	protected Rectangle rect;
	
	public Celda(int x, int y) {
		this.posX = x;
		this.posY = y;
		Color color = null;
		if ((x+y)%2==1) {
			color = Color.BISQUE.darker();
		}else {
			color = Color.BISQUE;
		}
		rect = new Rectangle(29,29, color);
		rect.setStroke(Color.BLACK);
		rect.setStrokeWidth(2);
		setAlignment(Pos.CENTER);
		
		setTranslateX((30 * x));
		setTranslateY(30 * y);
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
	
	
	public Celda(int x, int y, Color color, String etiqueta) {
		this.posX = x;
		this.posY = y;
		
		Rectangle rect = new Rectangle(29,29, color);
		rect.setStroke(Color.BLACK);
		rect.setStrokeWidth(3);
		setAlignment(Pos.CENTER);
		
		setTranslateX(30 * x + 325);
		setTranslateY(30 * y + 70);
		getChildren().addAll(rect);
		Label label = new Label(etiqueta);
		label.setTextFill(Color.WHITESMOKE);
		getChildren().add(label);
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
	
	public void seleccionarCelda() {
		
	}
	
	public int[] devolverPosicion() {
		int[] posicion = new int[2];
		posicion[0] = posX;
		posicion[1] = posY;
		return posicion;
	}
	
}
