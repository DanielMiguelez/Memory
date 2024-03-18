package memorydani;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Dani
 */
public class MemoryDani extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
       
       launch(args);
       
       Deck deck = new Deck();
       
        Card Mario = new Card("Mario", false);
        Card Luigi = new Card("Luigi", false);
        Card Wario = new Card("Wario", false);
        Card Toad = new Card("Toad", false);
        Card Yoshi = new Card("Yoshi", false);
        Card Peach = new Card("Peach", false);
        Card Bowser = new Card("Bowser", false);
        Card Koopa = new Card("Koopa", false);
        
        // ADD CARDS INTO THE DECK
        
        deck.addCards(Mario);
        deck.addCards(Luigi);
        deck.addCards(Wario);
        deck.addCards(Toad);
        deck.addCards(Yoshi);
        deck.addCards(Peach);
        deck.addCards(Bowser);
        deck.addCards(Koopa);
        
        // Iteramos para obtener las cartas y su posicion, volteadas o no volteadas.
        
          for (int i = 0; i < deck.getCards().size(); i++) {
            Card card = deck.getCards().get(i);
            System.out.println("Nombre de la carta: " + card.getName());
            System.out.println("Estado de la carta: " + card.isUncovered());
            System.out.println(); // Espacio en blanco para mejorar la legibilidad
        }
    }
    
}
