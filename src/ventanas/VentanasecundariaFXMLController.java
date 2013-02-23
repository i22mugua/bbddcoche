package ventanas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;


public class VentanasecundariaFXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button botonCancelar;

    @FXML
    private Button botonCopiarDatos;

    @FXML
    private ComboBox<?> comboBox;

    @FXML
    private TableView<?> tableDatos;


    @FXML
    void handleCancelar(ActionEvent event) {
    }

    @FXML
    void handleComboBox(ActionEvent event) {
    }

    @FXML
    void handleCopiarDatos(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert botonCancelar != null : "fx:id=\"botonCancelar\" was not injected: check your FXML file 'VentanaSecundariaFXML.fxml'.";
        assert botonCopiarDatos != null : "fx:id=\"botonCopiarDatos\" was not injected: check your FXML file 'VentanaSecundariaFXML.fxml'.";
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'VentanaSecundariaFXML.fxml'.";
        assert tableDatos != null : "fx:id=\"tableDatos\" was not injected: check your FXML file 'VentanaSecundariaFXML.fxml'.";


    }

}
