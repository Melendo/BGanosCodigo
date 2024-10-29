package Presentacion.Fabricante;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Negocio.Fabricante.TFabricante;
import Negocio.Fabricante.TFabricanteExtranjero;
import Negocio.Fabricante.TFabricanteLocal;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;

@SuppressWarnings("serial")
public class GUIFabricante extends JFrame implements IGUI {
	
	private TFabricante tFabricante;
	private HashSet<TFabricante> hSFabricante;
	private HashSet<TFabricanteLocal> hSFabricanteL;
	private HashSet<TFabricanteExtranjero> hSFabricanteE;
	private JButton bAlta;
	private JPanel mainPanel; 
	
	public GUIFabricante() {
		super("Fabricante");
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
	
	private void initGUI(){
		
		tFabricante = new TFabricante();
		hSFabricante = new HashSet<TFabricante> ();
		hSFabricanteL = new HashSet<TFabricanteLocal> ();
		hSFabricanteE = new HashSet<TFabricanteExtranjero> ();
		JLabel label = ComponentsBuilder.createLabel("Fabricante", 250, 30, 500, 50, Color.BLACK);
		this.add(label);
		
		//Alta
		bAlta = ComponentsBuilder.createButton("Alta Fabricante", 100, 100, 185, 100);
		bAlta.setVisible(true);
		this.add(bAlta);
		bAlta.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIFabricante.this.setVisible(false);
				ApplicationController.getInstance().manageRequest(new Context(Evento.ALTA_FABRICANTE_VISTA, tFabricante));
			}

		});
		
		
	}
	
	@Override
	public void actualizar(Context context) {
		// TODO Auto-generated method stub
		
	}
}