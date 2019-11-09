package Unidades;

import Tablero.Celda;

import java.util.List;

public interface Movilidad {
	public void mover(Unidad u, Celda destino) throws Exception;
}
