package Presentacion.Controller.Command.CommandMarcaJPA;

import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;
import Negocio.MarcaJPA.TMarca;

public class CommandAltaMarca implements Command {

	public Context execute(Object datos) {
		// TODO: preguntar por qué en este alta está FactoriaSA y en alta entrada
		// FactoriaNegocio
//		int res = FactoriaNegocio.getInstance().getMarcaJPA().altaMarca((TMarca)datos);
//		
//		if(res > -1) {
//			return new Context(Evento.ALTA_MARCA_OK, res);
//		} else {
//			return new Context(Evento.ALTA_MARCA_KO, res);
//		}

		TMarca marca = (TMarca) datos;
		int res = FactoriaNegocio.getInstance().getMarcaJPA().altaMarca(marca);
		if (res >= 0) {

			return new Context(Evento.ALTA_MARCA_OK, res);
		} else {
			return new Context(Evento.ALTA_MARCA_KO, res);
		}
	}
}