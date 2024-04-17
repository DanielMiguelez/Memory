package REGISTER;

/*
TO-DO:
CPU AGAINST GAME;
SET IMAGEN WINNER;
SONIDOS AL JUEGO;
REFACTORIZAR;
AÑADIR COMENTARIOS;
PÁGINA DE RANKING;
PÁGINA DE INSTRUCCIONES;
RECOGER DATOS ON PLAY AGIAN;
MONEDAS EN EL JUEGO;
EASTER-EGGS;
OPTIONS;
COMENTARIOS EN LA PARTIDA;
 */


import Game.gameController;
import Menu.menuController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import memory.Player;
import utilidades.bbdd.Bd;
import static utilidades.bbdd.Bd.crearBBDD;
import utilidades.bbdd.Gestor_conexion_POSTGRE;

public class registerController implements Initializable {
    
    @FXML
    private TextField labelP1;
    @FXML
    private TextField labelP2;
    @FXML
    private PasswordField passwordP1;
    @FXML
    private PasswordField passwordP2;
    @FXML 
    private Button registerLeft;
    @FXML 
    private Button registerRight;  
    @FXML 
    private Button loginLeft;
    @FXML 
    private Button loginRight;
    @FXML 
    private Button back;
    @FXML
    private String nameP1  = "P1";
    @FXML
    private String nameP2 = "P2";
    @FXML
    private String nameP3 = "P3";
    @FXML
    private String nameP4 = "P4";
    @FXML
    private ImageView leftUser;
    @FXML
    private ImageView rightUser;
    //victorias y nivel de jugadores
    
    public int id;
    public String name;
    public int victories;
    public int nivel;
    public String password;

    private String levelP1 = "lvlP1";
    private String levelP2 = "lvlP2";
    private String levelP3 = "lvlP3";
    private String levelP4 = "lvlP4";


    private String victoriesP1 = "winsP1";
    private String victoriesP2 = "winsP2";
    private String victoriesP3 = "winsP3";
    private String victoriesP4 = "winsP4";
    
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    
    private boolean consulta;
    public boolean logged = false;
    public boolean logged2 = false;
    public boolean logged3 = false;
    public boolean logged4 = false;
    
    String user1;
    String password1;
    
    String user2;
    String password2;
    
    String user3;
    String password3;
    
    String user4;
    String password4;
    
    private int numPlayers;
    
