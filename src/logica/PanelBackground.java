/**
 * DESCRIPCION: JPanel con fondo personalizado
 * NOMBRE: Jesús Alberto Lozada Montiel
 * N LISTA: 19
 */

package logica;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelBackground extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Image fondo;
	
	public PanelBackground(String s) {
		this.fondo = new ImageIcon(getClass().getResource(s)).getImage();
	}
	
	@Override
	public void paint(Graphics g){
        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
        this.setOpaque(false);
        super.paint(g);
    }
}
