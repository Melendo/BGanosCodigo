package Presentacion.Factura;

import javax.swing.JFrame;

import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Factura.TCarrito;
import Negocio.Factura.TFactura;
import Negocio.Factura.TLineaFactura;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;

public class GUIAbrirFactura extends JFrame implements IGUI {

	public GUIAbrirFactura() {
		initGUI();
	}

	public void initGUI() {

		TCarrito carrito = new TCarrito();
		carrito.setLineaFactura(new HashSet<TLineaFactura>());
        ApplicationController.getInstance().manageRequest(new Context(Evento.CERRAR_FACTURA_VISTA, carrito));
	}

	@Override
	public void actualizar(Context context) {
		
	}
}