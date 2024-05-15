package modelo;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class ParseoXML {
    
	
	public static void main(String[] args) {
    	 
    	try {
            // Obtener la URL de la localidad
            String ruta = "https://www.aemet.es/xml/municipios/localidad_44045.xml";
            
            // Crear el parser XML
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            URL url = new URL(ruta);
            InputStream stream = url.openStream();
            Document doc = db.parse(stream);
            
            // Normalizar el documento
            doc.getDocumentElement().normalize();
                
            ArrayList<Prediccion>listaPredicciones = new ArrayList<>();
            
            // Obtener el nombre y la provincia de la localidad, la direccion y la hora
            String nombreLocalidad = doc.getElementsByTagName("nombre").item(0).getTextContent();
            String provincia = doc.getElementsByTagName("provincia").item(0).getTextContent();
            String direccionWeb = doc.getElementsByTagName("enlace").item(0).getTextContent();
           
            //Tengo que crear los HashMap de municipio y provincia
            HashMap<String, String> mapProvincia = new HashMap<>();
            HashMap<String, String> mapMunicipio = new HashMap<>();
            mapProvincia.put(provincia, "");
            mapMunicipio.put(nombreLocalidad,"");
            
           //La hora falta parsearla
          // String fechaHora = datosFecha.substring(datosFecha.indexOf('>') + 1, datosFecha.lastIndexOf('<'));
            
            // Separar la fecha y la hora
            NodeList elaboradoList = doc.getElementsByTagName("elaborado");
            Element elaboradoElement = (Element) elaboradoList.item(0);

            // Obtener el contenido del elemento <elaborado>
            String elaboradoStr = elaboradoElement.getTextContent();

            // Convertir el contenido a fecha y hora
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date elaboradoDate = dateFormat.parse(elaboradoStr);

            // Formatear la fecha y la hora según el formato deseado
            SimpleDateFormat fechaFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm");
            String fechaFormateada = fechaFormat.format(elaboradoDate);
            String horaFormateada = horaFormat.format(elaboradoDate);

            // Imprimir la fecha y la hora formateadas
            System.out.println("Fecha: " + fechaFormateada);
            System.out.println("Hora: " + horaFormateada);
            
            
            System.out.println("Nombre de la localidad: " + nombreLocalidad);
            System.out.println("Provincia: " + provincia);
            System.out.println("Enlace: " + direccionWeb);
         

            // Dividir la URL para extraer el nombre del municipio y su ID
            String[] partesURL = direccionWeb.split("/");
            String idCompleto = partesURL[partesURL.length - 1].substring(partesURL[partesURL.length - 1].indexOf("-id") + 3); // Obtener el ID completo después de "-id"
            System.out.println("ID completo: " + idCompleto); // Imprimir el ID completo para verificar su formato
            String idProvincia = idCompleto.substring(0, 2); // Los dos primeros dígitos son la provincia
            String idMunicipio = idCompleto.substring(2); // Los tres siguientes dígitos son el municipio
       
            System.out.println("Provincia: " + idProvincia);
            System.out.println("Municipio: " + idMunicipio);
            
            
            // Recorrer los días y mostrar la información meteorológica
            // Los guardo en una NodeList dias
            NodeList dias = doc.getElementsByTagName("dia");
            
            
            	
            
            for (int i = 0; i < dias.getLength(); i++) {
                Element dia = (Element) dias.item(i);
               
                
                //TEMPERATURA
                String tempMax = dia.getElementsByTagName("maxima").item(0).getTextContent();
                String tempMin = dia.getElementsByTagName("minima").item(0).getTextContent();
                
                
                System.out.println("Temperatura Máxima: " + tempMax);
                System.out.println("Temperatura Mínima: " + tempMin);
                
                // Mostrar la temperaturaa diferentes horas
                Element temperatura = (Element) dia.getElementsByTagName("temperatura").item(0);
                NodeList datosTemperatura = temperatura.getElementsByTagName("dato");
                String temp ="";
                for (int j = 0; j < datosTemperatura.getLength(); j++) {
                    Element datoTemperatura = (Element) datosTemperatura.item(j);
                    String horaD = datoTemperatura.getAttribute("hora");
                    temp = datoTemperatura.getTextContent();
                    System.out.println("Temperatura a las " + horaD + ": " + temp);
                }
                //Creo un nuevo mapadeImagenes
                HashMap<String, String> estadosClima = new HashMap<>();
               // Mostrar el estado del cielo
                
                String descripcion ="";
                NodeList estadosCielo = dia.getElementsByTagName("estado_cielo");
                for (int j = 0; j < estadosCielo.getLength(); j++) {
                    Element estado = (Element) estadosCielo.item(j);
                    String periodo = estado.getAttribute("periodo");
                    descripcion = estado.getAttribute("descripcion");
                    //introduzco el estado del clima dentro del mapa
                    estadosClima.put(descripcion,"");
                    System.out.println("Estado del cielo para el período " + periodo + ": " + descripcion);
                    
                }
                                
             // Mostrar la dirección y velocidad del viento
                NodeList vientos = dia.getElementsByTagName("viento");
                String direccion="";
               String velocidad ="";
                for (int j = 0; j < vientos.getLength(); j++) {
                    Element viento = (Element) vientos.item(j);
                    String periodo = viento.getAttribute("periodo");
                    direccion = viento.getElementsByTagName("direccion").item(0).getTextContent();
                    velocidad = viento.getElementsByTagName("velocidad").item(0).getTextContent();
                    
                    System.out.println("Viento para el período " + periodo + ": Dirección " + direccion + ", Velocidad " + velocidad);
                }
                          
                //Uv
                String uvMax = dia.getElementsByTagName("uv_max").item(0).getTextContent();
                
                System.out.println("Rayos UV Máximos: " + uvMax);
                
                // humedad
                
                
                Element humedad = (Element) dia.getElementsByTagName("humedad_relativa").item(0);
                NodeList datosHumedad = humedad.getElementsByTagName("dato");
                String hum="";
                //Contemplo la posibilidad de que no hayan datos, me saltaba un error
                
                if(datosHumedad.getLength()==0) {
                	hum = "--";
                }else {
                                
                	for (int j = 0; j < datosHumedad.getLength(); j++) {
                		Element datoHumedad = (Element) datosHumedad.item(j);
                		String horaD = datoHumedad.getAttribute("hora");
                		hum = datoHumedad.getTextContent();
                	}
                    System.out.println("Humedad relativa a las " + horaFormateada + ": " + hum);
                }
                
                
                //PROB. LLUVIA
                String probLluvia = dia.getElementsByTagName("prob_precipitacion").item(0).getTextContent();
                
                System.out.println("Probabilidad de Lluvia: " + probLluvia);
                
                
                // Imprimir una línea en blanco para separar cada pronóstico meteorológico
                System.out.println();
                
               
                Prediccion pd = new Prediccion(fechaFormateada, horaFormateada, mapProvincia, mapMunicipio, nombreLocalidad, descripcion, temp, tempMax, tempMin, direccion, velocidad, uvMax, hum, probLluvia);
                listaPredicciones.add(pd);
               
                
            }
          
           
        } catch (Exception e) {
           e.getStackTrace();
        }
    }
	
	
	
	
}
