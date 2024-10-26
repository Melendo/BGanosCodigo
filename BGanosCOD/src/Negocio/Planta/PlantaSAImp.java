package Negocio.Planta;

import java.util.Set;

public class PlantaSAImp implements PlantaSA {

	public Integer altaPlanta(TPlanta planta) {
		if(planta.get_nombre().isEmpty() || planta.get_nombre_cientifico().isEmpty() || planta.get_id_invernadero().isEmpty()) {
			return -3;
		}else if(planta instanceof TPlantaFrutal) {
			
		}else if(planta instanceof TPlantaNoFrutal) {
			
		}
	}

	public Integer bajaPlanta(Integer id) {
		
	}

	public Integer modificarPlanta(TPlanta planta) {

	}

	public Set<TPlanta> listarPlanta() {

	}

	public TPlanta mostrarPlantaPorId(Integer id) {

	}

	public Set<TPlanta> listarPlantasPorTipo(String tipo) {

	}

	public Set<TPlanta> listarPlantasPorInvernadero(Integer id_invernadero) {

	}
}