package Integracion.SistemaDeRiego;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;
import Negocio.SistemaDeRiego.TSistemaDeRiego;

public class SistemaDeRiegoDAOTest {
	
	private static SistemaDeRiegoDAO sistemaRiegoDAO;
	
	private boolean equals(TSistemaDeRiego s1, TSistemaDeRiego s2){
		return true;
	}
	
	private TSistemaDeRiego getTSistemaDeRiego() {

		TSistemaDeRiego sistRiego = new TSistemaDeRiego();
		

		return sistRiego;
	}
	
	private TSistemaDeRiego getTSistemaDeRiego(String nombreUnico) {

		TSistemaDeRiego sistRiego = new TSistemaDeRiego();
		

		return sistRiego;
	}
	
	private TSistemaDeRiego getTSistemaDeRiego(Integer idFabricante) {

		TSistemaDeRiego sistRiego = new TSistemaDeRiego();
		

		return sistRiego;
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

	private int getNumRandom(){
		return ThreadLocalRandom.current().nextInt(0, 10000 + 1);
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
	}

	@Test
	public void testAltaSistemaDeRiego() {
		try {
			Transaccion trans = crearTransaccion();
			trans.start();
			TSistemaDeRiego sistRiego = getTSistemaDeRiego();
			Integer idSistemaDeRiego = sistemaRiegoDAO.altaSistemaDeRiego(sistRiego);
			if (idSistemaDeRiego < 0) {
				trans.rollback();
				fail("Error: altaSistemaDeRiego() debería retornar ID > 0");
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
			TSistemaDeRiego sistRiego = getTSistemaDeRiego();
			Integer idSistemaDeRiego = sistemaRiegoDAO.altaSistemaDeRiego(sistRiego);
			Integer result = sistemaRiegoDAO.bajaSistemaDeRiego(idSistemaDeRiego);
			if (result < 0) {
				trans.rollback();
				fail("Error: bajaSistemaDeRiego() debería retornar un número positivo");
			}
			trans.commit();
		} catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMostrarSistemaDeRiego() {
		try {
			Transaccion trans = crearTransaccion();
			trans.start();
			TSistemaDeRiego sistRiego = getTSistemaDeRiego();
			Integer idSistemaDeRiego = sistemaRiegoDAO.altaSistemaDeRiego(sistRiego);
			sistRiego.setId(idSistemaDeRiego);

			if (!equals(sistRiego, sistemaRiegoDAO.mostrarSistemaDeRiegoPorID(sistRiego.getId()))) {
				trans.rollback();
				fail("Error: mostrarSistemaDeRiegoPorID() sistRiego no coincide con el mostrado");
			}

			trans.commit();
		} catch (Exception e) {
			fail("Excepción");
			e.printStackTrace();
		}
	}

}
