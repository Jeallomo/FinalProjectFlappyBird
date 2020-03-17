package logica;

import javax.swing.JLabel;

import interfazGrafica.EscenarioJuego;

public class PelotaQueSeMueve extends Thread {
	//Attributes
	private int ejeDireccion = 1;
	
	//Objects
	private JLabel pajarito;
	private EscenarioJuego juego;

	public PelotaQueSeMueve(JLabel pajarito, EscenarioJuego juego){
		this.pajarito = pajarito;
		this.juego = juego;
	}

	public void run() {
		while(true) {
			
			if(this.pajarito.getX() + (10*this.ejeDireccion) <= 0) {
				this.pajarito.setLocation(0, this.pajarito.getY());
				this.ejeDireccion*=-1;
			} else if(this.pajarito.getX() + (10*this.ejeDireccion) >= this.juego.getWindowW()) {
				this.pajarito.setLocation(this.juego.getWindowW(), this.pajarito.getY());
				this.ejeDireccion*=-1;
			} else {
				this.pajarito.setLocation(this.pajarito.getX() + (10*this.ejeDireccion), this.pajarito.getY());
			}
			
			//this.pajarito.setLocation(this.pajarito.getX(), this.pajarito.getY() + 10);
			
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.juego.update();
		}
	}
}
