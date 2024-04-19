package REGISTER;

/*
TO-DO:
AÑADIR CPUS AL LOGIN;
CPU AGAINST GAME (EASY (1A-4RND), (2A[1 de esas 2 C. que levanta tiene que tener el id de ]-2RND) EQUILIBRADO , (CADA X-> FAIL) GOD);
SET IMAGEN WINNER;
SONIDOS AL JUEGO;
REFACTORIZAR (METER OBJETOS USUARIOS A GAMECONTROLLER PARA SETEAR INFO EN LABELNAMES);
AÑADIR COMENTARIOS;
PÁGINA DE RANKING;
PÁGINA DE INSTRUCCIONES;
RECOGER DATOS ON PLAY AGIAN;
MONEDAS EN EL JUEGO;
EASTER-EGGS;
OPTIONS;
COMENTARIOS EN LA PARTIDA;
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

    private int numPlayers;
    public gameController game;

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
            setP1StyleNull();
        }
        if (oFxId.equals("arrowRightP1")) {
            leftUserLogo.setImage(new Image(memory.Card.class.getResourceAsStream("/media/player3.png")));
            btnLoginLeft.setOnAction(event -> loginUser3());
            setP1StyleNull();
        }
    }

    public void changePlayerRight(Event e) {
        Object o = e.getSource();
        Node n = (Node) o;
        String oFxId = n.getId();
        //System.out.println(oFxId);
        if (oFxId.equals("arrowLeftP2")) {
            rightUserLogo.setImage(new Image(memory.Card.class.getResourceAsStream("/media/player2.png")));
            btnLoginRight.setOnAction(Event -> loginUser2());
            setP2StyleNull();
        }
        if (oFxId.equals("arrowRightP2")) {
            rightUserLogo.setImage(new Image(memory.Card.class.getResourceAsStream("/media/player4.png")));
            btnLoginRight.setOnAction(event -> loginUser4());
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

    @FXML
    public void registerUser1() {
        nameP1 = labelNameLeft.getText().toLowerCase();
        password1 = passwordUserLeft.getText().toLowerCase();

        //SI USUARIO Y CONTRASEÑA SON "VALIDOS"
        if (nameP1.length() > 0 && password1.length() >= 5) {
            //LOS METEMOS A LA BBDD MEMORY
            Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
            String query = String.format("insert into jugadores (nick_jugador,contraseña) values (" + "'" + nameP1 + "'" + " , " + "'" + password1 + "'" + ")");
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
        nameP2 = labelNameRight.getText().toLowerCase();
        password2 = passwordUserRight.getText().toLowerCase();

        if (nameP2.length() > 0 && password2.length() >= 5) {
            Gestor_conexion_POSTGRE gestor = new Gestor_conexion_POSTGRE("memory", true);
            String query = String.format("insert into jugadores (nick_jugador,contraseña) values (" + "'" + nameP2 + "'" + " , " + "'" + password2 + "'" + ")");
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
            if (nameP3.equals(nameP1) || nameP3.equals(nameP2) || nameP3.equals(nameP4)) {
                btnLoginLeft.setStyle("-fx-background-color: red;");
                labelNameLeft.setStyle("-fx-border-color: red;");
                logged3 = false;
            } else {
                btnLoginLeft.setStyle("-fx-background-color: green;");
                labelNameLeft.setStyle("-fx-border-color: green;");
                logged3 = true;

                id = Integer.parseInt(resultados[0][0]);
                name = (resultados[0][1]);
                victories = Integer.parseInt(resultados[0][2]);
                password = (resultados[0][3]);
                player3 = new Player(id, name, victories, password);
                System.out.println("Logeando p3:" + player3);
                nameP3 = name;
                victoriesP3 = victories + "";
//                    if (logged4)
//                       openGame();
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

                id = Integer.parseInt(resultados[0][0]);
                name = (resultados[0][1]);
                victories = Integer.parseInt(resultados[0][2]);
                password = (resultados[0][3]);
                player4 = new Player(id, name, victories, password);
                System.out.println("Logueando p4: " + player4);
                nameP4 = name;
                victoriesP4 = victories + "";
//                    if (logged3)
//                        openGame();
            }
        } else {
            btnLoginRight.setStyle("-fx-background-color: red;");
            labelNameRight.setStyle("-fx-border-color: red;");
            logged4 = false;
        }
    }

    public void openGame() {
        try {
//            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game/game.fxml"));
            Parent root = loader.load();
            // Obtener la escena actual y el escenario
            Scene currentScene = btnLoginLeft.getScene();
            Stage stage = (Stage) currentScene.getWindow();
            //cogemos los textos de los labels y se los pasamos al metodo del game controler
            game = loader.getController();
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

    public void checkLogged() {
        if (logged) {
            game.anchorPlayer1.setVisible(true);
            numPlayers++;
        }
        if (logged2) {
            game.anchorPlayer2.setVisible(true);
            numPlayers++;
        }
        if (logged3) {
            game.anchorPlayer3.setVisible(true);
            numPlayers++;
        }
        if (logged4) {
            game.anchorPlayer4.setVisible(true);
            numPlayers++;
        }

    }

    public void openMenu() {
        try {
//            currentStage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/menu.fxml"));
            Parent root = loader.load();
            // Obtener la escena actual y el escenario
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
}
