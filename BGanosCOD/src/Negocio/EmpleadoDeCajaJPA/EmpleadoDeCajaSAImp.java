/**
 * 
 */
package Negocio.EmpleadoDeCajaJPA;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Negocio.EMFSingleton.EMFSingleton;
import Negocio.TurnoJPA.Turno;


public class EmpleadoDeCajaSAImp implements EmpleadoDeCajaSA {
	
	public Integer altaEmpleadoDeCaja(TEmpleadoDeCaja empleado) {

		Integer id = -1;
		boolean exito = false;
		EmpleadoDeCaja empleadoExistente = null;
		EmpleadoDeCaja empleadoNuevo = null;
		Turno turno = null;
		

		EntityManager entityManager = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction entityTrans = entityManager.getTransaction();
		entityTrans.begin();

		
		TypedQuery<Turno> query2 = entityManager.createNamedQuery("Negocio.TurnoJPA.Turno.findByid", Turno.class);
		query2.setParameter("id", empleado.getId_Turno());
	
		try {
			turno = query2.getSingleResult();
		} catch (Exception e) {
			entityTrans.rollback();
            entityManager.close();
			return -404; // Turno no existe
		}
		
		if(!turno.isActivo()){
			
			entityTrans.rollback();
            entityManager.close();
			return -403; // Turno no activo
		}

		TypedQuery<EmpleadoDeCaja> query = entityManager.createNamedQuery("Negocio.EmpleadoDeCajaJPA.Trabajador.findByid", EmpleadoDeCaja.class);
		query.setParameter("dni", empleado.getDNI());
		
		try {
			empleadoExistente = query.getSingleResult();
		} catch (Exception e) {
			
		}
		
		if(empleadoExistente != null){ //Empleado existe
       
        	if(!empleadoExistente.getActivo()){	 // Empleado no activo
                
        		id = empleadoExistente.getId();
        		empleadoExistente.transferToEntity(empleado);
				//mirar si ahcer algo con turno
        		
                entityTrans.commit();
                entityManager.close();
                return id;
        	}
        	else{
        		entityTrans.rollback();   
                entityManager.close();
        		return -501; // Empleado existe y activo
        	}
        }
		 else{
				if(empleado instanceof TEmpleadoCompleto){
					empleadoNuevo = new EmpleadoCompleto(empleado);
				}
				else if(empleado instanceof TEmpleadoParcial){
					empleadoNuevo = new EmpleadoParcial(empleado);
				}
				
				//turno
				
	            entityManager.persist(empleadoNuevo);
	            exito = true;
	        }
		try{
        	entityTrans.commit();
        	if(exito){
        		id = empleadoNuevo.getId();
        	}
        		
        }
        catch(Exception e){
        	entityTrans.rollback();
        }
        entityManager.close();
        
        return id;
	}

	
	public Integer bajaEmpleadoDeCaja(Integer idEmpleado) {
		int res = -1;
		
    	
        if (idEmpleado != null && idEmpleado > 0) {
            return -4; // id incorrecto
        }

        EntityManager entityManager = EMFSingleton.getInstance().getEMF().createEntityManager();
        EntityTransaction entityTrans = entityManager.getTransaction();
        entityTrans.begin();
		
		EmpleadoDeCaja empleado = entityManager.find(EmpleadoDeCaja.class, idEmpleado);
		
		if(empleado != null){ 
			if(empleado.getActivo()){
				empleado.setActivo(false);
//				// Obtenemos el turno por id
//				TypedQuery<Turno> query2 = entityManager.createNamedQuery("Negocio.TurnoJPA.Turno.findByid", Turno.class);
//				query2.setParameter("id", empleado.getTurno().getId());
//				
//				Turno turno = null;
//				try {
//					turno = query2.getSingleResult();
//				} catch (Exception e) {
//					
//					entityTrans.rollback();
//			        entityManager.close();
//					return -115;
//				}
//				//turno?
				
				
				try {				
					entityTrans.commit();
					res = empleado.getId();
				} catch (Exception e) {
					entityTrans.rollback();
			        entityManager.close();
					return res;
				}
			}
			else{
				entityTrans.rollback();
		        entityManager.close();
				return -403; //Empleado inactivo
			}
			
		}
		else{
			entityTrans.rollback();
	        entityManager.close();
			return -404; //Empleado no encontrado 
		}
		
		entityManager.close();
		return res;
	}

	
	public Integer ModificarEmpleadoDeCaja(TEmpleadoDeCaja empleado) {

		Integer res = -1;
		String nombre = empleado.getNombre();
		
		if (nombre != null && !nombre.isEmpty()){
			return -4;
		}
		
	    EntityManager entityManager = EMFSingleton.getInstance().getEMF().createEntityManager();
	    EntityTransaction entityTrans = entityManager.getTransaction();
	    entityTrans.begin();

	    EmpleadoDeCaja empModificar = entityManager.find(EmpleadoDeCaja.class, empleado.getID());

	    if (empModificar != null ) { //Existe
	    	TypedQuery<EmpleadoDeCaja> query = entityManager.createNamedQuery("Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja.findByDNI", EmpleadoDeCaja.class);
	    	query.setParameter("DNI", empleado.getNombre());
	    	EmpleadoDeCaja empExistente = null;
    		Turno turno = null;

    		try{
    			empExistente = query.getSingleResult();
    		}
    		catch(Exception e){
    		 
        	}
        		
    		if(empExistente == null || empExistente.getId() == empModificar.getId()){ // No existe o es el mismo
    			
    			TypedQuery<Turno> query2 = entityManager.createNamedQuery("Negocio.TurnoJPA.Turno.findByid", Turno.class);
    			query2.setParameter("id", empleado.getId_Turno());
    			
    			//Se comprueba si existe
    			try {
    				turno = query2.getSingleResult();
    			} catch (Exception e) {
    				
    				entityTrans.rollback();
    	            entityManager.close();
    				return -115;
    		}        		
        			
    			if(turno.isActivo()){
    				empModificar.transferToEntity(empleado);
    				empModificar.setTurno(turno);
    				
					try {				
						entityTrans.commit();
						res = empModificar.getId();
					} catch (Exception e) {
						entityTrans.rollback();
				        entityManager.close();
						return res;
					}
    			}
        	}
    		else{
    			entityTrans.rollback();
    	        entityManager.close();
    			return -501; // exite ya el DNI y no es mismo
    		}	
        }
        else{
        	entityTrans.rollback();
            entityManager.close();
        	return -404; //empleado no existe
        }
        entityManager.close();

    return res;    		
	}

	
	public Set<TEmpleadoDeCaja> ListarEmpleadoDeCajaPorNombre(String nombre) {

		EntityManager entityManager = EMFSingleton.getInstance().getEMF().createEntityManager();

		TypedQuery<EmpleadoDeCaja> query = entityManager.createNamedQuery("Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja.findBynombre", EmpleadoDeCaja.class);
		query.setParameter("nombre", nombre);
		
		List<EmpleadoDeCaja> lista = query.getResultList(); 
		Set<TEmpleadoDeCaja> res = new LinkedHashSet<TEmpleadoDeCaja>(lista.size());

		for (EmpleadoDeCaja empleado : lista) {
			TEmpleadoDeCaja tEmpleado = empleado.entityToTransfer();
			res.add(tEmpleado);
		}
		
		entityManager.close();
		return res;
	}
	
	
	public TEmpleadoDeCaja MostrarEmpleadoDeCajaPorId(Integer id) {

		if (id != null && id > 0) {
			return null; // id incorrecto
	    }

        EntityManager entityManager = EMFSingleton.getInstance().getEMF().createEntityManager();
        EmpleadoDeCaja empleado = entityManager.find(EmpleadoDeCaja.class, id);

		if (empleado == null){ // no encontrado
	        entityManager.close();
	        return null;
		}

		TEmpleadoDeCaja TTrabajador = empleado.entityToTransfer();
		
		entityManager.close();
		
		return TTrabajador;
	}

