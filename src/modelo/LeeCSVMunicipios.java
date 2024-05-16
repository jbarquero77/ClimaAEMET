package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LeeCSVMunicipios {
	
	private HashMap<String, String> municipiosMap; //para tener todos los municipios del fichero CSV	

	public LeeCSVMunicipios() {
		this.municipiosMap = new HashMap<>();	
		cargarDatos();
	}

	private void cargarDatos() {
		String archivoCSV = "src/utilidades/Municipios.csv";
		String separador = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
			String linea;
			// Saltar la primera línea si contiene encabezados
			br.readLine();
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(separador);
				//CODAUTO,CPRO,CMUN,DC,NOMBRE
				String cpro = partes[1];
				String cmun = partes[2];
				String nombre = partes[4];

				
				// Agregar datos directamente a los HashMaps
				this.municipiosMap.put(cmun, nombre);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	

	public HashMap<String, String> getMunicipiosMap() {
		return this.municipiosMap;
	}

	//implementar un metodo que al pasarle un id provincia me modifique el hashmap de municipios y devuelva solo los que pertenecen a esa provincia
	public ObservableList<String> getMunicipiosMapByIdProvincia(String AIdProvincia){
		ObservableList<String> resultado = FXCollections.observableArrayList();	
		for (Entry<String, String> entry : this.municipiosMap.entrySet()) {
	        String id = entry.getKey();
	        String nombre = entry.getValue();	        
			if (id.startsWith(AIdProvincia)) {
	            resultado.add(nombre);
	        }
	    }
		return resultado;
	}
	
	public String getKeyOfValue(String municipiosName) {
		String resultado = "";	
		for (Entry<String, String> entry : this.municipiosMap.entrySet()) {
	        String id = entry.getKey();
	        String nombre = entry.getValue();	        
			if (nombre.startsWith(municipiosName)) {
	          resultado = id;
	          break;  
	        }
	    }

		return resultado;
	}
}
