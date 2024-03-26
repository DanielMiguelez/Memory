/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Menu.menuController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author aronbp
 */
public class gameController implements Initializable {
    
    @FXML
    private Button exitButton;
    
    @FXML
    private FlowPane board;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeImageView();
    }
    
private void initializeImageView()
    {
        for (int i=0; i<board.getChildren().size();i++)
        {
            //"cast" the Node to be of type ImageView
            ImageView imageView = (ImageView) board.getChildren().get(i);
            imageView.setImage(new Image(funcionamientoJuego.Card.class.getResourceAsStream("/media/Luigi1.png")));
            imageView.setUserData(i);

            //register a click listener
            //imageView.setOnMouseClicked(event -> {
            //    flipCard((int) imageView.getUserData());
            //});
        }
    }

public void closeWindows(){
    
     try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/menu.fxml"));
            
            Parent root = loader.load();
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.show();
            
            Stage myStage = (Stage) this.exitButton.getScene().getWindow();
            myStage.close();
            
            
        } catch (IOException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
/*
    private void flipCard(int indexOfCard){
        if (firstCard==null && secondCard==null)
            flipAllCards();

        ImageView imageView = (ImageView) imagesFlowPane.getChildren().get(indexOfCard);

        if (firstCard==null)
        {
            firstCard = cardsInGame.get(indexOfCard);
            imageView.setImage(firstCard.getImage());
        }
        else if (secondCard==null)
        {
            numOfGuess++;
            secondCard = cardsInGame.get(indexOfCard);
            imageView.setImage(secondCard.getImage());
            checkForMatch();
            updateLabels();
        }
    }
*/
}
