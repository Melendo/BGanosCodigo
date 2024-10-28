package Negocio.Planta;

import java.util.Set;

public interface PlantaSA {

	public Integer altaPlanta(TPlanta planta) throws Exception;

	public Integer bajaPlanta(Integer id) throws Exception;

	public Integer modificarPlanta(TPlanta planta) throws Exception;

	public Set<TPlanta> listarPlanta() throws Exception;

	public TPlanta mostrarPlantaPorId(Integer id) throws Exception;

	public Set<TPlanta> listarPlantasPorTipo(String tipo) throws Exception;

	public Set<TPlanta> listarPlantasPorInvernadero(Integer id_invernadero) throws Exception;
}