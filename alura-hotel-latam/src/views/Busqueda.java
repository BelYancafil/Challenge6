package views;

import conect.ConexionBase;
import conect.HuespedesDao;
import conect.HuespedesServices;
import conect.ReservasDAO;
import conect.reservasService;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 
        private List <Map<String,String>> leerReservaDb() throws SQLException{
         ConexionBase db_connect= new ConexionBase();
         PreparedStatement ps=null; 
         ResultSet rs=null;
         Connection conexion= db_connect.get_connection();
             String query="SELECT Id, Valor,FormaPago,FechaEntrada,FechaSalida FROM reservas";
             ps= conexion.prepareStatement(query);
             rs=ps.executeQuery();
             List <Map<String,String>> resultado=new ArrayList<>();
             while (rs.next()){
                 Map<String,String> fila=new HashMap<>();
                 fila.put("Id", String.valueOf(rs.getInt("Id")));
                 fila.put("Valor",String.valueOf(rs.getInt("Valor")));
                 fila.put("FormaPago", rs.getString("FormaPago"));
                 fila.put("FechaEntrada", rs.getString("FechaEntrada"));
                 fila.put("FechaSalida", rs.getString("FechaSalida"));
                 
                 resultado.add(fila);
             }
            
             
        
        return resultado;
    }
        
    private List <Map<String,String>> leerHuespedDb() throws SQLException{
         ConexionBase db_connect= new ConexionBase();
         PreparedStatement ps=null; 
         ResultSet rs=null;
         Connection conexion= db_connect.get_connection();
             String query="SELECT id, Nombre, Apellido, Nacionalidad, FechaNacimiento, Telefono, IdReserva FROM huespedes";
             ps= conexion.prepareStatement(query);
             rs=ps.executeQuery();
             List <Map<String,String>> resultado=new ArrayList<>();
             while (rs.next()){
                 Map<String,String> fila=new HashMap<>();
                 
                 fila.put("Id", String.valueOf(rs.getInt("id")));
                 
                 fila.put("Nombre",rs.getString("Nombre"));
                 
                 fila.put("Apellido", rs.getString("Apellido"));
                 fila.put("Nacionalidad", rs.getString("Nacionalidad"));
                 fila.put("FechaNacimiento", rs.getString("FechaNacimiento"));
                 fila.put("Telefono", rs.getString("Telefono"));
                 fila.put("IdReserva", String.valueOf(rs.getInt("IdReserva")));
                
                 resultado.add(fila);
             }
            
             
        
        return resultado;
    }
    public void eliminarReserva(){
        String idS=modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString();
       // String idSR=modelo.getValueAt(tbReservas.getSelectedRow(), 6).toString();
        
        int id=Integer.parseInt(idS) ;
        ReservasDAO.borrarReservaDB(id);      
                  
                      
    }
     public void eliminarHuesped(){
        String idS=modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString();
        String idSR=modeloH.getValueAt(tbHuespedes.getSelectedRow(), 6).toString();
        
        int id=Integer.parseInt(idS) ;
        int idReserva=Integer.parseInt(idSR) ;      
        
        HuespedesDao.borrarHuespedDB(id, idReserva); 
        
                  
                      
    }
     
      public void actualizarReserva(){
        String idS=modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString();
        String valors=modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString();
        String fe=modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString();
        String fs=modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString();
        String fp=modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString();
        
        int id=Integer.parseInt(idS) ;
        int valor=Integer.parseInt(valors) ;         
        
        reservasService.editarReserva(id, fe, fs, valor, fp); 
        
                  
                      
    }
       public void actualizarHuesped(){
        String idS=modeloH.getValueAt(tbHuespedes.getSelectedRow(), 0).toString();
        String nom=modeloH.getValueAt(tbHuespedes.getSelectedRow(), 1).toString();
        String ape=modeloH.getValueAt(tbHuespedes.getSelectedRow(), 2).toString();
        String fecnac=modeloH.getValueAt(tbHuespedes.getSelectedRow(), 3).toString();
        String tel=modeloH.getValueAt(tbHuespedes.getSelectedRow(), 5).toString();
        String nac=modeloH.getValueAt(tbHuespedes.getSelectedRow(), 4).toString();
         String idRes=modeloH.getValueAt(tbHuespedes.getSelectedRow(), 6).toString();
       
        
        int id=Integer.parseInt(idS) ;
        int idRe=Integer.parseInt(idRes) ;         
         
        HuespedesServices.editarHuespedes(id, nom, ape, fecnac, nac, tel, idRe);
        
                  
                      
    }
       
        private List <Map<String,String>> buscarReservaDb() throws SQLException{
         ConexionBase db_connect= new ConexionBase();
         PreparedStatement ps=null; 
         ResultSet rs=null;
         Connection conexion= db_connect.get_connection();
          List<Map<String, String>> resultado = new ArrayList<>();
         //if (txtBuscar.getText() == null){
         
             int id = Integer.parseInt(txtBuscar.getText());
             String query = "SELECT Id, Valor,FormaPago,FechaEntrada,FechaSalida FROM reservas WHERE id=?";
             ps = conexion.prepareStatement(query);
             ps.setInt(1, id);

             rs = ps.executeQuery();
            
             while (rs.next()) {
                 Map<String, String> fila = new HashMap<>();
                 fila.put("Id", String.valueOf(rs.getInt("Id")));
                 fila.put("Valor", String.valueOf(rs.getInt("Valor")));
                 fila.put("FormaPago", rs.getString("FormaPago"));
                 fila.put("FechaEntrada", rs.getString("FechaEntrada"));
                 fila.put("FechaSalida", rs.getString("FechaSalida"));

                 resultado.add(fila);
             }
           
             
        // }
          
        return resultado; 
       
    }
         private List <Map<String,String>> buscarHuespedDb() throws SQLException{
         ConexionBase db_connect= new ConexionBase();
         PreparedStatement ps=null; 
         ResultSet rs=null;
         Connection conexion= db_connect.get_connection();
          String clave = txtBuscar.getText();
             String query="SELECT id, Nombre, Apellido, Nacionalidad, FechaNacimiento, Telefono, IdReserva FROM huespedes WHERE Apellido LIKE ? ";
   
             ps= conexion.prepareStatement(query);
              
              ps.setString(1,"%" + clave+ "%");
             rs=ps.executeQuery();
             List <Map<String,String>> resultado=new ArrayList<>();
             while (rs.next()){
                 Map<String,String> fila=new HashMap<>();
                 
                 fila.put("Id", String.valueOf(rs.getInt("id")));
                 
                 fila.put("Nombre",rs.getString("Nombre"));
                 
                 fila.put("Apellido", rs.getString("Apellido"));
                 fila.put("Nacionalidad", rs.getString("Nacionalidad"));
                 fila.put("FechaNacimiento", rs.getString("FechaNacimiento"));
                 fila.put("Telefono", rs.getString("Telefono"));
                 fila.put("IdReserva", String.valueOf(rs.getInt("IdReserva")));
                
                 resultado.add(fila);
             }
            
             
        
        return resultado;
    }
        


        
	/**
	 * Create the frame.
	 */
	public  Busqueda() {
            try {
                setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setBounds(100, 100, 910, 571);
                contentPane = new JPanel();
                contentPane.setBackground(Color.WHITE);
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
                setContentPane(contentPane);
                contentPane.setLayout(null);
                setLocationRelativeTo(null);
                setUndecorated(true);
                
                txtBuscar = new JTextField();
                txtBuscar.setBounds(536, 127, 193, 31);
                txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
                contentPane.add(txtBuscar);
                txtBuscar.setColumns(10);
                
                
                JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
                lblNewLabel_4.setForeground(new Color(12, 138, 199));
                lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
                lblNewLabel_4.setBounds(331, 62, 280, 42);
                contentPane.add(lblNewLabel_4);
                
                JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
                panel.setBackground(new Color(12, 138, 199));
                panel.setFont(new Font("Roboto", Font.PLAIN, 16));
                panel.setBounds(20, 169, 865, 328);
                contentPane.add(panel);     
                
                
                
                tbReservas = new JTable();
                tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
                panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), tbReservas, null);
                modelo = (DefaultTableModel) tbReservas.getModel();
                modelo.addColumn("Numero de Reserva");
                modelo.addColumn("Fecha Check In");
                modelo.addColumn("Fecha Check Out");
                modelo.addColumn("Valor");
                modelo.addColumn("Forma de Pago");
               
                
                var reservas =this.leerReservaDb();
                reservas.forEach(reserva-> modelo.addRow(new Object [] {reserva.get("Id"),reserva.get("FechaEntrada"),reserva.get("FechaSalida"),reserva.get("Valor")
                        ,reserva.get("FormaPago")}));
                
                tbHuespedes = new JTable();
                tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
                panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), tbHuespedes, null);
                modeloH = (DefaultTableModel) tbHuespedes.getModel();
                modeloH.addColumn("Numero de Huesped");
                modeloH.addColumn("Nombre");
                modeloH.addColumn("Apellido");
                modeloH.addColumn("Fecha de Nacimiento");
                modeloH.addColumn("Nacionalidad");
                modeloH.addColumn("Telefono");
                modeloH.addColumn("Numero de Reserva");
               
                var huespedes =this.leerHuespedDb();
                huespedes.forEach(huesped-> modeloH.addRow(new Object [] {huesped.get("Id")
                        ,huesped.get("Nombre"),huesped.get("Apellido"),huesped.get("FechaNacimiento")
                        ,huesped.get("Nacionalidad"),huesped.get("Telefono")
                        ,huesped.get("IdReserva")}));
                
                JLabel lblNewLabel_2 = new JLabel("");
                lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
                lblNewLabel_2.setBounds(56, 51, 104, 107);
                contentPane.add(lblNewLabel_2);
                
                JPanel header = new JPanel();
                header.addMouseMotionListener(new MouseMotionAdapter() {
                    @Override
                    public void mouseDragged(MouseEvent e) {
                        headerMouseDragged(e);
                        
                    }
                });
                header.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        headerMousePressed(e);
                    }
                });
                header.setLayout(null);
                header.setBackground(Color.WHITE);
                header.setBounds(0, 0, 910, 36);
                contentPane.add(header);
                
                JPanel btnAtras = new JPanel();
                btnAtras.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        MenuUsuario usuario = new MenuUsuario();
                        usuario.setVisible(true);
                        dispose();
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        btnAtras.setBackground(new Color(12, 138, 199));
                        labelAtras.setForeground(Color.white);
                    }
                    @Override
                    public void mouseExited(MouseEvent e) {
                        btnAtras.setBackground(Color.white);
                        labelAtras.setForeground(Color.black);
                    }
                });
                btnAtras.setLayout(null);
                btnAtras.setBackground(Color.WHITE);
                btnAtras.setBounds(0, 0, 53, 36);
                header.add(btnAtras);
                
                labelAtras = new JLabel("<");
                labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
                labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
                labelAtras.setBounds(0, 0, 53, 36);
                btnAtras.add(labelAtras);
                
                JPanel btnexit = new JPanel();
                btnexit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        MenuUsuario usuario = new MenuUsuario();
                        usuario.setVisible(true);
                        dispose();
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
                        btnexit.setBackground(Color.red);
                        labelExit.setForeground(Color.white);
                    }
                    @Override
                    public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
                        btnexit.setBackground(Color.white);
                        labelExit.setForeground(Color.black);
                    }
                });
                btnexit.setLayout(null);
                btnexit.setBackground(Color.WHITE);
                btnexit.setBounds(857, 0, 53, 36);
                header.add(btnexit);
                
                labelExit = new JLabel("X");
                labelExit.setHorizontalAlignment(SwingConstants.CENTER);
                labelExit.setForeground(Color.BLACK);
                labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
                labelExit.setBounds(0, 0, 53, 36);
                btnexit.add(labelExit);
                
                JSeparator separator_1_2 = new JSeparator();
                separator_1_2.setForeground(new Color(12, 138, 199));
                separator_1_2.setBackground(new Color(12, 138, 199));
                separator_1_2.setBounds(539, 159, 193, 2);
               contentPane.add(separator_1_2);
                
                JPanel btnbuscar = new JPanel();
               btnbuscar.addMouseListener(new MouseAdapter() {
                    @Override
                    
                    public void mouseClicked(MouseEvent e) {
                        
                         if (panel.getSelectedIndex() == 0)  {
                          
                             if (txtBuscar.getText().isEmpty()) {

                                 JOptionPane.showMessageDialog(null, "Debe ingresar el nro de reserva para realizar la busqueda");

                             } else {
                                 try {

                                     var reservas = buscarReservaDb();
                                     modelo.setRowCount(0);
                                     reservas.forEach(reserva -> modelo.addRow(new Object[]{reserva.get("Id"), reserva.get("FechaEntrada"), reserva.get("FechaSalida"), reserva.get("Valor"),
                                         reserva.get("FormaPago")}));
                                 } catch (SQLException ex) {
                                     Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                                 }

                             }
                        } else if (panel.getSelectedIndex() == 1) {
                            if (txtBuscar.getText().isEmpty()) {

                                 JOptionPane.showMessageDialog(null, "Debe ingresar el apellido del huesped para realizar la busqueda");

                             } else {
                                 try {
                                     
                                     var huespedes = buscarHuespedDb();
                                     modeloH.setRowCount(0);

                                     huespedes.forEach(huesped -> modeloH.addRow(new Object[]{huesped.get("Id"),
                                          huesped.get("Nombre"), huesped.get("Apellido"), huesped.get("FechaNacimiento"),
                                          huesped.get("Nacionalidad"), huesped.get("Telefono"),
                                          huesped.get("IdReserva")}));
                                    
                                 } catch (SQLException ex) {
                                     Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                                 }

                             }
                        
                         }
                
                        
                        
                    }
                });
                
                
                
                btnbuscar.setLayout(null);
                btnbuscar.setBackground(new Color(12, 138, 199));
                btnbuscar.setBounds(748, 125, 122, 35);
                btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                contentPane.add(btnbuscar);
                
                JLabel lblBuscar = new JLabel("BUSCAR");
                lblBuscar.setBounds(0, 0, 122, 35);
                btnbuscar.add(lblBuscar);
               lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
               lblBuscar.setForeground(Color.WHITE);
               lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));
                
                JLabel lblAviso = new JLabel("RECUERDA: SI SE ELIMINA LA RESERVA SE ELIMINA TAMBIEN EL HUESPED ASOCIADO Y VICEVERSA");
                lblAviso.setBounds(9, 508,600, 35);               
                lblAviso.setHorizontalAlignment(SwingConstants.CENTER);
                lblAviso.setForeground(Color.RED);
                lblAviso.setFont(new Font("Roboto", Font.PLAIN, 12));
                
                contentPane.add(lblAviso);
                
                JPanel btnEditar = new JPanel();
                btnEditar.setLayout(null);
                btnEditar.setBackground(new Color(12, 138, 199));
                btnEditar.setBounds(635, 508, 122, 35);
                btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                contentPane.add(btnEditar);
                
                JLabel lblEditar = new JLabel("EDITAR");
                lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
                lblEditar.setForeground(Color.WHITE);
                lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
                lblEditar.setBounds(0, 0, 122, 35);
                btnEditar.add(lblEditar);
                
                btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                           
                                if (panel.getSelectedIndex()==0){
                                    try {
                                      if(tbReservas.getSelectedRow()==-1){
                                         JOptionPane.showMessageDialog(null, "Debe seleccionar una reserva");  
                                       }else{  
                                        actualizarReserva();
                                        JOptionPane.showMessageDialog(null, "La reserva se actualizo correctamente");
                                        modelo.setRowCount(0);
                                        var reservas = leerReservaDb();
                                        reservas.forEach(reserva -> modelo.addRow(new Object[]{reserva.get("Id"), reserva.get("FechaEntrada"), reserva.get("FechaSalida"), reserva.get("Valor"),
                                             reserva.get("FormaPago")}));
                                      }
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }else if (panel.getSelectedIndex()==1){
                                   try{
                                       if(tbHuespedes.getSelectedRow()==-1){
                                         JOptionPane.showMessageDialog(null, "Debe seleccionar un huesped");  
                                       }else{
                                        actualizarHuesped();
                                       
                                        JOptionPane.showMessageDialog(null, "El huesped se actualizo correctamente");
                                        modeloH.setRowCount(0);
                                        var huespedes = leerHuespedDb();
                                        huespedes.forEach(huesped -> modeloH.addRow(new Object[]{huesped.get("Id"),
                                          huesped.get("Nombre"), huesped.get("Apellido"), huesped.get("FechaNacimiento"),
                                          huesped.get("Nacionalidad"), huesped.get("Telefono"),
                                        huesped.get("IdReserva")}));
                                        }
                                    } catch (SQLException ex) {
                                       Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
			}
		});
               
                
          
                
                JPanel btnEliminar = new JPanel();
                btnEliminar.setLayout(null);
                btnEliminar.setBackground(new Color(12, 138, 199));
                btnEliminar.setBounds(767, 508, 122, 35);
                btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
                contentPane.add(btnEliminar);
                
                JLabel lblEliminar = new JLabel("ELIMINAR");
                lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
                lblEliminar.setForeground(Color.WHITE);
                lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
                lblEliminar.setBounds(0, 0, 122, 35);
                btnEliminar.add(lblEliminar);
                setResizable(false);
                btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
                           
                                if (panel.getSelectedIndex()==0){
                                    try {
                                         if(tbReservas.getSelectedRow()==-1){
                                         JOptionPane.showMessageDialog(null, "Debe seleccionar una reserva");  
                                       }else{  
                                        eliminarReserva();
                                        JOptionPane.showMessageDialog(null, "La reserva y el huesped asociado se eliminaron correctamente");
                                        modeloH.setRowCount(0);
                                        var huespedes = leerHuespedDb();
                                        huespedes.forEach(huesped -> modeloH.addRow(new Object[]{huesped.get("Id"),
                                             huesped.get("Nombre"), huesped.get("Apellido"), huesped.get("FechaNacimiento"),
                                             huesped.get("Nacionalidad"), huesped.get("Telefono"),
                                             huesped.get("IdReserva")}));

                                        modelo.setRowCount(0);
                                        var reservas = leerReservaDb();
                                        reservas.forEach(reserva -> modelo.addRow(new Object[]{reserva.get("Id"), reserva.get("FechaEntrada"), reserva.get("FechaSalida"), reserva.get("Valor"),
                                             reserva.get("FormaPago")}));
                                         }
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }else if (panel.getSelectedIndex()==1){
                                   try{
                                        if(tbHuespedes.getSelectedRow()==-1){
                                         JOptionPane.showMessageDialog(null, "Debe seleccionar un huesped");  
                                       }else{
                                        eliminarHuesped();
                                        JOptionPane.showMessageDialog(null, "El huesped y la reserva asociada se eliminaron correctamente");
                                        modeloH.setRowCount(0);
                                        var huespedes = leerHuespedDb();
                                        huespedes.forEach(huesped -> modeloH.addRow(new Object[]{huesped.get("Id"),
                                            huesped.get("Nombre"), huesped.get("Apellido"), huesped.get("FechaNacimiento"),
                                            huesped.get("Nacionalidad"), huesped.get("Telefono"),
                                            huesped.get("IdReserva")}));

                                        modelo.setRowCount(0);
                                        var reservas = leerReservaDb();
                                        reservas.forEach(reserva -> modelo.addRow(new Object[]{reserva.get("Id"), reserva.get("FechaEntrada"), reserva.get("FechaSalida"), reserva.get("Valor"),
                                            reserva.get("FormaPago")}));
                                        }
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                
                              
                               
			}
		});
               
                
            } catch (SQLException ex) {
                Logger.getLogger(Busqueda.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
	
//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
	 private void headerMousePressed(java.awt.event.MouseEvent evt) {
	        xMouse = evt.getX();
	        yMouse = evt.getY();
	    }

	    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
	        int x = evt.getXOnScreen();
	        int y = evt.getYOnScreen();
	        this.setLocation(x - xMouse, y - yMouse);
}
}
