package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class LeeMapaImagenes {
	 private HashMap<String, String> mapaEstadosClima;

	    public LeeMapaImagenes() {
	        mapaEstadosClima = new HashMap<>();
	        leeDatosEstadoClima();
	    }
//FUNCIONA!!!!!!!
	    private void leeDatosEstadoClima() {
	        try {
	            // Leer el archivo de estados del clima
	            BufferedReader brEstados = new BufferedReader(new FileReader("src/utilidades/estadosClima.txt"));

	            String linea;
	            int numeroImagen = 1;
	            // Leer línea por línea el archivo
	            while ((linea = brEstados.readLine()) != null) {
	                // Agregar la entrada al HashMap
	            	mapaEstadosClima.put(linea.trim(), "src/img/" + numeroImagen + ".png");

	                numeroImagen++;
	            }

	           
	            brEstados.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public HashMap<String, String> getWeatherMap() {
	        return this.mapaEstadosClima;
	    }

	   

	    public static void main(String[] args) {
	    	LeeMapaImagenes lmp = new LeeMapaImagenes();
	        HashMap<String, String> mapaEstados = lmp.getWeatherMap();
	        // Iterar sobre el HashMap para obtener clave y valor por separado
	        for (Entry<String, String> entry : mapaEstados.entrySet()) {
	            String clave = entry.getKey();
	            String valor = entry.getValue();
	            System.out.println("Clave: " + clave + ", Valor: " + valor);
	        }
	    }
	        
	}