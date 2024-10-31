package Presentacion.Entrada;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.Entrada.TEntrada;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

// TODO añadí un extends JFrame, no estaba, mirarlo en modelo
public class GUIEntrada extends JFrame implements IGUI {

	private JButton bAltaEntrada;
	private JButton bBajaEntrada;
	private JButton bModificarEntrada;
	private JButton bMostrarEntrada;
	private JButton bListarEntradas;
	private JButton bListarEntradasPorInvernadero;

	private JPanel j;

	private TEntrada tEntrada;

	public GUIEntrada() {

		super("BGanos");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 1000;
		int alto = 525;
		int x = (pantalla.width - ancho) / 2;
		int y = (pantalla.height - alto) / 2;
		this.setBounds(x, y, ancho, alto);
		this.setLayout(null);
		j = new JPanel();
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initGUI();
		this.setVisible(true);

	}

	private void initGUI() {
		tEntrada = new TEntrada();
		JLabel label = ComponentsBuilder.createLabel("Entrada", 250, 30, 500, 50, Color.BLACK);
		this.add(label);

		// ALTA ENTRADA
		bAltaEntrada = ComponentsBuilder.createButton("Alta entrada", 100, 120, 185, 100);
		bAltaEntrada.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIEntrada.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.ALTA_ENTRADA_VISTA, tEntrada));
			}
		});
		
		bAltaEntrada.setVisible(true);
		this.add(bAltaEntrada);
		
		// BAJA ENTRADA
		bBajaEntrada = ComponentsBuilder.createButton("Baja entrada", 407, 120, 185, 100);
		bBajaEntrada.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIEntrada.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.BAJA_ENTRADA_VISTA, tEntrada));
			}
		});
		
		bBajaEntrada.setVisible(true);
		this.add(bBajaEntrada);
		
		
		// MODIFICAR ENTRADA
		bModificarEntrada = ComponentsBuilder.createButton("Modificar entrada", 715, 120, 185, 100);
		bModificarEntrada.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIEntrada.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.MODIFICAR_ENTRADA_VISTA, tEntrada));
			}

		});
		bModificarEntrada.setVisible(true);
		this.add(bModificarEntrada);
		
		// MOSTRAR ENTRADA
		bMostrarEntrada = ComponentsBuilder.createButton("Mostrar entrada", 100, 290, 185, 100);
		bMostrarEntrada.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIEntrada.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.MOSTRAR_ENTRADA_POR_ID_VISTA, tEntrada));
			}

		});
		bMostrarEntrada.setVisible(true);
		this.add(bMostrarEntrada);
		
		// LISTAR ENTRADAS
		bListarEntradas = ComponentsBuilder.createButton("Listar entradas", 407, 290, 200, 100);
		bListarEntradas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIEntrada.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_ENTRADAS_VISTA, tEntrada));
			}

		});
		bListarEntradas.setVisible(true);
		this.add(bListarEntradas);
		
		
		// LISTAR ENTRADAS POR INVERNADERO
		bListarEntradasPorInvernadero = ComponentsBuilder.createButton("Listar entradas por invernadero", 715, 290, 200, 100);
		bListarEntradasPorInvernadero.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIEntrada.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_ENTRADAS_POR_INVERNADERO_VISTA, tEntrada));
			}

		});
		bListarEntradasPorInvernadero.setVisible(true);
		this.add(bListarEntradasPorInvernadero);

	}

	@Override
	public void actualizar(Context context) {

	}
}
