package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import modelo.Prediccion;
import modelo.PrediccionPrueba;

public class apiClimaController implements Initializable {

	@FXML
	private AnchorPane primeraCapa;

	// labels de que solo cambiaran de idioma
	@FXML
	private Label diaLunes;
	@FXML
	private Label diaMartes;
	@FXML
	private Label diaMiercoles;
	@FXML
	private Label diaJueves;
	@FXML
	private Label diaViernes;
	@FXML
	private Label diaSabado;
	@FXML
	private Label diaDomingo;

	@FXML
	private Label IDIOMA;
	@FXML
	private Label COMUNIDAD;
	@FXML
	private Label MUNICIPIO;
	@FXML
	private Label PROVINCIAS;
	

	@FXML
	private Button btnSalir;

	// labels que no cambiarán
	@FXML
	private Label localidad;
	@FXML
	private Label AMPM;
	@FXML
	private Label hora;
	@FXML
	private Label DIAFECHA;   //Podria usar el formateador de fechas segun pais

	// labels que muestran datos
	@FXML
	private Label tempActual; // Muestra la temperatura actual
	@FXML
	private Label tmp1; // Rangos 00:06

	@FXML
	private Label tmp2; // 06:12

	@FXML
	private Label tmp3; // 12:18

	@FXML
	private Label tmp4; // 18:24

	// Caja donde se guardan los datos semanales
	@FXML
	private HBox hbPrevSemana;

	// Temperaturas Máximas
	@FXML
	private Label TEMMAX;

	// Temperaturas Minimas
	@FXML
	private Label TEMMIN;

	// Faltan los labels de la vientoDireccion
	@FXML
	private Label VientoDir;
	@FXML
	private Label VientoVel;

// UV
	@FXML
	private Label UV;
	// labels de humedad
	@FXML
	private Label HUM;

	// labels de probLluvia
	@FXML
	private Label porLlu;

	@FXML
	private VBox vbHoras;

	// Imagenes para los estadosClima en el mismo dia

	@FXML
	private ImageView clima1;

	@FXML
	private ImageView clima2;

	@FXML
	private ImageView clima3;

	@FXML
	private ImageView clima4;

	@FXML
	private ImageView climaDia;

	// ComboBox


	@FXML
	private ComboBox<String> cbMunicipio;

	@FXML
	private ComboBox<String> cbProvincias;

	@FXML
	private ComboBox<String> cbIdioma;
	
	
	
	

	//Combox de IDIOMAS	
	@FXML
	private void onMousePresedIdioma(ActionEvent event) throws IOException {
	    Scene scene = this.primeraCapa.getScene();
	    
	    if (this.cbIdioma.getSelectionModel().getSelectedIndex() == 0) {
	        System.out.println("Cargando Español");
	        scene.setRoot(FXMLLoader.load(getClass().getResource("/vista/apiClima.fxml"),
	                ResourceBundle.getBundle("utilidades.traduccion", new Locale("es", "ES"))));
	        this.cbIdioma.getSelectionModel().select(0);
	    } else if (this.cbIdioma.getSelectionModel().getSelectedIndex() == 1) {
	        System.out.println("Cargando Inglés");
	        scene.setRoot(FXMLLoader.load(getClass().getResource("/vista/apiClima.fxml"),
	                ResourceBundle.getBundle("utilidades.traduccion", new Locale("en", "UK"))));
	        this.cbIdioma.getSelectionModel().select(1);
	    }
	}
	
	@FXML
	private void onMousePresedProvincias(ActionEvent event) throws IOException {
	   this.reset();
	    
	}
	
	
	@FXML
	private void onMousePresedMunicipio(ActionEvent event) throws IOException {
		this.reset();
	    
	}
	
	

	private HashMap<String, String> mapaProvincias;
	private HashMap<String, String> mapaMunicipios;
	private HashMap<String, String> mapaComunidades;
	private HashMap<String, String> mapaImagenes;

