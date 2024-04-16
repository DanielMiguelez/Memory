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
import javafx.animation.PauseTransition;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import utilidades.bbdd.Bd;
import utilidades.bbdd.Gestor_conexion_POSTGRE;

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
    private AnchorPane winnerPane;
    @FXML
    private Label labelPointsP1;
    @FXML
    private Label labelPointsP2;
    @FXML
    private Label rankingBtn;
    
    private Deck deck;
    private int tamTab;
    private int desp;
    private int turnoJugador = 1;
    
    private boolean click1 = true;
    private int idCard;
    private int temp;
    
    private int indexCard1;
    private int indexCard2;

    private boolean[] indexUsed;
    private int aciertos = 0;
    private boolean gameFinished = false;
    
    private int pointsP1;
    private int pointsP2;
    private String winner;
    
    public String incrementWins(String winner){
        return "update jugadores\n" + "set victorias_jugador  = victorias_jugador + 1\n" + "where nick_jugador = '"+winner+"'";
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        deck = new Deck();

        Card Mario = new Card("Mario", 1, new Image("/media/Mario.png"));
        Card Luigi = new Card("Luigi", 2, new Image("/media/Luigi1.png"));
//        Card donkeyKong = new Card("donkeyKong", 3, new Image("/media/donkeyKong.png"));
//        Card Toad = new Card("Toad", 4, new Image("/media/download.jpg"));
//        Card Yoshi = new Card("Yoshi", 5, new Image("/media/setaZul.png"));
//        Card Peach = new Card("Peach", 6, new Image("/media/luigiSide_1.png"));
//        Card Bowser = new Card("Bowser", 7, new Image("/media/marioSide.png"));
//        Card Koopa = new Card("Koopa", 8, new Image("/media/unnamed.png"));

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
        deck.shuffle();
    
        tamTab = deck.getCards().size();
        setBackground(tamTab);
        setBoard(tamTab);
        
       indexUsed = new boolean [tamTab];
    }
    
    
    
    public boolean openConnection( String q ){
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        String query = String.format(q);
        boolean verify = Bd.consultaModificacion(gestor, query);
        gestor.cerrar_Conexion(true);
        return verify;
    }
    
    @FXML
    private void openRanking() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Ranking/ranking.fxml"));
            Parent root = loader.load();
            // Obtener la escena actual y el escenario
            Scene currentScene = board.getScene();
            Stage stage = (Stage) currentScene.getWindow();

            // Reemplazar la escena actual con la escena del registro
            currentScene.setRoot(root);
            stage.show();
           
        } catch (IOException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    @FXML
    private void openMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/menu.fxml"));
            Parent root = loader.load();
            // Obtener la escena actual y el escenario
            Scene currentScene = board.getScene();
            Stage stage = (Stage) currentScene.getWindow();

            // Reemplazar la escena actual con la escena del registro
            currentScene.setRoot(root);
            stage.show();
           
        } catch (IOException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    @FXML
    private void openGameAgain() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game/game.fxml"));
            Parent root = loader.load();
            // Obtener la escena actual y el escenario
            Scene currentScene = board.getScene();
            Stage stage = (Stage) currentScene.getWindow();
            
            // Reemplazar la escena actual con la escena del registro
            currentScene.setRoot(root);
            stage.hide();
            stage.show();
           
        } catch (IOException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void setBoard(int tamTab){
            if (tamTab==16)
                desp = 8;
            for (int i=0+desp; i<tamTab+desp;i++){
                ImageView imageView = (ImageView) board.getChildren().get(i);
    //            imageView.setImage(new Image(memory.Card.class.getResourceAsStream("/media/Card.png")));
                imageView.setUserData(i);

                imageView.setOnMouseClicked(event -> {
                    int index = (int) imageView.getUserData()-desp;
                    //System.out.println(index);
                    imageView.setImage(deck.getCards().get(index).getImage());
       
                    if (click1){
                        idCard = deck.getCards().get(index).getId();
                        indexCard1 = index;
                        click1 = false;
                        System.out.println("First : "  + idCard);
                    }
                    else if (!click1){
                        temp = deck.getCards().get(index).getId();
                        indexCard2 = index;
                        if ( indexCard1 != indexCard2 && indexUsed[indexCard1] == false && indexUsed[indexCard2] == false){
                            click1 = true;
                            System.out.println("Seeegond: "  + temp);
                            compareCards(index);
                            if(gameFinished)
                                score();
                        }
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

    private void score(){
        System.out.println("El juego ha terminado!!");
        winnerPane.setVisible(true);
        if (pointsP1 == pointsP2){
        System.out.print("Los jugadores han empatado");
        }else if ( pointsP1 > pointsP2){
            winner = nameP1.getText();
            openConnection( incrementWins(winner) );
        }else{
            winner = nameP2.getText();
            openConnection( incrementWins(winner) );
        }
     }
    
    
    
    public void compareCards(int i){
        if ( idCard == temp){
            indexUsed[indexCard1] = true;
            indexUsed[indexCard2] = true;
            aciertos += 2;
            if ( aciertos == tamTab)
                gameFinished = true;
            System.out.println("Acertada");
            sumarPuntos();
            }
        else if ( idCard != temp ){
            flipCards();
            board.setDisable(true); // Bloquear el FlowPane
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> {
            board.setDisable(false); // Desbloquear el FlowPane después de 2 segundos
            });
            pause.play();
            System.out.println("Fallo");
            System.out.println();
            turnoJugadores();
        }  
    }
    
    private void turnoJugadores (){
        if (turnoJugador <2 ) {
            turnoJugador += 1;
            System.out.println("Turn: " + turnoJugador);
        }
        else {
            turnoJugador = 1;
            System.out.println("Turn: " + turnoJugador);
        }
    }
    
    private void flipCards(){
       PauseTransition pause = new PauseTransition(Duration.seconds(2));
                    pause.setOnFinished(e -> {
            ImageView imageView = (ImageView) board.getChildren().get(indexCard1);
            imageView.setImage(new Image(memory.Card.class.getResourceAsStream("/media/Card.png")));
            imageView = (ImageView) board.getChildren().get(indexCard2);
            imageView.setImage(new Image(memory.Card.class.getResourceAsStream("/media/Card.png")));
            
        });
        pause.play();
    }
    
    private void setBackground(int tamTab){
        if (tamTab==16)
            desp = 8;
        for (int i=0+desp; i<tamTab+desp;i++){
            ImageView imageView = (ImageView) board.getChildren().get(i);
            imageView.setImage(new Image(memory.Card.class.getResourceAsStream("/media/Card.png")));
        }
    }


    @FXML
    private void hoverNode(MouseEvent event) {
        Node node =  (Node) event.getTarget();
        if (node != null) {
            node.setEffect(new DropShadow(15, 5, 5, Color.BLACK));
        }
    }
    @FXML
    private void unhoverNode(MouseEvent event) {
      Node node =  (Node) event.getTarget();
      if (node != null) {
        node.setEffect(null);
            }
        }
    @FXML
    private void hoverNodeBright(MouseEvent event) {
        Node node =  (Node) event.getTarget();
        if (node != null) {
            node.setEffect(new DropShadow(15, 0, 0, Color.WHITE));
        }
    }




}