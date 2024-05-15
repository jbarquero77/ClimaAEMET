package modelo;

import java.util.Date;
import java.util.HashMap;

import org.w3c.dom.NodeList;

public class Prediccion {
	private String fecha;
	private String hora;
	private HashMap<String, String> provinciasMap;
	private HashMap<String, String> municipiosMap;
	// private HashMap<String, String> comunidadesAutonomasMap;
	private String localidad;

	private String imagenesClima;
	private String temperaturaActual;
	private String tempMax;
	private String tempMin;

	private String direccionViento;
	private String velocidadViento;
	private String uv;
	private String humedad;
	private String probPrecipitacion;

	public Prediccion(String fecha, String hora, HashMap<String, String> provinciasMap,
			HashMap<String, String> municipiosMap, String localidad, String imagenesClima, String temperaturaActual,
			String tempMax, String tempMin, String direccionViento, String velocidadViento, String uv, String humedad,
			String probPrecipitacion) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.provinciasMap = new HashMap<>();
		this.municipiosMap = new HashMap<>();
		;
		// this.comunidadesAutonomasMap = new HashMap<>();
		this.localidad = localidad;
		this.imagenesClima = imagenesClima;
		this.temperaturaActual = temperaturaActual;
		this.tempMax = tempMax;
		this.tempMin = tempMin;
		this.direccionViento = direccionViento;
		this.velocidadViento = velocidadViento;
		this.uv = uv;
		this.humedad = humedad;
		this.probPrecipitacion = probPrecipitacion;
	}

	/*
	 * Constructor del ParseoProfe, prueba public Prediccion(String fechaFormateada,
	 * String horaFormateada, HashMap<String, String> mapProvincia, HashMap<String,
	 * String> mapMunicipio, String nombreLocalidad, NodeList listaNodosDias) {
	 * 
	 * }
	 */

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public HashMap<String, String> getProvinciasMap() {
		return provinciasMap;
	}

	public void setProvinciasMap(HashMap<String, String> provinciasMap) {
		this.provinciasMap = provinciasMap;
	}

	public HashMap<String, String> getMunicipiosMap() {
		return municipiosMap;
	}

	public void setMunicipiosMap(HashMap<String, String> municipiosMap) {
		this.municipiosMap = municipiosMap;
	}
	/*
	 * public HashMap<String, String> getComunidadesAutonomasMap() { return
	 * comunidadesAutonomasMap; } public void
	 * setComunidadesAutonomasMap(HashMap<String, String> comunidadesAutonomasMap) {
	 * this.comunidadesAutonomasMap = comunidadesAutonomasMap; }
	 */

	public String getImagenesClima() {
		return imagenesClima;
	}

	public void setImagenesClima(String imagenesClima) {
		this.imagenesClima = imagenesClima;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getTemperaturaActual() {
		return temperaturaActual;
	}

	public void setTemperaturaActual(String temperaturaActual) {
		this.temperaturaActual = temperaturaActual;
	}

	public String getTempMax() {
		return tempMax;
	}

	public void setTempMax(String tempMax) {
		this.tempMax = tempMax;
	}

	public String getTempMin() {
		return tempMin;
	}

	public void setTempMin(String tempMin) {
		this.tempMin = tempMin;
	}

	public String getDireccionViento() {
		return direccionViento;
	}

	public void setDireccionViento(String direccionViento) {
		this.direccionViento = direccionViento;
	}

	public String getVelocidadViento() {
		return velocidadViento;
	}

	public void setVelocidadViento(String velocidadViento) {
		this.velocidadViento = velocidadViento;
	}

	public String getUv() {
		return uv;
	}

	public void setUv(String uv) {
		this.uv = uv;
	}

	public String getHumedad() {
		return humedad;
	}

	public void setHumedad(String humedad) {
		this.humedad = humedad;
	}

	public String getProbPrecipitacion() {
		return probPrecipitacion;
	}

	public void setProbPrecipitacion(String probPrecipitacion) {
		this.probPrecipitacion = probPrecipitacion;
	}

	@Override
	public String toString() {
		return "PrediccionDiaria [fecha=" + fecha + ", hora=" + hora + ", provinciasMap=" + provinciasMap
				+ ", municipiosMap=" + municipiosMap + ", comunidadesAutonomasMap=" + ", localidad=" + localidad
				+ ", imagenesClima=" + ", temperaturaActual=" + temperaturaActual + ", tempMax=" + tempMax
				+ ", tempMin=" + tempMin + ", direccionViento=" + direccionViento + ", velocidadViento="
				+ velocidadViento + ", uv=" + uv + ", humedad=" + humedad + ", probPrecipitacion=" + probPrecipitacion
				+ "]";
	}


	public String generarRuta(String idProvincia, String idMunicipio) {
		// Comprobar si el idProvincia y el idMunicipio existen
		if (!provinciasMap.containsKey(idProvincia) || !municipiosMap.containsKey(idMunicipio)) {
			System.out.println("Error, la Provincia o el Municipio no existen");
			return "";

		} else {
			return "https://www.aemet.es/xml/municipios/localidad_" + idProvincia + idMunicipio + ".xml";
		}
	}
}
