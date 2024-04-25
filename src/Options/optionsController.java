/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Options;

import Menu.menuController;
import Game.gameController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aronbp
 */
public class optionsController implements Initializable {

    
    @FXML
    private Label exit;
    @FXML
    private Label styleLabel;
    @FXML
    private Label amountOfCards;
    
    private int index = -1;
    private String[]vecCardStyle = {"DEFAULT", "RETRO", "MODERN", "DARK", "YELLOW", "COLORFUL"};
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void changeStyle(Event e){
        Node n = (Node) e.getSource();
        String nFxId = n.getId();
        //{"DEFAULT", "RETRO", "MODERN", "DARK", "YELLOW", "COLORFUL"};
        if (nFxId.equals("rightStyleArrow")){
            index++;
            if (index>5)
                index=0;
            styleLabel.setText(vecCardStyle[index]);
            gameController.vecSt = index;
        } else if (nFxId.equals("leftStyleArrow")){
            index--;
            if(index<0)
                index=5;
            styleLabel.setText(vecCardStyle[index]);
            gameController.vecSt = index;
        } 
    }
    
    public void changeBoardSize(Event e){
        Node n = (Node)e.getSource();
        String nFxId = n.getId();
        
        if(nFxId.equals("trimBoardSizeBtn")){
            amountOfCards.setText("16 CARDS");
            gameController.size = 16;
        }
        else if (nFxId.equals("expandBoardSizeBtn")){
            amountOfCards.setText("32 CARDS");
            gameController.size = 32;
        }
    }
    
    @FXML
    private void backToMenu(MouseEvent event) {
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/menu.fxml"));
            
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.show();
            
            Stage previousStage = (Stage) ((javafx.scene.Node)event.getSource()).getScene().getWindow();
            previousStage.close();
            
            
        } catch (IOException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
