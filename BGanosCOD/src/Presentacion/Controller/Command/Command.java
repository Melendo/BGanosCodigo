/**
 * 
 */
package Presentacion.Controller.Command;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author airam
 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public abstract interface Command {
	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param datos
	* @return
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public abstract Context execute(Object datos);
}