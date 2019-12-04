package Excepciones;

public class movimientoImposibleDistanciaNula extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public String getMessage() {
		return "Distancia de movimiento nula";
	}
}
