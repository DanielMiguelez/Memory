/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Menu.menuController;
import REGISTER.registerController;
//import static REGISTER.registerController.cpuLevels;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Random;
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
import javafx.event.Event;
import javafx.scene.control.TextArea;
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
    
    //retro, modern, dark, yellow, colorfull

   
    
    @FXML
    private AnchorPane pausePopUp;
    @FXML
    private Label rankingBtn;
    @FXML
    private FlowPane board;
    @FXML
    private FlowPane player1COINS;
    @FXML
    private FlowPane player2COINS;
    @FXML
    private FlowPane player3COINS;
    @FXML
    private FlowPane player4COINS;
    @FXML
    private FlowPane player1BLUECOINS;
    @FXML
    private FlowPane player2BLUECOINS;
    @FXML
    private FlowPane player3BLUECOINS;
    @FXML
    private FlowPane player4BLUECOINS;
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
    
    private Label[] winsPlayer;
    
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
    private int playerTurn = 0;

    private boolean click1 = true;
    private int idCard;
    private int idSecondCard;

    private int indexCard1;
    private int indexCard2;

    private boolean[] indexUsed;
    private int matches = 0;
    private boolean gameFinished = false;

    private int pointsP1;
    private int pointsP2;
    private int pointsP3;
    private int pointsP4;
    
    @FXML
    public static ImageView gameBackground;
        
    @FXML
    private ImageView imgPlayer1;
    @FXML
    private ImageView imgPlayer2;
    @FXML
    private ImageView imgPlayer3;
    @FXML
    private ImageView imgPlayer4;

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
    private TextField player1Comments;
    @FXML
    private TextField player2Comments;
    @FXML
    private TextField player3Comments;
    @FXML
    private TextField player4Comments;
    @FXML
    private TextFlow chat;
    @FXML
    private TextArea chatAlt;

    private int numTurns;
    private MediaPlayer mediaPlayer;
    
    public static int size = 32;

    String[][] winsP1;
    String[][] winsP2;
    String[][] winsP3;
    String[][] winsP4;
    
    Image[]cardStyles = new Image[6];
    
    public static int vecSt = -1;

    public String incrementWins(String winner) {
        return "update jugadores\n" + "set victorias_jugador  = victorias_jugador + 1\n" + "where nick_jugador = '" + winner + "'";
    }

    public String getUserVictories(String user) {
        return "select victorias_jugador from jugadores where nick_jugador = '" + user + "'";
    }
    
    public String createMatch() {
        LocalDate date = LocalDate.now();
        return "INSERT INTO partidas(fecha) VALUES( '" + date + "')";
    }
    
   public String insertDataGames(String winner) {
        LocalDate time = LocalDate.now();
        return "UPDATE partidas SET ganador_id = (SELECT id_jugador FROM jugadores WHERE nick_jugador = '" + winner + "') , duracion = '" + (minutes + ":" + seconds) + "', fecha = '" + time + "' WHERE id_partida = (SELECT MAX(id_partida) FROM partidas)";
    }

    public String insertCommentsDB(String nick, String comment) {
        return "INSERT INTO comentarios (id_jugador,id_partida,contenido) \n" +
                "SELECT j.id_jugador, p.id_partida, '" + comment + "' " +
                "FROM jugadores j " +
                "LEFT JOIN partidas p ON p.id_partida = id_partida  " +
                "WHERE j.nick_jugador = '" + nick + "' AND id_partida = (SELECT MAX(id_partida) FROM partidas)" ;

}

// public String getLastPartidaId(){
//        return "select id_partida FROM partidas ORDER BY fecha DESC LIMIT 1";
//    }
    

