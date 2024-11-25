/**
 * 
 */
package Negocio.ProductoJPA;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name = "Negocio.ProductoJPA.ProductoSouvenirs.findBydescripcion", query = "select obj from ProductoSouvenirs obj where :descripcion = obj.descripcion ")
public class ProductoSouvenirs extends Producto implements Serializable {

	private static final long serialVersionUID = 0;


	public ProductoSouvenirs() {
	}


	private String descripcion;


	public void getDescripcion() {

	}


	public void setDescripcion() {
		
	}
}