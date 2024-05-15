package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

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
	        return new HashMap<>(mapaEstadosClima);
	    }

	   

	    public static void main(String[] args) {
	    	LeeMapaImagenes mapper = new LeeMapaImagenes();
	        HashMap<String, String> mapaEstados = mapper.getWeatherMap();
	     
	        // Para verificar el contenido del HashMap
	        System.out.println(mapaEstados); 
	    }
	}