//   return "INSERT INTO partidas (ganador_id, duracion, fecha) SELECT id_jugador,
    //'" + (minutes + ":" + seconds) + "','" + time + "' FROM jugadores WHERE nick_jugador = '" + winner + "'";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (vecSt == -1)
            vecSt = 0;
       
        cardStyles[0] = new Image("/media/Card.png"); 
        cardStyles[1] = new Image("/media/retro.png"); 
        cardStyles[2] = new Image("/media/modern.png"); 
        cardStyles[3] = new Image("/media/dark.png"); 
        cardStyles[4] = new Image("/media/yellow.png"); 
        cardStyles[5] = new Image("/media/colorful.png"); 
        
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
        if (size == 32){
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
        } else if (size == 16){
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
        }
        deck.shuffle();
        boardSize = deck.getCards().size();
        
        setBackground(boardSize);
        setBoard(boardSize);
        indexUsed = new boolean[boardSize];
        winnerPane.setVisible(false);
        startTime();
        connectionSet(createMatch());
        playerTurns();
        

    }
    
    @FXML
    public void openPopUp(){
        pausePopUp.setVisible(true);
        timeline.stop();
    }
    
    @FXML
    public void closePopUp(){
        pausePopUp.setVisible(false);
        timeline.play();
    }

    @FXML
    private void sendToChat(Event e) {
        Object o = e.getSource();
        Node n = (Node) o;
        String oFxId = n.getId();
       
        String t = "";
        chat.setVisible(true);
        Text nuevoTexto = new Text();
         // Creamos un objeto Text con el texto que será ingresado por el usuario
        switch (oFxId){
            case "sendToChatP1":
                nuevoTexto = new Text ( nameP1.getText() + ": " + player1Comments.getText() +"\n" );
                connectionSet(insertCommentsDB(nameP1.getText(), player1Comments.getText()));
                player1Comments.setText("");
                nuevoTexto.setFill(Color.RED);
                break;
            case "sendToChatP2":
                nuevoTexto = new Text ( nameP2.getText() + ": " +player2Comments.getText() +"\n" );
                connectionSet(insertCommentsDB(nameP2.getText(), player2Comments.getText()));
                player2Comments.setText("");
                nuevoTexto.setFill(Color.BLUE);
                break;
            case "sendToChatP3":
                nuevoTexto = new Text ( nameP3.getText() + ": " +player3Comments.getText() +"\n" );
                connectionSet(insertCommentsDB(nameP3.getText(), player3Comments.getText()));
                player3Comments.setText("");
                nuevoTexto.setFill(Color.YELLOW);
                break;
            case "sendToChatP4":
                nuevoTexto = new Text ( nameP4.getText() + ": " +player4Comments.getText() +"\n" );
                connectionSet(insertCommentsDB(nameP4.getText(), player4Comments.getText()));
                player4Comments.setText("");
                nuevoTexto.setFill(Color.GREEN);
                break;
        }
        nuevoTexto.setFont(Font.font("Gill Sans Ultra Bold", 14));
        // Agregamos el nuevo objeto Text al TextFlow 'chat'
        if (chat.getChildren().size()<7)
            chat.getChildren().add(nuevoTexto);
        else {
            chat.getChildren().remove(0);
            chat.getChildren().add(nuevoTexto);
            }
        }
    

    public boolean connectionSet(String q) {
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        String query = String.format(q);
        boolean verify = Bd.consultaModificacion(gestor, query);
        gestor.cerrar_Conexion(true);
        return verify;
    }

    public String[][] connectionSelect(String q) {
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        String query = String.format(q);
        String[][] result = Bd.consultaSelect(gestor, query);
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
            System.out.println(numTurns);

            stage.hide();
            stage.show();

            if (numTurns > 3) {
                game.anchorPlayer4.setVisible(true);
                if (winsPlayer4.getText().equals("CPU")) {
                    winsP4 = new String[][]{{"CPU"}};
                } else {
                    winsP4 = connectionSelect(getUserVictories(nameP4.getText()));
                }
            }
            if (numTurns > 2) {
                game.anchorPlayer3.setVisible(true);
                if (winsPlayer3.getText().equals("CPU")) {
                    winsP3 = new String[][]{{"CPU"}};
                } else {
                    winsP3 = connectionSelect(getUserVictories(nameP3.getText()));
                }
            }
            if (numTurns > 1) {
                game.anchorPlayer2.setVisible(true);
                if (winsPlayer2.getText().equals("CPU")) {
                    winsP2 = new String[][]{{"CPU"}};
                } else {
                    winsP2 = connectionSelect(getUserVictories(nameP2.getText()));
                }
            }
            if (numTurns > 0) {
                game.anchorPlayer1.setVisible(true);
                if (winsPlayer1.getText().equals("CPU")) {
                    winsP1 = new String[][]{{"CPU"}};
                } else {
                    winsP1 = connectionSelect(getUserVictories(nameP1.getText()));
                }
            }

            game.setNumTurns(numTurns);

            if (numTurns == 1) {
                game.labelNames(nameP1.getText(), winsP1[0][0]);
            }
            if (numTurns == 2) {
                game.labelNames(nameP1.getText(), nameP2.getText(), winsP1[0][0], winsP2[0][0]);
            }
            if (numTurns == 3) {
                game.labelNames(nameP1.getText(), nameP2.getText(), nameP3.getText(), winsP1[0][0], winsP2[0][0], winsP3[0][0]);
            }
            if (numTurns == 4) {
                game.labelNames(nameP1.getText(), nameP2.getText(), nameP3.getText(), nameP4.getText(), winsP1[0][0], winsP2[0][0], winsP3[0][0], winsP4[0][0]);
            }
            currentScene.setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void labelNames(String n1, String n2, String n3, String n4, String winsP1, String winsP2, String winsP3, String winsP4) {
        nameP1.setText(n1);
        nameP2.setText(n2);
        nameP3.setText(n3);
        nameP4.setText(n4);
        winsPlayer1.setText(winsP1);
        winsPlayer2.setText(winsP2);
        winsPlayer3.setText(winsP3);
        winsPlayer4.setText(winsP4);
    }

    public void labelNames(String n1, String n2, String n3, String winsP1, String winsP2, String winsP3) {
        nameP1.setText(n1);
        nameP2.setText(n2);
        nameP3.setText(n3);
        winsPlayer1.setText(winsP1);
        winsPlayer2.setText(winsP2);
        winsPlayer3.setText(winsP3);
    }

    public void labelNames(String n1, String n2, String winsP1, String winsP2) {
        nameP1.setText(n1);
        nameP2.setText(n2);
        winsPlayer1.setText(winsP1);
        winsPlayer2.setText(winsP2);
    }

    public void labelNames(String n1, String winsP1) {
        nameP1.setText(n1);
        winsPlayer1.setText(winsP1);
    }

    private void setBoard(int boardSize) {
        if (boardSize == 16) {
            desp = 8;
        } else {
            desp = 0;
        }
        for (int i = 0 + desp; i < boardSize + desp; i++) {
            ImageView imageView = (ImageView) board.getChildren().get(i);
            imageView.setUserData(i);
             imageView.setOnMouseClicked(event -> {
                int index = (int) imageView.getUserData() - desp;
                //System.out.println(index);
                imageView.setImage(deck.getCards().get(index).getImage());

                if (click1) {
                    indexCard1 = index;
                    if (indexUsed[indexCard1] == false) {
                        idCard = deck.getCards().get(index).getId();
                        click1 = false;
                        System.out.println("First : " + idCard);
                    }
                } else if (!click1) {
                    idSecondCard = deck.getCards().get(index).getId();
                    indexCard2 = index;
                    if (indexCard1 != indexCard2 && indexUsed[indexCard2] == false) {
                        click1 = true;
                        System.out.println("Seeegond: " + idSecondCard);
                        compareCards(index);
                        if (gameFinished) {
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
                    if (pointsP1<9)
                        player1COINS.getChildren().get(pointsP1-1).setVisible(true);
                    else {
                        player1COINS.getChildren().get(pointsP1-9).setVisible(false);                        
                        player1BLUECOINS.getChildren().get(pointsP1-9).setVisible(true);
                        }
                    break;
            case 2:
                pointsP2++;
                    if (pointsP2<9)
                    player2COINS.getChildren().get(pointsP2-1).setVisible(true);
                    else{
                        player2COINS.getChildren().get(pointsP2-9).setVisible(false);                        
                        player2BLUECOINS.getChildren().get(pointsP2-9).setVisible(true);
                    }
                break;
            case 3:
                pointsP3++;
                    if (pointsP3<9)
                    player3COINS.getChildren().get(pointsP3-1).setVisible(true);
                    else{
                        player3COINS.getChildren().get(pointsP3-9).setVisible(false);                        
                        player3BLUECOINS.getChildren().get(pointsP3-9).setVisible(true);
                    }
                break;
            case 4:
                pointsP4++;
                    if (pointsP4<9)
                    player4COINS.getChildren().get(pointsP4-1).setVisible(true);
                    else {
                        player4COINS.getChildren().get(pointsP4-9).setVisible(false);                        
                        player4BLUECOINS.getChildren().get(pointsP4-9).setVisible(true);
                    }
                break;
            default:
                break;
        }
    }

    private void score() {
        String winner;
        System.out.println("El juego ha terminado!!");
        winnerPane.setVisible(true);
        System.out.println("P1: " + pointsP1 + "P2: " + pointsP2 + "P3: " + pointsP3 + "P4: " + pointsP4);

        if (pointsP1 > pointsP2 && pointsP1 > pointsP3 && pointsP1 > pointsP4) {
            winnerPicture.setImage(new Image(memory.Card.class.getResourceAsStream("/media/luigiSide.png")));
            winnerName.setText(nameP1.getText());
            insertDataGames(nameP1.getText());
            connectionSet(insertDataGames(winnerName.getText()));
            if (winsPlayer1.getText().equals("CPU")) {

            } else {
                connectionSet(incrementWins(nameP1.getText()));
            }
        } else if (pointsP2 > pointsP3 && pointsP2 > pointsP4 && pointsP2 > pointsP1) {
            winnerPicture.setImage(new Image(memory.Card.class.getResourceAsStream("/media/GreenMushroom.png")));
            winnerName.setText(nameP2.getText());
            connectionSet(insertDataGames(winnerName.getText()));
            if (winsPlayer2.getText().equals("CPU")) {

            } else {
                connectionSet(incrementWins(nameP2.getText()));
            }
        } else if (pointsP3 > pointsP2 && pointsP3 > pointsP4 && pointsP3 > pointsP1) {
            winnerPicture.setImage(new Image(memory.Card.class.getResourceAsStream("/media/marioSide.png")));
            winnerName.setText(nameP3.getText());
            connectionSet(insertDataGames(winnerName.getText()));
            if (winsPlayer3.getText().equals("CPU")) {

            } else {
                connectionSet(incrementWins(nameP3.getText()));
            }
        } else if (pointsP4 > pointsP3 && pointsP4 > pointsP2 && pointsP4 > pointsP1) {
            winnerPicture.setImage(new Image(memory.Card.class.getResourceAsStream("/media/Mushroom1.png")));
            winnerName.setText(nameP4.getText());
            connectionSet(insertDataGames(winnerName.getText()));
            if (winsPlayer4.getText().equals("CPU")) {
            } else {
                connectionSet(incrementWins(nameP4.getText()));
            }
        } else {
            winnerName.setText("DRAW");
            winnerPicture.setImage(new Image(memory.Card.class.getResourceAsStream("/media/cruz.png")));
            connectionSet(insertDataGames(""));
        }
    }

    public void compareCards(int i) {
        if (idCard == idSecondCard) {
            indexUsed[indexCard1] = true;
            indexUsed[indexCard2] = true;
            matches += 2;
            for (int j = 0; j < boardSize; j++) {
                if(deck.getCards().get(i).getId() == idCard)
                    deck.getCards().get(i).setTurned();
            }
            if (matches == boardSize) {
                gameFinished = true;
            }
            System.out.println(matches);
            sumarPuntos();
        } else if (idCard != idSecondCard) {
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
    
    private void playerTurns(){
        winsPlayer = new Label[] {winsPlayer1,winsPlayer2,winsPlayer3,winsPlayer4};
            if (playerTurn < numTurns) {
                playerTurn += 1;
                System.out.println("Turn: " + playerTurn);
            } else {
                playerTurn = 1;
                System.out.println("Turn: " + playerTurn);
            }
            if(winsPlayer[playerTurn-1].getText().equals("CPU"))
                logicaCPU();
            }
    
public void logicaCPU(){
    String mode = registerController.cpuLevels[playerTurn-1];
    Random rnd = new Random(); 
    boolean cpuTurnEnded = false;
    
    switch (mode){
        case "hard":
            //Declarar a = turnosAciertos (2-3) 
            //Hace un random de 0 a boardSize hasta que getTurned(), método de DECK, devuelva false;
            //Ese random será una carta no girada, busca la otra en el tablero comparando por ID y la levanta
            break;
        case "medium":
            break;
        case "easy":
            break;
    }
}

private void pauseGame(){
    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
    pause.setOnFinished(e -> {
            board.setDisable(false); // Desbloquear el FlowPane después de 2 segundos
        });
    pause.play();
    }
    private void coverCards() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> {
            ImageView imageView = (ImageView) board.getChildren().get(indexCard1 + desp);
            imageView.setImage(cardStyles[vecSt]);
            imageView = (ImageView) board.getChildren().get(indexCard2 + desp);
            imageView.setImage(cardStyles[vecSt]);

        });
        pause.play();
    }

    private void setBackground(int boardSize) {
        if (boardSize == 16) {
            desp = 8;
        }
        for (int i = 0 + desp; i < boardSize + desp; i++) {
            ImageView imageView = (ImageView) board.getChildren().get(i);
            imageView.setImage(cardStyles[vecSt]);
        }
    }

    @FXML
    private void hoverNode(MouseEvent event) {
        Node node = (Node) event.getTarget();
        if (node != null) {
            node.setEffect(new DropShadow(15, 5, 5, Color.BLACK));
        }
    }

    @FXML
    private void unhoverNode(MouseEvent event) {
        Node node = (Node) event.getTarget();
        if (node != null) {
            node.setEffect(null);
        }
    }

    @FXML
    private void hoverNodeBright(MouseEvent event) {
        Node node = (Node) event.getTarget();
        if (node != null) {
            node.setEffect(new DropShadow(15, 0, 0, Color.WHITE));
        }
    }
    
    private void startTime() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {

            timer.setText("Time " + (minutes < 10 ? " 0" : " ") + minutes + ":" + (seconds < 10 ? "0" : "") + seconds);
            seconds++;
            if (seconds == 60) {
                minutes++;
                seconds = 0;
            }
        }));

        System.out.println("Timer on");
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void pauseTime() {
        if (timeline != null) {
            timeline.stop();
            System.out.println("Timer off");
        }
    }

}
