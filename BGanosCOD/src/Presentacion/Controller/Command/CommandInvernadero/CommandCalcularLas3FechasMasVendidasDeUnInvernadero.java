package Presentacion.Controller.Command.CommandInvernadero;

import java.sql.Date;
import java.util.Set;

import Integracion.FactoriaQuery.FactoriaQuery;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.Invernadero.TInvernadero;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

public class CommandCalcularLas3FechasMasVendidasDeUnInvernadero implements Command {

	public Context execute(Object datos) {
		Set<Date> resultado = (Set<Date>) FactoriaNegocio.getInstance().getInvernaderoSA()
				.calcularLasTresFechasMasVendidasDeUnInvernadero((Integer) datos);

		if (resultado.size() == 1) {
			if (resultado.iterator().next() == null) {
				return new Context(Evento.LISTAR_INVERNADEROS_POR_SISTEMA_RIEGO_KO, resultado);
			}
		}
		return new Context(Evento.LISTAR_INVERNADEROS_POR_SISTEMA_RIEGO_OK, resultado);
	}
}