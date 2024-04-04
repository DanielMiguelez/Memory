package memory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import utilidades.bbdd.Bd;
import static utilidades.bbdd.Bd.crearBBDD;
import utilidades.bbdd.Gestor_conexion_POSTGRE;

public class FXMLDocumentController implements Initializable {
    
    public static void mostrar(String [][] vec){
        for(int i=0; i<vec.length; i++){
            for(int j=0; j<vec.length; j++)
                System.out.println(vec[i][j]+" ");
            System.out.println();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String vec[][];
       
       
        Gestor_conexion_POSTGRE gestor= new Gestor_conexion_POSTGRE("abpdefault",true);
        //Bd.consultaModificacion(gestor, "delete from jugadores where nick='jose' ");
        vec=Bd.consultaSelect(gestor, "select * from jugadores");
        gestor.cerrar_Conexion(true);
        if(vec!=null)
            mostrar(vec);
        else
            System.out.println("vacio"); 
    }

    public void menuMusic(){
        String path = "@/../src/memory/troopa.mp3";
        Media sound = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        //mediaPlayer.seek(Duration.ZERO);
        mediaPlayer.play();
        }
}
