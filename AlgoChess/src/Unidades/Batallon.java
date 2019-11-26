package Unidades;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Jugadores.Jugador;
import Tablero.Celda;

public class Batallon {
	protected int numeroDeSoldados = 3;
	protected List<SoldadoDeInfanteria> batallon = new ArrayList<SoldadoDeInfanteria>();
	protected int[] direccionDeMovimiento;
	boolean existeBatallon;
	 
	public Batallon(SoldadoDeInfanteria unSoldado, Celda celdaDestino) throws Exception {
		
		crearBatallon(unSoldado);
		obtenerDireccionDeMovimiento(unSoldado, celdaDestino);
	}
	
	private void obtenerBatallon(List<Unidad> unidades, SoldadoDeInfanteria unSoldado) {
		existeBatallon = false;
		List<Unidad> copiaUnidades = unidades;
		Iterator<Unidad> iteradorUnidades = unidades.iterator();
		Iterator<Unidad> iteradorCopiaUnidades = copiaUnidades.iterator();
		batallon.add(unSoldado);
		
		while(iteradorUnidades.hasNext() && batallon.size() < numeroDeSoldados ) {
			Unidad unidad = iteradorUnidades.next();
			if(unidad instanceof SoldadoDeInfanteria && unSoldado.estaADistanciaUno(unidad) && !batallon.contains(unidad)) {
				
				batallon.add((SoldadoDeInfanteria) unidad);
			}
		}
		
		while(iteradorCopiaUnidades.hasNext() && batallon.size() < numeroDeSoldados ) {
			Iterator<SoldadoDeInfanteria> iteradorBatallon = batallon.iterator();
			Unidad unidad1 = iteradorCopiaUnidades.next();
			while(iteradorBatallon.hasNext() && batallon.size() < numeroDeSoldados ) {
				SoldadoDeInfanteria unSoldadoBatallon = iteradorBatallon.next();
				if(unidad1 instanceof SoldadoDeInfanteria && unSoldadoBatallon.estaADistanciaUno(unidad1) && !batallon.contains(unidad1)) {
					batallon.add((SoldadoDeInfanteria) unidad1);
				}
			}
			
		}
		if(batallon.size()==numeroDeSoldados) {
			existeBatallon = true;
		}
		
	}
	
	private void crearBatallon(SoldadoDeInfanteria unSoldado){
		Jugador propietario = unSoldado.obtenerPropietario();
		List<Unidad> unidadesContiguas = new ArrayList<Unidad>();
		propietario.agregarUnidadesContiguasAliadas(unSoldado, unidadesContiguas);
		
		obtenerBatallon(unidadesContiguas, unSoldado);
		
	}
	
	private void obtenerDireccionDeMovimiento(SoldadoDeInfanteria unSoldado, Celda celdaDestino) {
		
		direccionDeMovimiento = unSoldado.obtenerCelda().calcularDistanciaAbsoluta(celdaDestino);
		
	}
	
	public void moverBatallon() throws Exception {
		if(existeBatallon == true) {
			
			Iterator<SoldadoDeInfanteria> iteradorSoldadosBatallon = batallon.iterator();
			
			while(iteradorSoldadosBatallon.hasNext()) {
				SoldadoDeInfanteria soldado = iteradorSoldadosBatallon.next();
				soldado.mover(direccionDeMovimiento);
			}
		}
		
	}
	
}
