package model;

public enum ENTITY {
	
	SOLDADO("view/resources/entityChooser/soldado.png"),
	JINETE("view/resources/entityChooser/jinete.png"),
	CURANDERO("view/resources/entityChooser/curandero.png"),
	CATAPULTA("view/resources/entityChooser/catapulta.png");
	
	private String urlShip;
	
	private ENTITY(String urlShip) {
		this.urlShip = urlShip;
	}
	
	public String getUrl() {
		return this.urlShip;
	}

}
