/**
 * 
 */
package Presentacion.Planta;

import javax.swing.JFrame;

import Presentacion.Controller.GUIMSG;
import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;


public class GUIModificarPlanta extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	public void initGUI() {

		
	
	}



	@Override
	public void actualizar(Context context) {
		switch(context.getEvento()) {
		case Evento.MODIFICAR_PLANTA_OK:
			GUIMSG.showMessage("Se realizo la modificacion correctamente", "LISTAR PLANTAS", false);
			break;
		case  Evento.MODIFICAR_PLANTA_KO:
			GUIMSG.showMessage("No se pudo realizar la modificacion", "MODIFICAR PLANTAS", true);
			break;
		default:
			GUIMSG.showMessage("ERROR INESPERADO", "LISTAR PLANTAS", true);
			break;
		
	}
	}
}