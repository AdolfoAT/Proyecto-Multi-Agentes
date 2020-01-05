/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;

import Conexion.Conexion;
import jade.core.behaviours.OneShotBehaviour;
import java.sql.Connection;
import Agentes.AgenteExperto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author jehm1
 */
public class Status extends OneShotBehaviour{
    AgenteExperto AE;
    Conexion cc = new Conexion();
    Connection cn = cc.Conectar();
    public Status(AgenteExperto a){
        super (a);
        AE=a;
    }
    
    public void action(){
       Seleccionar(); 
    }
    
  /*  public void MostrarProyectosSugeridos(){
        String DatosAreas;
        AE.IntExp.ProyectosRecomendados.removeAllItems();
        String SQLMostrarProyectos="SELECT reportedeinvestigacion.Titulo FROM reportedeinvestigacion, `ptc-asp`,aspirante where reportedeinvestigacion.Aspirante_idAspirante=aspirante.idAspirante and `ptc-asp`.Status_reporte like 'Recomendado';";
        try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(SQLMostrarProyectos);
                while(rs.next()){
                    DatosAreas=rs.getString("Titulo");
                    AE.IntExp.ProyectosRecomendados.addItem(DatosAreas);
                }
            }catch(Exception exc){
                System.out.println("No ejecutado por: "+exc);
            }
    }*/
    public void Seleccionar(){
        String NombreProyecto = (String) AE.IntExp.ProyectosRecomendados.getSelectedItem();
        String SQLNombreProyecto="SELECT idreportedeinvestigacion from reportedeinvestigacion where titulo like '"+NombreProyecto+"';";
        
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
                System.out.println(SQLActualizar);
                ps.executeUpdate();             
                JOptionPane.showMessageDialog(null,"Modificado con exito","Importante", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception exc){
                System.out.println("Error: "+exc);
                JOptionPane.showMessageDialog(null,"Error: "+exc,"Importante", JOptionPane.ERROR);
            }
            
    } 
    /*
    public void MostrarSeleccionados(){
        String DatosSeleccionados;
        AE.IntExp.ProyectoEvaluar.removeAllItems();
        String SQLMostrarSeleccionados="SELECT reportedeinvestigacion.Titulo FROM reportedeinvestigacion, `ptc-asp`,aspirante where reportedeinvestigacion.Aspirante_idAspirante=aspirante.idAspirante and `ptc-asp`.Status_reporte like 'Seleccionado';";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQLMostrarSeleccionados);
            while (rs.next()){
                DatosSeleccionados=rs.getString("Titulo");
                AE.IntExp.ProyectoEvaluar.addItem(DatosSeleccionados);
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    */
    /*public void CalificarProyecto(){
        
        String NombreProyecto = (String) AE.IntExp.ProyectoEvaluar.getSelectedItem();
        AE.IntEva.NomProyecto.setText(NombreProyecto);
        String SQLSeleccionarProyecto ="SELECT `Ptc-Asp`.`Calificacion`,`IdPtc-Asp` FROM `Ptc-Asp`, reportedeinvestigacion WHERE reportedeinvestigacion.NombreProyecto like '"+NombreProyecto+"'";
        int Calificacion=Integer.parseInt(AE.IntEva.Calificacion.getText());
        int ID=0;
            try {
                Statement st = cn.createStatement();
                ResultSet rs = st.executeQuery(SQLSeleccionarProyecto);
                while (rs.next()){
                    ID=rs.getInt("IdPtc-Asp");
                    
                }
            }catch(Exception exc){
                System.out.println("Error: "+exc);
            }
        String SQLActualizarCalificacion = "UPDATE sidecar.`ptc-asp` SET Calificacion="+Calificacion+" WHERE `IdPtc-Asp` = "+ID+";";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQLActualizarCalificacion);
            if(!rs.next()){
                JOptionPane.showMessageDialog(null,"Modificado con exito","Importante", JOptionPane.INFORMATION_MESSAGE);
            }
        }catch (Exception e){
            System.out.println("Error: "+e);
        }
    }*/
    
}
