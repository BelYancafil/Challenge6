/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conect;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Usuario
 */
public class reservasService {
    public static void crearReserva(String fe,String fs,int v,String fp){
      Reservas registro =new Reservas()  ;
      registro.setFechaEntrada(fe);
      registro.setFechaSalida(fs);
      registro.setValor(v);
      registro.setFormaPago(fp);
      
      ReservasDAO.crearReservaDB(registro);
      
    }
    
    
     public static void editarReserva(int id, String fe, String fs, int valor, String fp){
        Reservas reserva= new Reservas();
        reserva.setId(id);
        reserva.setFechaEntrada(fe);
        reserva.setFechaSalida(fs);
        reserva.setFormaPago(fp);
        reserva.setValor(valor);
        
        ReservasDAO.actualizarReservaDB(reserva);
    }
    
}
