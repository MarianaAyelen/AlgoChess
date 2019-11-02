package Unidades;



public class Unidad {
	
	protected int costo;
	protected int vida;
	


	public void restarVida(int danioSufrido) {
		
		vida = vida - danioSufrido; 
	}	
	
	public void sumarVida(int curacionGanada) {
		vida = vida + curacionGanada;
	}
	
	// Metodos utilizados para las pruebas 
	
	public int vidaDeLaUnidad() {
		return vida;
	}
	
	
}
