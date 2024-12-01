package Presentacion.VentaJPA;

import javax.swing.JFrame;

import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class GUIMostrarVentaPorId extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	private JTable tablaDatos;
	private JLabel mensajeVenta;
	private JScrollPane scroll;

	public GUIMostrarVentaPorId() {
		super("Mostrar Venta");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 700;
		int alto = 700;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}

	public void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("Introduzca el ID de la Venta a mostrar ", 1, 10, 80,
				20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		JLabel labelID = ComponentsBuilder.createLabel("ID Venta: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(labelID);

		JTextField id = new JTextField();
		id.setPreferredSize(new Dimension(250, 30));

		id.setEditable(true);
		panelID.add(id);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel mensaje = new JPanel();
		mainPanel.add(mensaje);

		mensajeVenta = ComponentsBuilder.createLabel("", 10, 100, 80, 20, Color.BLACK);
		mensaje.add(mensajeVenta);

		String[] nombreColumnas = { "Id Venta", "ID Precio total", "Forma de pago", "Fecha" };
		List<String[]> datosColumnas = new ArrayList<String[]>();

		tablaDatos = ComponentsBuilder.createTable(0, 4, nombreColumnas, datosColumnas.toArray(new String[][] {}));
		scroll = new JScrollPane(tablaDatos);
		scroll.setBounds(50, 115, 900, 288);
		mainPanel.add(scroll);

		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(75, 50, 100, 100);
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Integer id_Factura = Integer.parseInt(id.getText());
					ApplicationController.getInstance().manageRequest(
							new Context(Evento.MOSTRAR_FACTURA_POR_ID, !id.getText().isEmpty() ? id_Factura : 0));

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(GUIMostrarVentaPorId.this, "Los datos no son correctos", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		panelBotones.add(botonAceptar);

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(200, 50, 100, 100);
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIMostrarVentaPorId.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VENTA_VISTA, null));

			}
		});
		panelBotones.add(botonCancelar);

		this.setVisible(true);
		this.setResizable(true);
	}

	public void actualizar(Context context) {

	}
}