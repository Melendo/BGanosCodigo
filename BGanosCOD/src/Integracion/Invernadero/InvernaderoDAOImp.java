package Integracion.Invernadero;

import Negocio.Invernadero.TInvernadero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;

public class InvernaderoDAOImp implements InvernaderoDAO {

	public Integer altaInvernadero(TInvernadero invernadero) {
		int exito = -1;

		try {
			TransaccionManager tManager = TransaccionManager.getInstance();
			Transaccion t = tManager.getTransaccion();
			Connection c = (Connection) t.getResource();
			PreparedStatement statement = c.prepareStatement(
					"INSERT INTO invernadero(nombre, sustrato, tipo_iluminacion, activo) VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, invernadero.getNombre());
			statement.setString(2, invernadero.getSustrato());
			statement.setString(3, invernadero.getTipo_iluminacion());
			statement.setBoolean(4, true);

			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if (result.next()) {
				exito = result.getInt(1);
			}

			statement.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return exito;
	}

	public Integer bajaInvernadero(Integer id) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public TInvernadero mostrarInvernaderoPorID(Integer id) {
		TInvernadero invernadero = null;

		try {
			TransaccionManager tManager = TransaccionManager.getInstance();
			Transaccion t = tManager.getTransaccion();
			Connection c = (Connection) t.getResource();
			PreparedStatement statement = c.prepareStatement("SELECT * FROM invernadero WHERE id = ? FOR UPDATE");
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				invernadero = new TInvernadero();
				invernadero.setId(result.getInt("id"));
				invernadero.setNombre(result.getString("nombre"));
				invernadero.setSustrato(result.getString("sustrato"));
				invernadero.setTipo_iluminacion(result.getString("tipo_iluminacion"));
				invernadero.setActivo(result.getBoolean("activo"));
			}

			statement.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return invernadero;
	}

	public Set<TInvernadero> listarInvernadero() {
		Set<TInvernadero> invernaderos = new HashSet<>();
		try {
			TransaccionManager tManager = TransaccionManager.getInstance();
			Transaccion t = tManager.getTransaccion();
			Connection c = (Connection) t.getResource();
			PreparedStatement statement = c.prepareStatement("SELECT * FROM invernadero FOR UPDATE");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
            	TInvernadero invernadero = new TInvernadero();
            	invernadero.setId(result.getInt("id"));
            	invernadero.setNombre(result.getString("nombre"));
            	invernadero.setSustrato(result.getString("sustrato"));
            	invernadero.setTipo_iluminacion(result.getString("tipo_iluminacion"));
            	invernadero.setActivo(result.getBoolean("activo"));
            	invernaderos.add(invernadero);
            }


			statement.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return invernaderos;
	}

	public Set<TInvernadero> listarInvernaderoPorSR(Integer id_sistema_de_riego) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer modificarInvernadero(TInvernadero invernadero) {
		int exito = -1;

		try {
			TransaccionManager tManager = TransaccionManager.getInstance();
			Transaccion t = tManager.getTransaccion();
			Connection c = (Connection) t.getResource();
			PreparedStatement statement = c.prepareStatement(
					"UPDATE invernadero SET nombre = ?, sustrato = ?, tipo_iluminacion = ? where id = ?",
					Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, invernadero.getNombre());
			statement.setString(2, invernadero.getSustrato());
			statement.setString(3, invernadero.getTipo_iluminacion());
			statement.setInt(4, invernadero.getId());

			statement.executeUpdate();

			ResultSet result = statement.getGeneratedKeys();
			if (result.next()) {
				exito = result.getInt(1);
			}

			statement.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return exito;
	}

	public TInvernadero mostrarInvernaderoPorNombre(String nombre) {
		TInvernadero invernadero = null;

		try {
			TransaccionManager tManager = TransaccionManager.getInstance();
			Transaccion t = tManager.getTransaccion();
			Connection c = (Connection) t.getResource();
			PreparedStatement statement = c
					.prepareStatement("SELECT * FROM invernadero WHERE nombre like ? FOR UPDATE");
			statement.setString(1, nombre);

			ResultSet result = statement.executeQuery();

			if (result.next()) {
				invernadero = new TInvernadero();
				invernadero.setId(result.getInt("id"));
				invernadero.setNombre(result.getString("nombre"));
				invernadero.setSustrato(result.getString("sustrato"));
				invernadero.setTipo_iluminacion(result.getString("tipo_iluminacion"));
				invernadero.setActivo(result.getBoolean("activo"));
			}

			statement.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return invernadero;
	}
}