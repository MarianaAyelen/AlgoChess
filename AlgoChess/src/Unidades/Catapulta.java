package Unidades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Excepciones.CatapultaSoloAtacaADistancia;
import Jugadores.Jugador;

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
		List<Unidad> unidadesContiguas = encontrarUnidadesContiguas(unaUnidad);
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
		agregarUnidadesContiguasAliadasYEnemigas(unaUnidad, unidadesContiguas, this.propietario, unaUnidad.propietario);
		unidadesContiguas.remove(unaUnidad);
		return unidadesContiguas;
	}
	
	public void agregarUnidadesContiguasAliadasYEnemigas(Unidad unaUnidad, List<Unidad> unidadesContiguas, Jugador aliado, Jugador adversario) {//incluye sucesion de unidades contiguas
		Iterator<Unidad> iteradorUnidadesAliadas = aliado.getUnidadesJugador().iterator();
		Iterator<Unidad> iteradorUnidadesEnemigas = adversario.getUnidadesJugador().iterator();
		while (iteradorUnidadesAliadas.hasNext() || iteradorUnidadesEnemigas.hasNext()) {
			if(iteradorUnidadesAliadas.hasNext()) {
				Unidad otraUnidad = iteradorUnidadesAliadas.next();
				if(otraUnidad.calcularDistancia(unaUnidad)==1
					&& !unidadesContiguas.contains(otraUnidad)) {
					unidadesContiguas.add(otraUnidad);
					agregarUnidadesContiguasAliadasYEnemigas(otraUnidad, unidadesContiguas, aliado, adversario);//recursividad en la busqueda
				}				
			}
			if(iteradorUnidadesEnemigas.hasNext()) {
				Unidad otraUnidad = iteradorUnidadesEnemigas.next();
				if(otraUnidad.calcularDistancia(unaUnidad)==1
					&& !unidadesContiguas.contains(otraUnidad)) {
					unidadesContiguas.add(otraUnidad);
					agregarUnidadesContiguasAliadasYEnemigas(otraUnidad, unidadesContiguas, adversario, aliado);//recursividad en la busqueda
				}				
				
			}
		}
	}
	
	//Tipo de unidad
	public int tipoDeUnidad() {
		return 0;
	}
}
