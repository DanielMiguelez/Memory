/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

public class Card {
    
    public String name;
    private int id;
    private boolean turned;
    private String image;
    
    public Card (String name, int id){
        this.name = name;
        this.id = id;
        this.turned = false;
        this.image = image;
    }
    
    public String getName(){
        return name;
    }
    
    public int getId(){
        return id;
    }
    
    public boolean isTurned(){
        return turned;
    }
    
    public boolean flip(){
        return !turned;
    }
    
    public String getImage() {
        return image;
    }
    
    public String toString(){
        String info = "";
        return info + name + " " + id + " " +turned;
    }

}