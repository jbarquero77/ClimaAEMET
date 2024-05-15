package application;
	
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
	      
		//La aplicación iniciará en español,habrá que crear un método para que pueda elegir idioma
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("/vista/apiClima.fxml"), ResourceBundle.getBundle("utilidades.traduccion", new Locale("es", "ES")));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/vista/css/estilos.css").toExternalForm());
			primaryStage.setTitle("Aplicacion Clima Aemet. 1º DAW JOSE BARQUERO");
			primaryStage.setScene(scene);
			primaryStage.getIcons().add(new Image("img/AEMET.png"));
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
