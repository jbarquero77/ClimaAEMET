package modelo;

public class NodoDia {
    private String fecha;
    private int probabilidadLluvia;
    private String estadoCielo;
    private String direccionViento;
    private int velocidadViento;
    private int temperaturaMaxima;
    private int temperaturaMinima;
    private int humedadMaxima;
    private int humedadMinima;
    private int indiceUV;
	
    
    
    public NodoDia(String fecha, int probabilidadLluvia, String estadoCielo, String direccionViento,
			int velocidadViento, int temperaturaMaxima, int temperaturaMinima, int humedadMaxima, int humedadMinima,
			int indiceUV) {
		super();
		this.fecha = fecha;
		this.probabilidadLluvia = probabilidadLluvia;
		this.estadoCielo = estadoCielo;
		this.direccionViento = direccionViento;
		this.velocidadViento = velocidadViento;
		this.temperaturaMaxima = temperaturaMaxima;
		this.temperaturaMinima = temperaturaMinima;
		this.humedadMaxima = humedadMaxima;
		this.humedadMinima = humedadMinima;
		this.indiceUV = indiceUV;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getProbabilidadLluvia() {
		return probabilidadLluvia;
	}
	public void setProbabilidadLluvia(int probabilidadLluvia) {
		this.probabilidadLluvia = probabilidadLluvia;
	}
	public String getEstadoCielo() {
		return estadoCielo;
	}
	public void setEstadoCielo(String estadoCielo) {
		this.estadoCielo = estadoCielo;
	}
	public String getDireccionViento() {
		return direccionViento;
	}
	public void setDireccionViento(String direccionViento) {
		this.direccionViento = direccionViento;
	}
	public int getVelocidadViento() {
		return velocidadViento;
	}
	public void setVelocidadViento(int velocidadViento) {
		this.velocidadViento = velocidadViento;
	}
	public int getTemperaturaMaxima() {
		return temperaturaMaxima;
	}
	public void setTemperaturaMaxima(int temperaturaMaxima) {
		this.temperaturaMaxima = temperaturaMaxima;
	}
	public int getTemperaturaMinima() {
		return temperaturaMinima;
	}
	public void setTemperaturaMinima(int temperaturaMinima) {
		this.temperaturaMinima = temperaturaMinima;
	}
	public int getHumedadMaxima() {
		return humedadMaxima;
	}
	public void setHumedadMaxima(int humedadMaxima) {
		this.humedadMaxima = humedadMaxima;
	}
	public int getHumedadMinima() {
		return humedadMinima;
	}
	public void setHumedadMinima(int humedadMinima) {
		this.humedadMinima = humedadMinima;
	}
	public int getIndiceUV() {
		return indiceUV;
	}
	public void setIndiceUV(int indiceUV) {
		this.indiceUV = indiceUV;
	}
	@Override
	public String toString() {
		return "NodoDia [fecha=" + fecha + ", probabilidadLluvia=" + probabilidadLluvia + ", estadoCielo=" + estadoCielo
				+ ", direccionViento=" + direccionViento + ", velocidadViento=" + velocidadViento
				+ ", temperaturaMaxima=" + temperaturaMaxima + ", temperaturaMinima=" + temperaturaMinima
				+ ", humedadMaxima=" + humedadMaxima + ", humedadMinima=" + humedadMinima + ", indiceUV=" + indiceUV
				+ "]";
	}
    
    
}