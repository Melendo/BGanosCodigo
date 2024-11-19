package Negocio.Factura;

import java.util.HashSet;
import java.util.Set;

public class TFacturaConEntradas {

	private Set<TLineaFactura> tLineaFactura;

	private TFactura tFactura;

	public Set<TLineaFactura> gettLineaFactura() {
		return tLineaFactura;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tLineaFatura
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Void incluirLineaFactura(TLineaFactura tLineaFatura) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public void incluirLineaEntrada(TLineaFactura TLineaFactura) {
		if (tLineaFactura == null)
			tLineaFactura = new HashSet<TLineaFactura>();
		tLineaFactura.add(TLineaFactura);
	}

	public TFactura gettFactura() {
		return tFactura;
	}

	public void settFactura(TFactura tFactura) {
		this.tFactura = tFactura;
	}

}