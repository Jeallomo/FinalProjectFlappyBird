package logica;

import javax.swing.JLabel;

import interfazGrafica.EscenarioJuego;

public class MovimientoTerreno extends Thread {
	//Attributes
	private JLabel terreno1;
	private JLabel terreno2;
	private EscenarioJuego juego;
	private int reubicacionTerreno1 = -500;
	private int reubicacionTerreno2 = 0;
	private boolean terminar;
	
	
	public MovimientoTerreno(JLabel terreno1,JLabel terreno2, EscenarioJuego juego) {
		this.terreno1 = terreno1;
		this.terreno2 = terreno2;
		this.juego = juego;
		this.terminar = false;
	}
	
	public void run() {
		while(true) {
			while(!terminar) {
				if(reubicacionTerreno1 == -1000) {
					reubicacionTerreno1 = 0;
					terreno1.setLocation(500, terreno1.getY());
				}
				if(reubicacionTerreno2 == -1000) {
					reubicacionTerreno2 = 0;
					terreno2.setLocation(500, terreno2.getY());
				}
				
				terreno1.setLocation(terreno1.getX()-1, terreno1.getY());
				terreno2.setLocation(terreno2.getX()-1, terreno2.getY());
				
				reubicacionTerreno1--;
				reubicacionTerreno2--;
				
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {	}
				
				this.juego.update();
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void pauseThread() {
		this.terminar = true;
	}
	
	public void resumeThread() {
		reubicacionTerreno1 = -500;
		reubicacionTerreno2 = 0;
		terreno1.setLocation(0, terreno1.getY());
		terreno2.setLocation(500, terreno2.getY());
		this.terminar = false;
	}
}
