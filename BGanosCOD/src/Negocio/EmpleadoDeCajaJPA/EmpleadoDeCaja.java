/**
 * 
 */
package Negocio.EmpleadoDeCajaJPA;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import Negocio.VentaJPA.Venta;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import java.util.Set;
import Negocio.TurnoJPA.Turno;
import javax.persistence.OneToMany;
import javax.persistence.InheritanceType;
import javax.persistence.Inheritance;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "DNI") })
@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja.findByid", query = "select obj from EmpleadoDeCaja obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja.findByventa", query = "select obj from EmpleadoDeCaja obj where :venta MEMBER OF obj.venta "),
		@NamedQuery(name = "Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja.findByturno", query = "select obj from EmpleadoDeCaja obj where :turno MEMBER OF obj.turno "),
		@NamedQuery(name = "Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja.findBynombre", query = "select obj from EmpleadoDeCaja obj where :nombre = obj.nombre "),
		@NamedQuery(name = "Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja.findByApellido", query = "select obj from EmpleadoDeCaja obj where :Apellido = obj.Apellido "),
		@NamedQuery(name = "Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja.findByDNI", query = "select obj from EmpleadoDeCaja obj where :DNI = obj.DNI "),
		@NamedQuery(name = "Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja.findByTelefono", query = "select obj from EmpleadoDeCaja obj where :Telefono = obj.Telefono "),
		@NamedQuery(name = "Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja.findBySueldo", query = "select obj from EmpleadoDeCaja obj where :Sueldo = obj.Sueldo "),
		@NamedQuery(name = "Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja.findByid_turno", query = "select obj from EmpleadoDeCaja obj where :id_turno = obj.id_turno "),
		@NamedQuery(name = "Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja.findByversion", query = "select obj from EmpleadoDeCaja obj where :version = obj.version ") })
public abstract class EmpleadoDeCaja implements Serializable {
	
	private static final long serialVersionUID = 0;

	public EmpleadoDeCaja() {
	}

	@Id
	private Integer id;

	@OneToMany(mappedBy = "empleadoDeCaja")
	private Set<Venta> venta;
	@ManyToOne
	private Turno turno;
	
	private String nombre;
	private String Apellido;
	private String DNI;
	private Double Sueldo;
	private Integer telefono;
	private Integer version;
	private Boolean activo;
	private Integer tipo;
	
	
	public void transferToEntity(TEmpleadoDeCaja empleadoDeCaja) {
		this.setDNI(empleadoDeCaja.getDNI());
        this.setNombre(empleadoDeCaja.getNombre());
        this.setApellido(empleadoDeCaja.getApellido());
        this.setSueldo(empleadoDeCaja.getSueldo());
        this.setTelefono(empleadoDeCaja.getTelefono());
        this.setActivo(empleadoDeCaja.getActivo());
        this.setTipo(empleadoDeCaja.getTipo());
	}

	public EmpleadoDeCaja(TEmpleadoDeCaja empleadoDeCaja) {
		transferToEntity(empleadoDeCaja);
	}

	public abstract TEmpleadoDeCaja entityToTransfer();

    public abstract Double calcularSueldo();

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

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		this.Apellido = apellido;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dni) {
		this.DNI = dni;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer tlf) {
		this.telefono = tlf;
	}

	public Double getSueldo() {
		return Sueldo;
	}

	public void setSueldo(Double sueldo) {
		this.Sueldo = sueldo;
	}
	
	public Boolean getActivo(){
		return activo;
	}
	
	public void setActivo(Boolean activo){
		this.activo = activo;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Set<Venta> getVenta() {
		return venta;
	}

	public void setVenta(Set<Venta> venta) {
		this.venta = venta;
	}
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getTipo() {
		return tipo;
	}
	
	public void setTipo(Integer tipo){
		this.tipo = tipo;
	}
	
}