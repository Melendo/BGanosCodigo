package Negocio.MarcaJPA;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Negocio.EMFSingleton.EMFSingleton;

public class MarcaSAImp implements MarcaSA {

	public Integer altaMarca(TMarca marca) {
		Integer id = -1;
		boolean exito = false;
		Marca marcaExistente = null;
		Marca nuevaMarca = null;
		
		if(!validarNombre(marca.getNombre())) {
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
		
		if(marcaExistente != null) {
			if(!marcaExistente.getActivo()) {
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
			if(exito)
				id = nuevaMarca.getId();
		} catch (Exception e) {
			t.rollback();
		}
		
		em.close();
		
		return id;
	}
	
	public Integer bajaMarca(Integer id) {
		return null;

	}

	public Integer modificarMarca(TMarca marca) {
		return null;

	}

	// TODO: cambiado, era de tipo integer y no le pasaba nada por parámetro)
	public TMarca mostrarMarcaPorId(Integer id) {
		return null;

	}
	
	public Set<TMarca> listarMarcas() {
		return null;

	}

	public Set<TMarca> listarMarcasPorProveedor(Integer idProv) {
		return null;

	}
	
	private Boolean validarNombre(String nombre){
		return nombre != null && !nombre.isEmpty();
	}
	
    private boolean validarId(Integer id) {
        return id != null && id > 0;
    }
}