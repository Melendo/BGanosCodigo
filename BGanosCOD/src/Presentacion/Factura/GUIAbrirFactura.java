/**
 * 
 */
package Presentacion.Factura;

import javax.swing.JFrame;

import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Negocio.Factura.TCarrito;
import Negocio.Factura.TFactura;
import Negocio.Factura.TLineaFactura;

import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author airam
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIAbrirFactura extends JFrame implements IGUI {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private JLabel jLabel;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private JPanel jPanel;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private JTextField jTextField;
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	private JButton jButton;

	public GUIAbrirFactura() {
		initGUI();
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void initGUI() {
		// begin-user-code
		// TODO Auto-generated method stub
		TCarrito carrito = new TCarrito();
		carrito.setLineaFactura(new HashSet<TLineaFactura>());
        ApplicationController.getInstance().manageRequest(new Context(Evento.CERRAR_FACTURA_VISTA, carrito));
		// end-user-code
	}

	/** 
	* (non-Javadoc)
	* @see IGUI#actualizar(Context context)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/


	@Override
	public void actualizar(Context context) {
		// TODO Auto-generated method stub
		
	}
}