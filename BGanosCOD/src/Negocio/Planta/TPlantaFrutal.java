package Negocio.Planta;

public class TPlantaFrutal extends TPlanta {

	private String nombre_fruta;
	private String maduracion;
	
	public String get_nombre_fruta() {
		return nombre_fruta;
	}

	public String get_maduracion() {
		return maduracion;
	}

	public void set_nombre_fruta(String nombre_fruta) {
		this.nombre_fruta = nombre_fruta;
	}

	public void set_maduracion(String maduracion) {
		this.maduracion = maduracion;
	}
}