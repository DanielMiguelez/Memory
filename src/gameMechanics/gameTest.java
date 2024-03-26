/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameMechanics;
import gameMechanics.Deck;
import java.util.Scanner;

public class gameTest {
    
     // Función para mostrar el tablero
    
    public static void mostrarTablero(Card[][] matrizCartas, boolean[][] cartasDescubiertas) {
        int filas = matrizCartas.length;
        int columnas = matrizCartas[0].length;
        
        // Bucle para iterar sobre cada fila y columna del tablero
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (cartasDescubiertas[i][j]) {
                    System.out.print(matrizCartas[i][j].getName() + " ");
                } else {
                    System.out.print("Boca Abajo ");
                }
            }
            System.out.println(); // Nueva línea para la próxima fila
        }
    }
    
    //-----------------------------------------------------------------------------------------------
    
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        //CREAMOS LOS JUGADORES PARA SER SOLICITADOS MAS TARDE ------------
        
        System.out.println("Introduce Player 1 name");
        String PlayerName1 = sc.nextLine();
        System.out.println("Introduce Player 1 password");
        String PasswordPlayer1 = sc.nextLine();
        
        System.out.println("Introduce Player 2 name");
        String PlayerName2 = sc.nextLine();
        System.out.println("Introduce Player 2 password");
        String PasswordPlayer2 = sc.nextLine();
        
        // SOLICITAR NOMBRE DE LOS JUGADORES -------------------------
        
        Player player1 = new Player(PlayerName1, PasswordPlayer1);
        Player player2 = new Player(PlayerName2, PasswordPlayer2);
        
        Deck deck = new Deck();

        Card Mario = new Card("Mario", false);
        Card Luigi = new Card("Luigi", false);
        Card Wario = new Card("Wario", false);
        Card Toad = new Card("Toad", false);
        Card Yoshi = new Card("Yoshi", false);
        Card Peach = new Card("Peach", false);
        Card Bowser = new Card("Bowser", false);
        Card Koopa = new Card("Koopa", false);

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
        
        int rows = 4; // Determinamos el numero de filas.
        int columns = 4; // Determinamos las columnas.
        
        Card[][] matrizCartas = new Card[rows][columns];
        boolean[][] cartasDescubiertas = new boolean[rows][columns];
        
        int cartaIndex = 0;
        for(int i = 0; i<rows; i++){
            for(int j= 0; j< columns; j++){
                matrizCartas[i][j] = deck.getCards().get(cartaIndex);
                cartaIndex++;
            }
        }
    }
}


