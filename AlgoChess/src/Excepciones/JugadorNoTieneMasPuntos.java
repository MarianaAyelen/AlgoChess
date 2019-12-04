package Excepciones;

public class JugadorNoTieneMasPuntos extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Jugador no tiene mas puntos";
	}
}
