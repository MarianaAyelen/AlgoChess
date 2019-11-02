package Unidades;

public class UnidadCuradora extends Unidad {

	protected int curacion; 
	
	public void curarUnidad(Unidad unaUnidad) {
		unaUnidad.sumarVida(curacion);
	}
	
}
