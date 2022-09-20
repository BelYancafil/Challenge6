package conect;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Usuario
 */
public class ConexionBase {
    public Connection get_connection(){
        Connection conection= null;
        try {
            conection= DriverManager.getConnection("jdbc:mysql://localhost:3306/alura_hotel","root","forever");
            
        }catch (SQLException e){
            System.out.println(e);
        }
        return conection;
        
    }
    
}
