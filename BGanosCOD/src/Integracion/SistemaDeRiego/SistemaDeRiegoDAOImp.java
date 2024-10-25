/**
 * 
 */
package Integracion.SistemaDeRiego;

import Negocio.SistemaDeRiego.TSistemaDeRiego;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;

public class SistemaDeRiegoDAOImp implements SistemaDeRiegoDAO {
	
	public Integer altaSistemaDeRiego(TSistemaDeRiego sistemaDeRiego) {
		int res = -1;

        try {
            TransaccionManager tManager = TransaccionManager.getInstance();
            Transaccion trans = tManager.getTransaccion();
            Connection connection = (Connection) trans.getResource();

            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO sistema_de_riego (nombre, potencia_riego, cantidad_agua, frecuencia, activo, id_fabricante, id_invernadero) VALUES (?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            // Asignar parametos
            statement.setString(1, sistemaDeRiego.getNombre());
            statement.setInt(2, sistemaDeRiego.getPotenciaRiego());
            statement.setInt(3, sistemaDeRiego.getCantidad_agua());
            statement.setInt(4, sistemaDeRiego.getFrecuencia());
            statement.setBoolean(5, sistemaDeRiego.getActivo());
            statement.setInt(6, sistemaDeRiego.getIdFabricante());
            statement.setInt(7, sistemaDeRiego.getIdInvernadero());

            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                res = result.getInt(1); // Obtener id generado
            }

            result.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }
	

	
	public Integer bajaSistemaDeRiego(Integer id) {
	    int res = -1;

	    try {
	    	TransaccionManager tManager = TransaccionManager.getInstance();
            Transaccion trans = tManager.getTransaccion();
            Connection connection = (Connection) trans.getResource();
	        
	        PreparedStatement statement = connection.prepareStatement(
	            "UPDATE sistema_de_riego SET activo = false WHERE id = ?"
	        );

	        // Asignar id
	        statement.setInt(1, id);
	        res = statement.executeUpdate(); 

	        statement.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    return (res > 0) ? id : -1;
	}

	
	public Integer modificarSistemaDeRiego(TSistemaDeRiego sistemaDeRiego) {
    	int res = -1;
    	
        try {
        	TransaccionManager tManager = TransaccionManager.getInstance();
            Transaccion trans = tManager.getTransaccion();
            Connection connection = (Connection) trans.getResource();
            
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE sistema_de_riego SET nombre = ?, potencia_riego = ?, cantidad_agua = ?, frecuencia = ?, activo = ?, id_fabricante = ?, id_invernadero = ? WHERE id = ?"
                );

            	// Asignar parametos
                statement.setString(1, sistemaDeRiego.getNombre());
                statement.setInt(2, sistemaDeRiego.getPotenciaRiego());
                statement.setInt(3, sistemaDeRiego.getCantidad_agua());
                statement.setInt(4, sistemaDeRiego.getFrecuencia());
                statement.setBoolean(5, sistemaDeRiego.getActivo());
                statement.setInt(6, sistemaDeRiego.getIdFabricante());
                statement.setInt(7, sistemaDeRiego.getIdInvernadero());
                statement.setInt(8, sistemaDeRiego.getId());

                res = statement.executeUpdate();

                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return (res > 0) ? sistemaDeRiego.getId() : -1;
	}

	
	public TSistemaDeRiego mostrarSistemaDeRiegoPorID(Integer id) {
		TSistemaDeRiego sistemaDeRiego = null;

	    try {
	    	TransaccionManager tManager = TransaccionManager.getInstance();
            Transaccion trans = tManager.getTransaccion();
            Connection connection = (Connection) trans.getResource();
	        
	        PreparedStatement statement = connection.prepareStatement(
	            "SELECT * FROM sistema_de_riego WHERE id = ? FOR UPDATE"
	        );
	        statement.setInt(1, id);

	        ResultSet result = statement.executeQuery();
	     // Asignar parametos de vuelta al tsistemaDeRiego
	        if (result.next()) {
	            sistemaDeRiego = new TSistemaDeRiego();
	            sistemaDeRiego.setId(result.getInt("id"));
	            sistemaDeRiego.setNombre(result.getString("nombre"));
	            sistemaDeRiego.setPotenciaRiego(result.getInt("potencia_riego"));
	            sistemaDeRiego.setCantidad_agua(result.getInt("cantidad_agua"));
	            sistemaDeRiego.setFrecuencia(result.getInt("frecuencia"));
	            sistemaDeRiego.setActivo(result.getBoolean("activo"));
	            sistemaDeRiego.setIdFabricante(result.getInt("id_fabricante"));
	            sistemaDeRiego.setIdInvernadero(result.getInt("id_invernadero"));
	        }

	        statement.close();
	        result.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return sistemaDeRiego;
	}

	
	public Set<TSistemaDeRiego> listarSistemaDeRiegoPorFabricante(Integer idFabricante) {
		Set<TSistemaDeRiego> sistemasDeRiego = new HashSet<>();

	    try {
	    	TransaccionManager tManager = TransaccionManager.getInstance();
            Transaccion trans = tManager.getTransaccion();
            Connection connection = (Connection) trans.getResource();
	        
	        PreparedStatement statement = connection.prepareStatement(
	            "SELECT * FROM sistema_de_riego WHERE id_fabricante = ? FOR UPDATE"
	        );
	        statement.setInt(1, idFabricante);
	        
	        ResultSet result = statement.executeQuery();

	        while (result.next()) {
	            TSistemaDeRiego sistemaDeRiego = new TSistemaDeRiego();
	            sistemaDeRiego.setId(result.getInt("id"));
	            sistemaDeRiego.setNombre(result.getString("nombre"));
	            sistemaDeRiego.setPotenciaRiego(result.getInt("potencia_riego"));
	            sistemaDeRiego.setCantidad_agua(result.getInt("cantidad_agua"));
	            sistemaDeRiego.setFrecuencia(result.getInt("frecuencia"));
	            sistemaDeRiego.setActivo(result.getBoolean("activo"));
	            sistemaDeRiego.setIdFabricante(result.getInt("id_fabricante"));
	            sistemaDeRiego.setIdInvernadero(result.getInt("id_invernadero"));
	            
	            sistemasDeRiego.add(sistemaDeRiego);
	        }

	        statement.close();
	        result.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return sistemasDeRiego;
	}

	
	public Set<TSistemaDeRiego> listarSistemaDeRiego() {
		Set<TSistemaDeRiego> sistemasDeRiego = new HashSet<>();

	    try {
	    	TransaccionManager tManager = TransaccionManager.getInstance();
            Transaccion trans = tManager.getTransaccion();
            Connection connection = (Connection) trans.getResource();
	        
	        PreparedStatement statement = connection.prepareStatement(
	        		"SELECT * FROM sistema_de_riego FOR UPDATE"
	        );
	        
	        ResultSet result = statement.executeQuery();

	        while (result.next()) {
	            TSistemaDeRiego sistemaDeRiego = new TSistemaDeRiego();
	            sistemaDeRiego.setId(result.getInt("id"));
	            sistemaDeRiego.setNombre(result.getString("nombre"));
	            sistemaDeRiego.setPotenciaRiego(result.getInt("potencia_riego"));
	            sistemaDeRiego.setCantidad_agua(result.getInt("cantidad_agua"));
	            sistemaDeRiego.setFrecuencia(result.getInt("frecuencia"));
	            sistemaDeRiego.setActivo(result.getBoolean("activo"));
	            sistemaDeRiego.setIdFabricante(result.getInt("id_fabricante"));
	            sistemaDeRiego.setIdInvernadero(result.getInt("id_invernadero"));
	            
	            sistemasDeRiego.add(sistemaDeRiego);
	        }

	        statement.close();
	        result.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return sistemasDeRiego;
	}

	
	public TSistemaDeRiego leerPorNombreUnico(String nombre) {
		TSistemaDeRiego sistemaDeRiego = null;

	    try {
	    	TransaccionManager tManager = TransaccionManager.getInstance();
            Transaccion trans = tManager.getTransaccion();
            Connection connection = (Connection) trans.getResource();
	        
	        PreparedStatement statement = connection.prepareStatement(
	            "SELECT * FROM sistema_de_riego WHERE nombre = ? "
	        );
	        statement.setString(1, nombre);

	        ResultSet result = statement.executeQuery();

	        if (result.next()) {
	            sistemaDeRiego = new TSistemaDeRiego();
	            sistemaDeRiego.setId(result.getInt("id"));
	            sistemaDeRiego.setNombre(result.getString("nombre"));
	            sistemaDeRiego.setPotenciaRiego(result.getInt("potencia_riego"));
	            sistemaDeRiego.setCantidad_agua(result.getInt("cantidad_agua"));
	            sistemaDeRiego.setFrecuencia(result.getInt("frecuencia"));
	            sistemaDeRiego.setActivo(result.getBoolean("activo"));
	            sistemaDeRiego.setIdFabricante(result.getInt("id_fabricante"));
	            sistemaDeRiego.setIdInvernadero(result.getInt("id_invernadero"));
	        }

	        statement.close();
	        result.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return sistemaDeRiego;
	}

	
	public Set<TSistemaDeRiego> listarSistemaDeRiegoInvernadero(Integer idInvernadero) {
		Set<TSistemaDeRiego> sistemasDeRiego = new HashSet<>();

	    try {
	    	TransaccionManager tManager = TransaccionManager.getInstance();
            Transaccion trans = tManager.getTransaccion();
            Connection connection = (Connection) trans.getResource();
	        
	        PreparedStatement statement = connection.prepareStatement(
	            "SELECT * FROM sistema_de_riego WHERE id_invernadero = ?"
	        );
	        statement.setInt(1, idInvernadero);

	        ResultSet result = statement.executeQuery();

	        while (result.next()) {
	            TSistemaDeRiego sistemaDeRiego = new TSistemaDeRiego();
	            sistemaDeRiego.setId(result.getInt("id"));
	            sistemaDeRiego.setNombre(result.getString("nombre"));
	            sistemaDeRiego.setPotenciaRiego(result.getInt("potencia_riego"));
	            sistemaDeRiego.setCantidad_agua(result.getInt("cantidad_agua"));
	            sistemaDeRiego.setFrecuencia(result.getInt("frecuencia"));
	            sistemaDeRiego.setActivo(result.getBoolean("activo"));
	            sistemaDeRiego.setIdFabricante(result.getInt("id_fabricante"));
	            sistemaDeRiego.setIdInvernadero(result.getInt("id_invernadero"));
	            
	            sistemasDeRiego.add(sistemaDeRiego);
	        }

	        statement.close();
	        result.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return sistemasDeRiego;
	}
}