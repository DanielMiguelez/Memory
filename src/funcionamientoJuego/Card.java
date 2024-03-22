/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionamientoJuego;

public class Card {
    
    public String name;
    public boolean uncovered;
    
    public Card (String name, boolean uncovered){
        this.name = name;
        this.uncovered = false;
    }
    
    public String getName(){
        return name;
    }
    
    public boolean isUncovered(){
        return uncovered;
    }
    
    public String toString(){
        String info = "";
        return info + "name : " + name + "Is uncovered : " + uncovered;
    }

}