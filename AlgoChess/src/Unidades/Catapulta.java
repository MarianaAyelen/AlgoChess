package Unidades;


public class Catapulta extends UnidadAtacante {

	public Catapulta() {
		
		vidaMax = 50;  
		costo = 5;
		vida = 50;
		danioCuerpoAcuerpo = 0;
		danioADistancia = 20;
	
	}
	
	public void realizarComportamiento(Unidad unaUnidad) {
		unaUnidad.restarVida(danioADistancia);
	}
	
	
}
