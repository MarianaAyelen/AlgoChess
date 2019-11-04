package Unidades;

public class Jinete extends UnidadAtacante {
	
	public Jinete() {
	
		vidaMax = 100;
		costo = 3;
		vida = 100;
		danioCuerpoAcuerpo = 5;
		danioADistancia = 15;
			}

	public void realizarComportamiento(Unidad unaUnidad) {
		//chequear condiciones de distancias y agregar el otro ataque
		unaUnidad.restarVida(danioADistancia);
	}
	
	
}
