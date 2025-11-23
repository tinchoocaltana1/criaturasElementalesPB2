package ar.edu.unlam.pb2.CriaturasElementales;

import static org.junit.Assert.*;

import org.junit.Test;

public class MaestroTest {

	@Test
	public void dadoQueCreoUnMaestroSusAtributosSeInicializanCorrectamente() {
		Maestro m = new Maestro("Aang", 25, Afinidad.AIRE);

		assertEquals("Aang", m.getNombre());
		assertEquals((Integer)25, m.getNivelMaestria());
		assertEquals(Afinidad.AIRE, m.getAfinidad());
		assertTrue(m.getCriaturas().isEmpty());
	}

	@Test
	public void queElSetNombreCambieCorrectamente() {
		Maestro m = new Maestro("Aang", 25, Afinidad.AIRE);
		m.setNombre("Juan");

		assertEquals("Juan", m.getNombre());
	}

	@Test
	public void queElSetAfinidadCambieCorrectamente() {
		Maestro m = new Maestro("Aang", 25, Afinidad.AIRE);
		m.setAfinidad(Afinidad.AGUA);

		assertEquals(Afinidad.AGUA, m.getAfinidad());
	}

	@Test
	public void queElSetNivelMaestriaNoAcepteValoresFueraDeRango() {
		Maestro m = new Maestro("Aang", 25, Afinidad.AIRE);

		m.setNivelMaestria(0);
		assertEquals((Integer)25, m.getNivelMaestria());

		m.setNivelMaestria(60);
		assertEquals((Integer)25, m.getNivelMaestria());
	}

	@Test
	public void queElSetNivelMaestriaActualiceCorrectamente() {
		Maestro m = new Maestro("Aang", 25, Afinidad.AIRE);

		m.setNivelMaestria(40);
		assertEquals((Integer)40, m.getNivelMaestria());
	}

	@Test(expected = CriaturaNoExistenteException.class)
	public void dadoQueQuieraAgregarUnaCriaturaNullDebeLanzarExcepcion() throws Exception {
		Maestro m = new Maestro("Aang", 25, Afinidad.AIRE);
		ICriatura c = null;
		m.agregarCriatura(c);
	}

	@Test
	public void queAgregarUnaCriaturaFuncioneCorrectamente() throws Exception {
		Maestro m = new Maestro("Aang", 25, Afinidad.AIRE);
		ICriatura c = new Salvaje("Rex", 100, Afinidad.AGUA);

		m.agregarCriatura(c);

		assertTrue(m.getCriaturas().containsKey("Rex"));
		assertEquals(c, m.getCriaturas().get("Rex"));
	}

	@Test(expected = MaestriaInsuficienteException.class)
	public void dadoQueLaMaestriaEsInsuficienteNoDebeEntrenar() throws Exception {
		Maestro m = new Maestro("Aang", 10, Afinidad.AIRE);
		ICriatura c = new Salvaje("Rex", 100, Afinidad.AGUA);

		m.agregarCriatura(c);
		m.entrenarCriatura(c, 20);
	}

