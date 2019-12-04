package Excepciones;


public class movimientoImposibleUnidadNoMovil extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "Unidad no movil";
	}

}
