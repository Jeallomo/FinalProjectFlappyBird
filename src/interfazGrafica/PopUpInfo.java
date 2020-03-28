package interfazGrafica;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;

/**
 * Declaración clase  PopUpInfo
 * @author JulianEspinoza
 *
 */
public class PopUpInfo extends JFrame {

	// Components
	private JPanel manualDeUsuario;
	private JPanel listaCodes;
	private JButton cerrar;
	
	// Constants
	private final int windowW = 400;
	private final int windowH = 300;
	
	// Construct
	/**
	 * Constructor PopUpInfo
	 * @param ej
	 */
	public PopUpInfo(EscenarioJuego ej) {
		
		super();
		
		setLayout(new BorderLayout());
		setSize(this.windowW,this.windowH);
		setLocationRelativeTo(ej.getFrame());
		setUndecorated(true);

		JLabel titulo = new JLabel("Información");
		titulo.setFont(new Font(titulo.getFont().getFontName(), titulo.getFont().getStyle(), 17));
		
		cerrar = new JButton(new ImageIcon(getClass().getResource("/Imagenes/equis.png")));
		cerrar.setSize(25,25);
		cerrar.setOpaque(false);
		cerrar.setContentAreaFilled(false);
		cerrar.setBorderPainted(false);
		cerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				
			}
		});
		
		SpringLayout spring =  new SpringLayout();
		
		JPanel opcsVentana = new JPanel(spring);
		opcsVentana.setPreferredSize(new Dimension(this.windowW, 40));
		opcsVentana.setBackground(Color.GREEN.brighter());
		opcsVentana.setBorder(BorderFactory.createLineBorder(Color.GREEN.darker(), 3));
		opcsVentana.add(cerrar);
		cerrar.setVisible(true);
		opcsVentana.add(titulo);
		titulo.setVisible(true);
		spring.putConstraint(SpringLayout.WEST, cerrar, -50, SpringLayout.EAST, opcsVentana);
		spring.putConstraint(SpringLayout.WEST, titulo, 20, SpringLayout.WEST, opcsVentana);
		spring.putConstraint(SpringLayout.NORTH, titulo, 5, SpringLayout.NORTH, opcsVentana);
		add(opcsVentana,BorderLayout.NORTH);
		opcsVentana.setVisible(true);
		
		JLabel imagenManual = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/Logo.png")));
		
		JLabel imagenCodes = new JLabel(new ImageIcon(getClass().getResource("/Imagenes/Logo.png")));
		
		manualDeUsuario = new JPanel();
		manualDeUsuario.setVisible(true);
		manualDeUsuario.add(imagenManual);
		imagenManual.setVisible(true);
		
		listaCodes = new JPanel();
		listaCodes.setVisible(true);
		listaCodes.add(imagenCodes);
		imagenCodes.setVisible(true);
		
		JTabbedPane pane = new JTabbedPane();
		pane.setBackground(Color.GREEN.brighter());
		pane.setBorder(BorderFactory.createLineBorder(Color.GREEN.darker(), 3));
		pane.addTab("Manual de Usuario", manualDeUsuario);
		pane.addTab("Codigos", listaCodes);
		add(pane,BorderLayout.CENTER);
		
	}
	
	
}
