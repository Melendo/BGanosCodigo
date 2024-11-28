package Negocio.VentaJPA;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import Negocio.EMFSingleton.EMFSingleton;
import Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja;
import Negocio.ProductoJPA.Producto;
import Negocio.ProductoJPA.TProducto;

public class VentaSAImp implements VentaSA {

	public Integer modificarVenta(TVenta tVenta) {
		return null;

	}

	public List<TVenta> listarVentas() {
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();

		TypedQuery<Venta> query = em.createNamedQuery("Negocio.VentaJPA.Venta.findAll", Venta.class);
		query.setLockMode(LockModeType.OPTIMISTIC);

		List<Venta> lQuery = query.getResultList();
		List<TVenta> lVenta = new LinkedList<TVenta>();

		for (Venta v : lQuery)
			lVenta.add(v.toTransfer());

		em.close();
		return lVenta;
	}

	public TVentaConProductos mostrarPorId(Integer id) {

		return null;

	}

	public List<TVenta> ventasPorEmpleadoDeCaja(Integer id) {
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EmpleadoDeCaja emCaja = em.find(EmpleadoDeCaja.class, id);

		TypedQuery<Venta> query = em.createNamedQuery("Negocio.VentaJPA.Venta.findByempleadoDeCaja", Venta.class);
		query.setLockMode(LockModeType.OPTIMISTIC);
		query.setParameter("empleadoDeCaja", emCaja);

		List<Venta> lQuery = query.getResultList();
		List<TVenta> lVenta = new LinkedList<TVenta>();

		for (Venta v : lQuery)
			lVenta.add(v.toTransfer());

		em.close();
		return lVenta;
	}

	public TProducto aniadirProducto(Integer idProducto) {
		return null;

	}

	public Integer quitarProducto(Integer id) {
		return null;

	}

	public Integer devolverVenta(TLineaVenta tLinea) {
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction et = em.getTransaction();
		et.begin();

		Venta ventaD = em.find(Venta.class, tLinea.getIdVenta());

		if (ventaD == null) { // la Venta no existe
			et.rollback();
			em.close();
			return -2;
		}

		Producto prod = em.find(Producto.class, tLinea.getIdProducto());

		if (prod == null) { // el Producto no existe
			et.rollback();
			em.close();
			return -3;
		}

		LineaVenta lVenta = em.find(LineaVenta.class, new idLineaVenta(prod.getId(), tLinea.getIdVenta()));

		if (lVenta == null) { // la Linea de Venta no existe
			et.rollback();
			em.close();
			return -4;
		}

		if (tLinea.getCantidad() > lVenta.getCantidad()) { // no se puede devolver mas de lo que tenia la venta
			et.rollback();
			em.close();
			return -5;
		}

		prod.setStock(prod.getStock() + tLinea.getCantidad());
		ventaD.setPrecioTotal(ventaD.getPrecioTotal() - tLinea.getCantidad() * prod.getPrecio());

		if (ventaD.getPrecioTotal() == 0)
			ventaD.setActivo(false);

		lVenta.setCantidad(lVenta.getCantidad() - tLinea.getCantidad());
		lVenta.setPrecio(lVenta.getPrecio() - tLinea.getCantidad() * prod.getPrecio());

		if (lVenta.getCantidad() == 0)
			em.remove(lVenta);

		try {
			et.commit();
			em.close();
			return ventaD.getId();
		} catch (Exception e) {
			et.rollback();
			em.close();
			e.printStackTrace();
		}

		return -1; // Error desconocido
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

		for (TLineaVenta linV : carrito.getLineaVenta()) {// Iteramos sobre las lineas de venta
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
		} catch (Exception e) {
			e.printStackTrace();
			et.rollback();
			em.close();
		}

		return -1; // Error desconocido
	}
}