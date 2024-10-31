/**
 * 
 */
package Negocio.Invernadero;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Invernadero.InvernaderoDAO;
import Integracion.Invernadero.TieneDAO;
import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;
import Negocio.Entrada.TEntrada;
import Negocio.Planta.TPlanta;
import Negocio.SistemaDeRiego.TSistemaDeRiego;

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

		int exito = -1;
		Transaccion t = null;
		try {
			TransaccionManager transaction = TransaccionManager.getInstance();
			t = transaction.newTransaccion();
			t.start();
			FactoriaIntegracion f = FactoriaIntegracion.getInstance();

			InvernaderoDAO daoInvernadero = f.getInvernaderoDAO();
			TInvernadero invernadero = daoInvernadero.mostrarInvernaderoPorID(id);
			if (invernadero != null && invernadero.isActivo()) {
				Set<TEntrada> entradasActivas = f.getEntradaDAO().listarEntradasPorInvernadero(id);
				Set<TSistemaDeRiego> sisRiegoActivos = f.getSistemaDeRiegoDAO().listarSistemaDeRiegoInvernadero(id);
				Set<TPlanta> plantasActivas = f.getPlantaDAO().MostrarPorInvernadero(id);
				if (entradasActivas.size() == 0 && sisRiegoActivos.size() == 0 && plantasActivas.size() == 0) {
					exito = daoInvernadero.bajaInvernadero(id);
					t.commit();
				} else {
					exito = -24; // invernadero no puede darse de baja si tiene plantas, entradas o sistemas de
									// riego activos y vinculados
					t.rollback();
				}
			} else {
				exito = -23; // invernadero no existe o esta inactivo
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
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
					if (daoInvernadero.mostrarInvernaderoPorNombre(invernadero.getNombre()) == null) {
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
			if (invernaderoExiste != null && invernaderoExiste.isActivo()) {
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

	public Collection<TInvernadero> listarInvernaderoPorSR(Integer id_sistema_riegos) {

		Set<TInvernadero> invernaderos = new HashSet<TInvernadero>();
		Transaccion t = null;
		try {
			TransaccionManager transaction = TransaccionManager.getInstance();
			t = transaction.newTransaccion();
			t.start();
			FactoriaIntegracion f = FactoriaIntegracion.getInstance();

			InvernaderoDAO daoInvernadero = f.getInvernaderoDAO();
			TSistemaDeRiego sisRiego = f.getSistemaDeRiegoDAO().mostrarSistemaDeRiegoPorID(id_sistema_riegos);
			if (sisRiego != null && sisRiego.getActivo()) {
				invernaderos = daoInvernadero.listarInvernaderoPorSR(id_sistema_riegos);
				t.commit();
			}else {
				TInvernadero inv = new TInvernadero();
				inv.setId(-1);
				invernaderos.add(inv);
				t.rollback();
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return invernaderos;
	}

	public Integer vincularSRInvernadero(Integer id_sistema_riego, Integer id_invernadero) {
		int exito = -1;
		if (id_sistema_riego == 0 || id_invernadero == 0) {
			exito = -2;
		} else {
			Transaccion t = null;
			try {
				TransaccionManager transaction = TransaccionManager.getInstance();
				t = transaction.newTransaccion();
				t.start();
				FactoriaIntegracion f = FactoriaIntegracion.getInstance();

				TInvernadero invernaderoExiste = f.getInvernaderoDAO().mostrarInvernaderoPorID(id_invernadero);
				if (invernaderoExiste != null && invernaderoExiste.isActivo()) {
					TSistemaDeRiego sisExiste = f.getSistemaDeRiegoDAO().mostrarSistemaDeRiegoPorID(id_sistema_riego);
					if (sisExiste != null && sisExiste.getActivo()) {
						TTiene nuevoTiene = new TTiene();
						nuevoTiene.setId_Invernadero(id_invernadero);
						nuevoTiene.setId_SistemasDeRiego(id_sistema_riego);
						TieneDAO daoT = f.getDaoTiene();
						if (daoT.mostrarTiene(nuevoTiene) == null) {
							exito = daoT.vincularInvernaderoConSisRiego(nuevoTiene);
							t.commit();
						} else {
							exito = -5;
							t.rollback();
						}
					} else {
						exito = -4;
						t.rollback();
					}
				} else {
					exito = -3;
					t.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return exito;
	}

	public Integer desvincularSRInvernadero(Integer id_sistema_riego, Integer id_invernadero) {
		int exito = -1;
		if (id_sistema_riego == 0 || id_invernadero == 0) {
			exito = -2;
		} else {
			Transaccion t = null;
			try {
				TransaccionManager transaction = TransaccionManager.getInstance();
				t = transaction.newTransaccion();
				t.start();
				FactoriaIntegracion f = FactoriaIntegracion.getInstance();
				TInvernadero invernaderoExiste = f.getInvernaderoDAO().mostrarInvernaderoPorID(id_invernadero);
				if (invernaderoExiste != null && invernaderoExiste.isActivo()) {
					TSistemaDeRiego sisExiste = f.getSistemaDeRiegoDAO().mostrarSistemaDeRiegoPorID(id_sistema_riego);
					if (sisExiste != null && sisExiste.getActivo()) {
						TTiene nuevoTiene = new TTiene();
						nuevoTiene.setId_Invernadero(id_invernadero);
						nuevoTiene.setId_SistemasDeRiego(id_sistema_riego);
						TieneDAO daoT = f.getDaoTiene();
						if (daoT.mostrarTiene(nuevoTiene) != null) {
							exito = daoT.desvincularInvernaderoConSisRiego(nuevoTiene);
							t.commit();
						} else {
							exito = -5;
							t.rollback();
						}
					} else {
						exito = -4;
						t.rollback();
					}
				} else {
					exito = -3;
					t.rollback();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return exito;
	}

}