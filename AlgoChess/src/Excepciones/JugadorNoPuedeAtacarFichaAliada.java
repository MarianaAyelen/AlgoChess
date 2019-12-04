package Excepciones;

public class JugadorNoPuedeAtacarFichaAliada extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "Jugador no puede atacar ficha aliada";
	}
}
