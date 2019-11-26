package model;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class EntityPicker extends VBox {

	private ImageView circleImage;
	private ImageView entityImage;
	private Label quantityLabel;
	
	private String circleNotChoosen = "view/resources/entityChooser/grey_circle.png";
	private String circleAdd = "view/resources/entityChooser/green_boxTick.png";
	private String circleRemove = "view/resources/entityChooser/red_boxTick.png";
	
	private ENTITY entity; 
	
	private boolean isCircleChoosen;
	
	public EntityPicker(ENTITY entity) {
		quantityLabel = new Label("x 0");
		circleImage = new ImageView(circleNotChoosen);
		entityImage = new ImageView(entity.getUrl());
		this.entity = entity;
		isCircleChoosen = false;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.getChildren().add(circleImage);
		this.getChildren().add(entityImage);
		this.getChildren().add(quantityLabel);
	}
	
	public ENTITY getEntity() {
		return entity;
	}
	
	public void setLabel(int quantity) {
		quantityLabel.setText("x " + quantity);
	}
	
	public boolean getIsCircleChoosen() {
		return isCircleChoosen;
	}
	
	public void setCircleNotChoosen() {
		this.isCircleChoosen = false;
		circleImage.setImage(new Image(circleNotChoosen));
	}
	
	public void setCircleAdd() {
		this.isCircleChoosen = true;
		circleImage.setImage(new Image(circleAdd));
	}
	
	public void setCircleRemove() {
		this.isCircleChoosen = true;
		circleImage.setImage(new Image(circleRemove));
	}
}
