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

public class ParseoProfe {
	
	private String rutaXML;
	private Boolean esCorrectaLectura;
	
	ArrayList<Prediccion> listaPredicciones = new ArrayList<>();

	public void cargarProbabilidadLluvia(Element e) {
		NodeList listaLLuvia = e.getElementsByTagName("prob_precipitacion");

		for (int iLluvia = 0; iLluvia < listaLLuvia.getLength(); iLluvia++) {
			Node nodoLluvia = listaLLuvia.item(iLluvia);

			if (nodoLluvia.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoLluvia = (Element) nodoLluvia;
				// Con getAttribute accedemos al dato del atributo "periodo" de la etiqueta
				// "prob_precipitacion"
				// Ejemplo: <prob_precipitacion periodo="06-12">... obtenemos el dato "06-12"
			//	System.out.print("\tProbabilidad precipitación (" + elementoLluvia.getAttribute("periodo") + "): ");

				// Con getTextContent() accedemos al dato que contiene la etiqueta
				// "prob_precipitacion"
				// Ejemplo: <prob_precipitacion periodo="06-12"> 15 </prob_precipitacion>
				// obtenemos el dato "15"
				//System.out.println(elementoLluvia.getTextContent());
			}
		}
	}

	public Boolean getEsCorrectaLectura() {
		return esCorrectaLectura;
	}

	public void setEsCorrectaLectura(Boolean esCorrectaLectura) {
		this.esCorrectaLectura = esCorrectaLectura;
	}

	public ParseoProfe(String AIdProvincia, String AIdMunicipio) {
	  this.rutaXML = this.generarRuta(AIdProvincia, AIdMunicipio);
	  this.aleerDatos();
	}
	
	public void cargarEstadoCielo(Element e) {

		NodeList listaCielo = e.getElementsByTagName("estado_cielo");

		for (int iCielo = 0; iCielo < listaCielo.getLength(); iCielo++) {
			Node nodoCielo = listaCielo.item(iCielo);

			if (nodoCielo.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoCielo = (Element) nodoCielo;
/*
				System.out.print("\tEstado del Cielo (" + elementoCielo.getAttribute("periodo") + "): ");

				System.out.println(elementoCielo.getAttribute("descripcion"));
				*/
			}
		}
	}

	public void cargarViento(Element e) {
		NodeList listaViento = e.getElementsByTagName("viento");
		for (int iViento = 0; iViento < listaViento.getLength(); iViento++) {
			Node nodoViento = listaViento.item(iViento);

			if (nodoViento.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoViento = (Element) nodoViento;
				
				/*
				System.out.println("\tEstado del Viento (" + elementoViento.getAttribute("periodo") + "): ");
				System.out.println(elementoViento.getAttribute("direccion"));
				System.out.println(elementoViento.getAttribute("velocidad"));
				*/
			}
		}
	}

	public void cargarUV(Element e) {
		NodeList listaUV = e.getElementsByTagName("uv_max");
		for (int iUV = 0; iUV < listaUV.getLength(); iUV++) {
			Node nodoUV = listaUV.item(iUV);

			if (nodoUV.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoUV = (Element) nodoUV;
				/*
				System.out.println("\tIndice UV máximo:  (" + elementoUV.getAttribute("uv_max") + "): ");
				System.out.println(elementoUV.getAttribute("uv_max"));
*/
			}
		}
	}

	public void cargarTemperatura(Element e) {
		NodeList listaTemperatura = e.getElementsByTagName("temperatura");
		for (int iTemperatura = 0; iTemperatura < listaTemperatura.getLength(); iTemperatura++) {
			Node nodoTemperatura = listaTemperatura.item(iTemperatura);

			if (nodoTemperatura.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoTemperatura = (Element) nodoTemperatura;
				
				/*
				System.out.println("\tTemperatura (" + elementoTemperatura.getAttribute("maxima") + "): ");
				System.out.println("\tTemperatura (" + elementoTemperatura.getAttribute("minima") + "): ");

				System.out.println("\tTemperatura (" + elementoTemperatura.getAttribute("dato") + "): ");
				System.out.println(elementoTemperatura.getAttribute("hora"));
*/
			}
		}
	}

	public void cargarHumedad(Element e) {
		NodeList listaHumedad = e.getElementsByTagName("humedad_relativa");
		for (int iHumedad = 0; iHumedad < listaHumedad.getLength(); iHumedad++) {
			Node nodoHumedad = listaHumedad.item(iHumedad);

			if (nodoHumedad.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoHumedad = (Element) nodoHumedad;
				/*
				System.out.println("\tHumedad (" + elementoHumedad.getAttribute("maxima") + "): ");
				System.out.println("\tHumedad (" + elementoHumedad.getAttribute("minima") + "): ");

				System.out.println("\tHumedad (" + elementoHumedad.getAttribute("dato") + "): ");
				System.out.println(elementoHumedad.getAttribute("hora"));
*/
			}
		}
	}

