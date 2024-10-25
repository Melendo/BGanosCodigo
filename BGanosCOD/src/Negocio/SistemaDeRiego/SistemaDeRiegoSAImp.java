/**
 * 
 */
package Negocio.SistemaDeRiego;

import java.util.HashSet;
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
								sisRiego.setActivo(true);
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
		int exito = -1;

	    try {
	        // Inicializa la transacción
	        TransaccionManager transaction = TransaccionManager.getInstance();
	        Transaccion t = transaction.newTransaccion();
	        t.start();

	        FactoriaIntegracion f = FactoriaIntegracion.getInstance();
	        SistemaDeRiegoDAO daoSistRiego = f.getSistemaDeRiegoDAO();
	        
	        TSistemaDeRiego tSistRiego = daoSistRiego.mostrarSistemaDeRiegoPorID(id);

	        //  sistema de riego existe
	        if (tSistRiego != null) {
	            //  sistema de riego  activo
	            if (tSistRiego.getActivo()) {
	                exito = daoSistRiego.bajaSistemaDeRiego(id);
	                t.commit();
	            } else {
	                exito = -2; // Error: No activo
	                t.rollback();
	            }
	        } else {
	            exito = -404; // Error: No existe
	            t.rollback();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return exito;
	}

	
	public Integer modificarSisRiego(TSistemaDeRiego sisRiego) {
		// Comprobamos campos nulos
	    if (sisRiego.getNombre().isEmpty() || sisRiego.getFrecuencia() == -1 || 
	        sisRiego.getCantidad_agua() == -1 || sisRiego.getPotenciaRiego() == -1 || 
	        sisRiego.getIdFabricante() == 0 || sisRiego.getId() <= 0) { 
	    	
	        return -3; // Error: casos vacíos 
	    }
	    
	    int exito = -1;

	    try {
	        TransaccionManager transaccion = TransaccionManager.getInstance();
	        Transaccion t = transaccion.newTransaccion();
	        t.start();

	        FactoriaIntegracion f = FactoriaIntegracion.getInstance();
	        FabricanteDAO daoFabricante = f.getFabricanteDAO();
	        TFabricante fabricante = daoFabricante.mostrarFabricantePorId(sisRiego.getIdFabricante());

	        if (fabricante != null) {
	            if (fabricante.getActivo()) {
	                SistemaDeRiegoDAO daoSistRiego = f.getSistemaDeRiegoDAO();
	                TSistemaDeRiego tSistRiegoExistente = daoSistRiego.leerPorNombreUnico(sisRiego.getNombre());

	                if (tSistRiegoExistente == null || 
	                    (tSistRiegoExistente.getId().equals(sisRiego.getId()) && !tSistRiegoExistente.getActivo())) {
	                    // Si no existe un sistema de riego con el mismo nombre o si el nombre pertenece a uno inactivo
	                    exito = daoSistRiego.modificarSistemaDeRiego(sisRiego);
	                    t.commit(); 
	                } else {
	                    exito = -2; // Error: Nombre  existe y activo
	                    t.rollback(); 
	                }
	            } else {
	                exito = -511; // Error: fabricante no activo
	                t.rollback(); 
	            }
	        } else {
	            exito = -404; // Error: fabricante no existe
	            t.rollback();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return exito; 
	}

	
	public TSistemaDeRiego mostrarSisRiego(Integer id) {
		
		  TSistemaDeRiego sisRiegoMostrar = null;
		
	    try {
	        TransaccionManager transaction = TransaccionManager.getInstance();
	        Transaccion t = transaction.newTransaccion();
	        t.start();
	 
	        FactoriaIntegracion f = FactoriaIntegracion.getInstance();
	        SistemaDeRiegoDAO daoSistRiego = f.getSistemaDeRiegoDAO();
	        
	         sisRiegoMostrar = daoSistRiego.mostrarSistemaDeRiegoPorID(id);
	        
	        if (sisRiegoMostrar != null) {
	            t.commit(); 
	        } else {
	            sisRiegoMostrar = null; //No encontrado
	            t.rollback(); 
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
	        Transaccion t = transaction.newTransaccion();
	        t.start();

	        FactoriaIntegracion f = FactoriaIntegracion.getInstance();
	        SistemaDeRiegoDAO daoSistRiego = f.getSistemaDeRiegoDAO();
	        
	        listaSisRiego = daoSistRiego.listarSistemaDeRiego();
	        t.commit(); 
	        	        
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	    
	    return listaSisRiego;
	}

	
	public Set<TSistemaDeRiego> listarSisRiegoPorFabricante(Integer idFabricante) {
		
		Set<TSistemaDeRiego> listaSisRiego = new HashSet<>();

	    try {
	        TransaccionManager transaction = TransaccionManager.getInstance();
	        Transaccion t = transaction.newTransaccion();
	        t.start();
	        
	        FactoriaIntegracion f = FactoriaIntegracion.getInstance();
	        SistemaDeRiegoDAO daoSistRiego = f.getSistemaDeRiegoDAO();
	        
	        listaSisRiego = daoSistRiego.listarSistemaDeRiegoPorFabricante(idFabricante);	        
	        t.commit(); 
	        
	    } catch (Exception e) {
	        e.printStackTrace(); 
	    }
	    
	    return listaSisRiego;
	}


	public Set<TSistemaDeRiego> listarSisRiegoDelInvernadero(Integer idInvernadero) {
		
		 Set<TSistemaDeRiego> listaSisRiego = new HashSet<>();

		    try {
		      
		        TransaccionManager transaction = TransaccionManager.getInstance();
		        Transaccion t = transaction.newTransaccion();
		        t.start();

		        FactoriaIntegracion f = FactoriaIntegracion.getInstance();
		        SistemaDeRiegoDAO daoSistRiego = f.getSistemaDeRiegoDAO();
		        	
		        listaSisRiego = daoSistRiego.listarSistemaDeRiegoInvernadero(idInvernadero);		        
		        t.commit(); 
		        
		    } catch (Exception e) {
		        e.printStackTrace(); 
		    }
		   
		    return listaSisRiego;
	}
}