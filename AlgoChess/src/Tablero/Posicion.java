package Tablero;

public class Posicion {
	protected int fila;
	protected int columna;
	
	public Posicion(int new_fila, int new_columna) {
		fila = new_fila;
		columna = new_columna;
	}
	
	private int getFila() {
		return fila;
	}
	
	private int getColumna() {
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
