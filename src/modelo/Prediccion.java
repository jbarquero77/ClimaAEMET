package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.NodeList;

public class Prediccion {
	
	private String hora;
	private HashMap<String, String> provincia;
	private HashMap<String, String> municipio;
	
	private static List<NodoDia> listaNodosDias;

	public Prediccion(String hora, HashMap<String, String> provincia,
			HashMap<String, String> municipio, List<NodoDia> listaNodosDias) {
		this.hora = hora;
		this.provincia = provincia;
		this.municipio = municipio;
		this.listaNodosDias = new ArrayList<>();
	}

	public String getHora() {
		return hora;
	}

	public void setHoraElaboracion(String horaElaboracion) {
		this.hora= hora;
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

	
	public static List<NodoDia> getListaNodosDias() {
		return listaNodosDias;
	}

}
