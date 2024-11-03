/**
 * 
 */
package Integracion.Entrada;

import Negocio.Entrada.TEntrada;

import java.sql.Date;
import java.util.Set;

public interface EntradaDAO {

	public Integer altaEntrada(TEntrada entrada);

	public Integer bajaEntrada(Integer id);

	public Set<TEntrada> listarEntradas();

	// TODO preguntar si puedo poner el throws Exception
	public Integer modificarEntrada(TEntrada entrada);

	public TEntrada mostrarEntrada(Integer id);

	public Set<TEntrada> listarEntradasPorInvernadero(Integer idInvernadero);
	
	public TEntrada leerPorFechaUnica(Date fecha);
}