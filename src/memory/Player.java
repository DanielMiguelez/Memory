/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

public class Player {
    
    public String name;
    public String password;
    public int points;
    
    public Player (String name, String password){
        this.name = name;
        this.password = password;
        this.points = 0;
    }
    
    public String getName(){
        return name;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String toString(){
        String info = "";
        return info + "name : " + name + "password : " + password + "points : " + points;
    }

}