package Unidades;

import Excepciones.SoldadoDeInfanteriaSoloAtacaACortaDistancia;
import Tablero.Celda;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class SoldadoDeInfanteria extends UnidadAtacante {
		
		public SoldadoDeInfanteria() {
			
			vidaMax = 100;
			costo = 1;
			vida = 100;
			danioCuerpoAcuerpo = 10;
			danioADistancia = 0; 
			movilidad = new unidadMovil();
		
		}
		
		public void realizarComportamiento(Unidad unaUnidad) {
			chequearAtaqueAUnidadEnemiga(unaUnidad);
			int distancia = this.calcularDistancia(unaUnidad);
			if (distancia > 2) {
				throw new SoldadoDeInfanteriaSoloAtacaACortaDistancia();
			}
			unaUnidad.restarVida(danioCuerpoAcuerpo);
		}
		
		public boolean esBatallon(SoldadoDeInfanteria soldado2, SoldadoDeInfanteria soldado3) {
			boolean esBatallon = false;
			
			if(this.obtenerCelda().tresCeldasUnidas(soldado2.obtenerCelda(), soldado3.obtenerCelda())) {
				esBatallon = true;
			}
			
			return esBatallon;	
		}
		
		public void moverBatallon(SoldadoDeInfanteria soldado2, SoldadoDeInfanteria soldado3,Celda destino1, Celda destino2, Celda destino3) throws Exception {
			
			boolean esBatallon = this.esBatallon(soldado2, soldado3);
			boolean tresCeldasUnidas = destino1.tresCeldasUnidas(destino2, destino3);
			
			if(esBatallon && tresCeldasUnidas) {
				this.mover(destino1);
				soldado2.mover(destino2);
				soldado3.mover(destino3);
			}
			
		}
		
		
}
