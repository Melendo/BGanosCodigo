package Negocio.Planta;

import java.util.Set;

public interface PlantaSA {

	public Integer altaPlanta(TPlanta planta) ;

	public Integer bajaPlanta(Integer id) ;

	public Integer modificarPlanta(TPlanta planta) ;

	public Set<TPlanta> listarPlanta();

	public TPlanta mostrarPlantaPorId(Integer id) ;

	public Set<TPlanta> listarPlantasPorTipo(String tipo) ;

	public Set<TPlanta> listarPlantasPorInvernadero(Integer id_invernadero) ;
}