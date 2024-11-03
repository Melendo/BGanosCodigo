package Presentacion.Controller.Command.CommandFabricante;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

public class CommandMostrarFabricantePorId implements Command {
	
		public Context execute(Object datos) {
		datos = FactoriaNegocio.getInstance().getFabricanteSA().mostrarFabricantePorId((Integer)datos);
		return new Context(Evento.MOSTRAR_FABRICANTE_POR_ID_OK, datos);
	}
}