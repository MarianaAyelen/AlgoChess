package Unidades;



import Jugadores.*;
import Excepciones.*;
import Tablero.*;
import java.lang.Math;
import java.util.Iterator;
import java.util.List;

public class Unidad {
	
	protected Celda celdaActual;
	protected Jugador propietario;
	protected int costo;
	protected int vida;
	protected int vidaMax;
	protected Movilidad movilidad;
	protected static final int DISTANCIA_MOVIMIENTO_MAX = 1;
		
	public int calcularDistancia(Unidad otraUnidad) {
		int deltaX = this.obtenerPosicionX() - otraUnidad.obtenerPosicionX();
		int deltaY = this.obtenerPosicionY() - otraUnidad.obtenerPosicionY();
		return (int) (Math.sqrt(deltaX*deltaX + deltaY*deltaY)  ) ;
	}
	
	
	public void asignarCelda(Celda unaCelda) {
		celdaActual = unaCelda;
		if(unaCelda.estaVacia())
		{
			unaCelda.agregarUnidad(this);
		}
	}
	
	public int obtenerPosicionX() {
		return celdaActual.obtenerPosicionX();
	}
	
	public int obtenerPosicionY() {
		return celdaActual.obtenerPosicionY();
	}
	
	public void asignarPropietario(Jugador unJugador) {
		propietario = unJugador;
	}
	
	public void unidadSinVida() {
		
			this.propietario.eliminarUnidadSinVida(this);
			this.celdaActual.sacarUnidad();

		
	}
	public void restarVida(int danioSufrido) {
	
		vida = vida - danioSufrido; 
		if (vida <= 0) {
			throw new unidadSeQuedaSinVida();
		}
	}	
	
	public void sumarVida(int curacionGanada) {
		vida = vida + curacionGanada;
		if(vida > vidaMax) {
			vida = vidaMax; 
		}
			
	}
	
	public int obtenerCosto() {
		return costo;
	}
	
	public void realizarComportamiento(Unidad unaUnidad) {
	}
	
	public Jugador obtenerPropietario() {
		return propietario;
	}
	
	public boolean estaADistanciaUno(Unidad unaUnidad) {
		boolean estaADistanciaUno = false;
		if (this.obtenerCelda().esDistanciaUno(unaUnidad.obtenerCelda())) {
			estaADistanciaUno = true;
		}
		return estaADistanciaUno;
	}
	
	public boolean estaADistanciaCercana(Unidad unaUnidad) {
		boolean esDistanciaCercana = false;
		if ((this.obtenerCelda()).esDistanciaCercana(unaUnidad.obtenerCelda())) {
			esDistanciaCercana = true;
		}
		return esDistanciaCercana;
	}
	
	public boolean estaADistanciaMedia(Unidad unaUnidad) {
		
		return (this.obtenerCelda()).esDistanciaMedia(unaUnidad.obtenerCelda());
	}
	
	public boolean estaADistanciaLejana(Unidad unaUnidad) {
		boolean esDistanciaLejana = false;
		if (this.obtenerCelda().esDistanciaLejana(unaUnidad.obtenerCelda())) {
			esDistanciaLejana = true;
		}
		return esDistanciaLejana;
	}
	
	public boolean haySoldadoDeInfanteriaAliadoEnDistanciaCercana(Unidad unaUnidad) {
		boolean haySoldadoEnDistanciaCercana = false;
		List<Unidad> unidadesCercanas = propietario.unidadesAliadasEnDistanciaCercana(unaUnidad);
		Iterator<Unidad> iteradorUnidades = unidadesCercanas.iterator();
		while (iteradorUnidades.hasNext()) {
			Unidad otraUnidad = iteradorUnidades.next();
			if(otraUnidad instanceof SoldadoDeInfanteria) {
				haySoldadoEnDistanciaCercana = true;
			}
		}
		return haySoldadoEnDistanciaCercana;
	}
	
	public boolean esUnidadAliada(Unidad unaUnidad) {
		boolean esUnidadAliada = false;
		if (propietario.equals(unaUnidad.obtenerPropietario())) {
			esUnidadAliada = true;
		}
		return esUnidadAliada;
	}
	
	public boolean hayUnidadesEnemigasCercanas() {
		return propietario.hayUnidadesEnemigasCercanas(this);
	}
	
	//Movimiento
	public void mover(Celda destino) throws Exception {
		this.movilidad.mover(this, destino);
	}
	
	public void mover(int[] direccion) throws Exception {
		this.movilidad.mover(this, direccion);
	}
	
	//Tipo de unidad
	public int tipoDeUnidad() { //Catap:0,Cur:1,Jin:2,Sold:3 //TODO: mejorar implementacion (atributo en clase unidad)
		return -1;
	}
	
	// Metodos utilizados para las pruebas 
	
	public int vidaDeLaUnidad() {
		return vida;
	}
	
	public Celda obtenerCelda() {
		return celdaActual; 
	}
	
	public String obtenerNombrePropietario() {
		return this.propietario.obtenerPropietario();
	}
	
	public int obtenerVida() {
		return this.vida;
	}
	
}
