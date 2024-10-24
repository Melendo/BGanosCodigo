/**
 * 
 */
package Negocio.SistemaDeRiego;

public class TSistemaDeRiego {
	
	private Integer id;
	private String nombre;
	private Integer potenciaRiego;
	private Integer cantidad_agua;
	private Integer frecuencia;
	private Boolean activo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPotenciaRiego() {
		return potenciaRiego;
	}

	public void setPotenciaRiego(Integer potenciaRiego) {
		this.potenciaRiego = potenciaRiego;
	}

	public Integer getFrecuencia() {
		return frecuencia;
	}

	public void setFrecuencia(Integer frecuencia) {
		this.frecuencia = frecuencia;
	}


	public Integer getCantidad_agua() {
		return cantidad_agua;
	}

	public void setCantidad_agua(Integer cantidadAgua) {
		this.cantidad_agua = cantidadAgua;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}