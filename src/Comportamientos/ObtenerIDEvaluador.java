/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;

import Agentes.AgenteExperto;
import Conexion.Conexion;
import jade.core.behaviours.OneShotBehaviour;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jehm1
 */
public class ObtenerIDEvaluador extends OneShotBehaviour{
    
    AgenteExperto R;
    int idExperto;
    
    Conexion cc = new Conexion();
    Connection cn = cc.Conectar();
    
    public ObtenerIDEvaluador(AgenteExperto a){
        super (a);
        R=a;
    }
    
    
    public void action(){
        int id;
         id=obtenerID();
    }
    
    public int obtenerID(){
       String Instruccion0 = "SELECT max(idPTC) as idPTC FROM ptc;"; 
       
       try{
           PreparedStatement st = cn.prepareStatement(Instruccion0);
           ResultSet rst = st.executeQuery(Instruccion0);
           
           if(rst.next()){
               idExperto = rst.getInt("idPTC");
           }
           
           
       } catch (SQLException ex){
           System.out.println("Error; "+ex);
       }
       System.out.println(idExperto);
       return idExperto;
           
    }
}
