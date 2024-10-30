/**
 * 
 */
package Integracion.Invernadero;

import Negocio.Invernadero.TInvernadero;
import Negocio.Invernadero.TTiene;
import java.util.Set;

import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

public class TieneDAOImp implements TieneDAO {

	public Integer altaTiene(TTiene tiene) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer bajaTiene(Integer idInvernadero, Integer idSisRiego) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Set<TTiene> listarTiene() {
		Set<TTiene> tienen = new HashSet<TTiene>();
		try {
			TransaccionManager tManager = TransaccionManager.getInstance();
			Transaccion t = tManager.getTransaccion();
			Connection c = (Connection) t.getResource();
			PreparedStatement statement = c.prepareStatement("SELECT * FROM sistema_riego_de_invernadero FOR UPDATE");

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				TTiene tiene = new TTiene();
				tiene.setId_Invernadero(result.getInt("id_invernadero"));
				tiene.setId_SistemasDeRiego(result.getInt("id_sistema_riego"));

				tienen.add(tiene);
			}

			statement.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tienen;
	}

	public Integer vincularInvernaderoConSisRiego(TTiene tiene) {
		return altaTiene(tiene);
	}

	public Integer desvincularInvernaderoConSisRiego(TTiene tiene) {
		return bajaTiene(tiene.getId_Invernadero(), tiene.getId_SistemasDeRiego());
	}

	public Set<TTiene> mostrarTienePorInvernadero(Integer idInvernadero) {
		Set<TTiene> tienen = new HashSet<TTiene>();
		try {
			TransaccionManager tManager = TransaccionManager.getInstance();
			Transaccion t = tManager.getTransaccion();
			Connection c = (Connection) t.getResource();
			PreparedStatement statement = c
					.prepareStatement("SELECT * FROM sistema_riego_de_invernadero WHERE id_invernadero = ? FOR UPDATE");
			statement.setInt(1, idInvernadero);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				TTiene tiene = new TTiene();
				tiene.setId_Invernadero(result.getInt("id_invernadero"));
				tiene.setId_SistemasDeRiego(result.getInt("id_sistema_riego"));

				tienen.add(tiene);
			}

			statement.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tienen;
	}

	public Set<TTiene> mostrarTienePorSisRiego(Integer idSisRiego) {
		Set<TTiene> tienen = new HashSet<TTiene>();
		try {
			TransaccionManager tManager = TransaccionManager.getInstance();
			Transaccion t = tManager.getTransaccion();
			Connection c = (Connection) t.getResource();
			PreparedStatement statement = c
					.prepareStatement("SELECT * FROM sistema_riego_de_invernadero WHERE id_sistema_riego = ? FOR UPDATE");
			statement.setInt(1, idSisRiego);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				TTiene tiene = new TTiene();
				tiene.setId_Invernadero(result.getInt("id_invernadero"));
				tiene.setId_SistemasDeRiego(result.getInt("id_sistema_riego"));

				tienen.add(tiene);
			}

			statement.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tienen;
	}

	public TTiene mostrarTiene(TTiene tiene) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}