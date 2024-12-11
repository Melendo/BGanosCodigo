package Negocio;

import static org.junit.Assert.fail;

import java.util.Random;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Negocio.EMFSingleton.EMFSingleton;


public class VentaSATest {
	private static Random random;
	private EntityManager em;

	@Before
	public void setUp() {
		em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		em.createQuery("DELETE FROM Prodcuto m WHERE m.nombre LIKE '%Test%'").executeUpdate();
		em.createQuery("DELETE FROM Marca m WHERE m.nombre LIKE '%Test%'").executeUpdate();
		em.createQuery("DELETE FROM EmpleadoDeCaja m WHERE m.nombre LIKE '%Test%'").executeUpdate();
		em.createQuery("DELETE FROM Turno m WHERE m.nombre LIKE '%Test%'").executeUpdate();
		em.getTransaction().commit();
	}

	@After
	public void tearDown() {
		em.getTransaction().begin();
		em.createQuery("DELETE FROM Prodcuto m WHERE m.nombre LIKE '%Test%'").executeUpdate();
		em.createQuery("DELETE FROM Marca m WHERE m.nombre LIKE '%Test%'").executeUpdate();
		em.createQuery("DELETE FROM EmpleadoDeCaja m WHERE m.nombre LIKE '%Test%'").executeUpdate();
		em.createQuery("DELETE FROM Turno m WHERE m.nombre LIKE '%Test%'").executeUpdate();		
		em.getTransaction().commit();

		if (em.isOpen()) {
			em.close();
		}
	}
	
	
	@Test
	public void procesarVenta() {
		
		
		
		
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
	
}