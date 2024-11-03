/**
 * 
 */
package Presentacion.Controller.Command.CommandFactura;

import java.util.Set;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Factura.TFactura;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author airam
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class ListarFacturasCommand implements Command {
	/** 
	* (non-Javadoc)
	* @see Command#execute(Object datos)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Context execute(Object datos) {
		Set<TFactura> res = FactoriaNegocio.getInstance().getFacturaSA().listarFacturas();
		 return new Context(Evento.LISTAR_FACTURAS_VISTA, res);
	}
}