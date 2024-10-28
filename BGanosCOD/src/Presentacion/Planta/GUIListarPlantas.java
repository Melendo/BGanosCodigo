/**
 * 
 */
package Presentacion.Planta;

import javax.swing.JFrame;
import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

import javax.swing.JLabel;

import java.util.Set;

import javax.swing.JButton;
import javax.swing.JTextField;

import Negocio.Planta.TPlanta;

import javax.swing.JPanel;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author airam
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class GUIListarPlantas extends JFrame implements IGUI {


	public GUIListarPlantas(Set<TPlanta> plantas) {
		
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public void initGUI() {

	}



	@Override
	public void actualizar(Context context) {
		switch(context.getEvento()) {
		case Evento.LISTAR_PLANTAS_KO:
		
			break;
		case  Evento.LISTAR_PLANTAS_OK:
		
			break;
		default:
			
			break;
		
	}
	}
}