package interfazGrafica;

import java.awt.Dimension;

import java.awt.Font;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

import logica.CodeListener;
import logica.Colisionador;
import logica.MovimientoTerreno;
import logica.MovimientoTuberias;
import logica.Pajaro;
import logica.PanelBackground;
import modelo.Puntaje;

/**
 * Declaraci�n de clase EscenarioJuego
 * @author Jesus Lozada.
 * @version 
 *
 */
public class EscenarioJuego implements KeyListener{
	// Objects
	private Pajaro pelota;
	private MovimientoTuberias tubos;
	private Colisionador col;
	private CodeListener cod;
	private MovimientoTerreno terrenos;
	private Puntaje db;
	private Clip music;
	
	// Components
	private JFrame frame;
	private JLabel mejorPuntaje;
	private JLabel tuberiaAlta1,tuberiaBaja1,tuberiaAlta2,tuberiaBaja2;
	private JLabel terreno1, terreno2;
	private JLabel lblPuntos;
	private PanelBackground fondo;
	private PanelBackground suelo;
	private PanelMenuPrincipal menuPrincipal;
	private JLayeredPane campoJuego;
	private ImageIcon imagenTuboAlto;
	private ImageIcon imagenTuboBajo;
	private ImageIcon terreno;
	
	public ImageIcon imagenBird0,imagenBirdArriba,imagenBird20,imagenBird45,imagenBird90,imagenBird75;
	public JLabel bird;
	
	// Attributes
	private int jugando = 0;
	private int puntos = 0;
	
	// Constants
	private final int windowH = 600;
	private final int windowW = 500;
	private final int birdSizeW = 40;
	private final int birdSizeH = 40;

