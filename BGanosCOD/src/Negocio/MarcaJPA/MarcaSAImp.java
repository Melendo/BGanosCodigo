package Negocio.MarcaJPA;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Negocio.EMFSingleton.EMFSingleton;
import Negocio.ProveedorJPA.Proveedor;

public class MarcaSAImp implements MarcaSA {

	public synchronized Integer altaMarca(TMarca marca) {
		Integer id = -1;
		boolean exito = false;
		Marca marcaExistente = null;
		Marca nuevaMarca = null;

		if (!validarNombre(marca.getNombre())) {
			return -4;
		}

		// Empieza una transacción
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();

		// Obtenemos la Marca por nombre
		TypedQuery<Marca> query = em.createNamedQuery("Negocio.MarcaJPA.Marca.findBynombre", Marca.class);
		query.setParameter("nombre", marca.getNombre());

		try {
			marcaExistente = query.getSingleResult();
		} catch (Exception e) {
			System.out.println("No existe marca con el mismo nombre");
		}

		if (marcaExistente != null) {
			if (!marcaExistente.getActivo()) {
				// Reactivamos
				marcaExistente.transferToEntity(marca);
				id = marcaExistente.getId();
				try {
					t.commit();
					em.close();
					return id;
				} catch (Exception e) {
					t.rollback();
					em.close();
					return id;
				}

			} else { // si está activo rollback
				t.rollback();
				em.close();
				return -143;
			}

		} else {
			nuevaMarca = new Marca(marca);
			em.persist(nuevaMarca);
			exito = true;
		}

		try {
			t.commit();
			if (exito)
				id = nuevaMarca.getId();
		} catch (Exception e) {
			t.rollback();
		}

		em.close();

		return id;
	}

	public Integer bajaMarca(Integer id) {

		int res = -1;

		if (!validarId(id)) {
			System.out.println("Formato incorrecto para el ID de marca");
			return -4;
		}

		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();

		Marca marca = em.find(Marca.class, id);

		// TODO: preguntar si debo añadir un contador en la clase Marca.java,
		// para añadir en el if el marca.getContador() == 0
		if (marca != null && marca.getActivo()) {

			List<Proveedor> listaProveedores = marca.getProveedores();

			// Desvincular de la marca sus proveedores
			for (Proveedor p : listaProveedores) {
				p.getMarca().remove(marca);
			}

			listaProveedores.clear();
			marca.setActivo(false);

			try {
				t.commit();
				res = marca.getId();

			} catch (Exception e) {
				t.rollback();
				em.close();
				return res;
			}

		} else {
			t.rollback();
			em.close();
			return 142;
		}

		em.close();
		return res;
	}

	public Integer modificarMarca(TMarca marca) {

		int res = -1;
		
		if(!validarNombre(marca.getNombre())){
			System.out.println("Nombre de Marca inválido");
			return -4;
		}
		
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		Marca m = em.find(Marca.class, marca.getId());
		marca.setActivo(m.getActivo());
		if(m != null && m.getActivo()) {
			TypedQuery<Marca> query = em.createNamedQuery("Negocio.MarcaJPA.Marca.findBynombre", Marca.class);
			query.setParameter("nombre", marca.getNombre());
			Marca mExistente = null;
			
			try {
				mExistente = query.getSingleResult();
			} catch (Exception e){
				// No hay una marca con el mismo nombre
			}
			
			if(mExistente == null) {
				m.transferToEntity(marca);
//				em.persist(m); creo que esto no hace falta
				
				try {
					t.commit();
					res = m.getId();
				} catch(Exception e) {
					t.rollback();
					em.close();
					return res;
				}
				
			} else {
				t.rollback();
				em.close();
				return -145;
			}
					
		} else {
			t.rollback();
			em.close();
			return -144;
		}
		
		em.close();
		return res;
	}

	// TODO: cambiado, era de tipo integer y no le pasaba nada por parámetro)
	public TMarca mostrarMarcaPorId(Integer id) {

		if (!validarId(id)) {
			System.out.println("Formato incorrecto para el ID de marca");
			return null;
		}

		EMFSingleton entityManagerFactory = EMFSingleton.getInstance();
		EntityManager entityManager = entityManagerFactory.getEMF().createEntityManager();

		Marca marca = entityManager.find(Marca.class, id);

		if (marca == null) {
			return null;
		}

		TMarca tMarca = new TMarca(id, marca.getNombre(), marca.getPaisOrigen(), marca.getActivo());

		entityManager.close();
		return tMarca;
	}

	public Set<TMarca> listarMarcas() {
		// no hay transacción porque hay un listar
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();

		TypedQuery<Marca> query = em.createNamedQuery("Negocio.MarcaJPA.Marca.findAll", Marca.class);

		// TODO: preguntar si es mejor añadir el cast a set, o directamente ponerlo a
		// list el método
		List<Marca> l = query.getResultList(); // obtenemos una lista de marcas
		Set<TMarca> lista = new HashSet<TMarca>();

		for (Marca m : l) {
			TMarca t = m.entityToTransfer();
			lista.add(t);
		}

		em.close();
		return lista;

	}

	public Set<TMarca> listarMarcasPorProveedor(Integer idProv) {
		// Empieza una transacción
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();

		Proveedor proveedor = em.find(Proveedor.class, idProv);

		if (proveedor == null) {
			return null;
		}

		Set<Marca> marcasProveedor = proveedor.getMarca();
		// TODO preguntar si está bien el cast
		Set<TMarca> marcas = new HashSet<TMarca>();

		for (Marca marca : marcasProveedor) {
			marcas.add(new TMarca(marca));
		}

		t.commit();
		em.close();

		return marcas;

	}

	private Boolean validarNombre(String nombre) {
		return nombre != null && !nombre.isEmpty();
	}

	private boolean validarId(Integer id) {
		return id != null && id > 0;
	}
}