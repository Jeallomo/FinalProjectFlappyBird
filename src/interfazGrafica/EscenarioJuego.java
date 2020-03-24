package interfazGrafica;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import logica.CodeListener;
import logica.Colisionador;
import logica.MovimientoEscenario;
import logica.Pajaro;
import logica.PanelBackground;

public class EscenarioJuego implements KeyListener{
	// Objects
	private Pajaro pelota;
	private MovimientoEscenario tubos;
	private Colisionador col;
	private CodeListener cod;
	
	// Components
	private JFrame frame;
	private JPanel campoJuego;
	private PanelBackground titulo;
	private JLabel mensajeTitulo;
	private JLabel bird;
	private JLabel tuberiaAlta1,tuberiaBaja1,tuberiaAlta2,tuberiaBaja2;
	private ImageIcon imagenTuboAlto;
	private ImageIcon imagenTuboBajo;
	private ImageIcon imagenBird;
	
	// Attributes
	private boolean jugando = false;
	
	// Constants
	private final int windowH = 600;
	private final int windowW = 500;
	private final int birdSize = 40;

	// Construct
	public EscenarioJuego() {
		frame = new JFrame("Flappy Bird");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		campoJuego = new JPanel();
		campoJuego.setPreferredSize(new Dimension(this.windowW,this.windowH));
		campoJuego.setLayout(null);
		
		titulo = new PanelBackground("/Imagenes/fondoTitulo.png");
		titulo.setBounds((this.windowW-((this.windowW*3)/4))/2, 150, (this.windowW*3)/4, 200);
		titulo.setLayout(null);
		campoJuego.add(titulo);
		
		mensajeTitulo = new JLabel("Presiona una tecla para jugar");
		mensajeTitulo.setBounds(0, (200/2)-20, (this.windowW*3)/4, 30);
		mensajeTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.add(mensajeTitulo);
		
		imagenTuboAlto = new ImageIcon(getClass().getResource("/Imagenes/tuboArriba.png"));
		imagenTuboBajo = new ImageIcon(getClass().getResource("/Imagenes/tuboAbajo.png"));
		imagenBird = new ImageIcon(getClass().getResource("/Imagenes/bird.png"));
		
		bird = new JLabel();
		bird.setBounds(100, 150, this.birdSize*2,this.birdSize);
		bird.setAlignmentX(SwingConstants.CENTER);
		bird.setIcon(new ImageIcon(imagenBird.getImage().getScaledInstance(this.birdSize, this.birdSize, Image.SCALE_SMOOTH)));
		campoJuego.add(bird);
		
		tuberiaAlta1 = new JLabel();
		tuberiaAlta1.setBounds(500, -350, 100, 600);
		tuberiaAlta1.setIcon(new ImageIcon(imagenTuboBajo.getImage().getScaledInstance(100, 600, Image.SCALE_SMOOTH)));
		campoJuego.add(tuberiaAlta1);
		
		tuberiaBaja1 = new JLabel();
		tuberiaBaja1.setBounds(500,370, 100, 600);
		tuberiaBaja1.setIcon(new ImageIcon(imagenTuboAlto.getImage().getScaledInstance(100, 600, Image.SCALE_SMOOTH)));
		campoJuego.add(tuberiaBaja1);
		
		tuberiaAlta2 = new JLabel();
		tuberiaAlta2.setBounds(850, -350, 100, 600);
		tuberiaAlta2.setIcon(new ImageIcon(imagenTuboAlto.getImage().getScaledInstance(100, 600, Image.SCALE_SMOOTH)));
		campoJuego.add(tuberiaAlta2);
		
		tuberiaBaja2 = new JLabel();
		tuberiaBaja2.setBounds(800,370, 100, 600);
		tuberiaBaja2.setIcon(new ImageIcon(imagenTuboBajo.getImage().getScaledInstance(100, 600, Image.SCALE_SMOOTH)));
		campoJuego.add(tuberiaBaja2);
		
		pelota = new Pajaro(bird, this);
		tubos = new MovimientoEscenario(tuberiaBaja1,tuberiaAlta1,tuberiaAlta2,tuberiaBaja2,this);
		col = new Colisionador(bird,tuberiaBaja1,tuberiaAlta1,tuberiaAlta2,tuberiaBaja2, this);
		cod = new CodeListener(this);
		
		frame.addKeyListener(this);
		
		frame.add(campoJuego);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	// General Methods
	public void update() {
		frame.getContentPane().repaint();
	}
	
	// Methods for KeyListener
	@Override
	public void keyPressed(KeyEvent e) {
		if(this.jugando == false) {
			campoJuego.remove(titulo);
			frame.addKeyListener(pelota);
			frame.addKeyListener(cod);
			pelota.start();
			tubos.start();
			col.start();
			cod.start();
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

	public int getBirdSize() {
		return birdSize;
	}

	public JPanel getCampoBola() {
		return campoJuego;
	}

	public Pajaro getPelota() {
		return pelota;
	}

	public MovimientoEscenario getTubos() {
		return tubos;
	}
}
