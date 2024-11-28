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
		boolean success = false;
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

		
		TypedQuery<Turno> query2 = entityManager.createNamedQuery("Negocio.TurnoJPA.Departamento.mostrarTurno", Turno.class);
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

		TypedQuery<EmpleadoDeCaja> query = entityManager.createNamedQuery("Negocio.EmpleadoDeCajaJPA.Trabajador.MostrarEmpleadoDeCajaPorId", EmpleadoDeCaja.class);
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
		 else {
				if(empleado instanceof TEmpleadoCompleto){
					//empleadoNuevo = new TEmpleadoCompleto(empleado);
				}
				else if(empleado instanceof TEmpleadoParcial){
					//empleadoNuevo = new TEmpleadoParcial(empleado);
				}
				
	            entityManager.persist(empleadoNuevo);
	            success = true;
	        }
		try{
        	entityTrans.commit();
        	if(success){
        		id = empleadoNuevo.getId();
        	}
        		
        }
        catch(Exception e){
        	entityTrans.rollback();
        }
        entityManager.close();
        
        return id;
	}

	
	public Integer bajaEmpleadoDeCaja() {
		
		return null;
	}

	
	public Integer ModificarEmpleadoDeCaja() {

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