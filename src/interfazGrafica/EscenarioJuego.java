package interfazGrafica;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

public class EscenarioJuego implements KeyListener{
	// Objects
	private Pajaro pelota;
	private MovimientoTuberias tubos;
	private Colisionador col;
	private CodeListener cod;
	private MovimientoTerreno terrenos;
	private Puntaje db;
	
	// Components
	private JFrame frame;
	private PanelBackground fondo;
	private PanelBackground suelo;
	private JLayeredPane campoJuego;
	private PanelBackground titulo;
	private JLabel mejorPuntaje;
	private JLabel mensajeTitulo;
	private JLabel tuberiaAlta1,tuberiaBaja1,tuberiaAlta2,tuberiaBaja2;
	private JLabel terreno1, terreno2;
	private JLabel lblPuntos;
	private ImageIcon imagenTuboAlto;
	private ImageIcon imagenTuboBajo;
	private ImageIcon terreno;
	
	public ImageIcon imagenBird0,imagenBirdArriba,imagenBird20,imagenBird45,imagenBird90,imagenBird75;
	public JLabel bird;
	
	// Attributes
	private boolean jugando = false;
	private int puntos = 0;
	
	// Constants
	private final int windowH = 600;
	private final int windowW = 500;
	private final int birdSizeW = 35;
	private final int birdSizeH = 40;

	// Construct
	public EscenarioJuego(Puntaje db) {
		this.db = db;
		
		frame = new JFrame("Flappy Bird");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		imagenBird0 = new ImageIcon(getClass().getResource("/Imagenes/bird.png"));
		imagenBirdArriba = new ImageIcon(getClass().getResource("/Imagenes/bird20.png"));
		imagenBird20 = new ImageIcon(getClass().getResource("/Imagenes/bird -20.png"));
		imagenBird45 = new ImageIcon(getClass().getResource("/Imagenes/bird -45.png"));
		imagenBird75 = new ImageIcon(getClass().getResource("/Imagenes/bird -75.png"));
		imagenBird90 = new ImageIcon(getClass().getResource("/Imagenes/bird -90.png"));
		
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
		bird.setBounds(100, 150, this.birdSizeW*2,this.birdSizeW);
		bird.setAlignmentX(SwingConstants.CENTER);
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
	public void update() {
		lblPuntos.setText("Score: " + this.puntos);
		frame.getContentPane().repaint();
	}
	
	public void reset() {
		frame.removeKeyListener(pelota);
		frame.removeKeyListener(cod);
		
		pelota.pauseThread();
		tubos.pauseThread();
		terrenos.pauseThread();
		
		this.tuberiaAlta1.setLocation(500, -350);
		this.tuberiaBaja1.setLocation(500,370);
		this.tuberiaAlta2.setLocation(850, -350);
		this.tuberiaBaja2.setLocation(800,370);
		
		campoJuego.add(titulo, new Integer(3));
		this.db.addPuntos(this.puntos);
		this.db.ordenarPuntos();
		
		this.resetPuntos();
		
		if(this.db.getPuntajes().size() < 1) {
			this.mejorPuntaje.setText("Best: 0");
		} else {
			this.mejorPuntaje.setText("Best: " + this.db.getPuntajes().get(0));
		}
		
		this.update();
		this.jugando = false;
	}
	
	public void addPunto() {
		this.puntos++;
	}
	
	public void resetPuntos() {
		this.puntos = 0;
	}
	
	// Methods for KeyListener
	@Override
	public void keyPressed(KeyEvent e) {
		if(this.jugando == false) {
			campoJuego.remove(titulo);
			frame.addKeyListener(pelota);
			frame.addKeyListener(cod);
			
			pelota.resumeThread();
			tubos.resumeThread();
			col.resumeThread();
			terrenos.resumeThread();
			
			this.jugando = true;
			this.update();
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
