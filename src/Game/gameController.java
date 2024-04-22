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
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
/**
 * FXML Controller class
 *
 * @author aronbp
 */
public class gameController implements Initializable {
   
    @FXML
    private Label rankingBtn;
    @FXML
    private FlowPane board;
    @FXML
    private AnchorPane winnerPane;
    
    
    //INFO 4 USUARIOS
    @FXML
    public AnchorPane anchorPlayer1;
    @FXML
    public AnchorPane anchorPlayer2;
    @FXML
    public AnchorPane anchorPlayer3;
    @FXML
    public AnchorPane anchorPlayer4;
    @FXML
    private Label nameP1;
    @FXML
    private Label nameP2;
    @FXML
    private Label nameP3;
    @FXML
    private Label nameP4;
    @FXML
    private Label levelPlayer1;
    @FXML
    private Label levelPlayer2;
    @FXML
    private Label levelPlayer3;
    @FXML
    private Label levelPlayer4;
    @FXML
    private Label winsPlayer1;
    @FXML
    private Label winsPlayer2;
    @FXML
    private Label winsPlayer3;
    @FXML
    private Label winsPlayer4;
    @FXML
    private Label labelPointsP1;
    @FXML
    private Label labelPointsP2;
    @FXML
    private Label labelPointsP3;
    @FXML
    private Label labelPointsP4;
    //INFO 4 USUARIOS
    private Deck deck;
    private int boardSize;
    private int desp;
    private int playerTurn = 1;
    
    private boolean click1 = true;
    private int idCard;
    private int idSecondCard;
    
    private int indexCard1;
    private int indexCard2;

    private boolean[]indexUsed;
    private int matches = 0;
    private boolean gameFinished = false;
    
    private int pointsP1;
    private int pointsP2;
    private int pointsP3;
    private int pointsP4;
    
    private String winner;
    
    private int seconds = 0;
    private int minutes = 0;
    private Timeline timeline;
    
    @FXML
    private Label timer;
    
    @FXML
    private Label nameP11;
    @FXML
    private Label nameP21;
    @FXML
    private Label rankingBtn1;
    @FXML
    private ImageView winnerPicture;
    @FXML
    private Label winnerName;
    @FXML
    private Label playAgainBtn;
    @FXML
    private Label menuBtn;
    @FXML
    private Button botonEnviar;
    @FXML
    private TextField texto;
    @FXML 
    private TextFlow chat;
    
    private int numTurns;
    
    String[][]winsP1 = new String [0][1];
    String[][]winsP2 = new String [0][1];
    String[][]winsP3 = new String [0][1];
    String[][]winsP4 = new String [0][1];
    
    public String incrementWins(String winner){
        return "update jugadores\n" + "set victorias_jugador  = victorias_jugador + 1\n" + "where nick_jugador = '"+winner+"'";
    }
    public String getUserVictories(String user){
        return "select victorias_jugador from jugadores where nick_jugador = '" + user + "'";
    }
    
//    public String getWins(){
//        return 
//    }
    
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
        
        boardSize = deck.getCards().size();
        setBackground(boardSize);
        setBoard(boardSize);
        
