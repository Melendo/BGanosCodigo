package Negocio.MarcaJPA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

import java.util.List;
import Negocio.ProveedorJPA.Proveedor;
import javax.persistence.ManyToMany;
import Negocio.ProductoJPA.Producto;
import javax.persistence.OneToMany;

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "Nombre") })
@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.MarcaJPA.Marca.findByid", query = "select obj from Marca obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.MarcaJPA.Marca.findByActivo", query = "select obj from Marca obj where :activo = obj.activo "),
		@NamedQuery(name = "Negocio.MarcaJPA.Marca.findBynombre", query = "select obj from Marca obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.MarcaJPA.Marca.findByversion", query = "select obj from Marca obj where :version = obj.version "),
		@NamedQuery(name = "Negocio.MarcaJPA.Marca.findByproveedor", query = "select obj from Marca obj where :proveedor MEMBER OF obj.proveedor "),
		@NamedQuery(name = "Negocio.MarcaJPA.Marca.findByproducto", query = "select obj from Marca obj where :producto MEMBER OF obj.producto "),
		@NamedQuery(name = "Negocio.MarcaJPA.Marca.findByPaisOrigen", query = "select obj from Marca obj where :paisOrigen = obj.paisOrigen ") })
public class Marca implements Serializable {

	private static final long serialVersionUID = 0;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;

	private String paisOrigen;

	@Version
	private Integer version;

	private Boolean activo;

	@ManyToMany(mappedBy = "marca")
	private List<Proveedor> proveedor;

	@OneToMany(mappedBy = "marca")
	private List<Producto> producto;
	
	private int contadorProductos;

	public Marca() {
 
	}

	// TODO: nuevo
	public Marca(TMarca marca) {
		id = marca.getId();
		nombre = marca.getNombre();
		paisOrigen = marca.getPais();		
		activo = marca.getActivo();
	}

	public Integer getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getPaisOrigen() {
		return this.paisOrigen;
	}

	public Boolean getActivo() {
		return this.activo;
	}

	// TODO: le añadí un TMarca como parámetro
	public void transferToEntity(TMarca marca) {
		this.nombre = marca.getNombre();
		this.paisOrigen = marca.getPais();
		this.activo = marca.getActivo();
	}

	// TODO: nuevo método
	public TMarca entityToTransfer() {
		return new TMarca(this);
	}

	// TODO: todos los set no tienen parámetros, hay que ponerselos
	// TODO: el tipo de todos los set era Void, no void, cambiar
	// TODO: estaba: public Void setId()
	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	// TODO: falta get y set de proveedor
	public List<Proveedor> getProveedores() {
		return this.proveedor;
	}

	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedor = proveedores;
	}

	// TODO: faltan get y set de producto
	public List<Producto> getProductos() {
		return this.producto;
	}
	
	// TODO: añadido el set
	public void setProductos(List<Producto> productos) {
		this.producto = productos;
	}
	
	public int getContadorProductos() {
		return this.contadorProductos;
	}
	
	public void setContadorProductos(int contador) {
		this.contadorProductos = contador;
	}
}