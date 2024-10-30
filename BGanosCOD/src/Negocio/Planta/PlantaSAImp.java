package Negocio.Planta;

import java.util.HashSet;

import java.util.Set;

import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Invernadero.InvernaderoDAO;
import Integracion.Planta.PlantaDAO;
import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;
import Negocio.Invernadero.TInvernadero;

public class PlantaSAImp implements PlantaSA {

	public Integer altaPlanta(TPlanta planta) {
		int exito = -1;
		Transaccion t = null;
		try {
			TransaccionManager transaction = TransaccionManager.getInstance();
			t = transaction.newTransaccion();
			t.start();
			FactoriaIntegracion f = FactoriaIntegracion.getInstance();

			
			InvernaderoDAO daoinv = f.getInvernaderoDAO();
			PlantaDAO dao = f.getPlantaDAO();
			
			
			TInvernadero inv = daoinv.mostrarInvernaderoPorID(planta.get_id_invernadero());
			
			if(inv == null || !inv.isActivo()){
				
				exito = -1;
				t.rollback();
			} 
			else{
			
			
			exito = dao.altaPlanta(planta);
			
			if(exito>-1) {t.commit();}
			else {t.rollback();}
			
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	public Integer bajaPlanta(Integer id) {
		int exito = -1;
		Transaccion t = null;
		try {
			TransaccionManager transaction = TransaccionManager.getInstance();
			t = transaction.newTransaccion();
			t.start();
			FactoriaIntegracion f = FactoriaIntegracion.getInstance();

			PlantaDAO dao = f.getPlantaDAO();
			

			TPlanta p = dao.mostrarPorId(id);
			
			if(p == null || !p.getActivo()) {
				t.rollback();
				exito = -2;
			}
			else {
				exito = dao.bajaPlanta(id);
				
				
				if(exito>-1) {t.commit();}
				else {t.rollback();}
			}
			
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exito;
	}

	public Integer modificarPlanta(TPlanta planta)  {
		

		return -1;
	}

	public Set<TPlanta> listarPlanta()  {
		
		
		
		return null;
		
	}

	public TPlanta mostrarPlantaPorId(Integer id) {
		Transaccion t = null;
		TPlanta p = null;
		try {
			TransaccionManager transaction = TransaccionManager.getInstance();
			t = transaction.newTransaccion();
			t.start();
			FactoriaIntegracion f = FactoriaIntegracion.getInstance();

			PlantaDAO dao = f.getPlantaDAO();
			

			p = dao.mostrarPorId(id);
			
			if(p == null ) {
				t.rollback();
				
			}
			else {
			
				
				t.commit();
				
			}
			
		

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return p;
	}

	public Set<TPlanta> listarPlantasPorTipo(String tipo)  {
		

return null;
	}

	public Set<TPlanta> listarPlantasPorInvernadero(Integer id_invernadero) {
		

		return null;
	}
}