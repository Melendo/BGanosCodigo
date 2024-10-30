/**
 * 
 */
package Presentacion.Invernadero;

import javax.swing.JFrame;

import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Invernadero.TInvernadero;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIAltaInvernadero extends JFrame implements IGUI {

	private JPanel jPanel;

	private JTextField jTextField;

	private JLabel jLabel;

	private JButton jButton;

	public GUIAltaInvernadero() {
		super("Alta Invernadero");
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
	}

	public void iniGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JLabel msgIntroIDCabecera = ComponentsBuilder
				.createLabel("Introduzca los datos del habitat que desea dar de alta ", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		// Campo para introducir el nombre del invernadero
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(labelNombre);

		JTextField textNombre = new JTextField(20);
		textNombre.setMaximumSize(textNombre.getPreferredSize());
		mainPanel.add(textNombre);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Campo para introducir el sustrato
		JLabel labelSustrato = new JLabel("Sustrato:");
		labelSustrato.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(labelSustrato);

		JTextField textSustrato = new JTextField(20);
		textSustrato.setMaximumSize(textSustrato.getPreferredSize());
		mainPanel.add(textSustrato);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		// Campo para introducir el tipo de iluminacion
		JLabel labelIluminacion = new JLabel("Tipo de Iluminacion:");
		labelIluminacion.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(labelIluminacion);

		JTextField textIluminacion = new JTextField(20);
		textIluminacion.setMaximumSize(textIluminacion.getPreferredSize());
		mainPanel.add(textIluminacion);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

		// Panel para los botones
		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		// BOTON ACEPTAR (GUARDAR LOS DATOS DEL ALTA)
		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIAltaInvernadero.this.setVisible(false);
				try {
					String nombre = textNombre.getText();
					String sustrato = textSustrato.getText();
					String iluminacion = textIluminacion.getText();

					ApplicationController.getInstance().manageRequest(
							new Context(Evento.ALTA_INVERNADERO, new TInvernadero(nombre != null ? nombre : "",
									sustrato != null ? sustrato : "", iluminacion != null ? iluminacion : "")));
				} catch (Exception ex) {
					// ApplicationController.getInstance().manageRequest(new Context
					// (Evento.V_ERRORES, -4));
				}
			}
		});
		panelBotones.add(botonAceptar);

		// BOTON CANCELAR
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIAltaInvernadero.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.INVERNADERO_VISTA, null));
			}
		});
		panelBotones.add(botonCancelar);

		this.setVisible(true);
		this.setResizable(true);
	}

	@Override
	public void actualizar(Context context) {
		int resultado = (int) context.getDatos();
		if (context.getEvento() == Evento.ALTA_INVERNADERO_OK) {

			JOptionPane.showMessageDialog(this, "Inventario dado de alta correctamente con id: " + resultado, "Exito",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (context.getEvento() == Evento.ALTA_INVERNADERO_KO) {

			switch (resultado) {
			case -3:
				JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos requeridos.", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			case -23:
				JOptionPane.showMessageDialog(this, "Error: Ya existe un Invernadero con el mismo nombre.", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			default:
				JOptionPane.showMessageDialog(this, "Error desconocido al modificar el sistema de riego.", "Error",
						JOptionPane.ERROR_MESSAGE);
				break;
			}
		}
	}
}