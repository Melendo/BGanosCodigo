/**
 * 
 */
package Presentacion.Controller.Command.CommandFactura;

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
public class ModificarFacturaCommand implements Command {
	/** 
	* (non-Javadoc)
	* @see Command#execute(Object datos)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Context execute(Object datos) {
		int res = FactoriaNegocio.getInstance().getFacturaSA().modificarFactura((TFactura)datos);
		if(res > -1){
			return new Context(Evento.MODIFICAR_FACTURA_OK,res);
		}else {
			return new Context(Evento.MODIFICAR_FACTURA_KO,res);
		}
	}
}