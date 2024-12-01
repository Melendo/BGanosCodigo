package Presentacion.Controller.Command;

import Presentacion.Controller.Command.CommandEntrada.CommandAltaEntrada;
import Presentacion.Controller.Command.CommandEntrada.CommandBajaEntrada;
import Presentacion.Controller.Command.CommandEntrada.CommandListarEntrada;
import Presentacion.Controller.Command.CommandEntrada.CommandListarEntradasPorInvernadero;
import Presentacion.Controller.Command.CommandEntrada.CommandModificarEntrada;
import Presentacion.Controller.Command.CommandEntrada.CommandMostrarEntrada;
import Presentacion.Controller.Command.CommandFabricante.CommandAltaFabricante;
import Presentacion.Controller.Command.CommandFabricante.CommandGUIBajaFabricante;
import Presentacion.Controller.Command.CommandFabricante.CommandListarFabricantes;
import Presentacion.Controller.Command.CommandFabricante.CommandListarFabricantesExtranjeros;
import Presentacion.Controller.Command.CommandFabricante.CommandListarFabricantesLocales;
import Presentacion.Controller.Command.CommandFabricante.CommandListarInformacionFabricanteDeSistemaDeRiegoDeUnInvernadero;
import Presentacion.Controller.Command.CommandFabricante.CommandModificarFabricante;
import Presentacion.Controller.Command.CommandFabricante.CommandMostrarFabricantePorId;
import Presentacion.Controller.Command.CommandFactura.AbrirFacturaCommand;
import Presentacion.Controller.Command.CommandFactura.CerrarFacturaCommand;
import Presentacion.Controller.Command.CommandFactura.DevolverFacturaCommand;
import Presentacion.Controller.Command.CommandFactura.ListarFacturasCommand;
import Presentacion.Controller.Command.CommandFactura.ModificarFacturaCommand;
import Presentacion.Controller.Command.CommandFactura.MostrarFacturaPorIDCommand;
import Presentacion.Controller.Command.CommandInvernadero.CommandAltaInvernadero;
import Presentacion.Controller.Command.CommandInvernadero.CommandBajaInvernadero;
import Presentacion.Controller.Command.CommandInvernadero.CommandCalcularLas3FechasMasVendidasDeUnInvernadero;
import Presentacion.Controller.Command.CommandInvernadero.CommandDesvincularSRInvernadero;
import Presentacion.Controller.Command.CommandInvernadero.CommandListarInvernadero;
import Presentacion.Controller.Command.CommandInvernadero.CommandListarInvernaderoPorSR;
import Presentacion.Controller.Command.CommandInvernadero.CommandModificarInvernadero;
import Presentacion.Controller.Command.CommandInvernadero.CommandMostrarInvernaderoPorID;
import Presentacion.Controller.Command.CommandInvernadero.CommandVincularSRInvernadero;
import Presentacion.Controller.Command.CommandPlanta.CommandAltaPlanta;
import Presentacion.Controller.Command.CommandPlanta.CommandBajaPlanta;
import Presentacion.Controller.Command.CommandPlanta.CommandListarPlanta;
import Presentacion.Controller.Command.CommandPlanta.CommandListarPlantasPorInvernadero;
import Presentacion.Controller.Command.CommandPlanta.CommandListarPlantasPorTipo;
import Presentacion.Controller.Command.CommandPlanta.CommandModificarPlanta;
import Presentacion.Controller.Command.CommandPlanta.CommandMostarPlantaPorId;
import Presentacion.Controller.Command.CommandSistemaDeRiego.CommandAltaSistemaDeRiego;
import Presentacion.Controller.Command.CommandSistemaDeRiego.CommandBajaSistemaDeRiego;
import Presentacion.Controller.Command.CommandSistemaDeRiego.CommandModificarSistemaDeRiego;
import Presentacion.Controller.Command.CommandSistemaDeRiego.CommandMostrarSistemaDeRiegoDelInvernadero;
import Presentacion.Controller.Command.CommandSistemaDeRiego.CommandMostrarSistemaDeRiegoPorFabricante;
import Presentacion.Controller.Command.CommandSistemaDeRiego.CommandMostrarSistemasDeRiego;
import Presentacion.Controller.Command.CommandSistemaDeRiego.CommandMostrarSistemasDeRiegoPorId;
import Presentacion.Controller.Command.CommandTurnoJPA.CommandAltaTurno;
import Presentacion.Controller.Command.CommandTurnoJPA.CommandBajaTurno;
import Presentacion.Controller.Command.CommandTurnoJPA.CommandListarTurnos;
import Presentacion.Controller.Command.CommandTurnoJPA.CommandModificarTurno;
import Presentacion.Controller.Command.CommandTurnoJPA.CommandMostrarTurno;
import Presentacion.Controller.Command.CommandTurnoJPA.CommandObtenerNominaDelTurno;
import Presentacion.Controller.Command.CommandVentasJPA.CerrarVentaCommand;
import Presentacion.FactoriaVistas.Evento;

public class CommandFactoryImp extends CommandFactory {

