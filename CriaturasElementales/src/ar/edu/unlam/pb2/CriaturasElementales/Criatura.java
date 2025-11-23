package ar.edu.unlam.pb2.CriaturasElementales;

public abstract class Criatura implements ICriatura {
	protected String nombre;
	protected Integer energia; // 0-200
	protected Afinidad afinidad; // AGUA - FUEGO - AIRE - TIERRA
	protected Boolean esInestable; // true - false

	protected Criatura(String nombre, Integer energia, Afinidad afinidad) {
		this.nombre = nombre;
		this.energia = energia;
		this.afinidad = afinidad;
		this.esInestable = false;
	}

	@Override
	public Boolean esInestable() { return this.esInestable; }

	@Override
	public void pacificar() { this.esInestable = false; }
	
	@Override
	public void inestabilizar() { this.esInestable = true; }

	@Override
	public String getNombre() { return nombre; }
	
	@Override
	public void setNombre(String nombre) { this.nombre = nombre; }

	@Override
	public Integer getEnergia() { return energia; }

	@Override
	public void setEnergia(Integer energia) { 
		if (energia < 0) {
			return;
		} else if (energia > 200) {
			this.energia = 200;
			return;
		}
		
		this.energia = energia;
	}
	
	@Override
	public Afinidad getAfinidad() { return afinidad; }

	@Override
	public void setAfinidad(Afinidad afinidad) { this.afinidad = afinidad; }

	@Override
	public void decrementarEnergia(Integer energia) {
		this.energia -= energia;
		if(this.energia < 0) {
			this.energia = 0;
		}
	}

	@Override
	public void entrenar(Integer energia) {
		this.energia += energia;
		if(this.energia > 200) {
			this.energia = 200;
		}
	}
	
	protected boolean sonOpuestas(Afinidad a, Afinidad b) {
	    return (a == Afinidad.AGUA && b == Afinidad.FUEGO) ||
	           (a == Afinidad.FUEGO && b == Afinidad.AGUA ) ||
	           (a == Afinidad.AIRE && b == Afinidad.TIERRA) ||
	           (a == Afinidad.TIERRA && b == Afinidad.AIRE);
	}
	
	@Override
	public void interactuarCon(ICriatura otra) {
		// si alguna es ancestral, delego en la ancestral
	    if (otra instanceof Ancestral || this instanceof Ancestral) {
	        // la ancestral domina, así que dejo que la ancestral decida
	        if (this instanceof Ancestral) {
	            ((Ancestral) this).interactuarComoAncestral(otra); // si la actual es ancestral
	        } else {
	            ((Ancestral) otra).interactuarComoAncestral(this); // si la otra es ancestral
	        }
	        return;
	    }

	    Afinidad a1 = getAfinidad();
	    Afinidad a2 = otra.getAfinidad();

	    if (a1 == a2) {
	    	Integer energiaActual = this.getEnergia();
	    	Integer nuevaEnergiaActual = energiaActual + 10;
	    	
	    	Integer energiaOtra = otra.getEnergia();
	    	Integer nuevaEnergiaOtra = energiaOtra + 10;
	    	
	        this.setEnergia(nuevaEnergiaActual);
	        otra.setEnergia(nuevaEnergiaOtra);
	        return;
	    }

	    // si las afinidades son opuestas, se inestablizan ambas criaturas
	    if (sonOpuestas(a1, a2)) {
	        this.inestabilizar();
	        otra.inestabilizar();
	        return;
	    }
	}
}