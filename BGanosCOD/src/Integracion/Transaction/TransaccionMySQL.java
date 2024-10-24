package Integracion.Transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class TransaccionMySQL implements Transaccion {

	private String DB_properties;

	private Connection conexion;

	public void start() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void commit() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public void rollback() {
		// begin-user-code
		// TODO Auto-generated method stub

		// end-user-code
	}

	public Object getResource() {
		// begin-user-code
		// TODO Auto-generated method stub
		return null;
		// end-user-code
	}
}