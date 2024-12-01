package Negocio.MarcaJPA;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import java.util.Set;
import Negocio.ProveedorJPA.Proveedor;
import javax.persistence.ManyToMany;
import Negocio.ProductoJPA.Producto;
import javax.persistence.OneToMany;

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "Nombre") })
@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.MarcaJPA.Marca.findByid", query = "select obj from Marca obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.MarcaJPA.Marca.findByActivo", query = "select obj from Marca obj where :Activo = obj.Activo "),
		@NamedQuery(name = "Negocio.MarcaJPA.Marca.findByNombre", query = "select obj from Marca obj where :Nombre = obj.Nombre "),
		@NamedQuery(name = "Negocio.MarcaJPA.Marca.findByversion", query = "select obj from Marca obj where :version = obj.version "),
		@NamedQuery(name = "Negocio.MarcaJPA.Marca.findByproveedor", query = "select obj from Marca obj where :proveedor MEMBER OF obj.proveedor "),
		@NamedQuery(name = "Negocio.MarcaJPA.Marca.findByproducto", query = "select obj from Marca obj where :producto MEMBER OF obj.producto "),
		@NamedQuery(name = "Negocio.MarcaJPA.Marca.findByPaisOrigen", query = "select obj from Marca obj where :PaisOrigen = obj.PaisOrigen ") })
public class Marca implements Serializable {

	private static final long serialVersionUID = 0;

	@Id
	private Integer id;

	private Boolean activo;

	private String nombre;

	private Integer version;

	@ManyToMany(mappedBy = "marca")
	private Set<Proveedor> proveedor;

	@OneToMany(mappedBy = "marca")
	private Set<Producto> producto;

	private String paisOrigen;


	public Marca() {

	}
	// TODO: nuevo
	public Marca(TMarca marca) {

	}
	
	
	public Integer getId() {
		return this.id;
	}

	public String getPaisOrigen() {
		return this.paisOrigen;
	}

	public Boolean getActivo() {
		return this.activo;
	}


	public String getNombre() {
		return this.nombre;
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
	
	
	// TODO falta get y set de proveedor
	public Set<Proveedor> getProveedores(){
		return this.proveedor;
	}
	
	public void setProveedores(Set<Proveedor> proveedores) {
		this.proveedor = proveedores;
	}
}