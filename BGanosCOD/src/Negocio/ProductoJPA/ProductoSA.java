/**
 * 
 */
package Negocio.ProductoJPA;

import java.util.List;
import java.util.Set;


public interface ProductoSA {

	public Integer altaProductoSouvenirs (TProducto producto);
	public Integer altaProductoAlimentacion (TProducto producto);


	public Integer bajaProducto(Integer idProducto);

	public List<TProducto> listarProductos();

	public Set<TProducto> listarProductosPorMarca(Integer idMarca);

	public Set<TProducto> listarProductosPorTipo(Integer tipo);

	
	public Set<TProducto> listarProductoPorVenta(Integer idVenta);


	public Integer modificarProducto(TProducto producto);


	public TProducto mostrarProducto(Integer idProducto);
}