	// Construct
	/**
	 * Constructor de ventana principal
	 * @param db
	 */
	public EscenarioJuego(Puntaje db) {
		this.db = db;
		
		frame = new JFrame("Flappy Bird");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(windowW,windowH);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		
		imagenBird0 = new ImageIcon(getClass().getResource("/Imagenes/bird.png"));
		imagenBirdArriba = new ImageIcon(getClass().getResource("/Imagenes/bird20.png"));
		imagenBird20 = new ImageIcon(getClass().getResource("/Imagenes/bird -20.png"));
		imagenBird45 = new ImageIcon(getClass().getResource("/Imagenes/bird -45.png"));
		imagenBird75 = new ImageIcon(getClass().getResource("/Imagenes/bird -75.png"));
		imagenBird90 = new ImageIcon(getClass().getResource("/Imagenes/bird -90.png"));
		
		campoJuego = new JLayeredPane();
		campoJuego.setPreferredSize(new Dimension(this.windowW,this.windowH));
		campoJuego.setLayout(null);
		
		//Principal menu
		fondo = new PanelBackground("/Imagenes/fondo.png");
		fondo.setBounds(0, 0,this.windowW, this.windowH-95);
		campoJuego.add(fondo, new Integer(-1));
		
		menuPrincipal = new PanelMenuPrincipal(this);
		menuPrincipal.setLocation(0,100);
		campoJuego.add(menuPrincipal);
		
		mejorPuntaje = new JLabel();
		if(this.db.getPuntajes().size() < 1) {
			this.mejorPuntaje.setText("Best: 0");
		} else {
			this.mejorPuntaje.setText("Best: " + this.db.getPuntajes().get(0));
		}
		mejorPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		mejorPuntaje.setFont(new Font("Agency FB", mejorPuntaje.getFont().getStyle(), 100));
		menuPrincipal.getPaneles()[1].add(mejorPuntaje);
		
		//Game
		suelo = new PanelBackground("/Imagenes/suelo.png");
		suelo.setBounds(0, this.windowH-100,this.windowW, 100);
		campoJuego.add(suelo, new Integer(1));
		
		imagenTuboAlto = new ImageIcon(getClass().getResource("/Imagenes/tuboArriba.png"));
		imagenTuboBajo = new ImageIcon(getClass().getResource("/Imagenes/tuboAbajo.png"));
		terreno = new ImageIcon(getClass().getResource("/Imagenes/div.png"));
		
		bird = new JLabel();
		bird.setBounds(100, (this.windowH/2)-100-(this.birdSizeH/2), this.birdSizeW,this.birdSizeH);
		bird.setIcon(new ImageIcon(imagenBird0.getImage().getScaledInstance(this.birdSizeH, this.birdSizeW-10, Image.SCALE_SMOOTH)));
		campoJuego.add(bird, new Integer(0));
		bird.setVisible(false);
		
		tuberiaAlta1 = new JLabel();
		tuberiaAlta1.setBounds(500, -350, 100, 600);
		tuberiaAlta1.setIcon(new ImageIcon(imagenTuboBajo.getImage().getScaledInstance(100, 600, Image.SCALE_SMOOTH)));
		campoJuego.add(tuberiaAlta1, new Integer(0));
		
		tuberiaBaja1 = new JLabel();
		tuberiaBaja1.setBounds(500,370, 100, 600);
		tuberiaBaja1.setIcon(new ImageIcon(imagenTuboAlto.getImage().getScaledInstance(100, 600, Image.SCALE_SMOOTH)));
		campoJuego.add(tuberiaBaja1, new Integer(0));
		
		tuberiaAlta2 = new JLabel();
		tuberiaAlta2.setBounds(850, -350, 100, 600);
		tuberiaAlta2.setIcon(new ImageIcon(imagenTuboAlto.getImage().getScaledInstance(100, 600, Image.SCALE_SMOOTH)));
		campoJuego.add(tuberiaAlta2, new Integer(0));
		
		tuberiaBaja2 = new JLabel();
		tuberiaBaja2.setBounds(800,370, 100, 600);
		tuberiaBaja2.setIcon(new ImageIcon(imagenTuboBajo.getImage().getScaledInstance(100, 600, Image.SCALE_SMOOTH)));
		campoJuego.add(tuberiaBaja2, new Integer(0));
		
		terreno1 = new JLabel();
		terreno1.setBounds(0, windowH-100, this.windowW, 20);
		terreno1.setIcon(new ImageIcon(terreno.getImage()));
		campoJuego.add(terreno1, new Integer(2));
		
		terreno2 = new JLabel();
		terreno2.setBounds(500, windowH-100, this.windowW, 20);
		terreno2.setIcon(new ImageIcon(terreno.getImage()));
		campoJuego.add(terreno2, new Integer(2));
		
		lblPuntos = new JLabel("Score: " + this.puntos);
		lblPuntos.setBounds(15, 10, this.windowW, 20);
		campoJuego.add(lblPuntos, new Integer(2));
		lblPuntos.setVisible(false);
		
		pelota = new Pajaro(bird, this);
		tubos = new MovimientoTuberias(tuberiaBaja1,tuberiaAlta1,tuberiaAlta2,tuberiaBaja2,this);
		col = new Colisionador(bird,tuberiaBaja1,tuberiaAlta1,tuberiaAlta2,tuberiaBaja2, this, terreno1, terreno2);
		terrenos = new MovimientoTerreno(terreno1, terreno2, this);
		cod = new CodeListener(this);
		
		pelota.start();
		pelota.pauseThread();
		tubos.start();
		tubos.pauseThread();
		terrenos.start();
		terrenos.pauseThread();
		col.start();
		cod.start();
		
		frame.addKeyListener(this);
		
		frame.add(campoJuego);
		frame.setSize(this.windowW, windowH);
		frame.setVisible(true);
		frame.setResizable(false);
		
		this.update();
		
		//Audio
		try {
			Clip music = AudioSystem.getClip();
			music.open(AudioSystem.getAudioInputStream(getClass().getResource("/audio/Childs Nightmare.wav")));
			
			//Volumen
			FloatControl volumen = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
			volumen.setValue((float) -45);
			
			music.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (LineUnavailableException e) {} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}

	}
	
