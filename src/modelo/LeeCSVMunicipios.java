package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class LeeCSVMunicipios {
	//private static HashMap<String, String> provinciasMap;
	private static HashMap<String, String> municipiosMap;
	//private static HashMap<String, String> comunidadesAutonomasMap;

	public LeeCSVMunicipios() {
	//	provinciasMap = new HashMap<>();
		municipiosMap = new HashMap<>();
		//comunidadesAutonomasMap = new HashMap<>();
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
				//String cpro = partes[1];
				String cmun = partes[2];
				String nombre = partes[4];

				// Crear objetos y agregar a los HashMaps correspondientes
				// Agregar datos directamente a los HashMaps
			//	provinciasMap.put(cpro, nombre);
				municipiosMap.put(cmun, nombre);
			//	comunidadesAutonomasMap.put(codauto, nombre);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	

	public static HashMap<String, String> getMunicipiosMap() {
		return new HashMap<>(municipiosMap);
	}

	
}
