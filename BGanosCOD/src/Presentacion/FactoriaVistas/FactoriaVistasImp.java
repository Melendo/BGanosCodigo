package Presentacion.FactoriaVistas;

import Presentacion.MainView;

import Presentacion.Controller.IGUI;
import Presentacion.Controller.Command.Context;
import Presentacion.Entrada.GUIAltaEntrada;
import Presentacion.Entrada.GUIBajaEntrada;
import Presentacion.Entrada.GUIEntrada;
import Presentacion.Entrada.GUILIstarEntradasPorInvernadero;
import Presentacion.Entrada.GUIListarEntradas;
import Presentacion.Entrada.GUIModificarEntrada;
import Presentacion.Entrada.GUIMostrarEntrada;
import Presentacion.Fabricante.GUIAltaFabricante;
import Presentacion.Fabricante.GUIBajaFabricante;
import Presentacion.Fabricante.GUIFabricante;
import Presentacion.Fabricante.GUIListarFabricantes;
import Presentacion.Fabricante.GUIListarFabricantesExtranjeros;
import Presentacion.Fabricante.GUIListarFabricantesLocales;
import Presentacion.Fabricante.GUIListarInformacionFabricantePorSistemasDeRiegoDeUnInvernadero;
import Presentacion.Fabricante.GUIModificarFabricante;
import Presentacion.Fabricante.GUIMostrarFabricantePorID;
import Presentacion.Factura.GUIAbrirFactura;
import Presentacion.Factura.GUICerrarFactura;
import Presentacion.Factura.GUIDevolverFactura;
import Presentacion.Factura.GUIFactura;
import Presentacion.Factura.GUIListarFactura;
import Presentacion.Factura.GUIModificarFactura;
import Presentacion.Factura.GUIMostrarFacturaID;
import Presentacion.Invernadero.GUIAltaInvernadero;
import Presentacion.Invernadero.GUIBajaInvernadero;
import Presentacion.Invernadero.GUIDesvincularSRInvernadero;
import Presentacion.Invernadero.GUIInvernadero;
import Presentacion.Invernadero.GUIListarInvernadero;
import Presentacion.Invernadero.GUIListarInvernaderoPorSR;
import Presentacion.Invernadero.GUIModificarInvernadero;
import Presentacion.Invernadero.GUIMostrarInvernaderoPorID;
import Presentacion.Invernadero.GUITresFechasMasVendidas;
import Presentacion.Invernadero.GUIVincularSRInvernadero;
import Presentacion.Planta.GUIAltaPlanta;
import Presentacion.Planta.GUIBajaPlanta;
import Presentacion.Planta.GUIListarPlantas;
import Presentacion.Planta.GUIListarPlantasPorTipo;
import Presentacion.Planta.GUIListarPlantasPorInvernadero;
import Presentacion.Planta.GUIModificarPlanta;
import Presentacion.Planta.GUIMostarPlantasPorID;
import Presentacion.Planta.GUIPlanta;
import Presentacion.SistemaDeRiego.GUIAltaSistemaDeRiego;
import Presentacion.SistemaDeRiego.GUIBajaSistemaDeRiego;
import Presentacion.SistemaDeRiego.GUIListarSistemaDeRiegoDelInvernadero;
import Presentacion.SistemaDeRiego.GUIListarSistemaDeRiegoPorFabricante;
import Presentacion.SistemaDeRiego.GUIListarSistemasDeRiego;
import Presentacion.SistemaDeRiego.GUIModificarSistemaDeRiego;
import Presentacion.SistemaDeRiego.GUIMostrarSistemaDeRiegoPorID;
import Presentacion.SistemaDeRiego.GUISistemaDeRiego;
import Presentacion.TurnoJPA.GUIAltaTurno;
import Presentacion.TurnoJPA.GUIBajaTurno;
import Presentacion.TurnoJPA.GUIListarTurnos;
import Presentacion.TurnoJPA.GUIModificarTurno;
import Presentacion.TurnoJPA.GUIMostrarTurno;
import Presentacion.TurnoJPA.GUIObtenerNominaDeTurno;
import Presentacion.TurnoJPA.GUITurno;
import Presentacion.VentaJPA.GUIAbrirVenta;
import Presentacion.VentaJPA.GUIBajaVenta;
import Presentacion.VentaJPA.GUIVentaJPA;

import java.util.Set;

import Negocio.Entrada.TEntrada;
import Negocio.Fabricante.TFabricante;
import Negocio.Factura.TCarrito;
import Negocio.Factura.TFactura;
import Negocio.Invernadero.TInvernadero;
import Negocio.Planta.TPlanta;
import Negocio.SistemaDeRiego.TSistemaDeRiego;
import Negocio.TurnoJPA.TTurno;

public class FactoriaVistasImp extends FactoriaVistas {
    IGUI vistaActual = null;

