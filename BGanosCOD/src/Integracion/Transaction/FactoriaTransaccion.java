package Integracion.Transaction;

public abstract class FactoriaTransaccion {

	private static FactoriaTransaccion instance;

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param instance
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public static void getInstance(FactoriaTransaccion instance) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public static synchronized FactoriaTransaccion getInstance() {
		if (instance == null) {
			instance = new FactoriaTransaccionImp();
		}
		return instance;
	}

	public abstract Transaccion createTransaction() throws Exception;
}