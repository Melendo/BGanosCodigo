/**
 * 
 */
package Presentacion.Invernadero;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Negocio.Invernadero.TInvernadero;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

public class GUIInvernadero extends JFrame implements IGUI {

	private TInvernadero invernadero;
	private JButton bAltaInvernadero;

	public GUIInvernadero() throws HeadlessException {
		super("Bganos");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 1000;
		int alto = 525;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		iniGUI();
		this.setVisible(true);
	}

	public void iniGUI() {

		bAltaInvernadero = ComponentsBuilder.createButton("Alta Habitat", 100, 100, 185, 100);
		bAltaInvernadero.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIInvernadero.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.ALTA_INVERNADERO_VISTA, null));
			}

		});
		bAltaInvernadero.setVisible(true);
		this.add(bAltaInvernadero);
	}

	public void actualizar(Context context) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}