	public void aleerDatos() {

		try {
			// Preparamos los objetos necesarios para cargar el documento XML
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			URL url = new URL(this.rutaXML);
			InputStream stream = url.openStream();
			// Parseamos el documento con el objeto Document y le pasamos el stream de la
			// ruta
			Document doc = db.parse(stream);

			doc.getDocumentElement().normalize(); // Normalizamos el documento (opcional)

			// Obtener el nombre y la provincia de la localidad, la direccion y la hora
			String nombreLocalidad = doc.getElementsByTagName("nombre").item(0).getTextContent();
			String provincia = doc.getElementsByTagName("provincia").item(0).getTextContent();
			String direccionWeb = doc.getElementsByTagName("enlace").item(0).getTextContent();

			// Tengo que crear los HashMap de municipio y provincia
			HashMap<String, String> mapProvincia = new HashMap<>();
			HashMap<String, String> mapMunicipio = new HashMap<>();
			mapProvincia.put(provincia, "");
			mapMunicipio.put(nombreLocalidad, "");

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

/*			Imprimir la fecha y la hora formateadas
			System.out.println("Fecha: " + fechaFormateada);
			System.out.println("Hora: " + horaFormateada);

			System.out.println("Nombre de la localidad: " + nombreLocalidad);
			System.out.println("Provincia: " + provincia);
			System.out.println("Enlace: " + direccionWeb);
*/
			// Dividir la URL para extraer el nombre del municipio y su ID
			String[] partesURL = direccionWeb.split("/");
			String idCompleto = partesURL[partesURL.length - 1]
					.substring(partesURL[partesURL.length - 1].indexOf("-id") + 3); // Obtener el ID completo después de
																					// "-id"
			//System.out.println("ID completo: " + idCompleto); // Imprimir el ID completo para verificar su formato
			String idProvincia = idCompleto.substring(0, 2); // Los dos primeros dígitos son la provincia
			String idMunicipio = idCompleto.substring(2); // Los tres siguientes dígitos son el municipio

			//System.out.println("Provincia: " + idProvincia);
		//	System.out.println("Municipio: " + idMunicipio);

			// Obtenemos la lista de nodos referente a la etiqueta "dia" de nuestro xml
			NodeList listaNodosDias = doc.getElementsByTagName("dia");

			// Recorremos la lista de dias para cargar los datos que contiene cada día
			for (int iDias = 0; iDias < listaNodosDias.getLength(); iDias++) {
				Node nodoDia = listaNodosDias.item(iDias);

				if (nodoDia.getNodeType() == Node.ELEMENT_NODE) { // Comprobamos que es un Nodo
					// Convertimos cada nodo en un elemento para poder acceder
					// a los métodos de acceso a los datos (getAttribute, getElementsByTagName, etc)
					Element elementoDia = (Element) nodoDia;

					// Con getAttribute accedemos al dato que hay en el atributo del elemento
					// Por ejemplo: <dia fecha="2024-05-13"> accedemos a "2025-05-13"
				//	System.out.println("\nFecha id: " + elementoDia.getAttribute("fecha"));

					// Modularizamos lo mejor posible el código para cargar los datos (es un
					// ejemplo)
					cargarProbabilidadLluvia(elementoDia);
					cargarEstadoCielo(elementoDia);
					cargarViento(elementoDia);
					cargarTemperatura(elementoDia);
					cargarHumedad(elementoDia);
					cargarUV(elementoDia);
					
					
					// Creariamos un objeto Prediccion, con estos parámetros
				
					// le pasaríamos la lista de los NodosDia, que contienen toda la información por
					// días
					
					Prediccion prediccion = new Prediccion(horaFormateada, mapProvincia,
							mapMunicipio, (List<NodoDia>) listaNodosDias);
					listaPredicciones.add(prediccion);
				}
				//System.out.println();
			}

			this.esCorrectaLectura = true;
		} catch (Exception e) {
			this.esCorrectaLectura = false;
			System.out.println("Error en la lectura de datos");
		}
	}


	public String generarRuta(String AIdProvincia, String AIdMunicipio) {
		// Comprobar si el idProvincia y el idMunicipio existen
		if (AIdProvincia.trim().isEmpty() || AIdMunicipio.trim().isEmpty() ) {
			System.out.println("Error, la Provincia o el Municipio no existen");
			return "";

		} else {
			return "https://www.aemet.es/xml/municipios/localidad_" + AIdProvincia + AIdMunicipio + ".xml";
		}
	}
}
