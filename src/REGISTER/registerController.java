package REGISTER;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    
    private boolean consulta;
    
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
        
        if (user1.length()>0 && password1.length()>=5){
                Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("abpdefault", true);
                String query = String.format("insert into jugadores (name,contraseña) values (" + "'" + user1 + "'" + " , " + "'" + password1 + "'" + ")");
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
                Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("abpdefault", true);
                String query = String.format("insert into jugadores (name,contraseña) values (" + "'" + user2 + "'" + " , " + "'" + password2 + "'" + ")");
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
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("abpdefault", true);
        user1 = labelP1.getText().toLowerCase();
        password1 = passwordP1.getText().toLowerCase();
        
        //String query = String.format("SELECT name, contraseña FROM jugadores WHERE name =" + "'" + user2 + "'" +  AND contraseña = '%s'", , password2);
        String query = String.format("select (name , contraseña) from jugadores where name = " + "'" + user1 + "'" + " AND contraseña=" + "'" + password1 + "'");
        String[][]resultados  = Bd.consultaSelect(gestor, query);
        gestor.cerrar_Conexion(true);
        
         for (int i = 0; i < resultados.length; i++) {
             for (int j = 0; j < resultados.length; j++) {
                 System.out.println(resultados[i][j]);
             }
         }
            
            
        }    
    
     
    
}
