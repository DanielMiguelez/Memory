/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

public class Card {
    
    public String name;
     private int id;
    
    public Card (String name, int id){
        this.name = name;
        this.id = id;
    }
    
    public String getName(){
        return name;
    }
    
    public int getId(){
        return id;
    }
    
    public String toString(){
        String info = "";
        return info + name + " " + id;
    }

}