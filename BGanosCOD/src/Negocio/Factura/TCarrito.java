package Negocio.Factura;

import java.util.Set;

public class TCarrito {

	private Set<TLineaFactura> tLineaFactura;

	private TFactura tFactura;

	public Set<TLineaFactura> getLineasFactura() {
		return tLineaFactura;
	}

	public TFactura getFactura() {
		return tFactura;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param tLineaFactura
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Void setLineasFactura(Set tLineaFactura) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public void setLineaFactura(Set<TLineaFactura> tLineaFactura) {
		this.tLineaFactura = tLineaFactura;
	}

	public void setFactura(TFactura tFactura) {
		this.tFactura = tFactura;
	}
}