/**
 * 
 */
package Presentacion.Controller.Command;


public class Context {

	private Integer evento;
	
	private Object datos;

	public Integer getEvento() {
		return evento;
	}

	public Object getDatos() {
		return datos;
	}


	public void setContext(Integer evento, Object datos) {
		this.datos = datos;
	}

	
	public void setEvento(Integer evento) {
		this.evento = evento;
	}
}