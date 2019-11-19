package Unidades;

import java.util.ArrayList;
import java.util.List;

import Excepciones.CatapultaSoloAtacaADistancia;

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
		int distancia = this.calcularDistancia(unaUnidad);
		
		if (distancia < 6) {
			throw new CatapultaSoloAtacaADistancia();
		}
		try {
		unaUnidad.restarVida(danioADistancia);
		}catch (unidadSeQuedaSinVida e1) {
			unaUnidad.unidadSinVida();
		} 
		atacarAUnidadesContiguas(unaUnidad);
	}
	
	private void atacarAUnidadesContiguas(Unidad unaUnidad) {
		List<Unidad> unidadesContiguas = encontrarUnidadesContiguas(unaUnidad);//mismo propietaro que unaUnidad
		for(Unidad unidadContigua : unidadesContiguas)
		{
			try {
				unidadContigua.restarVida(danioADistancia);
				}catch (unidadSeQuedaSinVida e1) {
					unidadContigua.unidadSinVida();
				} 
		}
	}
	
	private List<Unidad> encontrarUnidadesContiguas(Unidad unaUnidad) {
		List<Unidad> unidadesContiguas = new ArrayList<Unidad>();
		unidadesContiguas.add(unaUnidad);
		unaUnidad.propietario.agregarUnidadesContiguasAliadas(unaUnidad, unidadesContiguas);
		unidadesContiguas.remove(unaUnidad);
		return unidadesContiguas;
	}
	
	//Tipo de unidad
	public int tipoDeUnidad() {
		return 0;
	}
}
