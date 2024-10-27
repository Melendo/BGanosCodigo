/**
 * 
 */
package Integracion.Invernadero;

import Negocio.Invernadero.TInvernadero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Set<TInvernadero> listarInvernadero() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Set<TInvernadero> listarInvernaderoPorSR(Integer id_sistema_de_riego) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer modificarInvernadero(TInvernadero invernadero) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	@Override
	public TInvernadero mostrarInvernaderoPorNombre(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}
}