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
import javafx.scene.control.Label;
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
    private Label nameP1;
    @FXML
    private Label nameP2;
    @FXML
     private Label levelP1;
    @FXML
    private Label levelP2;
    @FXML
     private Label victoriesP1;
    @FXML
    private Label victoriesP2;
    @FXML
    private Button exitButton;
    
    @FXML
    private FlowPane board;
     @FXML
    private Label labelPointsP1;
    @FXML
    private Label labelPointsP2;
    
    
    private Deck deck;
    private int tamTab;
    private int desp;
    private int turnoJugador = 1;
    
    private boolean click1 = true;
    private int idCard;
    private int temp;
   
    
    private int pointsP1;
    private int pointsP2;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        deck = new Deck();

        Card Mario = new Card("Mario", 1, new Image("/media/800px-Mario_Mario_Party_Superstars.png"));
        Card Luigi = new Card("Luigi", 2, new Image("/media/Luigi1.png"));
        Card donkeyKong = new Card("donkeyKong", 3, new Image("/media/donkeyKong.png"));
        Card Toad = new Card("Toad", 4, new Image("/media/download.jpg"));
        Card Yoshi = new Card("Yoshi", 5, new Image("/media/setaZul.png"));
        Card Peach = new Card("Peach", 6, new Image("/media/luigiSide_1.png"));
        Card Bowser = new Card("Bowser", 7, new Image("/media/marioSide.png"));
        Card Koopa = new Card("Koopa", 8, new Image("/media/unnamed.png"));

        // AGREGAR CARTAS AL MAZO 2 VECES, Y TENEMOS LAS 16.
        deck.addCards(Mario);
        deck.addCards(Mario);
        deck.addCards(Mario);
        deck.addCards(Mario);
        deck.addCards(Mario);
        deck.addCards(Mario);
        deck.addCards(Mario);
        deck.addCards(Mario);
        deck.addCards(Mario);
        deck.addCards(Mario);
        deck.addCards(Mario);
        deck.addCards(Mario);
        deck.addCards(Mario);
        deck.addCards(Mario);
        deck.addCards(Mario);
        deck.addCards(Mario); 
 
        deck.addCards(Luigi);
        deck.addCards(Luigi);
        deck.addCards(Luigi);
        deck.addCards(Luigi);
        deck.addCards(Luigi);
        deck.addCards(Luigi);
        deck.addCards(Luigi);
        deck.addCards(Luigi);
        deck.addCards(Luigi);
        deck.addCards(Luigi);
        deck.addCards(Luigi);
        deck.addCards(Luigi);
        deck.addCards(Luigi);
        deck.addCards(Luigi);
        deck.addCards(Luigi);
        deck.addCards(Luigi); 
//        deck.addCards(Luigi);
//        deck.addCards(donkeyKong);
//        deck.addCards(Toad);
//        deck.addCards(Yoshi);
//        deck.addCards(Peach);
//        deck.addCards(Bowser);
//        deck.addCards(Koopa);
//        
//        deck.addCards(Mario);
//        deck.addCards(Luigi);
//        deck.addCards(donkeyKong);
//        deck.addCards(Toad);
//        deck.addCards(Yoshi);
//        deck.addCards(Peach);
//        deck.addCards(Bowser);
//        deck.addCards(Koopa);
//        
        deck.shuffle();
    
        tamTab = deck.getCards().size();
        setBackground(tamTab);
        setBoard(tamTab);
        
        
        
        
        
    }
    
    

    private void setBoard(int tamTab){

            if (tamTab==16)
                desp = 8;
            for (int i=0+desp; i<tamTab+desp;i++){
                ImageView imageView = (ImageView) board.getChildren().get(i);
    //            imageView.setImage(new Image(memory.Card.class.getResourceAsStream("/media/Card.png")));
                imageView.setUserData(i);

                //Al pulsar una carta te dice su indice de 0 a 15 !

                imageView.setOnMouseClicked(event -> {
                    int index = (int) imageView.getUserData()-desp;
                    //System.out.println(index);
                    imageView.setImage(deck.getCards().get(index).getImage());

                    if (click1){
                        idCard = deck.getCards().get(index).getId();
                        click1 = false;
                        System.out.println("First : "  + idCard);
                    }
                    else if (!click1){
                        temp = deck.getCards().get(index).getId();
                        click1 = true;
                        System.out.println("Seeegond: "  + temp);
                        compareCards();
                    }


                });
            }
        }
    private void sumarPuntos() {
        if (turnoJugador == 2 ) {
            pointsP2++;
            labelPointsP2.setText("POINTS: " + pointsP2 + "");
            }
        else {
            pointsP1++;
            labelPointsP1.setText("POINTS: " + pointsP1 + "");
        }
    
    }

    public void compareCards(){
        if ( idCard == temp){
            System.out.println("Acertada");
            sumarPuntos();
            }
        else if ( idCard != temp ){
            System.out.println("Fallo");
            System.out.println();
            if (turnoJugador <2 ) {
                turnoJugador += 1;
                System.out.println("Turn: " + turnoJugador);
            }
            else {
                turnoJugador = 1;
                System.out.println("Turn: " + turnoJugador);
                }
            }
        }

    private void setBackground(int tamTab){
            if (tamTab==16)
                desp = 8;
            for (int i=0+desp; i<tamTab+desp;i++){
                ImageView imageView = (ImageView) board.getChildren().get(i);
                imageView.setImage(new Image(memory.Card.class.getResourceAsStream("/media/Card.png")));

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

public void labelNames(String n1, String n2, String l1, String l2, String V1, String V2){
    nameP1.setText(n1);
    nameP2.setText(n2);
    levelP1.setText(levelP1.getText() + " : " + l1);
    levelP2.setText(levelP2.getText() + " : " + l2);
    victoriesP1.setText(victoriesP1.getText() + " : " + V1);
    victoriesP2.setText(victoriesP2.getText() + " : " + V1);
   }


}


