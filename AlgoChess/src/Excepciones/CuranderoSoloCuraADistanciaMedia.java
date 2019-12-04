package Excepciones;

public class CuranderoSoloCuraADistanciaMedia extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Curandero solo cura a distancia media";
	}
}
