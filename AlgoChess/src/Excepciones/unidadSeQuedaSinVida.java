package Excepciones;

public class unidadSeQuedaSinVida extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "Unidad sin vida";
	}
}
