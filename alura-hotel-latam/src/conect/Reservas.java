/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conect;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Reservas {
    int id;
   String FechaEntrada;
    String FechaSalida;
    int Valor;
    String FormaPago;
    
    public Reservas(){    
    }
      public Reservas(String FechaEntrada, String FechaSalida, int Valor, String FormaPago) {
        this.FechaEntrada = FechaEntrada;
        this.FechaSalida = FechaSalida;
        this.Valor = Valor;
        this.FormaPago = FormaPago;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaEntrada() {
        return FechaEntrada;
    }

    public void setFechaEntrada(String FechaEntrada) {
        this.FechaEntrada = FechaEntrada;
    }

    public String getFechaSalida() {
        return FechaSalida;
    }

    public void setFechaSalida(String FechaSalida) {
        this.FechaSalida = FechaSalida;
    }

    public int getValor() {
        return Valor;
    }

    public void setValor(int Valor) {
        this.Valor = Valor;
    }

    public String getFormaPago() {
        return FormaPago;
    }

    public void setFormaPago(String FormaPago) {
        this.FormaPago = FormaPago;
    }
    
    
    
}
