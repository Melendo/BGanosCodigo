package Negocio.Invernadero;

import java.sql.Date;
import java.util.Collection;

public interface InvernaderoSA {

	//
	public Integer altaInvernadero(TInvernadero invernadero);

	//
	public Integer bajaInvernadero(Integer id);

	//
	public Integer modificarInvernadero(TInvernadero invernadero);

	//
	public TInvernadero mostrarInvernaderoPorID(Integer id);

	//
	public TInvernadero mostrarInvernaderoPorNombre(String nombre);

	//
	public Collection<TInvernadero> listarInvernadero();

	//
	public Collection<TInvernadero> listarInvernaderoPorSR(Integer id_sistema_riegos);

	//
	public Integer vincularSRInvernadero(Integer id_sistema_riego, Integer id_invernadero);

	//
	public Integer desvincularSRInvernadero(Integer id_sistema_riego, Integer id_invernadero);
	
	public Collection<Date> calcularLasTresFechasMasVendidasDeUnInvernadero (Integer id_invernadero);

}