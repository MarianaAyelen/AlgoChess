package Unidades;

import Excepciones.movimientoImposibleUnidadNoMovil;
import Tablero.Celda;

public class unidadNoMovil implements Movilidad {

	@Override
	public void mover(Unidad u, Celda destino) {
		throw new movimientoImposibleUnidadNoMovil();
	}

}
