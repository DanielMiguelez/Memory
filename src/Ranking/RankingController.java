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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Dani
 */
public class RankingController implements Initializable {

    @FXML
    private Button buttonMenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
