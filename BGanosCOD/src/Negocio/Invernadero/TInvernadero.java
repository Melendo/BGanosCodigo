package Negocio.Invernadero;

public class TInvernadero {

	private Integer id;

	private String sustrato;

	private String nombre;

	private String tipo_iluminacion;

	private Boolean activo;

	public TInvernadero(String nombre, String sustrato, String tipo_iluminacion) {
		this.nombre = nombre;
		this.sustrato = sustrato;
		this.tipo_iluminacion = tipo_iluminacion;
	}

	public TInvernadero(Integer id, String nombre, String sustrato, String tipo_iluminacion, Boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.sustrato = sustrato;
		this.tipo_iluminacion = tipo_iluminacion;
		this.activo = activo;
	}

	public TInvernadero() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSustrato() {
		return sustrato;
	}

	public void setSustrato(String sustrato) {
		this.sustrato = sustrato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo_iluminacion() {
		return tipo_iluminacion;
	}

	public void setTipo_iluminacion(String tipo_iluminacion) {
		this.tipo_iluminacion = tipo_iluminacion;
	}

	public Boolean isActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Integer getID() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/** 
	* <!-- begin-UML-doc -->
	* <!-- end-UML-doc -->
	* @param ID
	* @return
	* @generated "UML a JPA (com.ibm.xtools.transform.uml2.ejb3.java.jpa.internal.UML2JPATransform)"
	*/
	public Void setID(String ID) {
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
	public String toString() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

}