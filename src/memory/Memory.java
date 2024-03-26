package memory;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Scanner;

public class Memory extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); 
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       launch(args);
       
       //Player register
       boolean registered = false;
       
//       while(!registered){ 
//           
//        System.out.println("Ingrese nombre jugador 1: ");
//        String player1 = scanner.nextLine();
//
//        System.out.println("Ingrese contraseña jugador 1: ");
//        String passPlayer1 = scanner.nextLine();
//        
//        System.out.println("Ingrese nombre jugador 2: ");
//        String player2 = scanner.nextLine();
//
//        System.out.println("Ingrese contraseña jugador 2: ");
//        String passPlayer2 = scanner.nextLine();
//        
//        registered = true;
//       }
       //Deck init
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
        System.out.println(deck.getCards());
        // Declarar matriz de cartas
     String[][] cards = new String[4][4];
        // Declarar matriz de booleanos
     boolean[][] turned = new boolean[4][4];
        
     ArrayList<Card> Deck = deck.getCards();
     
//        for (int i = 0; i < Deck.size(); i++) {
//            Card card = Deck.get(i);
////            for(int j = 0; i < cards.length;i++){
////                 int row = i / 4; // Calcular fila basada en el índice del mazo
////                 int col = i % 4; // Calcular columna basada en el índice del mazo
////                 cards[row][col] = card.getName(); // Asignar el nombre de la carta a la posición correspondiente en la matriz
////                 
////            }
//              System.out.println(card);  
//         }
         // Obtener la posición de fila y columna del usuario
//        System.out.println("Ingrese la posición de la fila (0-3):");
//        int row = scanner.nextInt();
//
//        System.out.println("Ingrese la posición de la columna (0-3):");
//        int col = scanner.nextInt();
//                
    }
}
