/**
 * 
 */
package Negocio.ProductoJPA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.Set;
import Negocio.VentaJPA.LineaVenta;
import javax.persistence.OneToMany;
import javax.persistence.NamedQueries;
import Negocio.MarcaJPA.Marca;
import javax.persistence.ManyToOne;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;


@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.ProductoJPA.Producto.findByid", query = "select obj from Producto obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.ProductoJPA.Producto.findBylineaVenta", query = "select obj from Producto obj where :lineaVenta MEMBER OF obj.lineaVenta "),
		@NamedQuery(name = "Negocio.ProductoJPA.Producto.findByversion", query = "select obj from Producto obj where :version = obj.version "),
		@NamedQuery(name = "Negocio.ProductoJPA.Producto.findBynombre", query = "select obj from Producto obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.ProductoJPA.Producto.findBystock", query = "select obj from Producto obj where :stock = obj.stock "),
		@NamedQuery(name = "Negocio.ProductoJPA.Producto.findByactivo", query = "select obj from Producto obj where :activo = obj.activo "),
		@NamedQuery(name = "Negocio.ProductoJPA.Producto.findByprecio", query = "select obj from Producto obj where :precio = obj.precio "),
		@NamedQuery(name = "Negocio.ProductoJPA.Producto.findBymarca", query = "select obj from Producto obj where :marca = obj.marca ") })

public class Producto implements Serializable {

	private static final long serialVersionUID = 0;

	@Id  
	private Integer id;

	@OneToMany(mappedBy = "producto")
	private Set<LineaVenta> lineaVenta;

	private int version;

	@Column(unique = true, nullable = false)
	private String nombre;

	private Integer stock;

	private Boolean activo;
	
	private double precio;

	@ManyToOne
	private Marca marca;

	public Producto() {

	}

	public Producto(TProducto producto) {
		this.nombre = producto.getNombre();
		this.activo = producto.getActivo();
		this.precio = producto.getPrecio();
		this.id = producto.getId();
		this.stock = producto.getStock();
	}
	
	public void setMarca(Marca m){
		marca = m;
	}

	public Integer getId() {
		return this.id;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}


	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	
	public String getNombre() {
		return this.nombre;
	}


	public Double getPrecio() {

		return this.precio;
		
	}


	public Integer getStock() {
		// begin-user-code
		// TODO Auto-generated method stub
		return this.stock;
		// end-user-code
	}


	public Boolean getActivo() {
		// begin-user-code
		// TODO Auto-generated method stub
		return this.activo;
		// end-user-code
	}

	public void transferToEntity(TProducto producto) {
		this.nombre = producto.getNombre();
		this.activo = producto.getActivo();
		this.precio = producto.getPrecio();
		this.id = producto.getId();
		this.stock = producto.getStock();
		
	}


	public TProducto entityToTransfer() {
		TProducto ttp = new TProducto();
		ttp.setActivo(activo);
		ttp.setId(id);
		ttp.setIdMarca(marca.getId());
		ttp.setNombre(nombre);
		ttp.setPrecio(precio);
		ttp.setStock(stock);

		return ttp;
	}
}