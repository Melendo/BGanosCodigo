/**
 * 
 */
package Negocio.EmpleadoDeCajaJPA;

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
		String nombre = empleado.getNombre();
		
		if (nombre != null && !nombre.isEmpty()){
			return -4;
		}

		EntityManager entityManager = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction entityTrans = entityManager.getTransaction();
		entityTrans.begin();

		
		TypedQuery<Turno> query2 = entityManager.createNamedQuery("Negocio.TurnoJPA.Departamento.findByid", Turno.class);
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

		return null;
	}

	
	public Set<TEmpleadoDeCaja> ListarEmpleadoDeCajaPorNombre(String nombre) {
		
		return null;
	}

	
	public Set<TEmpleadoDeCaja> MostrarEmpleadoDeCajaPorId(Integer id) {
		
		return null;
	}

	public Set<TEmpleadoDeCaja> ListarEmpleadosPorTurno(Integer idTurno) {
		
		return null;
	}

	public Set<TEmpleadoDeCaja> ListarEmpleadosDeCaja() {
		
		return null;
	}
}