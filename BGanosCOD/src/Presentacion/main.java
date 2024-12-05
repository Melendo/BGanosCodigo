package Presentacion;

import javax.persistence.EntityManager;

import Negocio.EMFSingleton.EMFSingleton;
import Presentacion.Controller.ApplicationController;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

public class main {
	public static void main(String[] args) {
		EntityManager em = EMFSingleton.getInstance().getEMF().createEntityManager();
		Context c = new Context(Evento.VISTA_PRINCIPAL, null);
		ApplicationController.getInstance().manageRequest(c);
		em.close();
	}
}
