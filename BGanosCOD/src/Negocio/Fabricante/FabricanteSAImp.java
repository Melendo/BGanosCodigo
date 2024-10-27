package Negocio.Fabricante;

import java.util.Set;

import Integracion.Fabricante.FabricanteDAO;
import Integracion.FactoriaIntegracion.FactoriaIntegracion;
import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;

public class FabricanteSAImp implements FabricanteSA {
	public Integer altaFabricante(TFabricante fabricante) {
		int ret = -1;

		if (fabricante.getNombre().isEmpty() || fabricante.getCodFabricante().isEmpty())
			return -1;// XXX: falta añadir el evento de error

		try {
			TransaccionManager tm = TransaccionManager.getInstance();
			Transaccion t = tm.getTransaccion();
			t.start();
			FactoriaIntegracion fi = FactoriaIntegracion.getInstance();
			FabricanteDAO fd = fi.getFabricanteDAO();

			TFabricante tfa = fd.leerPorCodFabricante(fabricante.getCodFabricante());

			if (tfa == null) { // no existe por lo que le damos de alta
				ret = fd.altaFabricante(fabricante);
				t.commit();
			} else if(!tfa.getActivo()) { // esta desactivado, lo activamos
				ret = fd.modificarFabricante(fabricante);
				t.commit();
			} else { // ya existe y esta activado
				ret = -1; //XXX: falta el cod del error
				t.rollback();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	public Integer bajaFabricante(Integer idFabricante) {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public TFabricante mostrarFabricantePorId(Integer id) {
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

	public Set<TFabricante> listarFabricantes() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}

	public Set<TFabricante> listarFabricantePorNombre(String nombre) {
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

	public Set<TFabricante> listarFabricantesExtranjeros() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}