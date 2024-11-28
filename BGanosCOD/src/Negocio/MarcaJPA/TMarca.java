package Negocio.MarcaJPA;

public class TMarca {

	private Integer id;

	private String paisOrigen;

	private String nombre;

	private Boolean activo;

	public TMarca(Integer id, String nombre, String paisOrigen, Boolean activo) {
		this.id = id;
		this.nombre = nombre;
		this.paisOrigen = paisOrigen;
		this.activo = activo;
	}

	public TMarca(String nombre, String paisOrigen, Boolean activo) {
		this.nombre = nombre;
		this.paisOrigen = paisOrigen;
		this.activo = activo;
	}

	public TMarca() {
	}

	public Integer getId() {
		return this.id;
	}

	// TODO: cambiar en todos los set el parámetro que se le pasa, pasarle el
	// atributo a cambiar
	public void setId(Integer id) {
		this.id = id;
	}

	public String getPais() {
		return this.paisOrigen;
	}

	public void setPais(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}