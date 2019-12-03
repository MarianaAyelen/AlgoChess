package model;

import controller.ControladorJugador;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Celda extends StackPane {
	protected int posX;
	protected int posY;
	protected Rectangle rect;
	private Color colorOriginal;
	private Label etiqueta = new Label("");
	
	private ImageView imagen;
	 
	
	public Celda(int x, int y) {
		this.posX = x;
		this.posY = y;
		
		if ((x+y)%2==1) {
			colorOriginal = Color.BISQUE.darker().darker();
		}else {
			colorOriginal = Color.BISQUE.darker();
		}
		
		rect = new Rectangle(36,36, colorOriginal);
		rect.setStroke(Color.BLACK);
		rect.setStrokeWidth(2);
		setAlignment(Pos.CENTER);
		//getChildren().add(etiqueta);
		setTranslateX((37 * x));
		setTranslateY(37 * y);
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
		
		
		//imagenSoldado = new ImageView();
		
		
		
		Rectangle rect = new Rectangle(36,36, color);
		rect.setStroke(Color.BLACK);
		rect.setStrokeWidth(3);
		setAlignment(Pos.CENTER);
		
		setTranslateX(37 * x + 325);
		setTranslateY(37 * y + 70);
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
	
	public void pintarCelda(Color nuevoColor) {
		rect.setFill(nuevoColor);
	}
	
	public void despintarCelda() {
		rect.setFill(colorOriginal);
	}
	
	public void agregarImagenSoldado(ControladorJugador jugador) {
		Image nuevaImagen = new Image(jugador.obtenerImagenSoldado(), 30, 33, false, true);
		imagen = new ImageView(nuevaImagen);
		
		this.getChildren().add(imagen);
	}
	
	public void agregarImagenCatapulta(ControladorJugador jugador) {
		Image nuevaImagen = new Image(jugador.obtenerImagenCatapulta(), 33, 33, false, true);
		imagen = new ImageView(nuevaImagen);
		
		this.getChildren().add(imagen);
	}
	
	public void agregarImagenJinete(ControladorJugador jugador) {
		Image nuevaImagen = new Image(jugador.obtenerImagenJinete(), 33, 36, false, true);
		imagen = new ImageView(nuevaImagen);
		
		this.getChildren().add(imagen);
	}
	
	public void agregarImagenCurandero(ControladorJugador jugador) {
		Image nuevaImagen = new Image(jugador.obtenerImagenCurador(), 15, 32, false, true);
		imagen = new ImageView(nuevaImagen);
		
		this.getChildren().add(imagen);
	}
	
	public void eliminarImagen() {
		this.getChildren().remove(imagen);
	}
	
	public void agregarEtiqueta(String letra) {
		etiqueta.setText(letra);
		etiqueta.setTextFill(Color.WHITESMOKE);
		this.getChildren().add(etiqueta);
	}
	public void eliminarEtiqueta() {
		etiqueta.setText("");
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
