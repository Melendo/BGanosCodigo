package Negocio.Factura;

import java.util.HashSet;
import java.util.Set;

import Integracion.Entrada.EntradaDAO;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Factura.FacturaDAO;
import Integracion.Factura.LineaFacturaDAO;
import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;
import Negocio.Entrada.TEntrada;

public class FacturaSAImp implements FacturaSA {

	public Integer cerrarFactura(TCarrito carrito) {
		TransaccionManager tm = TransaccionManager.getInstance();
		try 
		{
			Transaccion t = tm.newTransaccion();
			t.start();
			FactoriaIntegracion fDAO = FactoriaIntegracion.getInstance();
			if(!carrito.getLineasFactura().isEmpty())
			{
				EntradaDAO daoEntrada = fDAO.getEntradaDAO();
				float precio_total = 0;
				for(TLineaFactura lineaFact : carrito.getLineasFactura())
				{
					TEntrada entrada = daoEntrada.mostrarEntrada(lineaFact.getidEntrada());
					if(entrada != null)
					{
						if(entrada.getActivo())
						{
							if(lineaFact.getCantidad() <= entrada.getStock())
							{
								entrada.setStock(entrada.getStock() - lineaFact.getCantidad());
								daoEntrada.modificarEntrada(entrada);
								//Calculamos el precio de la linea Factura y actualizamos en la linea Factura
								float precio_lineaF = lineaFact.getCantidad() * entrada.getPrecio();
								lineaFact.setPrecio(precio_lineaF);
								precio_total = precio_total +precio_lineaF ;
							}
							else
							{
								t.rollback();
								return -1;
							}
								
						}
						else
						{
							t.rollback();
							return -1;
						}
					}
					else
					{
						t.rollback();
						return -1;
					}
				}
				FacturaDAO daoFactura = fDAO.getFacturaDAO();
				TFactura factura = carrito.getFactura();
				factura.setPrecioTotal(precio_total);
				int id = daoFactura.cerrarFactura(factura);
				if(id > 0)
				{
					LineaFacturaDAO daoLF = fDAO.getDAOLineaFactura();
					for(TLineaFactura lf : carrito.getLineasFactura())
					{
						lf.setidFactura(id);
						int r = daoLF.crearLineaFactura(lf);
						if(r < 0)
						{
							t.rollback();
							return -1;
						}
					}
				}
				else
				{
					t.rollback();
					return -1;
				}
				t.commit();
				return id;
			}
			else
			{
				t.rollback();
				return -1;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return -1; 
		}
	}

	public TFacturaConEntradas mostrarFacturaPorID(Integer id) {
		TFacturaConEntradas facturaEntradas = new TFacturaConEntradas();		
		TFactura factura = new TFactura();
		try{
			TransaccionManager transaction = TransaccionManager.getInstance();
			Transaccion t = transaction.newTransaccion();
			t.start();
			FactoriaIntegracion fDAO = FactoriaIntegracion.getInstance();
			FacturaDAO daoFactura = fDAO.getFacturaDAO();
			TFactura facturaBD = daoFactura.mostrarFactura(id);
			if(facturaBD != null){
				facturaEntradas.settFactura(facturaBD);
				
				Set<TLineaFactura> lineasfacturaBD = fDAO.getDAOLineaFactura().mostrarLineaFacturaPorFactura(id);				
				
				for (TLineaFactura tLineaFactura : lineasfacturaBD) {
					facturaEntradas.incluirLineaEntrada(tLineaFactura);
					
				}
				t.commit();
			}else{
				factura.setid(-1);
				facturaEntradas.settFactura(factura);
				t.rollback();
			}			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return facturaEntradas;
	}

	public Set<TFactura> listarFacturas() {
		Set<TFactura> facturas = new HashSet<>();
        try {
			TransaccionManager transaction = TransaccionManager.getInstance();
			Transaccion t = transaction.newTransaccion();
			t.start();
			FactoriaIntegracion f = FactoriaIntegracion.getInstance();
			FacturaDAO daoFactura = f.getFacturaDAO();
			Set<TFactura> facturasBuscar = daoFactura.listarFactura();
			for(TFactura factura : facturasBuscar){
				facturas.add(factura);
			}
			facturasBuscar = null; //Liberamos memoria
			t.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return facturas;
	}

	public Integer modificarFactura(TFactura tfactura) {
		int r = -1;
		try 
		{
			FactoriaIntegracion fIntegracion = FactoriaIntegracion.getInstance();
			FacturaDAO daoFactura = fIntegracion.getFacturaDAO();
			TransaccionManager tm = TransaccionManager.getInstance();
			Transaccion t = tm.newTransaccion();
			t.start();
			TFactura facturaBD = daoFactura.mostrarFactura(tfactura.getid());
			if(facturaBD != null){
				if(facturaBD.getActivo()){
					tfactura.setPrecioTotal(facturaBD.getPrecioTotal());
					tfactura.setActivo(facturaBD.getActivo());
					r = daoFactura.modificarFactura(tfactura);
					if(r < 0){
						t.rollback();
						return -1;
					}
					t.commit();
				}else{
					t.rollback();
					return -1;
				}
			}else{
				t.rollback();
				return -1;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return r;
		}
		return r;
	}

	public Integer devolverFactura(TLineaFactura tlineaFactura) {
		TransaccionManager tm = TransaccionManager.getInstance();
		FactoriaIntegracion fDAO = FactoriaIntegracion.getInstance();
		int r = -1;
		try 
		{
			Transaccion t = tm.newTransaccion();
			t.start();
			FacturaDAO daoFactura = fDAO.getFacturaDAO();
			TFactura factura = daoFactura.mostrarFactura(tlineaFactura.getidFactura());
			if(factura != null)
			{
				if(factura.getActivo())
				{	
					EntradaDAO daoEntrada = fDAO.getEntradaDAO();
					TEntrada entrada = daoEntrada.mostrarEntrada(tlineaFactura.getidEntrada());
					if(entrada != null)
					{
						LineaFacturaDAO daoLF = fDAO.getDAOLineaFactura();
						TLineaFactura lf = daoLF.mostrarLineaFactura(tlineaFactura.getidFactura(), tlineaFactura.getidEntrada());
						if(lf != null)
						{
							if(lf.getCantidad() < tlineaFactura.getCantidad()){
								t.rollback();
								return -75; 
							}
							if(!entrada.getActivo())
								entrada.setActivo(true);
							entrada.setStock(entrada.getStock() + tlineaFactura.getCantidad());
							r = daoEntrada.modificarEntrada(entrada);
							if(r < 0)
							{
								t.rollback();
								return -68;
							}
							factura.setPrecioTotal(factura.getPrecioTotal() - (tlineaFactura.getCantidad() * entrada.getPrecio()));
							if(factura.getPrecioTotal() == 0)
								factura.setActivo(false);
							r = daoFactura.modificarFactura(factura);
							if(r < 0)
							{
								t.rollback();
								return -67;
							}
							lf.setCantidad(lf.getCantidad() - tlineaFactura.getCantidad());
							lf.setPrecio(lf.getPrecio() - ((tlineaFactura.getCantidad() * entrada.getPrecio())));
							r = daoLF.modificarLineaFactura(lf);
							if(r < 0)
							{
								t.rollback();
								return -1;
							}
							if(lf.getCantidad() == 0) {
								TLineaFactura baja = daoLF.bajaLineaFactura(lf.getidFactura(), lf.getidEntrada());
								r = baja == null ? 1 : -1;
							}
							if(r < 0)
							{
								t.rollback();
								return -1;
							}
							t.commit();
							return r;
						}
						else
						{
							t.rollback();
							return -1;
						}
					}
					else
					{
						t.rollback();
						return -1;
					}
				}
				else
				{
					t.rollback();
					return -1;
				}
			}
			else
			{
				t.rollback();
				return -1;
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return r;
		}
	}
}