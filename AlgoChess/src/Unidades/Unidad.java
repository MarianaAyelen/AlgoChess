package Unidades;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

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
	
	// Metodos utilizados para las pruebas 
	
	public int vidaDeLaUnidad() {
		return vida;
	}
	
	
}
