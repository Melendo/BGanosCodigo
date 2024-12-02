package Presentacion.Controller.Command.CommandVentasJPA;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.VentaJPA.TVenta;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

public class VentasPorEmpleadoDeCajaCommand implements Command {

	public Context execute(Object datos) {
		List<TVenta> res = FactoriaNegocio.getInstance().getVentaSA().ventasPorEmpleadoDeCaja((Integer) datos);
		if (res == null) {
			return new Context(Evento.VENTAS_POR_EMPLEADO_DE_CAJA_KO, res);
		} else
			return new Context(Evento.VENTAS_POR_EMPLEADO_DE_CAJA_KO, res);
	}
}