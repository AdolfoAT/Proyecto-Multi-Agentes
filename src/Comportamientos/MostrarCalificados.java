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
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EduardoMisael
 */
public class MostrarCalificados extends OneShotBehaviour{
    AgenteExperto AE;
    Conexion cc = new Conexion();
    Connection cn = cc.Conectar();
    DefaultTableModel modelo = new DefaultTableModel();
    public MostrarCalificados(AgenteExperto a){
        super (a);
        AE=a;
    }
    @Override
    public void action(){
        MostrarProyectosCalificados();
    }
    
    public void MostrarProyectosCalificados() {
        String Usu=AE.IntExp.Usu.getText();
        String idUsu=AE.ID;
        /*String SQLMostrarIDUsu="SELECT idPTC FROM ptc WHERE usuario LIKE '"+Usu+"';";
        System.out.println(SQLMostrarIDUsu);
        try{
            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(SQLMostrarIDUsu);
            while(rs.next()){
                idUsu=rs.getInt("idPTC");
            }
        }catch(Exception ex){
            System.out.println("Error: "+ex);
        }*/     
        modelo.addColumn("Nombre de Proyecto");
        modelo.addColumn("Calificacion");
        AE.IntExp.TablaCalificados.setModel(modelo);
        String datos[] = new String [2]; 
        String IDReporte;
        String Datos ;
        String SQLMostrarProyectos = "SELECT reportedeinvestigacion_idReportedeInvestigacion,calificacion from `ptc-asp` where ptc_idPTC = "+ idUsu +" and Status_reporte like 'Calificado' order by Calificacion asc;";
        int Calificacion;
        System.out.println(SQLMostrarProyectos);
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQLMostrarProyectos);
            while (rs.next()) {
                IDReporte = rs.getString("reportedeinvestigacion_idReportedeInvestigacion");
                String SQL = "SELECT Titulo FROM reportedeinvestigacion WHERE idReportedeInvestigacion="+IDReporte+";";
                try{
                Statement st2 = cn.createStatement();
                ResultSet rs2 = st2.executeQuery(SQL);
                System.out.println(SQL);
                    while (rs2.next()) {
                        Datos= rs2.getString("Titulo");
                        Calificacion=rs.getInt("Calificacion");
                        datos[0]=Datos;
                        datos[1]=rs.getString(2);
                        modelo.addRow(datos);
                    }
                    AE.IntExp.TablaCalificados.setModel(modelo);
                }catch(Exception ex){
                    
                }
            }
        } catch (Exception exc) {
            System.out.println("No ejecutado por: " + exc);
        }
    }
}
