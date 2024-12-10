package Integracion.FactoriaQuery;

public abstract class FactoriaQuery {

	private static FactoriaQuery instance;

	public static FactoriaQuery getInstance() {

		if (instance == null)
			instance = new FactoriaQueryImp();

		return instance;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param id
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Query getNewQuery(Integer id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public abstract Query getNewQuery(String nombre);

}