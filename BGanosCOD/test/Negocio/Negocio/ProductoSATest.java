package Negocio;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Negocio.EMFSingleton.EMFSingleton;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.MarcaJPA.Marca;
import Negocio.MarcaJPA.TMarca;
import Negocio.ProductoJPA.TProducto;
import Negocio.ProductoJPA.TProductoAlimentacion;
import Negocio.ProductoJPA.TProductoSouvenirs;

public class ProductoSATest {
	private EntityManager em;
	
	@Before
	public void setUp() {
		em = EMFSingleton.getInstance().getEMF().createEntityManager();
		em.getTransaction().begin();
		em.createQuery("DELETE FROM Producto p WHERE p.nombre LIKE '%Test%'").executeUpdate();
		em.createQuery("DELETE FROM Marca m WHERE m.nombre LIKE '%Test%'").executeUpdate();
		em.getTransaction().commit();
	}

	@After
	public void tearDown() {
		em.getTransaction().begin();
		em.createQuery("DELETE FROM Producto p WHERE p.nombre LIKE '%Test%'").executeUpdate();
		em.createQuery("DELETE FROM Marca m WHERE m.nombre LIKE '%Test%'").executeUpdate();
		em.getTransaction().commit();

		if (em.isOpen()) {
			em.close();
		}
	}
	
	
	@Test
	public void testAltaProductoAlimentacion() {
		
		EntityTransaction t = em.getTransaction();

		Marca marca = new Marca();
		marca.setNombre("Slay Test");
		marca.setPaisOrigen("Spain");
		marca.setActivo(true);
		marca.setContadorProductos(0);
		

		t.begin();
		em.persist(marca);
		t.commit();

	
		
		TProductoAlimentacion p = new TProductoAlimentacion("TestA", 1.0, 1, marca.getId(), 0, "test", 1.0, 1.0);
		
		int result = FactoriaNegocio.getInstance().getProductoJPA().altaProductoAlimentacion(p);
		
		assertTrue( result > 0);
	}
	
	
	@Test
	public void testAltaProductoSouvenirs() {
		
		EntityTransaction t = em.getTransaction();

		Marca marca = new Marca();
		marca.setNombre("Slay Test");
		marca.setPaisOrigen("Spain");
		marca.setActivo(true);
		marca.setContadorProductos(0);
		

		t.begin();
		em.persist(marca);
		t.commit();

	
		
		TProductoSouvenirs p = new TProductoSouvenirs("TestS", 1.0, 1, marca.getId(), 0, "test");
		
		int result = FactoriaNegocio.getInstance().getProductoJPA().altaProductoSouvenirs(p);
		
		assertTrue( result > 0);
	}
	
	@Test
	public void testMostrarProductoPorId() {
		Marca marca = new Marca();
		marca.setNombre("Slay Test");
		marca.setPaisOrigen("Spain");
		marca.setActivo(true);
		


		em.getTransaction().begin();
		em.persist(marca);
	
		em.getTransaction().commit();
		
		TProductoSouvenirs p = new TProductoSouvenirs("TestS", 1.0, 1, marca.getId(), 0, "test");
		

		em.getTransaction().begin();

		em.persist(p);
		em.getTransaction().commit();


		TProducto result = FactoriaNegocio.getInstance().getProductoJPA().mostrarProducto(p.getId());
		
		assertTrue(result != null);

	}
	
	@Test
	public void testListar() {
		Marca marca = new Marca();
		marca.setNombre("Ok Test");
		marca.setPaisOrigen("Spain");
		marca.setActivo(true);


		em.getTransaction().begin();
		em.persist(marca);
	
		em.getTransaction().commit();
		
		TProductoSouvenirs p = new TProductoSouvenirs("TestS", 1.0, 1, marca.getId(), 0, "test");
		

		em.getTransaction().begin();

		em.persist(p);
		em.getTransaction().commit();

		
		List<TProducto> result = FactoriaNegocio.getInstance().getProductoJPA().listarProductos();
		
		assertTrue(result != null);
	}
	
	@Test
	public void testListarPorMarca() {
		Marca marca = new Marca();
		marca.setNombre("Ok Test");
		marca.setPaisOrigen("Spain");
		marca.setActivo(true);

		

		em.getTransaction().begin();
		em.persist(marca);
	
		em.getTransaction().commit();
		
		TProductoSouvenirs p = new TProductoSouvenirs("TestS", 1.0, 1, marca.getId(), 0, "test");
		

		em.getTransaction().begin();

		em.persist(p);
		em.getTransaction().commit();

		List<TProducto> result = FactoriaNegocio.getInstance().getProductoJPA().listarProductosPorMarca(marca.getId());
		
		assertTrue(result != null);
	}
	
	@Test
	public void testListarPorTipo() {
		Marca marca = new Marca();
		marca.setNombre("Ok Test");
		marca.setPaisOrigen("Spain");
		marca.setActivo(true);

		

		em.getTransaction().begin();
		em.persist(marca);
	
		em.getTransaction().commit();
		
		TProductoSouvenirs p = new TProductoSouvenirs("TestS", 1.0, 1, marca.getId(), 0, "test");
		

		em.getTransaction().begin();

		em.persist(p);
		em.getTransaction().commit();

		
		List<TProducto> result = FactoriaNegocio.getInstance().getProductoJPA().listarProductosPorTipo(1);
		
		assertTrue(result != null);
	}
	
	

	
	
	

}
