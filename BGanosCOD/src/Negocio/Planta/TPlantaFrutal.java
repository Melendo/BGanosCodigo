package Negocio.Planta;

public class TPlantaFrutal extends TPlanta {



	private String nombre_fruta;
	private String maduracion;
	
	public TPlantaFrutal(String nombre, String nombre_cientifico, Integer tipo, Integer id_invernadero,String fruta, String maduracion ) {
		super(nombre, nombre_cientifico, tipo, id_invernadero);
		this.maduracion = maduracion;
		this.nombre_fruta = fruta;
	}
	
	public TPlantaFrutal() {
		// TODO Auto-generated constructor stub
	}

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