       indexUsed = new boolean [boardSize];
       winnerPane.setVisible(false);
       startTime();
       System.out.println(numTurns);
    }
    
    @FXML
    private void sendToChat(){
    String t = "\n";
    t += texto.getText(); // Suponiendo que 'texto' es un objeto TextField o similar donde el usuario ingresa el texto
    // Creamos un objeto Text con el texto ingresado por el usuario
    Text nuevoTexto = new Text(t);
    nuevoTexto.setFill(Color.WHEAT);
    nuevoTexto.setFont(Font.font("Gill Sans Ultra Bold", 12));// Agregamos el nuevo objeto Text al TextFlow 'chat'
    chat.setVisible(true);
    chat.getChildren().add(nuevoTexto);
    }
    
    public boolean connectionSet( String q ){
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        String query = String.format(q);
        boolean verify = Bd.consultaModificacion(gestor, query);
        gestor.cerrar_Conexion(true);
        return verify;
    }
    
    public String[][] connectionSelect( String q ){
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        String query = String.format(q);
        String[][]result = Bd.consultaSelect(gestor, query);
        gestor.cerrar_Conexion(true);
        return result;
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
            gameController game = loader.getController();
            
            if ( playerTurn == 4 ){
                anchorPlayer1.setVisible(true);
                anchorPlayer2.setVisible(true);
                anchorPlayer3.setVisible(true);
                anchorPlayer4.setVisible(true);
                winsP1 = connectionSelect(getUserVictories( nameP1.getText())); 
                winsP2 = connectionSelect(getUserVictories( nameP2.getText()));
                winsP3 = connectionSelect(getUserVictories( nameP3.getText())); 
                winsP4 = connectionSelect(getUserVictories( nameP4.getText()));
            }
            if ( playerTurn == 3 ){
                anchorPlayer1.setVisible(true);
                anchorPlayer2.setVisible(true);
                anchorPlayer3.setVisible(true);
                winsP1 = connectionSelect(getUserVictories( nameP1.getText())); 
                winsP2 = connectionSelect(getUserVictories( nameP2.getText()));
                winsP3 = connectionSelect(getUserVictories( nameP3.getText()));
            }
            if (playerTurn == 2 ){
                anchorPlayer1.setVisible(true);
                anchorPlayer2.setVisible(true);
                winsP1 = connectionSelect(getUserVictories( nameP1.getText())); 
                winsP2 = connectionSelect(getUserVictories( nameP2.getText()));
            }
            else{
               anchorPlayer1.setVisible(true);
                winsP1 = connectionSelect(getUserVictories( nameP1.getText())); 
            }
            
            game.setNumTurns(numTurns);
            game.labelNames(nameP1.getText(),nameP2.getText(),nameP3.getText(),nameP4.getText(),winsP1[0][0],winsP2[0][0],winsP3[0][0],winsP4[0][0]);
            // Reemplazar la escena actual con la escena del registro
            currentScene.setRoot(root);
            stage.hide();
            stage.show();
           
        } catch (IOException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    
    public void labelNames(String n1,String n2,String n3,String n4, String winsP1, String winsP2,String winsP3, String winsP4){
        nameP1.setText(n1);
        nameP2.setText(n2);
        nameP3.setText(n3);
        nameP4.setText(n4);
        winsPlayer1.setText(winsP1);
        winsPlayer2.setText(winsP2);
        winsPlayer3.setText(winsP3);
        winsPlayer4.setText(winsP4);
    }

    private void setBoard(int boardSize){
            if (boardSize==16)
                desp = 8;
            for (int i=0+desp; i<boardSize+desp;i++){
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
                        idSecondCard = deck.getCards().get(index).getId();
                        indexCard2 = index;
                        if ( indexCard1 != indexCard2 && indexUsed[indexCard1] == false && indexUsed[indexCard2] == false){
                            click1 = true;
                            System.out.println("Seeegond: "  + idSecondCard);
                            compareCards(index);
                            if(gameFinished){
                                pauseTime();
                                score();
                            }    
                        }
                    }


                });
            }
        }
    private void sumarPuntos() {
        switch (playerTurn) {
            case 1:
                pointsP1++;
                labelPointsP1.setText(pointsP1 + "");
                break;
            case 2:
                pointsP2++;
                labelPointsP2.setText(pointsP2 + "");
                break;
            case 3:
                pointsP3++;
                labelPointsP3.setText(pointsP3 + "");
                break;
            case 4:
                pointsP4++;
                labelPointsP4.setText(pointsP4 + "");
                break;
            default:
                break;
        }
    }

    private void score(){
        System.out.println("El juego ha terminado!!");
        winnerPane.setVisible(true);

        if (pointsP1 > pointsP2 && pointsP1 > pointsP3 && pointsP1 > pointsP4){
            winner = nameP1.getText();
            connectionSet( incrementWins(winner) );
        }else if ( pointsP2 > pointsP3 && pointsP2 > pointsP4 && pointsP2 > pointsP1){
            winner = nameP2.getText();
            connectionSet( incrementWins(winner) );
        }else if (pointsP3 > pointsP2 && pointsP3 > pointsP4 && pointsP3 > pointsP1){
            winner = nameP3.getText();
            connectionSet( incrementWins(winner) );
        }else if (pointsP4 > pointsP3 && pointsP4 > pointsP2 && pointsP4 > pointsP1){
            winner = nameP4.getText();
            connectionSet( incrementWins(winner) );
        }else {
            winnerName.setText("DRAW");
            winnerPicture.setVisible(false);
        }
     }
    
    public void compareCards(int i){
        if ( idCard == idSecondCard){
            indexUsed[indexCard1] = true;
            indexUsed[indexCard2] = true;
            matches += 2;
            if ( matches == boardSize)
                gameFinished = true;
            System.out.println("Acertada");
            sumarPuntos();
            }
        else if ( idCard != idSecondCard ){
            coverCards();
            board.setDisable(true); // Bloquear el FlowPane
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> {
            board.setDisable(false); // Desbloquear el FlowPane después de 2 segundos
            });
            pause.play();
            System.out.println("Fallo");
            System.out.println();
            playerTurns();
        }  
    }
    public void setNumTurns(int numTurns) {
        this.numTurns = numTurns;    
    }

    
    private void playerTurns (){

        if (playerTurn < numTurns ) {
            playerTurn += 1;
            System.out.println("Turn: " + playerTurn);
        }
        else {
            playerTurn = 1;
            System.out.println("Turn: " + playerTurn);
        }
    }
    
    private void coverCards(){
       PauseTransition pause = new PauseTransition(Duration.seconds(1));
                    pause.setOnFinished(e -> {
            ImageView imageView = (ImageView) board.getChildren().get(indexCard1);
            imageView.setImage(new Image(memory.Card.class.getResourceAsStream("/media/Card.png")));
            imageView = (ImageView) board.getChildren().get(indexCard2);
            imageView.setImage(new Image(memory.Card.class.getResourceAsStream("/media/Card.png")));
            
        });
        pause.play();
    }
    
    private void setBackground(int boardSize){
        if (boardSize==16)
            desp = 8;
        for (int i=0+desp; i<boardSize+desp;i++){
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

    private void startTime() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            
            
            timer.setText("Time "+(minutes<10?" 0":" ")+ minutes +":"+(seconds<10?"0":"")+ seconds);
            seconds++;
            if (seconds == 60){
                minutes++;
                seconds = 0;    
            }    
        }));
        
        System.out.println("Timer on");
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    
    private void pauseTime(){
        if(timeline != null){
            timeline.stop();
            System.out.println("Timer off");
        }
    }

  


}