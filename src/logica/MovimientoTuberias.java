package logica;

import javax.swing.JLabel;

import interfazGrafica.EscenarioJuego;


/**
 * Declaración de clase Movimiento Tuverias
 * @author Santiago Herrera
 * @version
 *
 */
public class MovimientoTuberias extends Hilo{

	//Attributes
	private JLabel tuberiaAlta1,tuberiaBaja1,tuberiaAlta2,tuberiaBaja2;
	private EscenarioJuego juego;
	private int reubicacionTubos1 = 0,reubicacionTubos2 = -300;
	private boolean terminar;
	
	/**
	 * Constructor de MovimientoTuberias
	 * @param tuberiaAlta1
	 * @param tuberiaBaja1
	 * @param tuberiaAlta2
	 * @param tuberiaBaja2
	 * @param juego
	 */
	public MovimientoTuberias(JLabel tuberiaAlta1,JLabel tuberiaBaja1,JLabel tuberiaAlta2,JLabel tuberiaBaja2,EscenarioJuego juego) {
		this.tuberiaAlta1 = tuberiaAlta1;
		this.tuberiaBaja1 = tuberiaBaja1;
		this.tuberiaAlta2 = tuberiaAlta2;
		this.tuberiaBaja2 = tuberiaBaja2;
		this.juego = juego;
		this.terminar = false;
	}
	/**
	 * Método corre el movimiento de las tuberias
	 */
	public void run() {
		while(true) {
			while(!terminar) {
				if(reubicacionTubos1%600 == 0) {
					int altura = (int)(Math.random()*30-18);
					reubicacionTubos1 = 0;
					this.tuberiaAlta1.setLocation(500, -350+altura*10);
					this.tuberiaBaja1.setLocation(500, 370+altura*10);
					
				}
				if(reubicacionTubos2%600== 0) {
					reubicacionTubos2 = 0;
					int altura = (int)(Math.random()*30-18);
					this.tuberiaAlta2.setLocation(500, -350+altura*10);
					this.tuberiaBaja2.setLocation(500, 370+altura*10);
				}
				
				this.tuberiaAlta1.setLocation(this.tuberiaAlta1.getX() - 1, this.tuberiaAlta1.getY());
				this.tuberiaBaja1.setLocation(this.tuberiaBaja1.getX() - 1, this.tuberiaBaja1.getY());
				this.tuberiaAlta2.setLocation(this.tuberiaAlta2.getX() - 1, this.tuberiaAlta2.getY());
				this.tuberiaBaja2.setLocation(this.tuberiaBaja2.getX() - 1, this.tuberiaBaja2.getY());
				
				if(this.tuberiaAlta1.getX() + 100 == this.juego.getBird().getX()) {
					this.juego.addPunto();
				}
				if(this.tuberiaAlta2.getX() + 100 == this.juego.getBird().getX()) {
					this.juego.addPunto();
				}
				
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
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Inicia la inhabilitacion del movimiento de las tuberias
	 */
	public void pauseThread() {
		this.terminar = true;
	}
	/**
	 * Inicia la habilitacion del movimiento de las tuberias
	 */
	public void resumeThread() {
		reubicacionTubos1 = 0;
		reubicacionTubos2 = -300;
		this.terminar = false;
	}
}
