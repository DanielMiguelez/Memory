/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menu;

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
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

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
    @FXML
    private MediaPlayer mediaPlayer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void openGame() {
        try {
           
            String path = "@/../src/media/koopa.mp3";
            
            Media sound = new Media(new File(path).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            
            //Para repetir el sonido en bucle
            /*
            mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            });*/
            
            mediaPlayer.play();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game/game.fxml"));
            
            Parent root = loader.load();
            
            Game.gameController controller = loader.getController();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.show();
            
            
            
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
            
            String path = "@/../src/media/peach.mp3";
   
            Media sound = new Media(new File(path).toURI().toString());
            mediaPlayer = new MediaPlayer(sound);
            
            /*
            mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            });*/
            
            mediaPlayer.play();
           
        } catch (IOException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    @FXML
    public void registerPress(KeyEvent event) {
        if (event.getCode() == KeyCode.A) {
            System.out.println("PRIMER");
            // Aquí abrirías la nueva ventana
            openRules();
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

    private void openRules() {
        System.out.println("SEEEEGOND");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/Rules.fxml"));
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
