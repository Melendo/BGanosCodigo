package Presentacion.Controller.Command.CommandMarcaJPA;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.MarcaJPA.TMarca;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;


public class CommandListarMarcas implements Command {

	public Context execute(Object datos) {
		List<TMarca> res = FactoriaSA.getInstance().getMarcaJPA().listarMarcas();
		return new Context (Evento.LISTAR_MARCAS_VISTA, res);

	}
}