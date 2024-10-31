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
import Presentacion.SistemaDeRiego.GUIListarSistemaDeRiegoPorFabricante;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

import Negocio.Entrada.TEntrada;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GUILIstarEntradasPorInvernadero extends JFrame implements IGUI {

	private JLabel jLabel;

	private JButton jButton;

	private JTextField jTextField;

	private JPanel m;

	
	public GUILIstarEntradasPorInvernadero() {
        super("Listar entradas por invernadero");
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int ancho = 630;
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
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setContentPane(mainPanel);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel(
				"Introduzca el ID del invernadero del que desea consultar sus entradas", 1, 10, 80, 20, Color.BLACK);
		msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(msgIntroIDCabecera);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelID = new JPanel();
		mainPanel.add(panelID);

		JLabel textIdHabitat = ComponentsBuilder.createLabel("ID invernadero: ", 10, 100, 80, 20, Color.BLACK);
		panelID.add(textIdHabitat);

		JTextField id = new JTextField();
		id.setPreferredSize(new Dimension(250, 30));

		id.setEditable(true);
		panelID.add(id);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));

		JPanel panelBotones = new JPanel();
		mainPanel.add(panelBotones);

		JButton botonAceptar = new JButton("Aceptar");
		botonAceptar.setBounds(75, 50, 100, 100);
		botonAceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUILIstarEntradasPorInvernadero.this.setVisible(false);
					try {
						Integer id_entrada = Integer.parseInt(id.getText());
						//Vamos a tratar el error de campos nulos
						ApplicationController.getInstance().manageRequest(new Context (Evento.LISTAR_ENTRADAS_POR_INVERNADERO,
								!id.getText().isEmpty() ? id_entrada : 0));
				
					} catch (Exception ex) {
						// TODO
//		    			ApplicationController.getInstance().manageRequest(new Context (Evento.V_ERRORES, -4));
					}

			}
		});
		panelBotones.add(botonAceptar);
		
		panelBotones.add(botonAceptar);

		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(200, 50, 100, 100);
		botonCancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUILIstarEntradasPorInvernadero.this.setVisible(false);
				// TODO
				ApplicationController.getInstance().manageRequest(new Context(Evento.ENTRADA_VISTA, null));
			}
		});
		panelBotones.add(botonCancelar);

		this.setVisible(true);
		this.setResizable(true);
		
		
		
//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//        this.setContentPane(mainPanel);
//
//        // Panel Central
//        JPanel panelCentro = new JPanel();
//        panelCentro.setLayout(new FlowLayout(FlowLayout.CENTER)); 
//        mainPanel.add(panelCentro);
//
//        // Campo de entrada para el invernadero
//        JLabel labelInvernadero = new JLabel("Ingrese el id del invernadero:");
//        panelCentro.add(labelInvernadero);
//        
//        JTextField fieldInvernadero = new JTextField();
//        fieldInvernadero.setPreferredSize(new Dimension(250, 30));
//        panelCentro.add(fieldInvernadero);
//
//        // Boton Buscar
//        JButton botonBuscar = new JButton("Buscar");
//        botonBuscar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                buscarPorInvernadero();
//            }
//        });
//        panelCentro.add(botonBuscar);
//        
//        // Tabla
//		String[] nombreColumnas = { "ID","Fecha","Precio","Stock","Activo", "Id Invernadero" };
//		JTable tabla = ComponentsBuilder.createTable(0, nombreColumnas.length, nombreColumnas, null); 
//        JScrollPane scroll = new JScrollPane(tabla);
//        scroll.setPreferredSize(new Dimension(750, 250));
//        mainPanel.add(scroll);
//        
//        // Panel de botones
//        JPanel panelBotones = new JPanel();
//        JButton botonCancelar = new JButton("Cancelar");
//        botonCancelar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                GUILIstarEntradasPorInvernadero.this.setVisible(false);
//                ApplicationController.getInstance().manageRequest(new Context(Evento.ENTRADA_VISTA, null));
//            }
//        });
//        panelBotones.add(botonCancelar);
//        mainPanel.add(panelBotones);
//
//        this.setVisible(true);
        
        
	}

	
//	private void buscarPorInvernadero() {
//				
//	}

	// TODO, esta constructora no estaba
	public GUILIstarEntradasPorInvernadero(Set<TEntrada> datos) {
		
	}

	@Override
	public void actualizar(Context context) {
		if(context.getEvento() == Evento.LISTAR_ENTRADAS_POR_INVERNADERO_OK) {
            JOptionPane.showMessageDialog(this, "Entradas listadas correctamente", "exito", JOptionPane.INFORMATION_MESSAGE);

		} else if(context.getEvento() == Evento.LISTAR_ENTRADAS_POR_INVERNADERO_KO) {
            JOptionPane.showMessageDialog(this, "Error al listar entradas", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}