package Presentacion.Fabricante;

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
import javax.swing.JPanel;
import javax.swing.JLabel;

public class GUIAltaFabricante extends JFrame implements IGUI {

	private JButton bLocal;
	private JButton bExtranjero;
	private JPanel mainPanel;
	private JLabel cabecera;

	public GUIAltaFabricante() {
		super("Alta Fabricante");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 1000;
		int alto = 525;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		mainPanel = new JPanel();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI();
		this.setVisible(true);
	}

	public void initGUI() {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		cabecera = ComponentsBuilder.createLabel("Seleccione si el fabricante es local o extranjero", 1, 10, 80,
				20, Color.BLACK);
		cabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(cabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 180)));

		// BOTONES ACEPTAR CANCELAR
		JPanel panelBotones = new JPanel();
		panelBotones.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(panelBotones);

		bLocal = ComponentsBuilder.createButton("Local", 100, 100, 185, 100);

		bLocal.addActionListener(e -> actualizar(new Context(Evento.ALTA_FABRICANTE, null)));
		
		bExtranjero = ComponentsBuilder.createButton("Extranjero", 100, 100, 185, 100);
		
		bExtranjero.addActionListener(e -> actualizar(new Context(Evento.ALTA_FABRICANTE, null)));
		
		panelBotones.add(bLocal);
		panelBotones.add(bExtranjero);
		
		JButton botonCancelar = ComponentsBuilder.createButton("Cancelar", 100, 100, 185, 100);
		botonCancelar.setBounds(200, 50, 100, 100);
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIAltaFabricante.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.FABRICANTE_VISTA, null));
			}
		});
		panelBotones.add(botonCancelar);
		botonCancelar.setAlignmentX(RIGHT_ALIGNMENT);
		botonCancelar.setAlignmentY(BOTTOM_ALIGNMENT);

		this.setVisible(true);
		this.setResizable(true);
		
		
	}
	@Override
	public void actualizar(Context context) {
		// TODO Auto-generated method stub

	}
}