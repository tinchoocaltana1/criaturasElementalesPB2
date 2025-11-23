package ar.edu.unlam.pb2.CriaturasElementales;

import static org.junit.Assert.*;

import org.junit.Test;

public class CriaturaTest {
	@Test
	public void dadoQueCreeUnaCriaturaCuandoConsultoSusAtributosSeInicializanCorrectamente() {
		Criatura c = new Salvaje("Rex", 120, Afinidad.AGUA);

		assertEquals("Rex", c.getNombre());
		assertEquals((Integer)120, c.getEnergia());
		assertEquals(Afinidad.AGUA, c.getAfinidad());
		assertFalse(c.esInestable());
	}
	
	@Test
	public void queElSetNombreCambieElNombreCorrectamente() {
		Criatura c = new Salvaje("Rex", 120, Afinidad.AGUA);
		
		c.setNombre("Pepe");
		
		assertEquals("Pepe", c.getNombre());
	}
	
	@Test
	public void queElSetAfinidadCambieLaAfinidadCorrectamente() {
		Criatura c = new Salvaje("Rex", 100, Afinidad.AGUA);
		
		c.setAfinidad(Afinidad.TIERRA);
		
		assertEquals(Afinidad.TIERRA, c.getAfinidad());
	}
	
	@Test(expected = EnergiaInvalidaException.class)
	public void dadoQueSeteoEnergiaNegativaQueLanceEnergiaInvalidaException() {
	    Criatura c = new Salvaje("Rex", 100, Afinidad.AGUA);

	    c.setEnergia(-20);
	}
	
	@Test
	public void queElSetEnergiaPongaDoscientosComoEnergiaSiRecibeUnParametroMayorDelLimite() {
		Criatura c = new Salvaje("Rex", 100, Afinidad.AGUA);
		c.setEnergia(210);
		assertEquals(200, c.getEnergia(), 1);
	}
	
	@Test
	public void queElSetEnergiaActualiceCorrectamenteLaEnergia() {
		Criatura c = new Salvaje("Rex", 100, Afinidad.AGUA);
		c.setEnergia(50);
		assertEquals(50, c.getEnergia(), 1);
	}
	
	@Test
	public void dadoQuePacifiqueUnaCriaturaInestableDeberiaQuedarEstable() {
		Criatura c = new Salvaje("Rex", 120, Afinidad.AGUA);
		c.inestabilizar();
		assertTrue(c.esInestable());

		c.pacificar();
		assertFalse(c.esInestable());
	}

	@Test
	public void dadoQueInestabiliceUnaCriaturaDeberiaQuedarInestable() {
		Criatura c = new Salvaje("Rex", 120, Afinidad.AGUA);
		c.inestabilizar();
		assertTrue(c.esInestable());
	}

	@Test
	public void dadoQueEntreneUnaCriaturaSeAumentaSuEnergiaCorrectamente() {
		Criatura c = new Domesticada("Rex", 20, Afinidad.AGUA);
		c.entrenar(50); // 20 inicial + 50 de entrenamiento = 70 total
		assertEquals(70, c.getEnergia(), 1);
	}
	
	@Test
	public void dadoQueEntreneUnaCriaturaNoDeberiaSuperarLaEnergiaMaxima() {
		Criatura c = new Domesticada("Rex", 190, Afinidad.AGUA);
		c.entrenar(50);
		assertEquals(200, c.getEnergia(), 1);
	}

	@Test
	public void dadoQueDecrementeLaEnergiaSiQuedaNegativaDebeAjustarseACero() {
		Criatura c = new Salvaje("Rex", 30, Afinidad.AGUA);
		c.decrementarEnergia(100);
		assertEquals(0, c.getEnergia(), 1);
	}

	@Test
	public void dadoQueDosAfinidadesSonOpuestasDeberiaDetectarseCorrectamente() {
		Criatura c = new Salvaje("Rex", 50, Afinidad.AGUA);

		assertTrue(c.sonOpuestas(Afinidad.AGUA, Afinidad.FUEGO));
		assertTrue(c.sonOpuestas(Afinidad.FUEGO, Afinidad.AGUA));
		assertTrue(c.sonOpuestas(Afinidad.AIRE, Afinidad.TIERRA));
		assertTrue(c.sonOpuestas(Afinidad.TIERRA, Afinidad.AIRE));

		assertFalse(c.sonOpuestas(Afinidad.AGUA, Afinidad.AGUA));
		assertFalse(c.sonOpuestas(Afinidad.AGUA, Afinidad.TIERRA));
	}
	
	@Test
	public void dadoQueSeCreaUnaCriaturaDomesticadaNoSePuedeInestabilizar() {
		Criatura c = new Domesticada("Domesticada", 100, Afinidad.AGUA);
		c.inestabilizar();
		
		assertFalse(c.esInestable());
	}
	
	@Test
	public void queAlEntrenarUnaCriaturaSalvajeSiempreSubaSuEnergia() {
		Criatura c = new Salvaje("Salvaje", 100, Afinidad.AGUA);
		
		c.entrenar(50);
		
		assertTrue(c.getEnergia() > 100);
	}
	
	@Test
	public void queAlDecrementarLaEnergiaDeUnaCriaturaAncestralNoBajeDeCien() {
		Criatura c = new Ancestral("Ancestral", 150, Afinidad.AGUA);
		
		c.decrementarEnergia(70);
		assertEquals(100, c.getEnergia(), 1);
	}
	
	@Test
	public void queDespuesDeDecrementarLaEnergiaDeUnaCriaturaAncestralSeActualiceBienLaEnergia() {
		Criatura c = new Ancestral("Ancestral", 150, Afinidad.AGUA);
		
		c.decrementarEnergia(20);
		assertEquals(130, c.getEnergia(), 1);
	}
	
	@Test
	public void queAlEntrenarUnaCriaturaAncestralLaEnergiaNoPaseDeDoscientos() {
		Criatura c = new Ancestral("Ancestral", 150, Afinidad.AGUA);
		
		c.entrenar(70);
		assertEquals(200, c.getEnergia(), 1);
	}
	
	@Test
	public void queAlEntrenarUnaCriaturaAncestralSiLaEnergiaAAumentarEsMayorACienSeVuelvaInestable() {
		Criatura c = new Ancestral("Ancestral", 110, Afinidad.AGUA);
		
		c.entrenar(101);
		assertTrue(c.esInestable());
	}
	
}
