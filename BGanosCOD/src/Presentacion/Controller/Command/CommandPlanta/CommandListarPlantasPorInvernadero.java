package Presentacion.Controller.Command.CommandPlanta;

import java.util.HashSet;
import java.util.Set;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Planta.*;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

public class CommandListarPlantasPorInvernadero implements Command {

	public Context execute(Object datos) {
		Set<TPlanta> res = new HashSet<> ();
		try {
			res = FactoriaNegocio.getInstance().getPlantaSA().listarPlantasPorInvernadero((Integer)datos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(res.size() == 1) {
			TPlanta planta = res.iterator().next();
			
			if(planta.get_id() <= 0) {
				return new Context(Evento.LISTAR_PLANTAS_DE_INVERNADERO_KO, planta);
			}else {
				return new Context(Evento.LISTAR_PLANTAS_DE_INVERNADERO_OK, res);
			}
			
		}else {
			return new Context(Evento.LISTAR_PLANTAS_DE_INVERNADERO_OK, res);
		}
	}
}