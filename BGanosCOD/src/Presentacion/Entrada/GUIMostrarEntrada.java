
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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Negocio.Entrada.TEntrada;

import javax.swing.JPanel;

public class GUIMostrarEntrada extends JFrame implements IGUI {

	private static final long serialVersionUID = 1L;

	private JButton botonAceptar;
	
	private JButton botonCancelar;

	private JLabel textIdEntrada;

	private JTextField id;

	private JPanel mainPanel;

	public GUIMostrarEntrada() {
		super("Mostar entrada");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 430;
		int alto = 330;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGUI();
	}

	public void initGUI() {

		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JLabel msgIntroIDCabecera = ComponentsBuilder
				.createLabel("Introduzca el ID de la entrada que quiere que se muestre ", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		textIdEntrada = ComponentsBuilder.createLabel("ID entrada: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(textIdEntrada);

		id = new JTextField();
		id.setPreferredSize(new Dimension(250, 30));

		id.setEditable(true);
		panelID.add(id);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		// Boton de aceptar
		botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(75, 50, 100, 100);

		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Integer id_entrada = Integer.parseInt(id.getText());
					// Vamos a tratar el error de campos nulos
					//GUIMostrarEntrada.this.setVisible(false);
					ApplicationController.getInstance().manageRequest(
							new Context(Evento.MOSTRAR_ENTRADA_POR_ID, !id.getText().isEmpty() ? id_entrada : 0));
					setVisible(false);

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(GUIMostrarEntrada.this, "Error en el formato del ID", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		panelBotones.add(botonAceptar);

		// Boton de cancelar
		botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(200, 50, 100, 100);

		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIMostrarEntrada.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.ENTRADA_VISTA, null));
			}
		});

		panelBotones.add(botonCancelar);

		this.setVisible(true);
		this.setResizable(true);

	}

	@Override
	public void actualizar(Context context) {

		if (context.getEvento() == Evento.MOSTRAR_ENTRADA_OK) {
			TEntrada entrada = (TEntrada) context.getDatos();
			String texto = "ID: " + entrada.getId() + "\nId invernadero: " + entrada.getIdInvernadero() + "\nFecha: "
					+ entrada.getFecha() + "\nPrecio: " + entrada.getPrecio() + "\nStock: " + entrada.getStock()
					+ "\nActivo: " + (entrada.getActivo() ? "Si" : "No");

			JOptionPane.showMessageDialog(this, texto, "Entrada", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(false);
			ApplicationController.getInstance().manageRequest(new Context(Evento.ENTRADA_VISTA, null));

		} else if (context.getEvento() == Evento.MOSTRAR_ENTRADA_KO) {
			JOptionPane.showMessageDialog(this, "No existe entrada con ID: " + ((TEntrada) context.getDatos()).getId(),
					"Error", JOptionPane.ERROR_MESSAGE);

			 this.setVisible(false);
			 ApplicationController.getInstance().manageRequest(new Context(Evento.MOSTRAR_ENTRADA_POR_ID_VISTA, null));

		}


	}
}