    public gameController game;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String c = "insert into jugadores (nick_jugador,contraseña) values (" + "'" + user1 + "'" + " , " + "'" + password1 + "'" + ")";
        String incrementWins = "update jugadores\n" + "set victorias_jugador  = victorias_jugador + 1\n" + "where nick_jugador = '"+user1+"'";
        
    }
    
    
    public boolean openConnection( String q ){
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        String query = String.format(q);
        boolean verify = Bd.consultaModificacion(gestor, query);
        gestor.cerrar_Conexion(true);
        return verify;
    }
    
    public void changePlayerLeft(Event e){
        Object o = (Object)e.getSource();
        Node n = (Node)o;
        String oFxId = n.getId();
        System.out.println(oFxId);
        
        if (oFxId.equals("arrowLeftP1")){
            leftUser.setImage(new Image(memory.Card.class.getResourceAsStream("/media/player1.png")));
            loginLeft.setOnAction(event -> loginUser1());
            setP1StyleNull();
            }
        if (oFxId.equals("arrowRightP1")){
            leftUser.setImage(new Image(memory.Card.class.getResourceAsStream("/media/player3.png")));
            loginLeft.setOnAction(event -> loginUser3());
            setP1StyleNull();
        }
    }
    
    public void changePlayerRight(Event e){
        Object o = e.getSource();
        Node n = (Node)o;
        String oFxId = n.getId();
        //System.out.println(oFxId);
        if (oFxId.equals("arrowLeftP2")){
            rightUser.setImage(new Image(memory.Card.class.getResourceAsStream("/media/player2.png")));
            loginRight.setOnAction(Event -> loginUser2());
            setP2StyleNull();
        }
        if (oFxId.equals("arrowRightP2")){
            rightUser.setImage(new Image(memory.Card.class.getResourceAsStream("/media/player4.png")));
            loginRight.setOnAction(event -> loginUser4());
            setP2StyleNull();
        }
    }
    
    public void setP1StyleNull(){
            loginLeft.setStyle(null);
            registerLeft.setStyle(null);
            labelP1.setStyle(null);
            passwordP1.setStyle(null);
            labelP1.setText(null);
            passwordP1.setText(null);
        }
    public void setP2StyleNull(){
            loginRight.setStyle(null);
            registerRight.setStyle(null);
            labelP2.setStyle(null);
            passwordP2.setStyle(null);
            labelP2.setText(null);
            passwordP2.setText(null);
        }
    
    
    
    @FXML
    public void registerUser1() {
        user1 = labelP1.getText().toLowerCase();
        password1 = passwordP1.getText().toLowerCase();
        
        //SI USUARIO Y CONTRASEÑA SON "VALIDOS"
        if (user1.length()>0 && password1.length()>=5){
                //LOS METEMOS A LA BBDD MEMORY
                Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
                String query = String.format("insert into jugadores (nick_jugador,contraseña) values (" + "'" + user1 + "'" + " , " + "'" + password1 + "'" + ")");
                consulta = Bd.consultaModificacion(gestor, query);
                gestor.cerrar_Conexion(true);
                if (consulta){
                    System.out.println("Registrando usuario 1");
                    registerLeft.setStyle("-fx-background-color: green;");
                    labelP1.setStyle("-fx-border-color: green;");
                    loginLeft.setStyle(null);
                    passwordP1.setStyle(null);
                    }
                else if (!consulta){
                    registerLeft.setStyle("-fx-background-color: red;");
                    labelP1.setStyle("-fx-border-color: red;");
                    }
                }
        else {
            // Establecer estilo de error al botón
            registerLeft.setStyle("-fx-background-color: red;");
            labelP1.setStyle("-fx-border-color: red;");
            }
                if (passwordP1.getText().length() < 5){
                    passwordP1.setStyle("-fx-border-color: red;");
                    }
                if (labelP1.getText().length() == 0){
                    labelP1.setPromptText("nickname cannot be empty");
                    labelP1.setStyle("-fx-prompt-text-fill: red;");
                    }
        }

    
    @FXML
    public void registerUser2() {
        user2 = labelP2.getText().toLowerCase();
        password2 = passwordP2.getText().toLowerCase();
        
          if (user2.length()>0 && password2.length()>=5){
                Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
                String query = String.format("insert into jugadores (nick_jugador,contraseña) values (" + "'" + user2 + "'" + " , " + "'" + password2 + "'" + ")");
                consulta = Bd.consultaModificacion(gestor, query);
                gestor.cerrar_Conexion(true);
                if (consulta){
                    registerRight.setStyle("-fx-background-color: green;");
                    labelP2.setStyle("-fx-border-color: green;");
                    loginRight.setStyle(null);
                    passwordP2.setStyle(null);
                }
                else if (!consulta){
                    registerRight.setStyle("-fx-background-color: red;");
                    labelP2.setStyle("-fx-border-color: red;");
                    }
                }
            else {
            // Establecer estilo de error al botón
            registerRight.setStyle("-fx-background-color: red;");
            labelP2.setStyle("-fx-border-color: red;");
            }
                if (passwordP2.getText().length() < 5){
                    passwordP2.setStyle("-fx-border-color: red;");
                    }
                if (labelP2.getText().length() == 0){
                    labelP2.setPromptText("nickname cannot be empty");
                    labelP2.setStyle("-fx-prompt-text-fill: red;");
                    }
        }
    
     public void loginUser1() {
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        user1 = labelP1.getText().toLowerCase();
        password1 = passwordP1.getText().toLowerCase();
        String query = String.format("select id_jugador, nick_jugador, COALESCE(victorias_jugador, 0), contraseña from jugadores where nick_jugador = " + "'" + user1 + "'" + " AND contraseña=" + "'" + password1 + "'");
        String[][]resultados   = Bd.consultaSelect(gestor, query);
        gestor.cerrar_Conexion(true);
        
        if (resultados!=null){
            if(user1.equals(user2)){
                loginLeft.setStyle("-fx-background-color: red;");
                labelP1.setStyle("-fx-border-color: red;");
                logged = false;
            }else{
                    loginLeft.setStyle("-fx-background-color: green;");
                    labelP1.setStyle("-fx-border-color: green;");
                    logged = true;

                   id = Integer.parseInt(resultados[0][0]);
                   name = (resultados[0][1]);
                   victories = Integer.parseInt(resultados[0][2]);
                   password = (resultados[0][3]);
                   player1 = new Player(id,name,victories,password);  
                   System.out.println("Logueando p1" + player1);
                   nameP1 = name;
                   levelP1 = nivel+"";
                   victoriesP1 = victories+"";
            } 
        }
        else {
                    loginLeft.setStyle("-fx-background-color: red;");
                    labelP1.setStyle("-fx-border-color: red;");
                    logged = false;
                }
        }
    
     public void loginUser2() {
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        user2 = labelP2.getText().toLowerCase();
        password2 = passwordP2.getText().toLowerCase();
 
        String query = String.format("select id_jugador, nick_jugador, COALESCE(victorias_jugador, 0), contraseña from jugadores where nick_jugador = " + "'" + user2 + "'" + " AND contraseña=" + "'" + password2 + "'");
        String[][]resultados  = Bd.consultaSelect(gestor, query);
        gestor.cerrar_Conexion(true);
        
        if (!logged){
            labelP2.setText("");
            labelP2.setPromptText("P2 CANNOT BE LOGGED B4 P1");
            passwordP2.setText("");
            logged2=false;
        }
        else if (resultados!=null){
            if(user2.equals(user1)){
                loginRight.setStyle("-fx-background-color: red;");
                labelP2.setStyle("-fx-border-color: red;");
                logged2 = false;
            }else{
                    loginRight.setStyle("-fx-background-color: green;");
                    labelP2.setStyle("-fx-border-color: green;");
                    logged2 = true;
                    
                   id = Integer.parseInt(resultados[0][0]);
                   name = (resultados[0][1]);
                   victories = Integer.parseInt(resultados[0][2]);
                   password = (resultados[0][3]);
                   player2 = new Player(id,name,victories,password);
                   System.out.println("Logueando player 2: "+ player2);
                    nameP2 = name;
                    levelP2 = nivel+"";
                    victoriesP2 = victories+"";
            }
        }
        else{
                    loginRight.setStyle("-fx-background-color: red;");
                    labelP2.setStyle("-fx-border-color: red;");
                    logged2 = false;
        }
    }
     
     public void loginUser3() {
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        user3 = labelP1.getText().toLowerCase();
        password3 = passwordP1.getText().toLowerCase();
        String query = String.format("select id_jugador, nick_jugador, COALESCE(victorias_jugador, 0), contraseña from jugadores where nick_jugador = " + "'" + user3 + "'" + " AND contraseña=" + "'" + password3 + "'");
        String[][]resultados   = Bd.consultaSelect(gestor, query);
        gestor.cerrar_Conexion(true);
        
        if (!logged2){
            labelP1.setText("");
            labelP1.setPromptText("P3 CANNOT BE LOGGED B4 P2");
            passwordP1.setText("");
            logged3=false;
        }
        else if (resultados!=null){
            if(user3.equals(user1) || user3.equals(user2) || user3.equals(user4)){
                loginLeft.setStyle("-fx-background-color: red;");
                labelP1.setStyle("-fx-border-color: red;");
                logged3 = false;
            }else{
                    loginLeft.setStyle("-fx-background-color: green;");
                    labelP1.setStyle("-fx-border-color: green;");
                    logged3 = true;

                   id = Integer.parseInt(resultados[0][0]);
                   name = (resultados[0][1]);
                   victories = Integer.parseInt(resultados[0][2]);
                   password = (resultados[0][3]);
                   player3 = new Player(id,name,victories,password);  
                   System.out.println("Logeando p3:" + player3);
                   nameP3 = name;
                   levelP3 = nivel+"";
                   victoriesP3 = victories+"";
//                    if (logged4)
//                       openGame();
            } 
        }
        else {
                    loginLeft.setStyle("-fx-background-color: red;");
                    labelP1.setStyle("-fx-border-color: red;");
                    logged3 = false;
                }
        }
     public void loginUser4() {
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        user4 = labelP2.getText().toLowerCase();
        password4 = passwordP2.getText().toLowerCase();
 
        String query = String.format("select id_jugador, nick_jugador, COALESCE(victorias_jugador, 0), contraseña from jugadores where nick_jugador = " + "'" + user4 + "'" + " AND contraseña=" + "'" + password4 + "'");
        String[][]resultados  = Bd.consultaSelect(gestor, query);
        gestor.cerrar_Conexion(true);
        
        if (!logged3){
            labelP2.setText("");
            labelP2.setPromptText("P4 CANNOT BE LOGGED B4 P3");
            passwordP2.setText("");
            logged4=false;
        }
        else if (resultados!=null){
            if(user4.equals(user1) || user4.equals(user2) || user4.equals(user3)){
                loginRight.setStyle("-fx-background-color: red;");
                labelP2.setStyle("-fx-border-color: red;");
                logged4 = false;
            }else{
                    loginRight.setStyle("-fx-background-color: green;");
                    labelP2.setStyle("-fx-border-color: green;");
                    logged4 = true;
                    
                   id = Integer.parseInt(resultados[0][0]);
                   name = (resultados[0][1]);
                   victories = Integer.parseInt(resultados[0][2]);
                   password = (resultados[0][3]);
                   player4 = new Player(id,name,victories,password);
                   System.out.println("Logueando p4: " + player4);
                    nameP4 = name;
                    levelP4 = nivel+"";
                    victoriesP4 = victories+"";
//                    if (logged3)
//                        openGame();
            }
        }
        else{
                    loginRight.setStyle("-fx-background-color: red;");
                    labelP2.setStyle("-fx-border-color: red;");
                    logged4 = false;
        }
    }

    
     public void openGame(){
        try {
//            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game/game.fxml"));
            Parent root = loader.load();
            // Obtener la escena actual y el escenario
            Scene currentScene = loginLeft.getScene();
            Stage stage = (Stage) currentScene.getWindow();
            //cogemos los textos de los labels y se los pasamos al metodo del game controler
            game = loader.getController();
            game.labelNames(nameP1,nameP2,nameP3,nameP4,levelP1,levelP2,levelP3,levelP4,victoriesP1,victoriesP2,victoriesP3,victoriesP4);
            checkLogged();
            game.setNumTurns(numPlayers);
            
            // Reemplazar la escena actual con la escena del registro
            currentScene.setRoot(root);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
            }
     }
    public void checkLogged(){
        if (logged){
            game.anchorPlayer1.setVisible(true);
            numPlayers++;
        }
        if (logged2){
            game.anchorPlayer2.setVisible(true);
            numPlayers++;
        }
        if (logged3){
            game.anchorPlayer3.setVisible(true);
            numPlayers++;
        }
        if (logged4){
            game.anchorPlayer4.setVisible(true);
            numPlayers++;
        }
      
    }
     public void openMenu(){
        try {
//            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/menu.fxml"));
            Parent root = loader.load();
            // Obtener la escena actual y el escenario
            Scene currentScene = loginLeft.getScene();
            Stage stage = (Stage) currentScene.getWindow();
            // Reemplazar la escena actual con la escena del registro
            currentScene.setRoot(root);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
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

    private boolean getLogged1(){
        return logged;
    }
    private boolean getLogged2(){
        return logged2;
    }
    private boolean getLogged3(){
        return logged3;
    }
    private boolean getLogged4(){
        return logged4;
    }
}

