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
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author EduardoMisael
 */
public class MostrarSeleccionados extends OneShotBehaviour{
    AgenteExperto AE;
    Conexion cc = new Conexion();
    Connection cn = cc.Conectar();
    
    public MostrarSeleccionados(AgenteExperto a){
        super (a);
        AE=a;
    }
    
    public void action(){
        MostrarSeleccionados();
        
    }
    
    public void MostrarSeleccionados(){
        String DatosSeleccionados,IDReporte;
        AE.IntExp.ProyectoEvaluar.removeAllItems();
        String SQLMostrarSeleccionados="SELECT reportedeinvestigacion_idReportedeInvestigacion from `ptc-asp` where Status_reporte like 'Seleccionado';";
        
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQLMostrarSeleccionados);
            while (rs.next()) {
                IDReporte = rs.getString("reportedeinvestigacion_idReportedeInvestigacion");
                String SQL = "SELECT Titulo FROM reportedeinvestigacion WHERE idReportedeInvestigacion="+IDReporte+";";
                Statement st2 = cn.createStatement();
                ResultSet rs2 = st2.executeQuery(SQL);
                    while (rs2.next()) {
                        DatosSeleccionados= rs2.getString("Titulo");
                        AE.IntExp.ProyectoEvaluar.addItem(DatosSeleccionados);
                    }
            }
        }catch(Exception e){
            System.out.println("Error: "+e);
        }
    }
    
}
    
