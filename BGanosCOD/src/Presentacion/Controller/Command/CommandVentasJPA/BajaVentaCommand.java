/**
 * 
 */
package Presentacion.Controller.Command.CommandVentasJPA;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;


public class BajaVentaCommand implements Command {
	
	public Context execute(Object datos) {
		int resultado = FactoriaNegocio.getInstance().getVentaSA().bajaVenta((Integer)datos);
		if(resultado > -1){
			return new Context(Evento.BAJA_VENTA_OK,resultado);
		}else {
			return new Context(Evento.BAJA_VENTA_KO,resultado);
		}
	}
}