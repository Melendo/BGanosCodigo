package Negocio.TurnoJPA;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Negocio.EMFSingleton.EMFSingleton;

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
		query.setParameter("nombre", turno.getHorario());

        try {
        	turnoExistente = query.getSingleResult();     
        } catch (Exception e) {
        	return -1;
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
		return null;
	}

	public Integer modificarTurno(TTurno turno) {
		return null;
	}

	public TTurno mostrarTurno(Integer idTurno) {
		return null;
	}

	public Set<TTurno> listarTurnos() {
		return null;
	}

	public Float obtenerNominaDelTurno(Integer idTurno) {
		return null;
	}
	
	//------ Métodos auxiliares ------//
	
	private boolean validarHorario(String horario) {
		return horario != null && !horario.isEmpty();
	}
}