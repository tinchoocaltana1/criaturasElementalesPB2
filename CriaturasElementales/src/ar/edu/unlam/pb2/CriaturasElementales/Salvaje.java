package ar.edu.unlam.pb2.CriaturasElementales;

import java.util.Random;

public class Salvaje extends Criatura {
	// mas dificiles de controlar
	// cuando se intenta entrenarlas, no se sabe cuánta energía de la indicada se va a incrementar
	
	public Salvaje(String nombre, Integer energia, Afinidad afinidad) {
		super(nombre, energia, afinidad);
	}
	
	@Override
	public void entrenar(Integer energia) {
		Random rand = new Random();
		Integer energiaImpredecible = rand.nextInt(energia + 1);
		
		this.energia += energiaImpredecible;
		
		if(this.energia > 200) {
			this.energia = 200;
		}
	}	
}
