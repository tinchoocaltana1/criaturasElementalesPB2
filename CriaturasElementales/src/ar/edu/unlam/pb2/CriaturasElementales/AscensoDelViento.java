package ar.edu.unlam.pb2.CriaturasElementales;

public class AscensoDelViento extends ConTransformacion {
	public AscensoDelViento(ICriatura criatura) {
		super(criatura);
	}
	
	//Convierte temporalmente al tipo AIRE, sin perder afinidades previas.
	@Override
	public Afinidad getAfinidad() {
		return Afinidad.AIRE;
	}
}
