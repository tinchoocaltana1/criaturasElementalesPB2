package ar.edu.unlam.pb2.CriaturasElementales;

import static org.junit.Assert.*;

import org.junit.Test;

public class InteraccionesCriaturasTest {
	@Test
    public void queAmbasCriaturasAumentenDiezDeEnergiaSiTienenIgualAfinidad() {
        ICriatura agua1 = new Domesticada("A1", 100, Afinidad.AGUA);
        ICriatura agua2 = new Domesticada("A2", 100, Afinidad.AGUA);

        agua1.interactuarCon(agua2);

        assertEquals(110, agua1.getEnergia(), 1);
        assertEquals(110, agua2.getEnergia(), 1);
    }

    @Test
    public void queAmbasCriaturasSeVuelvanInestablesSiUnaEsDeFuegoYOtraDeAgua() {
        ICriatura fuego = new Salvaje("Fuego", 100, Afinidad.FUEGO);
        ICriatura agua = new Salvaje("Agua", 100, Afinidad.AGUA);

        fuego.interactuarCon(agua);

        assertEquals(true, fuego.esInestable());
        assertEquals(true, agua.esInestable());
    }
    
    @Test
    public void queAmbasCriaturasSeVuelvanInestablesSiUnaEsDeAireYOtraDeTierra() {
        ICriatura aire = new Salvaje("Aire", 100, Afinidad.AIRE);
        ICriatura tierra = new Salvaje("Tierra", 100, Afinidad.TIERRA);

        aire.interactuarCon(tierra);

        assertEquals(true, aire.esInestable());
        assertEquals(true, tierra.esInestable());
    }
    
    @Test
    public void queSiUnaCriaturaEsAncestralLeSubaVeninteDeEnergiaYALaOtraSeLeResteQuince() {
    	ICriatura ancestral = new Ancestral("Ancestral", 100, Afinidad.AGUA);
    	ICriatura domesticada = new Domesticada("Domesticada", 100, Afinidad.TIERRA);
    	
    	ancestral.interactuarCon(domesticada);
    	
    	assertEquals(120, ancestral.getEnergia(), 1);
    	assertEquals(85, domesticada.getEnergia(), 1);
    }
    
    @Test
    public void queSiUnaCriaturaEsAncestralEInteractuaConUnaCriaturaConEnergiaMenorAQuinceSuEnergiaPaseASerCero() {
    	ICriatura ancestral = new Ancestral("Ancestral", 100, Afinidad.AGUA);
    	ICriatura domesticada = new Domesticada("Domesticada", 10, Afinidad.TIERRA);
    	
    	ancestral.interactuarCon(domesticada);
    	
    	assertEquals(120, ancestral.getEnergia(), 1);
    	assertEquals(0, domesticada.getEnergia(), 1);
    }

    @Test
    public void queSiUnaCriaturaEsAncestralLeSubaVeninteDeEnergiaYALaOtraSeLeResteQuinceAlReves() {
    	ICriatura ancestral = new Ancestral("Ancestral", 100, Afinidad.AGUA);
    	ICriatura domesticada = new Domesticada("Domesticada", 100, Afinidad.TIERRA);
    	
    	domesticada.interactuarCon(ancestral);
    	
    	assertEquals(120, ancestral.getEnergia(), 1);
    	assertEquals(85, domesticada.getEnergia(), 1);
    }
    
    @Test
    public void queSiNoTienenAfinidadIgualNiOpuestaNiNingunaEsAncestralNoPaseNada() {
    	ICriatura c1 = new Domesticada("DomesticadaA", 100, Afinidad.AGUA);
    	ICriatura c2 = new Domesticada("DomesticadaB", 100, Afinidad.TIERRA);
    	
    	c1.interactuarCon(c2);
    	
    	assertEquals(100, c1.getEnergia(), 1);
    	assertEquals(100, c2.getEnergia(), 1);
    }
}
