package Excepciones;

public class JineteSoloPuedeAtacarADistanciaMedia  extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "Jinete solo puede atacar a distancia media";
	}
}
