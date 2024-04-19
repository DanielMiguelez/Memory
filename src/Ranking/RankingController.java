    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ranking;

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
import javafx.stage.Stage;
import utilidades.bbdd.Bd;
import utilidades.bbdd.Gestor_conexion_POSTGRE;

/**
 * FXML Controller class
 *
 * @author Dani
 */
public class RankingController implements Initializable {

    @FXML
    private Button buttonMenu;
    @FXML
    private Label firstFromRanking;
    @FXML
    private Label secondFromRanking;
    @FXML
    private Label thirdFromRanking;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[][]r = connectionSelect( getRankingUsers() );
        String first = r[0][0];
        String second = r[1][0];
        String third = r[2][0];
        showRankingUsers(first,second,third);
    }   
    
    public void showRankingUsers(String f, String s, String t){
        firstFromRanking.setText(f);
        secondFromRanking.setText(s);
        thirdFromRanking.setText(t);
    }
    
    public String getRankingUsers(){
        return "select nick_jugador from jugadores j order by victorias_jugador desc limit 3";
    }
    //select nick_jugador 

    
    public String[][] connectionSelect( String q ){
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        String query = String.format(q);
        String[][]result = Bd.consultaSelect(gestor, query);
        gestor.cerrar_Conexion(true);
        return result;
    }

    public void openMenu(){
        try {
//            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/menu.fxml"));
            Parent root = loader.load();
            // Obtener la escena actual y el escenario
            Scene currentScene = buttonMenu.getScene();
            Stage stage = (Stage) currentScene.getWindow();
            // Reemplazar la escena actual con la escena del registro
            currentScene.setRoot(root);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
}
