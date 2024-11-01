/**
 * 
 */
package Presentacion.Controller.Command.CommandFactura;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Factura.TFactura;
import Negocio.Factura.TFacturaConEntradas;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author airam
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class MostrarFacturaPorIDCommand implements Command {
	/** 
	* (non-Javadoc)
	* @see Command#execute(Object datos)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Context execute(Object datos) {
		TFacturaConEntradas res = FactoriaNegocio.getInstance().getFacturaSA().mostrarFacturaPorID((Integer)datos);
		TFactura factura = res.gettFactura();
		if (factura.getid() <= 0)
			return new Context(Evento.MOSTRAR_FACTURA_POR_ID_KO,factura);
		else
			return new Context(Evento.MOSTRAR_FACTURA_POR_ID_OK,res);
	}
}