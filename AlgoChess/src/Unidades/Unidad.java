package Unidades;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

import Jugadores.*;

public class Unidad {
	
	protected Jugador propietario;
	protected int costo;
	protected int vida;
	protected int vidaMax;
 
	
	public void asignarPropietario(Jugador unJugador) {
		propietario = unJugador;
	}
	
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
	
	public Jugador obtenerPropietario() {
		return propietario;
	}
	// Metodos utilizados para las pruebas 
	
	public int vidaDeLaUnidad() {
		return vida;
	}
	
	
}
