package logica;

import interfazGrafica.EscenarioJuego;
import modelo.Puntaje;

public class MainClass {
	public static void main(String[] args) {
		Puntaje db = new Puntaje();
		
		@SuppressWarnings("unused")
		EscenarioJuego juego = new EscenarioJuego(db);
		
	}
}
