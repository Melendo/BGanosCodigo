/**
 * 
 */
package Presentacion.Entrada;

import javax.swing.JFrame;

import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Negocio.Entrada.TEntrada;

public class GUIModificarEntrada extends JFrame implements IGUI {

	private JButton jButton;

	private JPanel jPanel;

	private JLabel jLabel;

	private JTextField jTextField;

	public GUIModificarEntrada() {
		super("Modificar entrada");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 1000;
		int alto = 525;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}

	public void initGUI() {
		// Panel principal
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JLabel msgIntroIDCabecera = ComponentsBuilder
				.createLabel("Introduzca los datos de la entrada que desea modificar", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		// Campo para el id de la entrada
		JLabel labelID = new JLabel("ID: ");
		labelID.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(labelID);

		JTextField textID = new JTextField(20);
		textID.setMaximumSize(textID.getPreferredSize());
		mainPanel.add(textID);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Campo para introducir la fecha de la entrada
		JLabel labelFecha = new JLabel("Fecha (AAAA-DD-MM): ");
		labelFecha.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(labelFecha);

		JTextField textFecha = new JTextField(20);
		textFecha.setMaximumSize(textFecha.getPreferredSize());
		mainPanel.add(textFecha);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Campo para introducir el precio de la entrada
		JLabel labelPrecio = new JLabel("Precio: ");
		labelPrecio.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(labelPrecio);

		JTextField textPrecio = new JTextField(20);
		textPrecio.setMaximumSize(textPrecio.getPreferredSize());
		mainPanel.add(textPrecio);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Campo para introducir el stock de entradas
		JLabel labelStock = new JLabel("Stock: ");
		labelStock.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(labelStock);

		JTextField textStock = new JTextField(20);
		textStock.setMaximumSize(textStock.getPreferredSize());
		mainPanel.add(textStock);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Campo para introducir el id del invernadero asociado
		JLabel labelIdInvernadero = new JLabel("ID del invernadero: ");
		labelIdInvernadero.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(labelIdInvernadero);

		JTextField textIdInvernadero = new JTextField(20);
		textIdInvernadero.setMaximumSize(textIdInvernadero.getPreferredSize());
		mainPanel.add(textIdInvernadero);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Panel para los botones
		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(75, 50, 100, 100);

		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				GUIModificarEntrada.this.setVisible(false);

				try {

					Integer id_entrada = Integer.parseInt(textID.getText());

					// fecha, precio, stock, id_invernadero
					Date fecha = Date.valueOf(textFecha.getText());
					Float precio = Float.parseFloat(textPrecio.getText());
					Integer stock = Integer.parseInt(textStock.getText());
					Integer idInvernadero = Integer.parseInt(textIdInvernadero.getText());

					ApplicationController.getInstance()
							.manageRequest(new Context(Evento.ALTA_ENTRADA,
									new TEntrada(id_entrada != 0 ? id_entrada : 0, fecha != null ? fecha : null,
											precio != null ? precio : 0, !textStock.getText().isEmpty() ? stock : 0,
											!textIdInvernadero.getText().isEmpty() ? idInvernadero : 0, true)));

				} catch (Exception ex) {
					// TODO
				}
			}

		});

		panelBotones.add(botonAceptar);
		
	    JButton botonCancelar = new JButton("Cancelar");
	    botonCancelar.addActionListener(new ActionListener() {

	        @Override
	        public void actionPerformed(ActionEvent e) {
	            GUIModificarEntrada.this.setVisible(false);
	            ApplicationController.getInstance().manageRequest(new Context(Evento.MODIFICAR_ENTRADA_VISTA, null));
	        }
	    });
	    panelBotones.add(botonCancelar);

	    this.setVisible(true);
	    this.setResizable(true);
		
	}

	@Override
	public void actualizar(Context context) {
		
		if(context.getEvento() == Evento.ALTA_ENTRADA_OK) {
			
			
			
		} else if(context.getEvento() == Evento.ALTA_ENTRADA_KO) {
			
			
		}

		dispose();
	}
}