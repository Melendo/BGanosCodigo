package Presentacion.Controller.Command.CommandMarcaJPA;

import java.util.List;

import Negocio.FactoriaNegocio.FactoriaSA;
import Negocio.MarcaJPA.TMarca;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;


public class CommandListarMarcasPorProveedor implements Command {

	public Context execute(Object datos) {
		List<TMarca> res = FactoriaSA.getInstance().getMarcaJPA().listarMarcasPorProveedor((Integer)datos);
		
		if(res.size() == 1) {
			TMarca unaMarca = res.iterator().next();
			if(unaMarca.getId() <= 0)
				return new Context(Evento.LISTAR_MARCAS_POR_PROVEEDOR_KO, unaMarca);
			else
				return new Context(Evento.LISTAR_MARCAS_POR_PROVEEDOR_OK, unaMarca);

		} else {
			return new Context(Evento.LISTAR_MARCAS_POR_PROVEEDOR_OK, res);

		}		
	}
}