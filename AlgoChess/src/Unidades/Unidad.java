package Unidades;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class Unidad {
	
	protected int costo;
	protected int vida;
	protected int vidaMax;
 
	
	public void restarVida(int danioSufrido) {
	
		vida = vida - danioSufrido; 
	}	
	
	public void sumarVida(int curacionGanada) {
		vida = vida + curacionGanada;
		if(vida > vidaMax) {
			vida = vidaMax; 
		}
			
	}
	
	public void realizarComportamiento(Unidad unaUnidad) {
	}
	
	// Metodos utilizados para las pruebas 
	
	public int vidaDeLaUnidad() {
		return vida;
	}
	
	
}
