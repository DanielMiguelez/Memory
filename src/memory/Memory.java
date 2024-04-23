package memory;


import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Scanner;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Memory extends Application {
    
    public static boolean crearBBDD(boolean mostrar) {
        
        return false;
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Menu/menu.fxml")); 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }
        
    public static void main(String[] args) {
       launch(args);
    }
}