	@Test(expected = CriaturaNoExistenteException.class)
	public void entrenarCriaturaNullDebeLanzarExcepcion() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		m.entrenarCriatura(null, 10);
	}

	@Test(expected = CriaturaACargoNoEncontradaException.class)
	public void entrenarCriaturaNoACargoDebeFallar() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		ICriatura c = new Salvaje("Rex", 100, Afinidad.AGUA);

		m.entrenarCriatura(c, 20);
	}

	@Test
	public void queEntrenarUnaCriaturaDeMaestroLaHagaSubirEnergia() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		ICriatura c = new Domesticada("Pipo", 50, Afinidad.FUEGO);

		m.agregarCriatura(c);
		m.entrenarCriatura(c, 40);

		assertEquals((Integer)90, c.getEnergia());
	}

	@Test(expected = CriaturaNoExistenteException.class)
	public void pacificarCriaturaNullDebeLanzarExcepcion() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		m.pacificarCriatura(null);
	}

	@Test(expected = CriaturaACargoNoEncontradaException.class)
	public void pacificarCriaturaNoACargoDebeFallar() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		Criatura c = new Salvaje("Rex", 120, Afinidad.AGUA);

		m.pacificarCriatura(c);
	}

	@Test
	public void quePacificarCriaturaFuncionCorrectamente() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		Criatura c = new Salvaje("Rex", 120, Afinidad.AGUA);

		c.inestabilizar();
		assertTrue(c.esInestable());

		m.agregarCriatura(c);
		m.pacificarCriatura(c);

		assertFalse(c.esInestable());
	}

	@Test(expected = CriaturaNoExistenteException.class)
	public void transformarCriaturaNullDebeLanzarExcepcion() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		m.transformarCriatura(null, Transformacion.BENDICION_DEL_RIO);
	}

	@Test(expected = CriaturaACargoNoEncontradaException.class)
	public void transformarCriaturaNoACargoDebeLanzarExcepcion() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		ICriatura c = new Salvaje("Rex", 120, Afinidad.AGUA);

		m.transformarCriatura(c, Transformacion.LLAMA_INTERNA);
	}

	@Test
	public void queTransformarConBendicionDelRioFuncioneCorrectamente() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		ICriatura c = new Salvaje("Rex", 70, Afinidad.AGUA);

		m.agregarCriatura(c);

		ICriatura t = m.transformarCriatura(c, Transformacion.BENDICION_DEL_RIO);

		assertTrue(t instanceof BendicionDelRio);
		assertEquals((Integer)140, t.getEnergia());
	}

	@Test
	public void queTransformarConVinculoTerrestreFuncioneCorrectamente() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		ICriatura c = new Salvaje("Rex", 30, Afinidad.TIERRA);

		m.agregarCriatura(c);

		ICriatura t = m.transformarCriatura(c, Transformacion.VINCULO_TERRESTRE);

		assertTrue(t instanceof VinculoTerrestre);
		assertEquals((Integer)50, t.getEnergia());
	}

	@Test
	public void queTransformarConAscensoDelVientoFuncioneCorrectamente() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		ICriatura c = new Salvaje("Rex", 80, Afinidad.FUEGO);

		m.agregarCriatura(c);

		ICriatura t = m.transformarCriatura(c, Transformacion.ASCENSO_DEL_VIENTO);

		assertTrue(t instanceof AscensoDelViento);
		assertEquals(Afinidad.AIRE, t.getAfinidad());
	}

	@Test
	public void queTransformarConLlamaInternaAumenteEnergiaSiEsFuego() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		ICriatura c = new Domesticada("Rex", 150, Afinidad.FUEGO);

		m.agregarCriatura(c);

		ICriatura t = m.transformarCriatura(c, Transformacion.LLAMA_INTERNA);

		assertTrue(t instanceof LlamaInterna);
		assertEquals((Integer)180, t.getEnergia());
	}

	@Test
	public void queTransformarConLlamaInternaVuelvaInestableSiNoEsFuego() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		ICriatura c = new Domesticada("Rex", 100, Afinidad.AGUA);

		m.agregarCriatura(c);

		ICriatura t = m.transformarCriatura(c, Transformacion.LLAMA_INTERNA);

		assertTrue(t.esInestable());
	}
	
	@Test
	public void queUnaCriaturaTransformadaConBendicionDelRioDevuelvaEnergiaCientoOchentaSiLaDuplicacionDeSuEnergiaInicialEraSuperiorAlLimite() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		ICriatura c = new Domesticada("Rex", 100, Afinidad.AGUA);

		m.agregarCriatura(c);

		ICriatura t = m.transformarCriatura(c, Transformacion.BENDICION_DEL_RIO);

		assertEquals(180, t.getEnergia(), 1);
	}
	
	@Test
	public void queUnaCriaturaTransformadaConLlamaInternaDevuelvaNormalmenteSuEnergiaSiSuAfinidadNoEsFuego() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		ICriatura c = new Domesticada("Rex", 100, Afinidad.AGUA);

		m.agregarCriatura(c);

		ICriatura t = m.transformarCriatura(c, Transformacion.LLAMA_INTERNA);

		assertEquals(100, t.getEnergia(), 1);
	}
	
	@Test
	public void queUnaCriaturaTransformadaConLlamaInternaNoSeInestabiliceSiSuAfinidadEsFuego() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		ICriatura c = new Domesticada("Rex", 100, Afinidad.FUEGO);

		m.agregarCriatura(c);

		ICriatura t = m.transformarCriatura(c, Transformacion.LLAMA_INTERNA);

		assertFalse(t.esInestable());
	}
	
	@Test
	public void queUnaCriaturaTransformadaConVinculoTerrestreDevuelvaNormalmenteSuEnergiaSiNoEsMenorACincuenta() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		ICriatura c = new Domesticada("Rex", 100, Afinidad.AGUA);

		m.agregarCriatura(c);

		ICriatura t = m.transformarCriatura(c, Transformacion.VINCULO_TERRESTRE);

		assertEquals(100, t.getEnergia(), 1);
	}

	@Test(expected = TransformacionInvalidaException.class)
	public void enviarUnaTransformacionInvalidaDebeLanzarExcepcion() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		ICriatura c = new Domesticada("Rex", 100, Afinidad.AGUA);

		m.agregarCriatura(c);

		m.transformarCriatura(c, Transformacion.INVALIDA);
	}
	
	@Test(expected = CriaturaNoExistenteException.class)
	public void interactuarCriaturasConNullDebeLanzarExcepcion() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		Criatura c = new Salvaje("Rex", 100, Afinidad.AGUA);

		m.agregarCriatura(c);
		m.interactuarCriaturas(c, null);
	}

	@Test(expected = CriaturaACargoNoEncontradaException.class)
	public void interactuarCriaturasNoACargoDebeFallar() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		Criatura c1 = new Salvaje("A", 100, Afinidad.AGUA);
		Criatura c2 = new Salvaje("B", 100, Afinidad.FUEGO);

		m.agregarCriatura(c1);

		m.interactuarCriaturas(c1, c2);
	}

	@Test(expected = CriaturasIgualesException.class)
	public void interactuarConMismaCriaturaDebeFallar() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);
		Criatura c1 = new Salvaje("A", 100, Afinidad.AGUA);

		m.agregarCriatura(c1);

		m.interactuarCriaturas(c1, c1);
	}

	@Test
	public void queDosCriaturasInteractuenCorrectamente() throws Exception {
		Maestro m = new Maestro("Aang", 30, Afinidad.AIRE);

		Criatura c1 = new Salvaje("A", 100, Afinidad.AGUA);
		Criatura c2 = new Salvaje("B", 100, Afinidad.AGUA);

		m.agregarCriatura(c1);
		m.agregarCriatura(c2);

		m.interactuarCriaturas(c1, c2);

		assertTrue(c1.getEnergia() > 100);
		assertTrue(c2.getEnergia() > 100);
	}
}