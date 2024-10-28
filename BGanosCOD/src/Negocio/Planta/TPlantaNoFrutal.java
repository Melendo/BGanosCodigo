package Negocio.Planta;

public class TPlantaNoFrutal extends TPlanta {


	private String tipo_hoja;


	public TPlantaNoFrutal(String nombre, String nombre_cientifico, Integer tipo, Integer id_invernadero,String tipo_hoja ) {
		super(nombre, nombre_cientifico, tipo, id_invernadero);
		this.tipo_hoja = tipo_hoja;
	}
	
	public TPlantaNoFrutal() {
		// TODO Auto-generated constructor stub
	}

	public String get_tipo_hoja() {
		return tipo_hoja;
	}

	public void set_tipo_hoja(String tipo_hoja) {
		this.tipo_hoja = tipo_hoja;
	}
}