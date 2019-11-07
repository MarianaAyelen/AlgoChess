package Unidades;

import Excepciones.movimientoImposibleCeldaInhabilitada;
import Excepciones.movimientoImposibleDistanciaMayorAMaxima;
import Excepciones.movimientoImposibleDistanciaNula;
import Excepciones.movimientoImposibleUnidadNoMovil;
import Tablero.Celda;

public class unidadMovil implements Movilidad{

	@Override
	public void mover(Unidad u, Celda destino) throws Exception {
		Celda celdaVieja = u.celdaActual;
		//if(!celdaVieja.MismaFila(destino) || !celdaVieja.MismaColuna(destino)
		// || !celdaVieja.MismaDiagonal(destino)) {
		//		throw new movimientoImposibleDireccionNoConstante();
		//}//Validacion no necesaria dado que maxima distancia es 1
		if(celdaVieja.calcularDistancia(destino)>Unidad.DISTANCIA_MOVIMIENTO_MAX) {
				throw new movimientoImposibleDistanciaMayorAMaxima();
		}
		if(celdaVieja.calcularDistancia(destino)<=0) {
			throw new movimientoImposibleDistanciaNula();
		}
//		if(!destino.estaVacia()) {
//			throw new movimientoImposibleCeldaInhabilitada();
//		}
		destino.agregarUnidadEnCasilleroVacio(u);					
		celdaVieja.sacarUnidad();
	}

}
