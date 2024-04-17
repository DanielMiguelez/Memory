package memory;

import java.util.ArrayList;
import java.util.Collections;

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
}
