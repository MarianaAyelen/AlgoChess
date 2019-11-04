package Unidades;

public class UnidadCuradora extends Unidad {

	protected int curacion; 
	
	public void realizarComportamiento(Unidad unaUnidad) {
		unaUnidad.sumarVida(curacion);
	}
	
}
