/**
 * 
 */
package Presentacion.Controller.Command.CommandVentasJPA;

import Negocio.FactoriaNegocio.FactoriaNegocio;

import Negocio.VentaJPA.TVenta;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;


public class AltaVentaCommand implements Command {
	
	public Context execute(Object datos) {
		int resultado = FactoriaNegocio.getInstance().getVentaSA().altaVenta((TVenta)datos);
		if(resultado > -1){
			return new Context(Evento.ALTA_SISTEMA_DE_RIEGO_OK,resultado);
		}else {
			return new Context(Evento.ALTA_SISTEMA_DE_RIEGO_KO,resultado);
		}
	}
}