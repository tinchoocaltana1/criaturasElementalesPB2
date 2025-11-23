package ar.edu.unlam.pb2.CriaturasElementales;

public class Domesticada extends Criatura {
	// aumentan su energia de forma estable
	// nunca se vuelven inestables
	
	public Domesticada(String nombre, Integer energia, Afinidad afinidad) {
		super(nombre, energia, afinidad);
	}
	
	@Override
	public void inestabilizar() {
		return;
	}
}
