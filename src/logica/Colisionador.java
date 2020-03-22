package logica;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Colisionador extends Thread{
	private JLabel bird;
	private JLabel tb1;
	private JLabel tb2;
	private JLabel tb3;
	private JLabel tb4;
	
	//Construct
	public Colisionador(JLabel bird, JLabel tb1, JLabel tb2, JLabel tb3, JLabel tb4) {
		this.bird = bird;
		this.tb1 = tb1;
		this.tb2 = tb2;
		this.tb3 = tb3;
		this.tb4 = tb4;
	}
	
	//Methods
	public void run() {
		while(true) {
			if(this.bird.getBounds().intersects(this.tb1.getBounds()) || this.bird.getBounds().intersects(this.tb2.getBounds())
					|| this.bird.getBounds().intersects(this.tb3.getBounds()) || this.bird.getBounds().intersects(this.tb4.getBounds())) {
				JOptionPane.showMessageDialog(null, "Perdiste!");
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {	}
		}
	}
}
