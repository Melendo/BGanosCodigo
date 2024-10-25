/**
 * 
 */
package Negocio.SistemaDeRiego;

import java.util.HashSet;
import java.util.Set;

import Integracion.Fabricante.FabricanteDAO;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Invernadero.InvernaderoDAO;
import Integracion.SistemaDeRiego.SistemaDeRiegoDAO;
import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;
import Negocio.Fabricante.TFabricante;
import Negocio.Invernadero.TInvernadero;


public class SistemaDeRiegoSAImp implements SistemaDeRiegoSA {

	public Integer altaSisRiego(TSistemaDeRiego sisRiego) {
		//Comprobamos si en el alta han puesto campos nulos 
				if(sisRiego.getNombre().isEmpty()|| sisRiego.getFrecuencia() == -1|| sisRiego.getCantidad_agua() == -1 
						|| sisRiego.getPotenciaRiego() == -1|| sisRiego.getIdFabricante() == null || sisRiego.getIdInvernadero() == null){
					return -3; //Error casos vacios en alta
				}
				int res = -1;
				
				try {
					TransaccionManager tManager = TransaccionManager.getInstance();
					Transaccion trans = tManager.newTransaccion();
					trans.start();
					FactoriaIntegracion factoria = FactoriaIntegracion.getInstance();
					FabricanteDAO daoFabricante = factoria.getFabricanteDAO();
					InvernaderoDAO daoInvernadero = factoria.getInvernaderoDAO();
					TFabricante fabricante = daoFabricante.mostrarFabricantePorId(sisRiego.getIdFabricante()); 
					TInvernadero invernadero = daoInvernadero.mostrarInvernaderoPorID(sisRiego.getIdInvernadero()); 
					
					if(invernadero != null){
						if(fabricante != null){
							
							if(fabricante.getActivo()){
								SistemaDeRiegoDAO daoSistRiego = factoria.getSistemaDeRiegoDAO();
								TSistemaDeRiego tSistRiego = daoSistRiego.leerPorNombreUnico(sisRiego.getNombre());
								if(tSistRiego == null){ //No existe sist riego con mismo nombre
									res = daoSistRiego.altaSistemaDeRiego(sisRiego);
									trans.commit();
								}else if(tSistRiego.getActivo() == false){ //Existe pero no activo
									sisRiego.setId(tSistRiego.getId());
									sisRiego.setActivo(true);
									res = daoSistRiego.modificarSistemaDeRiego(sisRiego); //Reactivar y actualizar
									trans.commit();
								}else{ // Existe y activo
									res = -2; 
								}
							}else{
								res = -511; //Fabricante no activo
							}
							
						}else{
							res = -404; //Fabricante no existe
							trans.rollback();
						}
					}
					else{
						res = -403; //Invernadero no existe
						trans.rollback();
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return res;
	}

	
	public Integer bajaSisRiego(Integer id) {
		int res = -1;

	    try {
	        // Inicializa la transacción
	        TransaccionManager transaction = TransaccionManager.getInstance();
	        Transaccion trans = transaction.newTransaccion();
	        trans.start();

	        FactoriaIntegracion factoria = FactoriaIntegracion.getInstance();
	        SistemaDeRiegoDAO daoSistRiego = factoria.getSistemaDeRiegoDAO();
	        
	        TSistemaDeRiego tSistRiego = daoSistRiego.mostrarSistemaDeRiegoPorID(id);
	        
	        //  sistema de riego existe
	        if (tSistRiego != null) {
	            //  sistema de riego  activo
	            if (tSistRiego.getActivo()) {
	                res = daoSistRiego.bajaSistemaDeRiego(id);
	                trans.commit();
	            } else {
	                res = -2; // Error: No activo
	                trans.rollback();
	            }
	        } else {
	            res = -404; // Error: No existe
	            trans.rollback();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return res;
	}

	
	public Integer modificarSisRiego(TSistemaDeRiego sisRiego) {
		// Comprobamos campos nulos
	    if (sisRiego.getNombre().isEmpty() || sisRiego.getFrecuencia() == -1 || 
	        sisRiego.getCantidad_agua() == -1 || sisRiego.getPotenciaRiego() == -1 || 
	        		sisRiego.getIdInvernadero() == null ||sisRiego.getIdFabricante() == null || sisRiego.getId() <= 0) { 
	    	
	        return -3; // Error: casos vacíos 
	    }
	    
	    int res = -1;

	    try {
	        TransaccionManager tManager = TransaccionManager.getInstance();
	        Transaccion trans = tManager.newTransaccion();
	        trans.start();

	        FactoriaIntegracion factoria = FactoriaIntegracion.getInstance();
	        FabricanteDAO daoFabricante = factoria.getFabricanteDAO();
	        InvernaderoDAO daoInvernadero = factoria.getInvernaderoDAO();
	        TFabricante fabricante = daoFabricante.mostrarFabricantePorId(sisRiego.getIdFabricante());
	        TInvernadero invernadero = daoInvernadero.mostrarInvernaderoPorID(sisRiego.getIdInvernadero()); 
	        
	        if(invernadero != null){
	        	if (fabricante != null) {
		            if (fabricante.getActivo()) {
		                SistemaDeRiegoDAO daoSistRiego = factoria.getSistemaDeRiegoDAO();
		                TSistemaDeRiego tSistRiegoExistente = daoSistRiego.leerPorNombreUnico(sisRiego.getNombre());

		                if (tSistRiegoExistente == null || 
		                    (tSistRiegoExistente.getId().equals(sisRiego.getId()) && !tSistRiegoExistente.getActivo())) {
		                    // Si no existe un sistema de riego con el mismo nombre o si el nombre pertenece a uno inactivo
		                    res = daoSistRiego.modificarSistemaDeRiego(sisRiego);
		                    trans.commit(); 
		                } else {
		                    res = -2; // Error: Nombre  existe y activo
		                    trans.rollback(); 
		                }
		            } else {
		                res = -511; // Error: fabricante no activo
		                trans.rollback(); 
		            }
		        } else {
		            res = -404; // Error: fabricante no existe
		            trans.rollback();
		        }
	        }else {
	            res = -403; // Error: Invernadero no existe
	            trans.rollback();
	        }
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return res; 
	}

	
	public TSistemaDeRiego mostrarSisRiego(Integer id) {
		
		  TSistemaDeRiego sisRiegoMostrar = null;
		
	    try {
	        TransaccionManager transaction = TransaccionManager.getInstance();
	        Transaccion trans = transaction.newTransaccion();
	        trans.start();
	 
	        FactoriaIntegracion factoria = FactoriaIntegracion.getInstance();
	        SistemaDeRiegoDAO daoSistRiego = factoria.getSistemaDeRiegoDAO();
	        
	         sisRiegoMostrar = daoSistRiego.mostrarSistemaDeRiegoPorID(id);
	        
	        if (sisRiegoMostrar != null) {
	            trans.commit(); 
	        } else {
	            sisRiegoMostrar = null; //No encontrado
	            trans.rollback(); 
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	    
	    return sisRiegoMostrar;
	}

	
	public Set<TSistemaDeRiego> listarSisRiego() {
		
		Set<TSistemaDeRiego> listaSisRiego = new HashSet<>();

	    try {
	   
	        TransaccionManager transaction = TransaccionManager.getInstance();
	        Transaccion trans = transaction.newTransaccion();
	        trans.start();

	        FactoriaIntegracion factoria = FactoriaIntegracion.getInstance();
	        SistemaDeRiegoDAO daoSistRiego = factoria.getSistemaDeRiegoDAO();
	        
	        listaSisRiego = daoSistRiego.listarSistemaDeRiego();
	        trans.commit(); 
	        	        
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	    
	    return listaSisRiego;
	}

	
	public Set<TSistemaDeRiego> listarSisRiegoPorFabricante(Integer idFabricante) {
		
		Set<TSistemaDeRiego> listaSisRiego = new HashSet<>();

	    try {
	        TransaccionManager transaction = TransaccionManager.getInstance();
	        Transaccion trans = transaction.newTransaccion();
	        trans.start();
	        
	        FactoriaIntegracion factoria = FactoriaIntegracion.getInstance();
	        SistemaDeRiegoDAO daoSistRiego = factoria.getSistemaDeRiegoDAO();
	        
	        listaSisRiego = daoSistRiego.listarSistemaDeRiegoPorFabricante(idFabricante);	        
	        trans.commit(); 
	        
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	    
	    return listaSisRiego;
	}


	public Set<TSistemaDeRiego> listarSisRiegoDelInvernadero(Integer idInvernadero) {
		
		 Set<TSistemaDeRiego> listaSisRiego = new HashSet<>();

		    try {
		      
		        TransaccionManager transaction = TransaccionManager.getInstance();
		        Transaccion trans = transaction.newTransaccion();
		        trans.start();

		        FactoriaIntegracion factoria = FactoriaIntegracion.getInstance();
		        SistemaDeRiegoDAO daoSistRiego = factoria.getSistemaDeRiegoDAO();
		        	
		        listaSisRiego = daoSistRiego.listarSistemaDeRiegoInvernadero(idInvernadero);		        
		        trans.commit(); 
		        
		    } catch (Exception e) {
		        e.printStackTrace(); 
		    }
		   
		    return listaSisRiego;
	}
}