/**
 * 
 */
package Presentacion.Controller.Command.CommandProductoJPA;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

public class CommandBajaProducto implements Command {

	public Context execute(Object datos) {
		
		int resp = FactoriaNegocio.getInstance().getProductoJPA().bajaProducto((int) datos);
		int tmp;
		if(resp > 0)
			tmp = Evento.BAJA_PLANTA_OK;
		else
			tmp = Evento.BAJA_PLANTA_KO;
		
		return new Context(tmp, resp);
	
	}
}