package Excepciones;

public class jugadorPierde extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "Jugador pierde";
	}
}
