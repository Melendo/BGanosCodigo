/**
 * 
 */
package Presentacion.VentaJPA;

import javax.swing.JFrame;

import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GUIBajaVenta extends JFrame implements IGUI {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField _id; 
	
	public GUIBajaVenta(){
		super("Baja Venta");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = 600;
        int alto = 400;
        int x = (pantalla.width - ancho) / 2;
        int y = (pantalla.height - alto) / 2;
        this.setBounds(x, y, ancho, alto);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initGUI();
	}
	
	public void initGUI() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); 
        this.setContentPane(mainPanel);

        gbc.gridwidth = 2; 
        JLabel msgIntro = new JLabel("Introduzca el ID de la venta a dar de baja", JLabel.CENTER);
        mainPanel.add(msgIntro, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;

        JLabel labelId = new JLabel("ID:");
        gbc.gridx = 0;
        mainPanel.add(labelId, gbc);
        _id = new JTextField(20);
        gbc.gridx = 1; 
        mainPanel.add(_id, gbc);

        
        JPanel panelBotones = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER; 
        mainPanel.add(panelBotones, gbc);

        JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idTexto = _id.getText();
                    Integer idSistema = Integer.parseInt(idTexto); 
                                
                    ApplicationController.getInstance().manageRequest(new Context(Evento.BAJA_SISTEMA_RIEGO, idSistema));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(GUIBajaVenta.this, "Error en el formato del ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panelBotones.add(botonAceptar);

        // Boton Cancelar
        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(new ActionListener() {
        	@Override
 	        public void actionPerformed(ActionEvent e) {
        		GUIBajaVenta.this.setVisible(false);
 	            ApplicationController.getInstance().manageRequest(new Context(Evento.VENTA_VISTA, null));
 	        }
        });
        panelBotones.add(botonCancelar);

        this.setVisible(true);
	}

	public void actualizar(Context context) {
		int resultado = (int) context.getDatos();
		 if (context.getEvento() == Evento.BAJA_VENTA_OK) {
			 	
	            JOptionPane.showMessageDialog(this, "Venta " + resultado + " dada de baja correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        } else if (context.getEvento() == Evento.BAJA_VENTA_KO) {
	        	
	            switch (resultado) {
	            case -2:
	                JOptionPane.showMessageDialog(this, "Error: La venta ya está dada de baja.", "Error", JOptionPane.ERROR_MESSAGE);
	                break;
	            case -404:
	                JOptionPane.showMessageDialog(this, "Error: La venta especificada no existe.", "Error", JOptionPane.ERROR_MESSAGE);
	                break;
	            default:
	                JOptionPane.showMessageDialog(this, "Error desconocido al modificar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
	                break;
	            }
	        }
	}
}