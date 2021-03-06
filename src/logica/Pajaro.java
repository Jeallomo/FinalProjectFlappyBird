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
 * Declaración de clase Pajaro
 * 
 * @author Julian Espinoza
 *
 */
public class Pajaro extends Hilo implements KeyListener {

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
	 * 
	 * @param pajarito
	 * @param juego
	 */
	public Pajaro(JLabel pajarito, EscenarioJuego juego) {
		this.pajarito = pajarito;
		this.juego = juego;
		this.terminar = false;
	}

	// Method for Thread
	/**
	 * Metodo que corre el movimiento del pajaro
	 */
	public void run() {
		while (true) {
			while (!terminar) {
				if (this.ejeDireccion == 1) {
					if (delay) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e1) {
						}
					}
					delay = false;

					if (this.pajarito.getY() + (velocidad / 10 * this.ejeDireccion) <= -150) {
						this.pajarito.setLocation(this.pajarito.getX(), -150);
					} else if (this.pajarito.getY() + (velocidad / 10 * this.ejeDireccion) >= this.juego.getWindowH()
							- 99 - this.juego.getBirdSizeH()) {
						this.pajarito.setLocation(this.pajarito.getX(),
								this.juego.getWindowH() - 99 - this.juego.getBirdSizeH());
					} else {
						this.pajarito.setLocation(this.pajarito.getX(),
								this.pajarito.getY() + (velocidad / 10 * this.ejeDireccion));
					}

					if (this.juego.getActivePJ() == "bird/bird") {
						if (alturaDePaso - this.pajarito.getY() == 0) {
							juego.bird.setIcon(new ImageIcon(juego.imagenBird0.getImage().getScaledInstance(
									juego.getBirdSizeH(), juego.getBirdSizeW() - 10, Image.SCALE_SMOOTH)));
						}
						if (this.pajarito.getY() - alturaDePaso >= 10 & this.pajarito.getY() - alturaDePaso < 30) {
							juego.bird.setIcon(new ImageIcon(juego.imagenBird20.getImage().getScaledInstance(
									juego.getBirdSizeH(), juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
						}
						if (this.pajarito.getY() - alturaDePaso >= 30 & this.pajarito.getY() - alturaDePaso < 50) {
							juego.bird.setIcon(new ImageIcon(juego.imagenBird45.getImage().getScaledInstance(
									juego.getBirdSizeH(), juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
						}
						if (this.pajarito.getY() - alturaDePaso >= 50 & this.pajarito.getY() - alturaDePaso < 70) {
							juego.bird.setIcon(new ImageIcon(juego.imagenBird75.getImage().getScaledInstance(
									juego.getBirdSizeH(), juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
						}
						if (this.pajarito.getY() - alturaDePaso >= 70) {
							juego.bird.setIcon(new ImageIcon(juego.imagenBird90.getImage().getScaledInstance(
									juego.getBirdSizeH() - 5, juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
						}
					}

					if (this.juego.getActivePJ() == "space/space") {
						if (alturaDePaso - this.pajarito.getY() == 0) {
							juego.bird.setIcon(new ImageIcon(juego.imagenSpace0.getImage().getScaledInstance(
									juego.getBirdSizeH(), juego.getBirdSizeW() - 10, Image.SCALE_SMOOTH)));
						}
						if (this.pajarito.getY() - alturaDePaso >= 10 & this.pajarito.getY() - alturaDePaso < 30) {
							juego.bird.setIcon(new ImageIcon(juego.imagenSpace20.getImage().getScaledInstance(
									juego.getBirdSizeH(), juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
						}
						if (this.pajarito.getY() - alturaDePaso >= 30 & this.pajarito.getY() - alturaDePaso < 50) {
							juego.bird.setIcon(new ImageIcon(juego.imagenSpace45.getImage().getScaledInstance(
									juego.getBirdSizeH(), juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
						}
						if (this.pajarito.getY() - alturaDePaso >= 50 & this.pajarito.getY() - alturaDePaso < 70) {
							juego.bird.setIcon(new ImageIcon(juego.imagenSpace75.getImage().getScaledInstance(
									juego.getBirdSizeH(), juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
						}
						if (this.pajarito.getY() - alturaDePaso >= 70) {
							juego.bird.setIcon(new ImageIcon(juego.imagenSpace90.getImage().getScaledInstance(
									juego.getBirdSizeH() - 5, juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
						}
					}

					try {
						Thread.sleep(6);
					} catch (InterruptedException e) {
					}
					velocidad++;
				}
				while (this.ejeDireccion == -1) {
					if (this.StaticY - 45 != this.pajarito.getY()) {
						this.pajarito.setLocation(this.pajarito.getX(), this.pajarito.getY() + (1 * this.ejeDireccion));
						try {
							Thread.sleep(4);
						} catch (InterruptedException e) {
						}
					} else {
						this.ejeDireccion = 1;
					}
				}
				this.juego.update();
			}
			//
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
	}

	// General Methods
	/**
	 * Inicia la inhabilitacion del movimiento del pajaro
	 */
	@Override
	public void pauseThread() {
		this.terminar = true;
	}

	/**
	 * Inicia la habilitacion del movimiento del pajaro
	 */
	@Override
	public void resumeThread() {
		this.terminar = false;
		ejeDireccion = 1;
	}

	// Methods for KeyListener
	@Override
	public void keyPressed(KeyEvent e) {
		this.ejeDireccion = -1;
		this.StaticY = this.pajarito.getY();
		if (this.juego.getActivePJ() == "bird/bird") {
			juego.bird.setIcon(new ImageIcon(juego.imagenBirdArriba.getImage().getScaledInstance(juego.getBirdSizeH(),
					juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
		}
		if (this.juego.getActivePJ() == "space/space") {
			juego.bird.setIcon(new ImageIcon(juego.imagenSpaceArriba.getImage().getScaledInstance(juego.getBirdSizeH(),
					juego.getBirdSizeW(), Image.SCALE_SMOOTH)));
		}

		delay = true;
		alturaDePaso = this.StaticY;
		velocidad = 0;

		try {
			clip = AudioSystem.getClip();
			if (this.juego.getActivePJ() == "bird/bird") {
				clip.open(AudioSystem.getAudioInputStream(getClass().getResource("/audio/effects/jump.wav")));
			}
			if (this.juego.getActivePJ() == "porky/porky") {
				clip.open(AudioSystem.getAudioInputStream(getClass().getResource("/audio/effects/oink.wav")));
			}
			if (this.juego.getActivePJ() == "space/space") {
				clip.open(AudioSystem
						.getAudioInputStream(getClass().getResource("/audio/effects/spaceship.wav")));
			}

			FloatControl volumen = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			volumen.setValue((float) -30.0);
			clip.loop(0);
		} catch (LineUnavailableException e1) {
		} catch (IOException e1) {
		} catch (UnsupportedAudioFileException e1) {
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
