package logica;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import interfazGrafica.EscenarioJuego;

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
				this.ej.getCampoBola().setBackground(Color.black);
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {	}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		codigo = codigo + e.getKeyChar();
		System.out.println(codigo);
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
