package Negocio.VentaJPA;

import java.util.Set;

import Negocio.ProductoJPA.TProducto;

public interface VentaSA {

	public Integer altaVenta(TVenta tVenta);

	public Integer bajaVenta(Integer id);

	public Integer modificarVenta(TVenta tVenta);

	public Set<TVenta> listarVentas();

	public TVenta mostrarPorId(Integer id);

	public Set<TVenta> ventasPorEmpleadoDeCaja(Integer id);

	public TProducto añadirProducto(Integer idProducto);

	public Integer quitarProducto(Integer id);

	public Integer devolverVenta(TlineaVenta venta);

	public Integer procesarVenta(TCarrito carrito);
}