	public Command getCommand(Integer event) {
		switch (event) {
		// Eventos de Factura
		case Evento.ABRIR_FACTURA:
			return new AbrirFacturaCommand();
		case Evento.CERRAR_FACTURA:
			return new CerrarFacturaCommand();
		case Evento.MODIFICAR_FACTURA:
			return new ModificarFacturaCommand();
		case Evento.MOSTRAR_FACTURA_POR_ID:
			return new MostrarFacturaPorIDCommand();
		case Evento.LISTAR_FACTURAS:
			return new ListarFacturasCommand();
		case Evento.DEVOLVER_FACTURA:
			return new DevolverFacturaCommand();

		// Eventos de Entrada
		case Evento.ALTA_ENTRADA:
			return new CommandAltaEntrada();
		case Evento.BAJA_ENTRADA:
			return new CommandBajaEntrada();
		case Evento.MODIFICAR_ENTRADA:
			return new CommandModificarEntrada();
		case Evento.MOSTRAR_ENTRADA_POR_ID:
			return new CommandMostrarEntrada();
		case Evento.LISTAR_ENTRADAS:
			return new CommandListarEntrada();
		case Evento.LISTAR_ENTRADAS_POR_INVERNADERO:
			return new CommandListarEntradasPorInvernadero();

		// Eventos de Fabricante
		case Evento.ALTA_FABRICANTE:
			return new CommandAltaFabricante();
		case Evento.BAJA_FABRICANTE:
			return new CommandGUIBajaFabricante();
		case Evento.MODIFICAR_FABRICANTE:
			return new CommandModificarFabricante();
		case Evento.LISTAR_FABRICANTES:
			return new CommandListarFabricantes();
		case Evento.MOSTRAR_FABRICANTE_POR_ID:
			return new CommandMostrarFabricantePorId();
		//		case Evento.LISTAR_FABRICANTES_POR_NOMBRE:
		//		    return new CommandListarFabricantePorNombre();
		case Evento.LISTAR_FABRICANTES_LOCALES:
			return new CommandListarFabricantesLocales();
		case Evento.LISTAR_FABRICANTES_EXTRANJEROS:
			return new CommandListarFabricantesExtranjeros();
		case Evento.LISTAR_INFORMACION_FABRICANTES_DE_SISTEMA_DE_RIEGO_DE_UN_INVERNADERO:
			return new CommandListarInformacionFabricanteDeSistemaDeRiegoDeUnInvernadero();

		// Eventos de Invernadero
		case Evento.ALTA_INVERNADERO:
			return new CommandAltaInvernadero();
		case Evento.BAJA_INVERNADERO:
			return new CommandBajaInvernadero();
		case Evento.MODIFICAR_INVERNADERO:
			return new CommandModificarInvernadero();
		case Evento.LISTAR_INVERNADEROS:
			return new CommandListarInvernadero();
		case Evento.MOSTRAR_INVERNADERO_POR_ID:
			return new CommandMostrarInvernaderoPorID();
		case Evento.LISTAR_INVERNADEROS_POR_SISTEMA_RIEGO:
			return new CommandListarInvernaderoPorSR();
		case Evento.VINCULAR_SISTEMA_RIEGO_A_INVERNADERO:
			return new CommandVincularSRInvernadero();
		case Evento.DESVINCULAR_SISTEMA_RIEGO_DE_INVERNADERO:
			return new CommandDesvincularSRInvernadero();
		case Evento.CALCULAR_LAS_3_FECHAS_MAS_VENDIDAS_DE_UN_INVERNADERO:
			return new CommandCalcularLas3FechasMasVendidasDeUnInvernadero();

		// Eventos de Planta
		case Evento.ALTA_PLANTA:
			return new CommandAltaPlanta();
		case Evento.BAJA_PLANTA:
			return new CommandBajaPlanta();
		case Evento.MODIFICAR_PLANTA:
			return new CommandModificarPlanta();
		case Evento.LISTAR_PLANTAS:
			return new CommandListarPlanta();
		case Evento.MOSTRAR_PLANTA_POR_ID:
			return new CommandMostarPlantaPorId();
		case Evento.LISTAR_PLANTAS_POR_TIPO:
			return new CommandListarPlantasPorTipo();
		case Evento.LISTAR_PLANTAS_DE_INVERNADERO:
			return new CommandListarPlantasPorInvernadero();

		// Eventos de Sistema de Riego
		case Evento.ALTA_SISTEMA_RIEGO:
			return new CommandAltaSistemaDeRiego();
		case Evento.BAJA_SISTEMA_RIEGO:
			return new CommandBajaSistemaDeRiego();
		case Evento.MODIFICAR_SISTEMA_RIEGO:
			return new CommandModificarSistemaDeRiego();
		case Evento.MOSTRAR_SISTEMA_RIEGO_POR_ID:
			return new CommandMostrarSistemasDeRiegoPorId();
		case Evento.LISTAR_SISTEMAS_RIEGO_DE_INVERNADERO:
			return new CommandMostrarSistemaDeRiegoDelInvernadero();
		case Evento.LISTAR_SISTEMAS_RIEGO_POR_FABRICANTE:
			return new CommandMostrarSistemaDeRiegoPorFabricante();
		case Evento.LISTAR_SISTEMAS_RIEGO:
			return new CommandMostrarSistemasDeRiego();
		
		// Eventos de Turno
		case Evento.ALTA_TURNO:
			return new CommandAltaTurno();
		case Evento.BAJA_TURNO:
			return new CommandBajaTurno();
		case Evento.MODIFICAR_TURNO:
			return new CommandModificarTurno();
		case Evento.MOSTRAR_TURNO:
			return new CommandMostrarTurno();
		case Evento.LISTAR_TURNO:
			return new CommandListarTurnos();
		case Evento.OBTENER_NOMINA_DE_TURNO:
			return new CommandObtenerNominaDelTurno();
			
		// Eventos Venta
		case Evento.CERRAR_VENTA:
			return new CerrarVentaCommand();
		}
		return null;
	}
}