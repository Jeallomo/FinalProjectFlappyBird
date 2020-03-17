package logica;

import javax.swing.JLabel;

import interfazGrafica.EscenarioJuego;

public class PelotaQueSeMueve extends Thread {
	//Attributes
	private int ejeDireccion = 1;
	private int cont = 0;
	
	//Objects
	private JLabel pajarito;
	private EscenarioJuego juego;

	public PelotaQueSeMueve(JLabel pajarito, EscenarioJuego juego){
		this.pajarito = pajarito;
		this.juego = juego;
	}

	public void run() {
		while(true) {
			if(cont%10 == 0) {
				this.ejeDireccion*=-1;
			}
			
			this.pajarito.setLocation(this.pajarito.getX() + (10*this.ejeDireccion), this.pajarito.getY());
			
			cont++;
			System.out.println(cont);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.juego.update();
		}
	}
}
