package Negocio.MarcaJPA;

import java.util.List;

public interface MarcaSA {

	public Integer bajaMarca(Integer id);

	public Integer modificarMarca(TMarca marca);

	// TODO: cambié el tipo de set a list
	public List<TMarca> listarMarcas();

	// TODO: añadir el Integer id de parámetro,
	// TODO: cambiar tipo de integer a TMarca, antes estaba:
	
	// TODO: public Integer mostrarMarcaPorId();
	public TMarca mostrarMarcaPorId(Integer id);

	// TODO: cambié el tipo de set a list
	public List<TMarca> listarMarcasPorProveedor(Integer idProv);

	public Integer altaMarca(TMarca marca);
}