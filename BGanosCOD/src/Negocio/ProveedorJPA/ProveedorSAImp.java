package Negocio.ProveedorJPA;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import Negocio.EMFSingleton.EMFSingleton;

public class ProveedorSAImp implements ProveedorSA {

	public Integer altaProveedor(TProveedor tProv) {
		int exito = -1; // Error general
		Proveedor provExiste;
		EntityManager em = null;

		if (!this.validarCamposRellanados(tProv)) {
			exito = -2;// Campos vacios
		} else if (!this.comprobarTelefono(tProv.getTelefono())) {
			exito = -3; // Telefono mal definido
		} else {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();
				TypedQuery<Proveedor> query = em.createNamedQuery("Negocio.ProveedorJPA.Proveedor.findByCIF",
						Proveedor.class);
				query.setParameter("CIF", tProv.getCIF());

				try {
					provExiste = query.getSingleResult();
				} catch (NoResultException e) {
					provExiste = null;
				}

				EntityTransaction transaction = em.getTransaction();
				transaction.begin();

				if (provExiste == null) {
					Proveedor nuevoProveedor = new Proveedor();
					nuevoProveedor.transferToEntity(tProv);
					em.persist(nuevoProveedor);
					transaction.commit();
					exito = nuevoProveedor.getId();
				} else {
					if (provExiste.getActivo()) {
						exito = -4; // YA HAY UN PROVEEDOR ACTIVO CON ESE CIF
						transaction.rollback();
					} else {
						tProv.setId(provExiste.getId());
						provExiste.transferToEntity(tProv);
						exito = provExiste.getId();
						transaction.commit();
					}
				}
			} catch (Exception e) {
				exito = -1;
			} finally {
				if (em != null) {
					em.close();
				}
			}
		}

		return exito;
	}

	public Integer bajaProveedor(Integer id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer modificarProveedor(TProveedor tProv) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Set<TProveedor> listarProveedor() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public TProveedor mostrarProveedorPorID(Integer id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public TProveedor mostrarProveedorPorCIF(String CIF) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer vincularMarca(Integer idProv, Integer idMarca) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer desvincularMarca(Integer idProv, Integer idMarca) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Set<TProveedor> listarProveedoresDeMarca(Integer idMarca) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	/* FUNCIONES DE SOPORTE */

	private boolean comprobarTelefono(String telefono) {
		return telefono.matches("\\d{9}");
	}

	private boolean validarCamposRellanados(TProveedor proveedor) {
		if (proveedor.getCIF().isEmpty() || proveedor.getNombre().isEmpty() || proveedor.getTelefono().isEmpty()) {
			return false;
		}

		return true;
	}

}