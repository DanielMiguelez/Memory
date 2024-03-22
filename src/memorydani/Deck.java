 package memorydani;

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
    
    // Método para inicializar la baraja con un conjunto de cartas predefinido
     public void initializeDeck(String[] symbols) {
        for (String symbol : symbols) {
            cards.add(new Card(symbol, false));
        }
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
    
}
