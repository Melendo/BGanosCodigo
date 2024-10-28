/**
 * 
 */
package Presentacion.Planta;

import javax.swing.JFrame;


import Presentacion.ComponentsBuilder.ComponentsBuilder;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.GUIMSG;
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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author airam
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIBajaPlanta extends JFrame implements IGUI {


	public GUIBajaPlanta() {
		super("Baja Planta");
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
	
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void initGUI() {
		JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    this.setContentPane(mainPanel);
	    this.setVisible(true);
	    mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
	    
	    JLabel msgIntroIDCabecera = ComponentsBuilder.createLabel("BAJA PLANTA", 1, 10, 80, 20, Color.BLACK);
	    msgIntroIDCabecera.setAlignmentX(CENTER_ALIGNMENT);
	    mainPanel.add(msgIntroIDCabecera);

	    mainPanel.add(Box.createRigidArea(new Dimension(0, 40)));
	    JPanel panelID = new JPanel();
	    //PANEL DE TIPO DE LA PLANTA

	    mainPanel.add(panelID);

 		
 		
 		
 		JLabel labelID = ComponentsBuilder.createLabel("ID de la planta: ", 10, 100, 80, 20,Color.BLACK);
 		panelID.add(labelID);
 		JTextField textID = new JTextField(25);
 		panelID.add(textID);
 		
 		//PANEL DE LOS BOTONES
 		JPanel panelBotones = new JPanel();
 		mainPanel.add(panelBotones);
 		
 		//BOTON DE ACEPTAR
 		JButton botonAceptar = new JButton("Aceptar");
 		
 		
 		botonAceptar.addActionListener(new ActionListener() {
 			
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				setVisible(false);
 				
 				try {
 					
 					int idtmp = Integer.parseInt(textID.getText());
 		
 						
 						ApplicationController.getInstance().manageRequest(new Context(Evento.BAJA_PLANTA,idtmp));
 			
 				} catch (Exception e1) {
 					//ApplicationController.getInstance().manageRequest(new Context(Evento.vista, -4));
 				}
 			}
 		});
 		
 		panelBotones.add(botonAceptar);
	    
		// end-user-code
	}


	@Override
	public void actualizar(Context context) {
		switch(context.getEvento()) {
		case Evento.BAJA_PLANTA_OK:
			GUIMSG.showMessage("Planta dado de baja con ID: " + context.getDatos().toString(), "BAJA PLANTA", false);
			break;
		case  Evento.BAJA_PLANTA_KO:
			GUIMSG.showMessage("Planta NO se pudo dar de baja con ID: " + context.getDatos().toString(), "BAJA PLANTA", true);
			break;
		default:
			GUIMSG.showMessage("ERROR INESPERADO", "BAJA PLANTA", true);
			break;
		
	}
}
}