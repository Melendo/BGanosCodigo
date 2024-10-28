/**
 * 
 */
package Negocio.Invernadero;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Invernadero.InvernaderoDAO;
import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;

public class InvernaderoSAImp implements InvernaderoSA {

	public Integer altaInvernadero(TInvernadero invernadero) {
		if (invernadero.getNombre().isEmpty()
				|| invernadero.getSustrato().isEmpty() && invernadero.getTipo_iluminacion().isEmpty()) {
			return -3;
		}
		int exito = -1;
		Transaccion t = null;
		try {
			TransaccionManager transaction = TransaccionManager.getInstance();
			t = transaction.newTransaccion();
			t.start();
			FactoriaIntegracion f = FactoriaIntegracion.getInstance();

			InvernaderoDAO daoInvernadero = f.getInvernaderoDAO();
			TInvernadero nuevoInvernadero = daoInvernadero.mostrarInvernaderoPorNombre(invernadero.getNombre());
			if (nuevoInvernadero == null) {
				exito = daoInvernadero.altaInvernadero(invernadero);
				t.commit();
			} else if (!nuevoInvernadero.isActivo()) {

				invernadero.setId(nuevoInvernadero.getId());
				exito = daoInvernadero.modificarInvernadero(invernadero);
				t.commit();
			} else {

				exito = -23;
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
		Set<TInvernadero> invernaderos = new HashSet<>();
		Transaccion t = null;
		try {
			TransaccionManager transaction = TransaccionManager.getInstance();
			t = transaction.newTransaccion();
			t.start();
			FactoriaIntegracion f = FactoriaIntegracion.getInstance();

			InvernaderoDAO daoInvernadero = f.getInvernaderoDAO();
			invernaderos = daoInvernadero.listarInvernadero();

			t.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return invernaderos;
	}

	public Collection<TInvernadero> listarInvernaderoPorSR(Integer id_sistema_riegos) {
		return null;
	}

	public Integer modificarInvernadero(TInvernadero invernadero) {
		if (invernadero.getId() == 0 || invernadero.getNombre().isEmpty() || invernadero.getSustrato().isEmpty()
				|| invernadero.getTipo_iluminacion().isEmpty()) {
			return -3;
		}
		int exito = -1;
		Transaccion t = null;
		try {
			TransaccionManager transaction = TransaccionManager.getInstance();
			t = transaction.newTransaccion();
			t.start();
			FactoriaIntegracion f = FactoriaIntegracion.getInstance();

			InvernaderoDAO daoInvernadero = f.getInvernaderoDAO();
			TInvernadero nuevoInvernadero = daoInvernadero.mostrarInvernaderoPorID(invernadero.getId());
			if (nuevoInvernadero != null) {
				if (nuevoInvernadero.isActivo()) {
					if (daoInvernadero.mostrarInvernaderoPorNombre(invernadero.getNombre()) != null) {
						exito = daoInvernadero.modificarInvernadero(invernadero);
						t.commit();
					} else {
						exito = -25;
						t.rollback();
					}
				} else {
					exito = -24;
					t.rollback();
				}
			} else {

				exito = -23;
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	public TInvernadero mostrarInvernaderoPorID(Integer id) {
		TInvernadero invernadero = new TInvernadero();
		invernadero.setId(-1);

		try {
			TransaccionManager transaction = TransaccionManager.getInstance();
			Transaccion t = transaction.newTransaccion();
			t.start();
			FactoriaIntegracion f = FactoriaIntegracion.getInstance();
			InvernaderoDAO daoInvernadero = f.getInvernaderoDAO();
			TInvernadero invernaderoExiste = daoInvernadero.mostrarInvernaderoPorID(id);
			if (invernaderoExiste != null) {
				invernadero = invernaderoExiste;
				t.commit();
			} else {
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return invernadero;
	}

	public Integer vincularSRInvernadero(Integer id_sistema_riego, Integer id_invernadero) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	@Override
	public TInvernadero mostrarInvernaderoPorNombre(String nombre) {
		TInvernadero invernadero = new TInvernadero();
		invernadero.setId(-1);

		try {
			TransaccionManager transaction = TransaccionManager.getInstance();
			Transaccion t = transaction.newTransaccion();
			t.start();
			FactoriaIntegracion f = FactoriaIntegracion.getInstance();
			InvernaderoDAO daoInvernadero = f.getInvernaderoDAO();
			TInvernadero invernaderoExiste = daoInvernadero.mostrarInvernaderoPorNombre(nombre);
			if (invernaderoExiste != null) {
				invernadero = invernaderoExiste;
				t.commit();
			} else {
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return invernadero;
	}
}