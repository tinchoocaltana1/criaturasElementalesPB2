package ar.edu.unlam.pb2.CriaturasElementales;

import java.util.HashMap;
import java.util.List;

public class ConsejoDeElandria {
	public static HashMap<String, ICriatura> listarTodasLasCriaturas(List<Maestro> maestros) throws ListaDeMaestrosNulaException {
		if(maestros == null || maestros.size() == 0) {
			throw new ListaDeMaestrosNulaException("La lista de maestros no puede ser nula o estar vacia.");
		}
		
		HashMap<String, ICriatura> criaturas = new HashMap<>();
		
		for(Maestro m : maestros) {
			 criaturas.putAll(m.getCriaturas());
		}
		
		return criaturas;
	}
	
	public static ICriatura obtenerCriaturaConMayorEnergiaTotal(List<Maestro> maestros) throws ListaDeMaestrosNulaException {
		if(maestros == null || maestros.size() == 0) {
			throw new ListaDeMaestrosNulaException("La lista de maestros no puede ser nula o estar vacia.");
		}
		
		HashMap<String, ICriatura> criaturas = listarTodasLasCriaturas(maestros);
		Integer energiaMax = -1;
		ICriatura criaturaConMayorEnergiaTotal = null;
		
		for(ICriatura criatura : criaturas.values()) {
			Integer energia = criatura.getEnergia();
			
			if (energia > energiaMax) {
				energiaMax = energia;
				criaturaConMayorEnergiaTotal = criatura;
			}
		}
		
		return criaturaConMayorEnergiaTotal;
	}
	
	public static Maestro obtenerMaestroConMasCriaturasTransformadas(List<Maestro> maestros) throws ListaDeMaestrosNulaException {
		if(maestros == null || maestros.size() == 0) {
			throw new ListaDeMaestrosNulaException("La lista de maestros no puede ser nula o estar vacia.");
		}
		
		Maestro maestroConMasCriaturasTransformadas = null;
		Integer max = -1;
		
		for (Maestro m : maestros) {
			long cantidad = m.getCriaturas().values()
					.stream()
					.filter(c -> c instanceof ConTransformacion)
					.count();
			
			if(cantidad > max) {
				max = (int) cantidad;
				maestroConMasCriaturasTransformadas = m;
			}
		}
		
		return maestroConMasCriaturasTransformadas;
	}
	
	public static HashMap<Afinidad, Integer> obtenerCantidadDeCriaturasPorAfinidad(List<Maestro> maestros) throws ListaDeMaestrosNulaException {
		if(maestros == null || maestros.size() == 0) {
			throw new ListaDeMaestrosNulaException("La lista de maestros no puede ser nula o estar vacia.");
		}
		
		HashMap<Afinidad, Integer> mapa = new HashMap<>();
		
		for (Maestro m : maestros) {
			for (ICriatura c : m.getCriaturas().values()) {
				Afinidad afinidad = c.getAfinidad();
				
				mapa.put(afinidad, mapa.getOrDefault(afinidad, 0) + 1);
			}
		}
		
		return mapa;
	} 
}