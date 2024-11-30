/**
 * 
 */
package Negocio.ProductoJPA;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import Negocio.EMFSingleton.EMFSingleton;
import Negocio.MarcaJPA.Marca;



public class ProductoSAImp implements ProductoSA {

	public Integer altaProductoAlimentacion(TProducto producto) {
		
		Integer id = -1;
		
		// Empieza una transacción
				EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
				EntityTransaction t = em.getTransaction();
				t.begin();
				
				//busco marca
				Marca marca = em.find(Marca.class, producto.getIdMarca(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				
				if(marca == null || !marca.getActivo()){
					id = -2;
					t.rollback();
				}
				else{
					TypedQuery<Producto> query = em.createNamedQuery("negocio.ProductoJPA.Producto.findBynombre", Producto.class);
					query.setParameter("nombre", producto.getNombre());
					List<Producto> data = query.getResultList();
					Producto p = data.isEmpty() ? null : data.get(0);
					
					if(p != null && p instanceof ProductoSouvenirs){
						id = -3;
						t.rollback();
					}
					else if(p != null){
						id = -3;
						t.rollback();
					}
					else{
						p = new Producto(producto);
						p.setMarca(marca);
						//marca.getProductos().add(p);
						em.persist(p);
						t.commit();
						id = p.getId();
						p.setId(id);
					}
					
				}
				
				
		return id;
		
	}
	
	public Integer altaProductoSouvenirs (TProducto producto){
		
		Integer id = -1;
		
		// Empieza una transacción
				EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
				EntityTransaction t = em.getTransaction();
				t.begin();
				
				//busco marca
				Marca marca = em.find(Marca.class, producto.getIdMarca(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
				
				if(marca == null || !marca.getActivo()){
					id = -2;
					t.rollback();
				}
				else{
					TypedQuery<Producto> query = em.createNamedQuery("negocio.ProductoJPA.Producto.findBynombre", Producto.class);
					query.setParameter("nombre", producto.getNombre());
					
					List<Producto> data = query.getResultList();
					Producto p = data.isEmpty() ? null : data.get(0);
					
					if(p != null && p instanceof ProductoAlimentacion){
						id = -3;
						t.rollback();
					}
					else if(p != null){
						id = -3;
						t.rollback();
					}
					else{
						p = new Producto(producto);
						p.setMarca(marca);
						//marca.getProductos().add(p);
						em.persist(p);
						t.commit();
						id = p.getId();
						p.setId(id);
					}
					
				}
		
		return id;
	}

	public Integer bajaProducto(Integer idProducto) {
		int res = -1;
		
		// Empieza una transacción
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		Producto p = em.find(Producto.class, idProducto);
		if(p == null){
			res = -2;
			t.rollback();
		}
		else{
			p.setActivo(false);
			t.commit();
			res = p.getId();
		}
		
		
		return res;
		
	}


	public List<TProducto> listarProductos() {
		
		
		// Empieza una transacción
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		final TypedQuery<Producto> query = em.createNamedQuery("negocio.ProductoJPA.Producto.findAll", Producto.class);
		
		final List<TProducto> lista = query.getResultList().stream().map(Producto::entityToTransfer).collect(Collectors.toList());
		
		t.commit();
		
		
		return lista;
		
	}


	public Set<TProducto> listarProductosPorMarca(Integer idMarca) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}


	public Set<TProducto> listarProductosPorTipo(Integer tipo) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}


	public Set<TProducto> listarProductoPorVenta(Integer idVenta) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}


	public Integer modificarProducto(TProducto producto) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	
	public TProducto mostrarProducto(Integer idProducto) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}