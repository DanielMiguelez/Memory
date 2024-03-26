package memory;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Memory extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

  
    
    public static void main(String[] args) {
       
       launch(args);
       
          Deck deck = new Deck();

        Card Mario = new Card("Mario", 1);
        Card Luigi = new Card("Luigi", 2);
        Card Wario = new Card("Wario", 3);
        Card Toad = new Card("Toad", 4);
        Card Yoshi = new Card("Yoshi", 5);
        Card Peach = new Card("Peach", 6);
        Card Bowser = new Card("Bowser", 7);
        Card Koopa = new Card("Koopa", 8);

        // AGREGAR CARTAS AL MAZO
        deck.addCards(Mario);
        deck.addCards(Luigi);
        deck.addCards(Wario);
        deck.addCards(Toad);
        deck.addCards(Yoshi);
        deck.addCards(Peach);
        deck.addCards(Bowser);
        deck.addCards(Koopa);
        
        // Barajar el mazo
        deck.shuffle();
        
        // Declarar la matriz para representar las cartas
        
     ArrayList<Card> Deck = deck.getCards();
        for (int i = 0; i < Deck.size(); i++) {
            Card card = Deck.get(i);
              System.out.println(card);
    }
    }
}