	// General Methods
	/**
	 * M�todo repinta la pantalla
	 */
	public void update() {
		lblPuntos.setText("Score: " + this.puntos);
		frame.getContentPane().repaint();
	}
	/**
	 * M�todo reinicia el juego
	 */
	public void reset() {

		this.db.addPuntos(this.puntos);
		this.db.ordenarPuntos();
		
		if(this.db.getPuntajes().size() < 1) {
			this.mejorPuntaje.setText("Best: 0");
		} else {
			this.mejorPuntaje.setText("Best: " + this.db.getPuntajes().get(0));
		}
		
		frame.removeKeyListener(pelota);
		frame.removeKeyListener(cod);
		tubos.pauseThread();
		terrenos.pauseThread();
		menuPrincipal.setVisible(true);
		lblPuntos.setVisible(false);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {	}
		
		try {
			music = AudioSystem.getClip();
			music.open(AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/audio/effects/game over.wav")));
			FloatControl volumen = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
			volumen.setValue((float) -30.0);
			music.loop(0);
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
		
		while(bird.getY() <= windowH-100-birdSizeH) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {	}
		}
		
		pelota.pauseThread();
		
		this.jugando = 0;
	}
	/**
	 * M�todo suma un punto
	 */
	public void addPunto() {
		this.puntos++;
	}
	/**
	 * M�todo reinicia el puntaje
	 */
	public void resetPuntos() {
		this.puntos = 0;
	}
	/**
	 * M�todo a�ade m�sica al juego
	 */
	public void addMusic() {
		try {
			music = AudioSystem.getClip();
			music.open(AudioSystem.getAudioInputStream(getClass().getResource("/audio/Childs Nightmare.wav")));
			
			//Volumen
			FloatControl volumen = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
			volumen.setValue((float) -45.0);
			
			music.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (LineUnavailableException e) {} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	// Methods for KeyListener
	
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			
			frame.setVisible(false);
			frame.dispose();
			System.exit(0);
			
		}

		if(this.jugando == 1) {
			pelota.velocidad = 0;
			pelota.resumeThread();
			tubos.resumeThread();
			this.update();
			this.jugando = 2;
		}
		if(this.jugando == 0) {
			this.resetPuntos();
			
			bird.setIcon(new ImageIcon(imagenBird0.getImage().getScaledInstance(this.birdSizeH, this.birdSizeW-10, Image.SCALE_SMOOTH)));
			
			this.tuberiaAlta1.setLocation(500, -350);
			this.tuberiaBaja1.setLocation(500,370);
			this.tuberiaAlta2.setLocation(850, -350);
			this.tuberiaBaja2.setLocation(800,370);
			this.bird.setLocation(100, (this.windowH/2)-100+(this.birdSizeH/2));
			this.update();

			pelota.velocidad = 0;
			
			bird.setVisible(true);
			menuPrincipal.setVisible(false);
			lblPuntos.setVisible(true);
			
			this.db.addPuntos(this.puntos);
			this.db.ordenarPuntos();
			
			this.resetPuntos();
			
			if(this.db.getPuntajes().size() < 1) {
				this.mejorPuntaje.setText("Best: 0");
			} else {
				this.mejorPuntaje.setText("Best: " + this.db.getPuntajes().get(0));
			}
			
			frame.addKeyListener(pelota);
			frame.addKeyListener(cod);
			
			
			col.resumeThread();
			terrenos.resumeThread();
			
			this.jugando = 1;
			this.update();
			this.addMusic();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	// Getters
	public int getWindowH() {
		return windowH;
	}

	public int getWindowW() {
		return windowW;
	}

	public int getBirdSizeW() {
		return birdSizeW;
	}
	
	public int getBirdSizeH() {
		return birdSizeH;
	}

	public JLayeredPane getCampoBola() {
		return campoJuego;
	}

	public Pajaro getPelota() {
		return pelota;
	}

	public MovimientoTuberias getTubos() {
		return tubos;
	}

	public JLabel getBird() {
		return bird;
	}
}
