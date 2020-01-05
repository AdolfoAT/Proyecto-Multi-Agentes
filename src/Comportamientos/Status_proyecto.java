/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;

import Agentes.AgenteProponente;
import Conexion.Conexion;
import jade.core.behaviours.OneShotBehaviour;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Axel
 */
public class Status_proyecto extends OneShotBehaviour{
    AgenteProponente AgePro;
    Conexion cc = new Conexion();
    Connection cn = cc.Conectar();
    public Status_proyecto(AgenteProponente a){
        super (a);
        AgePro=a;
    }
    
    public void action(){
       Estatus(); 
    }
    
    
    
    public void Estatus(){
        String NombreProyecto = (String) AgePro.IP.Proyecto.getSelectedItem();
        String SQLNombreProyecto="SELECT * from idPtc-Asp where Status_reporte like 'Calificado' and ;";
        int idReporte=0;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQLNombreProyecto);
            while (rs.next()){
                idReporte=rs.getInt("idreportedeinvestigacion");
            }
        }catch(Exception ex){
            System.out.println("Error: "+ex);
        }
        
        int idPTC=0;
        String sql = "SELECT `ptc-asp`.`idPtc-Asp`,`ptc-asp`.Status_reporte, `ptc-asp`.reportedeinvestigacion_idReportedeInvestigacion FROM"
                + " `ptc-asp` WHERE `ptc-asp`.Status_reporte like 'Recomendado' and `ptc-asp`.reportedeinvestigacion_idReportedeInvestigacion="+idReporte+";";
        
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while (rs.next()){
                    idPTC=rs.getInt("idPtc-Asp");
        
                }
            }catch(Exception exc){
                System.out.println("Error: "+exc);
            }
            
        String SQLActualizar = "UPDATE sidecar.`ptc-asp` SET Status_Reporte='Seleccionado' WHERE `idPtc-Asp`="+idPTC+";";
        
            try {
                PreparedStatement ps = cn.prepareStatement(SQLActualizar);
               // System.out.println(SQLActualizar);
                ps.executeUpdate();             
               // JOptionPane.showMessageDialog(null,"Modificado con exito","Importante", JOptionPane.INFORMATION_MESSAGE);
            }catch(HeadlessException | SQLException exc){
                System.out.println("Error: "+exc);
                JOptionPane.showMessageDialog(null,"Error: "+exc,"Importante", JOptionPane.ERROR);
            }
    
}
}
