package logica;

import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;

import interfazGrafica.EscenarioJuego;

public class Colisionador extends Thread{
	//Attributes
	private Clip clip;
	
	//Components
	private JLabel bird;
	private JLabel tb1;
	private JLabel tb2;
	private JLabel tb3;
	private JLabel tb4;
	private JLabel t1;
	private JLabel t2;
	private EscenarioJuego ej;
	private boolean terminar;
	
	//Construct
	public Colisionador(JLabel bird, JLabel tb1, JLabel tb2, JLabel tb3, JLabel tb4, EscenarioJuego ej, JLabel t1, JLabel t2) {
		this.bird = bird;
		this.tb1 = tb1;
		this.tb2 = tb2;
		this.tb3 = tb3;
		this.tb4 = tb4;
		this.t1 = t1;
		this.t2 = t2;
		this.ej = ej;
		this.terminar = false;
	}
	
	//Methods
	public void run() {
		while(true) {
			while(!terminar) {
				if(this.bird.getBounds().intersects(this.tb1.getBounds()) || this.bird.getBounds().intersects(this.tb2.getBounds())
						|| this.bird.getBounds().intersects(this.tb3.getBounds()) || this.bird.getBounds().intersects(this.tb4.getBounds())
						|| this.bird.getBounds().intersects(this.t1.getBounds()) || this.bird.getBounds().intersects(this.t2.getBounds())) {
					
					//Reproducir sonido de golpe
					try {
						clip = AudioSystem.getClip();
						clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/effects/hit.wav")));
						FloatControl volumen = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
						volumen.setValue((float) -30.0);
						clip.loop(0);
					} catch (LineUnavailableException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnsupportedAudioFileException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					this.ej.reset();
					
					terminar = true;
				}
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {	}
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {	}
		}
	}
	
	public void resumeThread() {
		terminar = false;
	}
}
