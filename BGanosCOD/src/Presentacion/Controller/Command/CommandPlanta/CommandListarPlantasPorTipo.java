package Presentacion.Controller.Command.CommandPlanta;

import java.util.Set;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Planta.TPlanta;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

public class CommandListarPlantasPorTipo implements Command {

	public Context execute(Object datos) {
		Set<TPlanta> res = FactoriaNegocio.getInstance().getPlantaSA().listarPlantasPorTipo((String)datos);
		
		
		if(res.size() == 1) {
			TPlanta planta = res.iterator().next();
			if(planta.get_id() <= 0) {
				return new Context(Evento.LISTAR_PLANTAS_POR_TIPO_KO,planta);
			}else {
				return new Context(Evento.LISTAR_PLANTAS_POR_TIPO_OK, res);
			}
		}else {
			return new Context(Evento.LISTAR_PLANTAS_POR_TIPO_OK, res);
		}
	}
}