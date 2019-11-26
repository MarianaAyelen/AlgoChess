package Unidades;

import Excepciones.movimientoImposibleUnidadNoMovil;
import Tablero.Celda;

public class unidadNoMovil implements Movilidad {

	@Override
	public void mover(Unidad u, Celda destino) {
		throw new movimientoImposibleUnidadNoMovil();
	}

	@Override
	public void mover(Unidad u, int[] direccion) throws Exception {
		throw new movimientoImposibleUnidadNoMovil();
		
	}

}
