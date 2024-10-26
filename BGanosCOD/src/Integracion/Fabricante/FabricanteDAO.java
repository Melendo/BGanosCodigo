package Integracion.Fabricante;

import Negocio.Fabricante.TFabricante;
import java.util.Set;

public interface FabricanteDAO {
	
	public Integer altaFabricante(TFabricante fabricante);

	public Integer bajaFabricante(Integer idFabricante);

	public Integer modificarFabricante(TFabricante fabricante);

	public TFabricante mostrarFabricantePorId(Integer idFabricante);

	public TFabricante listarFabricantePorNombre(String nombre);

	public Set<TFabricante> listarFabricantes();

	public Set<TFabricante> listarFabricantesExtrangeros();

	public Set<TFabricante> listarFabricantesLocales();
	
	public TFabricante leerPorCodFabricante(int codFabricante);

}