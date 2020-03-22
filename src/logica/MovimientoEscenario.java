package logica;

import javax.swing.JLabel;

import interfazGrafica.EscenarioJuego;



public class MovimientoEscenario extends Thread {

	//Attributes
	private JLabel tuberiaAlta1,tuberiaBaja1,tuberiaAlta2,tuberiaBaja2;
	private EscenarioJuego juego;
	private int reubicacionTubos1 = 0,reubicacionTubos2 = -250;
	private boolean terminar;
	
	
	public MovimientoEscenario(JLabel tuberiaAlta1,JLabel tuberiaBaja1,JLabel tuberiaAlta2,JLabel tuberiaBaja2,EscenarioJuego juego) {
		this.tuberiaAlta1 = tuberiaAlta1;
		this.tuberiaBaja1 = tuberiaBaja1;
		this.tuberiaAlta2 = tuberiaAlta2;
		this.tuberiaBaja2 = tuberiaBaja2;
		this.juego = juego;
		this.terminar = false;
	}
	
	public void run() {
		while(!terminar) {
			if(reubicacionTubos1%500 == 0) {
				int altura = (int)(Math.random()*40-20);
				System.out.println(altura);
				reubicacionTubos1 = 0;
				this.tuberiaAlta1.setLocation(400, -350+altura*10);
				this.tuberiaBaja1.setLocation(400, 370+altura*10);
				
			}
			if(reubicacionTubos2%500== 0) {
				reubicacionTubos2 = 0;
				int altura = (int)(Math.random()*40-20);
				System.out.println(altura);
				this.tuberiaAlta2.setLocation(400, -350+altura*10);
				this.tuberiaBaja2.setLocation(400, 370+altura*10);
			}
			
			this.tuberiaAlta1.setLocation(this.tuberiaAlta1.getX() - 1, this.tuberiaAlta1.getY());
			this.tuberiaBaja1.setLocation(this.tuberiaBaja1.getX() - 1, this.tuberiaBaja1.getY());
			this.tuberiaAlta2.setLocation(this.tuberiaAlta2.getX() - 1, this.tuberiaAlta2.getY());
			this.tuberiaBaja2.setLocation(this.tuberiaBaja2.getX() - 1, this.tuberiaBaja2.getY());
			
			reubicacionTubos1++;
			reubicacionTubos2++;
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			this.juego.update();
		}
	}
	
	public void endThread() {
		this.terminar = true;
	}
}
