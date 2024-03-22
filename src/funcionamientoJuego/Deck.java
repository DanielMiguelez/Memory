package funcionamientoJuego;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Dani
 */

public class Deck {
    private ArrayList<Card> cards;
    
    public Deck (){
        cards = new ArrayList <>();
        }
    
    public void addCards(Card card){
        cards.add(card);
    }
    
    public void shuffle() {
        Collections.shuffle(cards);
    }
    
    public ArrayList <Card> getCards(){
        return cards;
    }
    
    
    // FUNCIONES RESPECTO A LAS CARTAS DE LA BARAJA -----------------
    
    public void mostrarCarta(int i){
        if(i >= 0 && i < cards.size()){
            Card carta = cards.get(i);
            carta.uncovered = true;
            }
        }
    
    public boolean compararcartas(int indiceCarta1, int indiceCarta2) {
    if (indiceCarta1 >= 0 && indiceCarta1 < cards.size() &&
        indiceCarta2 >= 0 && indiceCarta2 < cards.size()) {
        Card carta1 = cards.get(indiceCarta1);
        Card carta2 = cards.get(indiceCarta2);
        return carta1.getName().equals(carta2.getName());
    }
    return false;
    }
}
