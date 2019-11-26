package Unidades;

import Excepciones.movimientoImposibleDistanciaMayorAMaxima;
import Excepciones.movimientoImposibleDistanciaNula;
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
		if(destino.agregarUnidadEnCasilleroVacio(u)) {					
			celdaVieja.sacarUnidad();
		}
	}

	@Override
	public void mover(Unidad u, int[] direccion) throws Exception  {
		Celda celdaVieja = u.celdaActual;
		
		if(direccion[0]==0 && direccion[1]==0) {
			
			throw new movimientoImposibleDistanciaNula();
		}
		if(direccion[0]>1 || direccion[1]>1 || direccion[0]<-1 || direccion[1]<-1) {
			throw new movimientoImposibleDistanciaMayorAMaxima();
		}
		Celda celdaDestino = u.propietario.obtenerCeldaConDireccion(u.obtenerPosicionX(), u.obtenerPosicionY(), direccion);
		if(celdaDestino.agregarUnidadEnCasilleroVacio(u)) {					
			celdaVieja.sacarUnidad();
		}
	}

}