    @SuppressWarnings("unchecked")
    public IGUI generarVista(Context contexto) {
        switch(contexto.getEvento())
        {
            //              Vista Principal
            case Evento.VISTA_PRINCIPAL:
                vistaActual = new MainView();
                return vistaActual;

                
                
            //              Vistas Entrada
            case Evento.ENTRADA_VISTA:
                vistaActual = new GUIEntrada();
                return vistaActual;
            case Evento.ALTA_ENTRADA_VISTA:
                vistaActual = new GUIAltaEntrada();
                return vistaActual;
            case Evento.BAJA_ENTRADA_VISTA:
                vistaActual = new GUIBajaEntrada();
                return vistaActual;
            case Evento.MODIFICAR_ENTRADA_VISTA:
                vistaActual = new GUIModificarEntrada();
                return vistaActual;
            case Evento.MOSTRAR_ENTRADA_POR_ID_VISTA:
                vistaActual = new GUIMostrarEntrada();
                return vistaActual;
            case Evento.LISTAR_ENTRADAS_VISTA:
                vistaActual = new GUIListarEntradas((Set<TEntrada>) contexto.getDatos());
                return vistaActual;
            case Evento.LISTAR_ENTRADAS_POR_INVERNADERO_VISTA:
            	vistaActual = new GUILIstarEntradasPorInvernadero();
            	return vistaActual;
                
                

                //              Vistas Fabricante
                case Evento.FABRICANTE_VISTA:
                    vistaActual = new GUIFabricante();
                    return vistaActual;
                case Evento.ALTA_FABRICANTE_VISTA:
                    vistaActual = new GUIAltaFabricante();
                    return vistaActual;
                case Evento.BAJA_FABRICANTE_VISTA:
                    vistaActual = new GUIBajaFabricante();
                    return vistaActual;
                case Evento.MODIFICAR_FABRICANTE_VISTA:
                    vistaActual = new GUIModificarFabricante(); 
                    return vistaActual;
                case Evento.MOSTRAR_FABRICANTE_POR_ID_VISTA:
                    vistaActual = new GUIMostrarFabricantePorID(); 
                    return vistaActual;
                case Evento.LISTAR_FABRICANTES_LOCALES_VISTA:
                    vistaActual = new GUIListarFabricantesLocales((Set<TFabricante>) contexto.getDatos()); 
                    return vistaActual;                           
                case Evento.LISTAR_FABRICANTES_EXTRANJEROS_VISTA:
                    vistaActual = new GUIListarFabricantesExtranjeros((Set<TFabricante>) contexto.getDatos()); 
                    return vistaActual;
                case Evento.LISTAR_INFORMACION_FABRICANTES_DE_SISTEMA_DE_RIEGO_DE_UN_INVERNADERO_VISTA:
                    vistaActual = new GUIListarInformacionFabricantePorSistemasDeRiegoDeUnInvernadero(); 
                    return vistaActual;
                case Evento.LISTAR_FABRICANTES_VISTA:
                    vistaActual = new GUIListarFabricantes((Set<TFabricante>) contexto.getDatos()); 
                    return vistaActual;
                
                
                

            //              Vistas Factura
            case Evento.FACTURA_VISTA:
                vistaActual = new GUIFactura();
                return vistaActual;
            case Evento.ABRIR_FACTURA_VISTA:
                vistaActual = new GUIAbrirFactura();
                return vistaActual;
            case Evento.CERRAR_FACTURA_VISTA:
                vistaActual = new GUICerrarFactura((TCarrito) contexto.getDatos());
                return vistaActual;
            case Evento.DEVOLVER_FACTURA_VISTA:
                vistaActual = new GUIDevolverFactura();
                return vistaActual;
            case Evento.MODIFICAR_FACTURA_VISTA:
                vistaActual = new GUIModificarFactura();
                return vistaActual;
            case Evento.MOSTRAR_FACTURA_POR_ID_VISTA:
                vistaActual = new GUIMostrarFacturaID();
                return vistaActual;
            case Evento.LISTAR_FACTURAS_VISTA:
                vistaActual = new GUIListarFactura((Set<TFactura>) contexto.getDatos());
                return vistaActual;

                
                
                
//                  Vistas Invernadero
                case Evento.INVERNADERO_VISTA:
                    vistaActual = new GUIInvernadero(); 
                    return vistaActual;
                case Evento.ALTA_INVERNADERO_VISTA:
                    vistaActual = new GUIAltaInvernadero(); 
                    return vistaActual;
                case Evento.BAJA_INVERNADERO_VISTA:
                    vistaActual = new GUIBajaInvernadero(); 
                    return vistaActual;
                case Evento.MODIFICAR_INVERNADERO_VISTA:
                    vistaActual = new GUIModificarInvernadero(); 
                    return vistaActual;
                case Evento.MOSTRAR_INVERNADERO_POR_ID_VISTA:
                    vistaActual = new GUIMostrarInvernaderoPorID(); 
                    return vistaActual;
                case Evento.LISTAR_INVERNADEROS_VISTA:
                    vistaActual = new GUIListarInvernadero((Set<TInvernadero>) contexto.getDatos());
                    return vistaActual;
                case Evento.LISTAR_INVERNADEROS_POR_SISTEMA_RIEGO_VISTA:
                    vistaActual = new GUIListarInvernaderoPorSR(); 
                    return vistaActual;
                case Evento.VINCULAR_SISTEMA_RIEGO_INVERNADERO_VISTA:
                    vistaActual = new GUIVincularSRInvernadero(); 
                    return vistaActual;
                case Evento.DESVINCULAR_SISTEMA_RIEGO_INVERNADERO_VISTA:
                    vistaActual = new GUIDesvincularSRInvernadero(); 
                    return vistaActual;
                case Evento.CALCULAR_LAS_3_FECHAS_MAS_VENDIDAS_DE_UN_INVERNADERO_VISTA:
                	vistaActual = new GUITresFechasMasVendidas();
                	return vistaActual;
//                
                
                

                    //              Vistas Planta
                case Evento.PLANTA_VISTA:
                    vistaActual = new GUIPlanta();
                    return vistaActual;
                case Evento.ALTA_PLANTA_VISTA:
                    vistaActual = new GUIAltaPlanta(); 
                    return vistaActual;
                case Evento.BAJA_PLANTA_VISTA:
                    vistaActual = new GUIBajaPlanta(); 
                    return vistaActual;
                case Evento.MODIFICAR_PLANTA_VISTA:
                    vistaActual = new GUIModificarPlanta((TPlanta)contexto.getDatos()); 
                    return vistaActual;
                case Evento.MOSTRAR_PLANTA_POR_ID_VISTA:
                    vistaActual = new GUIMostarPlantasPorID();
                    return vistaActual;
                case Evento.LISTAR_PLANTAS_VISTA:
                    vistaActual = new GUIListarPlantas((Set<TPlanta>) contexto.getDatos()); 
                    return vistaActual;
                case Evento.LISTAR_PLANTAS_POR_TIPO_VISTA:
                    vistaActual = new GUIListarPlantasPorTipo((Set<TPlanta>) contexto.getDatos()); 
                    return vistaActual;
                case Evento.LISTAR_PLANTAS_INVERNADERO_VISTA:
                    vistaActual = new GUIListarPlantasPorInvernadero((Set<TPlanta>) contexto.getDatos());
                    return vistaActual;
                
                
                

//                  Vistas Sistema de Riego
                case Evento.SISTEMA_RIEGO_VISTA:
                    vistaActual = new GUISistemaDeRiego(); 
                    return vistaActual;
                case Evento.ALTA_SISTEMA_RIEGO_VISTA:
                    vistaActual = new GUIAltaSistemaDeRiego(); 
                    return vistaActual;
                case Evento.BAJA_SISTEMA_RIEGO_VISTA:
                    vistaActual = new GUIBajaSistemaDeRiego(); 
                    return vistaActual;
                case Evento.MODIFICAR_SISTEMA_RIEGO_VISTA:
                    vistaActual = new GUIModificarSistemaDeRiego(); 
                    return vistaActual;
                case Evento.MOSTRAR_SISTEMA_RIEGO_POR_ID_VISTA:
                    vistaActual = new GUIMostrarSistemaDeRiegoPorID(); 
                    return vistaActual;
                case Evento.LISTAR_SISTEMAS_RIEGO_VISTA:
                    vistaActual = new GUIListarSistemasDeRiego((Set<TSistemaDeRiego>) contexto.getDatos()); 
                    return vistaActual;
                case Evento.LISTAR_SISTEMAS_RIEGO_INVERNADERO_VISTA:
                    vistaActual = new GUIListarSistemaDeRiegoDelInvernadero(); 
                    return vistaActual;
                case Evento.LISTAR_SISTEMAS_RIEGO_POR_FABRICANTE_VISTA:
                    vistaActual = new GUIListarSistemaDeRiegoPorFabricante(); 
                    return vistaActual;
                    
                    
                    
                    
//                  Vistas Turno JPA
                case Evento.TURNO_VISTA:
                    vistaActual = new GUITurno(); 
                    return vistaActual;
                case Evento.ALTA_TURNO_VISTA:
                    vistaActual = new GUIAltaTurno(); 
                    return vistaActual;
                case Evento.BAJA_TURNO_VISTA:
                    vistaActual = new GUIBajaTurno(); 
                    return vistaActual;
                case Evento.MODIFICAR_TURNO_VISTA:
                    vistaActual = new GUIModificarTurno(); 
                    return vistaActual;
                case Evento.MOSTRAR_TURNO_VISTA:
                    vistaActual = new GUIMostrarTurno(); 
                    return vistaActual;
                case Evento.LISTAR_TURNO_VISTA:
                    //vistaActual = new GUIListarTurnos((Set<TTurno>) contexto.getDatos()); 
                    return vistaActual;
                case Evento.OBTENER_NOMINA_DE_TURNO_VISTA:
                    vistaActual = new GUIObtenerNominaDeTurno(); 
                    return vistaActual;
//					Vistas VENTA JPA
                case Evento.VENTA_VISTA:
                	vistaActual=new GUIVentaJPA();
                	return vistaActual;
                case Evento.ABRIR_VENTA_VISTA:
                	vistaActual=new GUIAbrirVenta();
                	return vistaActual;
                case Evento.BAJA_VENTA_VISTA:
                	vistaActual=new GUIBajaVenta();
                	return vistaActual;

            default:
                return null;
        }
    }
}
