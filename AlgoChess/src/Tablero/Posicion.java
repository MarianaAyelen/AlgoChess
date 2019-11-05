package Tablero;

public class Posicion {
	protected int fila;
	protected int columna;
	
	private void setFila(int nuevaFila) {
		fila = nuevaFila;
	}
	
	private void setColumna(int nuevaColumna) {
		columna = nuevaColumna;
	}
	
	public Posicion(int nuevaFila, int nuevaColumna) {
		this.setFila(nuevaFila);
		this.setColumna(nuevaColumna);
	}
	
	public int getFila() {
		return fila;
	}
	
	public int getColumna() {
		return columna;
	}
	
	
	public int distancia(Posicion otraPosicion) {
		if (otraPosicion.getFila() == fila){
			return otraPosicion.getColumna() - columna;
		}
		if (otraPosicion.getColumna() == columna) {
			return otraPosicion.getFila() - fila;
		}
		if(otraPosicion.getColumna() - columna != otraPosicion.getFila() - fila) {
			return otraPosicion.getColumna() - columna + otraPosicion.getFila() - fila; 
		}
		else {
			return otraPosicion.getColumna() - columna;
		}
	}
}
