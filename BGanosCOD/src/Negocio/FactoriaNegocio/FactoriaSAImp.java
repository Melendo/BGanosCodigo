package Negocio.FactoriaNegocio;

import Negocio.Entrada.EntradaSA;
import Negocio.Entrada.EntradaSAImp;
import Negocio.Fabricante.FabricanteSA;
import Negocio.Fabricante.FabricanteSAImp;
import Negocio.Factura.FacturaSA;
import Negocio.Factura.FacturaSAImp;
import Negocio.Invernadero.InvernaderoSA;
import Negocio.Invernadero.InvernaderoSAImp;
import Negocio.Planta.PlantaSA;
import Negocio.Planta.PlantaSAImp;
import Negocio.SistemaDeRiego.SistemaDeRiegoSA;
import Negocio.SistemaDeRiego.SistemaDeRiegoSAImp;


public class FactoriaSAImp extends FactoriaNegocio {

	public EntradaSA getEntradaSA() {
		return new EntradaSAImp();
	}

	public FabricanteSA getFabricanteSA() {
		return new FabricanteSAImp();
	}

	public FacturaSA getFacturaSA() {
		return new FacturaSAImp();
	}

	public InvernaderoSA getInvernaderoSA() {
		return new InvernaderoSAImp();
	}

	public PlantaSA getPlantaSA() {
		return new PlantaSAImp();
	}

	public SistemaDeRiegoSA getSistemaDeRiegoSA() {
		return new SistemaDeRiegoSAImp();
	}
}