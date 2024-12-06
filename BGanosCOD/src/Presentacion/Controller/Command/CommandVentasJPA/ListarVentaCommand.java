package Presentacion.Controller.Command.CommandVentasJPA;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.VentaJPA.TVenta;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

public class ListarVentaCommand implements Command {

	public Context execute(Object datos) {
		List<TVenta> res = FactoriaNegocio.getInstance().getVentaSA().listarVentas();
		return new Context(Evento.LISTAR_VENTAS_VISTA, res);
	}
}