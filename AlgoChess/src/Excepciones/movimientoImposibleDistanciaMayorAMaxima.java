package Excepciones;

public class movimientoImposibleDistanciaMayorAMaxima extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "Distancia de movimiento mayor a maxima";
	}
}
