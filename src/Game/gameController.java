/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Menu.menuController;
import REGISTER.registerController;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    
    @FXML
    private Deck deck;
    
    @FXML
    private Label nameP1;
    
    @FXML
    private Label nameP2;
    
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
        
        //REPETIDAS 3 VECES
        deck.addCards(Mario);
        deck.addCards(Luigi);
        deck.addCards(Wario);
        deck.addCards(Toad);
        deck.addCards(Yoshi);
        deck.addCards(Peach);
        deck.addCards(Bowser);
        deck.addCards(Koopa); 
//        deck.addCards(Mario);
//        deck.addCards(Luigi);
//        deck.addCards(Wario);
//        deck.addCards(Toad);
//        deck.addCards(Yoshi);
//        deck.addCards(Peach);
//        deck.addCards(Bowser);
//        deck.addCards(Koopa);
     
        
        // Barajar el mazo
        deck.shuffle();
        
        tamTab = deck.getCards().size();
       
        
        initializeImageView(tamTab);
    }
    
    public void setNameP1(String n1) {
        nameP1.setText(n1);
        }
    public void setNameP2(String n2) {
        nameP2.setText(n2);
        }
    
private void initializeImageView(int tamTab)
    {
        
        
        
        if (tamTab==16)
            desp = 8;
        for (int i=0+desp; i<tamTab+desp;i++)
        {
            //"cast" the Node to be of type ImageView
            ImageView imageView = (ImageView) board.getChildren().get(i-desp);
            imageView.setImage(new Image(memory.Card.class.getResourceAsStream("/media/Card.png")));
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
