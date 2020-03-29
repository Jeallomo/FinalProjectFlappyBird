package logica;

import interfazGrafica.EscenarioJuego;
import modelo.Puntaje;

/**
 * Declaracion clase MainClass
 * 
 * @author Julian Espinoza
 *
 */
public class MainClass {
	/**
	 * Metodo main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Puntaje db = new Puntaje();

		@SuppressWarnings("unused")
		EscenarioJuego juego = new EscenarioJuego(db);
	}
}