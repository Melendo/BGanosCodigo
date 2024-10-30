/**
 * 
 */
package Presentacion.Planta;

import javax.swing.JFrame;

import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.GUIMSG;


import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Negocio.Planta.TPlanta;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author airam
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIListarPlantas extends JFrame implements IGUI {


	public GUIListarPlantas() {
		
		initGUI();
	}

	
	private static final long serialVersionUID = 1L;
	
	private static final String headers[] = {"ID", "Nombre", "cientifico", "ID Invernadero"};
	
	private JTable table;
	private DefaultTableModel model;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void initGUI() {
    	this.setTitle("LISTAR PLANTAS");
    	this.setVisible(false);
    	// MAIN PANEL
    	JPanel mainPanel = new JPanel(new BorderLayout());
    	this.setContentPane(mainPanel);
    	
		// TABLE MODEL
    	this.model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			@Override
    		public boolean isCellEditable(int row, int col) { return false; }
    	};
    	this.model.setColumnCount(0);
    	for (String s : headers)
    		this.model.addColumn(s);
    	
    	// MAIN TABLE
    	this.table = new JTable(this.model);
  
		// BUTTONS PANEL

		mainPanel.add(new JScrollPane(this.table), BorderLayout.CENTER);
		
		Dimension dims_frame = this.getContentPane().getSize();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frame = new Dimension((int) dims_frame.getWidth(), (int) dims_frame.getHeight());
    	
    	
		this.setPreferredSize(new Dimension(400, 400));
		this.setLocation(dim.width / 2 - frame.getSize().width / 2 - 200, dim.height / 2 - frame.getSize().height / 2 - 200);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		
		ApplicationController.getInstance().manageRequest(new Context(Evento.LISTAR_PLANTAS,null));
	}
	


	@Override
	public void actualizar(Context context) {
		

		
		switch(context.getEvento()) {
		case Evento.LISTAR_PLANTAS_KO:
			
			
			GUIMSG.showMessage("No hay plantas", "LISTAR PLANTAS", true);
			
			break;
		case  Evento.LISTAR_PLANTAS_OK:
		
			this.model.setRowCount(0);
			for (TPlanta tp : ((ArrayList<TPlanta>) context.getDatos())) {
				String id = "" + tp.get_id(), nombre = tp.get_nombre(), cientifico = "" + tp.get_nombre_cientifico(), invernadero = "" + tp.get_id_invernadero();
				
				if (tp.getActivo()) {
					id = this.toBold(id);
					nombre = this.toBold(nombre);
					cientifico = this.toBold(cientifico);
					invernadero = this.toBold(invernadero);
					
					DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
					renderer.setFont(renderer.getFont().deriveFont(Font.BOLD));
					table.getColumnModel().getColumn(1).setCellRenderer(renderer);
				}
				this.model.addRow(new Object[] {id, nombre, cientifico, invernadero});
			}
			this.setVisible(true);
			
			break;
		default:
			GUIMSG.showMessage("ERROR INESPERADO", "LISTAR PLANTAS", true);
			break;
		
	}
	}
	
	private String toBold(String s) {
    	return "<html><b>" + s + "</b></html>";
    }
}