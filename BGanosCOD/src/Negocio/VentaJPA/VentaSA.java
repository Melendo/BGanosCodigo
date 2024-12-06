package Negocio.VentaJPA;

import java.util.List;

public interface VentaSA {

	public Integer modificarVenta(TVenta tVenta);

	public List<TVenta> listarVentas();

	public TVentaConProductos mostrarPorId(Integer id);

	public List<TVenta> ventasPorEmpleadoDeCaja(Integer id);

	public Integer devolverVenta(TLineaVenta venta);

	public Integer procesarVenta(TCarrito carrito);
}