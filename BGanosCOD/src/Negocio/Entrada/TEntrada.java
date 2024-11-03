/**
 * 
 */
package Negocio.Entrada;

import java.util.Date;

public class TEntrada {

	private Integer id;
	
	private Integer idInvernadero;

	private Date fecha;

	private Float precio;

	private Integer stock_entradas;

	private Boolean activo;
	
	
	public TEntrada(Integer id, Date fecha, Float precio, Integer stock, Integer idInvernadero, Boolean activo) {
		this.id = id;
		this.fecha = fecha;
		this.precio = precio;
		this.stock_entradas = stock;
		this.idInvernadero = idInvernadero;
		this.activo = activo;
	}
	
	public TEntrada(Date fecha, Float precio, Integer stock, Integer idInvernadero, Boolean activo) {
		this.fecha = fecha;
		this.precio = precio;
		this.stock_entradas = stock;
		this.idInvernadero = idInvernadero;
		this.activo = activo;
	}
	
	public TEntrada() {
		
	}

	public Integer getId() {
		return id;
	}
	
	public Integer getIdInvernadero() {
		return idInvernadero;
	}

	public Date getFecha() {
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
	
	public void setIdInvernadero(Integer idInvernadero) {
		this.idInvernadero = idInvernadero;
	}

	public void setFecha(Date fecha) {
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