/**
 * 
 */
package Integracion.Factura;

import Negocio.Factura.TFactura;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import Integracion.Transaction.Transaccion;
import Integracion.Transaction.TransaccionManager;

/** 
* <!-- begin-UML-doc -->
* <!-- end-UML-doc -->
* @author airam
* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
*/
public class FacturaDAOImp implements FacturaDAO {
	/** 
	* (non-Javadoc)
	* @see FacturaDAO#cerrarFactura(TFactura tfactura)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Integer cerrarFactura(TFactura tfactura) {
		TransaccionManager tManager = TransaccionManager.getInstance();
		int exito = -1;
		try {
			Transaccion t = tManager.getTransaccion();
			Connection c = (Connection) t.getResource();
			PreparedStatement statement = c.prepareStatement(
					"INSERT INTO factura(precio_total ,fecha_compra) VALUES (?,NOW())", Statement.RETURN_GENERATED_KEYS);
			statement.setFloat(1, tfactura.getPrecioTotal());
			statement.executeUpdate();
			ResultSet result = statement.getGeneratedKeys();
			if (result.next())
				exito = result.getInt(1);
			statement.close();
			result.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return exito;
	}

	/** 
	* (non-Javadoc)
	* @see FacturaDAO#mostrarFactura(Integer id)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public TFactura mostrarFactura(Integer id) {
		TFactura factura = null;
		try 
		{
			TransaccionManager tm = TransaccionManager.getInstance();
			Transaccion t = tm.getTransaccion();
			Connection c = (Connection) t.getResource();
			PreparedStatement statement = c.prepareStatement("SELECT * FROM factura WHERE id = ? FOR UPDATE");
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				factura = new TFactura();
				factura.setid(result.getInt(1));
				factura.setPrecioTotal(result.getFloat(2));
				factura.setFechaCompra(new Date(result.getDate(3).getTime()));
				factura.setActivo(result.getBoolean(4));
			}
			statement.close();
			result.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return factura;
	}

	/** 
	* (non-Javadoc)
	* @see FacturaDAO#listarFactura()
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Set<TFactura> listarFactura() {
		Set<TFactura> facturas = new HashSet<TFactura>();
		try
		{
		Transaccion t = TransaccionManager.getInstance().getTransaccion();
		Connection c = (Connection) t.getResource();
		PreparedStatement statement = c.prepareStatement("SELECT * FROM factura FOR UPDATE");
		ResultSet result = statement.executeQuery();
		while (result.next())
		{
			TFactura factura = new TFactura();
			factura.setid(result.getInt(1));
			factura.setPrecioTotal(result.getFloat(2));
			factura.setFechaCompra(new Date(result.getDate(3).getTime()));
			factura.setActivo(result.getBoolean(4));
			facturas.add(factura);
		}
	    statement.close();
		result.close();	
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return facturas;
	}

	/** 
	* (non-Javadoc)
	* @see FacturaDAO#modificarFactura(TFactura tfactura)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Integer modificarFactura(TFactura tfactura) {
		int exito = -1;
		try {
			TransaccionManager tManager = TransaccionManager.getInstance();
			Transaccion t = tManager.getTransaccion();
			Connection c = (Connection) t.getResource();
			PreparedStatement statement = c
					.prepareStatement("UPDATE factura SET precio_total = ?, fecha_compra = ?, activo = ? WHERE id = ?");
			
			statement.setFloat(1, tfactura.getPrecioTotal());
	        statement.setDate(2, new java.sql.Date(tfactura.getFechaCompra().getTime()));
			statement.setBoolean(3, tfactura.getActivo());
			statement.setInt(4, tfactura.getid());
			exito = statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(exito != -1)
        	return tfactura.getid();
        else
        	return exito;
	}

	/** 
	* (non-Javadoc)
	* @see FacturaDAO#devolverFactura(Integer id)
	* @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	*/
	public Integer devolverFactura(Integer id) {
		int exito = -1;
		try 
		{
			Transaccion t = TransaccionManager.getInstance().getTransaccion();
			Connection c = (Connection) t.getResource();
			Statement s = c.createStatement();
			exito = s.executeUpdate("UPDATE factura SET precio_total = 0 WHERE id = " + id+";");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return exito;
	}
}