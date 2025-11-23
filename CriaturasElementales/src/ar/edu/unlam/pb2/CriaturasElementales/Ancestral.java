package ar.edu.unlam.pb2.CriaturasElementales;

public class Ancestral extends Criatura {
	// su energía nunca puede bajar de 100
	// son extremadamente poderosas, pero sensibles a entrenamientos extremos
	
	public Ancestral(String nombre, Integer energia, Afinidad afinidad) {
		super(nombre, Math.max(energia, 100), afinidad);
	}
	
	@Override
	public void decrementarEnergia(Integer energia) {
		if ((this.energia - energia) < 100) {
			this.energia = 100;
		} else {
			this.energia -= energia;
		}
	}
	
	@Override
	public void entrenar(Integer energia) {
		this.energia += energia;
		if(this.energia > 200) {
			this.energia = 200;
		}
		
		if (energia > 100) {
			this.inestabilizar();
		}
	}
	
	// si la criatura que interactúa es ancestral, gana 20 de energía y la otra pierde 15 
	public void interactuarComoAncestral(ICriatura otra) {
		Integer energiaActual = this.getEnergia();
		Integer nuevaEnergiaActual = energiaActual + 20;
		
		Integer energiaOtra = otra.getEnergia();
		Integer nuevaEnergiaOtra = energiaOtra - 15;
		
		this.setEnergia(nuevaEnergiaActual);
		
		if (nuevaEnergiaOtra < 0) {
			otra.setEnergia(0);
		} else {
			otra.setEnergia(nuevaEnergiaOtra);
		}
	}
}