
package Presentacion.Fabricante;

import javax.swing.JFrame;
import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;

import java.awt.Dimension;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class GUIModificarFabricante extends JFrame implements IGUI {

	public GUIModificarFabricante() {
		super("Modificar Fabricante");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 600;
        int alto = 400;
        int x = (pantalla.width - ancho) / 2;
        int y = (pantalla.height - alto) / 2;
        this.setBounds(x, y, ancho, alto);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
	}

	public void initGUI() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	@Override
	public void actualizar(Context context) {
		// TODO Auto-generated method stub

	}
}