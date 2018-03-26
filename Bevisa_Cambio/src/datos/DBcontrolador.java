  
package datos;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class DBcontrolador {

    public Connection getCnx() {
        return cnx;
    }
     public DBcontrolador(Connection cnx) {
        this.cnx = null;
    }

    private Connection cnx;
  
    public DBcontrolador (String svr,String bd,String usr,String cve) throws SQLException{
        //java.sql.Connection cnx = null;
        String cnxStr="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://"+svr+"/"+bd;
        try{
            Class.forName(cnxStr);
            System.out.println("Controlador cargando...");
            
            cnx=DriverManager.getConnection(url, usr, cve);
            System.out.println("Conexion establecida");
        }
        catch(ClassNotFoundException ex){
            System.out.println("No se pudo cargar el controlador...");
        }
         catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en la conexion a la base de datos");
           
          }
    }
    
    private  final  String svr="localhost";
    private  final  String usr="root";
    private  final  String bd="insetelc_Bevisa";
    private  final  String pass="1234";//Clave
    
    public DBcontrolador () throws SQLException{
        //java.sql.Connection cnx = null;
        String cnxStr="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://"+this.svr+"/"+this.bd;
        try{
            Class.forName(cnxStr);
            System.out.println("Controlador cargando...");
            
            cnx=DriverManager.getConnection(url, this.usr, this.pass);
            System.out.println("Conexion establecida");
        }
        catch(ClassNotFoundException ex){
            System.out.println("No se pudo cargar el controlador...");
        }
         catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error en la conexion a la base de datos");
            
          }
    }

    public ArrayList seleccionar(String query){
            
            
           ArrayList <String[]> op = new ArrayList<>();
        try{
           Statement cmd =this.cnx.createStatement(); 
           ResultSet res=cmd.executeQuery(query);
           ResultSetMetaData pp = res.getMetaData();
           while(res.next()){
               String[] result = new String[pp.getColumnCount()];
               for (int i = 0; i < result.length; i++) {
                   result[i]=res.getString(i+1); 
               }
                op.add(result);
           }
           res.close();
           cmd.close();

        }
        catch(SQLException ex){
            System.out.println("Error en la DB"+ex);
            this.cnx=this.getCnx();
        }
        return op;
    }
    
    public String seleccionarid(String query){
            
        String id="";
        try{
           Statement cmd =this.cnx.createStatement(); 
           ResultSet res=cmd.executeQuery(query);
           while(res.next()){
           id=res.getString(1);
           }
           res.close();
           cmd.close();

        }
        catch(SQLException ex){
            System.out.println("Error en la DB"+ex);
            this.cnx=this.getCnx();
        }
        return id;
    }
    
    public ArrayList img (String query)        
    {
        ArrayList <InputStream> op = new ArrayList<>();
        try{
           Statement cmd =this.cnx.createStatement(); 
           ResultSet res=cmd.executeQuery(query);
           
           while(res.next()){
               
                op.add(res.getBinaryStream("img"));
           }
           res.close();
           cmd.close();

        }
        catch(SQLException ex){
            System.out.println("Error en la DB"+ex);
            this.cnx=this.getCnx();
        }
        return op;
    }
    
    public ArrayList columnas(String query){
            
            
           ArrayList <String> op = new ArrayList<>();
        try{
           Statement cmd =this.cnx.createStatement(); 
           ResultSet res=cmd.executeQuery(query);
           ResultSetMetaData pp = res.getMetaData();

               for (int i = 1; i < pp.getColumnCount()+1; i++) {
                   op.add(pp.getColumnName(i));
               }

           res.close();
           cmd.close();

        }
        catch(SQLException ex){
            System.out.println("Error en la DB"+ex);
            this.cnx=this.getCnx();
        }
        return op;
    }
    
    
    public void operacion (String query)
    {
         try{

            Statement cmd =this.cnx.createStatement(); 
            cmd.executeUpdate(query);
            cmd.close();

        }
        catch(SQLException ex){
            System.out.println("Error en la DB"+ ex.toString());
            this.cnx=this.getCnx();
        }
    }
    
    public void cierraCnx() throws SQLException{
        cnx.close();
    }
    
    
    public boolean DBcom (ComboBox cb, String query) throws SQLException
     {

         ArrayList <String[]> op = new ArrayList<>();
             op=seleccionar(query);
             for (int i = 0; i < op.size(); i++) {
               if((cb.getSelectionModel().getSelectedIndex()+1) == Integer.parseInt(op.get(i)[0]) )
               {
                   return false;
               }
            }
             
        return true;     
     }
    
    public boolean DBcom (JTextField tx, String query) throws SQLException
     {

            ArrayList <String[]> op = new ArrayList<>();
             op=seleccionar(query);
             for (int i = 0; i < op.size(); i++) {
               if(tx.getText().toLowerCase().compareTo(op.get(i)[0].toLowerCase()) == 0)
               {
                   return true;
               }
            }
             
        return false;     
     }
    
    public boolean DBcom (String tx, String query) throws SQLException
     {

            ArrayList <String[]> op = new ArrayList<>();
             op=seleccionar(query);
             for (int i = 0; i < op.size(); i++) {
               if(tx.toLowerCase().compareTo(op.get(i)[0].toLowerCase()) == 0)
               {
                   return true;
               }
            }
             
        return false;     
     }
    
    public boolean login (JTextField tx, String query) throws SQLException
     {

            ArrayList <String[]> op = new ArrayList<>();
             op=seleccionar(query);
             for (int i = 0; i < op.size(); i++) {
               if(tx.getText().compareTo(op.get(i)[0]) == 0)
               {
                   return true;
               }
            }
             
        return false;     
     }
    
    
}
