/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import javafx.scene.image.Image;

public class Card {
    
    public String name;
    private int id;
    private boolean turned;
    private Image image;
    
    
    public Card (String name, int id,Image image){
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
    
    public void setTurned(){
        this.turned = true;
    }
    
     public boolean getTurned(){
        return turned;
    }
     public void setCovered(){
        this.turned = false;
    }
    public Image getImage() {
        return image;
    }
    @Override
    public String toString(){
        String info = "";
        return info + name + " " + id + " " +turned;
    }

}