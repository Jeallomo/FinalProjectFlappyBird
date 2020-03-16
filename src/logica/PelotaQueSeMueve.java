package logica;

import javax.swing.JLabel;

public class PelotaQueSeMueve extends Thread {
	//Attributes
	private int ejeDireccion = 1;
	private int cont = 0;
	
	//Objects
	private JLabel pajarito;

	PelotaQueSeMueve(JLabel pajarito){
		this.pajarito = pajarito;
	}
	
	public void run() {
		if(cont%10 == 0) {
			this.ejeDireccion*=-1;
		}
		
		this.pajarito.setLocation(10*this.ejeDireccion, this.pajarito.getY());
		
		cont++;
	}
}
