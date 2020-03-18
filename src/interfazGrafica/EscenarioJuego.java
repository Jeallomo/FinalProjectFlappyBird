package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import logica.PelotaQueSeMueve;

public class EscenarioJuego implements KeyListener {
	//Objects
	private PelotaQueSeMueve pelota;
	private MovimientoEscenario tubos;
	
	//Components
	private JFrame frame;
	private JPanel campoBola;
	private JLabel bola;
	private JLabel tuberiaAlta1,tuberiaBaja1,tuberiaAlta2,tuberiaBaja2;
	
	//Constants
	private final int windowH = 600;
	private final int windowW = 400;
	private final int birdSize = 20;

	//Construct
	public EscenarioJuego() {
		frame = new JFrame("Flappy Bird");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		campoBola = new JPanel();
		campoBola.setPreferredSize(new Dimension(this.windowW,this.windowH));
		campoBola.setLayout(null);
		
		bola = new JLabel(":V");
		bola.setBounds(100, 150, this.birdSize,this.birdSize);
		bola.setAlignmentX(SwingConstants.CENTER);
		bola.addKeyListener(this);
		campoBola.add(bola);
		
		tuberiaAlta1 = new JLabel();
		tuberiaAlta1.setBounds(400, 0, 100, 250);
		tuberiaAlta1.setBorder(new LineBorder(Color.black));
		frame.add(tuberiaAlta1);
		
		tuberiaBaja1 = new JLabel();
		tuberiaBaja1.setBounds(400,350, 100, 250);
		tuberiaBaja1.setBorder(new LineBorder(Color.black));
		frame.add(tuberiaBaja1);
		
		tuberiaAlta2 = new JLabel();
		tuberiaAlta2.setBounds(650, 0, 100, 250);
		tuberiaAlta2.setBorder(new LineBorder(Color.black));
		frame.add(tuberiaAlta2);
		
		tuberiaBaja2 = new JLabel();
		tuberiaBaja2.setBounds(650,350, 100, 250);
		tuberiaBaja2.setBorder(new LineBorder(Color.black));
		frame.add(tuberiaBaja2);
		
		pelota = new PelotaQueSeMueve(bola, this);
		tubos = new MovimientoEscenario(tuberiaBaja1,tuberiaAlta1,tuberiaAlta2,tuberiaBaja2,this);
		pelota.start();
		tubos.start();
		
		frame.add(campoBola);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	//Methods
	public void update() {
		frame.repaint();
	}

	//Getters
	public int getWindowH() {
		return windowH;
	}

	public int getWindowW() {
		return windowW;
	}

	public int getBirdSize() {
		return birdSize;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("UP");
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("UP");
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
