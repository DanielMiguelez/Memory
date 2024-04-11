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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import memory.Card;
import memory.Deck;

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
    
    private Deck deck;
    private int tamTab;
    private int desp;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        deck = new Deck();

        Card Mario = new Card("Mario", 1, new Image("/media/mario.png"));
        Card Luigi = new Card("Luigi", 2, new Image("/media/luigi.png"));
        Card Wario = new Card("Wario", 3, new Image("/media/wario.png"));
        Card Toad = new Card("Toad", 4, new Image("/media/toad.png"));
        Card Yoshi = new Card("Yoshi", 5, new Image("/media/yoshi.png"));
        Card Peach = new Card("Peach", 6, new Image("/media/peach.png"));
        Card Bowser = new Card("Bowser", 7, new Image("/media/donkeyKong.png"));
        Card Koopa = new Card("Koopa", 8, new Image("/media/koopa.png"));

        // AGREGAR CARTAS AL MAZO 2 VECES, Y TENEMOS LAS 16.
        deck.addCards(Mario);
        deck.addCards(Luigi);
        deck.addCards(Wario);
        deck.addCards(Toad);
        deck.addCards(Yoshi);
        deck.addCards(Peach);
        deck.addCards(Bowser);
        deck.addCards(Koopa);
        
        deck.addCards(Mario);
        deck.addCards(Luigi);
        deck.addCards(Wario);
        deck.addCards(Toad);
        deck.addCards(Yoshi);
        deck.addCards(Peach);
        deck.addCards(Bowser);
        deck.addCards(Koopa);
    
        tamTab = deck.getCards().size();
        initializeImageView(tamTab);
    }
    
private void initializeImageView(int tamTab)
    {
        if (tamTab==16)
            desp = 8;
        for (int i=0+desp; i<tamTab+desp;i++)
        {
            ImageView imageView = (ImageView) board.getChildren().get(i);
            imageView.setImage(new Image(memory.Card.class.getResourceAsStream("/media/Card.png")));
            imageView.setStyle("-fx-border-color: black; -fx-border-width: 150px; -fx-background-color: transparent;");
            imageView.setUserData(i);

            //Al pulsar una carta te dice su indice de 0 a 15 !
            
            imageView.setOnMouseClicked(event -> {
                int index = (int) imageView.getUserData()-desp;
                System.out.println(index);
                imageView.setImage(deck.getCards().get(index).getImage());
            });
        }
    }

public void closeWindows(ActionEvent event){
    
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

    @FXML
private void hoverCards(MouseEvent event) {

  // Get the ImageView that was hovered over
  ImageView hoveredCard = (ImageView) event.getTarget();

  // Check if the hovered target is actually an ImageView
  if (hoveredCard != null) {
    // Apply shadow effect to the hovered card
    hoveredCard.setEffect(new DropShadow(5.0, 5.0, 5.0, Color.BLACK));
  }
}
@FXML
private void unhoverCards(MouseEvent event) {

  // Get the ImageView that was hovered over
  ImageView hoveredCard = (ImageView) event.getTarget();

  // Check if the hovered target is actually an ImageView
  if (hoveredCard != null) {
    // Apply shadow effect to the hovered card
    hoveredCard.setEffect(null);
        }
    }
}