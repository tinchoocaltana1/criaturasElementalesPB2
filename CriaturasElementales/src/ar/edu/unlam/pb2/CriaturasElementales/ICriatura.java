package ar.edu.unlam.pb2.CriaturasElementales;

public interface ICriatura {
	public Boolean esInestable();
	public void pacificar();
	public void inestabilizar();
	public String getNombre();
	public void setNombre(String nombre);
	public Integer getEnergia();
	public void setEnergia(Integer energia);
	public Afinidad getAfinidad();
	public void setAfinidad(Afinidad afinidad);
	public void decrementarEnergia(Integer energia);
	public void entrenar(Integer energia);
	public void interactuarCon(ICriatura otra);
}
