package Excepciones;


public class JugadorNoPuedeColocarEntidadesEnTerritorioEnemigo extends Exception {

	private static final long serialVersionUID = 1L;

	public JugadorNoPuedeColocarEntidadesEnTerritorioEnemigo() {
		 
	 }
	
	public String getMessage() {
		return "Jugador no puede colocar entidades en territorio enemigo";
	}
	  
}

