package Integracion.FactoriaQuery;

import Integracion.FactoriaQuery.calcularLasTresFechasMasVendidasDeUnInvernadero;
import Integracion.FactoriaQuery.ListarInformacionFabricantePorSistemasDeRiegoDeUnInvernadero;

public class FactoriaQueryImp extends FactoriaQuery {
	
	public Query getNewQuery(String nombre) {
		
		switch (nombre){
		
			case "calcularLasTresFechasMasVendidasDeUnInvernadero": 
				return new calcularLasTresFechasMasVendidasDeUnInvernadero();

			case "ListarInformacionFabricantePorSistemasDeRiegoDeUnInvernadero" : 
				return new ListarInformacionFabricantePorSistemasDeRiegoDeUnInvernadero(); 
		}
		
		return null;
	}
}