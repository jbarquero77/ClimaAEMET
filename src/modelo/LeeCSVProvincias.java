package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class LeeCSVProvincias {
	private HashMap<String, String> provinciasMap;

	public LeeCSVProvincias() {
		provinciasMap = new HashMap<>();
		cargarDatos();
	}

	public void cargarDatos() {
		String archivoCSV = "src/utilidades/Provincias.csv";
		String separador = " ";

		try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
			String linea;
			// Saltar la primera línea si contiene encabezados
			br.readLine();
			while ((linea = br.readLine()) != null) {
				String[] partes = linea.split(separador);
			// CODAUTO, NOMBRE
				String cpro = partes[0];
				String nombre = partes[1];
				// Crear objetos y agregar a los HashMaps correspondientes
				// Agregar datos directamente a los HashMaps
				provinciasMap.put(cpro, nombre);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	public HashMap<String, String> getProvinciasMap() {
		return this.provinciasMap;
	}
	
	public String getKeyOfValue(String provinciaName) {
		String resultado = "";	
		for (Entry<String, String> entry : this.provinciasMap.entrySet()) {
	        String id = entry.getKey();
	        String nombre = entry.getValue();	        
			if (nombre.startsWith(provinciaName)) {
	          resultado = id;
	          break;  
	        }
	    }

		return resultado;
	}
	
	
}
