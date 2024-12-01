/**
 * 
 */
package Presentacion.VentaJPA;

import javax.swing.JFrame;

import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;
import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Factura.TLineaFactura;


public class GUIDevolverVenta extends JFrame implements IGUI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField idVentaField;


	public GUIDevolverVenta(){
		super("Devolver Venta");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 400;
        int alto = 250;
        int x = (pantalla.width - ancho) / 2;
        int y = (pantalla.height - alto) / 2;
        this.setBounds(x, y, ancho, alto);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
	}
	
	
	public void initGUI() {
		JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.setContentPane(mainPanel);

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel panelIDFactura = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainPanel.add(panelIDFactura);

        JLabel labelIDFactura = ComponentsBuilder.createLabel("ID Venta: ", 10, 10, 80, 20, Color.BLACK);
        panelIDFactura.add(labelIDFactura);

        idVentaField = new JTextField();
        idVentaField.setPreferredSize(new Dimension(250, 30));
        panelIDFactura.add(idVentaField);
        

        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mainPanel.add(panelBotones);

        JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	//GUIDevolverFactura.this.setVisible(false);

                try {
                    int idFactura = Integer.parseInt(idVentaField.getText());

                    TLineaFactura lf = new TLineaFactura();
                    lf.setidFactura(idFactura);
                    
                    ApplicationController.getInstance().manageRequest(new Context(Evento.DEVOLVER_VENTA, lf));
                } catch (NumberFormatException ex) {
	    			JOptionPane.showMessageDialog(GUIDevolverVenta.this, "Los datos no son correctos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panelBotones.add(botonAceptar);

        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	GUIDevolverVenta.this.setVisible(false);
                ApplicationController.getInstance().manageRequest(new Context(Evento.VENTA_VISTA, null));
            }
        });
        panelBotones.add(botonCancelar);

        this.setVisible(true);
        this.setResizable(true);
	}

	
	public void actualizar(Context context) {
		int resultado = (int) context.getDatos();
        if (context.getEvento() == Evento.DEVOLVER_VENTA_OK) {
        	
            JOptionPane.showMessageDialog(this, "Venta devuelta correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else if (context.getEvento() == Evento.DEVOLVER_VENTA_KO) {
        	
            switch (resultado) {
            case -1:
                JOptionPane.showMessageDialog(this, "Se ha producido un error interno", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case -2:
                JOptionPane.showMessageDialog(this, "Datos incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Error desconocido al cerrar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            }
        }
	}
}