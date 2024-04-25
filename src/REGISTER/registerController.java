package REGISTER;

/*
TO-DO:
VEC TAM 3;
DEJAR CLARO DE QUIEN ES TURNO;
EASTER-EGGS;
PÁGINA DE INSTRUCCIONES;
OPTIONS;
CPU AGAINST GAME (EASY (1A-4RND), (2A[1 de esas 2 C. que levanta tiene que tener el id de ]-2RND) EQUILIBRADO , (CADA X-> FAIL) GOD);
SONIDOS AL JUEGO;

REFACTORIZAR (METER OBJETOS USUARIOS A GAMECONTROLLER PARA SETEAR INFO EN LABELNAMES);
AÑADIR COMENTARIOS;
BBDD AUTOCREADA CREATE TABLE IF NOT EXISTS;
CIFRAR CONTRASÑEAS CON SHA;
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
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import memory.Player;
import utilidades.bbdd.Bd;
import static utilidades.bbdd.Bd.crearBBDD;
import utilidades.bbdd.Gestor_conexion_POSTGRE;

public class registerController implements Initializable {

    @FXML
    private TextField labelNameLeft;
    @FXML
    private TextField labelNameRight;
    @FXML
    private PasswordField passwordUserLeft;
    @FXML
    private PasswordField passwordUserRight;
    @FXML
    private Button btnRegisterLeft;
    @FXML
    private Button btnRegisterRight;
    @FXML
    private Button btnLoginLeft;
    @FXML
    private Button btnLoginRight;
    @FXML
    private ImageView leftUserLogo;
    @FXML
    private ImageView rightUserLogo;
    
    public int id;
    public String name;
    public String password;
    public int victories;
    public int nivel;

    private String victoriesP1;
    private String victoriesP2;
    private String victoriesP3;
    private String victoriesP4;

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;

    private boolean consulta;
    public boolean logged = false;
    public boolean logged2 = false;
    public boolean logged3 = false;
    public boolean logged4 = false;

    String nameP1;
    String password1;
    String nameP2;
    String password2;
    String nameP3;
    String password3;
    String nameP4;
    String password4;
    
    public static String[]cpuLevels = new String[4];
    
    String cpuName;
    String cpuMode;

    private int numPlayers = 0;
    private int numberOfLogins = 0;

    public gameController game;
    public menuController menu;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    public boolean openConnection(String q) {
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        String query = String.format(q);
        boolean verify = Bd.consultaModificacion(gestor, query);
        gestor.cerrar_Conexion(true);
        return verify;
    }

    public void changePlayerLeft(Event e) {
        Object o = (Object) e.getSource();
        Node n = (Node) e.getSource();
        String oFxId = n.getId();
        System.out.println(oFxId);

        if (oFxId.equals("arrowLeftP1")) {
            leftUserLogo.setImage(new Image(memory.Card.class.getResourceAsStream("/media/player1.png")));
            btnLoginLeft.setOnAction(Event -> loginUser1());
            btnLoginLeft.setPrefWidth(115);
            btnRegisterLeft.setVisible(true);
            labelNameLeft.setPromptText("username");
            setP1StyleNull();
        }
        if (oFxId.equals("arrowRightP1")) {
            leftUserLogo.setImage(new Image(memory.Card.class.getResourceAsStream("/media/player3.png")));
            btnLoginLeft.setOnAction(Event -> loginUser3());
            btnLoginLeft.setPrefWidth(115);
            btnRegisterLeft.setVisible(true);
            labelNameLeft.setPromptText("username");
            setP1StyleNull();
        }
        
        if (oFxId.equals("arrowUpP1")) {
            leftUserLogo.setImage(new Image(memory.Card.class.getResourceAsStream("/media/cpu.png")));
            btnLoginLeft.setOnAction(Event -> loginCPU(e));
            btnLoginLeft.setPrefWidth(250);
            btnRegisterLeft.setVisible(false);
            labelNameLeft.setPromptText("Give your IA a name");
            passwordUserLeft.setPromptText("Type difficult: easy , medium , hard");
            setP1StyleNull();
        }
        if (oFxId.equals("arrowDownP1")) {
            leftUserLogo.setImage(new Image(memory.Card.class.getResourceAsStream("/media/player1.png")));
            btnLoginLeft.setOnAction(Event -> loginUser3());
            btnLoginLeft.setPrefWidth(115);
            btnRegisterLeft.setVisible(true);
            labelNameLeft.setPromptText("username");
            passwordUserLeft.setPromptText("Password must be &gt; 5");
            setP1StyleNull();
        }
    }

    public void changePlayerRight(Event e) {
        Object o = e.getSource();
        Node n = (Node) o;
        String oFxId = n.getId();
        if (oFxId.equals("arrowLeftP2")) {
            rightUserLogo.setImage(new Image(memory.Card.class.getResourceAsStream("/media/player2.png")));
            btnLoginRight.setOnAction(Event -> loginUser2());
            btnLoginRight.setPrefWidth(115);
            btnRegisterRight.setVisible(true);
            labelNameRight.setPromptText("username");
            setP2StyleNull();
        }
        if (oFxId.equals("arrowRightP2")) {
            rightUserLogo.setImage(new Image(memory.Card.class.getResourceAsStream("/media/player4.png")));
            btnLoginRight.setOnAction(Event -> loginUser4());
            btnLoginRight.setPrefWidth(115);
            btnRegisterRight.setVisible(true);
            labelNameRight.setPromptText("username");
            setP2StyleNull();
        }
        if (oFxId.equals("arrowUpP2")) {
            rightUserLogo.setImage(new Image(memory.Card.class.getResourceAsStream("/media/cpu.png")));
            btnLoginRight.setOnAction(Event -> loginCPU(e));
            btnLoginRight.setPrefWidth(250);
            btnRegisterRight.setVisible(false);
            labelNameRight.setPromptText("Give your IA a name");
            passwordUserRight.setPromptText("Type difficult: easy , medium , hard");
            setP2StyleNull();
        }
        if (oFxId.equals("arrowDownP2")) {
            rightUserLogo.setImage(new Image(memory.Card.class.getResourceAsStream("/media/player2.png")));
            btnLoginRight.setOnAction(Event -> loginUser2());
            btnLoginRight.setPrefWidth(115);
            btnRegisterRight.setVisible(true);
            labelNameRight.setPromptText("username");
            passwordUserRight.setPromptText("Password must be &gt; 5");
            setP2StyleNull();
        }
    }

    public void setP1StyleNull() {
        btnLoginLeft.setStyle(null);
        btnRegisterLeft.setStyle(null);
        labelNameLeft.setStyle(null);
        passwordUserLeft.setStyle(null);
        labelNameLeft.setText(null);
        passwordUserLeft.setText(null);
    }

    public void setP2StyleNull() {
        btnLoginRight.setStyle(null);
        btnRegisterRight.setStyle(null);
        labelNameRight.setStyle(null);
        passwordUserRight.setStyle(null);
        labelNameRight.setText(null);
        passwordUserRight.setText(null);
    }
    
    public void loginCPU(Event e) {
        Object o = e.getSource();
        Node n = (Node) o;
        String oFxId = n.getId();
        
        if (oFxId.equals("arrowUpP2")){
            cpuName = labelNameRight.getText();
            cpuMode = passwordUserRight.getText();
            if (cpuName.length() > 0 && cpuMode.equals("easy") || cpuMode.equals("medium") || cpuMode.equals("hard")){
                btnLoginRight.setStyle("-fx-background-color: green;");
                labelNameRight.setStyle("-fx-border-color: green;");
                loggedCpu();
                numberOfLogins++;    
            } else {
                btnLoginRight.setStyle("-fx-background-color: red;");
                labelNameRight.setStyle("-fx-border-color: red;");
            }
        }
        if (oFxId.equals("arrowUpP1")){
            cpuName = labelNameLeft.getText();
            cpuMode = passwordUserLeft.getText();
            if (cpuName.length() > 0 && cpuMode.equals("easy") || cpuMode.equals("medium") || cpuMode.equals("hard")){
                btnLoginLeft.setStyle("-fx-background-color: green;");
                labelNameLeft.setStyle("-fx-border-color: green;");
                loggedCpu();
                numberOfLogins++;    
            } else {
                btnLoginRight.setStyle("-fx-background-color: red;");
                labelNameRight.setStyle("-fx-border-color: red;");
            }
        }
    }
    
    public void loggedCpu(){
                if (numberOfLogins==0) {
                    nameP1 = cpuName;
                    password1 = cpuMode;
                    cpuLevels[0]=cpuMode;
                    victoriesP1 = "CPU";
                    // Estaría guapo meter -> levelPlayer1 = cpuMode; la dificultad en level.
                }
                else if (numberOfLogins==1) {
                    nameP2 = cpuName;
                    password2 = cpuMode;
                    victoriesP2 = "CPU";
                    cpuLevels[1]=cpuMode;                    
                }
                else if (numberOfLogins==2) {
                    nameP3 = cpuName;
                    password3 = cpuMode;
                    victoriesP3 = "CPU";
                    cpuLevels[2]=cpuMode;                    
                }
                else if (numberOfLogins==3) {
                    nameP4= cpuName;
                    password4 = cpuMode;
                    victoriesP4 = "CPU";
                    cpuLevels[3]=cpuMode;                    
                }
    }
    
    
    
    @FXML
    public void registerUser1() {
        name = labelNameLeft.getText().toLowerCase();
        password = passwordUserLeft.getText().toLowerCase();

        //SI USUARIO Y CONTRASEÑA SON "VALIDOS"
        if (name.length() > 0 && password.length() >= 5) {
            //LOS METEMOS A LA BBDD MEMORY
            Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
            String query = String.format("insert into jugadores (nick_jugador,contraseña) values (" + "'" + name + "'" + " , " + "'" + password + "'" + ")");
            consulta = Bd.consultaModificacion(gestor, query);
            gestor.cerrar_Conexion(true);
            if (consulta) {
                System.out.println("Registrando usuario 1");
                btnRegisterLeft.setStyle("-fx-background-color: green;");
                labelNameLeft.setStyle("-fx-border-color: green;");
                btnLoginLeft.setStyle(null);
                passwordUserLeft.setStyle(null);
            } else if (!consulta) {
                btnRegisterLeft.setStyle("-fx-background-color: red;");
                labelNameLeft.setStyle("-fx-border-color: red;");
            }
        } else {
            // Establecer estilo de error al botón
            btnRegisterLeft.setStyle("-fx-background-color: red;");
            labelNameLeft.setStyle("-fx-border-color: red;");
        }
        if (passwordUserLeft.getText().length() < 5) {
            passwordUserLeft.setStyle("-fx-border-color: red;");
        }
        if (labelNameLeft.getText().length() == 0) {
            labelNameLeft.setPromptText("nickname cannot be empty");
            labelNameLeft.setStyle("-fx-prompt-text-fill: red;");
        }
    }

    @FXML
    public void registerUser2() {
        name = labelNameRight.getText().toLowerCase();
        password = passwordUserRight.getText().toLowerCase();

        if (name.length() > 0 && password.length() >= 5) {
            Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
            String query = String.format("insert into jugadores (nick_jugador,contraseña) values (" + "'" + name + "'" + " , " + "'" + password + "'" + ")");
            consulta = Bd.consultaModificacion(gestor, query);
            gestor.cerrar_Conexion(true);
            if (consulta) {
                btnRegisterRight.setStyle("-fx-background-color: green;");
                labelNameRight.setStyle("-fx-border-color: green;");
                btnLoginRight.setStyle(null);
                passwordUserRight.setStyle(null);
            } else if (!consulta) {
                btnRegisterRight.setStyle("-fx-background-color: red;");
                labelNameRight.setStyle("-fx-border-color: red;");
            }
        } else {
            // Establecer estilo de error al botón
            btnRegisterRight.setStyle("-fx-background-color: red;");
            labelNameRight.setStyle("-fx-border-color: red;");
        }
        if (passwordUserRight.getText().length() < 5) {
            passwordUserRight.setStyle("-fx-border-color: red;");
        }
        if (labelNameRight.getText().length() == 0) {
            labelNameRight.setPromptText("nickname cannot be empty");
            labelNameRight.setStyle("-fx-prompt-text-fill: red;");
        }
    }

    public void loginUser1() {
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        nameP1 = labelNameLeft.getText().toLowerCase();
        password1 = passwordUserLeft.getText().toLowerCase();
        String query = String.format("select id_jugador, nick_jugador, COALESCE(victorias_jugador, 0), contraseña from jugadores where nick_jugador = " + "'" + nameP1 + "'" + " AND contraseña=" + "'" + password1 + "'");
        String[][] resultados = Bd.consultaSelect(gestor, query);
        gestor.cerrar_Conexion(true);

        if (resultados != null) {
            if (nameP1.equals(nameP2)) {
                btnLoginLeft.setStyle("-fx-background-color: red;");
                labelNameLeft.setStyle("-fx-border-color: red;");
                logged = false;
            } else {
                btnLoginLeft.setStyle("-fx-background-color: green;");
                labelNameLeft.setStyle("-fx-border-color: green;");
                logged = true;
                numberOfLogins++;

                id = Integer.parseInt(resultados[0][0]);
                name = (resultados[0][1]);
                victories = Integer.parseInt(resultados[0][2]);
                password = (resultados[0][3]);
                player1 = new Player(id, name, victories, password);
                System.out.println("Logueando p1" + player1);
                nameP1 = name;
                victoriesP1 = victories + "";
            }
        } else {
            btnLoginLeft.setStyle("-fx-background-color: red;");
            labelNameLeft.setStyle("-fx-border-color: red;");
            logged = false;
        }
    }

    public void loginUser2() {
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        nameP2 = labelNameRight.getText().toLowerCase();
        password2 = passwordUserRight.getText().toLowerCase();

        String query = String.format("select id_jugador, nick_jugador, COALESCE(victorias_jugador, 0), contraseña from jugadores where nick_jugador = " + "'" + nameP2 + "'" + " AND contraseña=" + "'" + password2 + "'");
        String[][] resultados = Bd.consultaSelect(gestor, query);
        gestor.cerrar_Conexion(true);

        if (!logged) {
            labelNameRight.setText("");
            labelNameRight.setPromptText("P2 CANNOT BE LOGGED B4 P1");
            passwordUserRight.setText("");
            logged2 = false;
        } else if (resultados != null) {
            if (nameP2.equals(nameP1)) {
                btnLoginRight.setStyle("-fx-background-color: red;");
                labelNameRight.setStyle("-fx-border-color: red;");
                logged2 = false;
            } else {
                btnLoginRight.setStyle("-fx-background-color: green;");
                labelNameRight.setStyle("-fx-border-color: green;");
                logged2 = true;
                numberOfLogins++;

                id = Integer.parseInt(resultados[0][0]);
                name = (resultados[0][1]);
                victories = Integer.parseInt(resultados[0][2]);
                password = (resultados[0][3]);
                player2 = new Player(id, name, victories, password);
                System.out.println("Logueando player 2: " + player2);
                nameP2 = name;
                victoriesP2 = victories + "";
            }
        } else {
            btnLoginRight.setStyle("-fx-background-color: red;");
            labelNameRight.setStyle("-fx-border-color: red;");
            logged2 = false;
        }
    }
    
    public void loginUser3() {
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        nameP3 = labelNameLeft.getText().toLowerCase();
        password3 = passwordUserLeft.getText().toLowerCase();
        String query = String.format("select id_jugador, nick_jugador, COALESCE(victorias_jugador, 0), contraseña from jugadores where nick_jugador = " + "'" + nameP3 + "'" + " AND contraseña=" + "'" + password3 + "'");
        String[][] resultados = Bd.consultaSelect(gestor, query);
        gestor.cerrar_Conexion(true);

        if (!logged2) {
            labelNameLeft.setText("");
            labelNameLeft.setPromptText("P3 CANNOT BE LOGGED B4 P2");
            passwordUserLeft.setText("");
            logged3 = false;
        } else if (resultados != null) {
            if (nameP3.equals(nameP1) || nameP3.equals(nameP2)) {
                btnLoginLeft.setStyle("-fx-background-color: red;");
                labelNameLeft.setStyle("-fx-border-color: red;");
                logged3 = false;
            } else {
                btnLoginLeft.setStyle("-fx-background-color: green;");
                labelNameLeft.setStyle("-fx-border-color: green;");
                logged3 = true;
                numberOfLogins++;

                id = Integer.parseInt(resultados[0][0]);
                name = (resultados[0][1]);
                victories = Integer.parseInt(resultados[0][2]);
                password = (resultados[0][3]);
                player3 = new Player(id, name, victories, password);
                System.out.println("Logeando p3:" + player3);
                nameP3 = name;
                victoriesP3 = victories + "";
            }
        } else {
            btnLoginLeft.setStyle("-fx-background-color: red;");
            labelNameLeft.setStyle("-fx-border-color: red;");
            logged3 = false;
        }
    }

    public void loginUser4() {
        Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
        nameP4 = labelNameRight.getText().toLowerCase();
        password4 = passwordUserRight.getText().toLowerCase();

        String query = String.format("select id_jugador, nick_jugador, COALESCE(victorias_jugador, 0), contraseña from jugadores where nick_jugador = " + "'" + nameP4 + "'" + " AND contraseña=" + "'" + password4 + "'");
        String[][] resultados = Bd.consultaSelect(gestor, query);
        gestor.cerrar_Conexion(true);

        if (!logged3) {
            labelNameRight.setText("");
            labelNameRight.setPromptText("P4 CANNOT BE LOGGED B4 P3");
            passwordUserRight.setText("");
            logged4 = false;
        } else if (resultados != null) {
            if (nameP4.equals(nameP1) || nameP4.equals(nameP2) || nameP4.equals(nameP3)) {
                btnLoginRight.setStyle("-fx-background-color: red;");
                labelNameRight.setStyle("-fx-border-color: red;");
                logged4 = false;
            } else {
                btnLoginRight.setStyle("-fx-background-color: green;");
                labelNameRight.setStyle("-fx-border-color: green;");
                logged4 = true;
                numberOfLogins++;

                id = Integer.parseInt(resultados[0][0]);
                name = (resultados[0][1]);
                victories = Integer.parseInt(resultados[0][2]);
                password = (resultados[0][3]);
                player4 = new Player(id, name, victories, password);
                System.out.println("Logueando p4: " + player4);
                nameP4 = name;
                victoriesP4 = victories + "";
            }
        } else {
            btnLoginRight.setStyle("-fx-background-color: red;");
            labelNameRight.setStyle("-fx-border-color: red;");
            logged4 = false;
        }
    }

    public void openGame() {
        try {
            FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("/Menu/menu.fxml"));
            Parent root = menuLoader.load();
            menu =  menuLoader.getController();
            menu.stopMusic();
            FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/Game/game.fxml"));
            root = gameLoader.load();
            game = gameLoader.getController();
            // Obtener la escena actual y el escenario
            Scene currentScene =btnLoginLeft.getScene();
            Stage stage = (Stage) currentScene.getWindow();
            //cogemos los textos de los labels y se los pasamos al metodo del game controler
            
            game.labelNames(nameP1, nameP2, nameP3, nameP4, victoriesP1, victoriesP2, victoriesP3, victoriesP4);
            checkLogged();
            game.setNumTurns(numPlayers);

            // Reemplazar la escena actual con la escena del registro
            currentScene.setRoot(root);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(menuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public int checkLogged() {
        if (numberOfLogins>0) {
            game.anchorPlayer1.setVisible(true);
            numPlayers++;
            System.out.println(numPlayers);
        }
        if (numberOfLogins>1) {
            game.anchorPlayer2.setVisible(true);
            numPlayers++;
            System.out.println(numPlayers);
        }
        if (numberOfLogins>2) {
            game.anchorPlayer3.setVisible(true);
            numPlayers++;
            System.out.println(numPlayers);
        }
        if (numberOfLogins>3) {
            game.anchorPlayer4.setVisible(true);
            numPlayers++;
            System.out.println(numPlayers);
        }
        return numPlayers;
    }

    public void openMenu() {
        try {
//            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/menu.fxml"));
            Parent root = loader.load();
            // Obtener la escena actual y el escenario
            menuController menu = loader.getController();

            Scene currentScene = btnLoginLeft.getScene();
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
        Node node = (Node) event.getTarget();
        if (node != null) {
            node.setEffect(new DropShadow(15, 0, 0, Color.BLACK));
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

    private boolean getLogged1() {
        return logged;
    }

    private boolean getLogged2() {
        return logged2;
    }

    private boolean getLogged3() {
        return logged3;
    }

    private boolean getLogged4() {
        return logged4;
    }
    public int getNumPlayers(){
        return numPlayers;
    }
}