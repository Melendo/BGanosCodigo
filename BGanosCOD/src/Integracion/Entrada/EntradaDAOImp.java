/**
 * 
 */
package Integracion.Entrada;

import Negocio.Entrada.TEntrada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;

import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;

public class EntradaDAOImp implements EntradaDAO {

	public Integer altaEntrada(TEntrada entrada) {

		int id = -1;

		try {
			TransaccionManager tm = TransaccionManager.getInstance();
			Transaccion t = tm.getTransaccion();
			Connection c = (Connection) t.getResource();

			PreparedStatement ps = c.prepareStatement(
					"INSERT INTO entrada (fecha, precio, stock, activo) VALUES (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, entrada.getFecha());
			ps.setFloat(2, entrada.getPrecio());
			ps.setInt(3, entrada.getStock());
			ps.setBoolean(4, entrada.getActivo());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next())
				id = rs.getInt(1);

			if (ps != null)
				ps.close();

			if (rs != null)
				rs.close();

		} catch (Exception e) {

		}

		return id;
	}

	public Integer bajaEntrada(Integer id) {
		try {
			TransaccionManager tm = TransaccionManager.getInstance();
			Transaccion t = tm.getTransaccion();
			Connection c = (Connection) t.getResource();

			PreparedStatement ps = c.prepareStatement("UPDATE entrada SET activo=false where idEntrada=?");

			ps.setInt(1, id);
			ps.executeUpdate();
			ps.close();
			return id;

		} catch (Exception e) {
			return -1;
		}
	}

	public Set<TEntrada> listarEntradas() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
		
		//		try {
		//		
		//	} catch (Exception e) {
		//		
		//	}
	}

	public Integer modificarEntrada(TEntrada entrada) throws Exception {
		try {
			TransaccionManager tm = TransaccionManager.getInstance();
			Transaccion t = tm.getTransaccion();
			Connection c = (Connection) t.getResource();
			
			PreparedStatement ps = c.prepareStatement("UPDATE entrada SET fecha=?, precio=?, stock=?, activo=? WHERE idEntrada=?");
			
			ps.setString(1, entrada.getFecha());
			ps.setFloat(2, entrada.getPrecio());
			ps.setInt(3, entrada.getStock());
			ps.setBoolean(4, entrada.getActivo());
			ps.setInt(5, entrada.getId());
			ps.executeUpdate();
			ps.close();
			
			return entrada.getId();
			
		} catch (Exception e) {
			throw new Exception("La conexión con la BBDD no se ha realizado correctamente");
		}
	}

	public TEntrada mostrarEntrada(Integer id) {
		
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
		
		//		try {
		//		
		//	} catch (Exception e) {
		//		
		//	}
	}

	public Set<TEntrada> listarEntradasPorInvernadero(Integer idInvernadero) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
		
		//		try {
		//		
		//	} catch (Exception e) {
		//		
		//	}
	}

}