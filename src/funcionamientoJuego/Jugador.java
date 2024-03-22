/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcionamientoJuego;

public class Jugador {
    
    public String name;
    public String contrasenya;
    
    public Jugador (String name, String contrasenya){
        this.name = name;
        this.contrasenya = contrasenya;
    }
    
    public String getName(){
        return name;
    }
    
    public String getContrasenya(){
        return contrasenya;
    }
    
    public String toString(){
        String info = "";
        return info + "name : " + name + "Contrasenya : " + contrasenya;
    }

}