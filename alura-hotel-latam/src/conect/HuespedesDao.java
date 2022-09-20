/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conect;

import static conect.ReservasDAO.idDevuelto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Usuario
 */
public class HuespedesDao {
    public static void crearHuespedDB(Huespedes huesped){
        ConexionBase db_connect= new ConexionBase();
        try(Connection conexion= db_connect.get_connection()){
            PreparedStatement ps=null;
            try{
               
                String query="INSERT INTO Huespedes(Nombre, Apellido, Nacionalidad,IdReserva,FechaNacimiento,Telefono) VALUES (?,?,?,?,?,?) ";
                ps=conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                
                ps.setString(1, huesped.getNombre());
                ps.setString(2, huesped.getApellido());
                 ps.setString(3, huesped.getNacionalidad());
                 ps.setInt(4, huesped.getId_reserva());
                ps.setString(5,  huesped.getfNacimiento());
                ps.setString(6, huesped.getTelefono());
                
                ps.executeUpdate();
               
            
            }catch (SQLException ex){
            System.out.println(ex);
            }
            
        }catch (SQLException e){
            System.out.println(e);
            
        }
    }
    
    public static void borrarHuespedDB(int id, int idReserva){
         ConexionBase db_connect= new ConexionBase();
         try(Connection conexion= db_connect.get_connection()){
             PreparedStatement ps=null;
             try{ 
                 String query2="DELETE FROM huespedes WHERE id=?";
                 ps=conexion.prepareStatement(query2);
                 ps.setInt(1, id);
                 ps.executeUpdate();
                 
                  String query="DELETE FROM reservas WHERE id=?";
                 ps=conexion.prepareStatement(query);
                 ps.setInt(1, idReserva);
                 ps.executeUpdate();
                
                 
                 
            }catch (SQLException ex){
            System.out.println(ex);
            }
             
         }catch (SQLException e){
            System.out.println(e);
            
        }
     
    }
    public static void actualizarHuespedDB(Huespedes huesped){
        ConexionBase db_connect= new ConexionBase();
          try(Connection conexion= db_connect.get_connection()){
            PreparedStatement ps=null;
            try{
                
             String query = "UPDATE huespedes set Nombre=?, Apellido=?, Nacionalidad=?, IdReserva=?, FechaNacimiento=?, Telefono=? WHERE id=? ";
             ps = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
             
             ps.setString(1, huesped.getNombre());
             ps.setString(2, huesped.getApellido());
             ps.setString(3, huesped.getNacionalidad());
             ps.setInt(4, huesped.getId_reserva());
             ps.setString(5, huesped.getfNacimiento());
             ps.setString(6, huesped.getTelefono());
             ps.setInt(7, huesped.getId());

             ps.executeUpdate();
             

             ResultSet rs = ps.getGeneratedKeys();
             if (rs.next()) {

                 idDevuelto = rs.getInt(1);
                 System.out.println(idDevuelto);
                 System.out.println("actualice");

}
            }catch (SQLException ex){
            System.out.println(ex);
            }
            
        }catch (SQLException e){
            System.out.println(e);
            
        }
                 
            
             
       
    }
}
