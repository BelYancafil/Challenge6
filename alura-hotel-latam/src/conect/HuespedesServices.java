/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conect;

/**
 *
 * @author Usuario
 */
public class HuespedesServices {
    public static void crearHuesped(String nom,String ape,String nac,int idRes,String fn, String tel){
      Huespedes registro =new Huespedes()  ;
      registro.setNombre(nom);
      registro.setApellido(ape);
      registro.setNacionalidad(nac);
      registro.setId_reserva(idRes);
      registro.setfNacimiento(fn);
      registro.setTelefono(tel);
      
      HuespedesDao.crearHuespedDB(registro);
      
    }
    
     public static void borrarHuespedes(){
        
    }
     public static void editarHuespedes(int id, String nom, String ape, String fnac, String nac, String tel, int idres){
        Huespedes huesped= new Huespedes();
        huesped.setId(id);
        huesped.setNombre(nom);
        huesped.setApellido(ape);
        huesped.setfNacimiento(fnac);
        huesped.setNacionalidad(nac);
        huesped.setTelefono(tel);
        huesped.setId_reserva(idres);
        
        HuespedesDao.actualizarHuespedDB(huesped);
    }
     
}
