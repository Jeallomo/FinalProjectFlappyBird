package interfazGrafica;

import javax.swing.JLabel;


public class MovimientoEscenario extends Thread {

	private JLabel tuberiaAlta1,tuberiaBaja1,tuberiaAlta2,tuberiaBaja2;
	
	private EscenarioJuego juego;
	
	private int ejeDireccion = 1;
	private int reubicacionTubos1 = 0,reubicacionTubos2 = -250;
	
	
	public MovimientoEscenario(JLabel tuberiaAlta1,JLabel tuberiaBaja1,JLabel tuberiaAlta2,JLabel tuberiaBaja2,EscenarioJuego juego) {
		this.tuberiaAlta1 = tuberiaAlta1;
		this.tuberiaBaja1 = tuberiaBaja1;
		this.tuberiaAlta2 = tuberiaAlta2;
		this.tuberiaBaja2 = tuberiaBaja2;
		this.juego = juego;
		//correrEscenario();
	}
	
	public void run() {
		while(true) {
			if(reubicacionTubos1%500 == 0) {
				reubicacionTubos1 = 0;
				this.tuberiaAlta1.setLocation(400, this.tuberiaAlta1.getY());
				this.tuberiaBaja1.setLocation(400, this.tuberiaBaja1.getY());
				
			}
			if(reubicacionTubos2%500 == 0) {
				reubicacionTubos2 = 0;
				
				this.tuberiaAlta2.setLocation(400, this.tuberiaAlta1.getY());
				this.tuberiaBaja2.setLocation(400, this.tuberiaBaja1.getY());
			}
			
			this.tuberiaAlta1.setLocation(this.tuberiaAlta1.getX() - 1, this.tuberiaAlta1.getY());
			this.tuberiaBaja1.setLocation(this.tuberiaBaja1.getX() - 1, this.tuberiaBaja1.getY());
			this.tuberiaAlta2.setLocation(this.tuberiaAlta2.getX() - 1, this.tuberiaAlta2.getY());
			this.tuberiaBaja2.setLocation(this.tuberiaBaja2.getX() - 1, this.tuberiaBaja2.getY());
			
			reubicacionTubos1++;
			reubicacionTubos2++;
			
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.juego.update();
	}
	}
}
