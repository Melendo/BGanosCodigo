
package Negocio.ProductoJPA;

import java.util.ArrayList;
import java.util.List;
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

	public List<TProducto> listarProductosPorMarca(Integer idMarca) {
		// Empieza una transacción
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		List<TProducto> lista = new ArrayList<TProducto>();
		
		Marca marca = em.find(Marca.class, idMarca,  LockModeType.OPTIMISTIC);
		
		if(marca == null || !marca.getActivo()){
			t.rollback();
			
			lista = null;
		}
		else
		{
			/*
			for(Producto p: marca.getProductos()){
				em.lock(p, LockModeType.OPTIMISTIC);
				lista.add(p.entityToTransfer());
			}
			
			*/
			t.commit();
		}
		
		return lista;
	}


	public List<TProducto> listarProductosPorTipo(Integer tipo) {
		// Empieza una transacción
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		List<TProducto> listaTipos = new ArrayList<TProducto>();
		
		final TypedQuery<Producto> query = em.createNamedQuery("negocio.ProductoJPA.Producto.findAll", Producto.class);
		
		final List<TProducto> lista = query.getResultList().stream().map(Producto::entityToTransfer).collect(Collectors.toList());
		
		for(TProducto p : lista){
			if(p.getTipoProducto() == tipo){
				listaTipos.add(p);
			}
		}

		t.commit();
		
		return listaTipos;
	}


	public List<TProducto> listarProductoPorVenta(Integer idVenta) {
	
		// Empieza una transacción
				EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
				EntityTransaction t = em.getTransaction();
				t.begin();
				
		
		return null;
	
	}


	public Integer modificarProducto(TProducto producto) {
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		int id = -1;
		
		Marca marca = em.find(Marca.class, producto.getIdMarca(), LockModeType.OPTIMISTIC_FORCE_INCREMENT);
		if(marca == null || !marca.getActivo()){
			id = -2;
			t.rollback();
			
		}
		else{
			Producto p = em.find(Producto.class, producto.getId());
			if(p == null ){
				id = -3;
				t.rollback();
			}
			else{
				if(producto.getTipoProducto() == 0){
					TProductoAlimentacion tali = (TProductoAlimentacion)producto;
					
					ProductoAlimentacion ali = (ProductoAlimentacion) p;
					ali.setActivo(tali.getActivo());
					ali.setId(tali.getId());
					ali.setMarca(marca);
					ali.setNombre(tali.getNombre());
					ali.setPeso(tali.getPeso());
					ali.setPrecio(tali.getPrecio());
					ali.setPrecioKilo(tali.getPrecioKilo());
					ali.setStock(tali.getStock());
					ali.setTipo(tali.getTipo());
				}
				else{
					ProductoSouvenirs sou = (ProductoSouvenirs) p;
					TProductoSouvenirs tsou = (TProductoSouvenirs) producto;
					
					sou.setActivo(tsou.getActivo());
					sou.setId(tsou.getId());
					sou.setMarca(marca);
					sou.setNombre(tsou.getNombre());
					sou.setDescripcion(tsou.getDescripcion());
					sou.setPrecio(tsou.getPrecio());
					sou.setStock(tsou.getStock());
					
				}
				t.commit();
				id = producto.getId();
			}
		}
		
		
		return id;
	}

	
	public TProducto mostrarProducto(Integer idProducto) {
		// Empieza una transacción
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		
		Producto p = em.find(Producto.class, idProducto, LockModeType.OPTIMISTIC);
	
		TProducto res = null;
		if(p == null){
			t.rollback();
			
		}
		else{
			
			res = p.entityToTransfer();
			t.commit();
		}
		
		return res;
	}
}