package Integracion;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Entrada.EntradaDAO;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Invernadero.InvernaderoDAO;
import Integracion.Invernadero.TieneDAO;
import Integracion.Planta.PlantaDAO;
import Integracion.SistemaDeRiego.SistemaDeRiegoDAO;
import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;

import Negocio.Invernadero.TInvernadero;

public class InvernaderoDAOTest {

	private static InvernaderoDAO invernaderoDAO;
	private static SistemaDeRiegoDAO sistemaRiegoDAO;
	private static TieneDAO tieneDAO;
	private static PlantaDAO plantaDAO;
	private static EntradaDAO entradaDAO;

	private static Transaccion t = crearTransaccion();

	private static Transaccion crearTransaccion() {
		try {
			TransaccionManager tManager = TransaccionManager.getInstance();
			tManager.newTransaccion();
			return tManager.getTransaccion();
		} catch (Exception e) {
			fail("Error transaccional");
			return null;
		}
	}

	private boolean equals(TInvernadero a, TInvernadero b) {
		if (a == null || b == null) {
			return false;
		}

		return a.getId() == b.getId() && a.getNombre().equals(b.getNombre()) && a.getSustrato().equals(b.getSustrato())
				&& a.getTipo_iluminacion().equals(b.getTipo_iluminacion()) && a.isActivo() == b.isActivo();
	}

	@BeforeClass
	public static void setUp() throws Exception {
		t.start();
		invernaderoDAO = FactoriaIntegracion.getInstance().getInvernaderoDAO();
		try {

		} catch (Exception e) {

		}
	}

	@AfterClass
	public static void tearDown() throws Exception {
		t.rollback();
		t.cerrarConnection();
	}

	@Test
	public void testAltaInvernadero() throws Exception {
		// Crea el objeto TInvernadero a insertar
		TInvernadero invernadero = new TInvernadero("InvAltaTest", "Tierra", "LED");

		// Llama a la función altaInvernadero
		Integer resultado = invernaderoDAO.altaInvernadero(invernadero);

		// Verifica que el ID retornado sea válido
		assertTrue("El ID debe ser mayor que 0", resultado > 0);

	}

