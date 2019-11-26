package Juego;

import Jugadores.Jugador;
import Tablero.Tablero;

public class Juego {

	
	public void CrearPartida() {
		Tablero tablero = new Tablero(20, 20);
		Jugador jugador1, jugador2;
		// Jugador[1] = jugador ganador
		// Jugador[2] = jugador perdedor
		String nombreGanador, nombrePerdedor;
		
		
		Jugador[] jugadores = new Jugador[2]; 
		
		jugador1 = this.crearJugadores();
		jugador2 = this.crearJugadores();
		
		this.compraUnidades(jugador1,jugador2);
		this.ubicarUnidadesEnTablero(jugador1, jugador2);
		
		jugadores = this.partidaEnCurso(jugador1, jugador2, tablero);
		
		nombreGanador = jugadores[1].obtenerPropietario();
		nombrePerdedor = jugadores[2].obtenerPropietario();
		System.out.println("jugador ganador: ");
		System.out.println(nombreGanador);
		
		System.out.println("jugador perdedor: ");
		System.out.println(nombreGanador);
	}
	
	private void ubicarUnidadesEnTablero(Jugador jugador1, Jugador jugador2) {
		
		jugador1.ubicarUnidades();
		jugador2.ubicarUnidades();
	}
	
	private void compraUnidades(Jugador jugador1, Jugador jugador2) {
		jugador1.comparUnidades();
		jugador2.comparUnidades();
	}
	
	
	public Jugador[] partidaEnCurso(Jugador jugador1, Jugador jugador2, Tablero tablero) {
		
		Jugador[] jugadores = new Jugador[2];		
		boolean partidaEnCurso = true;
		
		
		while(partidaEnCurso) {
			jugador1.turnoJugador();
			partidaEnCurso = this.chequearUnidades(jugador1,jugador2);
			if (!partidaEnCurso) {
				jugadores = this.obtenerGanador(jugador1,jugador2);
				break;
			}
			jugador2.turnoJugador();
			partidaEnCurso = this.chequearUnidades(jugador1,jugador2);
			if (!partidaEnCurso) {
				jugadores = this.obtenerGanador(jugador1,jugador2);
			}
		}
		
		
		return jugadores;
	}
	
	private Jugador[] obtenerGanador(Jugador jugador1 , Jugador jugador2 ) {
		Jugador jugadores[] =  new Jugador[2];
		int unidadesJugador1;
		unidadesJugador1 = jugador1.cantidadDeUnidades();
		
		if (unidadesJugador1 == 0) {
			jugadores[1] = jugador2;
			jugadores[2] = jugador1;
		}else {
			jugadores[1] = jugador1;
			jugadores[2] = jugador2;
		}
			
		return jugadores;
	}
	
	
	private boolean chequearUnidades(Jugador jugador1,Jugador jugador2) {
		int unidadesJugador1, unidadesJugador2;
		unidadesJugador1 = jugador1.cantidadDeUnidades();
		unidadesJugador2 = jugador2.cantidadDeUnidades();
		boolean partidaEnCurso = true;
		
		if (unidadesJugador1 == 0) {
			partidaEnCurso = false;
		}
		if (unidadesJugador2 == 0) {
			partidaEnCurso = false;
		}
		return partidaEnCurso;
	}
	
	
	public Jugador crearJugadores() {
		String nombre;
		Jugador unJugador;
		
		System.out.println("Nombre jugador 1");
		// leer por teclado
		nombre="jugador 1";
		unJugador = new Jugador(nombre);
		return unJugador;		 
		
	}
	
	
	
}
