package Unidades;


public class Catapulta extends UnidadAtacante {

	public Catapulta() {
		
		
		vidaMax = 50;  
		costo = 5;
		vida = 50;
		danioCuerpoAcuerpo = 0;
		danioADistancia = 20;
		movilidad = new unidadNoMovil();
	}
	
	public void realizarComportamiento(Unidad unaUnidad) {
		chequearAtaqueAUnidadEnemiga(unaUnidad);
		unaUnidad.restarVida(danioADistancia);
	}
	
	
}
