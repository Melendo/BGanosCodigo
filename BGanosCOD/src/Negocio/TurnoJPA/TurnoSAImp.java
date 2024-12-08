package Negocio.TurnoJPA;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;

import Negocio.EMFSingleton.EMFSingleton;
import Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja;

public class TurnoSAImp implements TurnoSA {

	/*  Lista de retorno
	 *  -4 datos incorrectos
	 *  -3 error en la transacción
	 *  -2 Turno existe y está activo
	 *  -1 Error de Base de datos
	 */
	public Integer altaTurno(TTurno turno) {
		
		Integer id = -1;
		boolean success = false;
		Turno turnoExistente = null;
		Turno nuevoTurno = null;
				
        if (!validarHorario(turno.getHorario()))
            return -4;
        

        EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

		TypedQuery<Turno> query = em.createNamedQuery("Negocio.TurnoJPA.Turno.findByhorario", Turno.class);
		query.setParameter("horario", turno.getHorario());

        try {
        	turnoExistente = query.getSingleResult();     
        } catch (Exception e) {
        	turnoExistente = null;
        }
        
        if(turnoExistente != null) {
        	if(!turnoExistente.isActivo()) {
        		turnoExistente.transferToEntity(turno);
                id = turnoExistente.getId();
                transaction.commit();
                em.close();
                return id;
        	} else {
        		transaction.rollback();   
                em.close();
        		return -2;
        	}
        } else {
        	nuevoTurno = new Turno();
        	nuevoTurno.transferToEntity(turno);
        	nuevoTurno.setActivo(true);
            em.persist(nuevoTurno);
            success = true;
        }
        
        try {
        	transaction.commit();
        	if(success)
        		id = nuevoTurno.getId();
        }
        catch(Exception e){
        	transaction.rollback();
        	return -3;
        }
        
        em.close();
        return id;
	}

	public Integer bajaTurno(Integer idTurno) {
		int resultado = -1;
    	
        if (!validarId(idTurno)) {
            return -4;
        }

        EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
		
		Turno turno = em.find(Turno.class, idTurno);
		
		if(turno != null && turno.isActivo()){
			turno.setActivo(false);
			try {				
				transaction.commit();
				resultado = turno.getId();
			} catch (Exception e) {
				transaction.rollback();
                em.close();
				return resultado;
			}
			
		}
		else{
			transaction.rollback();
            em.close();
			return -2;
		}
		
		em.close();
		return resultado;
	}

	public Integer modificarTurno(TTurno turno) {
		Integer resultado = -1;
	    EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();

	        if (!validarHorario(turno.getHorario()) || !validarId(turno.getId())) {
	        System.out.println("Id o nombre de departamento no v�lido");
	        return -4;
	    }
	    EntityTransaction transaction = em.getTransaction();

	    transaction.begin();

	    Turno turnoBD = em.find(Turno.class, turno.getId());

	    if (turnoBD != null && turnoBD.isActivo()) {
	    	turno.setActivo(turnoBD.isActivo());
    		TypedQuery<Turno> query = em.createNamedQuery("Negocio.TurnoJPA.Turno.findByhorario", Turno.class);
    		query.setParameter("horario", turno.getHorario());
    		int numTurnos;
    		
    		
			numTurnos = query.getResultList().size();
			if(numTurnos > 0) {
				return -2;
			}
    		
			turnoBD.transferToEntity(turno);
			try {				
				transaction.commit();
				resultado = turnoBD.getId();
			} catch (Exception e) {
				transaction.rollback();
                em.close();
				return resultado;
			}
	        			
	    }
        else
        {
        	transaction.rollback();
            em.close();
        	return -2;
        }
        em.close();

	    return resultado;
	}

	public TTurno mostrarTurno(Integer idTurno) {
        TTurno error = new TTurno();
    	
    	if (!validarId(idTurno)) {
            System.out.println("Formato incorrecto para el ID del departamento.");
            error.setId(-4);
            return error;
        }

        EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();

        Turno turno = em.find(Turno.class, idTurno);

		if (turno == null)
		{
			error.setId(-4);
			return error;
		}
		TTurno tTurno = turno.entityToTransfer();
		
		em.close();
		
		return tTurno;	}

	public Set<TTurno> listarTurnos() {
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();

		TypedQuery<Turno> query = em.createNamedQuery("Negocio.TurnoJPA.Turno.findAll", Turno.class);

		List<Turno> l = query.getResultList();
		Set<TTurno> lista = new HashSet<TTurno>();

		for (Turno turno : l) {
			TTurno t = turno.entityToTransfer();
			lista.add(t);
		}
		
		em.close();
		return lista;
	}

	public Float obtenerNominaDelTurno(Integer idTurno) {
		float nominaTotal = (float) 0.0;
	    EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
	        Turno turno = em.find(Turno.class, idTurno, LockModeType.OPTIMISTIC);

	        if (turno == null || !turno.isActivo()) {
	            System.out.println("El departamento con ID " + idTurno + " no existe o no est� activo.");
	            transaction.rollback();
	            em.close();
	            return (float) -4;
	        }

			TypedQuery<EmpleadoDeCaja> query = em.createNamedQuery("Negocio.EmpleadoDeCajaJPA.EmpleadoDeCaja.findByturno", EmpleadoDeCaja.class);
			query.setParameter("turno", turno);
			
			List<EmpleadoDeCaja> l = query.getResultList();
			
	        for (EmpleadoDeCaja empleado : l) {
	            if (empleado.getActivo()) {
					em.lock(empleado, LockModeType.OPTIMISTIC);
					nominaTotal += empleado.getSueldo(); 
	            }
	        }

	    transaction.commit();
	    em.close();
	    
	    return nominaTotal;
	}
	
	//------ Métodos auxiliares ------//
	
	private boolean validarHorario(String horario) {
		return horario != null && !horario.isEmpty();
	}
	
	private boolean validarId(Integer id) {
        return id != null && id > 0;
    }
}