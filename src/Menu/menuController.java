/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

import Game.gameController;
import java.io.File;
import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
 * @author aronbp
 */
public class menuController implements Initializable {

    @FXML
    private Button playButton;
    @FXML
    private Button rankingButton;
    @FXML
    private Button settingsButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void playSound(Media sound) {
        
        }
    
    @FXML
    private void openGame(ActionEvent event) {
        try {
            String path = "@/../src/Menu/jump.mp3";
   
            Media sound = new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game/game.fxml"));
            
            Parent root = loader.load();
            
            Game.gameController controller = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.show();
            mediaPlayer.play();
            /*
            stage.setOnCloseRequest(e -> controller.closeWindows());
            
            Stage myStage = (Stage) this.playButton.getScene().getWindow();
            myStage.close();
            */
            
        } catch (IOException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
    }

    @FXML
    private void openRanking(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ranking/ranking.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.show();
           
        } catch (IOException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }

    @FXML
    private void openSettings(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Options/Options.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.show();
           
        } catch (IOException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
