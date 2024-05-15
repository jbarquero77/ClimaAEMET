package modelo;

import java.util.HashMap;
import java.util.List;

import org.w3c.dom.NodeList;

public class PrediccionPrueba {
	private String fechaElaboracion;
	private String horaElaboracion;
	private HashMap<String, String> provincia;
	private HashMap<String, String> municipio;
	
	private List<NodoDia> listaNodosDias;

	public PrediccionPrueba(String fechaElaboracion, String horaElaboracion, HashMap<String, String> provincia,
			HashMap<String, String> municipio, List<NodoDia> listaNodosDias) {
		this.fechaElaboracion = fechaElaboracion;
		this.horaElaboracion = horaElaboracion;
		this.provincia = provincia;
		this.municipio = municipio;
		
		this.listaNodosDias = listaNodosDias;
	}


	public String getFechaElaboracion() {
		return fechaElaboracion;
	}

	public void setFechaElaboracion(String fechaElaboracion) {
		this.fechaElaboracion = fechaElaboracion;
	}

	public String getHoraElaboracion() {
		return horaElaboracion;
	}

	public void setHoraElaboracion(String horaElaboracion) {
		this.horaElaboracion = horaElaboracion;
	}

	public HashMap<String, String> getProvincia() {
		return provincia;
	}

	public void setProvincia(HashMap<String, String> provincia) {
		this.provincia = provincia;
	}

	public HashMap<String, String> getMunicipio() {
		return municipio;
	}

	public void setMunicipio(HashMap<String, String> municipio) {
		this.municipio = municipio;
	}

	
	public List<NodoDia> getListaNodosDias() {
		return listaNodosDias;
	}

	public void setListaNodosDias(List<NodoDia> listaNodosDias) {
		this.listaNodosDias = listaNodosDias;
	}

	@Override
	public String toString() {
		return "PrediccionPrueba [fechaElaboracion=" + fechaElaboracion + ", horaElaboracion=" + horaElaboracion
				+ ", provincia=" + provincia + ", municipio=" + municipio + ", nombreLocalidad=" + ", listaNodosDias=" + listaNodosDias + "]";
	}

}
