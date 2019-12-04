package Excepciones;

public class CatapultaSoloAtacaADistancia extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Catapulta solo ataca a distancia";
	}

}
