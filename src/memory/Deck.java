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
    
    public static void printCardsMatrix(ArrayList<Card> deck, String[][] cards) {
        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Card card = deck.get(index++);
                cards[i][j] = card.getName();
                System.out.print(card + "\t");
            }
            System.out.println();
        }
    }
}
