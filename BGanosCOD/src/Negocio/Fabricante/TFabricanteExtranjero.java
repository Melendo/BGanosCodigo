package Negocio.Fabricante;

public class TFabricanteExtranjero extends TFabricante {
	private Integer aranceles;
	private String pais_de_origen;

	public Integer getAranceles() {
		return aranceles;
	}

	public void setAranceles(Integer aranceles) {
		this.aranceles = aranceles;
	}

	public String getPaisDeOrigen() {
		return pais_de_origen;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param paisOrigen
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public void setPaisDeOrigen(Integer paisOrigen) {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void setPaisDeOrigen(String paisOrigen) {
		this.pais_de_origen = paisOrigen;
	}
}