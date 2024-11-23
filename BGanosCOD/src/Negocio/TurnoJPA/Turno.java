package Negocio.TurnoJPA;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja;
import javax.persistence.ManyToOne;

@Entity
@NamedQueries({
		@NamedQuery(name = "Negocio.TurnoJPA.Turno.findByid", query = "select obj from Turno obj where :id = obj.id "),
		@NamedQuery(name = "Negocio.TurnoJPA.Turno.findByversion", query = "select obj from Turno obj where :version = obj.version "),
		@NamedQuery(name = "Negocio.TurnoJPA.Turno.findByactivo", query = "select obj from Turno obj where :activo = obj.activo "),
		@NamedQuery(name = "Negocio.TurnoJPA.Turno.findByhorario", query = "select obj from Turno obj where :horario = obj.horario "),
		@NamedQuery(name = "Negocio.TurnoJPA.Turno.findByempleadoDeCaja", query = "select obj from Turno obj where :empleadoDeCaja = obj.empleadoDeCaja ") })
public class Turno implements Serializable {
	
	private static final long serialVersionUID = 0;

	public Turno() {
	}

	@Id
	private Integer id;
	
	private Integer version;
	
	private Boolean activo;
	
	private String horario;
	
	@ManyToOne
	private EmpleadoDeCaja empleadoDeCaja;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean isActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public TTurno entityToTransfer() {
		TTurno tturno = new TTurno();
		tturno.setActivo(activo);
		tturno.setHorario(horario);
		tturno.setId(id);
		return tturno;
	}

	public void transferToEntity(TTurno turno) {
		this.id = turno.getId();
		this.activo = turno.isActivo();
		this.horario = turno.getHorario();
	}
}