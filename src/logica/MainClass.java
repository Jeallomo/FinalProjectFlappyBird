package logica;

import java.io.IOException;

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
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Puntaje db = new Puntaje();

		@SuppressWarnings("unused")
		EscenarioJuego juego = new EscenarioJuego(db);
	}
}