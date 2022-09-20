/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import views.Busqueda;

/**
 *
 * @author Usuario
 */
public class ReservasDAO {
    public static int idDevuelto;
    public static void crearReservaDB(Reservas mensaje){
        ConexionBase db_connect= new ConexionBase();
        try(Connection conexion= db_connect.get_connection()){
            PreparedStatement ps=null;
            try{
               
                String query="INSERT INTO reservas(Valor, FormaPago,FechaEntrada, FechaSalida) VALUES (?,?,?,?) ";
                ps=conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
               
                ps.setInt(1, mensaje.getValor());
                ps.setString(2, mensaje.getFormaPago());
                 ps.setString(3, mensaje.getFechaEntrada());
                ps.setString(4,  mensaje.getFechaSalida());
                
                ps.executeUpdate();
               
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    
                 idDevuelto= rs.getInt(1);
               
                 
                }
            }catch (SQLException ex){
            System.out.println(ex);
            }
            
        }catch (SQLException e){
            System.out.println(e);
            
        }
    }
    
    public static void borrarReservaDB(int id){
         ConexionBase db_connect= new ConexionBase();
         try(Connection conexion= db_connect.get_connection()){
             PreparedStatement ps=null;
             try{ 
                 String query2="DELETE FROM huespedes WHERE idReserva=?";
                 ps=conexion.prepareStatement(query2);
                 ps.setInt(1, id);
                 ps.executeUpdate();
                 
                  String query="DELETE FROM reservas WHERE id=?";
                 ps=conexion.prepareStatement(query);
                 ps.setInt(1, id);
                 ps.executeUpdate();
                
                 
                 
            }catch (SQLException ex){
            System.out.println(ex);
            }
             
         }catch (SQLException e){
            System.out.println(e);
            
        }
    }
    public static void actualizarReservaDB( Reservas reserva){
         ConexionBase db_connect= new ConexionBase();
          try(Connection conexion= db_connect.get_connection()){
            PreparedStatement ps=null;
            try{
        
          
             String query = "UPDATE reservas set Valor=?, FormaPago=?,FechaEntrada=?, FechaSalida=? WHERE id=? ";
             ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
             
             ps.setInt(1, reserva.getValor());
             ps.setString(2, reserva.getFormaPago());
             ps.setString(3, reserva.getFechaEntrada());
             ps.setString(4, reserva.getFechaSalida());
             ps.setInt(5, reserva.getId());

             ps.executeUpdate();

             ResultSet rs = ps.getGeneratedKeys();
             if (rs.next()) {

                 idDevuelto = rs.getInt(1);
                 System.out.println(idDevuelto);
                

}
            }catch (SQLException ex){
            System.out.println(ex);
            }
            
        }catch (SQLException e){
            System.out.println(e);
            
        }
                 
            
             
       
    }
    
    
}
