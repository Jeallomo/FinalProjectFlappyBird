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
 * Declaración de clase EscenarioJuego
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
	private JLabel mensajeTitulo;
	private JLabel tuberiaAlta1,tuberiaBaja1,tuberiaAlta2,tuberiaBaja2;
	private JLabel terreno1, terreno2;
	private JLabel lblPuntos;
	private PanelBackground fondo;
	private PanelBackground suelo;
	private JLayeredPane campoJuego;
	private PanelBackground titulo;
	private ImageIcon imagenTuboAlto;
	private ImageIcon imagenTuboBajo;
	private ImageIcon terreno;
	
	public ImageIcon imagenBird0,imagenBirdArriba,imagenBird20,imagenBird45,imagenBird90,imagenBird75;
	public ImageIcon imagenPorky;
	public JLabel bird;
	
	// Attributes
	private int jugando = 0;
	private int puntos = 0;
	private String activePJ = "bird/bird";
	
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
		
		imagenBird0 = new ImageIcon(getClass().getResource("/Imagenes/bird/bird.png"));
		imagenBirdArriba = new ImageIcon(getClass().getResource("/Imagenes/bird/bird20.png"));
		imagenBird20 = new ImageIcon(getClass().getResource("/Imagenes/bird/bird -20.png"));
		imagenBird45 = new ImageIcon(getClass().getResource("/Imagenes/bird/bird -45.png"));
		imagenBird75 = new ImageIcon(getClass().getResource("/Imagenes/bird/bird -75.png"));
		imagenBird90 = new ImageIcon(getClass().getResource("/Imagenes/bird/bird -90.png"));
		imagenPorky = new ImageIcon(getClass().getResource("/Imagenes/porky/porky.png"));
		
		campoJuego = new JLayeredPane();
		campoJuego.setPreferredSize(new Dimension(this.windowW,this.windowH));
		campoJuego.setLayout(null);
		
		fondo = new PanelBackground("/Imagenes/fondo.png");
		fondo.setBounds(0, 0,this.windowW, this.windowH-95);
		campoJuego.add(fondo, new Integer(-1));
		
		suelo = new PanelBackground("/Imagenes/suelo.png");
		suelo.setBounds(0, this.windowH-100,this.windowW, 100);
		campoJuego.add(suelo, new Integer(1));
		
		titulo = new PanelBackground("/Imagenes/fondoTitulo.png");
		titulo.setBounds((this.windowW-((this.windowW*3)/4))/2, 150, (this.windowW*3)/4, 200);
		titulo.setLayout(null);
		campoJuego.add(titulo, new Integer(3));
		
		mensajeTitulo = new JLabel("Presiona una tecla para jugar");
		mensajeTitulo.setBounds(0, (200/2), (this.windowW*3)/4, 30);
		mensajeTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.add(mensajeTitulo);
		
		mejorPuntaje = new JLabel();
		if(this.db.getPuntajes().size() < 1) {
			this.mejorPuntaje.setText("Best: 0");
		} else {
			this.mejorPuntaje.setText("Best: " + this.db.getPuntajes().get(0));
		}
		mejorPuntaje.setBounds(0, 40, (this.windowW*3)/4, 30);
		mejorPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		mejorPuntaje.setFont(new Font(mejorPuntaje.getFont().getFontName(), mejorPuntaje.getFont().getStyle(), 40));
		titulo.add(mejorPuntaje);
		
		imagenTuboAlto = new ImageIcon(getClass().getResource("/Imagenes/tuboArriba.png"));
		imagenTuboBajo = new ImageIcon(getClass().getResource("/Imagenes/tuboAbajo.png"));
		terreno = new ImageIcon(getClass().getResource("/Imagenes/div.png"));
		
		bird = new JLabel();
		bird.setBounds(100, (this.windowH/2)-100-(this.birdSizeH/2), this.birdSizeW,this.birdSizeH);
		bird.setIcon(new ImageIcon(imagenBird0.getImage().getScaledInstance(this.birdSizeH, this.birdSizeW-10, Image.SCALE_SMOOTH)));
		campoJuego.add(bird, new Integer(0));
		
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
	}
	
	// General Methods
	/**
	 * Método repinta la pantalla
	 */
	public void update() {
		lblPuntos.setText("Score: " + this.puntos);
		frame.getContentPane().repaint();
	}
	/**
	 * Método reinicia el juego
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
		campoJuego.add(titulo, new Integer(3));
		
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
	 * Método suma un punto
	 */
	public void addPunto() {
		this.puntos++;
	}
	/**
	 * Método reinicia el puntaje
	 */
	public void resetPuntos() {
		this.puntos = 0;
	}
	/**
	 * Método añade música al juego
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
		if(this.jugando == 1) {
			pelota.velocidad = 0;
			pelota.resumeThread();
			tubos.resumeThread();
			this.update();
			this.jugando = 2;
		}
		if(this.jugando == 0) {
			this.resetPuntos();
			
			if(this.activePJ == "bird/bird") {
				bird.setIcon(new ImageIcon(imagenBird0.getImage().getScaledInstance(this.birdSizeH, this.birdSizeW-10, Image.SCALE_SMOOTH)));
			}
			
			this.tuberiaAlta1.setLocation(500, -350);
			this.tuberiaBaja1.setLocation(500,370);
			this.tuberiaAlta2.setLocation(850, -350);
			this.tuberiaBaja2.setLocation(800,370);
			this.bird.setLocation(100, (this.windowH/2)-100+(this.birdSizeH/2));
			this.update();
			
			campoJuego.remove(titulo);
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

	public JLayeredPane getCampoJuego() {
		return campoJuego;
	}

	public PanelBackground getFondo() {
		return fondo;
	}

	public void setFondo(PanelBackground fondo) {
		this.fondo = fondo;
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

	public void setActivePJ(String activePJ) {
		this.activePJ = activePJ;
	}

	public ImageIcon getImagenPorky() {
		return imagenPorky;
	}

	public String getActivePJ() {
		return activePJ;
	}

	public JLabel getLblPuntos() {
		return lblPuntos;
	}
	
	
}
