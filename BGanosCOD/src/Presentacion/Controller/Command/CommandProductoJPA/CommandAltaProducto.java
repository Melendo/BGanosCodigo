/**
 * 
 */
package Presentacion.Controller.Command.CommandProductoJPA;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.ProductoJPA.TProducto;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;


public class CommandAltaProducto implements Command {

	public Context execute(Object datos) {
		
		
		
		int resp = FactoriaNegocio.getInstance().getProductoJPA().altaProducto((TProducto) datos);
	
		
		
		return null;
		
	}
}