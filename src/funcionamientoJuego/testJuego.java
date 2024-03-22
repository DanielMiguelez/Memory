/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionamientoJuego;

import java.util.Scanner;

public class testJuego {
    
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
        
        System.out.println("Introduce el nombre del primer jugador");
        String nombreJugador1 = sc.nextLine();
        System.out.println("Ingrese la contraseña del primer jugador:");
        String contrasenyaJugador1 = sc.nextLine();
        
        System.out.println("Introduce el nombre del segundo jugador");
        String nombreJugador2 = sc.nextLine();
        System.out.println("Ingrese la contraseña del segundo jugador:");
        String contrasenyaJugador2 = sc.nextLine();
        
        // SOLICITAR NOMBRE DE LOS JUGADORES -------------------------
        
        Jugador jugador1 = new Jugador(nombreJugador1, contrasenyaJugador1);
        Jugador jugador2 = new Jugador(nombreJugador2, contrasenyaJugador2);
        
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
        
        int filas = 4; // Determinamos el numero de filas.
        int columnas = 4; // Determinamos las columnas.
        
        Card[][] matrizCartas = new Card[filas][columnas];
        boolean[][] cartasDescubiertas = new boolean[filas][columnas];
        
        int cartaIndex = 0;
        for(int i = 0; i<filas; i++){
            for(int j= 0; j< columnas; j++){
                matrizCartas[i][j] = deck.getCards().get(cartaIndex);
                cartaIndex++;
            }
        }
        
        
        // INICIAMOS EL JUEGO ----------------
        
        boolean juegoTerminado = false;
        
        int puntosJugador1 = 0;
        int puntosJugador2 = 0;
        int turnoJugador = 1;
        int intentosJugador1 = 0;
        int intentosJugador2 = 0;
        int parejasAcertadas = 0;
        int parejasTotales = filas*columnas /2;
        
        while (!juegoTerminado) {
            
        // Mostrar el tablero actual
   
        System.out.println("Tablero actual:");
        
        mostrarTablero(matrizCartas, cartasDescubiertas);
         
            //Aqui empieza el turno del jugador numero 1
            
            System.out.println("Turno del jugador " + jugador1.getName());
            
            System.out.println("Selecciona la fila de la primera carta:");
            int filaCarta1 = sc.nextInt();
            
            System.out.println("Introduce la columna de la primera carta");
            int columnaCarta1 = sc.nextInt();
            
            cartasDescubiertas[filaCarta1][columnaCarta1] = true;
            
            
            System.out.println("Selecciona la fila de la segunda carta:");
            int filaCarta2 = sc.nextInt();
            
            System.out.println("Introduce la columna de la segunda carta");
            int columnaCarta2 = sc.nextInt();
            
            cartasDescubiertas[filaCarta2][columnaCarta2] = true;
            
            // Tablero despues del turno del primer jugador
            
            System.out.println("Tablero después del turno de " + jugador1.getName() + ":");
            
            mostrarTablero(matrizCartas, cartasDescubiertas);
           
            
            //Aqui empieza el turno del jugador numero 2
            
            System.out.println("Turno del jugador " + jugador2.getName());
            
            System.out.println("Selecciona la fila de la primera carta:");
            int filaCarta3 = sc.nextInt();
            
            System.out.println("Introduce la columna de la primera carta");
            int columnaCarta3 = sc.nextInt();
            
            cartasDescubiertas[filaCarta3][columnaCarta3] = true;
            
            
            System.out.println("Selecciona la fila de la segunda carta:");
            int filaCarta4 = sc.nextInt();
            
            System.out.println("Introduce la columna de la segunda carta");
            int columnaCarta4 = sc.nextInt();
            
            cartasDescubiertas[filaCarta4][columnaCarta4] = true;
            
            // Tablero despues del turno del primer jugador
            
            System.out.println("Tablero después del turno de " + jugador2.getName() + ":");
             
            mostrarTablero(matrizCartas, cartasDescubiertas);
              
            
            if (matrizCartas[filaCarta1][columnaCarta1].getName().equals(matrizCartas[filaCarta3][columnaCarta3].getName())
                && matrizCartas[filaCarta2][columnaCarta2].getName().equals(matrizCartas[filaCarta4][columnaCarta4].getName())) {
                
                 // Las cartas son iguales
            System.out.println("¡Las cartas son iguales!");
            // Incrementar el puntaje del jugador actual
            
                if (turnoJugador == 1) {
                    puntosJugador1++;
                } else {
                    puntosJugador2++;
                }
                
            } else {
                // Las cartas no son iguales, volver a ponerlas boca abajo
                cartasDescubiertas[filaCarta1][columnaCarta1] = false;
                cartasDescubiertas[filaCarta2][columnaCarta2] = false;
                cartasDescubiertas[filaCarta3][columnaCarta3] = false;
                cartasDescubiertas[filaCarta4][columnaCarta4] = false;
                System.out.println("Las cartas no son iguales. ¡Inténtalo de nuevo!");
            }  
        }  
    }
}