	private void selecIdiomas() {
		ObservableList<String> languageOptions = FXCollections.observableArrayList("Español", "Inglés");
		// comboBox.getItems().add("Espanol");
		// comboBox.getItems().add("Ingles");

		cbIdioma.setItems(languageOptions);
		cbIdioma.getSelectionModel().select(0);
	}
	
	
	private void selecMunicipios() {
		ObservableList<String> nombresMunicipios = FXCollections.observableArrayList(mapaMunicipios.values());
		cbMunicipio.setItems(nombresMunicipios);
		cbMunicipio.getSelectionModel().select(0);
		
	}
	
	
	private void selecProvincias() {		
		ObservableList<String> nombresProvincias = FXCollections.observableArrayList(mapaProvincias.values());
		
		cbProvincias.setItems(nombresProvincias);
		cbProvincias.getSelectionModel().select(0);
	}
	/*
	public String imagenesClima() {
		String rutaImagen="";
		mapaImagenes.get(rutaImagen);
		return rutaImagen;
	}
*/

	// Acciones Botones
	// Ejemplo de instancias de las clases Prediccion y PrediccionDiaria

	private Prediccion prediccionDiaria;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// Aquí puedes inicializar y poblar los ComboBox
		mapaProvincias = new HashMap<>();
		mapaMunicipios = new HashMap<>();
		mapaComunidades = new HashMap<>();
		this.selecIdiomas();
		this.selecMunicipios();
		this.selecProvincias();
		
		if (prediccionDiaria != null) {

			AMPM.setText(prediccionDiaria.getHora());
			DIAFECHA.setText(prediccionDiaria.getFecha().toString());
			hora.setText(prediccionDiaria.getHora());

			// Llenar el ComboBox de provincias con datos
			ObservableList<String> nombresProvincias = FXCollections.observableArrayList(mapaProvincias.values());
			cbProvincias.setItems(nombresProvincias);
			cbProvincias.setOnAction((EventHandler<ActionEvent>) this);
			cbProvincias.getItems().addAll(nombresProvincias);

			ObservableList<String> nombresMunicipios = FXCollections.observableArrayList(mapaMunicipios.values());
			cbMunicipio.setItems(nombresMunicipios);
			cbMunicipio.setOnAction((EventHandler<ActionEvent>) this);
			cbMunicipio.getItems().addAll(nombresMunicipios);
/*
			ObservableList<String> nombresComunidades = FXCollections.observableArrayList(mapaComunidades.values());
			cbComunidad.setItems(nombresComunidades);
			cbComunidad.setOnAction((EventHandler<ActionEvent>) this);
			cbComunidad.getItems().addAll(nombresComunidades);
*/
			// PANEL COMMBOX
			IDIOMA.setText("");
			PROVINCIAS.setText(mapaProvincias.get(nombresProvincias));
			MUNICIPIO.setText(mapaMunicipios.get(nombresMunicipios));
			//COMUNIDAD.setText(mapaComunidades.get(nombresComunidades));

			localidad.setText(prediccionDiaria.getLocalidad());
			tempActual.setText(prediccionDiaria.getTemperaturaActual()); // Muestra la temperatura actual

			// Meter un for
			tmp1.setText(prediccionDiaria.getTempMax()); // Rangos 00:06
			tmp2.setText(""); // 06:12
			tmp3.setText(""); // 12:18
			tmp4.setText(""); // 18:24

			// meter otro bucle para los dias y que para cada día me muestre los datos
			diaLunes.setText("");
			

			TEMMAX.setText(prediccionDiaria.getTempMax());
			TEMMIN.setText(prediccionDiaria.getTempMin());
			VientoDir.setText(prediccionDiaria.getDireccionViento());
			VientoVel.setText(prediccionDiaria.getVelocidadViento());
			UV.setText(prediccionDiaria.getUv());
			HUM.setText(prediccionDiaria.getHumedad());
			porLlu.setText(prediccionDiaria.getProbPrecipitacion());

		}
	}

	@FXML
	void clicSalir(ActionEvent event) {
		Platform.exit();
	}

	
	//Metodo para resetear todos los valores de pantalla
	
	public void reset() {
		
	}
	
	

}
