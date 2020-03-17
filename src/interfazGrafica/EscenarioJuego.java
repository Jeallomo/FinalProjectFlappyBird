package interfazGrafica;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import logica.PelotaQueSeMueve;

public class EscenarioJuego {
	//Objects
	private PelotaQueSeMueve pelota;
	private MovimientoEscenario tubos;
	
	//Components
	private JFrame frame;
	private JPanel campoBola;
	private JPanel campoDeJuego;
	private JLabel bola;
	private JLabel tuberiaAlta1,tuberiaBaja1,tuberiaAlta2,tuberiaBaja2;

	//Construct
	public EscenarioJuego() {
		frame = new JFrame("Flappy Bird");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		campoBola = new JPanel();
		campoBola.setPreferredSize(new Dimension(400,600));
		campoBola.setLayout(null);
		
		bola = new JLabel(":V");
		bola.setBounds(300, 150, 20, 20);
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
}
