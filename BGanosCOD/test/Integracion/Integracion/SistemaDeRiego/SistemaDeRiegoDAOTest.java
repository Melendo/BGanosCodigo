package Integracion.SistemaDeRiego;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.BeforeClass;
import org.junit.Test;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;
import Negocio.SistemaDeRiego.TSistemaDeRiego;

public class SistemaDeRiegoDAOTest {
	
	private static SistemaDeRiegoDAO sistemaRiegoDAO;
	
    // Comparar dos objetos TSistemaDeRiego
    private boolean equals(TSistemaDeRiego s1, TSistemaDeRiego s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        return s1.getId().equals(s2.getId()) &&
               s1.getNombre().equals(s2.getNombre()) &&
               s1.getPotenciaRiego().equals(s2.getPotenciaRiego()) &&
               s1.getCantidad_agua().equals(s2.getCantidad_agua()) &&
               s1.getFrecuencia().equals(s2.getFrecuencia()) &&
               s1.getActivo().equals(s2.getActivo()) &&
               s1.getIdFabricante().equals(s2.getIdFabricante());
    }

    // Crear un sistema de riego con valores predeterminados
    private TSistemaDeRiego getTSistemaDeRiego() {
        return new TSistemaDeRiego(getNumRandom(), getNameRandom(), getNumRandom(), getNumRandom(), getNumRandom(), true, getNumRandom());
    }

    // Crear un sistema de riego utilizando un nombre �nico
    private TSistemaDeRiego getTSistemaDeRiego(String nombreUnico) {
        return new TSistemaDeRiego(getNumRandom(), nombreUnico, getNumRandom(), getNumRandom(), getNumRandom(), true, getNumRandom());
    }

    // Crear un sistema de riego utilizando el ID del fabricante
    private TSistemaDeRiego getTSistemaDeRiego(Integer idFabricante) {
        return new TSistemaDeRiego(getNumRandom(), getNameRandom(), getNumRandom(), getNumRandom(), getNumRandom(), true, idFabricante);
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
				fail("Error: altaSistemaDeRiego() deber�a retornar ID > 0");
			}
			trans.commit(); 
		} catch (Exception e) {
			
			fail("Excepci�n");
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
				fail("Error: bajaSistemaDeRiego() deber�a retornar un n�mero positivo");
			}
			trans.commit(); 
		} catch (Exception e) {
			fail("Excepci�n");
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
			fail("Excepci�n");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModificarSistemaDeRiego() {
	    try {
	    	Transaccion trans = crearTransaccion();
			trans.start();
			TSistemaDeRiego sistRiego = getTSistemaDeRiego();
			Integer idSistemaDeRiego = sistemaRiegoDAO.altaSistemaDeRiego(sistRiego);
			sistRiego.setId(idSistemaDeRiego);
	        
			sistRiego.setNombre(getNameRandom());
			sistRiego.setActivo(false);

	        Integer resultado = sistemaRiegoDAO.modificarSistemaDeRiego(sistRiego);

	        if (resultado < 0) {
	        	trans.rollback();
	            fail("Error: modificarEspecie() deber�a un numero positivo");
	        }

	        TSistemaDeRiego sistRiegoMod = sistemaRiegoDAO.mostrarSistemaDeRiegoPorID(sistRiego.getId());
	        if (!sistRiego.getNombre().equals(sistRiegoMod.getNombre())
	                || !sistRiego.getActivo().equals(sistRiegoMod.getActivo())) {
	        	trans.rollback();
	            fail("Error: Los atributos de la especie no coinciden despu�s de la modificaci�n");
	        }

	        trans.commit();
	    } catch (Exception e) {
	        fail("Excepci�n");
	        e.printStackTrace();
	    }
	}
	
	@Test
	public void testListarSistemaDeRiego() {
	    try {
	    	Transaccion trans = crearTransaccion();
			trans.start();

			TSistemaDeRiego sistRiego = getTSistemaDeRiego();
			TSistemaDeRiego sistRiego2 = getTSistemaDeRiego(); 

	        Integer idSistemaDeRiego = sistemaRiegoDAO.altaSistemaDeRiego(sistRiego);
	        sistRiego.setId(idSistemaDeRiego);
	        boolean encontrado = false;
	        Integer idSistemaDeRiego2 = sistemaRiegoDAO.altaSistemaDeRiego(sistRiego2);	        
	        sistRiego.setId(idSistemaDeRiego2);
	        boolean encontrado2 = false;
	       	     
	        Set<TSistemaDeRiego> sistemasRiego = sistemaRiegoDAO.listarSistemaDeRiego();
	       
	        for (TSistemaDeRiego sistR : sistemasRiego) {
	            if (sistR.getId().equals(sistRiego.getId())) {
	            	encontrado = true;
	            } else if (sistR.getId().equals(sistRiego2.getId())) {
	            	encontrado2 = true;
	            }
	        }

	        if (!encontrado || !encontrado2) {
	        	fail("Error: La lista de especies no contiene todas las especies creadas");
	            trans.rollback();	            
	        }

	        trans.commit();
	    } catch (Exception e) {
	        fail("Excepci�n");
	        e.printStackTrace();
	    }
	}

}
