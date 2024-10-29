package Presentacion.Controller.Command.CommandPlanta;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Planta.*;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

public class CommandAltaPlanta implements Command {

	@Override
	public Context execute(Object datos) {
		int res;
		try {
			res = FactoriaNegocio.getInstance().getPlantaSA().altaPlanta((TPlanta)datos);
			if(res > -1) {
				return new Context(Evento.ALTA_PLANTA_OK, res);
			}else {
				return new Context(Evento.ALTA_PLANTA_KO, res);
			}
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new Context(Evento.ALTA_PLANTA_KO, -1);
		}

	}
}