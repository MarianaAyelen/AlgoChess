package Excepciones;

public class JineteNoPuedeAtacarConSoldadosAliadosCerca extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "Jinete no puede atacar con soldados aliados cerca";
	}
}