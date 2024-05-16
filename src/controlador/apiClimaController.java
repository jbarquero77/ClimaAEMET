package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modelo.NodoDia;
import modelo.ParseoProfe;
import modelo.Prediccion;
import modelo.Prediccion;
import modelo.LeeCSVProvincias;
import modelo.LeeCSVMunicipios;

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

	// labels que no cambiarán de idioma
	@FXML
	private static Label localidad;

	@FXML
	private Label hora;
	@FXML
	private static Label DIAFECHA; // Podria usar el formateador de fechas segun pais

	// labels que muestran datos
	@FXML
	private Label tempActual; // Muestra la temperatura actual tenemos que recorrer las horas y mostrar el estado correspondiente
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

	// Combox de IDIOMAS
	@FXML
	private void onMousePresedIdioma(ActionEvent event) throws IOException {
		Scene scene = this.primeraCapa.getScene();

		if (this.cbIdioma.getSelectionModel().getSelectedIndex() == 0) {

			scene.setRoot(FXMLLoader.load(getClass().getResource("/vista/apiClima.fxml"),
					ResourceBundle.getBundle("utilidades.traduccion", new Locale("es", "ES"))));
			this.cbIdioma.getSelectionModel().select(0);
		} else if (this.cbIdioma.getSelectionModel().getSelectedIndex() == 1) {

			scene.setRoot(FXMLLoader.load(getClass().getResource("/vista/apiClima.fxml"),
					ResourceBundle.getBundle("utilidades.traduccion", new Locale("en", "UK"))));
			this.cbIdioma.getSelectionModel().select(1);
		}
	}

	@FXML
	private void onMousePresedProvincias(ActionEvent event) throws IOException {
		this.reset();
		// System.out.println(this.cbProvincias.());
		// event.getSource()
		// this.leeMunicipios.getMunicipiosMapByIdProvincia(this.mapaProvincias);
	}

	@FXML
	private void onMousePresedMunicipio(ActionEvent event) throws IOException {
		this.reset();
		// tindre el idProvincia y el municipio seleccionado del combo

		String idProvincia = "46";
		String idMunicipio = "029";
		ParseoProfe predice = new ParseoProfe(idProvincia, idMunicipio);
		System.out.println(predice);
	}

	private HashMap<String, String> mapaProvincias;
	private HashMap<String, String> mapaMunicipios;
	private HashMap<String, String> mapaImagenes;

	private void selecMunicipios() {
		ObservableList<String> nombresMunicipios = FXCollections.observableArrayList(mapaMunicipios.values());
		cbMunicipio.setItems(nombresMunicipios);
		// cbMunicipio.getSelectionModel().select(0);

	}

	private void selecProvincias() {
		ObservableList<String> nombresProvincias = FXCollections.observableArrayList(mapaProvincias.values());

		cbProvincias.setItems(nombresProvincias);
		// cbProvincias.getSelectionModel().select(0);
	}

	public String imagenesClima() {
		String rutaImagen = "";
		mapaImagenes.get(rutaImagen);
		return rutaImagen;
	}

	// Acciones Botones
	// Ejemplo de instancias de las clases Prediccion y PrediccionDiaria

	private Prediccion prediccionDiaria;
	private LeeCSVProvincias leeProvincias;
	private LeeCSVMunicipios leeMunicipios;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// this.reset();
		// solo la primera vez que iniciamos la app se selecciona siempre el Spain
		// if (this.cbIdioma.getSelectionModel().getSelectedIndex() == -1)
		// this.cbIdioma.getSelectionModel().select(0);

		// Inicializar y poblar los ComboBox
		this.leeProvincias = new LeeCSVProvincias();
		this.leeMunicipios = new LeeCSVMunicipios();
		this.mapaProvincias = leeProvincias.getProvinciasMap();
		this.mapaMunicipios = leeMunicipios.getMunicipiosMap();

		// mapaComunidades = new HashMap<>();
		this.selecMunicipios();
		this.selecProvincias();

		if (prediccionDiaria != null) {

			hora.setText(prediccionDiaria.getHora().toString());
			localidad.setText(prediccionDiaria.getMunicipio().toString());

			cargarDatos(prediccionDiaria);	 
		}
	}

	@FXML
	void clicSalir(ActionEvent event) {
		Platform.exit();
	}

	// Metodo para resetear todos los valores de pantalla

	public void reset() {
		// Etiquetas de información general
		localidad.setText("");
		hora.setText("");
		DIAFECHA.setText("");
		tempActual.setText("");

		// Etiquetas de temperatura por hora
		tmp1.setText("");
		tmp2.setText("");
		tmp3.setText("");
		tmp4.setText("");

		// Etiquetas de información adicional
		TEMMAX.setText("");
		TEMMIN.setText("");
		VientoDir.setText("");
		VientoVel.setText("");
		UV.setText("");
		HUM.setText("");
		porLlu.setText("");

		// Imágenes de estado del clima clima1 corresponde al periodo de 00:06 y climaDia es el estado del clima para cada dia
		clima1.setImage(null);
		clima2.setImage(null);
		clima3.setImage(null);
		clima4.setImage(null);
		climaDia.setImage(null);

		// Reiniciar ComboBox
		cbMunicipio.getSelectionModel().clearSelection();
	}

	public static void cargarDatos(Prediccion prediccion) {
		if (prediccion != null) {
			// manejar la lista de NodoDia para mostrar los datos
			List<NodoDia> nodosDias = prediccion.getListaNodosDias();
			if (nodosDias != null && !nodosDias.isEmpty()) {
				//iterar sobre la lista de NodoDia y mostrar los datos en la interfaz
				for (NodoDia nodoDia : nodosDias) {
					nodoDia.getFecha();
					nodoDia.getProbabilidadLluvia();
					nodoDia.getEstadoCielo();
					nodoDia.getDireccionViento();
					nodoDia.getVelocidadViento();
					nodoDia.getTemperaturaMaxima();
					nodoDia.getTemperaturaMinima();
					nodoDia.getHumedadMaxima();
					nodoDia.getIndiceUV();
				}
			}
		}
	}
}