	public Set<TEmpleadoDeCaja> ListarEmpleadosPorTurno(Integer idTurno) {

		EntityManager entityManager = EMFSingleton.getInstance().getEMF().createEntityManager();

		TypedQuery<EmpleadoDeCaja> query = entityManager.createNamedQuery("Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja.findByturno", EmpleadoDeCaja.class);
		query.setParameter("id_Turno", idTurno);
		
		List<EmpleadoDeCaja> lista = query.getResultList(); 
		Set<TEmpleadoDeCaja> res = new LinkedHashSet<TEmpleadoDeCaja>(lista.size());

		for (EmpleadoDeCaja empleado : lista) {
			TEmpleadoDeCaja tEmpleado = empleado.entityToTransfer();
			res.add(tEmpleado);
		}
		
		entityManager.close();
		return res;
	}

	public Set<TEmpleadoDeCaja> ListarEmpleadosDeCaja() {

		EntityManager entityManager = EMFSingleton.getInstance().getEMF().createEntityManager();

		TypedQuery<EmpleadoDeCaja> query = entityManager.createNamedQuery("Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja.findAll", EmpleadoDeCaja.class);

		List<EmpleadoDeCaja> lista = query.getResultList();
		Set<TEmpleadoDeCaja> res = new LinkedHashSet<TEmpleadoDeCaja>(lista.size());

		for (EmpleadoDeCaja empleado : lista) {
			TEmpleadoDeCaja tEmpleado = empleado.entityToTransfer();
			res.add(tEmpleado);
		}
		
		entityManager.close();
		return res;
	}
}