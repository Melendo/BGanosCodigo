package Integracion.Fabricante;

import Negocio.Fabricante.TFabricante;
import Negocio.Fabricante.TFabricanteExtranjero;
import Negocio.Fabricante.TFabricanteLocal;
import Negocio.Habitat.THabitat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;

import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;
import Integracion.Transactions.Transaction;
import Integracion.Transactions.TransactionManager;

public class FabricanteDAOImp implements FabricanteDAO {

	@SuppressWarnings("resource")
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
			if (r.next()) {
				int id = r.getInt(1);
				
				if(fabricante instanceof TFabricanteLocal) {
					s = c.prepareStatement("INSERT INTO fabricante_local(id_fabricante, impuesto, subvencion) Values(?,?,?)");
					s.setInt(1, id);
					s.setInt(2, ((TFabricanteLocal)fabricante).getImpuesto());
					s.setInt(3, ((TFabricanteLocal)fabricante).getSubvencion());
					
					if(s.executeUpdate() == 0) {
						s.close();
						r.close();
						return -1;
					}
				}
				else if(fabricante instanceof TFabricanteExtranjero) {
					s = c.prepareStatement("INSERT INTO fabricante_extranjero (id_fabricante, aranceles, pais_origen) Values(?,?,?)");
					s.setInt(1, id);
					s.setInt(2, ((TFabricanteExtranjero) fabricante).getAranceles());
					s.setString(2, ((TFabricanteExtranjero) fabricante).getPaisDeOrigen());
					
					if(s.executeUpdate() == 0) {
						s.close();
						r.close();
						return -1;
					}
				}
				
				s.close();
				r.close();
				return id;
			}
			else
				return -1;
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
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

	@Override
	public TFabricante leerPorCodFabricante(int codFabricante) {
		TFabricante tf;
		
		try {
            TransaccionManager tm = TransaccionManager.getInstance();
            Transaccion t = tm.getTransaccion();
            Connection c = (Connection) t.getResource();
            PreparedStatement s = c.prepareStatement(
            		"SELECT * FROM Animal AS e JOIN fabricante_local AS el ON e.id=el.id WHERE e.id=? FOR UPDATE");
            s.setInt(1, codFabricante);

            ResultSet r = s.executeQuery();

            if (r.next()) {
            	
            	if()
            	
            	tf = new TFabricante();
            	tf.setId(result.getInt("id"));
            	tf.setNombre(result.getString("nombre"));
            	tf.setTamano(result.getInt("tamano"));
            	tf.setActivo(result.getBoolean("activo"));
            }

            statement.close();
            result.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return habitat;
	}
}