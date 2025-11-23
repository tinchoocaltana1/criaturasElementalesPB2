package ar.edu.unlam.pb2.CriaturasElementales;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

public class ConsejoTest {

	@Test(expected = ListaDeMaestrosNulaException.class)
	public void alListarTodasLasCriaturasSiLaListaDeMaestrosEsNulaDebeLanzarExcepcion() throws Exception {
		ConsejoDeElandria.listarTodasLasCriaturas(null);
	}
	
	@Test(expected = ListaDeMaestrosNulaException.class)
	public void alListarTodasLasCriaturasSiLaListaDeMaestrosEstaVaciaDebeLanzarExcepcion() throws Exception {		
		ConsejoDeElandria.listarTodasLasCriaturas(new ArrayList<>());
	}
	
	@Test
	public void queSeListenTodasLasCriaturasDeTodosLosMaestros() throws Exception {
		HashMap<String, ICriatura> criaturas = new HashMap<>();
		List<Maestro> maestros = new ArrayList<>();
		
		Maestro m = new Maestro("M", 10, Afinidad.AIRE);
		ICriatura c = new Salvaje("C", 100, Afinidad.AGUA);
		m.agregarCriatura(c);
		
		Maestro p = new Maestro("P", 20, Afinidad.AIRE);
		ICriatura d = new Salvaje("D", 100, Afinidad.AGUA);
		p.agregarCriatura(d);
		
		maestros.add(m);
		maestros.add(p);
		criaturas.put("C", c);
		criaturas.put("D", d);
		
		assertEquals(criaturas, ConsejoDeElandria.listarTodasLasCriaturas(maestros));
	}
	
	@Test(expected = ListaDeMaestrosNulaException.class)
	public void alBuscarLaCriaturaConMayorEnergiaSiLaListaDeMaestrosEsNulaDebeLanzarExcepcion() throws Exception {
		ConsejoDeElandria.obtenerCriaturaConMayorEnergiaTotal(null);
	}
	
	@Test(expected = ListaDeMaestrosNulaException.class)
	public void alBuscarLaCriaturaConMayorEnergiaSiLaListaDeMaestrosEstaVaciaDebeLanzarExcepcion() throws Exception {		
		ConsejoDeElandria.obtenerCriaturaConMayorEnergiaTotal(new ArrayList<>());
	}
	
	@Test
	public void queSeObtengaLaCriaturaConMayorEnergiaEntreTodosLosMaestros() throws Exception {
		HashMap<String, ICriatura> criaturas = new HashMap<>();
		List<Maestro> maestros = new ArrayList<>();
		
		Maestro m = new Maestro("M", 10, Afinidad.AIRE);
		ICriatura c = new Salvaje("C", 150, Afinidad.AGUA);
		m.agregarCriatura(c);
		
		Maestro p = new Maestro("P", 20, Afinidad.AIRE);
		ICriatura d = new Salvaje("D", 100, Afinidad.AGUA);
		p.agregarCriatura(d);
		
		maestros.add(m);
		maestros.add(p);
		criaturas.put("C", c);
		criaturas.put("D", d);
		
		assertEquals(c, ConsejoDeElandria.obtenerCriaturaConMayorEnergiaTotal(maestros));
	}

	@Test(expected = ListaDeMaestrosNulaException.class)
	public void alBuscarElMaestroConMasCriaturasTransformadasSiLaListaDeMaestrosEsNulaDebeLanzarExcepcion() throws Exception {
		ConsejoDeElandria.obtenerMaestroConMasCriaturasTransformadas(null);
	}
	
	@Test(expected = ListaDeMaestrosNulaException.class)
	public void alBuscarElMaestroConMasCriaturasTransformadasSiLaListaDeMaestrosEstaVaciaDebeLanzarExcepcion() throws Exception {		
		ConsejoDeElandria.obtenerMaestroConMasCriaturasTransformadas(new ArrayList<>());
	}
	
	@Test
	public void queSeObtengaElMaestroConMasCriaturasTransformadas() throws Exception {
		HashMap<String, ICriatura> criaturas = new HashMap<>();
		List<Maestro> maestros = new ArrayList<>();
		
		Maestro m = new Maestro("M", 10, Afinidad.AIRE);
		ICriatura c = new Salvaje("C", 150, Afinidad.AGUA);
		m.agregarCriatura(c);
		
		Maestro p = new Maestro("P", 20, Afinidad.AIRE);
		ICriatura d = new Salvaje("D", 100, Afinidad.AGUA);
		p.agregarCriatura(d);
		p.transformarCriatura(d, Transformacion.BENDICION_DEL_RIO);
		
		maestros.add(m);
		maestros.add(p);
		criaturas.put("C", c);
		criaturas.put("D", d);
		
		assertEquals(p, ConsejoDeElandria.obtenerMaestroConMasCriaturasTransformadas(maestros));
	}
	
	@Test(expected = ListaDeMaestrosNulaException.class)
	public void alBuscarLaCantidadDeCriaturasPorAfinidadSiLaListaDeMaestrosEsNulaDebeLanzarExcepcion() throws Exception {
		ConsejoDeElandria.obtenerCantidadDeCriaturasPorAfinidad(null);
	}
	
	@Test(expected = ListaDeMaestrosNulaException.class)
	public void alBuscarLaCantidadDeCriaturasPorAfinidadSiLaListaDeMaestrosEstaVaciaDebeLanzarExcepcion() throws Exception {		
		ConsejoDeElandria.obtenerCantidadDeCriaturasPorAfinidad(new ArrayList<>());
	}
	
	@Test
	public void queSeObtengaLaCantidadDeCriaturasPorAfinidad() throws Exception {
		List<Maestro> maestros = new ArrayList<>();
		
		Maestro m = new Maestro("M", 10, Afinidad.AIRE);
		ICriatura c = new Salvaje("C", 150, Afinidad.AGUA);
		ICriatura c1 = new Salvaje("C1", 150, Afinidad.AGUA);
		m.agregarCriatura(c);
		m.agregarCriatura(c1);
		
		Maestro p = new Maestro("P", 20, Afinidad.AIRE);
		ICriatura d = new Salvaje("D", 100, Afinidad.FUEGO);
		ICriatura d1 = new Salvaje("D1", 100, Afinidad.AIRE);
		p.agregarCriatura(d);
		p.agregarCriatura(d1);
		
		maestros.add(m);
		maestros.add(p);
		
		HashMap<Afinidad, Integer> mapa = new HashMap<>();
		mapa.put(Afinidad.AGUA, 2);
		mapa.put(Afinidad.FUEGO, 1);
		mapa.put(Afinidad.AIRE, 1);
		
		assertEquals(mapa, ConsejoDeElandria.obtenerCantidadDeCriaturasPorAfinidad(maestros));
	}
}
