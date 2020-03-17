package interfazGrafica;

import javax.swing.JLabel;


public class MovimientoEscenario extends Thread {

	private JLabel tuberiaAlta,tuberiaBaja;
	
	private EscenarioJuego juego;
	
	private int ejeDireccion = 1;
	private int cont = 0;
	
	public MovimientoEscenario(JLabel tuberiaAlta,JLabel tuberiaBaja,EscenarioJuego juego) {
		this.tuberiaAlta = tuberiaAlta;
		this.tuberiaBaja = tuberiaBaja;
		this.juego = juego;
	}
	
	public void correrEscenario() {
		while(true) {
			if(cont%10 == 0) {
				this.ejeDireccion*=-1;
			}
			
			this.tuberiaAlta.setLocation(this.tuberiaAlta.getX() + (10*this.ejeDireccion), this.tuberiaAlta.getY());
			this.tuberiaBaja.setLocation(this.tuberiaBaja.getX() + (10*this.ejeDireccion), this.tuberiaBaja.getY());
			
			cont++;
			//System.out.println(cont);
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
