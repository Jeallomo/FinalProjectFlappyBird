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
	private JLabel tuberiaAlta,tuberiaBaja;

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
		
		tuberiaAlta = new JLabel();
		tuberiaAlta.setBounds(200, 0, 100, 250);
		tuberiaAlta.setBorder(new LineBorder(Color.black));
		frame.add(tuberiaAlta);
		
		tuberiaBaja = new JLabel();
		tuberiaBaja.setBounds(400,350, 100, 250);
		tuberiaBaja.setBorder(new LineBorder(Color.black));
		frame.add(tuberiaBaja);
		
		pelota = new PelotaQueSeMueve(bola, this);
		tubos = new MovimientoEscenario(tuberiaBaja,tuberiaAlta,this);
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
