package Unidades;



public class SoldadoDeInfanteria extends UnidadAtacante {

		public SoldadoDeInfanteria() {
			
			vidaMax = 100;
			costo = 1;
			vida = 100;
			danioCuerpoAcuerpo = 10;
			danioADistancia = 0; 
			movilidad = new unidadMovil();
		
		}
		
		public void realizarComportamiento(Unidad unaUnidad) {
			chequearAtaqueAUnidadEnemiga(unaUnidad);
			unaUnidad.restarVida(danioCuerpoAcuerpo);
		}
		
	
		
}
