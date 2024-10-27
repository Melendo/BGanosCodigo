package Integracion.Fabricante;

import Negocio.Fabricante.TFabricante;
import Negocio.Fabricante.TFabricanteExtranjero;
import Negocio.Fabricante.TFabricanteLocal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Set;

import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;

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

	public TFabricante leerPorCodFabricante(String codFabricante) {
		TFabricante tf = null;
		
		try {
            TransaccionManager tm = TransaccionManager.getInstance();
            Transaccion t = tm.getTransaccion();
            Connection c = (Connection) t.getResource();
            PreparedStatement s = c.prepareStatement(
            		"SELECT * FROM fabricante AS e JOIN fabricante_local AS el ON e.id=el.id WHERE e.cod_fabricante=? FOR UPDATE");
            s.setString(1, codFabricante);
            ResultSet r = s.executeQuery();

            if (r.next()) {
            	TFabricanteLocal tfl = new TFabricanteLocal();
            	tfl.setId(r.getInt("id"));
            	tfl.setActivo(r.getBoolean("activo"));
            	tfl.setCodFabricante(r.getString("cod_fabricante"));
            	tfl.setNombre(r.getString("nombre"));
            	tfl.setTelefono(r.getString("telefono"));
            	tfl.setImpuesto(r.getInt("impuesto"));
            	tfl.setSubvencion(r.getInt("subvencion"));
            	
            	tf = tfl;
            }
            else {
            	s = c.prepareStatement("SELECT * FROM fabricante AS e JOIN fabricante_extranjero AS es ON e.id=es.id WHERE e.cod_fabricante=? FOR UPDATE");
            	s.setString(1, codFabricante);
            	r = s.executeQuery();
            	
            	if(r.next()) {
            		TFabricanteExtranjero tfe = new TFabricanteExtranjero();
                	tfe.setId(r.getInt("id"));
                	tfe.setActivo(r.getBoolean("activo"));
                	tfe.setCodFabricante(r.getString("cod_fabricante"));
                	tfe.setNombre(r.getString("nombre"));
                	tfe.setTelefono(r.getString("telefono"));
                	tfe.setAranceles(r.getInt("aranceles"));
                	tfe.setPaisDeOrigen(r.getString("pais_origen"));
                	
                	tf = tfe;
            	}
            }
            
            r.close();
            s.close();
            return tf;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
	}
}