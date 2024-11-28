
package Negocio.EmpleadoDeCajaJPA;

import java.util.Set;


public interface EmpleadoDeCajaSA {
	
	public Integer altaEmpleadoDeCaja(TEmpleadoDeCaja empleado);

	public Integer bajaEmpleadoDeCaja();

	public Integer ModificarEmpleadoDeCaja();

	public Set<TEmpleadoDeCaja> ListarEmpleadoDeCajaPorNombre(String nombre);

	public Set<TEmpleadoDeCaja> MostrarEmpleadoDeCajaPorId(Integer id);

	public Set<TEmpleadoDeCaja> ListarEmpleadosPorTurno(Integer idTurno);

	public Set<TEmpleadoDeCaja> ListarEmpleadosDeCaja();
}