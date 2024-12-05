package Negocio.ProveedorJPA;

import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;

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
		int exito = -1; // Error general
		Proveedor provExiste;
		EntityManager em = null;

		if (id < 1) {
			exito = -2;// Id debe ser mayor que 0

		} else {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				EntityTransaction transaction = em.getTransaction();
				transaction.begin();

				provExiste = em.find(Proveedor.class, id);
				if (provExiste != null) {
					if (provExiste.getActivo()) {
						provExiste.setActivo(false);
						transaction.commit();
						exito = provExiste.getId();
					} else {
						transaction.rollback();
						exito = -3; // El proveedor ya esta dado de baja
					}
				} else {
					transaction.rollback();
					exito = -4; // El proveedor no existe
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

	public Integer modificarProveedor(TProveedor tProv) {
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

				provExiste = em.find(Proveedor.class, tProv.getId());

				EntityTransaction transaction = em.getTransaction();
				transaction.begin();

				if (provExiste == null) {
					exito = -4; // NO HAY UN PROVEEDOR ACTIVO CON ESE ID
					transaction.rollback();
				} else {
					TypedQuery<Proveedor> query = em.createNamedQuery("Negocio.ProveedorJPA.Proveedor.findByCIF",
							Proveedor.class);
					query.setParameter("CIF", tProv.getCIF());
					Proveedor proveedorCIF;
					try {
						proveedorCIF = query.getSingleResult();
					} catch (NoResultException e) {
						proveedorCIF = null;
					}

					if (proveedorCIF == null || (proveedorCIF != null && proveedorCIF.getId() == tProv.getId())) {
						tProv.setActivo(provExiste.getActivo());
						provExiste.transferToEntity(tProv);
						transaction.commit();
						exito = provExiste.getId();
					} else {
						exito = -5; // YA HAY UN PROVEEDOR CON ESE CIF
						transaction.rollback();
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

	public Set<TProveedor> listarProveedor() {
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();

		TypedQuery<Proveedor> query = em.createNamedQuery("Negocio.ProveedorJPA.Proveedor.findAll", Proveedor.class);
		List<Proveedor> l = query.getResultList();
		Set<TProveedor> proveedores = new LinkedHashSet<TProveedor>();

		for (Proveedor p : l) {
			proveedores.add(p.entityToTransfer());
		}

		em.close();

		return proveedores;
	}

	public TProveedor mostrarProveedorPorID(Integer id) {
		TProveedor prov = new TProveedor();
		prov.setId(-1); // Error general
		EntityManager em = null;

		if (id < 1) {
			prov.setId(-2); // Id debe ser mayor que 0

		} else {
			try {
				em = EMFSingleton.getInstance().getEMF().createEntityManager();

				Proveedor provExiste = em.find(Proveedor.class, id);

				if (provExiste != null) {
					prov = provExiste.entityToTransfer();

				} else {
					prov.setId(-3); // El proveedor no existe
				}
			} catch (Exception e) {
				prov.setId(-1); // Error general
			} finally {
				if (em != null) {
					em.close();
				}
			}
		}
		return prov;
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