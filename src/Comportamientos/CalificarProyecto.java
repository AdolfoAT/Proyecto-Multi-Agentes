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
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author EduardoMisael
 */
public class CalificarProyecto extends OneShotBehaviour{
    
    AgenteExperto AE;
    Conexion cc = new Conexion();
    Connection cn = cc.Conectar();
    
    public CalificarProyecto(AgenteExperto a){
        super (a);
        AE=a;
    }

    
    public void action(){
        Calificar();
    }
    
    public void Calificar (){
        
        String NombreProyecto = AE.IntExp.ProyectoEvaluar.getSelectedItem().toString();
        String SQLSeleccionarProyecto ="SELECT idreportedeinvestigacion FROM reportedeinvestigacion WHERE reportedeinvestigacion.Titulo like '"+NombreProyecto+"'";
        System.out.println(SQLSeleccionarProyecto);
        String calif = AE.IntEva.Calificacion.getText();
        int Calificacion=Integer.parseInt(calif);
        int ID=0;
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(SQLSeleccionarProyecto);
                while (rs.next()){
                    ID=rs.getInt("idreportedeinvestigacion");
                    
                }
            }catch(Exception exc){
                System.out.println("Error: "+exc);
            }
        String SQLActualizarCalificacion = "UPDATE sidecar.`ptc-asp` SET Status_Reporte='Calificado', Calificacion="+Calificacion+" WHERE `reportedeinvestigacion_idReportedeInvestigacion` = "+ID+";";
        System.out.println(SQLActualizarCalificacion);
        try {
                PreparedStatement ps = cn.prepareStatement(SQLActualizarCalificacion);
               // System.out.println(SQLActualizarCalificacion);
                ps.executeUpdate();             
            
            }catch(Exception exc){
                System.out.println("Error: "+exc);
                JOptionPane.showMessageDialog(null,"Error: "+exc,"Importante", JOptionPane.ERROR_MESSAGE);
            }
    }
}
