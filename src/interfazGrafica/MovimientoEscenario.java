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
		//correrEscenario();
	}
	
	public void run() {
		while(true) {
			if(cont%400 == 0) {
				cont = 0;
				this.tuberiaAlta.setLocation(400, this.tuberiaAlta.getY());
				this.tuberiaBaja.setLocation(400, this.tuberiaBaja.getY());
			}
			
			this.tuberiaAlta.setLocation(this.tuberiaAlta.getX() - (cont/100), this.tuberiaAlta.getY());
			this.tuberiaBaja.setLocation(this.tuberiaBaja.getX() - (cont/100), this.tuberiaBaja.getY());
			
			cont++;
			//System.out.println(cont);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.juego.update();
	}
	}
}
