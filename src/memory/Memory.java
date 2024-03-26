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
    
//        public void newTurn(){
//     
//          
//     }
        
    public static void main(String[] args) {
      
       launch(args);
       
       // In Game Variables
       
//       int playerPoints = 0;
//       int attempts = 0;
//       boolean sameCards = false;
//       int turn = 1;
       
       
       //Player register
//       boolean registered = false;
       
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

        // AGREGAR CARTAS AL MAZO 2 VECES, Y TENEMOS LAS 16.
        
        deck.addCards(Mario);
        deck.addCards(Luigi);
        deck.addCards(Wario);
        deck.addCards(Toad);
        deck.addCards(Yoshi);
        deck.addCards(Peach);
        deck.addCards(Bowser);
        deck.addCards(Koopa);
        
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
//        System.out.println(deck.getCards());
        
        // Declarar matriz de cartas
        
     String[][] cards = new String[4][4];
        // Declarar matriz de booleanos
//     boolean[][] turned = new boolean[4][4];
        
     ArrayList<Card> Deck = deck.getCards();
     
     System.out.println();
     
      if (Deck.size() >= 16) {
            // Llenar la matriz de cartas
            int index = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Card card = Deck.get(index++);
                    cards[i][j] = card.getName(); // Asignar el nombre de la carta a la posición correspondiente en la matriz
                    System.out.print(card + "\t"); // Imprimir la carta y agregar un tabulador para mantener las columnas alineadas
                }
                System.out.println(); // Imprimir una nueva línea después de imprimir todas las cartas de una fila
            }
        } else {
            System.out.println("El mazo no tiene suficientes cartas para llenar la matriz.");
        }
        
          //Obtener la posición de fila y columna del usuario
          
        System.out.println();
        boolean jugador1 = true;
        
        while(jugador1){
            Scanner scanner = new Scanner(System.in);
             System.out.println("Ingrese la posición de la fila (0-3) para la primera carta:");
            int row1 = scanner.nextInt();

            System.out.println("Ingrese la posición de la columna (0-3) para la primera carta:");
            int col1 = scanner.nextInt();

            System.out.println("Ingrese la posición de la fila (0-3) para la segunda carta:");
            int row2 = scanner.nextInt();

            System.out.println("Ingrese la posición de la columna (0-3) para la segunda carta:");
            int col2 = scanner.nextInt();
            
            jugador1 = false;
            
             int card1 = row1*4+col1;
             int card2 = row2*4+col2;
             
             if(Deck.get(card1).equals(Deck.get(card2))){
                 System.out.println("hola joseANgel");
                 jugador1 = true;
                 
             }else{
                 System.out.println("Anaalcantara");
             }
//           
        }
        
    }

}
