package Integracion.Fabricante;

import Negocio.Fabricante.TFabricante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;

import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;

public class FabricanteDAOImp implements FabricanteDAO {

	public Integer altaFabricante(TFabricante fabricante) {
		try {
			TransaccionManager tm = TransaccionManager.getInstance();
			Transaccion t = tm.getTransaccion();
			Connection c = (Connection) t.getResource();

			PreparedStatement s = c.prepareStatement(
					"INSERT INTO fabricante (cod_fabricante, nombre, telefono, activo VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			s.setString(1, fabricante.getCodFabricante());
			s.setString(2, fabricante.getNombre());
			s.setString(3, fabricante.getTelefono());
			s.setBoolean(4, fabricante.getActivo());
			s.executeUpdate();
			
			ResultSet r = s.getGeneratedKeys();
			
		} catch (Exception e) {

		}

		return null;
	}

	public Integer bajaFabricante(Integer idFabricante) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public TFabricante listarFabricantePorNombre(String nombre) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Set<TFabricante> listarFabricantes() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Set<TFabricante> listarFabricantesExtrangeros() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Set<TFabricante> listarFabricantesLocales() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Integer modificarFabricante(TFabricante fabricante) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public TFabricante mostrarFabricantePorId(Integer idFabricante) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}