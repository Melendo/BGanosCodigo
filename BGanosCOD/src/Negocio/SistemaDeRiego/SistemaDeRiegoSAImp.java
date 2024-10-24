/**
 * 
 */
package Negocio.SistemaDeRiego;

import java.util.Set;

import Integracion.Fabricante.FabricanteDAO;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.SistemaDeRiego.SistemaDeRiegoDAO;
import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;
import Negocio.Fabricante.TFabricante;


public class SistemaDeRiegoSAImp implements SistemaDeRiegoSA {

	public Integer altaSisRiego(TSistemaDeRiego sisRiego) {
		//Comprobamos si en el alta han puesto campos nulos 
				if(sisRiego.getNombre().isEmpty()|| sisRiego.getFrecuencia() == -1|| sisRiego.getCantidad_agua() == -1 || sisRiego.getPotenciaRiego() == -1|| sisRiego.getIdFabricante() == 0 ){
					return -3; //Error casos vacios en alta
				}
				int exito = -1;
				
				try {
					TransaccionManager transaccion = TransaccionManager.getInstance();
					Transaccion t = transaccion.newTransaccion();
					t.start();
					FactoriaIntegracion f = FactoriaIntegracion.getInstance();
					FabricanteDAO daoFabricante = f.getFabricanteDAO();
					TFabricante fabricante = daoFabricante.mostrarFabricantePorId(sisRiego.getIdFabricante()); 
					
					if(fabricante != null){
						
						if(fabricante.getActivo()){
							SistemaDeRiegoDAO daoSistRiego = f.getSistemaDeRiegoDAO();
							TSistemaDeRiego tSistRiego = daoSistRiego.leerPorNombreUnico(sisRiego.getNombre());
							if(tSistRiego == null){ //No existe sist riego con mismo nombre
								exito = daoSistRiego.altaSistemaDeRiego(sisRiego);
								t.commit();
							}else if(tSistRiego.getActivo() == false){ //Existe pero no activo
								sisRiego.setId(tSistRiego.getId());
								exito = daoSistRiego.modificarSistemaDeRiego(sisRiego); //Reactivar y actualizar
								t.commit();
							}else{ // Existe y activo
								exito = -2; 
							}
						}else{
							exito = -511; //Fabricante no activo
						}
						
					}else{
						exito = -404; //Fabricante no existe
						t.rollback();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return exito;
	}

	
	public Integer bajaSisRiego(Integer id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	
	public Integer modificarSisRiego(TSistemaDeRiego sisRiego) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	
	public TSistemaDeRiego mostrarSisRiego(Integer id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	
	public Set<TSistemaDeRiego> listarSisRiego() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	
	public Set<TSistemaDeRiego> listarSisRiegoPorFabricante(Integer idFabricante) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}


	public Set<TSistemaDeRiego> listarSisRiegoDelInvernadero(Integer idInvernadero) {
		// TODO Auto-generated method stub
		return null;
	}
}