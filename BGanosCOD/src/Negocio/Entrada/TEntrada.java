/**
 * 
 */
package Negocio.Entrada;

import java.util.Date;

public class TEntrada {

	private Integer id;

	private String fecha;

	private Float precio;

	private Integer stock_entradas;

	private Boolean activo;

	public Integer getId() {
		return id;
	}

	// TODO aclarar si fecha es de tipo string o date
	public String getFecha() {
		return fecha;
	}

	public Float getPrecio() {
		return precio;
	}

	public Integer getStock() {
		return stock_entradas;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public void setStock(Integer stock) {
		this.stock_entradas = stock;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
}