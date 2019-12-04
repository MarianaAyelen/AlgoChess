package Excepciones;


public class JugadorNoPuedeAgregarMasEntidades extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Jugador no puede agregar mas entidades";
	}
	
}
