package Unidades;

import Tablero.Celda;

public interface Movilidad {
	public void mover(Unidad u, Celda destino) throws Exception;
}
