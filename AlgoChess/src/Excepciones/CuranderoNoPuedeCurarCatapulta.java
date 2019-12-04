package Excepciones;

public class CuranderoNoPuedeCurarCatapulta extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Curandero no puede curar catapulta";
	}
}
