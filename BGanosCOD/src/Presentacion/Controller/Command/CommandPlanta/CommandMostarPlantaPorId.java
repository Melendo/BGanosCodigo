package Presentacion.Controller.Command.CommandPlanta;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Planta.*;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

public class CommandMostarPlantaPorId implements Command {

	public Context execute(Object datos) {
		TPlanta res = new TPlanta();
		try {
			res = FactoriaNegocio.getInstance().getPlantaSA().mostrarPlantaPorId((int)datos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(res.get_id() > -1) {
			return new Context(Evento.MOSTRAR_PLANTA_POR_ID_OK, res);
		}else {
			return new Context(Evento.MOSTRAR_PLANTA_POR_ID_KO, res);
		}
	}
}