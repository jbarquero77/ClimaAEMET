package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class LeeCSVMunicipios {
	
	private HashMap<String, String> municipiosMap;
	private HashMap<String, String> provinciaMunicipioMap;
	private String idProvincia;

	public LeeCSVMunicipios() {
		municipiosMap = new HashMap<>();	
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
				municipiosMap.put(cmun, nombre);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	

	public HashMap<String, String> getMunicipiosMap() {
		return this.municipiosMap;
	}

	//implementar un metodo que al pasarle un id provincia me modifique el hashmap de municipios y devuelva solo los que pertenecen a esa provincia
	public HashMap<String, String> getMunicipiosMapByIdProvincia(){
		for (Entry<String, String> entry : provinciaMunicipioMap.entrySet()) {
	        String id = entry.getKey();
	        String nombre = entry.getValue();	        
			if (id.startsWith(idProvincia)) {
	            System.out.println(nombre);
	        }
	    }
		return this.provinciaMunicipioMap;
	}
}
