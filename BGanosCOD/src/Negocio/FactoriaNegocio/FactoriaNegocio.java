
package Negocio.FactoriaNegocio;

import Negocio.Entrada.EntradaSA;
import Negocio.Fabricante.FabricanteSA;
import Negocio.Factura.FacturaSA;
import Negocio.Invernadero.InvernaderoSA;
import Negocio.Planta.PlantaSA;
import Negocio.ProductoJPA.ProductoSA;
import Negocio.SistemaDeRiego.SistemaDeRiegoSA;

public abstract class FactoriaNegocio {

	private static FactoriaNegocio instance;

	public static FactoriaNegocio getInstance() {
		if(instance == null) 
			instance = new FactoriaSAImp();
		return instance;
	}

	public abstract EntradaSA getEntradaSA();
	
	public abstract FabricanteSA getFabricanteSA();

	public abstract FacturaSA getFacturaSA();

	public abstract  InvernaderoSA getInvernaderoSA();

	public abstract PlantaSA getPlantaSA();

	public abstract SistemaDeRiegoSA getSistemaDeRiegoSA();
	
	public abstract ProductoSA getProductoJPA() ;
	
}