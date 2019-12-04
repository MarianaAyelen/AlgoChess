package Excepciones;

public class JugadorNoPuedeCurarAunidadEnemiga extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Jugador no puede curar unidad enemiga";
	}
}
