package Unidades;

import Tablero.Celda;

public class unidadMovil implements Movilidad{

	@Override
	public void mover(Unidad u, Celda destino) throws Exception {
		Celda celdaVieja = u.celdaActual;
		//TODO: verificar celdaVieja esta en misma fila/columna/diagonal que destino
		// verificar que la distancia no sobrepasa distanciaMaxMovilidad
		try {
			destino.agregarUnidadEnCasilleroVacio(u);					
		}catch(Exception e) {
			throw(e);
		}finally {
			celdaVieja.sacarUnidad();
		}
	}

}
