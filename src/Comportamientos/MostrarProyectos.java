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


/**
 *
 * @author EduardoMisael
 */
public class MostrarProyectos extends OneShotBehaviour{
    
    AgenteExperto AE;
    Conexion cc = new Conexion();
    Connection cn = cc.Conectar();
    //Login Lo= new Login(AE);
    public MostrarProyectos(AgenteExperto a){
        super(a);
        AE=a;
    }
    public void action(){
        MostrarUsuario();
        MostrarProyectosSugeridos();
        
        
    }
    public void MostrarUsuario(){
        String Usu = AE.ID;
        System.out.println(Usu);
        AE.IntExp.Usu.setText(Usu);
    }
    public void MostrarProyectosSugeridos() {
        String IDReporte;
        String Datos ;
        String Usu = AE.ID;
        AE.IntExp.ProyectosRecomendados.removeAllItems();
        System.out.println("ID EVA : "+ Usu);
        /*String SQLIdUsuario="SELECT idPTC from ptc where idPTC like '"+Usu+"';";
        int idUsuario=0;
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQLIdUsuario);
            while(rs.next()){
                idUsuario = rs.getInt("idPTC");
            }
        }catch(Exception ex){
            System.out.println("Error: "+ex);
        }*/
        String SQLMostrarProyectos = "SELECT reportedeinvestigacion_idReportedeInvestigacion from `ptc-asp` where Status_reporte like 'Recomendado' and `ptc_idPTC` ="+Usu+";";
        System.out.println(SQLMostrarProyectos);
        try {
            PreparedStatement st;
            st = cn.prepareStatement(SQLMostrarProyectos);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                IDReporte = rs.getString("reportedeinvestigacion_idReportedeInvestigacion");
                System.out.println("Reporte de investigacion " + IDReporte);
                String SQL = "SELECT Titulo FROM reportedeinvestigacion WHERE idReportedeInvestigacion="+IDReporte+";";
                PreparedStatement st2 = cn.prepareStatement(SQL);
                ResultSet rs2 = st2.executeQuery();
                    while (rs2.next()) {
                        Datos= rs2.getString("Titulo");
                        AE.IntExp.ProyectosRecomendados.addItem(Datos);
                        System.out.println("Titulo" + Datos);
                    }
            }
        } catch (Exception exc) {
            System.out.println("No ejecutado por: " + exc);
        }
    }
}
