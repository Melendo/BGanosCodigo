package Integracion;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.Fabricante.FabricanteDAO;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Invernadero.InvernaderoDAO;
import Integracion.Invernadero.TieneDAO;
import Integracion.SistemaDeRiego.SistemaDeRiegoDAO;
import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;
import Negocio.Fabricante.TFabricante;
import Negocio.Fabricante.TFabricanteLocal;
import Negocio.Invernadero.TInvernadero;
import Negocio.Invernadero.TTiene;
import Negocio.SistemaDeRiego.TSistemaDeRiego;

public class FabricanteDAOTest {

	private static SistemaDeRiegoDAO sistemaRiegoDAO;
	private static FabricanteDAO fabricanteDAO;
	private static InvernaderoDAO invernaderoDAO;
	private static TieneDAO tieneDAO;

	private boolean equals(TFabricante t1, TFabricante t2) {
		if (t1 == null || t2 == null)
			return false;
		return t1.getId().equals(t2.getId()) && t1.getNombre().equals(t2.getNombre())
				&& t1.getCodFabricante().equals(t2.getCodFabricante()) && t1.getTelefono().equals(t2.getTelefono())
				&& t1.getActivo().equals(t2.getActivo());
	}

	// Crear un sistema de riego con valores predeterminados
	private TFabricante getTFabricanteLocal() {
		TFabricante tf = new TFabricante();
		tf.setActivo(true);
		tf.setCodFabricante(getNameRandom() + getNumRandom());
		tf.setId(getNumRandom());
		tf.setNombre(getNameRandom());
		tf.setTelefono(getTelRamdom());
		return tf;
	}

	private TFabricante getTFabricanteExtranjero() {
		TFabricante tf = new TFabricante();
		tf.setActivo(true);
		tf.setCodFabricante(getNameRandom() + getNumRandom());
		tf.setId(getNumRandom());
		tf.setNombre(getNameRandom());
		tf.setTelefono(getTelRamdom());
		return tf;
	}

	private TInvernadero getTInvernadero() {
		TInvernadero tInvernadero = new TInvernadero();
		tInvernadero.setActivo(true);
		tInvernadero.setNombre(getNameRandom());
		tInvernadero.setSustrato(getNameRandom());
		tInvernadero.setTipo_iluminacion(getNameRandom());

		return tInvernadero;
	}

	private TSistemaDeRiego getTSistemaDeRiego() {
		int idFabricante = fabricanteDAO.altaFabricante(getTFabricanteLocal());
		return new TSistemaDeRiego(getNumRandom(), getNameRandom(), getNumRandom(), getNumRandom(), getNumRandom(),
				true, idFabricante);
	}

	private String getNameRandom() {
		String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder nombreAleatorio = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int index = random.nextInt(caracteres.length());
			nombreAleatorio.append(caracteres.charAt(index));
		}
		return nombreAleatorio.toString();
	}

	private int getNumRandom() {
		return ThreadLocalRandom.current().nextInt(0, 10000 + 1);
	}

	private String getTelRamdom() {
		String caracteres = "0123456789";
		StringBuilder nombreAleatorio = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 9; i++) {
			int index = random.nextInt(caracteres.length());
			nombreAleatorio.append(caracteres.charAt(index));
		}
		return nombreAleatorio.toString();
	}

	private Transaccion crearTransaccion() {
		try {
			TransaccionManager tManager = TransaccionManager.getInstance();
			tManager.newTransaccion();
			return tManager.getTransaccion();
		} catch (Exception e) {
			fail("Error transaccional");
			return null;
		}
	}

	@BeforeClass
	public static void beforeClass() {
		sistemaRiegoDAO = FactoriaIntegracion.getInstance().getSistemaDeRiegoDAO();
		fabricanteDAO = FactoriaIntegracion.getInstance().getFabricanteDAO();
		invernaderoDAO = FactoriaIntegracion.getInstance().getInvernaderoDAO();
		tieneDAO = FactoriaIntegracion.getInstance().getDaoTiene();
	}

	@Test
	public void testAltaFabricanteLocal() {
		try {
			Transaccion trans = crearTransaccion();
			trans.start();
			if (fabricanteDAO.altaFabricante(getTFabricanteLocal()) < 0) {
				trans.rollback();
				fail("Error: altaFabricante() debería devolver un entero positivo");
			}
			trans.commit();
		} catch (Exception e) {

			fail("Excepción");
			e.printStackTrace();
		}
	}

	@Test
	public void testAltaFabricanteExtranjero() {
		try {
			Transaccion trans = crearTransaccion();
			trans.start();
			if (fabricanteDAO.altaFabricante(getTFabricanteExtranjero()) < 0) {
				trans.rollback();
				fail("Error: altaFabricante() debería devolver un entero positivo");
			}
			trans.commit();
		} catch (Exception e) {

			fail("Excepción");
			e.printStackTrace();
		}
	}

	@Test
	public void testBajaSistemaDeRiego() {
		try {
			Transaccion trans = crearTransaccion();
			trans.start();
			TFabricante tf = getTFabricanteExtranjero();
			if (fabricanteDAO.bajaFabricante(fabricanteDAO.altaFabricante(tf)) < 0) {
				trans.rollback();
				fail("Error: bajaFabricante() debería devolver un entero positivo");
			}
			trans.commit();
		} catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMostrarFabricante() {
		try {
			Transaccion trans = crearTransaccion();
			trans.start();
			TFabricante tf = getTFabricanteLocal();
			Integer idSistemaDeRiego = fabricanteDAO.altaFabricante(tf);
			tf.setId(idSistemaDeRiego);

			if (!equals(tf, fabricanteDAO.mostrarFabricantePorId(tf.getId()))) {
				trans.rollback();
				fail("Error: mostrarFabricantePorID() tf no coincide con el mostrado");
			}

			trans.commit(); 
		} catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}
	}
}
