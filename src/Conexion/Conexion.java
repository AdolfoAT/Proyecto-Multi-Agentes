/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;
import java.sql.*;
import javax.swing.JOptionPane;

 /*
 * @author Adolfo
 */

public class Conexion {
    public Connection con;
    protected Statement stmt;    
    public Conexion(){
        
    }
    
    public Connection Conectar(){
        
        con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/sidecar","root","12345");
            stmt=con.createStatement();
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Error en la conexion con la base de datos");
            return null;
        }
        return con;
    }   
} 
    