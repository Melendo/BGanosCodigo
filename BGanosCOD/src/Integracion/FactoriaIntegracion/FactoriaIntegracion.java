
package Integracion.FactoriaIntegracion;

import Integracion.Invernadero.InvernaderoDAO;
import Integracion.Invernadero.TieneDAO;
import Integracion.Entrada.EntradaDAO;
import Integracion.Fabricante.FabricanteDAO;
import Integracion.Factura.FacturaDAO;
import Integracion.Factura.LineaFacturaDAO;
import Integracion.Planta.PlantaDAO;
import Integracion.SistemaDeRiego.SistemaDeRiegoDAO;

public abstract class FactoriaIntegracion {

	private static FactoriaIntegracion instance;

	public static FactoriaIntegracion getInstance() {
		if (instance == null)
			instance = new FactoriaIntegracionImp();
		return instance;
	}

	public abstract FacturaDAO getFacturaDAO();

	public abstract EntradaDAO getEntradaDAO();

	public abstract InvernaderoDAO getInvernaderoDAO();

	public abstract SistemaDeRiegoDAO getSistemaDeRiegoDAO();

	public abstract FabricanteDAO getFabricanteDAO();

	public abstract PlantaDAO getPlantaDAO();

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public SistemaDeRiegoDAO getSistemaDeRiesoDAO() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public TieneDAO getTieneDAO() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public LineaFacturaDAO getLineaFacturaDAO() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public abstract LineaFacturaDAO getDAOLineaFactura();

	public abstract TieneDAO getDaoTiene();

}