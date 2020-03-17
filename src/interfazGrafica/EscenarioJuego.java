package interfazGrafica;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logica.PelotaQueSeMueve;

public class EscenarioJuego {
	//Objects
	private PelotaQueSeMueve pelota;
	
	//Components
	private JFrame frame;
	private JPanel campoBola;
	private JLabel bola;

	//Construct
	public EscenarioJuego() {
		frame = new JFrame("Flappy Bird");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		campoBola = new JPanel();
		campoBola.setPreferredSize(new Dimension(600,300));
		campoBola.setLayout(null);
		
		bola = new JLabel(":V");
		bola.setBounds(300, 150, 20, 20);
		campoBola.add(bola);
		
		pelota = new PelotaQueSeMueve(bola, this);
		pelota.start();
		
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
