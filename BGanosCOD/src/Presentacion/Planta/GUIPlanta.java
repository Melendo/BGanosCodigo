package Presentacion.Planta;

import javax.swing.JFrame;


import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.Planta.TPlanta;
import Negocio.Planta.TPlantaFrutal;
import Negocio.Planta.TPlantaNoFrutal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;

import Presentacion.FactoriaVistas.*;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;

public class GUIPlanta extends JFrame implements IGUI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton bAltaPlanta;
	private JButton bBajaPlanta;
	private JButton bModificarPlanta;
	private JButton bMostrarPlantaPorId;
	private JButton bListarPlantas;
	private JButton bListarPlantasPorTipo;
	private JButton bListarPlantasPorInvernadero;
	private JButton backButton;
	private JPanel j;
	
	//private TPlanta tPlanta;
	//private Set<TPlanta> hsPlanta;
	//private Set<TPlantaFrutal> hsPlantaFrutal;
	//private Set<TPlantaNoFrutal> hsPlantaNoFrutal;

	public GUIPlanta() {
		super("BGANOS");
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
	
	public void initGUI() {
	
		JLabel label = ComponentsBuilder.createLabel("Planta", 250, 30, 500, 50, Color.BLACK);
		this.add(label);
		
		//ALTA PLANTA
		bAltaPlanta = ComponentsBuilder.createButton("Alta Planta", 100, 100, 185, 100);
		bAltaPlanta.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIPlanta.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.ALTA_PLANTA_VISTA, null));
			}
		});
		bAltaPlanta.setVisible(true);
		this.add(bAltaPlanta);
		
		//BAJA PLANTA
		bBajaPlanta = ComponentsBuilder.createButton("Baja Planta", 350, 100, 185, 100);
		bBajaPlanta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIPlanta.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.BAJA_PLANTA_VISTA, null));
			}
		});
		bBajaPlanta.setVisible(true);
		this.add(bBajaPlanta);
		
		//MODIFICAR PLANTA
		bModificarPlanta = ComponentsBuilder.createButton("Modificar Planta", 600, 100, 185, 100);
		bModificarPlanta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIPlanta.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.MODIFICAR_PLANTA_VISTA, null));
			}
		});
		bModificarPlanta.setVisible(true);
		this.add(bModificarPlanta);
		
		//MOSTRAR PLANTA POR ID
		bMostrarPlantaPorId = ComponentsBuilder.createButton("Mostar Planta Por Id", 100, 250, 185, 100);
		bMostrarPlantaPorId.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIPlanta.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.MOSTRAR_PLANTA_POR_ID_VISTA, null));
			}
		});
		bMostrarPlantaPorId.setVisible(true);
		this.add(bMostrarPlantaPorId);
		
		//LISTAR PLANTAS
		bListarPlantas = ComponentsBuilder.createButton("Listar Plantas", 350, 250, 185, 100);
		bListarPlantas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIPlanta.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_PLANTAS_VISTA, null));
			}
		});
		bListarPlantas.setVisible(true);
		this.add(bListarPlantas);
		
		//LISTAR PLANTAS POR TIPO
		bListarPlantasPorTipo = ComponentsBuilder.createButton("Listar Plantas Por Tipo", 600, 250, 185, 100);
		bListarPlantasPorTipo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIPlanta.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_PLANTAS_POR_TIPO_VISTA, null));
			}
		});
		bListarPlantasPorTipo.setVisible(true);
		this.add(bListarPlantasPorTipo);
		
		//LISTAR PLANTAS POR INVERNADERO
		bListarPlantasPorInvernadero = ComponentsBuilder.createButton("Listar Plantas Por Invernadero", 100, 400, 185, 100);
		bListarPlantasPorInvernadero.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIPlanta.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_PLANTAS_INVERNADERO_VISTA, null));
			}
		});
		bListarPlantasPorInvernadero.setVisible(true);
		this.add(bListarPlantasPorInvernadero);
		
		
		//BACK BUTTON
		backButton = ComponentsBuilder.createBackButton();
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIPlanta.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.VISTA_PRINCIPAL,null));
				dispose();
			}
		}); 
		backButton.setVisible(true);
		this.add(backButton);
		
		getContentPane().add(j);
	}
	
	@Override
	public void actualizar(Context context) {}
}