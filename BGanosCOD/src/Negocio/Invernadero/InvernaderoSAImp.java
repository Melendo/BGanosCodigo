/**
 * 
 */
package Negocio.Invernadero;

import java.util.Collection;


import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Invernadero.InvernaderoDAO;
import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;

public class InvernaderoSAImp implements InvernaderoSA {

	public Integer altaInvernadero(TInvernadero invernadero) {
		// Comprobamos si en el alta han puesto campos nulos
		if (invernadero.getNombre().isEmpty() || invernadero.getSustrato().isEmpty() && invernadero.getTipo_iluminacion().isEmpty()) {
			return -3; // Enviamos error de no se pueden dejar campos vacios en ALTA
		}
		int exito = -1;
		Transaccion t = null;
		try {
			TransaccionManager transaction = TransaccionManager.getInstance();
			t = transaction.newTransaccion();
			t.start();
			FactoriaIntegracion f = FactoriaIntegracion.getInstance();

			InvernaderoDAO daoInvernadero = f.getInvernaderoDAO();
			TInvernadero nuevoInvernadero = daoInvernadero. mostrarInvernaderoPorNombre(invernadero.getNombre());
			if (nuevoInvernadero == null) { // No existe un invernadero con el mismo nombre, por lo tanto damos de alta sin problema
				exito = daoInvernadero.altaInvernadero(invernadero);
				t.commit();
			} else if (!nuevoInvernadero.isActivo()) { // Comprobamos si ese invernadero que ya tiene el mismo nombre esta dado de baja
				// Procedemos a reactivarlo y actualizar sus valores
				invernadero.setId(nuevoInvernadero.getId());
				exito = daoInvernadero.modificarInvernadero(invernadero);
				t.commit();
			} else {
				// Mostramos error
				exito = -23; // Error de que ya existe un habitat con el mismo nombre y activo
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	public Integer bajaInvernadero(Integer id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer desvincularSRInvernadero(Integer id_sistema_riego, Integer id_invernadero) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Collection<TInvernadero> listarInvernadero() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Collection<TInvernadero> listarInvernaderoPorSR(Integer id_sistema_riegos) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer modificarInvernadero(TInvernadero invernadero) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public TInvernadero mostrarInvernaderoPorID(Integer id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer vincularSRInvernadero(Integer id_sistema_riego, Integer id_invernadero) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}