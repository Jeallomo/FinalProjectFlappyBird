package logica;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

import interfazGrafica.EscenarioJuego;
/**
 * 
 * @author Julian Espinoza
 *
 */
public class CodeListener extends Thread implements KeyListener{
	//Attributes
	private String codigo;
	private EscenarioJuego ej;
	
	//Construct
	public CodeListener(EscenarioJuego ej){
		this.codigo = "";
		this.ej = ej;
	}
	
	//Methods
	public void run() {
		while(true){
			if(codigo.indexOf("night") != -1) {
				this.ej.getCampoJuego().remove(this.ej.getFondo());
				this.ej.setFondo(new PanelBackground("/Imagenes/fondoNoche.png"));
				this.ej.getFondo().setBounds(0, 0,this.ej.getWindowW(), this.ej.getWindowH()-95);
				this.ej.getCampoJuego().add(this.ej.getFondo(), new Integer(-1));
				this.ej.getLblPuntos().setForeground(Color.white);
				this.codigo = "";
				this.ej.update();
			}
			if(codigo.indexOf("day") != -1) {
				this.ej.getCampoJuego().remove(this.ej.getFondo());
				this.ej.setFondo(new PanelBackground("/Imagenes/fondo.png"));
				this.ej.getFondo().setBounds(0, 0,this.ej.getWindowW(), this.ej.getWindowH()-95);
				this.ej.getCampoJuego().add(this.ej.getFondo(), new Integer(-1));
				this.ej.getLblPuntos().setForeground(Color.black);
				this.codigo = "";
				this.ej.update();
			}
			if(codigo.indexOf("duque") != -1) {
				if(this.ej.getActivePJ() == "porky/porky") {
					this.ej.setActivePJ("bird/bird");
					
				} else {
					this.ej.setActivePJ("porky/porky");
					this.ej.getBird().setIcon(new ImageIcon(this.ej.getImagenPorky().getImage().getScaledInstance(this.ej.getBirdSizeH(), this.ej.getBirdSizeW(), Image.SCALE_SMOOTH)));
				}
				this.codigo = "";
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {	}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		codigo = codigo + e.getKeyChar();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
