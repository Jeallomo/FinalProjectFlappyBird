
package logica;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Declaración de clase PanelBackGround
 * 
 * @author Jesus Lozada
 *
 */
public class PanelBackground extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image fondo;

	/**
	 * Constructor PanelBackground
	 * 
	 * @param s
	 */
	public PanelBackground(String s) {
		this.fondo = new ImageIcon(getClass().getResource(s)).getImage();
	}

	@Override
	/**
	 * @param g
	 */
	public void paint(Graphics g) {
		g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
		this.setOpaque(false);
		super.paint(g);
	}
}
