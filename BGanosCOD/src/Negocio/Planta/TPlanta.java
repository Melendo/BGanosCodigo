package Negocio.Planta;

public class TPlanta {

	private String nombre_cientifico;
	private String nombre;
	private Integer id;
	private Boolean activo;
	private Integer tipo;
	private Integer id_invernadero;
	
	public String get_nombre_cientifico() {
		return nombre_cientifico;
	}
	
	public String get_nombre() {
		return nombre;
	}

	public Integer get_id() {
		return id;
	}

	public Integer get_tipo(){
		return tipo;
	}
	
	public Integer get_id_invernadero() {
		return id_invernadero;
	}
	
	public Boolean getActivo() {
		return activo;
	}
	
	public void set_nombre_cientifico(String nombreCientifico) {
		this.nombre_cientifico = nombreCientifico;
	}

	public void set_nombre(String nombre) {
		this.nombre = nombre;
	}

	public void set_id(Integer id) {
		this.id = id;
	}

	public void set_tipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	public void set_id_invernadero(Integer id_invernadero) {
		this.id_invernadero = id_invernadero;
	}
	
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	
	public String toString() {

	}
	
}