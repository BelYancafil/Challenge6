package conect;


import java.sql.Connection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario
 */
public class Inicio {
    public static void main (String [] args){
        ConexionBase conexion = new ConexionBase();
        
        try (Connection cnx= conexion.get_connection()){
            
        }catch (Exception e){
            System.out.println(e);
        }
    }
    
}
