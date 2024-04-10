package REGISTER;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Menu.menuController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utilidades.bbdd.Bd;
import static utilidades.bbdd.Bd.crearBBDD;
import utilidades.bbdd.Gestor_conexion_POSTGRE;

public class registerController implements Initializable {
    
    @FXML
    private TextField labelP1;
    @FXML
    private TextField labelP2;
    @FXML
    private PasswordField passwordP1;
    @FXML
    private PasswordField passwordP2;
    @FXML 
    private Button registerLeft;
    @FXML 
    private Button registerRight;  
    @FXML 
    private Button loginLeft;
    @FXML 
    private Button loginRight;
    
    private boolean consulta;
    boolean logged = false;
    boolean logged2 = false;
    
    String user1;
    String password1;
    String user2;
    String password2;

        
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    @FXML
    public void registerUser1() {
        user1 = labelP1.getText().toLowerCase();
        password1 = passwordP1.getText().toLowerCase();
        
        //SI USUARIO Y CONTRASEÑA SON "VALIDOS"
        if (user1.length()>0 && password1.length()>=5){
                //LOS METEMOS A LA BBDD MEMORY
                Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
                String query = String.format("insert into jugadores (nick_jugador,contraseña) values (" + "'" + user1 + "'" + " , " + "'" + password1 + "'" + ")");
                consulta = Bd.consultaModificacion(gestor, query);
                gestor.cerrar_Conexion(true);
                if (consulta){
                    registerLeft.setStyle("-fx-background-color: green;");
                    labelP1.setStyle("-fx-border-color: green;");
                    }
                else if (!consulta){
                    registerLeft.setStyle("-fx-background-color: red;");
                    labelP1.setStyle("-fx-border-color: red;");
                    }
                }
        else {
            // Establecer estilo de error al botón
            registerLeft.setStyle("-fx-background-color: red;");
            labelP1.setStyle("-fx-border-color: red;");
            }
        }
    
    @FXML
    public void registerUser2() {
        user2 = labelP2.getText().toLowerCase();
        password2 = passwordP2.getText().toLowerCase();
        
          if (user2.length()>0 && password2.length()>=5){
                Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
                String query = String.format("insert into jugadores (nick_jugador,contraseña) values (" + "'" + user2 + "'" + " , " + "'" + password2 + "'" + ")");
                consulta = Bd.consultaModificacion(gestor, query);
                gestor.cerrar_Conexion(true);
                if (consulta){
                    registerRight.setStyle("-fx-background-color: green;");
                    labelP2.setStyle("-fx-border-color: green;");
                }
                else if (!consulta){
                    registerRight.setStyle("-fx-background-color: red;");
                    labelP2.setStyle("-fx-border-color: red;");
                    }
                }
            else {
            // Establecer estilo de error al botón
            registerRight.setStyle("-fx-background-color: red;");
            labelP2.setStyle("-fx-border-color: red;");
            }
        }
    
     public void loginUser1() {
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        user1 = labelP1.getText().toLowerCase();
        password1 = passwordP1.getText().toLowerCase();
        
        String query = String.format("select (nick_jugador , contraseña) from jugadores where nick_jugador = " + "'" + user1 + "'" + " AND contraseña=" + "'" + password1 + "'");
        String[][]resultados   = Bd.consultaSelect(gestor, query);
        gestor.cerrar_Conexion(true);
        
        if (resultados!=null){
                    loginLeft.setStyle("-fx-background-color: green;");
                    labelP1.setStyle("-fx-border-color: green;");
                    logged = true;
//                    if (logged2)
//                        openMenu();
                }
        else {
                    loginLeft.setStyle("-fx-background-color: red;");
                    labelP1.setStyle("-fx-border-color: red;");
                    logged = false;
                }
        }
    
     public void loginUser2() {
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        user2 = labelP2.getText().toLowerCase();
        password2 = passwordP2.getText().toLowerCase();
 
        String query = String.format("select (nick_jugador , contraseña) from jugadores where nick_jugador = " + "'" + user2 + "'" + " AND contraseña=" + "'" + password2 + "'");
        String[][]resultados  = Bd.consultaSelect(gestor, query);
        gestor.cerrar_Conexion(true);
        
        if (resultados!=null){
                    loginRight.setStyle("-fx-background-color: green;");
                    labelP2.setStyle("-fx-border-color: green;");
                    logged = true;
//                    if (logged)
//                        openMenu();
                }
        else {
                    loginRight.setStyle("-fx-background-color: red;");
                    labelP2.setStyle("-fx-border-color: red;");
                    logged = false;
                }
        }    
    
     public void openMenu(ActionEvent event){
//        try {
//            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            currentStage.close();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/menu.fxml"));
//            Parent root = loader.load();
//            Menu.menuController controller = loader.getController();
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException ex) {
//            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }
     
    
}
