package Presentacion.FactoriaVistas;

import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;

public class FactoriaVistasImp extends FactoriaVistas {
	IGUI vistaActual = null;

	@SuppressWarnings("unchecked")
	public IGUI generarVista(Context contexto) {
		switch(contexto.getEvento())
		{
		
		// 				Vista Principal
			case Evento.VISTA_PRINCIPAL:
				vistaActual = new VistaPrincipal();
				return vistaActual;
		
		// 				Vistas Entrada
				
			case Evento.ENTRADA_VISTA:
				vistaActual = new VistaEntrada();
				return vistaActual;
			case Evento.ALTA_ENTRADA_VISTA:
				vistaActual = new VistaAltaEntrada();
				return vistaActual;
			case Evento.BAJA_ENTRADA_VISTA:
				vistaActual = new VistaBajaEntrada();
				return vistaActual;
			case Evento.MODIFICAR_ENTRADA_VISTA:
				vistaActual = new VistaModificarEntrada();
				return vistaActual;
			case Evento.MOSTRAR_ENTRADA_VISTA:
				vistaActual = new VistaMostrarEntrada();
				return vistaActual;
			case Evento.LISTAR_ENTRADAS_VISTA:
                vistaActual = new VistaListarEntradas((Set<TEntrada>) contexto.getDatos());
				return vistaActual;
			case Evento.LISTAR_ENTRADAS_POR_TIPO_VISTA:
				vistaActual = new VistaListarEntradasPorTipo();
				return vistaActual;


		// 					Vistas Fabricante
				
		case Evento.FABRICANTE_VISTA:
			vistaActual = new VistaFabricante();
			return vistaActual;
		case Evento.ALTA_FABRICANTE_VISTA:
			vistaActual = new VistaAltaFabricante();
			return vistaActual;
		case Evento.BAJA_FABRICANTE_VISTA:
			vistaActual = new VistaBajaFabricante();
			return vistaActual;
		case Evento.MODIFICAR_FABRICANTE_VISTA:
			vistaActual = new VistaModificarFabricante();
			return vistaActual;
		case Evento.MOSTRAR_FABRICANTE_VISTA:
			vistaActual = new VistaMostrarFabricante();
			return vistaActual;
		case Evento.LISTAR_FABRICANTES_VISTA:
			vistaActual = new VistaListarFabricantes((Set<TFabricante>) contexto.getDatos());
			return vistaActual;
			
	
			
		// 				Vistas Factura
			
		case Evento.FACTURA_VISTA:
			vistaActual = new VistaFactura();
			return vistaActual;
		case Evento.ABRIR_FACTURA_VISTA:
			vistaActual = new VistaAbrirFactura();
			return vistaActual;
		case Evento.DEVOLVER_FACTURA_VISTA:
			vistaActual = new VistaDevolverFactura();
			return vistaActual;
		case Evento.MODIFICAR_FACTURA_VISTA:
			vistaActual = new VistaModificarFactura();
			return vistaActual;
		case Evento.MOSTRAR_FACTURA_VISTA:
			vistaActual = new VistaMostrarFactura();
			return vistaActual;
		case Evento.LISTAR_FACTURAS_VISTA:
			vistaActual = new VistaListarFacturas((Set<TFactura>) contexto.getDatos());
			return vistaActual;
			
			
		//				Vistas Invernadero
			
		case Evento.INVERNADERO_VISTA:
			vistaActual = new VistaInvernadero();
			return vistaActual;
		case Evento.ALTA_INVERNADERO_VISTA:
			vistaActual = new VistaAltaInvernadero();
			return vistaActual;
		case Evento.BAJA_INVERNADERO_VISTA:
			vistaActual = new VistaBajaInvernadero();
			return vistaActual;
		case Evento.MODIFICAR_INVERNADERO_VISTA:
			vistaActual = new VistaModificarInvernadero();
			return vistaActual;
		case Evento.MOSTRAR_INVERNADERO_VISTA:
			vistaActual = new VistaMostrarInvernadero();
			return vistaActual;
		case Evento.LISTAR_INVERNADEROS_VISTA:
			vistaActual = new VistaListarInvernaderos((Set<TInvernadero>) contexto.getDatos());
			return vistaActual;
			

		// 				Vistas Planta
			
		case Evento.PLANTA_VISTA:
			vistaActual = new VistaPlanta();
			return vistaActual;
		case Evento.ALTA_PLANTA_VISTA:
			vistaActual = new VistaAltaPlanta();
			return vistaActual;
		case Evento.BAJA_PLANTA_VISTA:
			vistaActual = new VistaBajaPlanta();
			return vistaActual;
		case Evento.MODIFICAR_PLANTA_VISTA:
			vistaActual = new VistaModificarPlanta();
			return vistaActual;
		case Evento.MOSTRAR_PLANTA_VISTA:
			vistaActual = new VistaMostrarPlanta();
			return vistaActual;
		case Evento.LISTAR_PLANTAS_VISTA:
			vistaActual = new VistaListarPlantas((Set<TPlanta>) contexto.getDatos());
			return vistaActual;
			

		// 				Vistas Sistema de Riego
			
		case Evento.SISTEMA_RIEGO_VISTA:
			vistaActual = new VistaSistemaRiego();
			return vistaActual;
		case Evento.ALTA_SISTEMA_RIEGO_VISTA:
			vistaActual = new VistaAltaSistemaRiego();
			return vistaActual;
		case Evento.BAJA_SISTEMA_RIEGO_VISTA:
			vistaActual = new VistaBajaSistemaRiego();
			return vistaActual;
		case Evento.MODIFICAR_SISTEMA_RIEGO_VISTA:
			vistaActual = new VistaModificarSistemaRiego();
			return vistaActual;
		case Evento.MOSTRAR_SISTEMA_RIEGO_VISTA:
			vistaActual = new VistaMostrarSistemaRiego();
			return vistaActual;
		case Evento.LISTAR_SISTEMAS_RIEGO_VISTA:
			vistaActual = new VistaListarSistemasRiego((Set<TSistemaRiego>) contexto.getDatos());
			return vistaActual;

		default:
			return null;
		}
	}
}
