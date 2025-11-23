package ar.edu.unlam.pb2.CriaturasElementales;

import java.util.HashMap;

public class Maestro {
	private String nombre;
	private Integer nivelMaestria; // 1-50
	private Afinidad afinidad; // AGUA - FUEGO - AIRE - TIERRA
	private HashMap<String, ICriatura> criaturas;
	
	public Maestro(String nombre, Integer nivelMaestria, Afinidad afinidad) {
		this.nombre = nombre;
		if (nivelMaestria < 1 || nivelMaestria > 50) { this.nivelMaestria = 1; } else { this.nivelMaestria = nivelMaestria; }
		this.afinidad = afinidad;
		this.criaturas = new HashMap<>();
	}
	
	public String getNombre() { return this.nombre; }
	
	public void setNombre(String nombre) { this.nombre = nombre; }
	
	public Integer getNivelMaestria() { return this.nivelMaestria; }
	
	public void setNivelMaestria(Integer nivelMaestria) {		
		if(nivelMaestria < 1 || nivelMaestria > 50) {
			return;
		}
		
		this.nivelMaestria = nivelMaestria; 
	}
	
	public Afinidad getAfinidad() { return this.afinidad; }
	
	public void setAfinidad(Afinidad afinidad) { this.afinidad = afinidad; }
	
	public void agregarCriatura(ICriatura criatura) throws CriaturaNoExistenteException {
		if (criatura == null) {
			throw new CriaturaNoExistenteException("La criatura no existe.");
		}
		
		criaturas.put(criatura.getNombre(), criatura);
	}
	
	public HashMap<String, ICriatura> getCriaturas() {
		return this.criaturas;
	}
	
	private Boolean verificarNivelMaestriaSuficiente() {
		if (this.nivelMaestria < 20) {
			return false;
		}
		return true;
	}
	
	public void entrenarCriatura(ICriatura criatura, Integer energia) throws MaestriaInsuficienteException, CriaturaNoExistenteException, CriaturaACargoNoEncontradaException {
		if (!this.verificarNivelMaestriaSuficiente()) {
			throw new MaestriaInsuficienteException("El maestro no tiene suficiente maestria para entrenar.");
		}
		
		if (criatura == null) {
			throw new CriaturaNoExistenteException("La criatura no existe.");
		}
		
		ICriatura criaturaEncontrada = criaturas.get(criatura.getNombre());
		
		if (criaturaEncontrada == null) {
			throw new CriaturaACargoNoEncontradaException("La criatura no se encuentra a cargo del maestro.");
		}
		
		criaturaEncontrada.entrenar(energia);
	}
	
	public void pacificarCriatura(Criatura criatura) throws CriaturaNoExistenteException, CriaturaACargoNoEncontradaException {
		if (criatura == null) {
			throw new CriaturaNoExistenteException("La criatura no existe.");
		}
		
		ICriatura criaturaEncontrada = criaturas.get(criatura.getNombre());
		
		if (criaturaEncontrada == null) {
			throw new CriaturaACargoNoEncontradaException("La criatura no se encuentra a cargo del maestro.");
		}
		criaturaEncontrada.pacificar();
	}
	
	public ICriatura transformarCriatura(ICriatura criatura, Transformacion transformacion) throws CriaturaNoExistenteException, CriaturaACargoNoEncontradaException, TransformacionInvalidaException {
		if (criatura == null) {
			throw new CriaturaNoExistenteException("La criatura no existe");
		}
		
		String nombreCriatura = criatura.getNombre();
		
		if (!this.criaturas.containsKey(nombreCriatura)) {
			throw new CriaturaACargoNoEncontradaException("La criatura no se encuentra a cargo del maestro.");
		}
		
		ICriatura criaturaTransformada;
		
		switch(transformacion) {
			case BENDICION_DEL_RIO:
				criaturaTransformada = new BendicionDelRio(criatura);
				break;
			case LLAMA_INTERNA:
				criaturaTransformada = new LlamaInterna(criatura);
				break;
			case VINCULO_TERRESTRE:
				criaturaTransformada = new VinculoTerrestre(criatura);
				break;
			case ASCENSO_DEL_VIENTO:
				criaturaTransformada = new AscensoDelViento(criatura);
				break;
			default:
				throw new TransformacionInvalidaException("Ritual de transformacion no reconocido.");
		}
		
		// se reemplaza la criatura en el HashMap<String, Criatura>
		this.criaturas.put(nombreCriatura, criaturaTransformada);
		
		return criaturaTransformada;
	}
	
	public void interactuarCriaturas(Criatura c1, Criatura c2) throws CriaturaNoExistenteException, CriaturaACargoNoEncontradaException, CriaturasIgualesException {
		if (c1 == null || c2 == null) {
			throw new CriaturaNoExistenteException("Una o ambas criaturas no existen.");
		}
		
		if (!criaturas.containsKey(c1.getNombre()) || !criaturas.containsKey(c2.getNombre())) {
		    throw new CriaturaACargoNoEncontradaException("Ambas criaturas deben estar a cargo del maestro.");
		}
		
		if (c1.getNombre().equals(c2.getNombre())) {
			throw new CriaturasIgualesException("Una criatura no puede interactuar consigo misma.");
		}
		
		c1.interactuarCon(c2);
	}
}