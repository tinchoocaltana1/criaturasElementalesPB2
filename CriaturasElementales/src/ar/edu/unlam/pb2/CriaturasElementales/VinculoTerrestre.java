package ar.edu.unlam.pb2.CriaturasElementales;

public class VinculoTerrestre extends ConTransformacion {
	public VinculoTerrestre(ICriatura criatura) {
		super(criatura);
	}

	//Garantiza que nunca quede con energía menor a 50.
	@Override
	public Integer getEnergia() {
		Integer energiaBase = criaturaEnvuelta.getEnergia();
		
		if (energiaBase < 50) {
			return 50;
		}
		return energiaBase;
	}
}
