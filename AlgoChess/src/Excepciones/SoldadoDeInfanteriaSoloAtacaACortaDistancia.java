package Excepciones;

public class SoldadoDeInfanteriaSoloAtacaACortaDistancia extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "Soldado de infanteria solo ataca a corta distancia";
	}
}
