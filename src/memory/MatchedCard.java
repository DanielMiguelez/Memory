/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

/**
 *
 * @author Dani
 */

public class MatchedCard extends Card {
    private boolean matched;
    
    public MatchedCard(String name, int id){
        super(name, id);
        this.matched = false;
    }
    
    public boolean isMatched(){
        return matched;
    }
    
    public void setMatched(boolean matched){
        this.matched = matched;
    }
 
    public boolean isSameCard(MatchedCard cardMatched){
        return this.getName().equals(cardMatched.getName())&& this.getId() == cardMatched.getId(); 
    }  
}


