package ar.edu.unlam.pb2.CriaturasElementales;

public class BendicionDelRio extends ConTransformacion {
	public BendicionDelRio(ICriatura criatura) {
		super(criatura);
	}
	
	// Duplica la energía, pero nunca supera 180
	@Override
	public Integer getEnergia() {
		Integer energiaBase = criaturaEnvuelta.getEnergia();
		
		if ((energiaBase * 2) > 180) {
			return 180;
		}
		return energiaBase * 2;
	}
}
