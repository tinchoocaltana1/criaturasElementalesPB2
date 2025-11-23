package ar.edu.unlam.pb2.CriaturasElementales;

public abstract class ConTransformacion implements ICriatura {
	protected ICriatura criaturaEnvuelta;
	
	public ConTransformacion(ICriatura criaturaEnvuelta) {
		this.criaturaEnvuelta = criaturaEnvuelta;
	}
	
	@Override
    public String getNombre() { 
        return criaturaEnvuelta.getNombre();
    }

	@Override
    public void setNombre(String nombre) {
        criaturaEnvuelta.setNombre(nombre);
    }

    @Override
    public Integer getEnergia() {
        return criaturaEnvuelta.getEnergia();
    }
    
    @Override
	public void setEnergia(Integer energia) { 
		criaturaEnvuelta.setEnergia(energia);
	}

    @Override
    public Afinidad getAfinidad() {
        return criaturaEnvuelta.getAfinidad();
    }

    @Override
    public void setAfinidad(Afinidad afinidad) {
        criaturaEnvuelta.setAfinidad(afinidad);
    }

    @Override
    public Boolean esInestable() {
        return criaturaEnvuelta.esInestable();
    }

    @Override
    public void pacificar() {
        criaturaEnvuelta.pacificar();
    }

    @Override
    public void inestabilizar() {
        criaturaEnvuelta.inestabilizar();
    }

    @Override
    public void entrenar(Integer energia) {
        criaturaEnvuelta.entrenar(energia);
    }

    @Override
    public void decrementarEnergia(Integer energia) {
        criaturaEnvuelta.decrementarEnergia(energia);
    }
    
    @Override
    public void interactuarCon(ICriatura otra) {
    	criaturaEnvuelta.interactuarCon(otra);
    }
}
