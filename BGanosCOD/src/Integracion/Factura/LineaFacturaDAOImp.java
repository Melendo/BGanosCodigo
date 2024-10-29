/**
 * 
 */
package Integracion.Factura;

import Negocio.Factura.TFactura;
import Negocio.Factura.TLineaFactura;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import Integracion.Transaction.TransaccionManager;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author airam
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class LineaFacturaDAOImp implements LineaFacturaDAO {
	/** 
	* (non-Javadoc)
	* @see LineaFacturaDAO#crearLineaFactura(TLineaFactura lineaFactura)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Integer crearLineaFactura(TLineaFactura lineaFactura) {
		try {
			Connection c = (Connection) TransaccionManager.getInstance().getTransaccion().getResource();
			PreparedStatement statement = c.prepareStatement("INSERT INTO lineafactura (id_factura, id_pase, cantidad, precio) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, lineaFactura.getidFactura());
			statement.setInt(2, lineaFactura.getidEntrada());
			statement.setInt(3, lineaFactura.getCantidad());
			statement.setDouble(4, lineaFactura.getPrecio());
			int affectedRows = statement.executeUpdate();
			
			statement.close();
			
			if (affectedRows == 0)
				return -1;
						
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/** 
	* (non-Javadoc)
	* @see LineaFacturaDAO#bajaLineaFactura(Integer idFactura, Integer idEntrada)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TLineaFactura bajaLineaFactura(Integer idFactura, Integer idEntrada) {
		TLineaFactura lineaFactura = null;
		try {
			Connection c = (Connection) TransaccionManager.getInstance().getTransaccion().getResource();
			PreparedStatement statement = c.prepareStatement("DELETE FROM lineafactura WHERE id_factura = ? AND id_pase = ?", Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idFactura);
			statement.setInt(2, idEntrada);
			int affectedRows = statement.executeUpdate();
			
			statement = c.prepareStatement("SELECT * FROM lineafactura WHERE id_factura = ? AND id_pase = ? FOR UPDATE", Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idFactura);
			statement.setInt(2, idEntrada);
			ResultSet result = statement.executeQuery();
			
			if (result.next())
			{
				lineaFactura = new TLineaFactura();
				lineaFactura.setidFactura(result.getInt(1));
				lineaFactura.setidEntrada(result.getInt(2));
				lineaFactura.setCantidad(result.getInt(3));
				lineaFactura.setPrecio(result.getFloat(4));
			}
			
			statement.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lineaFactura;
	}

	/** 
	* (non-Javadoc)
	* @see LineaFacturaDAO#modificarLineaFactura(TLineaFactura tlineaFactura)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Integer modificarLineaFactura(TLineaFactura tlineaFactura) {
		try {
			Connection c = (Connection) TransaccionManager.getInstance().getTransaccion().getResource();
			PreparedStatement statement = c.prepareStatement("UPDATE lineafactura SET cantidad = ?, precio = ? WHERE id_factura = ? AND id_pase = ?", Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, tlineaFactura.getCantidad());
			statement.setDouble(2, tlineaFactura.getPrecio());
			statement.setInt(3, tlineaFactura.getidFactura());
			statement.setInt(4, tlineaFactura.getidEntrada());
			int affectedRows = statement.executeUpdate();
			
			statement.close();
			
			if (affectedRows == 0)
				return -1;
						
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	/** 
	* (non-Javadoc)
	* @see LineaFacturaDAO#mostrarLineaFactura(Integer idFactura, Integer idEntrada)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TLineaFactura mostrarLineaFactura(Integer idFactura, Integer idEntrada) {
		TLineaFactura lineafactura = null;
		try {
			Connection c = (Connection) TransaccionManager.getInstance().getTransaccion().getResource();
			PreparedStatement statement = c.prepareStatement("SELECT * FROM lineafactura WHERE id_factura = ? AND id_pase = ? FOR UPDATE", Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idFactura);
			statement.setInt(2, idEntrada);
			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				lineafactura = new TLineaFactura();
				lineafactura.setidFactura(result.getInt(1));
				lineafactura.setidEntrada(result.getInt(2));
				lineafactura.setCantidad(result.getInt(3));
				lineafactura.setPrecio(result.getFloat(4));
			}
			result.close();
			statement.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lineafactura;
	}

	/** 
	* (non-Javadoc)
	* @see LineaFacturaDAO#listarLineasDeFacturas()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Set<TLineaFactura> listarLineasDeFacturas() {
		Set<TLineaFactura> lineasfactura = null;

		try {
			Connection c = (Connection) TransaccionManager.getInstance().getTransaccion().getResource();
			PreparedStatement statement = c.prepareStatement("SELECT * FROM lineafactura");
			ResultSet result = statement.executeQuery();
			lineasfactura = new HashSet<TLineaFactura>();
			
			while (result.next()) {
				Integer idF = result.getInt(1);
				Integer idP = result.getInt(2);
				Integer cant = result.getInt(3);
				Float pre = result.getFloat(4);
				TLineaFactura f = new TLineaFactura();
				f.setidFactura(idF);
				f.setidEntrada(idP);
				f.setCantidad(cant);
				f.setPrecio(pre);
				lineasfactura.add(f);
			}

			result.close();
			statement.close();
			return lineasfactura;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lineasfactura;
	}

	/** 
	* (non-Javadoc)
	* @see LineaFacturaDAO#mostrarLineaFacturaPorFactura(Integer idFactura)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Set<TLineaFactura> mostrarLineaFacturaPorFactura(Integer idFactura) {
		Set<TLineaFactura> lineasfactura = null;

		try {
			Connection c = (Connection) TransaccionManager.getInstance().getTransaccion().getResource();
			PreparedStatement statement = c.prepareStatement("SELECT * FROM lineafactura WHERE id_factura = ? FOR UPDATE", Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idFactura);
			ResultSet result = statement.executeQuery();
			lineasfactura = new HashSet<TLineaFactura>();
			
			while (result.next()) {
				Integer idF = result.getInt(1);
				Integer idP = result.getInt(2);
				Integer cant = result.getInt(3);
				Float pre = result.getFloat(4);
				TLineaFactura f = new TLineaFactura();
				f.setidFactura(idF);
				f.setidEntrada(idP);
				f.setCantidad(cant);
				f.setPrecio(pre);
				lineasfactura.add(f);
			}

			result.close();
			statement.close();
			return lineasfactura;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lineasfactura;
	}
}