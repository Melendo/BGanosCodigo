package Presentacion.Controller.Command.CommandPlanta;

import java.util.HashSet;
import java.util.Set;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Planta.*;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

public class CommandListarPlanta implements Command {

	public Context execute(Object datos) {
		Set<TPlanta> res = new HashSet<>();
		try {
			 res = FactoriaNegocio.getInstance().getPlantaSA().listarPlanta();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return new Context(Evento.LISTAR_PLANTAS_VISTA, res);
	}
}