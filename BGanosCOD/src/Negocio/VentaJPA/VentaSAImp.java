package Negocio.VentaJPA;

import java.sql.Date;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;

import Negocio.EMFSingleton.EMFSingleton;
import Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja;
import Negocio.ProductoJPA.Producto;
import Negocio.ProductoJPA.TProducto;

public class VentaSAImp implements VentaSA {

	public Integer altaVenta(TVenta tVenta) {
		return null;
	}

	public Integer bajaVenta(Integer id) {
		return null;

	}

	public Integer modificarVenta(TVenta tVenta) {
		return null;

	}

	public Set<TVenta> listarVentas() {
		return null;

	}

	public TVenta mostrarPorId(Integer id) {
		return null;

	}

	public Set<TVenta> ventasPorEmpleadoDeCaja(Integer id) {
		return null;

	}

	public TProducto añadirProducto(Integer idProducto) {
		return null;

	}

	public Integer quitarProducto(Integer id) {
		return null;

	}

	public Integer devolverVenta(TLineaVenta venta) {
		return null;

	}

	public Integer procesarVenta(TCarrito carrito) {
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		EmpleadoDeCaja emCaja = em.find(EmpleadoDeCaja.class, carrito.getVenta().getIdEmplado(),
				LockModeType.OPTIMISTIC);

		if (emCaja == null) {// Empleado no existe
			et.rollback();
			em.close();
			return -2;
		}
		
		if (!emCaja.getActivo()) {// Empledo dado de baja
			et.rollback();
			em.close();
			return -3;
		}

		if (carrito.getLineaVenta().isEmpty()) {// Carrito vacio
			et.rollback();
			em.close();
			return -4;
		}
		
		double total = 0; // Creamos la venta
		Venta venta = new Venta(carrito.getVenta());
		venta.setActivo(true);
		venta.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
		em.persist(venta);

		for (TLineaVenta linV : carrito.getLineaVenta()) {
			Producto prod = em.find(Producto.class, linV.getIdProducto());
			if (prod == null) { // Producto no existe
				et.rollback();
				em.close();
				return -5;
			}
			if (!prod.getActivo()) { // Producto dado de baja
				et.rollback();
				em.close();
				return -6;
			}
			if (linV.getCantidad() > prod.getStock()) { // No hay stock suficiente
				et.rollback();
				em.close();
				return -7;
			}

			prod.setStock(linV.getCantidad());
			LineaVenta lineaVenta = new LineaVenta(linV);
			double precio = linV.getCantidad() * prod.getPrecio();
			lineaVenta.setProducto(prod);
			lineaVenta.setVenta(venta);
			lineaVenta.setPrecio(precio);
			em.persist(lineaVenta);
			venta.setLineaVenta(lineaVenta);
			total += precio;
		}
		venta.setPrecioTotal(total);
		try {
			et.commit();
			em.close();
			return venta.getId();
		}catch (Exception e){
			e.printStackTrace();
			et.rollback();
			em.close();
		}

		return -1; // Error desconocido
	}
}