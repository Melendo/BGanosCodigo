/**
 * 
 */
package Presentacion.ProveedorJPA;

import javax.swing.JFrame;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;

public class GUIListarProveedoresDeMarca extends JFrame implements IGUI {
	
	private JTextField idText;
	private JPanel mainPanel;
	private JTable tabla;
	private JButton botonCancelar;

	public GUIListarProveedoresDeMarca() {
		super("Listar Proveedores de una Marca");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 800;
		int alto = 400;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		iniGUI();
	}

	public void iniGUI() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);

		// Panel Central
		JPanel panelCentro = new JPanel();
		panelCentro.setLayout(new FlowLayout(FlowLayout.CENTER));
		mainPanel.add(panelCentro);

		
		JLabel labelMarca = new JLabel("Ingrese el id de la Marca:");
		panelCentro.add(labelMarca);

		idText = new JTextField();
		idText.setPreferredSize(new Dimension(250, 30));
		panelCentro.add(idText);

		// Boton Buscar
		JButton botonBuscar = new JButton("Buscar");
		botonBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				buscarPorMarca();
			}
		});
		panelCentro.add(botonBuscar);

		// Tabla
		String[] nombreColumnas = { "ID", "Nombre", "CIF", "Telefono", "Activo" };
		tabla = ComponentsBuilder.createTable(0, nombreColumnas.length, nombreColumnas, null);
		JScrollPane scroll = new JScrollPane(tabla);
		scroll.setPreferredSize(new Dimension(750, 250));
		mainPanel.add(scroll);

		// Panel de botones
		JPanel panelBotones = new JPanel();
		botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIListarProveedoresDeMarca.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.PROVEEDOR_VISTA, null));
			}
		});
		panelBotones.add(botonCancelar);
		mainPanel.add(panelBotones);

		this.setVisible(true);
	}
	
	private void buscarPorMarca() {
		String id = idText.getText().trim();
		if (id.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Por favor, ingrese una Marca.", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			int idMarca = Integer.parseInt(id);
			ApplicationController.getInstance()
					.manageRequest(new Context(Evento.LISTAR_PROVEEDORES_DE_MARCA, idMarca));
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID valido para la Marca.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void actualizar(Context context) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}
}