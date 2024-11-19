
package Presentacion.FactoriaVistas;

import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;

public abstract class FactoriaVistas {

	private static FactoriaVistas instance;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public static FactoriaVistas getInstance() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public static FactoriaVistas getIntance() {
		if (instance == null) {
			instance = new FactoriaVistasImp();
		}
		return instance;
	}

	public abstract IGUI generarVista(Context context);
}