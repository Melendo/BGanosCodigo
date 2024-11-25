package Negocio.VentaJPA;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.persistence.NamedQueries;
import javax.persistence.ManyToOne;

import Negocio.ProductoJPA.Producto;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.VentaJPA.LineaVenta.findByid", query = "select obj from LineaVenta obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.VentaJPA.LineaVenta.findBycantidad", query = "select obj from LineaVenta obj where :cantidad = obj.cantidad "),
		@NamedQuery(name = "Negocio.VentaJPA.LineaVenta.findByprecio", query = "select obj from LineaVenta obj where :precio = obj.precio "),
		@NamedQuery(name = "Negocio.VentaJPA.LineaVenta.findByventa", query = "select obj from LineaVenta obj where :venta = obj.venta "),
		@NamedQuery(name = "Negocio.VentaJPA.LineaVenta.findByproducto", query = "select obj from LineaVenta obj where :producto = obj.producto "),
		@NamedQuery(name = "Negocio.VentaJPA.LineaVenta.findByversion", query = "select obj from LineaVenta obj where :version = obj.version ") })
public class LineaVenta implements Serializable {

	private static final long serialVersionUID = 0;

	public LineaVenta() {
	}

	public LineaVenta(TLineaVenta tLineaVenta) {
		id.setIdProducto(tLineaVenta.getIdProducto());
		id.setIdVenta(tLineaVenta.getIdVenta());
		cantidad = tLineaVenta.getCantidad();
		precio = tLineaVenta.getPrecio();
		// TODO
		/*
		 * venta =... producto =...
		 */

	}

	@EmbeddedId
	private idLineaVenta id;
	private Integer cantidad;
	private Double precio;

	@ManyToOne
	private Venta venta;

	@ManyToOne
	private Producto producto;

	@Version
	private Integer version;

	public void transferToEntity(TLineaVenta lineaVenta) {// TODO

	}

	public TLineaVenta entityToTransfer() {
		TLineaVenta lineaVenta = new TLineaVenta();
		lineaVenta.setCantidad(cantidad);
		lineaVenta.setIdPoducto(id.getIdProducto());
		lineaVenta.setIdVenta(id.getIdventa());
		lineaVenta.setPrecio(precio);

		return lineaVenta;
	}

	public idLineaVenta getId() {
		return id;
	}

	public Producto getProducto() {
		return producto;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}
}