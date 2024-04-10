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
    
    String user1;
    String password1;
    String user2;
    String password2;
        
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    public void registerUser() {
        user1 = labelP1.getText().toLowerCase();
        user2 = labelP2.getText().toLowerCase();
        password1 = passwordP1.getText().toLowerCase();
        password2 = passwordP2.getText().toLowerCase();

        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("abpdefault", true);
    
        String query = String.format("insert into jugadores (name,contraseña) values (" + " ' " + user1 + " ' " + " , " + " ' " + password1 + " ' " + ")");
        boolean consulta = Bd.consultaModificacion(gestor, query);
        gestor.cerrar_Conexion(true);
        System.out.println(user1 + "  " + password1);
        }
    
//     public void loginUser(String nickName, String password) {
//        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("defaultabp", true);
//    
//        String query = String.format("select jugadores where (name, contraseña) VALUES ("+nickName+password+")");
//    
//        boolean consulta = Bd.consultaModificacion(gestor, query);
//    
//        gestor.cerrar_Conexion(true);
//    }    
    
    
}
