/**
 * 
 */
package Integracion.SistemaDeRiego;

import Negocio.SistemaDeRiego.TSistemaDeRiego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;

import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;

public class SistemaDeRiegoDAOImp implements SistemaDeRiegoDAO {
	
	public Integer altaSistemaDeRiego(TSistemaDeRiego sistemaDeRiego) {
		int exito = -1;

        try {
            TransaccionManager tManager = TransaccionManager.getInstance();
            Transaccion trans = tManager.getTransaccion();
            Connection connection = (Connection) trans.getResource();

            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO sistema_de_riego (nombre, potencia_riego, cantidad_agua, frecuencia, activo, id_fabricante) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            // Asignar parametos
            statement.setString(1, sistemaDeRiego.getNombre());
            statement.setInt(2, sistemaDeRiego.getPotenciaRiego());
            statement.setInt(3, sistemaDeRiego.getCantidad_agua());
            statement.setInt(4, sistemaDeRiego.getFrecuencia());
            statement.setBoolean(5, sistemaDeRiego.getActivo());
            statement.setInt(6, sistemaDeRiego.getIdFabricante());

            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                exito = result.getInt(1); // Obtener id generado
            }

            result.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exito;
    }
	

	
	public Integer bajaSistemaDeRiego(Integer id) {
	    int exito = -1;

	    try {
	    	TransaccionManager tManager = TransaccionManager.getInstance();
            Transaccion trans = tManager.getTransaccion();
            Connection connection = (Connection) trans.getResource();
	        
	        PreparedStatement statement = connection.prepareStatement(
	            "UPDATE sistema_de_riego SET activo = false WHERE id = ?"
	        );

	        // Asignar id
	        statement.setInt(1, id);
	        exito = statement.executeUpdate(); 

	        statement.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return (exito > 0) ? id : -1;
	}

	
	public Integer modificarSistemaDeRiego(TSistemaDeRiego sistemaDeRiego) {
    	int exito = -1;
    	
        try {
        	TransaccionManager tManager = TransaccionManager.getInstance();
            Transaccion trans = tManager.getTransaccion();
            Connection connection = (Connection) trans.getResource();
            
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE sistema_de_riego SET nombre = ?, potencia_riego = ?, cantidad_agua = ?, frecuencia = ?, activo = ?, id_fabricante = ? WHERE id = ?"
                );

            	// Asignar parametos
                statement.setString(1, sistemaDeRiego.getNombre());
                statement.setInt(2, sistemaDeRiego.getPotenciaRiego());
                statement.setInt(3, sistemaDeRiego.getCantidad_agua());
                statement.setInt(4, sistemaDeRiego.getFrecuencia());
                statement.setBoolean(5, sistemaDeRiego.getActivo());
                statement.setInt(6, sistemaDeRiego.getIdFabricante());
                statement.setInt(7, sistemaDeRiego.getId());

                exito = statement.executeUpdate();

                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return (exito > 0) ? sistemaDeRiego.getId() : -1;
	}

	
	public TSistemaDeRiego mostrarSistemaDeRiegoPorID(Integer id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	
	public Set<TSistemaDeRiego> listarSistemaDeRiegoPorFabricante(Integer idFabricante) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	
	public Set<TSistemaDeRiego> listarSistemaDeRiego() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	@Override
	public TSistemaDeRiego leerPorNombreUnico(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TSistemaDeRiego> listarSistemaDeRiegoInvernadero(Integer idInvernadero) {
		// TODO Auto-generated method stub
		return null;
	}
}