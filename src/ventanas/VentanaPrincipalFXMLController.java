/**
 * Sample Skeleton for "VentanaPrincipalFXML.fxml" Controller Class
 * You can copy and paste this code into your favorite IDE
 **/

package ventanas;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToolBar;


public class VentanaPrincipalFXMLController
    implements Initializable {

    @FXML //  fx:id="PanelMysql"
    private TitledPane PanelMysql; // Value injected by FXMLLoader

    @FXML //  fx:id="PanelSQLLite"
    private TitledPane PanelSQLLite; // Value injected by FXMLLoader

    @FXML //  fx:id="botonCancelar"
    private Button botonCancelar; // Value injected by FXMLLoader

    @FXML //  fx:id="botonCopiarDatos"
    private Button botonCopiarDatos; // Value injected by FXMLLoader

    @FXML //  fx:id="botonExplorarSQLLite"
    private Button botonExplorarSQLLite; // Value injected by FXMLLoader

    @FXML //  fx:id="botonProbarMySql"
    private Button botonProbarMySql; // Value injected by FXMLLoader

    @FXML //  fx:id="botonProbarSQLLite"
    private Button botonProbarSQLLite; // Value injected by FXMLLoader

    @FXML //  fx:id="botonVerDatosMysql"
    private Button botonVerDatosMysql; // Value injected by FXMLLoader

    @FXML //  fx:id="botonVerDatosSQLLite"
    private Button botonVerDatosSQLLite; // Value injected by FXMLLoader

    @FXML //  fx:id="boxNombreBBDDMysql"
    private TextField boxNombreBBDDMysql; // Value injected by FXMLLoader

    @FXML //  fx:id="boxPasswordMysql"
    private PasswordField boxPasswordMysql; // Value injected by FXMLLoader

    @FXML //  fx:id="boxRutaFicheroSQLLite"
    private TextField boxRutaFicheroSQLLite; // Value injected by FXMLLoader

    @FXML //  fx:id="boxServidorMysql"
    private TextField boxServidorMysql; // Value injected by FXMLLoader

    @FXML //  fx:id="boxUsuarioMysql"
    private TextField boxUsuarioMysql; // Value injected by FXMLLoader

    @FXML //  fx:id="checkAutentificacionMysql"
    private CheckBox checkAutentificacionMysql; // Value injected by FXMLLoader

    @FXML //  fx:id="toolBar"
    private ToolBar toolBar; // Value injected by FXMLLoader


    // Handler for Button[fx:id="botonCancelar"] onAction
    public void handlebotonCancelar(ActionEvent event) {
        // handle the event here
    }

    // Handler for Button[fx:id="botonCopiarDatos"] onAction
    public void handlebotonCopiar(ActionEvent event) {
        // handle the event here
    }

    // Handler for Button[fx:id="botonExplorarSQLLite"] onAction
    public void handlebotonExplorarSQLLite(ActionEvent event) {
        // handle the event here
    }

    // Handler for Button[fx:id="botonProbarMySql"] onAction
    public void handlebotonProbarMysql(ActionEvent event) {
        // handle the event here
    }

    // Handler for Button[fx:id="botonProbarSQLLite"] onAction
    public void handlebotonProbarSQLLite(ActionEvent event) {
        // handle the event here
    }

    // Handler for Button[fx:id="botonVerDatosMysql"] onAction
    public void handlebotonVerDatosMysql(ActionEvent event) {
        // handle the event here
    }

    // Handler for Button[fx:id="botonVerDatosSQLLite"] onAction
    public void handlebotonVerDatosSQLLite(ActionEvent event) {
        // handle the event here
    }

    // Handler for CheckBox[fx:id="checkAutentificacionMysql"] onAction
    public void handlecheckAutentificacionMysql(ActionEvent event) {
        // handle the event here
    }

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert PanelMysql != null : "fx:id=\"PanelMysql\" was not injected: check your FXML file 'VentanaPrincipalFXML.fxml'.";
        assert PanelSQLLite != null : "fx:id=\"PanelSQLLite\" was not injected: check your FXML file 'VentanaPrincipalFXML.fxml'.";
        assert botonCancelar != null : "fx:id=\"botonCancelar\" was not injected: check your FXML file 'VentanaPrincipalFXML.fxml'.";
        assert botonCopiarDatos != null : "fx:id=\"botonCopiarDatos\" was not injected: check your FXML file 'VentanaPrincipalFXML.fxml'.";
        assert botonExplorarSQLLite != null : "fx:id=\"botonExplorarSQLLite\" was not injected: check your FXML file 'VentanaPrincipalFXML.fxml'.";
        assert botonProbarMySql != null : "fx:id=\"botonProbarMySql\" was not injected: check your FXML file 'VentanaPrincipalFXML.fxml'.";
        assert botonProbarSQLLite != null : "fx:id=\"botonProbarSQLLite\" was not injected: check your FXML file 'VentanaPrincipalFXML.fxml'.";
        assert botonVerDatosMysql != null : "fx:id=\"botonVerDatosMysql\" was not injected: check your FXML file 'VentanaPrincipalFXML.fxml'.";
        assert botonVerDatosSQLLite != null : "fx:id=\"botonVerDatosSQLLite\" was not injected: check your FXML file 'VentanaPrincipalFXML.fxml'.";
        assert boxNombreBBDDMysql != null : "fx:id=\"boxNombreBBDDMysql\" was not injected: check your FXML file 'VentanaPrincipalFXML.fxml'.";
        assert boxPasswordMysql != null : "fx:id=\"boxPasswordMysql\" was not injected: check your FXML file 'VentanaPrincipalFXML.fxml'.";
        assert boxRutaFicheroSQLLite != null : "fx:id=\"boxRutaFicheroSQLLite\" was not injected: check your FXML file 'VentanaPrincipalFXML.fxml'.";
        assert boxServidorMysql != null : "fx:id=\"boxServidorMysql\" was not injected: check your FXML file 'VentanaPrincipalFXML.fxml'.";
        assert boxUsuarioMysql != null : "fx:id=\"boxUsuarioMysql\" was not injected: check your FXML file 'VentanaPrincipalFXML.fxml'.";
        assert checkAutentificacionMysql != null : "fx:id=\"checkAutentificacionMysql\" was not injected: check your FXML file 'VentanaPrincipalFXML.fxml'.";
        assert toolBar != null : "fx:id=\"toolBar\" was not injected: check your FXML file 'VentanaPrincipalFXML.fxml'.";

        // initialize your logic here: all @FXML variables will have been injected

    }

}
