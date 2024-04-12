/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

public class Player {
    
    public String name;
    public String password;
    public int victories;
    public int points;
    public int id;
    
    public Player (int id,String name,int points, int victories){
        this.name = name;
        this.password = password;
        this.points = 0;
        this.victories = 0;
        this.id = 0;
    }
 
    public String getName(){
        return name;
    }
    
    public String getPassword(){
        return password;
    }
    
     public int getPoints(){
        return points;
    }
     
      public int getVictories(){
        return victories;
    }
    
    public void sumPoints(int points){
        this.points += points;
    }
    
    public void sumVictories(int victories){
        this.victories += victories;
    }
    
    public String toString(){
        String info = "";
        return info + "name : " + name + "password : " + password + "points : " + points + "Victories : " + victories;
    }

}