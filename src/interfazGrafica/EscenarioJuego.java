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
 *
 */
@SuppressWarnings("serial")
public class EscenarioJuego extends javax.swing.JFrame implements KeyListener{
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
	private PopUpInfo info;
	private JLabel puntajeActual;
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
	private ImageIcon imagenAsteroideAlto;
	private ImageIcon imagenAsteroideBajo;
	private ImageIcon terreno;
	
	public ImageIcon imagenBird0,imagenBirdArriba,imagenBird20,imagenBird45,imagenBird90,imagenBird75;
	public ImageIcon imagenSpace0,imagenSpaceArriba,imagenSpace20,imagenSpace45,imagenSpace90,imagenSpace75;
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
	public EscenarioJuego(Puntaje db) throws IOException {
		
		
		this.db = db;
		
		frame = new JFrame("Flappy Bird");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(windowW,windowH);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		
		imagenBird0 = new ImageIcon(getClass().getResource("/Imagenes/bird/bird.png"));
		imagenBirdArriba = new ImageIcon(getClass().getResource("/Imagenes/bird/bird20.png"));
		imagenBird20 = new ImageIcon(getClass().getResource("/Imagenes/bird/bird -20.png"));
		imagenBird45 = new ImageIcon(getClass().getResource("/Imagenes/bird/bird -45.png"));
		imagenBird75 = new ImageIcon(getClass().getResource("/Imagenes/bird/bird -75.png"));
		imagenBird90 = new ImageIcon(getClass().getResource("/Imagenes/bird/bird -90.png"));
		
		imagenSpace0 = new ImageIcon(getClass().getResource("/Imagenes/space/space 0.png"));
		imagenSpaceArriba = new ImageIcon(getClass().getResource("/Imagenes/space/space 20.png"));
		imagenSpace20 = new ImageIcon(getClass().getResource("/Imagenes/space/space -20.png"));
		imagenSpace45 = new ImageIcon(getClass().getResource("/Imagenes/space/space -45.png"));
		imagenSpace75 = new ImageIcon(getClass().getResource("/Imagenes/space/space -75.png"));
		imagenSpace90 = new ImageIcon(getClass().getResource("/Imagenes/space/space -90.png"));
		
		imagenPorky = new ImageIcon(getClass().getResource("/Imagenes/porky/porky.png"));
		
		campoJuego = new JLayeredPane();
		campoJuego.setPreferredSize(new Dimension(this.windowW,this.windowH));
		campoJuego.setLayout(null);
		
		//Principal menu
		fondo = new PanelBackground("/Imagenes/fondo.png");
		fondo.setBounds(0, 0,this.windowW, this.windowH-95);
		campoJuego.add(fondo, new Integer(-1));
		
		info = new PopUpInfo(this);
		info.setVisible(false);
		
		menuPrincipal = new PanelMenuPrincipal(this);
		menuPrincipal.setLocation(50,120);
		campoJuego.add(menuPrincipal);
		
		mejorPuntaje = new JLabel();
		if(this.db.getPuntajes().size() < 1) {
			this.mejorPuntaje.setText("Best: 0");
		} else {
			this.mejorPuntaje.setText("Best: " + this.db.getPuntajes().get(0));
		}
		mejorPuntaje.setHorizontalAlignment(SwingConstants.CENTER);
		mejorPuntaje.setFont(new Font("Agency FB", mejorPuntaje.getFont().getStyle(), 70));
		menuPrincipal.getPaneles()[1].add(mejorPuntaje);
		
		puntajeActual = new JLabel();
		puntajeActual.setFont(new Font("Agency FB", puntajeActual.getFont().getStyle(), 30));
		puntajeActual.setHorizontalAlignment(SwingConstants.CENTER);
		menuPrincipal.getPaneles()[1].add(puntajeActual);
		
		//Game
		suelo = new PanelBackground("/Imagenes/suelo.png");
		suelo.setBounds(0, this.windowH-100,this.windowW, 100);
		campoJuego.add(suelo, new Integer(1));
		
		imagenTuboAlto = new ImageIcon(getClass().getResource("/Imagenes/tuboArriba.png"));
		imagenTuboBajo = new ImageIcon(getClass().getResource("/Imagenes/tuboAbajo.png"));
		imagenAsteroideAlto = new ImageIcon(getClass().getResource("/Imagenes/asteroideArriba.png"));
		imagenAsteroideBajo = new ImageIcon(getClass().getResource("/Imagenes/asteroideAbajo.png"));
		terreno = new ImageIcon(getClass().getResource("/Imagenes/div.png"));
		
		bird = new JLabel();
		bird.setBounds(100, (this.windowH/2)-100-(this.birdSizeH/2), this.birdSizeW,this.birdSizeH);
		bird.setIcon(new ImageIcon(imagenBird0.getImage().getScaledInstance(this.birdSizeH, this.birdSizeW-10, Image.SCALE_SMOOTH)));
		campoJuego.add(bird, new Integer(0));
		bird.setVisible(false);
		
		tuberiaAlta1 = new JLabel();
		tuberiaAlta1.setBounds(500, -350, 100, 600);
		tuberiaAlta1.setIcon(new ImageIcon(imagenTuboAlto.getImage().getScaledInstance(100, 600, Image.SCALE_SMOOTH)));
		campoJuego.add(tuberiaAlta1, new Integer(0));
		
		tuberiaBaja1 = new JLabel();
		tuberiaBaja1.setBounds(500,370, 100, 600);
		tuberiaBaja1.setIcon(new ImageIcon(imagenTuboBajo.getImage().getScaledInstance(100, 600, Image.SCALE_SMOOTH)));
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
		tubos = new MovimientoTuberias(tuberiaAlta1,tuberiaBaja1,tuberiaAlta2,tuberiaBaja2,this);
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
		puntajeActual.setText("Score: "+ this.puntos);
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
		menuPrincipal.setVisible(true);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {	}
		
		try {
			music = AudioSystem.getClip();
			music.open(AudioSystem.getAudioInputStream(getClass().getResource("/audio/effects/game over.wav")));
			FloatControl volumen = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
			volumen.setValue((float) -30.0);
			music.loop(0);
		} catch (LineUnavailableException e1) {
		} catch (IOException e1) {
		} catch (UnsupportedAudioFileException e1) {
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
			if(this.activePJ != "space/space") {
				music.open(AudioSystem.getAudioInputStream(getClass().getResource("/audio/Childs Nightmare.wav")));
			} else {
				music.open(AudioSystem.getAudioInputStream(getClass().getResource("/audio/Interplanetary Odyssey.wav")));
			}
			
			//Volumen
			FloatControl volumen = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
			volumen.setValue((float) -35.0);
			
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
		
		if(e.getKeyCode() == KeyEvent.VK_I && this.jugando == 0) {
			
			info.setVisible(true);
			
		} else {

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
				if(this.activePJ == "space/space") {
					bird.setIcon(new ImageIcon(imagenSpace0.getImage().getScaledInstance(this.birdSizeH, this.birdSizeW-10, Image.SCALE_SMOOTH)));
				}
				
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
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	// Getters
	public JFrame getFrame() {
		return frame;
	}
	
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

	public JLabel getTuberiaAlta1() {
		return tuberiaAlta1;
	}

	public JLabel getTuberiaBaja1() {
		return tuberiaBaja1;
	}

	public JLabel getTuberiaAlta2() {
		return tuberiaAlta2;
	}

	public JLabel getTuberiaBaja2() {
		return tuberiaBaja2;
	}

	public ImageIcon getImagenAsteroideAlto() {
		return imagenAsteroideAlto;
	}

	public ImageIcon getImagenAsteroideBajo() {
		return imagenAsteroideBajo;
	}

	public ImageIcon getImagenTuboAlto() {
		return imagenTuboAlto;
	}

	public ImageIcon getImagenTuboBajo() {
		return imagenTuboBajo;
	}	
	
}
