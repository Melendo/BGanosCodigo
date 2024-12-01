package Negocio.VentaJPA;

import java.util.List;

import Negocio.ProductoJPA.TProducto;

public interface VentaSA {

	public Integer modificarVenta(TVenta tVenta);

	public List<TVenta> listarVentas();

	public TVentaConProductos mostrarPorId(Integer id);

	public List<TVenta> ventasPorEmpleadoDeCaja(Integer id);

	public TProducto aniadirProducto(Integer idProducto);

	public Integer quitarProducto(Integer id);

	public Integer devolverVenta(TLineaVenta venta);

	public Integer procesarVenta(TCarrito carrito);
}