
package Presentacion.Controller.Command.CommandEmpleadoDeCajaJPA;

import java.util.Set;

import Negocio.EmpleadoDeCajaJPA.TEmpleadoDeCaja;
import Negocio.FactoriaNegocio.FactoriaNegocio;
import Negocio.FactoriaNegocio.FactoriaSA;
import Presentacion.Controller.Command.Command;
import Presentacion.Controller.Command.Context;
import Presentacion.FactoriaVistas.Evento;

public class ListarEmpleadoDeCajaCommand implements Command {
	
	public Context execute(Object datos) {
<<<<<<< HEAD
		
		Set<TEmpleadoDeCaja> resultado = FactoriaSA.getInstance().getEmpleadoDeCajaJPA().ListarEmpleadosDeCaja();
=======
		Set<TEmpleadoDeCaja> resultado = FactoriaNegocio.getInstance().getEmpleadoDeCajaJPA().ListarEmpleadosDeCaja();
>>>>>>> branch 'EmpleadoCajaJPA' of https://github.com/anavarro-fdi-ucm-es/ms2425bganoscod.git
		
		return new Context(Evento.LISTAR_EMPLEADOS_DE_CAJA_VISTA, resultado); 
	}
}