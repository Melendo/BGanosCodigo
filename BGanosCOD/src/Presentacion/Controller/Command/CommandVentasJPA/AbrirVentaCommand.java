/**
 * 
 */
package Presentacion.Controller.Command.CommandVentasJPA;

import Negocio.FactoriaNegocio.FactoriaNegocio;

import Negocio.VentaJPA.TVenta;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;


public class AbrirVentaCommand implements Command {
	
	public Context execute(Object datos) {//TODO: command abrir venta
//		int resultado = FactoriaNegocio.getInstance().getVentaSA();
//		if(resultado > -1){
//			return new Context(Evento.ABRIR_VENTA_OK,resultado);
//		}else {
//			return new Context(Evento.ABRIR_VENTA_KO,resultado);
//		}
		return null;
	}
}