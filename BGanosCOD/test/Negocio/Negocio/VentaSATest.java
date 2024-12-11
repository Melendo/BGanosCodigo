package Negocio;

import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Negocio.EMFSingleton.EMFSingleton;
import Negocio.EmpleadoDeCajaJPA.EmpleadoCompleto;
import Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja;
import Negocio.MarcaJPA.Marca;
import Negocio.ProductoJPA.Producto;
import Negocio.ProductoJPA.ProductoSouvenirs;
import Negocio.TurnoJPA.Turno;
import Negocio.VentaJPA.LineaVenta;
import Negocio.VentaJPA.Venta;

public class VentaSATest {
	private EntityManager em;

	@Before
	public void setUp() {
		em = EMFSingleton.getInstance().getEMF().createEntityManager();
	}

	@After
	public void tearDown() {

		if (em.isOpen()) {
			em.close();
		}
	}

	@Test
	public void procesarVenta() {

		Turno turno = getTurno();
		Marca marca = getMarca();

		em.getTransaction().begin();
		em.persist(turno);
		em.persist(marca);
		em.getTransaction().commit();

		EmpleadoDeCaja empleado = getEmpleado();
		empleado.setTurno(turno);
		Producto producto = getProducto();
		producto.setMarca(marca);

		em.getTransaction().begin();
		em.persist(empleado);
		em.persist(producto);
		em.getTransaction().commit();

		Venta venta = getVenta();

		venta.setEmpleado(empleado);

		em.getTransaction().begin();
		em.persist(venta);

		LineaVenta lv = new LineaVenta();
		lv.setCantidad(1);
		lv.setPrecio(1.0);
		lv.setProducto(producto);
		lv.setVenta(venta);
			
		em.persist(lv);
		em.getTransaction().commit();
		
		
		assert(venta.getId() > 0);
	}

	@Test
	public void modificarVenta() {

	}

	@Test
	public void devolverVenta() {

	}

	@Test
	public void mostrarVenta() {

	}

	@Test
	public void listarVenta() {

	}

	@Test
	public void ventaPorEmpleado() {

	}

	private Turno getTurno() {
		Turno turno = new Turno();
		turno.setHorario("Tarde");
		turno.setActivo(true);

		return turno;
	}

	private Marca getMarca() {
		Marca marca = new Marca();
		marca.setNombre("Marca Test");
		marca.setPaisOrigen("Pais Test");
		marca.setActivo(true);
		return marca;
	}

	private Producto getProducto() {
		Producto producto = new ProductoSouvenirs();
		producto.setNombre("Prodcuto Test");
		producto.setPrecio(10.0);
		producto.setStock(9999);
		((ProductoSouvenirs) producto).setDescripcion("Test");
		return producto;
	}

	private EmpleadoDeCaja getEmpleado() {
		EmpleadoDeCaja empleado = new EmpleadoCompleto();
		empleado.setActivo(true);
		empleado.setApellido("Apellido Test");
		empleado.setDNI("12345678a");
		empleado.setNombre("Nombre Test");
		empleado.setTelefono(123456789);
		((EmpleadoCompleto) empleado).setSueldo_Base(10.0);
		((EmpleadoCompleto) empleado).setSueldo(10.0);
		((EmpleadoCompleto) empleado).setComplementos(0.0);
		return empleado;
	}

	private Venta getVenta() {
		Venta venta = new Venta();

		venta.setActivo(true);
		venta.setFecha(new Date(Calendar.getInstance().getTime().getTime()));
		venta.setFormaPago("Test");
		return venta;
	}

}