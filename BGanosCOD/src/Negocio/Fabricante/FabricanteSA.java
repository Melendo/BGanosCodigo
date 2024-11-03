package Negocio.Fabricante;

import java.util.Set;

public interface FabricanteSA {
	public Integer altaFabricante(TFabricante animal);

	public Integer bajaFabricante(Integer idFabricante);

	public Integer modificarFabricante(TFabricante fabricante);

	public TFabricante mostrarFabricantePorId(Integer id);

	public Set<TFabricante> listarFabricantes();

	public Set<TFabricante> listarFabricantesLocales();

	public Set<TFabricante> listarFabricantesExtranjeros();
	
	public Set<TFabricante> listarFabricantesPorInvernadero(Integer id);
}