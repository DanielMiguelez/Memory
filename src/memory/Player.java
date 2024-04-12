/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

public class Player {
    public int id;
    public String name;
    public int victories;
    public int nivel;
    public String password;

    public Player (int id,String name,int victories,int nivel,String password){
        this.id = id;
        this.name = name;
        this.victories = victories;
        this.nivel = nivel;
        this.password = password;
    }
    
 
    public String getName(){
        return name;
    }
    
    public String getPassword(){
        return password;
    }
    
     public int getVictories(){
        return victories;
    }

    
    public void sumPoints(int points){
        this.victories += victories;
    }
    
    public void sumVictories(int victories){
        this.victories += victories;
    }
    
    public String toString(){
        String info = "";
        return info + "id : " + id + " name : " + name + " victories : " + victories + " Nivel : "+nivel + " password : " + password ;
    }

}