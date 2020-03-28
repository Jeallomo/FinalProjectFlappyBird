package interfazGrafica;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PanelMenuPrincipal extends JPanel {

	// Components
	private JLabel titulo;
	private JPanel[] paneles = new JPanel[3];

	// Construct
	public PanelMenuPrincipal(EscenarioJuego ej) {

		setLayout(new GridLayout(3, 1));
		setSize(500, 400);
		setOpaque(false);

		titulo = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/Logo.png")));

		paneles[0] = new JPanel() {
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				AlphaComposite old = (AlphaComposite) g2.getComposite();
				g2.setComposite(AlphaComposite.SrcOver.derive(0.5f));
				super.paintComponent(g);
				g2.setComposite(old);
			}
		};
		paneles[0].setBackground(Color.WHITE);
		paneles[0].add(titulo);
		add(paneles[0]);

		paneles[1] = new JPanel(new GridLayout(2,1)){
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				AlphaComposite old = (AlphaComposite) g2.getComposite();
				g2.setComposite(AlphaComposite.SrcOver.derive(0.5f));
				super.paintComponent(g);
				g2.setComposite(old);
			}
		};
		paneles[1].setBackground(Color.WHITE);
		add(paneles[1]);

		JLabel aux = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/texto1.png")));
		aux.setVisible(true);

		JLabel aux1 = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/texto2.png")));
		aux1.setVisible(true);

		paneles[2] = new JPanel(){
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
				AlphaComposite old = (AlphaComposite) g2.getComposite();
				g2.setComposite(AlphaComposite.SrcOver.derive(0.5f));
				super.paintComponent(g);
				g2.setComposite(old);
			}
		};
		paneles[2].setBackground(Color.WHITE);
		paneles[2].setLayout(new GridLayout(2, 1));
		paneles[2].setSize(500, 50);
		paneles[2].add(aux);
		paneles[2].add(aux1);
		add(paneles[2]);

		// Do visible
		titulo.setVisible(true);
		paneles[0].setVisible(true);
		aux.setVisible(true);
		paneles[1].setVisible(true);
		paneles[2].setVisible(true);

	}

	// Getters
	public JPanel[] getPaneles() {
		return paneles;
	}

}
