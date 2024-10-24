/**
 * 
 */
package Integracion.Transaction;


public interface Transaccion {
	
	public void start() throws Exception;
	
	public void commit()throws Exception;

	public void rollback()throws Exception;

	public Object getResource();
	
	public void cerrarConnection() throws Exception;
}