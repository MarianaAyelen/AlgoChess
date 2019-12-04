package Excepciones;


public class movimientoImposibleCeldaInhabilitada extends Exception {

	private static final long serialVersionUID = 1L;

	public movimientoImposibleCeldaInhabilitada() {
		     
	  }
	
	public String getMessage() {
		return "Celda inhabilitada";
	}
}
