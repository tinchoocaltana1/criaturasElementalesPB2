package ar.edu.unlam.pb2.CriaturasElementales;

public class LlamaInterna extends ConTransformacion {
	public LlamaInterna(ICriatura criatura) {
		super(criatura);
	}
	
	// Si la afinidad es fuego → +30 energía
	// Si no, la criatura se vuelve inestable
	@Override
	public Integer getEnergia() {
		Integer energiaBase = criaturaEnvuelta.getEnergia();
		Afinidad afinidad = criaturaEnvuelta.getAfinidad();
		
		if (afinidad == Afinidad.FUEGO) {
			if ((energiaBase + 30) <= 200) {
				return energiaBase + 30;
			}
		}
		return energiaBase;
	}
	
	@Override
	public Boolean esInestable() {
		Boolean emocionBase = criaturaEnvuelta.esInestable();
		Afinidad afinidad = criaturaEnvuelta.getAfinidad();
		
		if (afinidad != Afinidad.FUEGO) {
			return true;
		}
		return emocionBase;
	}
}
