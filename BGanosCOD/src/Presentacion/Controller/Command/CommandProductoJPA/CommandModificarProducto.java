/**
 * 
 */
package Presentacion.Controller.Command.CommandProductoJPA;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Planta.TPlanta;
import Negocio.ProductoJPA.TProducto;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

public class CommandModificarProducto implements Command {

	public Context execute(Object datos) {

		TProducto tmp = (TProducto) datos;
		
		if(tmp.getIdMarca() == null){
			TProducto p = FactoriaNegocio.getInstance().getProductoJPA().mostrarProducto(tmp.getId());
			
			if(p != null){return new Context(Evento.MODIFICAR_PLANTA_VISTA, p);}
			else{
				return new Context(Evento.MODIFICAR_PLANTA_KO, -3);
			}
		}
		else{
		
			int	res = FactoriaNegocio.getInstance().getPlantaSA().modificarPlanta((TPlanta)datos);
	
			if(res > -1) {
				return new Context(Evento.MODIFICAR_PLANTA_OK, res);
			}
			else {
				return new Context(Evento.MODIFICAR_PLANTA_KO, res);
			}
		
		}
	
	}
}