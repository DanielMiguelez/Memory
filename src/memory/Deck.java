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
    
    
    // FUNCIONES RESPECTO A LAS CARTAS DE LA BARAJA -----------------
    
//    public void showCard(int i){
//        if(i >= 0 && i < cards.size()){
//            Card card = cards.get(i);
//            card.uncovered = true;
//            }
//        }
//    
//    public boolean compareCards(int indexCard1, int indexCard2) {
//    if (indexCard1 >= 0 && indexCard1 < cards.size() &&
//        indexCard2 >= 0 && indexCard2 < cards.size()) {
//        Card carta1 = cards.get(indexCard1);
//        Card carta2 = cards.get(indexCard2);
//        return carta1.getName().equals(carta2.getName());
//    }
//    return false;
//    }
}
