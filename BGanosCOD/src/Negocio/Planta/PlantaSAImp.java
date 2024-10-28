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

	public Integer altaPlanta(TPlanta planta) throws Exception {
		
		TransaccionManager manager = TransaccionManager.getInstance();
		Transaccion trans = manager.newTransaccion();
		FactoriaIntegracion integracion = FactoriaIntegracion.getInstance();
		PlantaDAO daoP = integracion.getPlantaDAO();
		
		trans.start();

		int resp = daoP.altaPlanta(planta);
			
		if(resp > 0){
			trans.commit();
		}
		else{trans.rollback();}

		
		return resp;
	}

	public Integer bajaPlanta(Integer id) throws Exception {
		TransaccionManager manager = TransaccionManager.getInstance();
		Transaccion trans = manager.newTransaccion();
		FactoriaIntegracion integracion = FactoriaIntegracion.getInstance();
		PlantaDAO daoP = integracion.getPlantaDAO();
		
		trans.start();
		
		TPlanta tmp = daoP.mostrarPorId(id);
		
		if(tmp != null ){
			
			if(tmp.getActivo()){
				int resp = daoP.bajaPlanta(id);
				
				if(resp > 0){
					trans.commit();
					return resp;
				}
				else{ 
					trans.rollback();
				}
				
	
			}
			else{
				trans.rollback();
			}
			
			
		}
		else{
			trans.rollback();
		}
		
		
		
		
		return -1;
	}

	public Integer modificarPlanta(TPlanta planta) throws Exception {

		TransaccionManager manager = TransaccionManager.getInstance();
		Transaccion trans = manager.newTransaccion();
		FactoriaIntegracion integracion = FactoriaIntegracion.getInstance();
		PlantaDAO daoP = integracion.getPlantaDAO();
		
		trans.start();
		
		
		TPlanta tmp = daoP.mostrarPorId(planta.get_id());
		
		if(tmp == null){
			trans.rollback();
			
		}
		else{
			
		int resp = daoP.modificarPlanta(planta);
		trans.commit();
		return resp;
		}
		return -1;
	}

	public Set<TPlanta> listarPlanta() throws Exception {
		TransaccionManager manager = TransaccionManager.getInstance();
		Transaccion trans = manager.newTransaccion();
		FactoriaIntegracion integracion = FactoriaIntegracion.getInstance();
		PlantaDAO daoP = integracion.getPlantaDAO();
		
		trans.start();
		
		Set<TPlanta> tmp = daoP.listarPlantas();
		
		if(tmp == null){
			trans.rollback();
			return new HashSet<>();
		}
		else{
			trans.commit();
			return tmp;
		}
		
	}

	public TPlanta mostrarPlantaPorId(Integer id) throws Exception {
		TransaccionManager manager = TransaccionManager.getInstance();
		Transaccion trans = manager.newTransaccion();
		FactoriaIntegracion integracion = FactoriaIntegracion.getInstance();
		PlantaDAO daoP = integracion.getPlantaDAO();
		
		trans.start();
		
		
		TPlanta tmp = daoP.mostrarPorId(id);
		
		if(tmp == null){
			trans.rollback();
			return new TPlanta();
		}
		else{
			trans.commit();
			return tmp;
		}
	}

	public Set<TPlanta> listarPlantasPorTipo(String tipo) throws Exception {
		
		TransaccionManager manager = TransaccionManager.getInstance();
		Transaccion trans = manager.newTransaccion();
		FactoriaIntegracion integracion = FactoriaIntegracion.getInstance();
		PlantaDAO daoP = integracion.getPlantaDAO();
		
		trans.start();
		int t = 1;
		
		if(tipo == "Frutal"){t = 0;}
		
		Set<TPlanta> tmp = daoP.mostrarPorTipo(t);
		
		if(tmp == null){
			trans.rollback();
			return new HashSet<>() ;
		}
		else {
			trans.commit();
			return tmp ;
		}

	}

	public Set<TPlanta> listarPlantasPorInvernadero(Integer id_invernadero) throws Exception {
		
		TransaccionManager manager = TransaccionManager.getInstance();
		Transaccion trans = manager.newTransaccion();
		FactoriaIntegracion integracion = FactoriaIntegracion.getInstance();
		PlantaDAO daoP = integracion.getPlantaDAO();
		InvernaderoDAO daoI = integracion.getInvernaderoDAO();
		
		trans.start();
		
		TInvernadero inv = daoI.mostrarInvernaderoPorID(id_invernadero);
		
		if(inv == null || !inv.isActivo()){
			
			trans.rollback();
			return new HashSet<>() ;
			
		}
		else{
			Set<TPlanta> tmp = daoP.MostrarPorInvernadero(id_invernadero);
			trans.commit();
			return tmp;
			
		}
		
	}
}