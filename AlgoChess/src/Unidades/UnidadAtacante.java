package Unidades;


public class UnidadAtacante extends Unidad {


	protected int danioADistancia;
	protected int danioCuerpoAcuerpo;
	
	public void atacarCuerpoACuerpo(Unidad unaUnidad) {
		unaUnidad.restarVida(danioCuerpoAcuerpo);
	}
	
	public void atacarADistancia(Unidad unaUnidad) {
		unaUnidad.restarVida(danioADistancia);
	}
	
}
