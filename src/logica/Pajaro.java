package logica;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import interfazGrafica.EscenarioJuego;

/**
 * Declaraci�n de clase Pajaro
 * @author Julian Espinoza
 * @version
 *
 */
public class Pajaro extends Thread implements KeyListener {

	// Objects
	private JLabel pajarito;
	private EscenarioJuego juego;
	private Clip clip;
	
	// Attributes
	private int ejeDireccion = 1;
	private int StaticY;
	private boolean terminar;
	private boolean delay = false;
	private int alturaDePaso = 150;
    public int velocidad = 0;
	
	// Construct
    /**
     * Constructor Pajaro
     * @param pajarito
     * @param juego
     */
	public Pajaro(JLabel pajarito, EscenarioJuego juego) {
		this.pajarito = pajarito;
		this.juego = juego;
		this.terminar = false;
		
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/effects/jump.wav")));
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Method for Thread
	/**
	 * M�todo corre el movimiento del pajaro
	 */
	public void run() {
		 while(true) {
			 while (!terminar) {
				if(this.ejeDireccion == 1) {
					if(delay) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					delay = false;
					
					if(this.pajarito.getY() + (velocidad/10 * this.ejeDireccion) <= -150){
						this.pajarito.setLocation(this.pajarito.getX(), -150);
					} else if(this.pajarito.getY() + (velocidad/10 * this.ejeDireccion) >= this.juego.getWindowH()-99-this.juego.getBirdSizeH()){
						this.pajarito.setLocation(this.pajarito.getX(), this.juego.getWindowH()-99-this.juego.getBirdSizeH());
					} else {
						this.pajarito.setLocation(this.pajarito.getX(), this.pajarito.getY() + (velocidad/10 * this.ejeDireccion));
					}
					
					if(this.juego.getActivePJ() == "bird/bird") {
						if(alturaDePaso - this.pajarito.getY() == 0) {
							juego.bird.setIcon(new ImageIcon(juego.imagenBird0.getImage().getScaledInstance(juego.getBirdSizeH(), juego.getBirdSizeW()-10, Image.SCALE_SMOOTH)));
						}
						if(this.pajarito.getY()- alturaDePaso  >= 10 & this.pajarito.getY()- alturaDePaso < 30) {
							juego.bird.setIcon(new ImageIcon(juego.imagenBird20.getImage().getScaledInstance(juego.getBirdSizeH(), juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
						}
						if(this.pajarito.getY() - alturaDePaso >= 30 & this.pajarito.getY() - alturaDePaso < 50) {
							juego.bird.setIcon(new ImageIcon(juego.imagenBird45.getImage().getScaledInstance(juego.getBirdSizeH(), juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
						}
						if(this.pajarito.getY() - alturaDePaso >= 50 & this.pajarito.getY() - alturaDePaso < 70) {
							juego.bird.setIcon(new ImageIcon(juego.imagenBird75.getImage().getScaledInstance(juego.getBirdSizeH(), juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
						}
						if( this.pajarito.getY() - alturaDePaso >=   70) {
							juego.bird.setIcon(new ImageIcon(juego.imagenBird90.getImage().getScaledInstance(juego.getBirdSizeH()-5, juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
						}
					}
					
					try {
						Thread.sleep(6);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					velocidad++;
				}
				while(this.ejeDireccion == -1) {
					if(this.StaticY-45 != this.pajarito.getY()) {
						this.pajarito.setLocation(this.pajarito.getX(), this.pajarito.getY() + (1 * this.ejeDireccion));
						try {
							Thread.sleep(4);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					else {
						this.ejeDireccion = 1;
					}
				}
				this.juego.update();
			 }
			//  
			 try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 }
	}
	
	//General Methods
	/**
	 * Inicia la inhabilitacion del movimiento del pajaro
	 */
	public void pauseThread() {
		this.terminar = true;
	}
	/**
	 * Inicia la habilitacion del movimiento del pajaro
	 */
	public void resumeThread() {
		this.terminar = false;
		ejeDireccion = 1;
	}
	
	// Methods for KeyListener
	@Override
	public void keyPressed(KeyEvent e) {
		this.ejeDireccion = -1;
		this.StaticY = this.pajarito.getY();
		if(this.juego.getActivePJ() == "bird/bird") {
			juego.bird.setIcon(new ImageIcon(juego.imagenBirdArriba.getImage().getScaledInstance(juego.getBirdSizeH(), juego.getBirdSizeW(), Image.SCALE_SMOOTH)));	
		}

		delay = true;
		alturaDePaso = this.StaticY;
		velocidad = 0;

		try {
			clip = AudioSystem.getClip();
			if(this.juego.getActivePJ() == "bird/bird") {
				clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/effects/jump.wav")));
			}
			if(this.juego.getActivePJ() == "porky/porky") {
				clip.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/effects/oink.wav")));
			}
			
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
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}
}
