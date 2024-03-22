/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionamientoJuego;

import java.util.Scanner;

public class testJuego {
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

        // Iteramos para obtener las cartas y su posición, volteadas o no volteadas.
        for (int i = 0; i < deck.getCards().size(); i++) {
            Card card = deck.getCards().get(i);
            System.out.println("Nombre de la carta: " + card.getName());
            System.out.println("Estado de la carta: " + card.isUncovered());
            System.out.println(); // Espacio en blanco para mejorar la legibilidad
        }
        
        // INICIAMOS EL JUEGO ----------------
        
        boolean juegoTerminado = false;
        
        int puntosJugador1 = 0;
        int puntosJugador2 = 0;
        
        while(!juegoTerminado){
            
        }
        
    }
}