	@Test
	public void testBajaInvernadero() throws Exception {
		// Crea el objeto TInvernadero a insertar
		TInvernadero invernadero = new TInvernadero("InvBajaTest", "Tierra", "LED");

		Connection c = (Connection) t.getResource();
		PreparedStatement statement = c.prepareStatement(
				"INSERT INTO invernadero(nombre, sustrato, tipo_iluminacion, activo) VALUES (?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, invernadero.getNombre());
		statement.setString(2, invernadero.getSustrato());
		statement.setString(3, invernadero.getTipo_iluminacion());
		statement.setBoolean(4, true);
		statement.executeUpdate();

		ResultSet result = statement.getGeneratedKeys();

		int id_baja = 0;
		if (result.next()) {
			id_baja = result.getInt(1);
		}

		result.close();
		statement.close();
		// Llama a la función altaInvernadero
		Integer resultado = invernaderoDAO.bajaInvernadero(id_baja);

		// Verifica que el ID retornado sea válido
		assertTrue("El ID debe ser mayor que 0", resultado > 0);

	}

	@Test
	public void testModificarInvernadero() throws Exception {
		// Crea el objeto TInvernadero a insertar
		TInvernadero invernadero = new TInvernadero("InvModificarTest", "Tierra", "LED");

		Connection c = (Connection) t.getResource();
		PreparedStatement statement = c.prepareStatement(
				"INSERT INTO invernadero(nombre, sustrato, tipo_iluminacion, activo) VALUES (?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, invernadero.getNombre());
		statement.setString(2, invernadero.getSustrato());
		statement.setString(3, invernadero.getTipo_iluminacion());
		statement.setBoolean(4, true);
		statement.executeUpdate();

		ResultSet result = statement.getGeneratedKeys();

		int id_modificar = 0;
		if (result.next()) {
			id_modificar = result.getInt(1);
		}

		result.close();
		statement.close();

		TInvernadero invernaderoModificado = new TInvernadero("InvModificarTest", "Tierra", "LED");
		invernaderoModificado.setId(id_modificar);
		// Llama a la función altaInvernadero
		Integer resultado = invernaderoDAO.modificarInvernadero(invernaderoModificado);

		// Verifica que el ID retornado sea válido
		assertTrue("El ID debe ser mayor que 0", resultado > 0);

	}

	@Test
	public void testMostrarPorIDInvernadero() throws Exception {
		// Crea el objeto TInvernadero a insertar
		TInvernadero invernadero = new TInvernadero("InvMostrarIDTest", "Tierra", "LED");

		Connection c = (Connection) t.getResource();
		PreparedStatement statement = c.prepareStatement(
				"INSERT INTO invernadero(nombre, sustrato, tipo_iluminacion, activo) VALUES (?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, invernadero.getNombre());
		statement.setString(2, invernadero.getSustrato());
		statement.setString(3, invernadero.getTipo_iluminacion());
		statement.setBoolean(4, true);
		statement.executeUpdate();

		ResultSet result = statement.getGeneratedKeys();

		int id_mostrar = 0;
		if (result.next()) {
			id_mostrar = result.getInt(1);
		}

		result.close();
		statement.close();

		invernadero.setId(id_mostrar);
		invernadero.setActivo(true);
		// Llama a la función altaInvernadero
		TInvernadero invernaderoLeido = invernaderoDAO.mostrarInvernaderoPorID(id_mostrar);

		// Verifica que el ID retornado sea válido
		assertTrue("El ID debe ser mayor que 0", equals(invernadero, invernaderoLeido));

	}

	@Test
	public void testMostrarPorNombreInvernadero() throws Exception {
		// Crea el objeto TInvernadero a insertar
		TInvernadero invernadero = new TInvernadero("InvMostrarNameTest", "Tierra", "LED");

		Connection c = (Connection) t.getResource();
		PreparedStatement statement = c.prepareStatement(
				"INSERT INTO invernadero(nombre, sustrato, tipo_iluminacion, activo) VALUES (?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, invernadero.getNombre());
		statement.setString(2, invernadero.getSustrato());
		statement.setString(3, invernadero.getTipo_iluminacion());
		statement.setBoolean(4, true);
		statement.executeUpdate();

		ResultSet result = statement.getGeneratedKeys();

		int id_mostrar = 0;
		if (result.next()) {
			id_mostrar = result.getInt(1);
		}

		result.close();
		statement.close();

		invernadero.setId(id_mostrar);
		invernadero.setActivo(true);
		String nombre_mostrar = invernadero.getNombre();
		// Llama a la función altaInvernadero
		TInvernadero invernaderoLeido = invernaderoDAO.mostrarInvernaderoPorNombre(nombre_mostrar);

		// Verifica que el ID retornado sea válido
		assertTrue("El ID debe ser mayor que 0", equals(invernadero, invernaderoLeido));

	}

	@Test
	public void testListarInvernadero() throws Exception {
		// Crea el objeto TInvernadero a insertar
		TInvernadero invernadero = new TInvernadero("InvListarTest", "Tierra", "LED");

		Connection c = (Connection) t.getResource();
		PreparedStatement statement = c.prepareStatement(
				"INSERT INTO invernadero(nombre, sustrato, tipo_iluminacion, activo) VALUES (?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, invernadero.getNombre());
		statement.setString(2, invernadero.getSustrato());
		statement.setString(3, invernadero.getTipo_iluminacion());
		statement.setBoolean(4, true);
		statement.executeUpdate();

		statement.close();

		// Llama a la función altaInvernadero
		Set<TInvernadero> lista = invernaderoDAO.listarInvernadero();

		// Verifica que el ID retornado sea válido
		assertTrue("El ID debe ser mayor que 0", lista.size() > 0);

	}
}
