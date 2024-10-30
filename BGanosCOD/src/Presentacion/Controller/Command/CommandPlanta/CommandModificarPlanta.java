package Presentacion.Controller.Command.CommandPlanta;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Planta.*;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

public class CommandModificarPlanta implements Command {

	public Context execute(Object datos) {
		int res = -1;

			res = FactoriaNegocio.getInstance().getPlantaSA().modificarPlanta((TPlanta)datos);

		if(res > -1) {
			return new Context(Evento.MODIFICAR_PLANTA_OK, res);
		}else {
			return new Context(Evento.MODIFICAR_PLANTA_KO, res);
		}
	}
}